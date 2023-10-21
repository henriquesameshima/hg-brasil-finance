package io.sameshima.hgbrasil.service.dto.ibovespa;

import java.time.LocalDate;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

import io.sameshima.hgbrasil.utils.LocalDateDeserializer;
import lombok.Generated;
import lombok.Getter;
import lombok.ToString;

@Generated
public @Getter @ToString class IbovespaInfo {

	@JsonProperty("date")
	@JsonDeserialize(using = LocalDateDeserializer.class)
	private LocalDate date;

	@JsonProperty("close")
	private Integer close;

	@JsonProperty("high")
	private Integer high;

	@JsonProperty("low")
	private Integer low;

	@JsonProperty("last")
	private Integer last;

	@JsonProperty("volume")
	private Long volume;

	@JsonProperty("change_percent")
	private Double changePercent;

	@JsonProperty("data")
	private List<DataPoint> data;

}
