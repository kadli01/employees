package db;


import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import alaposztalyok.Employee;

public class DBConnection {
	
	private static Connection conn;
	
	public static Connection connect(){
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
	
	public static List<Employee> empBe(String sql){
		List<Employee> empList=new ArrayList<>();
		connect();
		try {		
			
			//String sql = "select * from Employees where City = "+c;
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(sql);
			
			while (rs.next()) {
				String last = rs.getString(2);
				String first = rs.getString(3);
				String city = rs.getString(9);
				int id = rs.getInt(1);
				//System.out.println(last);
				Employee emp = new Employee(first, last, city, id);
				empList.add(emp);		
			}
			rs.close();
			stmt.close();
			conn.close();
			return empList;
		} catch (Exception e) {
				e.printStackTrace();	
				
		}finally {
			return empList;
		}
	}
	
	public static boolean torol(int id) {
		String sql = "delete from Employees where EmployeeID = "+id;
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
