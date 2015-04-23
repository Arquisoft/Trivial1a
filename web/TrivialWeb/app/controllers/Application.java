package controllers;

import play.mvc.*;
import play.data.*;

import models.*;


public class Application extends Controller {
  static Form<Task> taskForm = Form.form(Task.class); 
  static Form<User> userForm = Form.form(User.class);
  
  public static Result index() {
//    return redirect(routes.Application.tasks());
	  return ok(views.html.inicio.render());
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