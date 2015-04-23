package es.uniovi.asw.trivial.infrastructure;

import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import es.uniovi.asw.trivial.excepcion.BusinessException;

public class Logger {

	private static final String LOG_FILE = "src/main/java/resources/log/log.txt";
		
	public void log(String file, String process, String issue) throws BusinessException {
		
		String nameFile = "\n".concat(file);
		String processName = "\t\t\t\t\t\t".concat(process);
		SimpleDateFormat format = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");	
		String date = "\t\t\t\t\t".concat(format.format(Calendar.getInstance().getTime()));
		String issueToLog = "\t\t\t\t\t".concat(issue);
		
		write(nameFile.concat(processName).concat(date).concat(issueToLog.replace("\n", "")));
	}

	private void write(String line) throws BusinessException {
		
		FileWriter writer = null;
		
		try {
			writer = new FileWriter(LOG_FILE, true);
			writer.write(line);
		} catch (IOException io) {
			throw new BusinessException("Error de Entrada/Salida: " + io.getMessage());
		} finally {
			try {
				writer.close();
			} catch (IOException e) {
				throw new BusinessException("\nNo ha sido posible cerrar el flujo de datos: " + e.getMessage());
			}
		}
	}
}