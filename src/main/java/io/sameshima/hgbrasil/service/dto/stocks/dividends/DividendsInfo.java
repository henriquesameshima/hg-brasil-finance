package io.sameshima.hgbrasil.service.dto.stocks.dividends;

import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

import io.sameshima.hgbrasil.utils.LocalDateDeserializer;
import lombok.Generated;
import lombok.Getter;
import lombok.ToString;

@Generated
public @Getter @ToString class DividendsInfo {

	@JsonProperty("kind")
	private String kind;

	@JsonProperty("currency")
	private String currency;

	@JsonProperty("isin_code")
	private String isinCode;

	@JsonProperty("label")
	private String label;

	@JsonProperty("amount")
	private Double amount;

	@JsonProperty("approved_in")
	@JsonDeserialize(using = LocalDateDeserializer.class)
	private LocalDate approvedIn;

	@JsonProperty("traded_until")
	@JsonDeserialize(using = LocalDateDeserializer.class)
	private LocalDate tradedUntil;

	@JsonProperty("payment_date")
	@JsonDeserialize(using = LocalDateDeserializer.class)
	private LocalDate paymentDate;

}
