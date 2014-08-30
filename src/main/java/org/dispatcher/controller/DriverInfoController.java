package org.dispatcher.controller;

import org.dispatcher.beans.DriverInfoBean;
import org.dispatcher.beans.DriverInfoResponse;
import org.dispatcher.beans.DriverPosResponse;
import org.dispatcher.beans.PositionBean;
import org.dispatcher.constants.FilePathConstants;
import org.dispatcher.constants.Status;
import org.dispatcher.reader.DriverReaderImpl;
import org.dispatcher.reader.ReaderFactory;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DriverInfoController {
	
	@Autowired
	ReaderFactory readerFactory;

	public void setReaderFactory(ReaderFactory readerFactory) {
		this.readerFactory = readerFactory;
	}

	@RequestMapping(value = "/drivers/info/{driverId}", method = RequestMethod.POST, consumes = "application/json", produces = "application/json")
	public DriverInfoResponse modifyDriversInfo(@PathVariable String driverId,
			@RequestBody DriverInfoBean driverInfoBean) {
		DriverReaderImpl driverReader = (DriverReaderImpl) readerFactory.getReader(this.getClass().getSimpleName());
		DriverInfoBean driverInfoBean2 = driverReader.modifyDriverInfo(driverId, driverInfoBean);
		PositionBean driverPositionBean = driverReader.modifyDriverPosition(driverInfoBean.getRouteNumber(), new PositionBean(driverInfoBean.getLat(), driverInfoBean.getLon()), driverInfoBean.isSendNotification());
		DriverInfoResponse infoResponse = new DriverInfoResponse();
		infoResponse.setStatus(Status.ERROR);
		if(driverInfoBean2!=null && driverPositionBean!=null){
			infoResponse.setStatus(Status.INFO);
			infoResponse.setDriverInfoBean(driverInfoBean2);
		}
		return infoResponse;
	}

	@RequestMapping(value = "/drivers/info/{driverId}", method = RequestMethod.GET, produces = "application/json")
	public DriverInfoResponse getDriversInfo(@PathVariable String driverId) {
		DriverReaderImpl driverReader = (DriverReaderImpl) readerFactory.getReader(this.getClass().getSimpleName());
		DriverInfoBean driverInfoBean = driverReader.getDriverInfo(driverId);
		PositionBean driverPositionBean = driverReader.getDriverPosition(driverInfoBean.getRouteNumber());
		DriverInfoResponse infoResponse = new DriverInfoResponse();
		infoResponse.setStatus(Status.ERROR);
		if(driverInfoBean!=null){
			driverInfoBean.setLat(driverPositionBean.getLat());
			driverInfoBean.setLon(driverPositionBean.getLon());
			infoResponse.setStatus(Status.INFO);
			infoResponse.setDriverInfoBean(driverInfoBean);
		}
		return infoResponse;
	}

	@RequestMapping(value = "/drivers/info", method = RequestMethod.GET, produces = "application/json")
	public JSONObject getAllDriversInfo() {
		DriverReaderImpl driverReader = (DriverReaderImpl) readerFactory.getReader(this.getClass().getSimpleName());
		return driverReader.getJsonObject(FilePathConstants.driverInfo);
	}
	
	@RequestMapping(value = "/routes/{routeId}", method = RequestMethod.POST, consumes = "application/json", produces = "application/json")
	public DriverPosResponse modifyDriversPosition(@PathVariable String routeId, @RequestBody PositionBean positionBean){
		DriverReaderImpl driverReader = (DriverReaderImpl) readerFactory.getReader(this.getClass().getSimpleName());
		PositionBean driverPositionBean = driverReader.modifyDriverPosition(routeId, positionBean, true);
		DriverPosResponse posResponse = new DriverPosResponse();
		posResponse.setStatus(Status.ERROR);
		if(driverPositionBean!=null){
			posResponse.setStatus(Status.INFO);
			posResponse.setDriverPositionBean(driverPositionBean);
		}
		return posResponse;
	}
	
	@RequestMapping(value = "/routes/{routeId}", method = RequestMethod.GET, produces = "application/json")
	public DriverPosResponse getDriversPosition(@PathVariable String routeId){
		DriverReaderImpl driverReader = (DriverReaderImpl) readerFactory.getReader(this.getClass().getSimpleName());
		PositionBean driverPositionBean = driverReader.getDriverPosition(routeId);
		DriverPosResponse posResponse = new DriverPosResponse();
		posResponse.setStatus(Status.ERROR);
		if(driverPositionBean!=null){
			posResponse.setStatus(Status.INFO);
			posResponse.setDriverPositionBean(driverPositionBean);
		}
		return posResponse;
	}
	
	@RequestMapping(value = "/routes", method = RequestMethod.GET, produces = "application/json")
	public JSONObject getAllDriversPos(){
		DriverReaderImpl driverReader = (DriverReaderImpl) readerFactory.getReader(this.getClass().getSimpleName());
		return driverReader.getJsonObject(FilePathConstants.driverPos);
	}
}
