package io.sameshima.hgbrasil.service.api;

import java.io.IOException;

import io.sameshima.hgbrasil.service.dto.DefaultResponse;
import io.sameshima.hgbrasil.service.exceptions.SynchronousException;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public abstract class AbstractService<T> {

	protected final HGBrasilService service;
	protected final String chaveAPI;

	protected AbstractService(HGBrasilService service, String chaveAPI) {
		this.service = service;
		this.chaveAPI = chaveAPI;
	}

	protected abstract Call<DefaultResponse<T>> makeServiceCall(Object... params);

	public DefaultResponse<T> fetchData(Object... params) throws SynchronousException {
		try {
			Response<DefaultResponse<T>> response = makeServiceCall(params).execute();
			return handleResponse(response);
		} catch (IOException e) {
			throw new SynchronousException(e.getMessage());
		}
	}

    public void fetchDataAsync(CallbackResponse<T> callback, Object... params) {
        makeServiceCall(params).enqueue(new Callback<DefaultResponse<T>>() {
            @Override
            public void onResponse(Call<DefaultResponse<T>> call, Response<DefaultResponse<T>> response) {
                if (response.isSuccessful()) {
                    callback.onSuccess(response.body());
                } else {
                    callback.onError(response.errorBody().toString());
                }
            }

            @Override
            public void onFailure(Call<DefaultResponse<T>> call, Throwable t) {
                callback.onError(t.getMessage());
            }
        });
    }

	protected DefaultResponse<T> handleResponse(Response<DefaultResponse<T>> response) throws SynchronousException {
		if (response.isSuccessful()) {
			return response.body();
		} else {
			throw new SynchronousException(response.errorBody().toString());
		}
	}
}
