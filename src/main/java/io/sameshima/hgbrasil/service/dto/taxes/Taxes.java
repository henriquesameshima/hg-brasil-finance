package io.sameshima.hgbrasil.service.dto.taxes;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Generated;
import lombok.Getter;
import lombok.ToString;

@Generated
public @Getter @ToString class Taxes {

	@JsonProperty("date")
	private String date;

	@JsonProperty("cdi")
	private Float cdi;

	@JsonProperty("selic")
	private Float selic;

	@JsonProperty("daily_factor")
	private Float dailyFactor;

	@JsonProperty("selic_daily")
	private Float selicDaily;

	@JsonProperty("cdi_daily")
	private Float cdiDaily;

}
