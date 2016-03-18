package com.kyip.api.registration.external.model.response;

import java.time.LocalTime;

public class BasicResponse {
	private boolean success;

	private LocalTime localTime;

	public boolean isSuccess() {
		return success;
	}

	public void setSuccess(boolean success) {
		this.success = success;
	}

	public LocalTime getLocalTime() {
		return localTime;
	}

	public void setLocalTime(LocalTime localTime) {
		this.localTime = localTime;
	}
}
