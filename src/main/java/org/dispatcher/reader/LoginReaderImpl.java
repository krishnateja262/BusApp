package org.dispatcher.reader;

import java.util.HashMap;
import java.util.Map;

import org.dispatcher.beans.UserBean;
import org.dispatcher.constants.FilePathConstants;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.springframework.stereotype.Component;

/**
 * Created by KrishnaTeja on 19/08/14.
 */
@Component
public class LoginReaderImpl extends AbstractLoginReader implements Reader{

	public Map<String, UserBean> getUsers() {
		JSONObject jsonObject = super.getJsonObject(FilePathConstants.userList);
		JSONArray jsonArray = (JSONArray) jsonObject.get("users");
		JSONObject jsonObject2 = new JSONObject();
		Map<String, UserBean> userMap = new HashMap<String, UserBean>();
		for(int i=0;i<jsonArray.size();i++){
			UserBean userBean = new UserBean();
			jsonObject2 = (JSONObject) jsonArray.get(i);
			userBean.setUsername((String) jsonObject2.get("id"));
			userBean.setPassword((String) jsonObject2.get("password"));
			userBean.setType((String) jsonObject2.get("type"));
			userMap.put(userBean.getUsername(), userBean);
		}
		return userMap;
	}

	@Override
	public String matchUser(String username, String password) {
		Map<String, UserBean> userMap = getUsers();
		if(userMap.get(username) != null){
			if(userMap.get(username).getPassword().equals(password))
				return userMap.get(username).getType().toUpperCase();
		}
		return null;
	}
	
	
}
