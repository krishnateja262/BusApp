package org.dispatcher.reader;

import org.json.simple.JSONObject;

/**
 * Created by KrishnaTeja on 18/08/14.
 */
public interface ReaderInterface {

    public JSONObject getJsonObject(String filePath);
    
    public boolean modifyJsonFile(String filePath, JSONObject jsonObject);
}
