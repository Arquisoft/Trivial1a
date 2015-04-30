package model;

import java.util.List;

import net.vz.mongodb.jackson.Id;
import net.vz.mongodb.jackson.JacksonDBCollection;
import net.vz.mongodb.jackson.ObjectId;
import play.modules.mongodb.jackson.MongoDB;

public class UserOld {

	@Id
	@ObjectId
	public String id;

	public String name;
	public String password;
	public boolean admin;
	public int rightQuestions;
	public int failedQuestions;

	private static JacksonDBCollection<UserOld, String> coll = MongoDB.getCollection("users", UserOld.class, String.class);
	
	public UserOld(){
		
	}

	public UserOld(String name, String password, boolean admin, int rightQuestions, int failedQuestions) {
		this.name = name;
		this.password = password;
		this.admin = admin;
		this.rightQuestions = rightQuestions;
		this.failedQuestions = failedQuestions;
	}

	 public static List<UserOld> all() {
		    return UserOld.coll.find().toArray();
	 }
	

}