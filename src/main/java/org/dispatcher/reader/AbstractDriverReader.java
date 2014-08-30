package org.dispatcher.reader;

import org.dispatcher.beans.DriverInfoBean;
import org.dispatcher.beans.PositionBean;

public abstract class AbstractDriverReader extends ReaderInterfaceImpl{

	public abstract DriverInfoBean getDriverInfo(String driverId);
	
	public abstract DriverInfoBean modifyDriverInfo(String driverId, DriverInfoBean driverInfoBean);
	
	public abstract PositionBean modifyDriverPosition(String driverId, PositionBean driverPositionBean, boolean sendNotification);
	
	public abstract PositionBean getDriverPosition(String driverId);
}
