package db;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import alaposztalyok.Employee;

public class DBConnection {

	private static Connection conn;

	public static Connection connect() {
		String connString = "jdbc:sqlserver://localhost; integratedSecurity = true; databasename=NORTHWND";
		try {
			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
		} catch (Exception e) {
			e.printStackTrace();
		}
		try {
			conn = DriverManager.getConnection(connString);
			return conn;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	public static List<Employee> empBe(String sql) {
		List<Employee> empList = new ArrayList<>();
		connect();
		try {
			// String sql = "select * from Employees where City = "+c;

			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(sql);

			while (rs.next()) {
				String last = rs.getString(2);
				String first = rs.getString(3);
				String city = rs.getString(9);
				int id = rs.getInt(1);
				// System.out.println(last);
				Employee emp = new Employee(first, last, city, id);
				empList.add(emp);
			}

			rs.close();
			stmt.close();
			conn.close();
			return empList;
		} catch (Exception e) {
			e.printStackTrace();

		} finally {
			return empList;
		}
	}

	public static void getTerritory(int empID) {
		connect();
		String sql = "select r.RegionDescription, t.TerritoryDescription "
				+ "from Region r left outer join Territories t on r.RegionID=t.RegionID where t.TerritoryID "
				+ "in(select t.TerritoryID from EmployeeTerritories et "
				+ "inner join Employees e on e.EmployeeID=et.EmployeeID "
				+ "inner join Territories t on et.TerritoryID = t.TerritoryID where e.EmployeeID=" + empID + ") "
				+ "order by TerritoryDescription";

		try {
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(sql);
			while (rs.next()) {
				System.out.println(rs.getString(1) + " " + rs.getString(2));
			}
			rs.close();
			stmt.close();
			conn.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public static boolean torol(int id) {
		String sql = "delete from Employees where EmployeeID = " + id;
		try {
			Statement stmt = conn.createStatement();
			boolean siker = stmt.execute(sql);
			stmt.close();
			conn.close();
			return siker;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

}
