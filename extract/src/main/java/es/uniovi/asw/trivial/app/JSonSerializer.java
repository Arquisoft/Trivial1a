package es.uniovi.asw.trivial.app;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

import com.google.gson.Gson;

import es.uniovi.asw.trivial.excepcion.BusinessException;
import es.uniovi.asw.trivial.infrastructure.Logger;
import es.uniovi.asw.trivial.model.Trivial;

public class JSonSerializer implements TrivialSerializer {

	private Gson jSon;
	private String jSonFile;
	private Logger logger;
	
	public JSonSerializer() {
		this.jSon = new Gson();
		this.logger = new Logger();
	}

	@Override
	public void serialize(Trivial trivial, String file) throws BusinessException {
		
		String jSonString = jSon.toJson(trivial);
	
		BufferedWriter writer = null;
	
		try {
			jSonFile = prepareJSonFile(file);
			writer = new BufferedWriter(new FileWriter(jSonFile));
			writer.write(jSonString);
		} catch (IOException e) {
			String msg = "No ha sido posible generar el fichero JSon. Error: " + e.getMessage();
			logger.log(jSonFile, "Serializer", msg);
			throw new BusinessException(msg);
		} finally {
			try {
				if (writer != null)
					writer.close();
			} catch (IOException e) {
				String msg = "No ha sido posible cerrar el flujo de datos. Error: " + e.getMessage();
				logger.log(jSonFile, "Serializer", msg);
				throw new BusinessException(msg);
			}
		}
	}

	private String prepareJSonFile(String file) {
		
		int dot = file.lastIndexOf('.');
		String noExtFile = null;

		if (dot > 0)
			noExtFile = file.substring(0, dot);
		
		return "src/main/java/resources/json/".concat(noExtFile).concat(".json");
	}	
}