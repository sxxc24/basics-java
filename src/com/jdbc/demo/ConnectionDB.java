package com.jdbc.demo;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

class ConnectionDB extends Thread {
	private static final String url = "jdbc:mysql://localhost:3306/demodata";
	private static final String user = "root";
	private static final String password = "Sudharsan@24";

	public void run() {
		String ddl, update;
		Connection con = null;
		PreparedStatement ps = null;
		String updatesql = "INSERT INTO student (name, email) VALUES (?,?)";
		String delete = "DELETE FROM student WHERE id = ?";
		String search = "SELECT ";
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			con = DriverManager.getConnection(url, user, password);
			Statement st = con.createStatement();

			// ps = con.prepareStatement(updatesql);//compiles only once
			// ps.setString(1, "xxx");
			// ps.setString(2, "xxx@gmail.com");
			// ps.executeUpdate();
			// ps = con.prepareStatement(delete);
			// ps.setInt(1, 3);
			// ps.executeUpdate();
			// ddl = "CREATE TABLE student (id INT AUTO_INCREMENT PRIMARY KEY,name
			// VARCHAR(255) NOT NULL,email VARCHAR(255) NOT NULL)";
			// st.execute(ddl);
			// update = "INSERT INTO student (name, email) VALUES ('Alice Johnson',
			// 'alice.johnson@example.com'),('Bob Smith', 'bob.smith@example.com')";
			// st.executeUpdate(update);

			ResultSet rs = st.executeQuery("SELECT * FROM student");
			while (rs.next()) {
				int id = rs.getInt("id");
				String name = rs.getString("name");
				String email = rs.getString("email");

				// Display the data
				System.out.println("ID: " + id + ", Name: " + name + ", Email: " + email);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				con.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		ConnectionDB o = new ConnectionDB();
		ExecutorService ser = Executors.newFixedThreadPool(2);
		ser.submit(o);
		ser.shutdown();
	}

}
