package es.uniovi.asw.trivial.app;

import es.uniovi.asw.trivial.excepcion.BusinessException;
import es.uniovi.asw.trivial.model.Trivial;

public interface TrivialParser {
	
	Trivial parse() throws BusinessException;
}
