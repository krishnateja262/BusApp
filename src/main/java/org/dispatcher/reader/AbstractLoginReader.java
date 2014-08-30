package org.dispatcher.reader;

import java.util.Map;

import org.dispatcher.beans.UserBean;

/**
 * Created by KrishnaTeja on 19/08/14.
 */
public abstract class AbstractLoginReader extends ReaderInterfaceImpl{

	public abstract Map<String, UserBean> getUsers();

	public abstract String matchUser(String username, String password);
}
