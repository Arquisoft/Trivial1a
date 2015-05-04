package model;

import java.util.List;

import model.types.Role;
import net.vz.mongodb.jackson.DBCursor;
import net.vz.mongodb.jackson.Id;
import net.vz.mongodb.jackson.JacksonDBCollection;
import net.vz.mongodb.jackson.ObjectId;
import play.modules.mongodb.jackson.MongoDB;

import com.mongodb.BasicDBObject;


public class User {

	private static JacksonDBCollection<User, String> users = MongoDB.getCollection("users", User.class, String.class);
	
	@Id
	@ObjectId
	public String id;
	
	public String userName="";
	public String name="";	
	public String surName="";
	public String password="";
	public Role role = Role.USER;
	
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
	
	
	public User(String userName, String name,String surName,String password) {

		this.userName = userName;
		this.name = name;
		this.password = password;
		this.surName = surName;
		
	}
	public static User register(String userName, String name,String surName,String password) {
		User user = new User(userName,name,surName,password);
		
		user = users.insert(user).getSavedObject();
		
		return user;
	}
	
	public static boolean existUserName(String userName){
		
		User user = null;
		
		BasicDBObject query = new BasicDBObject("userName", userName);

		DBCursor<User> cursor = users.find(query);
		
		try {
			   while(cursor.hasNext()) {
				 user = cursor.next();
			   }
			} finally {
			   cursor.close();
			}
		
		return user==null?false:true;
		
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