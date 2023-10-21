package io.sameshima.hgbrasil.service;

import java.util.List;

import io.sameshima.hgbrasil.service.api.AbstractService;
import io.sameshima.hgbrasil.service.api.HGBrasilService;
import io.sameshima.hgbrasil.service.dto.DefaultResponse;
import io.sameshima.hgbrasil.service.dto.ibovespa.IbovespaInfo;
import retrofit2.Call;

public class IndiceBovespaDetailService extends AbstractService<List<IbovespaInfo>> {

	public IndiceBovespaDetailService(HGBrasilService service, String chaveAPI) {
		super(service, chaveAPI);
	}

	@Override
	public Call<DefaultResponse<List<IbovespaInfo>>> makeServiceCall(Object... params) {
		if (params.length == 1 && params[0] instanceof String) {
			String date = (String) params[0];
			return service.getIbovespaDetail(chaveAPI, date);
		} else if (params.length == 2 && params[0] instanceof String && params[1] instanceof String) {
			String startDate = (String) params[0];
			String endDate = (String) params[1];
			return service.getIbovespaDetail(chaveAPI, startDate, endDate);
		} else if (params.length == 1 && params[0] instanceof Integer) {
			Integer days = (Integer) params[0];
			return service.getIbovespaDetail(chaveAPI, days);
		}
		throw new IllegalArgumentException("Invalid parameters.");
	}

}
