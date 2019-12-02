package com.luv2code.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;

public class TestJdbc {

	public static void main(String[] args) {

		String jdbcUrl = 
				"jdbc:mysql://localhost:3306/hb_student_tracker?useSSL=false&serverTimezone=UTC";
		String user = "hbstudent";
		String password = "@hbStudent!18";
		
		try {
			System.out.println("connecting to the DATABASE: " + jdbcUrl);
			Connection conn = DriverManager.getConnection(jdbcUrl, user, password);
			System.out.println("connection successful !!!: " + conn);

		} catch (Exception excp) {
			excp.printStackTrace();
		}
	}
}
