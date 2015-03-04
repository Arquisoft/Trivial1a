package es.uniovi.asw.trivial;

import java.io.IOException;
import java.util.List;

import org.junit.*;

import es.uniovi.asw.trivial.app.GIFTParser;
import es.uniovi.asw.trivial.app.MongoParser;
import es.uniovi.asw.trivial.app.TrivialApp;
import es.uniovi.asw.trivial.model.Question;
import es.uniovi.asw.trivial.model.Trivial;
import es.uniovi.asw.trivial.persistence.TrivialGateway;

public class ExtractorTest {

	final String[] realQuestions = { 	"¿Quién 'sabía que no sabía nada'?",
										"¿En qué lugar del cuerpo se produce la insulina?",
										"¿En que ciudad nacio Fernando alonso?",
										"¿Con qué se fabricaba el pergamino?",
										"¿Qué atleta se hizo famoso por utilizar una nueva técnica en atletismo?" };
	final String[] realCategories = { "", "", "", "", "" };
	final String[] realAnswers = { 	"Sócrates", "En el páncreas", "Oviedo",
									"Con hojas de arbusto", "Dick Fosbury" };
	final String[] realComments = { "", "", "", "", "" };
	
	
	@Test
	public void testParserGift() {
		
		try {
		
			TrivialApp app = new TrivialApp(new GIFTParser("test1.gift"));
			Trivial trivial = app.getTrivial();
			Assert.assertNotNull(trivial);
			List<Question> questions = trivial.getQuestions();
			
			for (int i = 0; i< questions.size(); i++){
				Question preg = questions.get(i);
				
				Assert.assertTrue(preg.getName().equals("Pregunta " + (i + 1)));
				Assert.assertTrue(preg.getQuestion().equals(realQuestions[i]));
			//	Assert.assertTrue(preg.getAnswers().equals(realAnswers[i]));
			//	Assert.assertTrue(preg.getCategory().equals(realCategories[i]));
			//	Assert.assertTrue(preg.getComments().equals(realComments[i]));
				

			}

			
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	
	
	@Test
	public void testLeerDB() {
		
		try {
			TrivialGateway.deleteAllQuestions();
			
			TrivialApp app = new TrivialApp(new GIFTParser("test1.gift"));
			Assert.assertNotNull(app);
			
			TrivialGateway.addQuestions(app.getTrivial());
			
			app = new TrivialApp(new MongoParser());
			
			Trivial trivial = app.getTrivial();
			Assert.assertNotNull(trivial);
			List<Question> questions = trivial.getQuestions();
			
			for (int i = 0; i < questions.size(); i++) {
				Question preg = questions.get(i);

				Assert.assertTrue(preg.getName().equals("Pregunta " + (i + 1)));
				Assert.assertTrue(preg.getQuestion().equals(realQuestions[i]));
			//	Assert.assertTrue(preg.getAnswers().equals(realAnswers[i]));
			//	Assert.assertTrue(preg.getCategory().equals(realCategories[i]));
			//	Assert.assertTrue(preg.getComments().equals(realComments[i]));
				
			}
			
		} catch (IOException e) {
			Assert.assertTrue(false);
		}
		
		Assert.assertFalse(TrivialGateway.isEmpty());
		
		TrivialGateway.deleteAllQuestions();
		
		Assert.assertTrue(TrivialGateway.isEmpty());
		
	}
	
	

	@Test
	public void testArchivoDesconocido() {
		
		try {
		
			TrivialApp app = new TrivialApp(new GIFTParser("asf.gift"));
			Trivial trivial = app.getTrivial();
			Assert.assertNull(trivial);
			
			Assert.assertTrue(false);
		} catch (IOException e) {
			Assert.assertTrue(true);
		}
		
	}
	
	@Test
	public void testFormatoDesconocido() {
		
		try {
			TrivialApp app = new TrivialApp(new GIFTParser("test2.gift"));
			
		//	Assert.assertTrue(false);
		} catch (IOException e) {
			Assert.assertTrue(false);
		}

		
	}
	
	
	
}
