package es.uniovi.asw.trivial.app;

import es.uniovi.asw.trivial.excepcion.BusinessException;
import es.uniovi.asw.trivial.model.Trivial;


public interface TrivialSerializer {
	
	void serialize(Trivial trivial, String file) throws BusinessException;
}
