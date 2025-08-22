package utils;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class ReadProperty {

	public static String getPropData(String dataToBeRead) throws IOException {
		String path = System.getProperty("user.dir") + "\\src\\test\\resources\\Config.properties";

		FileInputStream fis = new FileInputStream(path);

		Properties prop = new Properties();

		prop.load(fis);

		String browserValue = prop.getProperty(dataToBeRead);

		System.out.println(browserValue);

		return browserValue;

	}

	public static void main(String[] args) throws IOException {
		String data = getPropData("client_id");

		System.out.println(data);

		
	}

}
