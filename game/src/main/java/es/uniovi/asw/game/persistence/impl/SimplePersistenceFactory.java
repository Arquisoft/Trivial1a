package es.uniovi.asw.game.persistence.impl;

import es.uniovi.asw.game.model.User;
import es.uniovi.asw.game.persistence.PersistenceFactory;
import es.uniovi.asw.game.persistence.TrivialDAO;

/**
 * A factory for creating SimplePersistence objects.
 * @author Grupo 1a
 * @see <a href = "https://github.com/Arquisoft/Trivial1a/" /> Git Grupo 1a </a> 
 */
public class SimplePersistenceFactory implements PersistenceFactory {

	@Override
	public TrivialDAO createTrivialDAO() {
		try{
			TrivialDAO dao = new TrivialGatewayImpl();
			dao.existUser(new User("prueba", "prueba", false, 0, 0));
			return new TrivialGatewayImpl();
		}catch(Exception e)
		{
			System.out.println("no ha sido posible conectar con la base de datos. \n por lo tanto se simulará.");
			return new TrivialGatewaySimulator();
		}
	}

	@Override
	public TrivialDAO createTrivialSimulator() {
		return new TrivialGatewaySimulator();
	}
}