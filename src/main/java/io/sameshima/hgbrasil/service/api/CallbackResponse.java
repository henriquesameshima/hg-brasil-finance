package io.sameshima.hgbrasil.service.api;

import io.sameshima.hgbrasil.service.dto.DefaultResponse;

public interface CallbackResponse<T> {

	void onSuccess(DefaultResponse<T> response);
	
	void onError(final String message);
	
}
