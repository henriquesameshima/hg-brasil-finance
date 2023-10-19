package io.sameshima.hgbrasil.service.dto.stocks.dividends;

import java.util.List;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

import io.sameshima.hgbrasil.utils.DividendsResultDeserializer;
import lombok.Generated;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Generated
@JsonDeserialize(using = DividendsResultDeserializer.class)
public @ToString class DividendsResult extends DividendsOrError {

	@Getter	@Setter
	private List<DividendsInfo> dividendsList;

}
