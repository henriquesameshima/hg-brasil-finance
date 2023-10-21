package io.sameshima.test.service.api;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.io.IOException;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import io.sameshima.hgbrasil.service.api.RetrofitClient;
import okhttp3.Call;
import okhttp3.OkHttpClient;
import okhttp3.Response;
import okhttp3.mockwebserver.MockResponse;
import okhttp3.mockwebserver.MockWebServer;

class RetrofitInterceptorTest {

	private MockWebServer mockWebServer;
	private RetrofitClient client;

	@BeforeEach
	public void setup() throws IOException {
		mockWebServer = new MockWebServer();
		mockWebServer.start();
		client = new RetrofitClient();
	}

	@AfterEach
	public void tearDown() throws IOException {
		mockWebServer.shutdown();
	}

	@Test
	void testResponseInterceptorError() {
		mockWebServer.enqueue(new MockResponse().setBody("{\"results\": {\"error\": true, \"message\": \"Some error message\"}}"));

		OkHttpClient okHttpClient = new OkHttpClient.Builder().addInterceptor(client.responseInterceptor()).build();
		
		Call call = okHttpClient.newCall(new okhttp3.Request.Builder().url(mockWebServer.url("/")).build());
		
		assertThrows(IOException.class, () -> call.execute());
	}
	
	@Test
	void testResponseInterceptorSuccess() throws IOException {
		mockWebServer.enqueue(new MockResponse().setBody("{\"results\": {\"data\": \"Some data\"}}"));

		OkHttpClient okHttpClient = new OkHttpClient.Builder().addInterceptor(client.responseInterceptor()).build();
		
		Call call = okHttpClient.newCall(new okhttp3.Request.Builder().url(mockWebServer.url("/")).build());
		
		Response response = call.execute();
		
		assertNotNull(response.body());
	}

	@Test
	void testCacheInterceptor() throws IOException {
		mockWebServer.enqueue(new MockResponse().setBody("{\"results\": {\"data\": \"Some data\"}}"));

		OkHttpClient okHttpClient = new OkHttpClient.Builder().addInterceptor(client.createCacheInterceptor(10)).build();
		
		Response call = okHttpClient.newCall(new okhttp3.Request.Builder().url(mockWebServer.url("/")).build()).execute();
		
		assertEquals("public, max-age=10", call.headers().get("Cache-Control"));
	}
}