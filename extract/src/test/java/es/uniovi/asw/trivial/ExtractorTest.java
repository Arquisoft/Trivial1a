package es.uniovi.asw.trivial;

import java.io.IOException;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;

import es.uniovi.asw.trivial.app.GIFTParser;
import es.uniovi.asw.trivial.app.TrivialApp;
import es.uniovi.asw.trivial.app.TrivialParser;
import es.uniovi.asw.trivial.infrastructure.Factory;
import es.uniovi.asw.trivial.model.Answer;
import es.uniovi.asw.trivial.model.Question;
import es.uniovi.asw.trivial.model.Trivial;
import es.uniovi.asw.trivial.persistence.TrivialDAO;

public class ExtractorTest {

	final String[] realQuestions = { "¿Quién 'sabía que no sabía nada'?",
			"¿En qué lugar del cuerpo se produce la insulina?",
			"¿En que ciudad nacio Fernando alonso?",
			"¿Con qué se fabricaba el pergamino?",
			"¿Qué atleta se hizo famoso por utilizar una nueva técnica en atletismo?" };

	final String[] realAnswers = { "Aristóteles", "Ortega y Gasset",
			"Sócrates", "En la hipófisis", "En el páncreas", "En el duodeno",
			"Oviedo", "Londres", "Gijon", "Con piel de animales",
			"Con tiras de madera", "Con hojas de arbusto", "Dick Fosbury",
			"Carl Lewis", "Emil Zátopek" };

	final String[] realCorrectAnswers = { "Sócrates", "En el páncreas",
			"Oviedo", "Con hojas de arbusto", "Dick Fosbury" };
	final String[] realCategories = { "", "", "", "", "" };
	final String[] realComments = { "", "", "", "", "" };

	@Test
	public void testParserGift() {

		try {

			TrivialApp app = new TrivialApp(new GIFTParser("test1.gift"));
			Trivial trivial = app.getTrivial();
			Assert.assertNotNull(trivial);
			List<Question> questions = trivial.getQuestions();

			for (int i = 0, j = 0; i < questions.size(); i++) {
				Question preg = questions.get(i);

				Assert.assertTrue(preg.getName().equals("Pregunta " + (i + 1)));
				Assert.assertTrue(preg.getQuestion().equals(realQuestions[i]));

				for (Answer answer : preg.getAnswers()) {

					Assert.assertTrue(answer.getAnswer().equals(realAnswers[j]));

					if (answer.getAnswer().equals(realCorrectAnswers[i])) {
						Assert.assertTrue(answer.getIsCorrect());
					}

					j++;
				}

				// Assert.assertTrue(preg.getCategory().equals(realCategories[i]));
				// Assert.assertTrue(preg.getComments().equals(realComments[i]));

			}

		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	@Test
	public void testLeerDB() {

		// TrivialDAO trivialDAO = Factory.persistence.createTrivialDAO();
		final TrivialDAO trivialDAO = Factory.persistence
				.createTrivialSimulator();

		try {

			trivialDAO.deleteAllQuestions();

			TrivialApp app = new TrivialApp(new GIFTParser("test1.gift"));
			Assert.assertNotNull(app);

			trivialDAO.saveQuestions(app.getTrivial());

			app = new TrivialApp(new TrivialParser() {

				@Override
				public Trivial parse() throws IOException {
					Trivial trivial = new Trivial();

					for (Question q : trivialDAO.findAllQuestions())
						trivial.addQuestion(q);

					return trivial;
				}
			});

			Trivial trivial = app.getTrivial();
			Assert.assertNotNull(trivial);
			List<Question> questions = trivial.getQuestions();

			for (int i = 0, j = 0; i < questions.size(); i++) {
				Question preg = questions.get(i);

				Assert.assertTrue(preg.getName().equals("Pregunta " + (i + 1)));
				Assert.assertTrue(preg.getQuestion().equals(realQuestions[i]));

				for (Answer answer : preg.getAnswers()) {

					Assert.assertTrue(answer.getAnswer().equals(realAnswers[j]));

					if (answer.getAnswer().equals(realCorrectAnswers[i])) {
						Assert.assertTrue(answer.getIsCorrect());
					}

					j++;
				}
				// Assert.assertTrue(preg.getCategory().equals(realCategories[i]));
				// Assert.assertTrue(preg.getComments().equals(realComments[i]));

			}

		} catch (IOException e) {
			Assert.assertTrue(false);
		}

		Assert.assertFalse(trivialDAO.findAllQuestions().isEmpty());

		trivialDAO.deleteAllQuestions();

		Assert.assertTrue(trivialDAO.findAllQuestions().isEmpty());
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

		// TrivialApp app = new TrivialApp(new GIFTParser("test2.gift"));

	}

}
