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

import io.sameshima.hgbrasil.service.TaxesService;
import io.sameshima.hgbrasil.service.dto.DefaultResponse;
import io.sameshima.hgbrasil.service.dto.taxes.Taxes;
import io.sameshima.hgbrasil.service.exceptions.SynchronousException;
import io.sameshima.hgbrasil.service.facades.TaxesFacade;

class TaxesFacadeTest {

	@InjectMocks
	private TaxesFacade taxesFacade;

	@Mock
	private TaxesService taxesService;

	@BeforeEach
	public void setUp() {
		MockitoAnnotations.openMocks(this);
	}

	@Test
	void testGetBrazilianTaxes() throws SynchronousException {
		DefaultResponse<List<Taxes>> expectedResponse = new DefaultResponse<>();
		when(taxesService.fetchData()).thenReturn(expectedResponse);
		
		DefaultResponse<List<Taxes>> response = taxesFacade.getBrazilianTaxes();
		assertEquals(expectedResponse, response);
	}
	
	@Test
	void testGetBrazilianTaxesAsync() throws SynchronousException {
		taxesFacade.getBrazilianTaxesAsync(null);
		verify(taxesService, times(1)).fetchDataAsync(null);
	}
	
	@Test
	void testGetBrazilianTaxesWithException() throws SynchronousException {
		SynchronousException expectedException = new SynchronousException("Error");

		doThrow(expectedException).when(taxesService).fetchData();

		SynchronousException exception = assertThrows(SynchronousException.class, () -> taxesFacade.getBrazilianTaxes());

		assertEquals(expectedException, exception);
	}
}