package io.sameshima.hgbrasil.service.dto.stocks.price;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Generated;
import lombok.Getter;
import lombok.ToString;

@Generated
public @Getter @ToString class StockInfo extends StockOrError {

	@JsonProperty("kind")
	private String kind;

	@JsonProperty("symbol")
	private String symbol;

	@JsonProperty("name")
	private String name;

	@JsonProperty("company_name")
	private String companyName;

	@JsonProperty("document")
	private String document;

	@JsonProperty("description")
	private String description;

	@JsonProperty("website")
	private String website;

	@JsonProperty("sector")
	private String sector;

	@JsonProperty("financials")
	private Financials financials;

	@JsonProperty("region")
	private String region;

	@JsonProperty("currency")
	private String currency;

	@JsonProperty("market_time")
	private MarketTime marketTime;

	@JsonProperty("logo")
	private Logo logo;

	@JsonProperty("market_cap")
	private double marketCap;

	@JsonProperty("price")
	private double price;

	@JsonProperty("change_percent")
	private double changePercent;

	@JsonProperty("change_price")
	private double changePrice;

	@JsonProperty("updated_at")
	private String updatedAt;
	
}