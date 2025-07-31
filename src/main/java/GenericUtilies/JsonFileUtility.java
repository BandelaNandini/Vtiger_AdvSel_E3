package GenericUtilies;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class JsonFileUtility {
/**
 * This method is used to fetch data from json file
 * @param key
 * @return String
 * @throws FileNotFoundException
 * @throws IOException
 * @throws ParseException
 */
	public String fetchDataFromJSONfile(String key) throws FileNotFoundException, IOException, ParseException {
		JSONParser parser = new JSONParser();
		Object obj = parser.parse(new FileReader("./src/test/resources/Vtiger_JsonData.json"));
		JSONObject js = (JSONObject) obj;
		String data = js.get(key).toString();
		return data;
	}

}
