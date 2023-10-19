package io.sameshima.hgbrasil.service.dto.stocks.price;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Generated;
import lombok.Getter;
import lombok.ToString;

@Generated
public @Getter @ToString class Logo {

	@JsonProperty("small")
	private String small;

	@JsonProperty("big")
	private String big;

}
