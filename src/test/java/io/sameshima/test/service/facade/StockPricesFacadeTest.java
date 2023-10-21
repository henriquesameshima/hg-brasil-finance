package io.sameshima.test.service.facade;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import io.sameshima.hgbrasil.service.StockPriceService;
import io.sameshima.hgbrasil.service.dto.DefaultResponse;
import io.sameshima.hgbrasil.service.dto.stocks.price.StockOrError;
import io.sameshima.hgbrasil.service.exceptions.SynchronousException;
import io.sameshima.hgbrasil.service.facades.StockPricesFacade;

class StockPricesFacadeTest {

	@InjectMocks
	private StockPricesFacade stockPricesFacade;

	@Mock
	private StockPriceService stockPriceService;

	@BeforeEach
	public void setUp() {
		MockitoAnnotations.openMocks(this);
	}

	@Test
	void testGetHighStockPricesAsync() {
		stockPricesFacade.getHighStockPricesAsync(null);
		verify(stockPriceService, times(1)).fetchDataAsync(null, "get-high");
	}

	@Test
	void testGetLowStockPricesAsync() {
		stockPricesFacade.getLowStockPricesAsync(null);
		verify(stockPriceService, times(1)).fetchDataAsync(null, "get-low");
	}

	@Test
	void testGetStockPricesAsyncWithSymbol() {
		String symbol = "AAPL";
		stockPricesFacade.getStockPricesAsync(symbol, null);
		verify(stockPriceService, times(1)).fetchDataAsync(null, symbol);
	}

	@Test
	void testGetStockPricesAsyncWithSymbolsList() {
		List<String> symbols = Arrays.asList("AAPL", "GOOG");
		stockPricesFacade.getStockPricesAsync(symbols, null);
		verify(stockPriceService, times(1)).fetchDataAsync(null, "AAPL,GOOG");
	}

	@Test
	void testGetHighStockPrices() throws SynchronousException {
		DefaultResponse<Map<String, StockOrError>> expectedResponse = new DefaultResponse<>();
		when(stockPriceService.fetchData("get-high")).thenReturn(expectedResponse);
		assertEquals(expectedResponse, stockPricesFacade.getHighStockPrices());
	}

	@Test
	void testGetLowStockPrices() throws SynchronousException {
		DefaultResponse<Map<String, StockOrError>> expectedResponse = new DefaultResponse<>();
		when(stockPriceService.fetchData("get-low")).thenReturn(expectedResponse);
		assertEquals(expectedResponse, stockPricesFacade.getLowStockPrices());
	}

	@Test
	void testGetStockPricesWithSymbol() throws SynchronousException {
		String symbol = "AAPL";
		DefaultResponse<Map<String, StockOrError>> expectedResponse = new DefaultResponse<>();
		when(stockPriceService.fetchData(symbol)).thenReturn(expectedResponse);
		assertEquals(expectedResponse, stockPricesFacade.getStockPrices(symbol));
	}

	@Test
	void testGetStockPricesWithSymbolsList() throws SynchronousException {
		List<String> symbols = Arrays.asList("AAPL", "GOOG");
		DefaultResponse<Map<String, StockOrError>> expectedResponse = new DefaultResponse<>();
		when(stockPriceService.fetchData("AAPL,GOOG")).thenReturn(expectedResponse);
		assertEquals(expectedResponse, stockPricesFacade.getStockPrices(symbols));
	}

	@Test
	void testGetHighStockPricesWithException() throws SynchronousException {
		SynchronousException expectedException = new SynchronousException("Error");
		doThrow(expectedException).when(stockPriceService).fetchData("get-high");
		SynchronousException exception = assertThrows(SynchronousException.class, () -> stockPricesFacade.getHighStockPrices());
		assertEquals(expectedException, exception);
	}
}
