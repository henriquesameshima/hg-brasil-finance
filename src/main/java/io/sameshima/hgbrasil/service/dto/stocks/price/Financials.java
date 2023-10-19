package io.sameshima.hgbrasil.service.dto.stocks.price;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Generated;
import lombok.Getter;
import lombok.ToString;

@Generated
public @Getter @ToString class Financials {

	@JsonProperty("quota_count")
	private long quotaCount;

	@JsonProperty("dividends")
	private Dividends dividends;

}
