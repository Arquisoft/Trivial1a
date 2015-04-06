package es.uniovi.asw.game.view;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.Toolkit;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import es.uniovi.asw.game.aplication.Game;
import es.uniovi.asw.game.board2D.BuilderBoard2D;
import es.uniovi.asw.game.board2D.TrivialBoard;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

import javax.swing.border.LineBorder;
import javax.swing.border.CompoundBorder;
import javax.swing.border.MatteBorder;
import javax.swing.JLabel;

import java.awt.Font;

import javax.swing.SwingConstants;
import javax.swing.JButton;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

/**
 * 
 * @author Grupo 1a
 * @see <a href = "https://github.com/Arquisoft/Trivial1a/" /> Git Grupo 1a </a>
 */
public class TestView2 extends View {

	private JPanel contentPane;
	private TrivialBoard pnBoard = null;
	private JLabel labelJugador;

	public TestView2(Game game, String name) {
		super(game, name);
	}

	/**
	 * Create the frame.
	 */
	public TestView2() {
		setResizable(false);
		setTitle("TestView2");
		setIconImage(Toolkit.getDefaultToolkit().getImage("img\\icono_trivial.png"));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//		setBounds(100, 100, 800, 820);
		setSize(800, 820);
		setLocationRelativeTo(null); 								//centrar en la pantalla
		// añade a la vista el menú por defecto de la interfáz view
		setJMenuBar(getViewMenuBar());
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(null);
		contentPane.add(getPnBoard());	//agregar el panel2D
		setContentPane(contentPane);
		
		JPanel pnInformacion = new JPanel();
		pnInformacion.setBorder(new LineBorder(new Color(0, 0, 0), 2));
		pnInformacion.setBounds(10, 510, 764, 228);
		pnInformacion.setBorder(new EmptyBorder(2, 2, 2, 2 ));
		contentPane.add(pnInformacion);
		pnInformacion.setLayout(null);
		
		JPanel pPregunta = new JPanel();
		pPregunta.setBorder(new MatteBorder(2, 2, 0, 2, (Color) new Color(0, 0, 0)));
		pPregunta.setBounds(9, 11, 519, 111);
		pnInformacion.add(pPregunta);
		
		JPanel pnRespuesta1 = new JPanel();
		pnRespuesta1.setBorder(new MatteBorder(2, 2, 1, 2, (Color) new Color(0, 0, 0)));
		pnRespuesta1.setBounds(9, 122, 519, 25);
		pnInformacion.add(pnRespuesta1);
		
		JPanel pnRespuesta2 = new JPanel();
		pnRespuesta2.setBorder(new MatteBorder(2, 2, 0, 2, (Color) new Color(0, 0, 0)));
		pnRespuesta2.setBounds(9, 146, 519, 25);
		pnInformacion.add(pnRespuesta2);
		
		JPanel pnRespuesta3 = new JPanel();
		pnRespuesta3.setBorder(new MatteBorder(2, 2, 2, 2, (Color) new Color(0, 0, 0)));
		pnRespuesta3.setBounds(9, 171, 519, 25);
		pnInformacion.add(pnRespuesta3);
		
		JPanel pnRespuesta4 = new JPanel();
		pnRespuesta4.setBorder(new MatteBorder(0, 2, 2, 2, (Color) new Color(0, 0, 0)));
		pnRespuesta4.setBounds(9, 196, 519, 25);
		pnInformacion.add(pnRespuesta4);
		
		JPanel pnDerecha = new JPanel();
		pnDerecha.setBounds(538, 11, 236, 506);
		contentPane.add(pnDerecha);
		pnDerecha.setLayout(null);
		
		JPanel pnJugadorActual = new JPanel();
		pnJugadorActual.setBorder(new LineBorder(new Color(0, 0, 0)));
		pnJugadorActual.setBounds(10, 104, 216, 164);
		pnDerecha.add(pnJugadorActual);
		pnJugadorActual.setLayout(null);
		
		JLabel labelTituloJugador = new JLabel("Jugador actual ");
		labelTituloJugador.setHorizontalAlignment(SwingConstants.CENTER);
		labelTituloJugador.setBounds(10, 5, 196, 28);
		pnJugadorActual.add(labelTituloJugador);
		labelTituloJugador.setFont(new Font("Tahoma", Font.PLAIN, 23));
		
		labelJugador = new JLabel("");
		labelJugador.setBounds(58, 44, 100, 100);
		pnJugadorActual.add(labelJugador);
		
		JPanel pnDado = new JPanel();
		pnDado.setForeground(Color.WHITE);
		pnDado.setBorder(new LineBorder(new Color(0, 0, 0)));
		pnDado.setBounds(10, 279, 216, 216);
		pnDerecha.add(pnDado);
		pnDado.setLayout(null);
		
		ImageIcon icono = new ImageIcon("img/dados/dado.gif");
		JLabel labelDado = new JLabel(icono);
		labelDado.setForeground(Color.WHITE);
		labelDado.setBounds(37, 11, 145, 145);

		//labelDado.setIcon(icon);
		pnDado.add(labelDado);
		
		JButton btnTirarDado = new JButton("Tirar dado");
		btnTirarDado.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnTirarDadoMouseClicked(evt);
            }
        });
			
		btnTirarDado.setFont(new Font("Tahoma", Font.PLAIN, 15));
		btnTirarDado.setBounds(10, 167, 196, 38);
		pnDado.add(btnTirarDado);
		
		JPanel pnTitulo = new JPanel();
		pnTitulo.setBounds(10, 11, 216, 82);
		pnDerecha.add(pnTitulo);
		
		JLabel labelTitulo = new JLabel(new ImageIcon("img/titulo.png"));
		pnTitulo.add(labelTitulo);
		
		
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
			//FIXME
			BuilderBoard2D panelBoard = new BuilderBoard2D(FICHA_AMARILLA, FICHA_AZUL, FICHA_MARRON, FICHA_NARANJA, FICHA_ROSA, FICHA_VERDE);
			
			inicializarJugadores();
			
			pnBoard = panelBoard.getPanelBoard();
			pnBoard.setLocation(19, 10);
			pnBoard.addMouseListener(new java.awt.event.MouseAdapter() {
		            public void mouseClicked(java.awt.event.MouseEvent evt) {
		                panelMouseClicked(evt);
		            }
		        });
			
		}
		
		return pnBoard;
	}
	
	 private void inicializarJugadores() {

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
        ImageIcon icono = null;
			switch (jugadores[count % nPlayers]) {
			
			case JUGADOR_AMARILLA:
				break;
			case JUGADOR_AZUL:
				break;
			case JUGADOR_MARRON:	
					aux[0] = "Ficha_Marron";
					aux[1] ="Ficha_Verde";
					pnBoard.repintarTablero(aux, id);
					icono = new ImageIcon(pnBoard.getIconActualPlayer("Ficha_Verde", 100));
					labelJugador.setIcon(icono);
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
				icono = new ImageIcon(pnBoard.getIconActualPlayer("Ficha_Marron", 100));
				labelJugador.setIcon(icono);
				count++;
				break;
			default:
				break;
			}
			
	 }
	private void btnTirarDadoMouseClicked(MouseEvent evt) {
		// TODO Auto-generated method stub
		System.out.println("DADOO");
	}
	
	
	
	
	//////////////////////////////
	String[] nombreFichas= {};
	Integer[] jugadores = {};	
//	int turn = 0;
	private int count = 0;
	private int nPlayers= 0;
	//////

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
