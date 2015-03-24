package es.uniovi.asw.game.persistence;

import java.net.UnknownHostException;

import com.mongodb.DB;
import com.mongodb.MongoClient;

/**
 * The Class DBHelper.
 */
public class DBHelper {

	/** The mongo. */
	private static MongoClient mongo;

	/** The instance. */
	private static DBHelper instance;

	/**
	 * Instantiates a new DB helper.
	 */
	private DBHelper() {
	}

	/**
	 * Crear conexion.
	 */
	private static void crearConexion() {
		try {
			mongo = new MongoClient("localhost", 27017);
		} catch (UnknownHostException e) {
			throw new RuntimeException(
					"No ha sido posible establecer la conexión con "
							+ "la Base de Datos. Error: " + e.getMessage());
		}
	}

	/**
	 * Gets the connection.
	 *
	 * @return the connection
	 */
	public MongoClient getConnection() {
		if (mongo == null)
			crearConexion();

		return mongo;
	}

	/**
	 * Gets the single instance of DBHelper.
	 *
	 * @return single instance of DBHelper
	 */
	public static DBHelper getInstance() {
		if (instance == null)
			instance = new DBHelper();

		return instance;
	}

	/**
	 * Gets the database.
	 *
	 * @param name
	 *            the name
	 * @return the database
	 */
	public DB getDatabase(String name) {
		if (mongo == null)
			crearConexion();

		return mongo.getDB(name);
	}
}