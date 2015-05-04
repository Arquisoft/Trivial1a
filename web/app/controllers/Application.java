package controllers;

import java.awt.Point;





import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;

import javax.imageio.ImageIO;

import controllers.board2D.BuilderBoard2D;
import model.Login;
import model.Register;
import model.Task;
import model.Trivial;
import model.User;
import play.data.DynamicForm;
import play.data.Form;
import play.mvc.Controller;
import play.mvc.Result;
import views.html.*;

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
			Register registro= filledForm.get();
			User.register(registro.userName, registro.name, registro.surName, registro.password);
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
			
			return ok(tablero.render());
//		}

		
	}

	
	
	public static Result clickTablero() {

		  DynamicForm form = Form.form().bindFromRequest();

		     if (form.data().size() == 0) {
		         return badRequest("No vienen coordenadas bien");
		     } else { 
		      
		         String response = Integer.parseInt(form.get("x")) +";"+ Integer.parseInt(form.get("y") );
		         
		         
		         BufferedImage img = builderBoard.getBufferedBoard();
		         ByteArrayOutputStream baos = new ByteArrayOutputStream();
		         try {
					ImageIO.write( img, "jpg", baos );
					baos.flush();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		         
		     	byte[] imageInByte = baos.toByteArray();
		         
		         
		         
		         return ok(imageInByte).as("image/jpeg");
		         
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