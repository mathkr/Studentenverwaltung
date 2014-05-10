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

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.ResultSet;
import java.sql.Date;

public class Main {
	public static void main (String[] args) {
		try {
			Class.forName("com.mysql.jdbc.Driver").newInstance();

			Connection con = DriverManager.getConnection(
    			    "jdbc:mysql://h2285677.stratoserver.net/" +
			    "stuma?user=stuma&password=samsung");

			System.out.println("connection successful");

			String query = "SELECT TABLE_NAME FROM " +
			    "INFORMATION_SCHEMA.TABLES WHERE " +
			    "TABLE_TYPE='base TABLE' AND TABLE_SCHEMA='stuma'";

			ResultSet rs = con.createStatement()
			    .executeQuery(query);

			System.out.println("\ntables:");
			while (rs.next()) {
				System.out.println(rs.getString(1));
			}
			System.out.println();

			query = "SELECT * FROM students";
			rs = con.createStatement().executeQuery(query);

			System.out.println("\nstudents:");
			while (rs.next()) {
				String name = rs.getString("first_name");
				name += " " + rs.getString("last_name");
				Date bday = rs.getDate("birthday");
				int matrikel = rs.getInt("matrikel");

				System.out.printf("%s %d %s%n",
				    name, matrikel, bday.toString());
			}
			System.out.println();

		} catch (SQLException e) {
			System.out.println("message: " + e.getMessage());
			System.out.println("state: " + e.getSQLState());
			System.out.println("errorcode: " + e.getErrorCode());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
