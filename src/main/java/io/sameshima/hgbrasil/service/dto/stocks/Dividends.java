package io.sameshima.hgbrasil.service.dto.stocks;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Generated;
import lombok.Getter;
import lombok.ToString;

@Generated
public @Getter @ToString class Dividends {

	@JsonProperty("yield_12m")
	private double yield12m;

	@JsonProperty("yield_12m_sum")
	private double yield12mSum;

}
