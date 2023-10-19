package io.sameshima.hgbrasil.service.dto.stocks.dividends;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;

import io.sameshima.hgbrasil.service.dto.ResultType;

@JsonTypeInfo(
	    use = JsonTypeInfo.Id.NAME,
	    include = JsonTypeInfo.As.EXISTING_PROPERTY,
	    property = "error",
	    visible = true,
	    defaultImpl = DividendsResult.class
	)
	@JsonSubTypes({
	    @JsonSubTypes.Type(value = ErrorResponse.class, name = "true")
	})
public abstract class DividendsOrError implements ResultType {}