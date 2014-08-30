package org.dispatcher.reader;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.stereotype.Service;

@Service
public class ReaderInterfaceImpl implements ReaderInterface{

	public JSONObject getJsonObject(String filePath) {
		
		JSONParser jsonParser = new JSONParser();
        JSONObject jsonObject = null;
        try{
            Object object = jsonParser.parse(new FileReader(filePath));
            jsonObject = (JSONObject)object;
        }catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return jsonObject;
	}

	public boolean modifyJsonFile(String filePath, JSONObject jsonObject) {
		boolean flag = false;
		try{
			FileWriter file = new FileWriter(filePath);
			file.write(jsonObject.toJSONString());
			file.flush();
			file.close();
			flag = true;
		}catch(IOException e){
			e.printStackTrace();
		}
		return flag;
	}

}
