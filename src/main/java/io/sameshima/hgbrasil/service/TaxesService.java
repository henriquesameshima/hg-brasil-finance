package io.sameshima.hgbrasil.service;

import java.util.List;

import io.sameshima.hgbrasil.service.api.AbstractService;
import io.sameshima.hgbrasil.service.api.HGBrasilService;
import io.sameshima.hgbrasil.service.dto.DefaultResponse;
import io.sameshima.hgbrasil.service.dto.taxes.Taxes;
import retrofit2.Call;

public final class TaxesService extends AbstractService<List<Taxes>> {

	public TaxesService(HGBrasilService service, String chaveAPI) {
		super(service, chaveAPI);
	}

	@Override
	protected Call<DefaultResponse<List<Taxes>>> makeServiceCall(Object... params) {
		return service.getBrazilianTaxes(chaveAPI);
	}

}
