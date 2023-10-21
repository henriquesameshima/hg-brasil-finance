package io.sameshima.test.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
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
	void makeServiceCallWithNotNumberTest() {
		when(hgBrasilService.getIbovespaDetail(anyString(), anyInt())).thenReturn(mockedCall);

		Call<DefaultResponse<List<IbovespaInfo>>> result = indiceBovespaDetailService.makeServiceCall("");

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
	void makeServiceCallWithoutDaysAgoTest() {

		when(hgBrasilService.getIbovespaDetail(anyString(), anyInt())).thenReturn(mockedCall);

		Call<DefaultResponse<List<IbovespaInfo>>> result = indiceBovespaDetailService.makeServiceCall();

		assertEquals(mockedCall, result);
	}
}