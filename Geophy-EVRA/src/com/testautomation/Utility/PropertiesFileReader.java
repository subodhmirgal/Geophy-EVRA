package com.testautomation.Utility;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

/*Author - Subodh M
 * This method is use to read config file
 */
public class PropertiesFileReader {

	public Properties getProperty() throws IOException {
		@SuppressWarnings("unused")
		FileInputStream inputStream = null;
		Properties properties = new Properties();
		try {
			properties.load(new FileInputStream("resources/browser-config.properties"));
		} catch (Exception e) {
			System.out.println("Exception: " + e);
		}
		return properties;
	}

}
