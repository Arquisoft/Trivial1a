package es.uniovi.asw.trivial.persistence;

import java.net.UnknownHostException;
import com.mongodb.DB;
import com.mongodb.MongoClient;

//hacer singleton
public class DBHelpper 
{
	private static MongoClient mongo = null;
	private static DBHelpper instance = null;
	
	private DBHelpper() {}
	
	private static void crearConexion() 
	{
		mongo = new MongoClient("localhost", 27017);
    }
	
	public MongoClient getConnection()
	{
		if(mongo == null)
			crearConexion();
		return mongo;
	} 
	
	public static DBHelpper getInstance()
	{
		if(instance == null)
			instance = new DBHelpper();
		return instance;
	}
	
	@SuppressWarnings("deprecation")
	public DB getDatabase(String name)
	{
		if(mongo == null)
			crearConexion();
//		mongo.getDatabase(DB_NAME);
		return mongo.getDB(name);
	}

}
