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

import io.sameshima.hgbrasil.service.StockDividendsService;
import io.sameshima.hgbrasil.service.api.HGBrasilService;
import io.sameshima.hgbrasil.service.dto.DefaultResponse;
import io.sameshima.hgbrasil.service.dto.stocks.dividends.DividendsOrError;
import retrofit2.Call;

class StockDividendsServiceTest {

	@InjectMocks
	private StockDividendsService stockDividendsService;

	@Mock
	private HGBrasilService hgBrasilService;

	@Mock
	private Call<DefaultResponse<Map<String, DividendsOrError>>> mockedCall;

	@BeforeEach
	public void setUp() {
		MockitoAnnotations.openMocks(this);
		stockDividendsService = new StockDividendsService(hgBrasilService, "");
	}

	@Test
	void testMakeServiceCallWithSymbol() {
		when(hgBrasilService.getStocksDividends(anyString(), anyString())).thenReturn(mockedCall);

		Call<DefaultResponse<Map<String, DividendsOrError>>> resultCall = stockDividendsService.makeServiceCall("");

		assertEquals(mockedCall, resultCall);
	}

	@Test
	void testMakeServiceCallWithoutSymbol() {
		assertThrows(IllegalArgumentException.class, () -> stockDividendsService.makeServiceCall());
	}

	@Test
	void testMakeServiceCallWrongFormat() {
		assertThrows(IllegalArgumentException.class, () -> stockDividendsService.makeServiceCall(123));
	}
}