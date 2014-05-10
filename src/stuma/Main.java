package stuma;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.PreparedStatement;
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
				int pruefungen = rs.getInt("pruefungen");
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
