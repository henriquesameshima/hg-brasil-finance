package io.sameshima.test.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import io.sameshima.hgbrasil.service.IndiceBovespaDetailService;
import io.sameshima.hgbrasil.service.api.HGBrasilService;
import io.sameshima.hgbrasil.service.dto.DefaultResponse;
import io.sameshima.hgbrasil.service.dto.ibovespa.IbovespaInfo;
import retrofit2.Call;

class IndiceBovespaDetailServiceTest {

	@InjectMocks
	private IndiceBovespaDetailService indiceBovespaDetailService;

	@Mock
	private HGBrasilService hgBrasilService;

	@Mock
	private Call<DefaultResponse<List<IbovespaInfo>>> mockedCall;

	@BeforeEach
	public void setUp() {
		MockitoAnnotations.openMocks(this);
		indiceBovespaDetailService = new IndiceBovespaDetailService(hgBrasilService, "");
	}

	@Test
	void makeServiceCallWithIntervalDateTest() {
		String startDate = "2023-03-01";
		String endDate = "2023-03-02";

		when(hgBrasilService.getIbovespaDetail(anyString(), anyString(), anyString())).thenReturn(mockedCall);

		Call<DefaultResponse<List<IbovespaInfo>>> result = indiceBovespaDetailService.makeServiceCall(startDate, endDate);

		assertEquals(mockedCall, result);
	}

	@Test
	void makeServiceCallWithDateTest() {
		String date = "2023-03-01";

		when(hgBrasilService.getIbovespaDetail(anyString(), anyString())).thenReturn(mockedCall);

		Call<DefaultResponse<List<IbovespaInfo>>> result = indiceBovespaDetailService.makeServiceCall(date);

		assertEquals(mockedCall, result);
	}

	@Test
	void makeServiceCallWithDaysAgoTest() {
		int daysAgo = 5;

		when(hgBrasilService.getIbovespaDetail(anyString(), anyInt())).thenReturn(mockedCall);

		Call<DefaultResponse<List<IbovespaInfo>>> result = indiceBovespaDetailService.makeServiceCall(daysAgo);

		assertEquals(mockedCall, result);
	}

	@Test
	void makeServiceCallWrongParamsTest() {
		assertThrows(IllegalArgumentException.class, () -> indiceBovespaDetailService.makeServiceCall());
		assertThrows(IllegalArgumentException.class, () -> indiceBovespaDetailService.makeServiceCall("", 0));
		assertThrows(IllegalArgumentException.class, () -> indiceBovespaDetailService.makeServiceCall(0, ""));
		assertThrows(IllegalArgumentException.class, () -> indiceBovespaDetailService.makeServiceCall(0, 0));
		assertThrows(IllegalArgumentException.class, () -> indiceBovespaDetailService.makeServiceCall(0f));
	}
}