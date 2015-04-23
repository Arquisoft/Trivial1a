package es.uniovi.asw.game.board2D;

import java.awt.Color;
import java.awt.List;
import java.awt.Point;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

/**
 * 
 * @author Grupo 1a
 * @see <a href = "https://github.com/Arquisoft/Trivial1a/" /> Git Grupo 1a </a>
 */
public class BuilderBoard2D {

	private static final boolean CUNIA_AMARILLA = false;
	private static final boolean CUNIA_AZUL = false;
	private static final boolean CUNIA_MARRON = false;
	private static final boolean CUNIA_NARANJA = false;
	private static final boolean CUNIA_ROSA = false;
	private static final boolean CUNIA_VERDE = false;
	///////////////////////////
	
	private final int[] colores = 	{	3,5,8,10,13,15,18,	20,23,26,28,31,33,36,	38,41,43,46,48,51,54,	56,59,61,64,66,69,71,
    		74,77,79,82,84,87,89,	92,94,97,99,102,105,107,	110,112,115,117,120,122,125,	127,130,133,135,138,140,143,
    		145,148,150,153,156,158,161,	163,166,168,171,173,176,179,	181,184
		};
	
					//centro de cada casilla
	private final int[][] coordenadas = { 	{174,463 }, {202 ,471 }, {234 ,475 }, {263 , 475}, {294 ,471 }, {324 ,463 }, { 366,443 },  
		    		{ 401,417 }, {423 ,394 }, {442 , 368}, {456 ,341 }, {465 ,315 }, {472 ,288 }, {475 , 250},
		    		{ 471,205 }, {462 ,175 }, {450 ,148 }, {435 ,121 }, {415 ,97 }, {393 ,76 }, {358,52 },
		    		{ 321, 36}, {292 ,28 }, {262 ,25 }, {234 ,25 }, {203 , 28}, { 173, 37}, {135 , 54},
		    		{ 102, 78}, {81 ,98 }, {61 ,124 }, {46 , 150}, { 34, 180}, { 26, 209}, {24 ,250 },
		    		{26 ,287 }, {32 , 314}, {42 ,341 }, {55 ,366 }, {72 , 391}, {98 , 418}, {135 ,445 },
		    		//aristas
		    		{155,412},{171,384},{187,356},{203,328},{219,300},
		    		{346,411},{330,383},{314,355},{298,327},{282,299},
		    		{435,250},{403,250},{371,250},{339,250},{307,250},
		    		{344,87},{328,115},{312,143},{296,171},{280,199},
		    		{152,86},{168,114},{184,142},{200,170},{216,198},
		    		{60,250},{92,250},{124,250},{156,250},{188,250}
		    	};
	////////////////
    private final int MITAD_FICHA = 13;
    private final int CASILLA_CENTRAL = 72;
    private final int ZONA_BLANCA = 73;
    private final int TRANSPARENTE = 16777215;
    ///////////////
	private TrivialBoard gb2d;
	private List piezas = null;  
	
	  
	  
		public TrivialBoard getPanelBoard(){
			return gb2d;
		}
	
	
	  public BuilderBoard2D(boolean jAmarillo, boolean jAzul, boolean jMarron, boolean jNaranja, boolean jRosa, boolean jVerde){
	  
      try {
          gb2d = new TrivialBoard();
          gb2d.setBoard(new File("img/tableros/real500.png"), new File("img/tableros/cache500.png"));
          
          initBoxes();
          
          piezas = new List();
          initPieces(jAmarillo, jAzul, jMarron, jNaranja, jRosa, jVerde);
          
          gb2d.repintarTablero(piezas.getItems(), CASILLA_CENTRAL);	
                      
		   } catch (IOException ex) {
		       System.out.println(ex.getMessage());
		       System.exit(1);
		   }
	
	  }
	  
	  private void initBoxes() {
			
			for (int i = 0; i < 72; i++) {
				gb2d.addBox(i, new Color(colores[i], colores[i], colores[i]),new Point(coordenadas[i][0] - MITAD_FICHA, coordenadas[i][1] - MITAD_FICHA));
			}

			gb2d.addBox(72, new Color(68, 56, 140), new Point(250 - MITAD_FICHA,
					250 - MITAD_FICHA)); // CENTRAL
			
			gb2d.addBox(ZONA_BLANCA, new Color(255, 255, 255), new Point(0, 0)); // BLANCO

		}
	  
