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
	
	public void getIbovespaDetailAsync(final Integer daysAgo, final CallbackResponse<List<IbovespaInfo>> callback) {
		indiceBovespaDetailService.fetchDataAsync(callback, daysAgo);
	}
	
	public void getIbovespaDetailAsync(final CallbackResponse<List<IbovespaInfo>> callback) {
		getIbovespaDetailAsync(0, callback);
	}

}
