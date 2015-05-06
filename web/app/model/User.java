package model;

import java.util.List;

import model.types.Color;
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
	
	public String userName = "";
	public String name = "";
	public String surName = "";
	public String password = "";
	public Role role = Role.USER;
	public int aciertoGeografia = 0;
	public int aciertoCiencias = 0;
	public int aciertoArte = 0;
	public int aciertoDeportes = 0;
	public int aciertoHistoria = 0;
	public int aciertoEntretenimiento = 0;
	
	
	public void setAcierto(Color color){
		switch(color){
		case BLUE:
				aciertoGeografia++;
			break;
		case BROWN:
				aciertoArte++;
			break;
		case CENTER:
			break;
		case GREEN:
				aciertoCiencias++;
			break;
		case ORANGE:
				aciertoDeportes++;
			break;
		case PINK:
				aciertoEntretenimiento++;
			break;
		case YELLOW:
				aciertoHistoria++;
			break;
		default:
			break;

		}
	}

	public User(){
		
	}
public User(String userName){
		this.userName=userName;
	}
	
	public List<User> findAll() {
		return users.find().toArray();
	}
	
	@Override
	public String toString() {
		return "User [userName=" + userName + ", name=" + name + ", surName="
				+ surName + ", AciertoGeografia=" + aciertoGeografia
				+ ", aciertoCiencias=" + aciertoCiencias + ", AciertoArte="
				+ aciertoArte + ", AciertoDeportes=" + aciertoDeportes
				+ ", AciertoHistoria=" + aciertoHistoria
				+ ", AciertoEntretenimiento=" + aciertoEntretenimiento + "]";
	}

	public void saveUser(User user){

		
		
		BasicDBObject updated= new BasicDBObject();
		updated.append("$inc",new BasicDBObject().append("aciertoCiencias", aciertoCiencias));
		updated.append("$inc",new BasicDBObject().append("aciertoGeografia", aciertoGeografia ));
		updated.append("$inc",new BasicDBObject().append("aciertoHistoria", aciertoHistoria));
		updated.append("$inc",new BasicDBObject().append("aciertoArte", aciertoArte));
		updated.append("$inc",new BasicDBObject().append("aciertoEntretenimiento", aciertoEntretenimiento));
		updated.append("$inc",new BasicDBObject().append("aciertoDeportes", aciertoDeportes)); 
		
		System.out.println(updated.toMap());
		
		users.update(new BasicDBObject().append("userName", user.userName), updated);
		
	}
	
	
	public User(String userName, String name,String surName,String password) {

		this.userName = userName;
		this.name = name;
		this.password = password;
		this.surName = surName;
		aciertoGeografia = 0;
		aciertoCiencias = 0;
		aciertoArte = 0;
		aciertoDeportes = 0;
		aciertoHistoria = 0;
		aciertoEntretenimiento = 0;
	}
	
	public static User findByName(String name){ 
		User user = null;

		BasicDBObject query = new BasicDBObject("userName", name);

		DBCursor<User> cursor = users.find(query);

		try {
			while (cursor.hasNext()) {
				user = cursor.next();
			}
		} finally {
			cursor.close();
		}

		return user;
		
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