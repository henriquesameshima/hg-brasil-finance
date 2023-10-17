package io.sameshima.hgbrasil.service.facades;

import java.util.List;

import io.sameshima.hgbrasil.service.TickerListService;
import io.sameshima.hgbrasil.service.api.CallbackResponse;
import io.sameshima.hgbrasil.service.dto.DefaultResponse;
import io.sameshima.hgbrasil.service.exceptions.SynchronousException;

/**
 * The Class TickersFacade.
 */
public class TickersFacade {

	/** The ticker list service. */
	private final TickerListService tickerListService;

	/**
	 * Instantiates a new tickers facade.
	 *
	 * @param tickerListService the ticker list service
	 */
	public TickersFacade(TickerListService tickerListService) {
		this.tickerListService = tickerListService;
	}

	/**
	 * Gets the all tickers async.
	 *
	 * @param callback the callback
	 * @return the all tickers async
	 */
	public void getAllTickersAsync(final CallbackResponse<List<String>> callback) {
		tickerListService.fetchDataAsync(callback);
	}

	/**
	 * Gets the all tickers.
	 *
	 * @return the all tickers
	 * @throws SynchronousException the synchronous exception
	 */
	public DefaultResponse<List<String>> getAllTickers() throws SynchronousException {
		return tickerListService.fetchData();
	}
}
