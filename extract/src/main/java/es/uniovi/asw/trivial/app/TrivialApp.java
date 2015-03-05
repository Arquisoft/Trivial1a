package es.uniovi.asw.trivial.app;

import java.io.IOException;

import es.uniovi.asw.trivial.excepcion.BusinessException;
import es.uniovi.asw.trivial.infrastructure.Factory;
import es.uniovi.asw.trivial.model.Trivial;

public class TrivialApp {

	private Trivial trivial;
	private TrivialSerializer serializer;

	public TrivialApp(TrivialParser parser) throws IOException {
		this.trivial = parser.parse();
	}
	
	public Trivial getTrivial() {
		return trivial;
	}
	
	public void setSerializer(TrivialSerializer serializer) {
		this.serializer = serializer;
	}
	
	
	public void toJSon(String file) throws BusinessException {
		serializer.serialize(trivial, file);
	}

	public void saveToDataBase() {
		Factory.persistence.createTrivialDAO().saveQuestions(trivial);
	}
}