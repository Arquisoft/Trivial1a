package es.uniovi.asw.web.model;


public class Random {

	private static java.util.Random random = new java.util.Random();

	public static Integer throwDie() {
		return random.nextInt(6)+1;
	}
	
	public static Integer randomQuestion(Integer max) {
		return random.nextInt(max);
	}
}