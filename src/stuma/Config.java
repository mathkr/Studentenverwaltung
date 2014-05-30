/* Programming assignments for 'Programmieren I + II' at the
 * Hochschule Bremerhaven, GERMANY.
 *
 * Copyright (C) 2014 Matthis Krause
 *
 * This program is free software; you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation; either version 2 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program; if not, see <http://www.gnu.org/licenses/>.
 */

package stuma;

import java.util.Properties;
import java.io.FileInputStream;
import java.io.InputStream;

public class Config {
	private static final String GENERAL_FILE = "resources/.stumarc";

	private static Properties generalProperties;

	private Config(){}

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
