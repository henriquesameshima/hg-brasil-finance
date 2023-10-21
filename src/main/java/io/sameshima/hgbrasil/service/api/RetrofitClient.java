package io.sameshima.hgbrasil.service.api;

import java.io.File;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;

import io.sameshima.hgbrasil.service.dto.stocks.dividends.DividendsInfo;
import io.sameshima.hgbrasil.service.dto.stocks.dividends.DividendsOrError;
import io.sameshima.hgbrasil.service.dto.stocks.price.StockInfo;
import io.sameshima.hgbrasil.service.dto.stocks.price.StockOrError;
import io.sameshima.hgbrasil.utils.DeserializadorDefault;
import okhttp3.Cache;
import okhttp3.Interceptor;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.ResponseBody;
import retrofit2.Retrofit;
import retrofit2.converter.jackson.JacksonConverterFactory;

public final class RetrofitClient {

	private static final int TEN_MB_CACHE_SIZE = 10 * 1024 * 1024;
	private static final String BASE_URL = "https://api.hgbrasil.com/finance/";

	private Retrofit retrofit;

	public RetrofitClient() {
		this(new OkHttpClient.Builder());
	}

	public RetrofitClient(OkHttpClient.Builder okHttpClientBuilder) {
		okHttpClientBuilder.callTimeout(30, TimeUnit.SECONDS);
		retrofit = createRetrofit(okHttpClientBuilder);
	}

	private Retrofit createRetrofit(OkHttpClient.Builder okHttpClientBuilder) {
		okHttpClientBuilder.addInterceptor(responseInterceptor());
		return new Retrofit.Builder().baseUrl(BASE_URL)
				.addConverterFactory(JacksonConverterFactory.create(configureMapper()))
				.client(okHttpClientBuilder.build()).build();
	}

	public HGBrasilService getApiService() {
		return retrofit.create(HGBrasilService.class);
	}

	public HGBrasilService getApiService(CacheConfig cacheConfig) {
		if (cacheConfig == null) {
			return getApiService();
		}

		OkHttpClient.Builder okHttpClientBuilder = new OkHttpClient.Builder();
		File httpCacheDirectory = new File(System.getProperty("java.io.tmpdir"), "hg-brasil-cache");
		Cache cache = new Cache(httpCacheDirectory, TEN_MB_CACHE_SIZE);

		okHttpClientBuilder.addNetworkInterceptor(createCacheInterceptor(cacheConfig.cacheDurationInSeconds()))
				.cache(cache);

		this.retrofit = createRetrofit(okHttpClientBuilder);
		return getApiService();
	}

	public Interceptor responseInterceptor() {
		return chain -> {
			Request request = chain.request();
			Response originalResponse = chain.proceed(request);
			String responseBodyString = originalResponse.body().string();

			JsonNode jsonNode = new ObjectMapper().readTree(responseBodyString).get("results");
			if (jsonNode.has("error")) {
				throw new IOException(jsonNode.get("message").asText());
			}

			ResponseBody newResponse = ResponseBody.create(responseBodyString,
					MediaType.Companion.parse("application/json; charset=utf-8"));
			return originalResponse.newBuilder().body(newResponse).build();
		};
	}

	public Interceptor createCacheInterceptor(final int cacheDurationInSeconds) {
		return chain -> {
			Response originalResponse = chain.proceed(chain.request());
			return originalResponse.newBuilder().removeHeader("Pragma")
					.header("Cache-Control", "public, max-age=" + cacheDurationInSeconds).build();
		};
	}

	private ObjectMapper configureMapper() {
		ObjectMapper mapper = new ObjectMapper();
		SimpleModule module = createModuleWithDeserializers();
		mapper.registerModule(module);
		return mapper;
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	private SimpleModule createModuleWithDeserializers() {
		SimpleModule module = new SimpleModule();
		module.addDeserializer(StockOrError.class, new DeserializadorDefault(StockInfo.class));
		module.addDeserializer(DividendsOrError.class, new DeserializadorDefault(DividendsInfo.class));
		return module;
	}
}