package model;

import java.util.List;

import model.types.Role;
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
	
	public String login;
	public String password;
	public String name;
	public String surName;
	public Role role;
	
	public User(String login) {
		this.login = login;
	}
	
	public List<User> findAll() {
		return users.find().toArray();
	}
	
	public static User findByLogin(String login) {
		
		return users.findOne(new BasicDBObject("login", login));
	}
}