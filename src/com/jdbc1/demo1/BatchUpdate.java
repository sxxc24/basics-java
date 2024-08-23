package com.jdbc1.demo1;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Scanner;

public class BatchUpdate {
	public static void main(String[] args) throws ClassNotFoundException, SQLException {

		String url = "jdbc:mysql://localhost:3306/demodata", user = "root", password = "Sudharsan@24";
		String update = "INSERT INTO empdb (name, email, salary) VALUES (?, ?, ?)";
		Class.forName("com.mysql.cj.jdbc.Driver");
		Connection cn = DriverManager.getConnection(url, user, password);
		PreparedStatement pst = cn.prepareStatement(update);
		Scanner ip = new Scanner(System.in);
		String name, email;
		int sal;
		int uplen = 0;
		while (true) {
			System.out.println("update [y/n] : ");
			String yn = ip.next();
			if (yn.equalsIgnoreCase("n")) {
				int[] uparr = pst.executeBatch();
				uplen = uparr.length;
				break;
			}
			System.out.println("enter name : ");
			name = ip.next();
			System.out.println("enter email : ");
			email = ip.next();
			System.out.println("enter salary : ");
			sal = ip.nextInt();
			pst.setString(1, name);
			pst.setString(2, email);
			pst.setInt(3, sal);
			pst.addBatch();
		}

		System.out.println(uplen);
	}
}
