package org.dispatcher.beans;

import java.util.ArrayList;

import org.dispatcher.constants.Status;

public class DriverPosResponse {

	Status status;
	PositionBean driverPositionBean;
	ArrayList<PositionBean> positionBeans;
	
	public Status getStatus() {
		return status;
	}
	public void setStatus(Status status) {
		this.status = status;
	}
	public PositionBean getDriverPositionBean() {
		return driverPositionBean;
	}
	public void setDriverPositionBean(PositionBean driverPositionBean) {
		this.driverPositionBean = driverPositionBean;
	}
	public ArrayList<PositionBean> getPositionBeans() {
		return positionBeans;
	}
	public void setPositionBeans(ArrayList<PositionBean> positionBeans) {
		this.positionBeans = positionBeans;
	}
}
