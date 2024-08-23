package com.jdbc3.DAO;

import java.sql.SQLException;

public class AccessDAO {
	public static void main(String[] args) throws ClassNotFoundException, SQLException {
		DBcontroller get = new DBcontroller();
		DatabaseDAO dataset = get.getDB("sudharsan x");
		System.out.println(dataset.toString());
	}
}