	@SuppressWarnings("unused")
	  private void initPieces(boolean jAmarillo, boolean jAzul, boolean jMarron, boolean jNaranja, boolean jRosa, boolean jVerde){

	    	try {
	    		int i = 0;
		    	if(jAmarillo){
		    		BufferedImage ficha = createPiece( "img/fichas/fAmarilla.png", CUNIA_AMARILLA, CUNIA_AZUL, CUNIA_MARRON, CUNIA_NARANJA, CUNIA_ROSA, CUNIA_VERDE);
		    		gb2d.addPiece( "Ficha_Amarillo", ficha );
		    		piezas.add("Ficha_Amarillo");
//		    		jugadores[i] = 1;
		    		i++;
		    	}
		    	if (jAzul) {
		    		BufferedImage ficha = createPiece( "img/fichas/fAzul.png", CUNIA_AMARILLA, CUNIA_AZUL, CUNIA_MARRON, CUNIA_NARANJA, CUNIA_ROSA, CUNIA_VERDE);
		    		gb2d.addPiece( "Ficha_Azul", ficha );
		    		piezas.add("Ficha_Azul");
//		    		jugadores[i] = 2;
		    		i++;
				}
		    	if (jMarron) {
					BufferedImage ficha = createPiece( "img/fichas/fMarron.png", CUNIA_AMARILLA, CUNIA_AZUL, CUNIA_MARRON, CUNIA_NARANJA, CUNIA_ROSA, CUNIA_VERDE);
		    		gb2d.addPiece( "Ficha_Marron", ficha );
		    		piezas.add("Ficha_Marron");
//		    		jugadores[i] = 3;
		    		i++;
				}
		    	if (jNaranja) {
					BufferedImage ficha = createPiece( "img/fichas/fNaranja.png", CUNIA_AMARILLA, CUNIA_AZUL, CUNIA_MARRON, CUNIA_NARANJA, CUNIA_ROSA, CUNIA_VERDE);
		    		gb2d.addPiece( "Ficha_Naranja", ficha );
		    		piezas.add("Ficha_Naranja");
//		    		jugadores[i] = 4;
		    		i++;
				}
		    	if (jRosa) {
					BufferedImage ficha = createPiece( "img/fichas/fRosa.png", CUNIA_AMARILLA, CUNIA_AZUL, CUNIA_MARRON, CUNIA_NARANJA, CUNIA_ROSA, CUNIA_VERDE);
		    		gb2d.addPiece( "Ficha_Rosa", ficha );
		    		piezas.add("Ficha_Rosa");
//		    		jugadores[i] = 5;
		    		i++;
				}
		    	if (jVerde) {
					BufferedImage ficha = createPiece( "img/fichas/fVerde.png", CUNIA_AMARILLA, CUNIA_AZUL, CUNIA_MARRON, CUNIA_NARANJA, CUNIA_ROSA, CUNIA_VERDE);
		    		gb2d.addPiece( "Ficha_Verde", ficha );
		    		piezas.add("Ficha_Verde");
//		    		jugadores[i] = 6;
		    		i++;
				}
	  		} catch (IOException e) {
	  			e.printStackTrace();
	  		}
	    }
	  
	  private BufferedImage createPiece(String path, boolean cAmarillo, boolean cAzul, boolean cMarron, boolean cNaranja, boolean cRosa, boolean cVerde ) {

	      	BufferedImage fichaRet = null;
	      	BufferedImage relleno = null;
	   	
	      	try {

	  			fichaRet = ImageIO.read(new File(path));
	  			relleno = ImageIO.read(new File("img/cunias/cNegro.png"));
	  			
		      	if(cAmarillo){
		          	BufferedImage cunia = ImageIO.read(new File("img/cunias/cAmarillo.png"));
		      		fichaRet = addWedge(fichaRet, cunia);
		      	}
		      	if (cAzul) {
		      		BufferedImage cunia = ImageIO.read(new File("img/cunias/cAzul.png"));
		      		fichaRet =addWedge(fichaRet, cunia);
				}
		      	if (cMarron) {
					BufferedImage cunia = ImageIO.read(new File("img/cunias/cMarron.png"));
					fichaRet =addWedge(fichaRet, cunia);
				}
		      	if (cNaranja) {
					BufferedImage cunia = ImageIO.read(new File("img/cunias/cNaranja.png"));
					fichaRet =addWedge(fichaRet, cunia);
				}
		      	if (cRosa) {
					BufferedImage cunia = ImageIO.read(new File("img/cunias/cRosa.png"));
					fichaRet =addWedge(fichaRet, cunia);
				}
		      	if (cVerde) {
					BufferedImage cunia = ImageIO.read(new File("img/cunias/cVerde.png"));
					fichaRet =addWedge(fichaRet, cunia);
				}
		      	
	      	} catch (IOException e) {
				e.printStackTrace();
				System.out.println("ERRRRROOOOOOOOOOOR");
			}
	  	
	        try {
				ImageIO.write(fichaRet , "png", new File("img/prueba1.png"));  
			} catch (IOException e) {
				e.printStackTrace();
			} 
	      	
	  	//rellenar los huecos transparentes
	  			for (int i = 0; i < fichaRet.getHeight(); i++) {
	  				for (int j = 0; j < fichaRet.getWidth(); j++) {
	  					
	  					int colorFicha = fichaRet.getRGB(i, j);
	  					int colorNegro = relleno.getRGB(i, j);
	  					if(colorFicha==TRANSPARENTE  && colorNegro != TRANSPARENTE ){ //donde hay transparente en quesitos se rellena
	  						//fichaRet.setRGB(i, j, -16777216); //negro
	  						fichaRet.setRGB(i, j, colorNegro);
	  					}

	  				}
	  			}
	  	
	  			return fichaRet;
	  			
		}//fin metod
	  
		
	  private BufferedImage addWedge(BufferedImage ficha, BufferedImage cunia) {
			
			for (int i = 0; i < cunia.getHeight(); i++) {
					for (int j = 0; j < cunia.getWidth(); j++) {
						int colorFicha = ficha.getRGB(i, j);
						int colorCunia = cunia.getRGB(i, j);

						if(colorFicha==TRANSPARENTE && colorCunia != TRANSPARENTE)	//donde hay cunia se pinta
							ficha.setRGB(i, j, colorCunia);
					}
				}//fin fors
			
			return ficha;
		}
}