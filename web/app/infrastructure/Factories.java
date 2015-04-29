package infrastructure;

import persistance.PersistenceFactory;
import persistance.impl.SimplePersistenceFactory;
import business.ServicesFactory;
import business.impl.SimpleServicesFactory;

public class Factories {

	public static ServicesFactory services = new SimpleServicesFactory();
	public static PersistenceFactory persistence = new SimplePersistenceFactory();
}