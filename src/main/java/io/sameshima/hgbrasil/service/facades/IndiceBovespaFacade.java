package io.sameshima.hgbrasil.service.facades;

import java.util.List;

import io.sameshima.hgbrasil.service.IndiceBovespaDetailService;
import io.sameshima.hgbrasil.service.api.CallbackResponse;
import io.sameshima.hgbrasil.service.dto.DefaultResponse;
import io.sameshima.hgbrasil.service.dto.ibovespa.IbovespaInfo;
import io.sameshima.hgbrasil.service.exceptions.SynchronousException;

public class IndiceBovespaFacade {

	private final IndiceBovespaDetailService indiceBovespaDetailService;

	public IndiceBovespaFacade(IndiceBovespaDetailService indiceBovespaDetailService) {
		this.indiceBovespaDetailService = indiceBovespaDetailService;
	}

	public DefaultResponse<List<IbovespaInfo>> getIbovespaDetail() throws SynchronousException {
		return getIbovespaDetail(0);
	}

	public DefaultResponse<List<IbovespaInfo>> getIbovespaDetail(final Integer daysAgo) throws SynchronousException {
		return indiceBovespaDetailService.fetchData(daysAgo);
	}

	public DefaultResponse<List<IbovespaInfo>> getIbovespaDetail(final String startDate, final String endDate) throws SynchronousException {
		return indiceBovespaDetailService.fetchData(startDate, endDate);
	}

	public DefaultResponse<List<IbovespaInfo>> getIbovespaDetail(final String date) throws SynchronousException {
		return indiceBovespaDetailService.fetchData(date);
	}

	public void getIbovespaDetailAsync(final Integer daysAgo, final CallbackResponse<List<IbovespaInfo>> callback) {
		indiceBovespaDetailService.fetchDataAsync(callback, daysAgo);
	}

	public void getIbovespaDetailAsync(final CallbackResponse<List<IbovespaInfo>> callback) {
		getIbovespaDetailAsync(0, callback);
	}

	public void getIbovespaDetailAsync(final String startDate, final String endDate, final CallbackResponse<List<IbovespaInfo>> callback) {
		indiceBovespaDetailService.fetchDataAsync(callback, startDate, endDate);
	}

	public void getIbovespaDetailAsync(final String date, final CallbackResponse<List<IbovespaInfo>> callback) {
		indiceBovespaDetailService.fetchDataAsync(callback, date);
	}

}
