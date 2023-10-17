package io.sameshima.hgbrasil.service.facades;

import java.util.List;

import io.sameshima.hgbrasil.service.TaxesService;
import io.sameshima.hgbrasil.service.api.CallbackResponse;
import io.sameshima.hgbrasil.service.dto.DefaultResponse;
import io.sameshima.hgbrasil.service.dto.taxes.Taxes;
import io.sameshima.hgbrasil.service.exceptions.SynchronousException;

/**
 * The Class TaxesFacade.
 */
public class TaxesFacade {

	/** The taxes service. */
	private final TaxesService taxesService;

	/**
	 * Instantiates a new taxes facade.
	 *
	 * @param taxesService the taxes service
	 */
	public TaxesFacade(TaxesService taxesService) {
		this.taxesService = taxesService;
	}

	/**
	 * Gets the brazilian taxes async.
	 *
	 * @param callback the callback
	 * @return the brazilian taxes async
	 */
	public void getBrazilianTaxesAsync(final CallbackResponse<List<Taxes>> callback) {
		taxesService.fetchDataAsync(callback);
	}

	/**
	 * Gets the brazilian taxes.
	 *
	 * @return the brazilian taxes
	 * @throws SynchronousException the synchronous exception
	 */
	public DefaultResponse<List<Taxes>> getBrazilianTaxes() throws SynchronousException {
		return taxesService.fetchData();
	}
}
