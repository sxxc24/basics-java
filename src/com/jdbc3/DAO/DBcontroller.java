package com.jdbc3.DAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DBcontroller {
	Connection cn;

	DBcontroller() throws ClassNotFoundException, SQLException {
		String url = "jdbc:mysql://localhost:3306/demodata", user = "root", password = "Sudharsan@24";
		String Driver = "com.mysql.cj.jdbc.Driver";
		Class.forName(Driver);
		cn = DriverManager.getConnection(url, user, password);
	}

	public DatabaseDAO getDB(String name) throws SQLException {
		DatabaseDAO pojo = new DatabaseDAO();
		PreparedStatement pst = cn.prepareStatement("select * from empdb where name= ?");
		pst.setString(1, name);
		ResultSet rs = pst.executeQuery();
		if (rs.next()) {
			pojo.setName(rs.getString("name"));
			pojo.setEmail(rs.getString("email"));
			pojo.setSalary(rs.getInt("salary"));
		}
		return pojo;
	}

	public void setDB(String name, String email, int salary) throws SQLException {
		String injectData = "INSERT INTO empdb (name, email, salary) VALUES (?, ?, ?)";
		PreparedStatement pst = cn.prepareStatement(injectData);
		pst.setString(1, name);
		pst.setString(2, email);
		pst.setInt(3, salary);
		pst.executeUpdate();
	}
}
