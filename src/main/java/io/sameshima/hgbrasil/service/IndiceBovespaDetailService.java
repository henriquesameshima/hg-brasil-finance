package io.sameshima.hgbrasil.service;

import java.util.List;
import java.util.Optional;

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
		final Integer daysAgo = Optional.ofNullable(params).filter(p -> p.length > 0 && p[0] instanceof Integer)
				.map(p -> (Integer) p[0]).orElse(0);
		return service.getIbovespaDetail(chaveAPI, daysAgo);
	}

}
