package io.sameshima.hgbrasil.service.facades;

import java.util.List;
import java.util.Map;

import io.sameshima.hgbrasil.service.StockDividendsService;
import io.sameshima.hgbrasil.service.api.CallbackResponse;
import io.sameshima.hgbrasil.service.dto.DefaultResponse;
import io.sameshima.hgbrasil.service.dto.stocks.dividends.DividendsOrError;
import io.sameshima.hgbrasil.service.exceptions.SynchronousException;

public class StockDividendsFacade {

	private final StockDividendsService stockDividendsService;

	public StockDividendsFacade(StockDividendsService stockDividendsService) {
		this.stockDividendsService = stockDividendsService;
	}

	public void getDividendsAsync(final String symbol, CallbackResponse<Map<String, DividendsOrError>> callback) {
		stockDividendsService.fetchDataAsync(callback, symbol);
	}

	public void getDividendsAsync(final List<String> symbols, CallbackResponse<Map<String, DividendsOrError>> callback) {
		getDividendsAsync(String.join(",", symbols), callback);
	}

	public DefaultResponse<Map<String, DividendsOrError>> getDividends(final String symbol) throws SynchronousException {
		return stockDividendsService.fetchData(symbol);
	}

	public DefaultResponse<Map<String, DividendsOrError>> getDividends(final List<String> symbols)
			throws SynchronousException {
		return getDividends(String.join(",", symbols));
	}

}
