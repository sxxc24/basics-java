package com.jdbc1.demo1;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Types;
import java.util.Scanner;

class connectdb {
	private static final String url = "jdbc:mysql://localhost:3306/demodata";
	private static final String user = "root";
	private static final String password = "Sudharsan@24";
	private static final String create = "CREATE TABLE empdb ( name VARCHAR(255) NOT NULL, email VARCHAR(255) NOT NULL,salary DECIMAL(10, 2) NOT NULL);";
	private static final String injectData = "INSERT INTO empdb (name, email, salary) VALUES (?, ?, ?)";
	Connection connect;
	Statement st;
	PreparedStatement ps;
	CallableStatement cst;

	public void methodConnect() {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			connect = DriverManager.getConnection(url, user, password);
			ps = connect.prepareStatement(create);
			if (!isTableExists()) {
				ps.execute();
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void update(String name, String email, int salary) throws SQLException {
		ps = connect.prepareStatement(injectData);
		ps.setString(1, name);
		ps.setString(2, email);
		ps.setInt(3, salary);
		ps.executeUpdate();
	}

	public void show() throws SQLException {
		st = connect.createStatement();
		// ResultSet rs = st.executeQuery("SELECT * FROM empdb");
		CallableStatement cst1 = connect.prepareCall("{call getdb()}");// procedure for salary >40000
		cst1.execute();
		ResultSet rs = cst1.getResultSet();
		while (rs.next()) {
			String name = rs.getString("name");
			String email = rs.getString("email");
			int salary = rs.getInt("salary");
			System.out.println("name : " + name + " email : " + email + " salary : " + salary);
		}
	}

	public void search(String name) throws SQLException {
		cst = connect.prepareCall("{call getEmailByname(?,?)}");// procedure created in mysql workbench
		cst.setString(1, name);
		cst.registerOutParameter(2, Types.VARCHAR);// output of sql procedure
		cst.execute();
		String result = cst.getString(2);
		System.out.println(result);
	}

	public boolean isTableExists() {
		boolean tableExists = false;
		try {
			DatabaseMetaData dbMetaData = connect.getMetaData();
			ResultSet rs = dbMetaData.getTables(null, null, "empdb", null);
			tableExists = rs.next();
			rs.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return tableExists;
	}
}

public class PerfectConnection {

	public static void main(String[] args) throws SQLException, InterruptedException {
		// TODO Auto-generated method stub

		connectdb ob = new connectdb();

		ob.methodConnect();

		Scanner ip = new Scanner(System.in);
		while (true) {
			System.out.println("update [y/n] : ");
			String yn = ip.next();
			if (yn.equalsIgnoreCase("n"))
				break;
			System.out.println("enter name : ");
			String name = ip.next();
			System.out.println("enter email : ");
			String email = ip.next();
			System.out.println("enter salary : ");
			int salary = ip.nextInt();
			ob.update(name, email, salary);

		}

		ob.show();
		Thread.sleep(500);
		System.err.println("enter name to search: ");
		String name = ip.next();
		ob.search(name);
	}

}
