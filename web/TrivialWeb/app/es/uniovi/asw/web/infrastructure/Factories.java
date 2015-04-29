package es.uniovi.asw.web.infrastructure;

import es.uniovi.asw.web.business.ServicesFactory;
import es.uniovi.asw.web.business.impl.SimpleServicesFactory;
import es.uniovi.asw.web.persistance.PersistenceFactory;
import es.uniovi.asw.web.persistance.impl.SimplePersistenceFactory;

public class Factories {

	public static ServicesFactory services = new SimpleServicesFactory();
	public static PersistenceFactory persistence = new SimplePersistenceFactory();
}