package io.sameshima.hgbrasil.service.dto.stocks.price;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;

import io.sameshima.hgbrasil.service.dto.ResultType;

@JsonTypeInfo(
	    use = JsonTypeInfo.Id.NAME,
	    include = JsonTypeInfo.As.EXISTING_PROPERTY,
	    property = "error",
	    visible = true,
	    defaultImpl = StockInfo.class
	)
	@JsonSubTypes({
	    @JsonSubTypes.Type(value = ErrorResponse.class, name = "true")
	})
public abstract class StockOrError implements ResultType {}