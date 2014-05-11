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

import org.javalite.activejdbc.Base;
import java.util.List;

public class StumaModel implements Subsystem {
	private static StumaModel singleton;

	private StumaModel() {}

	public static StumaModel getStumaModel() {
		if (singleton == null) {
			singleton = new StumaModel();
		}

		return singleton;
	}

	private void initDataBaseConnection() {
		String driver = Config.database().getProperty("driver");
		String protocol = Config.database().getProperty("protocol");
		String host = Config.database().getProperty("host");
		String database = Config.database().getProperty("database");
		String user = Config.database().getProperty("user");
		String pass = Config.database().getProperty("pass");

		StringBuilder con = new StringBuilder();
		con.append("jdbc:");
		con.append(protocol);
		con.append("://");
		con.append(host);
		con.append("/");
		con.append(database);

		Base.open(driver, con.toString(), user, pass);
	}

	private void disconnect() {
		Base.close();
	}

	@Override
	public void close() {
		if (Base.hasConnection()) {
			disconnect();
		}
	}

	public List<Student> getStudents() {
		if (!Base.hasConnection()) {
			initDataBaseConnection();
		}

		return Student.findAll();
	}

	public Student getStudentById(int id) {
		if (!Base.hasConnection()) {
			initDataBaseConnection();
		}

		return Student.findById(id);
	}
}
