package io.sameshima.test.service.facade;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import io.sameshima.hgbrasil.service.TickerListService;
import io.sameshima.hgbrasil.service.dto.DefaultResponse;
import io.sameshima.hgbrasil.service.exceptions.SynchronousException;
import io.sameshima.hgbrasil.service.facades.TickersFacade;

class TickersFacadeTest {

	@InjectMocks
	private TickersFacade tickersFacade;

	@Mock
	private TickerListService tickerListService;

	@BeforeEach
	public void setUp() {
		MockitoAnnotations.openMocks(this);
	}

	@Test
	void testGetAllTickers() throws SynchronousException {
		DefaultResponse<List<String>> expectedResponse = new DefaultResponse<>();
		when(tickerListService.fetchData()).thenReturn(expectedResponse);

		DefaultResponse<List<String>> response = tickersFacade.getAllTickers();
		assertEquals(expectedResponse, response);
	}

	@Test
	void testGetAllTickersAsync() {
		tickersFacade.getAllTickersAsync(null);
		verify(tickerListService, times(1)).fetchDataAsync(null);
	}

	@Test
	void testGetAllTickersWithException() throws SynchronousException {
		SynchronousException expectedException = new SynchronousException("Error");

		doThrow(expectedException).when(tickerListService).fetchData();

		SynchronousException exception = assertThrows(SynchronousException.class, () -> tickersFacade.getAllTickers());

		assertEquals(expectedException, exception);
	}
}
