package es.uniovi.asw.game.view;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import es.uniovi.asw.game.aplication.Game;
import es.uniovi.asw.game.board2D.BuilderBoard2D;
import es.uniovi.asw.game.board2D.TrivialBoard;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

public class TestView2 extends View {

	private JPanel contentPane;

	public TestView2(Game game, String name) {
		super(game, name);
	}

	/**
	 * Create the frame.
	 */
	public TestView2() {
		setTitle("TestView2");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 800, 600);
		// añade a la vista el menú por defecto de la interfáz view
		setJMenuBar(getViewMenuBar());
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(null);
		setContentPane(contentPane);
		
		contentPane.add(getPnBoard());	//agregar el panel2D
		
	}

	/**
	 * Launch the application.
	 */
	@Override
	public void render() {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TestView2 frame = new TestView2();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	@Override
	public int getNumColores() {
		// TODO Auto-generated method stub
		return 5;
	}
	
	
	private JPanel getPnBoard() {
		
		if(pnBoard == null){
		/*	pnBoard = new JPanel();
			pnBoard.setBackground(Color.GRAY);
			pnBoard.setSize(500, 500);
			pnBoard.setBounds(10, 10, 510, 510);
		*/
			//FIXME
			BuilderBoard2D panelBoard = new BuilderBoard2D(FICHA_AMARILLA, FICHA_AZUL, FICHA_MARRON, FICHA_NARANJA, FICHA_ROSA, FICHA_VERDE);
			
			inicializarJugadores();
			pnBoard = panelBoard.getPanelBoard();
			
			pnBoard.addMouseListener(new java.awt.event.MouseAdapter() {
		            public void mouseClicked(java.awt.event.MouseEvent evt) {
		                panelMouseClicked(evt);
		            }
		        });
			
		}
		
		return pnBoard;
	}
	
	 private void inicializarJugadores() {
		// TODO Auto-generated method stub

		 List<Integer> listaJugadores = new ArrayList<Integer>();
		 List<String> listaNombreFichas = new ArrayList<String>();
		 
		 int i = 0;
		if(FICHA_AMARILLA){
			
			listaJugadores.add(1);
			listaNombreFichas.add("Ficha_Amarillo");
			i++;
		} 
		if(FICHA_AZUL){
			listaJugadores.add(2);
			listaNombreFichas.add("Ficha_Azul");
			i++;
		}
		if(FICHA_MARRON){
			listaJugadores.add(3);
			listaNombreFichas.add("Ficha_Marron");
			i++;
		}
		if (FICHA_NARANJA) {
			listaJugadores.add(4);
			listaNombreFichas.add("Ficha_Naranja");
			i++;	
		}
		if (FICHA_ROSA) {
			listaJugadores.add(5);
			listaNombreFichas.add("Ficha_Rosa");
			i++;
		}
		if (FICHA_VERDE) {
			listaJugadores.add(6);
			listaNombreFichas.add("Ficha_Verde");
			i++;
		}
		 
		nPlayers = i;
		jugadores = (listaJugadores.toArray(jugadores));
		nombreFichas = listaNombreFichas.toArray(nombreFichas);
		 
	}

	private void panelMouseClicked(java.awt.event.MouseEvent evt) {
	 
			int id = pnBoard.getBoxId(evt.getPoint());
	    	System.out.println(id);
	    	
	    	if(id==73)
	    		return;
	    	
        String[] aux = new String[nPlayers];
	        
			switch (jugadores[count % nPlayers]) {
			
			case JUGADOR_AMARILLA:
				break;
			case JUGADOR_AZUL:
				break;
			case JUGADOR_MARRON:	
					aux[0] = "Ficha_Marron";
					aux[1] ="Ficha_Verde";
					pnBoard.repintarTablero(aux, id);
					count++;
				break;
			case JUGADOR_NARANJA:
				break;
			case JUGADOR_ROSA:
				break;
			case JUGADOR_VERDE:
				aux[0] = "Ficha_Verde";
				aux[1] = "Ficha_Marron";
				pnBoard.repintarTablero(aux, id);
				count++;
				break;
			default:
				break;
			}
			
	 }
	
	
	
	
	//////////////////////////////
	String[] nombreFichas= {};
	Integer[] jugadores = {};	
//	int turn = 0;
	private int count = 0;
	private int nPlayers= 0;
	//////
	private TrivialBoard pnBoard = null;
	//////////////
	private static final boolean FICHA_AMARILLA = false;
	private static final boolean FICHA_AZUL = false;
	private static final boolean FICHA_MARRON = true;
	private static final boolean FICHA_NARANJA = false;
	private static final boolean FICHA_ROSA = false;
	private static final boolean FICHA_VERDE = true;
////////////////////
	private static final int JUGADOR_AMARILLA = 1;
	private static final int JUGADOR_AZUL = 2;
	private static final int JUGADOR_MARRON = 3;
	private static final int JUGADOR_NARANJA = 4;
	private static final int JUGADOR_ROSA = 5;
	private static final int JUGADOR_VERDE = 6;
}
