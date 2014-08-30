package org.dispatcher.reader;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ReaderFactory {

	@Autowired
	DriverReaderImpl driverReader;

	public void setDriverReader(DriverReaderImpl driverReader) {
		this.driverReader = driverReader;
	}
	
	@Autowired
    LoginReaderImpl login;

    public void setLogin(LoginReaderImpl login) {
		this.login = login;
	}
    
	@Autowired
	PassengerReader passengerReader;

	public void setPassengerReader(PassengerReader passengerReader) {
		this.passengerReader = passengerReader;
	}
	
	@Autowired
	NotifierReader notifierReader;

	public void setNotifierReader(NotifierReader notifierReader) {
		this.notifierReader = notifierReader;
	}
	
	public Reader getReader(String s){
		if (s.equalsIgnoreCase("DriverInfoController"))
			return driverReader;
		else if(s.equalsIgnoreCase("LoginRestController"))
			return login;
		else if(s.equalsIgnoreCase("PassengerController"))
			return passengerReader;
		else if(s.equalsIgnoreCase("GCMController"))
			return notifierReader;
		else
			return null;
	}
}
