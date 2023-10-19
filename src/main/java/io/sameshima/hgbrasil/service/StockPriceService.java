package io.sameshima.hgbrasil.service;

import java.util.Map;

import io.sameshima.hgbrasil.service.api.AbstractService;
import io.sameshima.hgbrasil.service.api.HGBrasilService;
import io.sameshima.hgbrasil.service.dto.DefaultResponse;
import io.sameshima.hgbrasil.service.dto.stocks.price.StockOrError;
import retrofit2.Call;

public class StockPriceService extends AbstractService<Map<String, StockOrError>> {

	public StockPriceService(HGBrasilService service, String chaveAPI) {
		super(service, chaveAPI);
	}

	@Override
	protected Call<DefaultResponse<Map<String, StockOrError>>> makeServiceCall(Object... params) {
		if (params.length > 0 && params[0] instanceof String) {
			String symbol = (String) params[0];
			return service.getStocksPrice(chaveAPI, symbol);
		}
		throw new IllegalArgumentException("Symbol is required for StockPriceService.");
	}

}
