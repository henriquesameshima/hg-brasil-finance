package io.sameshima.test.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import io.sameshima.hgbrasil.service.TickerListService;
import io.sameshima.hgbrasil.service.api.HGBrasilService;
import io.sameshima.hgbrasil.service.dto.DefaultResponse;
import retrofit2.Call;

class TickerListServiceTest {

	@InjectMocks
	private TickerListService tickerListService;

	@Mock
	private HGBrasilService hgBrasilService;

	@Mock
	private Call<DefaultResponse<List<String>>> mockedCall;

	@BeforeEach
	public void setUp() {
		MockitoAnnotations.openMocks(this);
		tickerListService = new TickerListService(hgBrasilService, "");
	}

	@Test
	void testMakeServiceCall() {
		when(hgBrasilService.getAllTickers(anyString())).thenReturn(mockedCall);

		Call<DefaultResponse<List<String>>> resultCall = tickerListService.makeServiceCall();

		assertEquals(mockedCall, resultCall);
	}
}
