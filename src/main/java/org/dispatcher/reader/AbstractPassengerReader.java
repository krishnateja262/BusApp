package org.dispatcher.reader;

import org.dispatcher.beans.passenger.PassengerRegisterRequest;
import org.dispatcher.beans.passenger.PassengerRegisterResponse;
import org.dispatcher.beans.passenger.PassengerRouteResponse;

public abstract class AbstractPassengerReader extends ReaderInterfaceImpl{

	public abstract PassengerRouteResponse getBusNumber(String startingUID, String endingUID, String session);
	
	public abstract PassengerRegisterResponse registerPassenger(String passengerId,PassengerRegisterRequest passengerRegisterRequest);
}
