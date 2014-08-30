package org.dispatcher.controller;

import org.dispatcher.beans.LoginBean;
import org.dispatcher.beans.LoginResponseBean;
import org.dispatcher.constants.Status;
import org.dispatcher.constants.UserType;
import org.dispatcher.reader.LoginReaderImpl;
import org.dispatcher.reader.ReaderFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by KrishnaTeja on 18/08/14.
 */
@RestController
public class LoginRestController {

	@Autowired
	ReaderFactory readerFactory;

	public void setReaderFactory(ReaderFactory readerFactory) {
		this.readerFactory = readerFactory;
	}
    
    
    @RequestMapping(value = "/login", method = RequestMethod.POST, consumes="application/json", produces="application/json")
    public LoginResponseBean authenticateUser(@RequestBody LoginBean loginBean){
    	LoginReaderImpl login = (LoginReaderImpl) readerFactory.getReader(this.getClass().getSimpleName());
    	String matcher = login.matchUser(loginBean.getUsername(), loginBean.getPassword());
        if(matcher != null)
            return new LoginResponseBean(Status.INFO, UserType.valueOf(matcher));
        else
            return new LoginResponseBean(Status.ERROR);
    }
}
