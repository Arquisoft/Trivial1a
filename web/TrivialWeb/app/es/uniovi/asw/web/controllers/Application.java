package es.uniovi.asw.web.controllers;

import play.mvc.Controller;
import play.mvc.Result;
import es.uniovi.asw.web.views.html.*;

public class Application extends Controller {

    public static Result index() {
        return ok(index.render("Your new application is ready."));
    }
}