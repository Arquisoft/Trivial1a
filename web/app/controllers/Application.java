package controllers;

import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.awt.Point;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;

import javax.imageio.ImageIO;

import com.sun.org.apache.xerces.internal.impl.dv.util.Base64;

import model.Login;
import model.Question;
import model.Register;
import model.Trivial;
import model.User;
import model.types.Color;
import play.data.DynamicForm;
import play.data.Form;
import play.mvc.Controller;
import play.mvc.Result;
import views.html.ayuda;
import views.html.estadisticas;
import views.html.inicio;
import views.html.prejuego;
import views.html.registro;
import views.html.tablero;
import controllers.board2D.BuilderBoard2D;
import cucumber.api.java.cs.A;

public class Application extends Controller {
	
	static Trivial trivial = null;
	static BuilderBoard2D builderBoard = null;
	

	public static Result mostrarInicio() {

		Form<Login> loginForm = Form.form(Login.class);
		return ok(inicio.render(loginForm));
	}

	public static Result enviarLogin() {

		Form<Login> filledForm = Form.form(Login.class).bindFromRequest();

		if (filledForm.hasGlobalErrors()) {

			return badRequest(inicio.render(filledForm));
			// return ok(routes.Application.mostrarInicio());
		} else {
			Login login = filledForm.get();
			
			Form<String> nameForm = Form.form(String.class);
			
			return ok(prejuego.render(login.userName, nameForm));
			
			// return redirect(routes.Application.mostrarPrejuego(login.name));
		}

		// return redirect(routes.Application.mostrarPrejuego(login.name));
		// return ok(views.html.prejuego.render(login.name));
	}

	public static Result mostrarRegistro() {
		Form<Register> registerForm = Form.form(Register.class);

		return ok(registro.render(registerForm));
	}

	public static Result enviarRegistro() {

		Form<Register> filledForm = Form.form(Register.class).bindFromRequest();

		if (filledForm.hasGlobalErrors()) {

			return badRequest(registro.render(filledForm));

		} else {
			Register registro = filledForm.get();
			User.register(registro.userName, registro.name, registro.surName,
					registro.password);
			return redirect(routes.Application.mostrarInicio());
		}

	}

	
	public static Result clickJugar(){//TODO JUGAR
		
//		Form<String> nameForm = Form.form(String.class).bindFromRequest();
		
//		if (nameForm.hasGlobalErrors()) {
//			return badRequest();
//		} else {
			
			trivial = new Trivial("NOMBRE");
			builderBoard = new BuilderBoard2D(true, false, false, false, false, false);
			
			getImage();
			return ok(tablero.render());
//		}

		
	}

	

	public static Result getImage() {
	    ByteArrayInputStream input = null; 
	    
	        try {
	        	BufferedImage img = builderBoard.getBufferedBoard();
	        	ByteArrayOutputStream baos = new ByteArrayOutputStream();
	            byte[] byteArray;
	            
	            ImageIO.write( img, "png", baos );
	            baos.flush();
	            byteArray = baos.toByteArray();
	            input = new ByteArrayInputStream(byteArray);
	        } catch (Exception e) {  }
	        System.out.println("LLEGA AQUI");
	    return ok(input).as("image/png");
	}
	
	
	public static Result clickDado(){
			
		//pintar posibles
		int[] aux = trivial.getPosiblesMov();
		
		builderBoard.pintarPosiblesMov(aux);
		
//////
		 ByteArrayInputStream input = null; 
		 
	        try {
	        	BufferedImage img = builderBoard.getBufferedBoard();
	        	ByteArrayOutputStream baos = new ByteArrayOutputStream();
	        	byte[] byteArray={};          
	            ImageIO.write( img, "png", baos );
	            baos.flush();
	            byteArray = baos.toByteArray();
	            input = new ByteArrayInputStream(byteArray);
	        } catch (Exception e) {  }
	        System.out.println("LLEGA CLICKDADO");
	        
//	        Base64.encode(byteArray);
	    return ok(input).as("image/png");
//		 System.out.println("LLEGA CLICKDADO");
//		return getImage();
	}

	public static Result clickTablero() {

		  DynamicForm form = Form.form().bindFromRequest();

//		         BufferedImage img = builderBoard.getBufferedBoard();
//		         ByteArrayOutputStream baos = new ByteArrayOutputStream();
//		         try {
//					ImageIO.write( img, "jpg", baos );
//					baos.flush();
//				} catch (IOException e) {
//					// TODO Auto-generated catch block
//					e.printStackTrace();
//				}
//		         
//		     	byte[] imageInByte = baos.toByteArray();
//		      
//		     	 File file = new File( "d:\Images\"+name+".jpg" );
//		        return ok(org.apache.commons.io.FileUtils.readFileToByteArray(file)).as("image/jpeg");
// 

		if (form.data().size() == 0) {
			return badRequest("No vienen coordenadas bien");
		} else {

			int casilla = builderBoard.getCasilla(new Point(Integer.parseInt(form.get("x")), Integer.parseInt(form.get("y"))) ) ;
			
			System.out.println(casilla);
			
			Map<Color, List<Question>> questions = trivial.getQuestions();
			List<Question> questions2 = questions.get(Color.YELLOW);

			Random generator = new Random();
			int i = generator.nextInt(questions2.size());

			Question question = questions2.get(i);

			String response = Integer.parseInt(form.get("x")) + ";"
					+ Integer.parseInt(form.get("y")) + ";"
					+ question.getQuestion() + ";"
					+ question.getAnswers().get(0).answer + ";"
					+ question.getAnswers().get(1).answer + ";"
					+ question.getAnswers().get(2).answer + ";"
					+ question.getAnswers().get(3).answer + ";"
					+ question.getAnswers().get(4).answer;
			
			return ok(response);
		}

	}

	public static Result mostrarAyuda() {

		return ok(ayuda.render());
	}

	public static Result mostrarEstadisticas() {

		return ok(estadisticas.render());
	}


//	public static Result mostrarTablero() {
//
//		return ok(tablero.render(0,0));
//	}

	public static Result mostrarTablero() {

		return ok(tablero.render());
	}

	// public static Result index() {
	// return ok(
	// views.html.index.render(User.all(), userForm)
	// );
	// }

	// public static Result tasks() {
	// return ok(
	// views.html.index.render(Task.all(), taskForm)
	// );
	// }
	//
	// public static Result newTask() {
	// Form<Task> filledForm = taskForm.bindFromRequest();
	// if(filledForm.hasErrors()) {
	// return badRequest(
	// views.html.index.render(Task.all(), filledForm)
	// );
	// } else {
	// Task.create(filledForm.get());
	// return redirect(routes.Application.tasks());
	// }
	// }
	//
	// public static Result deleteTask(String id) {
	// Task.delete(id);
	// return redirect(routes.Application.tasks());
	// }
}
