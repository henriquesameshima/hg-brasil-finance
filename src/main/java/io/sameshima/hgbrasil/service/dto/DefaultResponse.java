package io.sameshima.hgbrasil.service.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;
import lombok.Generated;
import lombok.ToString;

@Generated
public @Data @ToString class DefaultResponse<T> {

	@JsonProperty("by")
	private String by;

	@JsonProperty("mode")
	private String mode;

	@JsonProperty("valid_key")
	private Boolean validKey;

	@JsonProperty("execution_time")
	private Float executionTime;

	@JsonProperty("from_cache")
	private Boolean fromCache;

	@JsonProperty("results")
	private T resultados;

}
