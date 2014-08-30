package org.dispatcher.controller;

import org.dispatcher.beans.passenger.PassengerRegisterRequest;
import org.dispatcher.beans.passenger.PassengerRegisterResponse;
import org.dispatcher.beans.passenger.PassengerRouteRequest;
import org.dispatcher.beans.passenger.PassengerRouteResponse;
import org.dispatcher.constants.FilePathConstants;
import org.dispatcher.reader.PassengerReader;
import org.dispatcher.reader.ReaderFactory;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PassengerController {
	
	@Autowired
	ReaderFactory readerFactory;

	public void setReaderFactory(ReaderFactory readerFactory) {
		this.readerFactory = readerFactory;
	}

	@RequestMapping(value = "/passengers/busnumbers", method = RequestMethod.POST)
	public PassengerRouteResponse getRoute(
			@RequestBody PassengerRouteRequest passengerRouteRequest) {
		PassengerReader passengerReader = (PassengerReader) readerFactory.getReader(this.getClass().getSimpleName());
		return passengerReader.getBusNumber(passengerRouteRequest
				.getStartingPoint(), passengerRouteRequest.getEndingPoint(),
				passengerRouteRequest.getSession().toString().toLowerCase());
	}
	
	@RequestMapping(value="/passengers/{passengerId}", method = RequestMethod.POST)
	public PassengerRegisterResponse updatePassengerDetails(@PathVariable String passengerId,@RequestBody PassengerRegisterRequest registerRequest){
		PassengerReader passengerReader = (PassengerReader) readerFactory.getReader(this.getClass().getSimpleName());
		return passengerReader.registerPassenger(passengerId,registerRequest);
	}
	
	@RequestMapping(value="/passengers/{passengerId}", method = RequestMethod.GET)
	public JSONObject getPassengerDetails(@PathVariable String passengerId){
		PassengerReader passengerReader = (PassengerReader) readerFactory.getReader(this.getClass().getSimpleName());
		JSONObject jsonObject = passengerReader.getJsonObject(FilePathConstants.passengerInfo);
		return (JSONObject) jsonObject.get(passengerId);
	}
	
	@RequestMapping(value="/passengers", method = RequestMethod.GET)
	public JSONObject getAllPassengersInfo(){
		PassengerReader passengerReader = (PassengerReader) readerFactory.getReader(this.getClass().getSimpleName());
		return passengerReader.getJsonObject(FilePathConstants.passengerInfo);
	}

}
