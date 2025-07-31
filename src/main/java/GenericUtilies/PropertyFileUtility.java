package GenericUtilies;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Properties;

/**
 * @author B.Nandini This class contains property file reusable methods
 */
public class PropertyFileUtility {

	/**
	 * This method is used to fetch the data from property file
	 * 
	 * @param key
	 * @return String
	 * @throws IOException
	 */
	public String FetchDataFromPropFile(String key) throws IOException {
		FileInputStream fis = new FileInputStream("./src/test/resources/VTiger_CMData.properties");
		Properties p = new Properties();
		p.load(fis);
		String data = p.getProperty(key);
		return data;
	}

	/**
	 * This method is used to write back data to property file
	 * 
	 * @param key
	 * @param value
	 * @param comments
	 * @throws IOException
	 */
	public void WriteBackDataToPropFile(String key, String value, String comments) throws IOException {
		FileInputStream fis = new FileInputStream("./src/test/resources/VTiger_CMData.properties");
		Properties p = new Properties();
		p.load(fis);
		p.put(key, value);
		FileOutputStream fos = new FileOutputStream("./src/test/resources/VTiger_CMData.properties");
		p.store(fos, comments);
	}

}
