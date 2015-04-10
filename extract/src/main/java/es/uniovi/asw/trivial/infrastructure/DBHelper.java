package es.uniovi.asw.trivial.infrastructure;

import java.net.UnknownHostException;
import java.util.Arrays;

import com.mongodb.DB;
import com.mongodb.MongoClient;
import com.mongodb.MongoCredential;
import com.mongodb.ServerAddress;

public class DBHelper 
{
	private static MongoClient mongo;
	private static DBHelper instance;
	
	private DBHelper() { }
	
	private static void crearConexion() {
		try {
//			mongo = new MongoClient("localhost", 27017);
			
			char[] pass={'1','2','3','4'};
			MongoCredential credential = MongoCredential.createMongoCRCredential("trivial1a", "trivial1a", pass);
			mongo = new MongoClient(new ServerAddress("ds039311.mongolab.com:39311"), Arrays.asList(credential));
			
		} catch (UnknownHostException e) {
			throw new RuntimeException("No ha sido posible establecer la conexión con "
					+ "la Base de Datos. Error: " + e.getMessage());
		}
    }
	
	public MongoClient getConnection() {
		if(mongo == null)
			crearConexion();
		
		return mongo;
	} 
	
	public static DBHelper getInstance() {
		if(instance == null)
			instance = new DBHelper();
		
		return instance;
	}
	
	public DB getDatabase(String name) {
		if(mongo == null)
			crearConexion();
		
		return mongo.getDB(name);
	}
}