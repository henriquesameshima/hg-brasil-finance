package io.sameshima.hgbrasil.service.api;

import java.io.File;
import java.io.IOException;

import okhttp3.Cache;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Response;
import retrofit2.Retrofit;
import retrofit2.converter.jackson.JacksonConverterFactory;

public final class RetrofitClient {

	private RetrofitClient() {
	}

	private static final String BASE_URL = "https://api.hgbrasil.com/finance/";

	private static Retrofit retrofit = null;

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
			int cacheSize = 10 * 1024 * 1024;
			Cache cache = new Cache(httpCacheDirectory, cacheSize);

			OkHttpClient.Builder okHttpClientBuilder = new OkHttpClient.Builder();

			if (cacheConfig != null) {
				okHttpClientBuilder.addNetworkInterceptor(createCacheInterceptor(cacheConfig.cacheDurationInSeconds()))
						.cache(cache);
			}

			retrofit = new Retrofit.Builder().baseUrl(BASE_URL).addConverterFactory(JacksonConverterFactory.create())
					.client(okHttpClientBuilder.build()).build();
		}
		return retrofit.create(HGBrasilService.class);
	}

	public static HGBrasilService getApiService() {
		return getApiService(null);
	}

}