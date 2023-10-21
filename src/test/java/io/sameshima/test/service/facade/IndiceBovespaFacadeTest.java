package io.sameshima.test.service.facade;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import io.sameshima.hgbrasil.service.IndiceBovespaDetailService;
import io.sameshima.hgbrasil.service.dto.DefaultResponse;
import io.sameshima.hgbrasil.service.dto.ibovespa.IbovespaInfo;
import io.sameshima.hgbrasil.service.exceptions.SynchronousException;
import io.sameshima.hgbrasil.service.facades.IndiceBovespaFacade;

class IndiceBovespaFacadeTest {

	@InjectMocks
	private IndiceBovespaFacade facade;

	@Mock
	private IndiceBovespaDetailService detailService;

	@BeforeEach
	public void setUp() {
		MockitoAnnotations.openMocks(this);
	}

	@Test
	void testGetIbovespaDetail() throws SynchronousException {
		DefaultResponse<List<IbovespaInfo>> response = new DefaultResponse<>();
		when(detailService.fetchData(0)).thenReturn(response);

		DefaultResponse<List<IbovespaInfo>> result = facade.getIbovespaDetail();

		assertEquals(response, result);
	}

	@Test
	void testGetIbovespaDetailDate() throws SynchronousException {
		DefaultResponse<List<IbovespaInfo>> response = new DefaultResponse<>();
		when(detailService.fetchData("2023-03-01")).thenReturn(response);

		DefaultResponse<List<IbovespaInfo>> result = facade.getIbovespaDetail("2023-03-01");

		assertEquals(response, result);
	}

	@Test
	void testGetIbovespaDetailIntervalDate() throws SynchronousException {
		DefaultResponse<List<IbovespaInfo>> response = new DefaultResponse<>();
		when(detailService.fetchData("2023-03-01", "2023-03-02")).thenReturn(response);

		DefaultResponse<List<IbovespaInfo>> result = facade.getIbovespaDetail("2023-03-01", "2023-03-02");

		assertEquals(response, result);
	}

	@Test
	void testGetIbovespaDetailAsyncWithDaysAgo() {
		doNothing().when(detailService).fetchDataAsync(null, 5);
		facade.getIbovespaDetailAsync(5, null);
		verify(detailService, times(1)).fetchDataAsync(null, 5);
	}

	@Test
	void testGetIbovespaDetailAsyncWithoutDaysAgo() {
		doNothing().when(detailService).fetchDataAsync(null, 0);
		facade.getIbovespaDetailAsync(null);
		verify(detailService, times(1)).fetchDataAsync(null, 0);
	}

	@Test
	void testGetIbovespaDetailAsyncWithDate() {
		doNothing().when(detailService).fetchDataAsync(null, "2023-03-01");
		facade.getIbovespaDetailAsync("2023-03-01", null);
		verify(detailService, times(1)).fetchDataAsync(null, "2023-03-01");
	}

	@Test
	void testGetIbovespaDetailAsyncWithIntervalDate() {
		doNothing().when(detailService).fetchDataAsync(null, "2023-03-01", "2023-03-02");
		facade.getIbovespaDetailAsync("2023-03-01", "2023-03-02", null);
		verify(detailService, times(1)).fetchDataAsync(null, "2023-03-01", "2023-03-02");
	}

}