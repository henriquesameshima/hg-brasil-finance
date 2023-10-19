package io.sameshima.hgbrasil.service.dto.stocks.dividends;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Generated;
import lombok.Getter;
import lombok.ToString;

@Generated
public @Getter @ToString class ErrorResponse extends DividendsOrError {

	@JsonProperty("error")
	private Boolean error;
	
	@JsonProperty("message")
	private String message;

}
