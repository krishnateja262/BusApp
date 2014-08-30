package org.dispatcher.reader;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

import org.dispatcher.beans.passenger.PassengerRegisterRequest;
import org.dispatcher.beans.passenger.PassengerRegisterResponse;
import org.dispatcher.beans.passenger.PassengerRouteResponse;
import org.dispatcher.beans.passenger.StartEndTimeBean;
import org.dispatcher.constants.FilePathConstants;
import org.dispatcher.constants.Status;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.springframework.stereotype.Service;

@Service
public class PassengerReader extends AbstractPassengerReader implements Reader {

	@Override
	public PassengerRouteResponse getBusNumber(String startingUID,
			String endingUID, String session) {
		HashMap<String, String> startingPoints = new HashMap<String, String>();
		HashMap<String, String> endingPoints = new HashMap<String, String>();
		HashMap<String, StartEndTimeBean> routeResponse = new HashMap<String, StartEndTimeBean>();
		String s1 = "No Buses for given routes";
		JSONObject jsonObject = super
				.getJsonObject(FilePathConstants.routeList);
		try {
			JSONObject startingPointObject = (JSONObject) jsonObject
					.get(startingUID);
			JSONObject endingPointObject = (JSONObject) jsonObject
					.get(endingUID);
			JSONArray startingSessionArray = (JSONArray) startingPointObject
					.get(session);
			JSONArray endingSessionArray = (JSONArray) endingPointObject
					.get(session);
			for (int i = 0; i < startingSessionArray.size(); i++) {
				JSONObject object = (JSONObject) startingSessionArray.get(i);
				startingPoints.put((String) object.get("busnumber"),
						(String) object.get("time"));
			}
			for (int i = 0; i < endingSessionArray.size(); i++) {
				JSONObject object = (JSONObject) endingSessionArray.get(i);
				endingPoints.put((String) object.get("busnumber"),
						(String) object.get("time"));
			}
			Set<String> commonKeys = new HashSet<String>(
					startingPoints.keySet());
			commonKeys.retainAll(endingPoints.keySet());
			for (String s : commonKeys) {
				StartEndTimeBean timeBean = new StartEndTimeBean();
				timeBean.setRouteUrl("/routes/" + s);
				timeBean.setStartingPointTime(startingPoints.get(s));
				timeBean.setEndingPointTime(endingPoints.get(s));
				routeResponse.put(s, timeBean);
			}
		} catch (NullPointerException e) {
			s1 = "check your stop UID's";
		}
		PassengerRouteResponse passengerRouteResponse = new PassengerRouteResponse(
				routeResponse);
		if (routeResponse.size() > 0)
			passengerRouteResponse.setStatus("Success");
		else
			passengerRouteResponse.setStatus(s1);
		return passengerRouteResponse;
	}

	@SuppressWarnings("unchecked")
	@Override
	public PassengerRegisterResponse registerPassenger(String passengerId,
			PassengerRegisterRequest passengerRegisterRequest) {
		JSONObject jsonObject = super
				.getJsonObject(FilePathConstants.passengerInfo);
		JSONObject passengerObject = (JSONObject) jsonObject.get(passengerId);
		PassengerRegisterResponse passengerRegisterResponse = new PassengerRegisterResponse();
		passengerRegisterResponse.setStatus(Status.ERROR);
		if (passengerObject != null) {
			JSONObject newPassengerObject = new JSONObject();
			newPassengerObject.put("routeNumber",
					passengerRegisterRequest.getRouteNumber());
			newPassengerObject
					.put("gcmId", passengerRegisterRequest.getGcmId());
			newPassengerObject.put("lat", passengerRegisterRequest.getLat());
			newPassengerObject.put("lon", passengerRegisterRequest.getLon());
			newPassengerObject.put("timeInterval",
					passengerRegisterRequest.getNewTimeInterval());
			jsonObject.remove(passengerId);
			jsonObject.put(passengerId, newPassengerObject);
			if (!super.modifyJsonFile(FilePathConstants.passengerInfo,
					jsonObject))
				return passengerRegisterResponse;
			else {
				boolean status = false;
				boolean stopWrite = true;
				if (passengerRegisterRequest.getNewTimeInterval() <= 10
						&& passengerRegisterRequest.getNewTimeInterval() >= 1)
					status = writeToTime(passengerId, passengerRegisterRequest);
				//stopWrite = writeToStop(passengerId, passengerRegisterRequest);
				if (status && stopWrite) {
					passengerRegisterResponse.setStatus(Status.INFO);
					passengerRegisterResponse.setUrl("/routes/"
							+ passengerRegisterRequest.getRouteNumber());
					return passengerRegisterResponse;
				} else
					return passengerRegisterResponse;
			}
		} else
			return passengerRegisterResponse;
	}

