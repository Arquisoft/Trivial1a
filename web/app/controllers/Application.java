package controllers;

import java.awt.Point;



import model.Login;
import model.Register;
import model.Task;
import model.User;
import play.data.DynamicForm;
import play.data.Form;
import play.mvc.Controller;
import play.mvc.Result;
import views.html.*;

public class Application extends Controller {


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
			return ok(prejuego.render(login.userName));
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
	
	
	
	public static Result clickTablero() {

		DynamicForm data = Form.form().bindFromRequest();
		
		int x = Integer.parseInt( data.get("x") );

		int y = Integer.parseInt( data.get("y") );
		
		Point point = new Point(x, y);
		
		return ok();
		
	}
	
	

	public static Result mostrarAyuda() {

		return ok(ayuda.render());
	}

	public static Result mostrarEstadisticas() {

		return ok(estadisticas.render());
	}

	public static Result mostrarTablero() {

		return ok(tablero.render(0,0));
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