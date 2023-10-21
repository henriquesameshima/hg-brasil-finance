package io.sameshima.test.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

import java.util.Map;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import io.sameshima.hgbrasil.service.StockPriceService;
import io.sameshima.hgbrasil.service.api.HGBrasilService;
import io.sameshima.hgbrasil.service.dto.DefaultResponse;
import io.sameshima.hgbrasil.service.dto.stocks.price.StockOrError;
import retrofit2.Call;

class StockPriceServiceTest {

	@InjectMocks
	private StockPriceService stockPriceService;

	@Mock
	private HGBrasilService hgBrasilService;

	@Mock
	private Call<DefaultResponse<Map<String, StockOrError>>> mockedCall;

	@BeforeEach
	public void setUp() {
		MockitoAnnotations.openMocks(this);
		stockPriceService = new StockPriceService(hgBrasilService, "");
	}

	@Test
	void testMakeServiceCallWithSymbol() {
		when(hgBrasilService.getStocksPrice(anyString(), anyString())).thenReturn(mockedCall);

		Call<DefaultResponse<Map<String, StockOrError>>> resultCall = stockPriceService.makeServiceCall("");

		assertEquals(mockedCall, resultCall);
	}

	@Test
	void testMakeServiceCallWithoutSymbol() {
		assertThrows(IllegalArgumentException.class, () -> stockPriceService.makeServiceCall());
	}

	@Test
	void testMakeServiceCallWrongFormat() {
		assertThrows(IllegalArgumentException.class, () -> stockPriceService.makeServiceCall(123));
	}
}
