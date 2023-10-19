package io.sameshima.hgbrasil;

import io.sameshima.hgbrasil.service.StockDividendsService;
import io.sameshima.hgbrasil.service.StockPriceService;
import io.sameshima.hgbrasil.service.TaxesService;
import io.sameshima.hgbrasil.service.TickerListService;
import io.sameshima.hgbrasil.service.api.CacheConfig;
import io.sameshima.hgbrasil.service.api.HGBrasilService;
import io.sameshima.hgbrasil.service.api.RetrofitClient;
import io.sameshima.hgbrasil.service.facades.StockDividendsFacade;
import io.sameshima.hgbrasil.service.facades.StockPricesFacade;
import io.sameshima.hgbrasil.service.facades.TaxesFacade;
import io.sameshima.hgbrasil.service.facades.TickersFacade;

/**
 * Class for usage HG Brasil API - Finance.
 * 
 * @version 0.0.1
 * @author Henrique Sameshima (sameshima@live.com)
 */
public final class HGFinance {

	private final TickersFacade tickersFacade;
	private final TaxesFacade taxesFacade;
	private final StockPricesFacade stockPricesFacade;
	private final StockDividendsFacade stockDividendsFacade;

	/**
	 * Instantiates a new HG finance.
	 *
	 * @param chaveAPI the chave API
	 */
	public HGFinance(final String chaveAPI) {
		this(chaveAPI, null);
	}

	/**
	 * Instantiates a new HG finance.
	 *
	 * @param chaveAPI    the chave API
	 * @param cacheConfig the cache config
	 */
	public HGFinance(final String chaveAPI, final CacheConfig cacheConfig) {
		final HGBrasilService service = (cacheConfig != null) ? RetrofitClient.getApiService(cacheConfig)
				: RetrofitClient.getApiService();
		this.tickersFacade = new TickersFacade(new TickerListService(service, chaveAPI));
		this.taxesFacade = new TaxesFacade(new TaxesService(service, chaveAPI));
		this.stockPricesFacade = new StockPricesFacade(new StockPriceService(service, chaveAPI));
		this.stockDividendsFacade = new StockDividendsFacade(new StockDividendsService(service, chaveAPI));
	}

	/**
	 * Gets the tickers services.
	 *
	 * @return the tickers services
	 */
	public TickersFacade getTickersServices() {
		return tickersFacade;
	}

	/**
	 * Gets the taxes services.
	 *
	 * @return the taxes services
	 */
	public TaxesFacade getTaxesServices() {
		return taxesFacade;
	}

	/**
	 * Gets the stock prices services.
	 *
	 * @return the stock prices services
	 */
	public StockPricesFacade getStockPricesServices() {
		return stockPricesFacade;
	}

	/**
	 * Gets the stock dividends services.
	 *
	 * @return the stock dividends services
	 */
	public StockDividendsFacade getStockDividendsServices() {
		return stockDividendsFacade;
	}
}
