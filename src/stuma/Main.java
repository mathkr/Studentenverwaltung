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

public class Main {
	public static void main (String[] args) {
		String driver = Config.database().getProperty("driver");
		String protocol = Config.database().getProperty("protocol");
		String host = Config.database().getProperty("host");
		String database = Config.database().getProperty("database");
		String user = Config.database().getProperty("user");
		String pass = Config.database().getProperty("pass");

		String con= "jdbc:" + protocol + "://" + host + "/" + database;

		Base.open(driver, con, user, pass);

		/* Student s = new Student(); */
		/* s.set("first_name", "Hans"); */
		/* s.set("last_name", "Meier"); */
		/* s.set("dob", "1989-04-21"); */
		/* s.saveIt(); */

		Student s = Student.findFirst("first_name = ?", "Matthis");
		System.out.println(s);

		/* Student.createIt( */
		/*     "last_name",  "Krause", */
		/*     "first_name", "Matthis", */
		/*     "dob",   	  "1991-06-02" */
		/* ); */

		Base.close();
	}
}
