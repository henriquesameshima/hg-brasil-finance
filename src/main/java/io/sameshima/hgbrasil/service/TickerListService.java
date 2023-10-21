package io.sameshima.hgbrasil.service;

import java.util.List;

import io.sameshima.hgbrasil.service.api.AbstractService;
import io.sameshima.hgbrasil.service.api.HGBrasilService;
import io.sameshima.hgbrasil.service.dto.DefaultResponse;
import retrofit2.Call;

public class TickerListService extends AbstractService<List<String>> {

	public TickerListService(HGBrasilService service, String chaveAPI) {
		super(service, chaveAPI);
	}

	@Override
	public Call<DefaultResponse<List<String>>> makeServiceCall(Object... params) {
		return service.getAllTickers(chaveAPI);
	}

}
