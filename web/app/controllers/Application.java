package controllers;

import java.awt.Point;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.util.List;
import java.util.Map;
import java.util.Random;

import javax.imageio.ImageIO;

import model.Box;
import model.Login;
import model.Question;
import model.Register;
import model.Trivial;
import model.User;
import model.types.Color;
import model.types.Role;
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

public class Application extends Controller {

	static Trivial trivial = null;
	static BuilderBoard2D builderBoard = null;
	static int[] posiblesMov = {};

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

	public static Result clickJugar(String name) {
		System.out.println(name);
		trivial = new Trivial(name);
		builderBoard = new BuilderBoard2D(true, false, false, false, false,
				false);
		return ok(tablero.render(name));
	}

	public static Result getImage() {
		ByteArrayInputStream input = null;

		try {
			BufferedImage img = builderBoard.getBufferedBoard();
			ByteArrayOutputStream baos = new ByteArrayOutputStream();
			byte[] byteArray;

			ImageIO.write(img, "png", baos);
			baos.flush();
			byteArray = baos.toByteArray();
			input = new ByteArrayInputStream(byteArray);
		} catch (Exception e) {
		}
		System.out.println("LLEGA AQUI");
		return ok(input).as("image/png");
	}

	public static Result clickDado() {

		DynamicForm form = Form.form().bindFromRequest();

		int x = Integer.parseInt(form.get("total"));
		posiblesMov = trivial.getPosiblesMov(x);

		builderBoard.pintarPosiblesMov(posiblesMov);

		ByteArrayInputStream input = null;
		try {
			BufferedImage img = builderBoard.getBufferedBoard();
			ByteArrayOutputStream baos = new ByteArrayOutputStream();
			byte[] byteArray = {};
			ImageIO.write(img, "png", baos);
			baos.flush();
			byteArray = baos.toByteArray();
			input = new ByteArrayInputStream(byteArray);
		} catch (Exception e) {
		}
		System.out.println("LLEGA CLICKDADO");

		return ok(input).as("image/png");
	}

	public static Result acierto() {

		int actual = trivial.getActualPlayer().getActual().getId();
		int[] wedges = trivial.getGraph().getWedges();

		for (int i = 0; i < wedges.length; i++) {
			if (wedges[i] == actual) {
				trivial.getActualPlayer().addWedge(trivial.getGraph().getBox(wedges[i]).getCategory());
				
				System.out.println("Al jugador"+trivial.getActualPlayer().getUser()+ " se le ha dado el quesito "+ trivial.getGraph().getBox(wedges[i]).getCategory());
				
				List<Color> quesitos = trivial.getActualPlayer().getWedges();
				
				builderBoard.pintarQuesitos(quesitos);
				builderBoard.repintarTablero(actual);
			}
		}
		int aciertos = trivial.getActualPlayer().getWins();

		trivial.getActualPlayer().setWins(aciertos + 1);

		User usuario = User.findByName(trivial.getActualPlayer().getUser());
		Box casillaActual=trivial.getGraph().getBox(actual); 
		
		trivial.getActualPlayer().setAcierto(casillaActual.getCategory());
		
		System.out.println("se le ha sumado un acierto al usuario "
				+ usuario.name + " en la categoria "
				+ casillaActual.getCategory());
		
		System.out.println("el jugador tiene "
				+ trivial.getActualPlayer().getWins() + " aciertos y "
				+ trivial.getActualPlayer().getFails() + " fallos");

		if (actual == 7 && trivial.getActualPlayer().gano()) {
			System.out.println("HAS GANADO CAMPEON");
			usuario.saveUser(trivial.getActualPlayer());
			System.out.println(usuario.toString());
			System.out.println(trivial.getActualPlayer().toString());
			
			return ok("win");
		}
		return ok("continue");
	}

