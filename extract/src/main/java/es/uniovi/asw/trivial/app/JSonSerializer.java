package es.uniovi.asw.trivial.app;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

import com.google.gson.Gson;

import es.uniovi.asw.trivial.excepcion.BusinessException;
import es.uniovi.asw.trivial.model.Trivial;

public class JSonSerializer implements TrivialSerializer {

	private Gson jSon;
	
	public JSonSerializer() {
		jSon = new Gson();
	}

	@Override
	public void serialize(Trivial trivial, String file) throws BusinessException {
		
		String jSonString = jSon.toJson(trivial);
	
		BufferedWriter writer = null;
	
		try {
			writer = new BufferedWriter(new FileWriter(removeFileExtension(file).concat(".json")));
			writer.write(jSonString);
		} catch (IOException e) {
			throw new BusinessException("No ha sido posible generar el fichero JSon. Error: " + e.getMessage());
		} finally {
			try {
				if (writer != null)
					writer.close();
			} catch (IOException e) {
				throw new BusinessException("No ha sido posible cerrar el flujo de datos. Error: " + e.getMessage());
			}
		}
	}

	private String removeFileExtension(String file) {
		
		int dot = file.lastIndexOf('.');
		String noExtFile = null;

		if (dot > 0)
			noExtFile = file.substring(0, dot);
		
		return noExtFile;
	}	
}