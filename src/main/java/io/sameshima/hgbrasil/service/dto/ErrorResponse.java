package io.sameshima.hgbrasil.service.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Generated;
import lombok.Getter;
import lombok.ToString;

@Generated
public @Getter @ToString class ErrorResponse {

	@JsonProperty("error")
	private Boolean error;

	@JsonProperty("message")
	private String message;

}
