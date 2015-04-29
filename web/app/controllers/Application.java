package controllers;

import model.Task;
import model.UserOld;
import play.data.Form;
import play.mvc.Controller;
import play.mvc.Result;


public class Application extends Controller {
  static Form<Task> taskForm = Form.form(Task.class); 
  static Form<UserOld> userForm = Form.form(UserOld.class);
  
  public static Result index() {
//    return redirect(routes.Application.tasks());
	  return ok(views.html.inicio.render());
  }
  public static Result index2() {
//    return redirect(routes.Application.tasks());
	  return ok(views.html.registro.render());
  }
  public static Result index3() {
//    return redirect(routes.Application.tasks());
	  return ok(views.html.prejuego.render());
  }
  public static Result index4() {

	  return ok(views.html.ayudaa.render());
}
  public static Result index5() {

	  return ok(views.html.estadisticas.render());
}
  
  public static Result users() {
    return ok(
      views.html.index.render()
    );
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
