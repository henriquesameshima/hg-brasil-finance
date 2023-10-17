package io.sameshima.hgbrasil.service.exceptions;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Generated;

@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
@Generated
public @Data class SynchronousException extends Exception {

	private static final long serialVersionUID = -2960092820597324761L;
	
	private final String message;

}
