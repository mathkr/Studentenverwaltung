package stuma;

import java.util.Properties;
import java.io.FileInputStream;
import java.io.InputStream;

public class Config {
	private static final String DB_FILE = "resources/stumadb";
	private static final String GENERAL_FILE = "resources/.stumarc";

	private static Properties databaseProperties;
	private static Properties generalProperties;

	private Config(){}

	public static Properties database() {
		if (databaseProperties == null) {
			databaseProperties = readPropertiesFile(DB_FILE);
		}

		return databaseProperties;
	}

	public static Properties general() {
		if (generalProperties == null) {
			generalProperties = readPropertiesFile(GENERAL_FILE);
		}

		return generalProperties;
	}

	private static Properties readPropertiesFile(String file) {
		Properties res = new Properties();
		try {
			InputStream is = new FileInputStream(file);
			res.load(is);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return res;
	}
}
