package model;

import java.util.List;

import com.mongodb.BasicDBObject;

import net.vz.mongodb.jackson.*;
import play.modules.mongodb.jackson.MongoDB;
import model.types.Role;


public class User {

	private static JacksonDBCollection<User, String> users = MongoDB.getCollection("users", User.class, String.class);
	
	@Id
	@ObjectId
	public String id;
	
	public String userName="";
	public String name="";
	public String password="";
	public String surName="";
	public Role role;
	
	public User(String userName) {
		this.userName = userName;
	}
	public User(){
		
	}
	public List<User> findAll() {
		return users.find().toArray();
	}
	
	public static User findByLogin(String login) {
		
		return users.findOne(new BasicDBObject("login", login));
	}
	
	public static User authenticate(String userName, String password) {
		
		User user = null;

		BasicDBObject query = new BasicDBObject("userName", userName);
		query.append("password",password);
		
		DBCursor<User> cursor = users.find(query);
		
		try {
			   while(cursor.hasNext()) {
				 user = cursor.next();
			   }
			} finally {
			   cursor.close();
			}
			
		return user;
	}
}