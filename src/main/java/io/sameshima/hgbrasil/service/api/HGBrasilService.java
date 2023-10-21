package io.sameshima.hgbrasil.service.api;

import java.util.List;
import java.util.Map;

import io.sameshima.hgbrasil.service.dto.DefaultResponse;
import io.sameshima.hgbrasil.service.dto.ibovespa.IbovespaInfo;
import io.sameshima.hgbrasil.service.dto.stocks.dividends.DividendsOrError;
import io.sameshima.hgbrasil.service.dto.stocks.price.StockOrError;
import io.sameshima.hgbrasil.service.dto.taxes.Taxes;
import lombok.Generated;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

@Generated
public interface HGBrasilService {

	@GET("taxes")
	Call<DefaultResponse<List<Taxes>>> getBrazilianTaxes(@Query("key") final String key);
	
	@GET("stock_price")
	Call<DefaultResponse<Map<String, StockOrError>>> getStocksPrice(@Query("key") final String key, @Query("symbol") final String symbol);
	
	@GET("stock_dividends")
	Call<DefaultResponse<Map<String, DividendsOrError>>> getStocksDividends(@Query("key") final String key, @Query("symbol") final String symbol);
	
	@GET("ticker_list")
	Call<DefaultResponse<List<String>>> getAllTickers(@Query("key") final String key);
	
	@GET("ibovespa")
	Call<DefaultResponse<List<IbovespaInfo>>> getIbovespaDetail(@Query("key") final String key, @Query("days_ago") final Integer daysAgo);
	
}
