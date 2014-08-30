package org.dispatcher.reader;

import org.dispatcher.beans.DriverInfoBean;
import org.dispatcher.beans.PositionBean;
import org.dispatcher.constants.FilePathConstants;
import org.json.simple.JSONObject;
import org.springframework.stereotype.Service;

@Service
public class DriverReaderImpl extends AbstractDriverReader implements Reader{

	@Override
	public DriverInfoBean getDriverInfo(String driverId) {

		JSONObject jsonObject = super
				.getJsonObject(FilePathConstants.driverInfo);
		JSONObject driverObject = (JSONObject) jsonObject.get(driverId);
		DriverInfoBean driverInfoBean;
		if (driverObject != null) {
			driverInfoBean = new DriverInfoBean();
			driverInfoBean.setGcmId((String) driverObject.get("gcmid"));
			driverInfoBean.setRouteNumber((String) driverObject.get("routeno"));
			driverInfoBean.setSendNotification((Boolean) driverObject
					.get("sendNotification"));
		} else {
			driverInfoBean = null;
		}
		return driverInfoBean;
	}

	@SuppressWarnings("unchecked")
	@Override
	public DriverInfoBean modifyDriverInfo(String driverId,
			DriverInfoBean driverInfoBean) {

		JSONObject jsonObject = super
				.getJsonObject(FilePathConstants.driverInfo);
		JSONObject driverObject = (JSONObject) jsonObject.get(driverId);
		if (driverObject != null) {
			JSONObject newDriverObject = new JSONObject();

			newDriverObject.put("routeno", driverInfoBean.getRouteNumber());
			newDriverObject.put("gcmid", driverInfoBean.getGcmId());
			newDriverObject.put("sendNotification",
					driverInfoBean.isSendNotification());

			jsonObject.remove(driverId);
			jsonObject.put(driverId, newDriverObject);
			if (!super.modifyJsonFile(FilePathConstants.driverInfo, jsonObject))
				return null;
			else
				return driverInfoBean;
		}else
			return null;
	}

	@SuppressWarnings("unchecked")
	@Override
	public PositionBean modifyDriverPosition(String routeId,
			PositionBean driverPositionBean, boolean sendNotification) {
		JSONObject jsonObject = super
				.getJsonObject(FilePathConstants.driverPos);
		JSONObject driverObject = (JSONObject) jsonObject.get(routeId);
		JSONObject newDriverPos = new JSONObject();
		newDriverPos.put("lat", driverPositionBean.getLat());
		newDriverPos.put("lon", driverPositionBean.getLon());
		newDriverPos.put("sendNotification", sendNotification);
		if (driverObject != null) {
			jsonObject.remove(routeId);
			jsonObject.put(routeId, newDriverPos);
			if (!super.modifyJsonFile(FilePathConstants.driverPos, jsonObject))
				return null;
			else
				return driverPositionBean;
		}else{
			return null;
		}
	}

	@Override
	public PositionBean getDriverPosition(String routeId) {
		JSONObject jsonObject = super
				.getJsonObject(FilePathConstants.driverPos);
		JSONObject driverObject = (JSONObject) jsonObject.get(routeId);
		PositionBean driverPositionBean;
		if(driverObject!=null){
			driverPositionBean = new PositionBean();
			driverPositionBean.setLat((String) driverObject.get("lat"));
			driverPositionBean.setLon((String) driverObject.get("lon"));
			driverPositionBean.setSendNotification((Boolean) driverObject.get("sendNotification"));
		}else{
			driverPositionBean = null;
		}
		return driverPositionBean;
	}

}
