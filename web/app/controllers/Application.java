package controllers;

import model.Login;
import model.Task;
import model.UserOld;
import play.data.Form;
import play.mvc.Controller;
import play.mvc.Result;


public class Application extends Controller {
  static Form<Task> taskForm = Form.form(Task.class); 
  static Form<UserOld> userForm = Form.form(UserOld.class);
   
  
  public static Result mostrarInicio() {
	  
	  Form<Login> loginForm = Form.form(Login.class);
	  return ok(views.html.inicio.render(loginForm));
  }
  
  public static Result  enviarLogin() {
	  Form<Login> filledForm = Form.form(Login.class).bindFromRequest();
	  
	  if(filledForm.hasErrors()) {	  
		  
        return badRequest(views.html.inicio.render(filledForm));
//		  return ok(routes.Application.mostrarInicio());
      } else {
    	  Login login = filledForm.get();
       return ok(views.html.prejuego.render(login.name)); 
//    	  return redirect(routes.Application.mostrarPrejuego(login.name)); 
      }

//	  return redirect(routes.Application.mostrarPrejuego(login.name));
//	  return ok(views.html.prejuego.render(login.name));
  }
  
//  public static Result mostrarPrejuego(String name ){
////    return redirect(routes.Application.tasks());
//
//	  return ok(views.html.prejuego.render(name));
//  }
  
  public static Result mostrarRegistro() {
//    return redirect(routes.Application.tasks());
	  return ok(views.html.registro.render());
  }
 
  public static Result mostrarAyuda() {

	  return ok(views.html.ayudaa.render());
}
  public static Result mostrarEstadisticas() {

	  return ok(views.html.estadisticas.render());
}
  public static Result mostrarTablero() {

	  return ok(views.html.tablero.render());
}
  
   
//  public static Result index() {
//	    return ok(
//	      views.html.index.render(User.all(), userForm)
//	    );
//	  }
  
//  public static Result tasks() {
//    return ok(
//      views.html.index.render(Task.all(), taskForm)
//    );
//  }
//  
//  public static Result newTask() {
//    Form<Task> filledForm = taskForm.bindFromRequest();
//      if(filledForm.hasErrors()) {
//        return badRequest(
//          views.html.index.render(Task.all(), filledForm)
//        );
//      } else {
//        Task.create(filledForm.get());
//        return redirect(routes.Application.tasks());  
//      }
//  }
//  
//  public static Result deleteTask(String id) {
//    Task.delete(id);
//    return redirect(routes.Application.tasks());
//  }
  
}