	public static Result fallo() {

		int actual = trivial.getActualPlayer().getActual().getId();
		int[] wedges = trivial.getGraph().getWedges();

		for (int i = 0; i < wedges.length; i++) {
			if (wedges[i] == actual) {
				trivial.getActualPlayer().removeWedge(
						trivial.getGraph().getBox(wedges[i]).getCategory());
				System.out.println("Al jugador"
						+ trivial.getActualPlayer().getUser()
						+ " se le ha quitado el quesito "
						+ trivial.getGraph().getBox(wedges[i]).getCategory());
				
				List<Color> quesitos = trivial.getActualPlayer().getWedges();
				System.out.println("Ques: "+quesitos);
				
				builderBoard.pintarQuesitos(quesitos);
				builderBoard.repintarTablero(actual);
				
			}
		}

		int fallos = trivial.getActualPlayer().getFails();
		trivial.getActualPlayer().setFails(fallos + 1);
		System.out.println("el jugador tiene "
				+ trivial.getActualPlayer().getWins() + " aciertos y "
				+ trivial.getActualPlayer().getFails() + " fallos");
		return ok();
	}

	public static Result clickTablero() {

		DynamicForm form = Form.form().bindFromRequest();

		if (form.data().size() == 0) {
			return badRequest("No vienen coordenadas bien");
		} else {
			String response = "";
			int casilla = builderBoard.getCasilla(new Point(Integer
					.parseInt(form.get("x")), Integer.parseInt(form.get("y"))));
			Box actual = new Box(casilla);

			System.out.println(casilla);
			boolean correcto = false;
			if(posiblesMov != null){
				for (int i = 0; i < posiblesMov.length; i++) {
					System.out.println(posiblesMov[i]);
	
					if (posiblesMov[i] == actual.getId()) {
						correcto = true;
					}
				}
			
			if (correcto) {
				trivial.getActualPlayer().setActual(actual);

				builderBoard.repintarTablero(actual.getId());

				Map<Color, List<Question>> questions = trivial.getQuestions();
				
				Color casillaColor = trivial.getGraph().getBox(actual.getId()).getCategory();
				
//				List<Question> questions2 = questions.get(Color.YELLOW);
				
				List<Question> questions2 = questions.get(casillaColor);
				
				Random generator = new Random();
				int i = generator.nextInt(questions2.size());

				Question question = questions2.get(i);
				response = Integer.parseInt(form.get("x")) + ";"
						+ Integer.parseInt(form.get("y")) + ";"
						+ question.getQuestion() + ";"
						+ question.getAnswers().get(0).answer + ";"
						+ question.getAnswers().get(1).answer + ";"
						+ question.getAnswers().get(2).answer + ";"
						+ question.getAnswers().get(3).answer + ";"
						+ question.getAnswers().get(4).answer;
				posiblesMov = null;
				return ok(response);
			}
			}
			return ok();
		}

	}

	public static Result mostrarAyuda() {

		return ok(ayuda.render());
	}

	public static Result mostrarEstadisticas(String name) { 
		return ok(estadisticas.render(name));
	}
	
	public static Result cargarEstadisticas() {
		DynamicForm form = Form.form().bindFromRequest();
		String name=form.get("usuario");
		if(User.existUserName(name)){
		User user=User.findByName(name);
		
		if(user.role==Role.ADMIN){
			
			List<User> users=User.findAll();
			String response="admin;";
			for(User u  :users){
				System.out.println(u.userName);
				if(!u.userName.equals("admin")){

					int ciencia=u.aciertoCiencias;
					int arte=u.aciertoArte;
					int historia=u.aciertoHistoria;
					int geografia=u.aciertoGeografia;
					int entretenimiento=u.aciertoEntretenimiento;
					int deporte=u.aciertoDeportes;
					
					int total=ciencia+arte+historia+geografia+entretenimiento+deporte;
					if(total==0)total=1;
					response=response+u.userName;
					response=response+";"+ciencia+";"+arte+";"+historia+";"+geografia+";"+entretenimiento+";"+deporte+";"+total+";";
					}
			}
			return ok(response); 
		}
		
		int ciencia=user.aciertoCiencias;
		int arte=user.aciertoArte;
		int historia=user.aciertoHistoria;
		int geografia=user.aciertoGeografia;
		int entretenimiento=user.aciertoEntretenimiento;
		int deporte=user.aciertoDeportes;
		
		int total=ciencia+arte+historia+geografia+entretenimiento+deporte;
		if(total==0)total=1;
		String response=ciencia+";"+arte+";"+historia+";"+geografia+";"+entretenimiento+";"+deporte+";"+total;
		 
		return ok(response);}
		return ok("invalido");
	}
}
