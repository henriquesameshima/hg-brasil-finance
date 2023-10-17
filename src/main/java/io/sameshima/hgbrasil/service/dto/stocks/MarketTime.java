package io.sameshima.hgbrasil.service.dto.stocks;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Generated;
import lombok.Getter;
import lombok.ToString;

@Generated
public @Getter @ToString class MarketTime {

	@JsonProperty("open")
	private String open;

	@JsonProperty("close")
	private String close;

	@JsonProperty("timezone")
	private int timezone;

}
