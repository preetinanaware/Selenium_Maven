package utility;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class PropertyUtils {

	Properties properties;

	public PropertyUtils(String filePath) {
		File file = new File(filePath);
		properties = new Properties();

		try {
			FileInputStream fileInput = new FileInputStream(file);
			properties.load(fileInput);
		} catch (IOException e) {
		}
	}

	public String getPropertyValue(String key) {
		return properties.getProperty(key);
	}

}
