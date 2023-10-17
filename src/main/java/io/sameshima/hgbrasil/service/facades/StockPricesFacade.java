package io.sameshima.hgbrasil.service.facades;

import java.util.List;
import java.util.Map;

import io.sameshima.hgbrasil.service.StockPriceService;
import io.sameshima.hgbrasil.service.api.CallbackResponse;
import io.sameshima.hgbrasil.service.dto.DefaultResponse;
import io.sameshima.hgbrasil.service.dto.stocks.StockInfo;
import io.sameshima.hgbrasil.service.exceptions.SynchronousException;

/**
 * The Class StockPricesFacade.
 */
public class StockPricesFacade {

	/** The stock price service. */
	private final StockPriceService stockPriceService;

	/**
	 * Instantiates a new stock prices facade.
	 *
	 * @param stockPriceService the stock price service
	 */
	public StockPricesFacade(StockPriceService stockPriceService) {
		this.stockPriceService = stockPriceService;
	}

	/**
	 * Gets the high stock prices async.
	 *
	 * @param callback the callback
	 * @return the high stock prices async
	 */
	public void getHighStockPricesAsync(CallbackResponse<Map<String, StockInfo>> callback) {
		stockPriceService.fetchDataAsync(callback, "get-high");
	}

	/**
	 * Gets the low stock prices async.
	 *
	 * @param callback the callback
	 * @return the low stock prices async
	 */
	public void getLowStockPricesAsync(CallbackResponse<Map<String, StockInfo>> callback) {
		stockPriceService.fetchDataAsync(callback, "get-low");
	}

	/**
	 * Gets the stock prices async.
	 *
	 * @param symbol the symbol
	 * @param callback the callback
	 * @return the stock prices async
	 */
	public void getStockPricesAsync(final String symbol, CallbackResponse<Map<String, StockInfo>> callback) {
		stockPriceService.fetchDataAsync(callback, symbol);
	}

	/**
	 * Gets the stock prices async.
	 *
	 * @param symbols the symbols
	 * @param callback the callback
	 * @return the stock prices async
	 */
	public void getStockPricesAsync(final List<String> symbols, CallbackResponse<Map<String, StockInfo>> callback) {
		getStockPricesAsync(String.join(",", symbols), callback);
	}

	/**
	 * Gets the high stock prices.
	 *
	 * @return the high stock prices
	 * @throws SynchronousException the synchronous exception
	 */
	public DefaultResponse<Map<String, StockInfo>> getHighStockPrices() throws SynchronousException {
		return stockPriceService.fetchData("get-high");
	}

	/**
	 * Gets the low stock prices.
	 *
	 * @return the low stock prices
	 * @throws SynchronousException the synchronous exception
	 */
	public DefaultResponse<Map<String, StockInfo>> getLowStockPrices() throws SynchronousException {
		return stockPriceService.fetchData("get-low");
	}

	/**
	 * Gets the stock prices.
	 *
	 * @param symbol the symbol
	 * @return the stock prices
	 * @throws SynchronousException the synchronous exception
	 */
	public DefaultResponse<Map<String, StockInfo>> getStockPrices(final String symbol) throws SynchronousException {
		return stockPriceService.fetchData(symbol);
	}

	/**
	 * Gets the stock prices.
	 *
	 * @param symbols the symbols
	 * @return the stock prices
	 * @throws SynchronousException the synchronous exception
	 */
	public DefaultResponse<Map<String, StockInfo>> getStockPrices(final List<String> symbols)
			throws SynchronousException {
		return getStockPrices(String.join(",", symbols));
	}

}
