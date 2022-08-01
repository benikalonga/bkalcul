package beni.momentum.bkalcul.controlers;

import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import beni.momentum.bkalcul.models.User;

public class UserDAO implements DbDAO<User> {

	@Override
	public boolean create(User t) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public User read(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public User read(String id) {
		// TODO Auto-generated method stub
		return null;
	}
	public User read(String username, String password) {
		 try{
			 
			 	String sql = "select * from user where username = '"+username+"' and password = '"+password+"'";
			 	
	            Statement stmt=DbConnection.getConnection().createStatement();  
	            ResultSet rs=stmt.executeQuery(sql);
	            
	            if(rs != null) {
	            	rs.next();
		            
		            User user = new User();
		            user.setUsername(rs.getString("username"));
		            user.setPassword(rs.getString("password"));  
		            user.setAdmin(rs.getBoolean("isAdmin"));  

		            return user;
	            }
	            
	        }catch(Exception e){
	            System.out.println(e);
	        }       
		 return null;
	}

	@Override
	public ArrayList<User> readAll() {
		try{
			 
		 	String sql = "select * from user";
		 	
            Statement stmt=DbConnection.getConnection().createStatement();  
            ResultSet rs=stmt.executeQuery(sql);
            
            if(rs != null) {
            	ArrayList<User> usersList = new ArrayList<User>();
            	while(rs.next()) {

    	            User user = new User();
    	            user.setUsername(rs.getString("username"));
    	            user.setPassword(rs.getString("password"));  
    	            user.setAdmin(rs.getBoolean("isAdmin"));  
    	            
    	            usersList.add(user);
            	}
            	return usersList;
            }
            
        }catch(Exception e){
            System.out.println(e);
        }       
		return null;
	}

	@Override
	public boolean delete(int id) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean delete(String username) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean updateRecord(User t) {
		// TODO Auto-generated method stub
		return false;
	}
	
}
