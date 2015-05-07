package controllers;

import java.awt.Point;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.util.ArrayList;
import java.util.HashMap;
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

	 static HashMap<String,Trivial> trivials = new HashMap<String,Trivial>();
	 static HashMap<String,BuilderBoard2D> builderBoards = new HashMap<String,BuilderBoard2D>();

	public static Result mostrarInicio() {

		Form<Login> loginForm = Form.form(Login.class);
		return ok(inicio.render(loginForm));
	}

	public static Result enviarLogin() {

		Form<Login> filledForm = Form.form(Login.class).bindFromRequest();

		if (filledForm.hasGlobalErrors()) {
			
			return badRequest(inicio.render(filledForm));
		} else {
			Login login = filledForm.get();

			Form<String> nameForm = Form.form(String.class);
			return ok(prejuego.render(login.userName, nameForm));
		}
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
		
		Trivial trivial = new Trivial(name);
		trivials.put(name,	trivial);
		
		BuilderBoard2D builderBoard = new BuilderBoard2D(true, false, false, false, false, false);
		builderBoards.put(name, builderBoard);
		
		return ok(tablero.render(name));
	}

	public static Result getImage() {
		ByteArrayInputStream input = null;
		
		DynamicForm form = Form.form().bindFromRequest();
		String name = form.get("usuario");
		
		try {
			BufferedImage img = builderBoards.get(name).getBufferedBoard();
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
		String name = form.get("usuario");
		
		int[] posiblesMov = trivials.get(name).getPosiblesMov(x);

		builderBoards.get(name).pintarPosiblesMov(posiblesMov);

		ByteArrayInputStream input = null;
		try {
			BufferedImage img = builderBoards.get(name).getBufferedBoard();
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
		DynamicForm form = Form.form().bindFromRequest();
		String name = form.get("usuario");
		
		int actual = trivials.get(name).getActualPlayer().getActual().getId();
		int[] wedges = trivials.get(name).getGraph().getWedges();

		for (int i = 0; i < wedges.length; i++) {
			if (wedges[i] == actual) {
				trivials.get(name).getActualPlayer().addWedge(trivials.get(name).getGraph().getBox(wedges[i]).getCategory());
				
				System.out.println("Al jugador"+trivials.get(name).getActualPlayer().getUser()+ " se le ha dado el quesito "+ trivials.get(name).getGraph().getBox(wedges[i]).getCategory());
				
				List<Color> quesitos = trivials.get(name).getActualPlayer().getWedges();
				
				builderBoards.get(name).pintarQuesitos(quesitos);
				builderBoards.get(name).repintarTablero(actual);
			}
		}
		int aciertos = trivials.get(name).getActualPlayer().getWins();

		trivials.get(name).getActualPlayer().setWins(aciertos + 1);

		User usuario = User.findByName(trivials.get(name).getActualPlayer().getUser());
		Box casillaActual=trivials.get(name).getGraph().getBox(actual); 
		
		trivials.get(name).getActualPlayer().setAcierto(casillaActual.getCategory());
		
		System.out.println("se le ha sumado un acierto al usuario "
				+ usuario.name + " en la categoria "
				+ casillaActual.getCategory());
		
		System.out.println("el jugador tiene "
				+ trivials.get(name).getActualPlayer().getWins() + " aciertos y "
				+ trivials.get(name).getActualPlayer().getFails() + " fallos");

		if (actual == 7 && trivials.get(name).getActualPlayer().gano()) {
			System.out.println("HAS GANADO CAMPEON");
			usuario.saveUser(trivials.get(name).getActualPlayer());
			System.out.println(usuario.toString());
			System.out.println(trivials.get(name).getActualPlayer().toString());
			
			return ok("win");
		}
		return ok("continue");
	}

	public static Result fallo() {
		DynamicForm form = Form.form().bindFromRequest();
		String name = form.get("usuario");
		
		int actual = trivials.get(name).getActualPlayer().getActual().getId();
		int[] wedges = trivials.get(name).getGraph().getWedges();

		for (int i = 0; i < wedges.length; i++) {
			if (wedges[i] == actual) {
				trivials.get(name).getActualPlayer().removeWedge(
						trivials.get(name).getGraph().getBox(wedges[i]).getCategory());
				System.out.println("Al jugador"
						+ trivials.get(name).getActualPlayer().getUser()
						+ " se le ha quitado el quesito "
						+ trivials.get(name).getGraph().getBox(wedges[i]).getCategory());
				
				List<Color> quesitos = trivials.get(name).getActualPlayer().getWedges();
				System.out.println("Ques: "+quesitos);
				
				builderBoards.get(name).pintarQuesitos(quesitos);
				builderBoards.get(name).repintarTablero(actual);
				
			}
		}

		int fallos = trivials.get(name).getActualPlayer().getFails();
		trivials.get(name).getActualPlayer().setFails(fallos + 1);
		System.out.println("el jugador tiene "
				+ trivials.get(name).getActualPlayer().getWins() + " aciertos y "
				+ trivials.get(name).getActualPlayer().getFails() + " fallos");
		return ok();
	}

	public static Result clickTablero() {

		DynamicForm form = Form.form().bindFromRequest();
		String name = form.get("usuario");
		int[] posiblesMov = trivials.get(name).getPosiblesMov();
		
		if (form.data().size() == 0) {
			return badRequest("No vienen coordenadas bien");
		} else {
			String response = "";
			int casilla = builderBoards.get(name).getCasilla(new Point(Integer
					.parseInt(form.get("x")), Integer.parseInt(form.get("y"))));
			Box actual = new Box(casilla);

			System.out.println(casilla);
			boolean correcto = false;
			if (posiblesMov != null) {
				for (int i = 0; i < posiblesMov.length; i++) {
					System.out.println(posiblesMov[i]);

					if (posiblesMov[i] == actual.getId()) {
						correcto = true;
					}
				}

				if (correcto) {
					trivials.get(name).getActualPlayer().setActual(actual);

					builderBoards.get(name).repintarTablero(actual.getId());

					Map<Color, List<Question>> questions = trivials.get(name)
							.getQuestions();

					Color casillaColor = trivials.get(name).getGraph()
							.getBox(actual.getId()).getCategory();

					if (!casillaColor.equals(Color.GREY)) {

						List<Question> questions2 = questions.get(casillaColor);//TODO 

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
						
						trivials.get(name).setPosiblesMov(null);
						return ok(response);

					}

				}

			}
		}
		return ok();
	}

	public static Result mostrarAyuda() {

		return ok(ayuda.render());
	}

	public static Result mostrarEstadisticas(String name) { 
		return ok(estadisticas.render(name));
	}
<<<<<<< HEAD


=======
	
	public static Result cargarEstadisticas() {
		DynamicForm form = Form.form().bindFromRequest();
		String name=form.get("usuario");
		if(User.existUserName(name)){
		User user=User.findByName(name);
		
		int ciencia=user.aciertoCiencias;
		int arte=user.aciertoArte;
		int historia=user.aciertoHistoria;
		int geografia=user.aciertoGeografia;
		int entretenimiento=user.aciertoEntretenimiento;
		int deporte=user.aciertoDeportes;
		
		int total=ciencia+arte+historia+geografia+entretenimiento+deporte;
		if(total==0)total=1;
		String response=ciencia+";"+arte+";"+historia+";"+geografia+";"+entretenimiento+";"+deporte+";"+total+";";
		 
		return ok(response);}
		return ok("invalido");
	}
>>>>>>> branch 'multi' of https://github.com/Arquisoft/Trivial1a.git
}
