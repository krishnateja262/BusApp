package org.dispatcher.beans;

import org.dispatcher.constants.Status;

public class DriverInfoResponse {

	Status status;
	DriverInfoBean driverInfoBean;
	int passengerCount;
	
	public Status getStatus() {
		return status;
	}
	public void setStatus(Status status) {
		this.status = status;
	}
	public DriverInfoBean getDriverInfoBean() {
		return driverInfoBean;
	}
	public void setDriverInfoBean(DriverInfoBean driverInfoBean) {
		this.driverInfoBean = driverInfoBean;
	}
	public int getPassengerCount() {
		return passengerCount;
	}
	public void setPassengerCount(int passengerCount) {
		this.passengerCount = passengerCount;
	}
}
