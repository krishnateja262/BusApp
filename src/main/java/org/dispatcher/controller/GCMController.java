package org.dispatcher.controller;

import org.dispatcher.constants.FilePathConstants;
import org.dispatcher.constants.Status;
import org.dispatcher.reader.DriverReaderImpl;
import org.dispatcher.reader.NotifierReader;
import org.dispatcher.reader.ReaderFactory;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class GCMController {
	
	@Autowired
	ReaderFactory readerFactory;

	public void setReaderFactory(ReaderFactory readerFactory) {
		this.readerFactory = readerFactory;
	}

	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/timing/{timeInterval}", method = RequestMethod.GET)
	public JSONObject getNotifiers(@PathVariable String timeInterval) {
		NotifierReader notifierReader = (NotifierReader) readerFactory.getReader(this.getClass().getSimpleName());
		if (Integer.parseInt(timeInterval) <= 10
				&& Integer.parseInt(timeInterval) >= 1)
			return notifierReader.getJsonObject(FilePathConstants.basePath
					+ "times/" + timeInterval + ".json");
		else{
			JSONObject jsonObject = new JSONObject();
			jsonObject.put("status", Status.ERROR);
			return jsonObject;
		}
	}

	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/notifiers/{routeId}", method = RequestMethod.GET)
	public JSONObject mainNotifier(@PathVariable String routeId) {
		DriverReaderImpl driverReader = (DriverReaderImpl) readerFactory.getReader("DriverInfoController");
		JSONObject jsonObject = new JSONObject();
		if(driverReader.getDriverPosition(routeId).isSendNotification()){
			jsonObject.put("status", Status.INFO);
			return jsonObject;
		}else{
			jsonObject.put("status", Status.ERROR);
			return jsonObject;
		}
	}
}
