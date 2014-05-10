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
import org.javalite.activejdbc.Model;

import com.mysql.jdbc.Driver;

public class Main {
	private class Student extends Model {}

	public static void main (String[] args) {
		String driver = "com.mysql.jdbc.Driver";
		String db = "jdbc:mysql://h2285677.stratoserver.net/stuma";
		String user = "stuma";
		String pw = "samsung";

		Base.open(driver, db, user, pw);

		Student.createIt(
		    "last_name",  "Krause",
		    "first_name", "Matthis",
		    "dob",   	  "1991-06-02"
		);

		Base.close();
	}
}
