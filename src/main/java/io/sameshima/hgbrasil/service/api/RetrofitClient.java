package io.sameshima.hgbrasil.service.api;

import java.io.File;
import java.io.IOException;

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
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.ResponseBody;
import retrofit2.Retrofit;
import retrofit2.converter.jackson.JacksonConverterFactory;

public final class RetrofitClient {

	private RetrofitClient() {
	}

	private static final int TEN_MB_CACHE_SIZE = 10 * 1024 * 1024;

	private static final String BASE_URL = "https://api.hgbrasil.com/finance/";

	private static Retrofit retrofit = null;

	private static Interceptor responseInterceptor() {
		return new Interceptor() {
			@Override
			public Response intercept(Chain chain) throws IOException {
				Request request = chain.request();

				Response originalResponse = chain.proceed(request);
				String responseBodyString = originalResponse.body().string();

				JsonNode jsonNode = new ObjectMapper().readTree(responseBodyString).get("results");

				if(jsonNode.has("error") && jsonNode.get("error").asBoolean()) {
					throw new IOException(jsonNode.get("message").asText());
				}

				ResponseBody newResponseBody = ResponseBody.create(originalResponse.body().contentType(), responseBodyString);
				return originalResponse.newBuilder().body(newResponseBody).build();
			}
		};
	}

	private static Interceptor createCacheInterceptor(final int cacheDurationInSeconds) {
		return new Interceptor() {
			@Override
			public Response intercept(Chain chain) throws IOException {
				Response originalResponse = chain.proceed(chain.request());

				return originalResponse.newBuilder().removeHeader("Pragma")
						.header("Cache-Control", "public, max-age=" + cacheDurationInSeconds).build();
			}
		};
	}

	public static HGBrasilService getApiService(final CacheConfig cacheConfig) {
		if (retrofit == null) {
			File httpCacheDirectory = new File(System.getProperty("java.io.tmpdir"), "hg-brasil-cache");
			int cacheSize = TEN_MB_CACHE_SIZE;
			Cache cache = new Cache(httpCacheDirectory, cacheSize);

			OkHttpClient.Builder okHttpClientBuilder = new OkHttpClient.Builder();
			okHttpClientBuilder.addInterceptor(responseInterceptor());

			if (cacheConfig != null) {
				okHttpClientBuilder.addNetworkInterceptor(createCacheInterceptor(cacheConfig.cacheDurationInSeconds()))
						.cache(cache);
			}

			retrofit = new Retrofit.Builder().baseUrl(BASE_URL)
					.addConverterFactory(JacksonConverterFactory.create(configureMapper()))
					.client(okHttpClientBuilder.build()).build();
		}
		return retrofit.create(HGBrasilService.class);
	}

	private static ObjectMapper configureMapper() {
		ObjectMapper mapper = new ObjectMapper();
		SimpleModule module = createModuleWithDeserializers();
		mapper.registerModule(module);
		return mapper;
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	private static SimpleModule createModuleWithDeserializers() {
		SimpleModule module = new SimpleModule();
		module.addDeserializer(StockOrError.class, new DeserializadorDefault(StockInfo.class));
		module.addDeserializer(DividendsOrError.class, new DeserializadorDefault(DividendsInfo.class));
		return module;
	}

	public static HGBrasilService getApiService() {
		return getApiService(null);
	}

}