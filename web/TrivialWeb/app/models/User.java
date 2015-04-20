package models;

import java.util.List;
import play.modules.mongodb.jackson.MongoDB;
import net.vz.mongodb.jackson.Id;
import net.vz.mongodb.jackson.JacksonDBCollection;
import net.vz.mongodb.jackson.ObjectId;

public class User {

	@Id
	@ObjectId
	public String id;

	public String name;
	public String password;
	public boolean admin;
	public int rightQuestions;
	public int failedQuestions;

	private static JacksonDBCollection<User, String> coll = MongoDB.getCollection("users", User.class, String.class);
	
	public User(){
		
	}

	public User(String name, String password, boolean admin, int rightQuestions, int failedQuestions) {
		this.name = name;
		this.password = password;
		this.admin = admin;
		this.rightQuestions = rightQuestions;
		this.failedQuestions = failedQuestions;
	}

	 public static List<User> all() {
		    return User.coll.find().toArray();
	 }
	

}
