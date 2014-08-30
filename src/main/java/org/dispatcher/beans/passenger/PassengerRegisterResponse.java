package org.dispatcher.beans.passenger;

import org.dispatcher.constants.Status;

public class PassengerRegisterResponse {

	Status status;
	String url;

	public Status getStatus() {
		return status;
	}

	public void setStatus(Status status) {
		this.status = status;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}
}
