package io.sameshima.hgbrasil.service.dto.ibovespa;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

import io.sameshima.hgbrasil.utils.LocalDateTimeDeserializer;
import lombok.Generated;
import lombok.Getter;
import lombok.ToString;

@Generated
public @Getter @ToString class DataPoint {

	@JsonProperty("points")
	private Double points;

	@JsonProperty("change")
	private Double change;

	@JsonProperty("date")
	@JsonDeserialize(using = LocalDateTimeDeserializer.class)
	private LocalDateTime date;

}
