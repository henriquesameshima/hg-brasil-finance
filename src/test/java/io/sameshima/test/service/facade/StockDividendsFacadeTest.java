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

import io.sameshima.hgbrasil.service.StockDividendsService;
import io.sameshima.hgbrasil.service.dto.DefaultResponse;
import io.sameshima.hgbrasil.service.dto.stocks.dividends.DividendsOrError;
import io.sameshima.hgbrasil.service.exceptions.SynchronousException;
import io.sameshima.hgbrasil.service.facades.StockDividendsFacade;

class StockDividendsFacadeTest {

	@InjectMocks
	private StockDividendsFacade stockDividendsFacade;

	@Mock
	private StockDividendsService stockDividendsService;

	@BeforeEach
	public void setUp() {
		MockitoAnnotations.openMocks(this);
	}

	@Test
	void testGetDividendsAsyncWithSingleSymbol() {
		String symbol = "AAPL";
		
		stockDividendsFacade.getDividendsAsync(symbol, null);

		verify(stockDividendsService, times(1)).fetchDataAsync(null, symbol);
	}

	@Test
	void testGetDividendsAsyncWithMultipleSymbols() {
		List<String> symbols = Arrays.asList("AAPL", "GOOGL", "MSFT");
		String joinedSymbols = String.join(",", symbols);
		stockDividendsFacade.getDividendsAsync(symbols, null);
		verify(stockDividendsService, times(1)).fetchDataAsync(null, joinedSymbols);
	}

	@Test
	void testGetDividendsWithSingleSymbol() throws SynchronousException {
		String symbol = "AAPL";
		DefaultResponse<Map<String, DividendsOrError>> expectedResponse = new DefaultResponse<>();
		when(stockDividendsService.fetchData(symbol)).thenReturn(expectedResponse);

		DefaultResponse<Map<String, DividendsOrError>> response = stockDividendsFacade.getDividends(symbol);

		assertEquals(expectedResponse, response);
	}

	@Test
	void testGetDividendsWithMultipleSymbols() throws SynchronousException {
		List<String> symbols = Arrays.asList("AAPL", "GOOGL", "MSFT");
		DefaultResponse<Map<String, DividendsOrError>> expectedResponse = new DefaultResponse<>();
		String joinedSymbols = String.join(",", symbols);
		when(stockDividendsService.fetchData(joinedSymbols)).thenReturn(expectedResponse);

		DefaultResponse<Map<String, DividendsOrError>> response = stockDividendsFacade.getDividends(symbols);

		assertEquals(expectedResponse, response);
	}

	@Test
	void testGetDividendsWithSingleSymbolThrowsException() throws SynchronousException {
		String symbol = "AAPL";
		SynchronousException expectedException = new SynchronousException("Error");
		doThrow(expectedException).when(stockDividendsService).fetchData(symbol);

		SynchronousException exception = assertThrows(SynchronousException.class, () -> stockDividendsFacade.getDividends(symbol));

		assertEquals(expectedException, exception);
	}

	@Test
	void testGetDividendsWithMultipleSymbolsThrowsException() throws SynchronousException {
		List<String> symbols = Arrays.asList("AAPL", "GOOGL", "MSFT");
		SynchronousException expectedException = new SynchronousException("Error");
		String joinedSymbols = String.join(",", symbols);
		
		doThrow(expectedException).when(stockDividendsService).fetchData(joinedSymbols);

		SynchronousException exception = assertThrows(SynchronousException.class, () -> stockDividendsFacade.getDividends(symbols));

		assertEquals(expectedException, exception);
	}
}