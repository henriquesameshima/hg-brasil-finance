package io.sameshima.test.service.api;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.doAnswer;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.io.IOException;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import io.sameshima.hgbrasil.service.api.AbstractService;
import io.sameshima.hgbrasil.service.api.CallbackResponse;
import io.sameshima.hgbrasil.service.api.HGBrasilService;
import io.sameshima.hgbrasil.service.dto.DefaultResponse;
import io.sameshima.hgbrasil.service.exceptions.SynchronousException;
import okhttp3.MediaType;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

class AbstractServiceTest {

	@Mock
	private HGBrasilService mockService;

	@Mock
	private Call<DefaultResponse<String>> mockCall;

	@Mock
	private CallbackResponse<String> mockCallback;

	@Captor
	private ArgumentCaptor<Callback<DefaultResponse<String>>> callbackCaptor;

	private AbstractService<String> abstractService;

	@BeforeEach
	public void setUp() {
		MockitoAnnotations.openMocks(this);

		abstractService = new AbstractService<String>(mockService, "") {
			@Override
			protected Call<DefaultResponse<String>> makeServiceCall(Object... params) {
				return mockCall;
			}
		};
	}

	@Test
	void testFetchDataAsyncSuccess() {
		DefaultResponse<String> expectedResult = new DefaultResponse<>();

		doAnswer(invocation -> {
			callbackCaptor.getValue().onResponse(mockCall, Response.success(expectedResult));
			return null;
		}).when(mockCall).enqueue(callbackCaptor.capture());

		abstractService.fetchDataAsync(mockCallback);

		verify(mockCallback).onSuccess(expectedResult);
	}

	@Test
	void testFetchDataAsyncResponseFailure() {
		doAnswer(invocation -> {
			callbackCaptor.getValue().onResponse(mockCall, Response.error(400,
					ResponseBody.create("{}", MediaType.Companion.parse("application/json; charset=utf-8"))));
			return null;
		}).when(mockCall).enqueue(callbackCaptor.capture());

		abstractService.fetchDataAsync(mockCallback);

		verify(mockCallback).onError(anyString());
	}

	@Test
	void testFetchDataAsyncFailure() {
		Throwable expectedThrowable = new Throwable("Error");

		doAnswer(invocation -> {
			callbackCaptor.getValue().onFailure(mockCall, expectedThrowable);
			return null;
		}).when(mockCall).enqueue(callbackCaptor.capture());

		abstractService.fetchDataAsync(mockCallback);

		verify(mockCallback).onError(expectedThrowable.getMessage());
	}

	@Test
	void testFetchDataSuccess() throws Exception {
		DefaultResponse<String> expectedResult = new DefaultResponse<>();
		when(mockCall.execute()).thenReturn(Response.success(expectedResult));

		DefaultResponse<String> result = abstractService.fetchData();
		assertEquals(expectedResult, result);
	}

	@Test
    void testFetchDataFailure() throws Exception {
        when(mockCall.execute()).thenReturn(Response.error(400, ResponseBody.create("{}",
				MediaType.Companion.parse("application/json; charset=utf-8"))));

        assertThrows(SynchronousException.class, () -> abstractService.fetchData());
    }

	@Test
    void testFetchDataIOException() throws Exception {
        when(mockCall.execute()).thenThrow(IOException.class);

        assertThrows(SynchronousException.class, () -> abstractService.fetchData());
    }

}