	@SuppressWarnings("unchecked")
	private boolean writeToStop(String passengerId,
			PassengerRegisterRequest passengerRegisterRequest) {
		boolean stat = false;
		boolean stat1 = false;
		if (passengerRegisterRequest.getOldStartingPoint() != null
				&& !passengerRegisterRequest.getOldStartingPoint().isEmpty()) {
			JSONObject oldObject = super
					.getJsonObject(FilePathConstants.basePath
							+ "passengerCount/"
							+ passengerRegisterRequest.getOldStartingPoint()
							+ ".json");
			Long count = (Long) oldObject.get("passengerCount");
			if(count>0)
				count = count - 1;
			oldObject.remove("passengerCount");
			oldObject.put("passengerCount", count);
			if (!super.modifyJsonFile(FilePathConstants.basePath
					+ "passengerCount/"
					+ passengerRegisterRequest.getOldStartingPoint()
					+ ".json", oldObject))
				stat = false;
			else
				stat = true;
		}
		if(passengerRegisterRequest.getNewStartingPoint() != null && !passengerRegisterRequest.getNewStartingPoint().isEmpty()){
			JSONObject newObject = super.getJsonObject(FilePathConstants.basePath
							+ "passengerCount/"
							+ passengerRegisterRequest.getNewStartingPoint()
							+ ".json");
			Long count = (Long) newObject.get("passengerCount");
			count = count+1;
			newObject.remove("passengerCount");
			newObject.put("passengerCount", count);
			if (!super.modifyJsonFile(FilePathConstants.basePath
					+ "passengerCount/"
					+ passengerRegisterRequest.getNewStartingPoint()
					+ ".json", newObject))
				stat1 = false;
			else
				stat1 = true;
		}

		return stat&stat1;
	}

	@SuppressWarnings("unchecked")
	public boolean writeToTime(String passengerId,
			PassengerRegisterRequest passengerRegisterRequest) {
		if (passengerRegisterRequest.getOldTimeInterval() <= 10
				&& passengerRegisterRequest.getOldTimeInterval() >= 1) {
			JSONObject oldObject = super
					.getJsonObject(FilePathConstants.basePath + "times/"
							+ passengerRegisterRequest.getOldTimeInterval()
							+ ".json");
			if (oldObject.get(passengerId) != null) {
				oldObject.remove(passengerId);
				super.modifyJsonFile(FilePathConstants.basePath + "times/"
						+ passengerRegisterRequest.getOldTimeInterval()
						+ ".json", oldObject);
			}
		}
		JSONObject jsonObject = super.getJsonObject(FilePathConstants.basePath
				+ "times/" + passengerRegisterRequest.getNewTimeInterval()
				+ ".json");
		JSONObject passengerObject = (JSONObject) jsonObject.get(passengerId);

		if (passengerObject != null)
			jsonObject.remove(passengerId);
		else
			passengerObject = new JSONObject();
		passengerObject.put("routeNumber",
				passengerRegisterRequest.getRouteNumber());
		passengerObject.put("gcmId", passengerRegisterRequest.getGcmId());
		jsonObject.put(passengerId, passengerObject);
		if (!super.modifyJsonFile(FilePathConstants.basePath + "times/"
				+ passengerRegisterRequest.getNewTimeInterval() + ".json",
				jsonObject))
			return false;
		else
			return true;
	}

}
