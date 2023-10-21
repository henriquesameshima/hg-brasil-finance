package io.sameshima.hgbrasil.service;

import java.util.Map;

import io.sameshima.hgbrasil.service.api.AbstractService;
import io.sameshima.hgbrasil.service.api.HGBrasilService;
import io.sameshima.hgbrasil.service.dto.DefaultResponse;
import io.sameshima.hgbrasil.service.dto.stocks.dividends.DividendsOrError;
import retrofit2.Call;

public class StockDividendsService extends AbstractService<Map<String, DividendsOrError>> {

	public StockDividendsService(HGBrasilService service, String chaveAPI) {
		super(service, chaveAPI);
	}

	@Override
	public Call<DefaultResponse<Map<String, DividendsOrError>>> makeServiceCall(Object... params) {
		if (params.length > 0 && params[0] instanceof String) {
			String symbol = (String) params[0];
			return service.getStocksDividends(chaveAPI, symbol);
		}
		throw new IllegalArgumentException("Symbol is required for StockDividendsService.");
	}

}
