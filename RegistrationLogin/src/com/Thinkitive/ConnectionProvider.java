package com.Thinkitive;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

public class ConnectionProvider {

	Connection con;
	Statement st;
	PreparedStatement ps;
	
	public ConnectionProvider(){
		try {
			Class.forName("com.mysql.jdbc.Driver");
		}catch(ClassNotFoundException e) {
			e.printStackTrace();
		}
	}
	public Connection getDbConnection() {

		try {
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/mydb", "prithwish", "Arghya@1997");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return con;

	}

	public void closeConnection() {
		if (con != null) {
			try {
				con.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	public Statement getStatement() {
		try {
			st = this.getDbConnection().createStatement();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return st;
	}
	
	public PreparedStatement getPreparedStatement(String sql) {
		try {
			ps = this.getDbConnection().prepareStatement(sql);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return ps;
	}
}
