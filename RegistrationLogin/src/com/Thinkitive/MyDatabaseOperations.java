package com.Thinkitive;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class MyDatabaseOperations {

	ConnectionProvider db = new ConnectionProvider();
	Statement st;
	PreparedStatement ps;

	public int insert(String name, String email, String password, String city, String country) {

		int status = 0;
		try {
			ps = db.getPreparedStatement("insert into registration values(?,?,?,?,?)");
			ps.setString(1, name);
			ps.setString(2, email);
			ps.setString(3, password);
			ps.setString(4, city);
			ps.setString(5, country);
			status = ps.executeUpdate();

			db.closeConnection();
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		return status;
	}
	
	public boolean isValidCredential(String email, String password) {
		
		boolean status = false;
		try {
			ps = db.getPreparedStatement("select * from registration where email=? and password=?");
		
			ps.setString(1,email);
			ps.setString(2, password);
			ResultSet rs = ps.executeQuery();
			status = rs.next();
			
			db.closeConnection();

		}catch(Exception e) {
			e.printStackTrace();
		}
		return status;
	}

	public void deleteEmp(int id) {

		try {
			ps = db.getPreparedStatement("delete from user where id=?");
			ps.setInt(1, id);
			ps.execute();
			db.closeConnection();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/*public void updateEmp(int id, String name) {
		try {
			ps = db.getPreparedStatement("update think_emp set name=? where id=?");
			ps.setString(1, name);
			ps.setInt(2, id);
			ps.execute();
			db.closeConnection();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}*/

	public List displayEmp() {
		
		ArrayList<String> l=new ArrayList<>();
		st = db.getStatement();

		try {
			ResultSet rs = st.executeQuery("select * from registration");

			while (rs.next()) {
				
				l.add(rs.getString(1));
				l.add(rs.getString(2));
				l.add(rs.getString(4));
				l.add(rs.getString(5));
				l.add("@");
				
			}

			db.closeConnection();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		System.out.println(l);
		return l;

	}
}