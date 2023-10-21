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

import io.sameshima.hgbrasil.service.TaxesService;
import io.sameshima.hgbrasil.service.api.HGBrasilService;
import io.sameshima.hgbrasil.service.dto.DefaultResponse;
import io.sameshima.hgbrasil.service.dto.taxes.Taxes;
import retrofit2.Call;

class TaxesServiceTest {

	@InjectMocks
	private TaxesService taxesService;

	@Mock
	private HGBrasilService hgBrasilService;

	@Mock
	private Call<DefaultResponse<List<Taxes>>> mockedCall;

	@BeforeEach
	public void setUp() {
		MockitoAnnotations.openMocks(this);
		taxesService = new TaxesService(hgBrasilService, "");
	}

	@Test
	void testMakeServiceCall() {
		when(hgBrasilService.getBrazilianTaxes(anyString())).thenReturn(mockedCall);

		Call<DefaultResponse<List<Taxes>>> resultCall = taxesService.makeServiceCall();

		assertEquals(mockedCall, resultCall);
	}
}
