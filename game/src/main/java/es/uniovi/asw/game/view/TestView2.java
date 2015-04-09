package es.uniovi.asw.game.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.SwingConstants;
import javax.swing.border.BevelBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.border.MatteBorder;

import es.uniovi.asw.game.aplication.Game;
import es.uniovi.asw.game.board2D.BuilderBoard2D;
import es.uniovi.asw.game.board2D.TrivialBoard;
import es.uniovi.asw.game.model.Answer;
import es.uniovi.asw.game.model.Question;

/**
 * 
 * @author Grupo 1a
 * @see <a href = "https://github.com/Arquisoft/Trivial1a/" /> Git Grupo 1a </a>
 */
public class TestView2 extends View {
	
	private static final long serialVersionUID = 1L;
	
	private static final boolean FICHA_AMARILLA = true;
	private static final boolean FICHA_AZUL = true;
	private static final boolean FICHA_MARRON =  true;
	private static final boolean FICHA_NARANJA = true;
	private static final boolean FICHA_ROSA = true;
	private static final boolean FICHA_VERDE = true;
////////////////////
	private static final int JUGADOR_VERDE = 0;
	private static final int JUGADOR_MARRON = 1;
	private static final int JUGADOR_AMARILLA = 2;
	private static final int JUGADOR_AZUL = 3;
	private static final int JUGADOR_NARANJA = 4;
	private static final int JUGADOR_ROSA = 5;
	

	private JPanel contentPane;
	private TrivialBoard pnBoard = null;
	private JLabel labelJugador;
	private JLabel labelTituloJugador;
	private Question currentQuestion;
	private JButton btnTirarDado;
	private JPanel pnDado;
	private JButton btIniciar;
	private JLabel labelDado;
	private JTextArea txQuestion;
	private JPanel answersPanel;
	private boolean fichasJugadores[];
	private int[] nexPositions;
	
	
	
	String[] nombreFichas= {};

	public TestView2(Game game, String name) {
		super(game, name);
	}

	/**
	 * Create the frame.
	 */
	public TestView2() {
		fichasJugadores = new boolean[6];
		setResizable(false);
		setTitle("TestView2");
		setIconImage(Toolkit.getDefaultToolkit().getImage("img\\icono_trivial.png"));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//		setBounds(100, 100, 800, 820);
		setSize(800, 771);
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
		pPregunta.setLayout(new BorderLayout(0, 0));
		
		txQuestion = new JTextArea();
		txQuestion.setEditable(false);
		pPregunta.add(txQuestion, BorderLayout.CENTER);
		
		JPanel pnDerecha = new JPanel();
		pnDerecha.setBounds(538, 11, 236, 506);
		contentPane.add(pnDerecha);
		pnDerecha.setLayout(null);
		
		JPanel pnJugadorActual = new JPanel();
		pnJugadorActual.setBorder(new LineBorder(new Color(0, 0, 0)));
		pnJugadorActual.setBounds(10, 104, 216, 164);
		pnDerecha.add(pnJugadorActual);
		pnJugadorActual.setLayout(null);
		
		labelTituloJugador = new JLabel("Jugador actual ");
		labelTituloJugador.setHorizontalAlignment(SwingConstants.CENTER);
		labelTituloJugador.setBounds(10, 5, 196, 28);
		pnJugadorActual.add(labelTituloJugador);
		labelTituloJugador.setFont(new Font("Tahoma", Font.PLAIN, 23));
		
		labelJugador = new JLabel("");
		labelJugador.setHorizontalAlignment(SwingConstants.CENTER);
		labelJugador.setBounds(10, 44, 196, 100);
		pnJugadorActual.add(labelJugador);
		
		ImageIcon icono = new ImageIcon("img/dados/dado.gif");
		labelDado = new JLabel(icono);
		labelDado.setForeground(Color.BLACK);
		labelDado.setBounds(37, 11, 145, 145);
		
		pnDado = new JPanel();
		pnDado.setForeground(Color.WHITE);
		pnDado.setBorder(new LineBorder(new Color(0, 0, 0)));
		pnDado.setBounds(10, 279, 216, 216);
		pnDerecha.add(pnDado);
		pnDado.setLayout(null);

		//labelDado.setIcon(icon);
		pnDado.add(labelDado);
		
		JPanel pnTitulo = new JPanel();
		pnTitulo.setBounds(10, 11, 216, 82);
		pnDerecha.add(pnTitulo);
		
		JLabel labelTitulo = new JLabel(new ImageIcon("img/titulo.png"));
		pnTitulo.add(labelTitulo);
		
		btnTirarDado = new JButton("Tirar dado");
		btnTirarDado.setEnabled(false);
		btnTirarDado.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnTirarDadoMouseClicked(evt);
            }
        });
			
		btnTirarDado.setFont(new Font("Tahoma", Font.PLAIN, 15));
		btnTirarDado.setBounds(10, 167, 196, 38);
		pnDado.add(btnTirarDado);
		
		btIniciar = new JButton("");
		btIniciar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(IsEmptyUsers()){
					JOptionPane.showMessageDialog(TestView2.this,
							"Es necesario definir algún usuario antes de comenzar la partida.");
					sowUserLogin();
				}
				else{
						inicializarJugadores();
						labelTituloJugador.setText(getControler().getCurrentUser().getName());
						setUserIcon();
						btnTirarDado.setEnabled(true);
						btIniciar.setEnabled(false);
					}
				
			}
		});
		btIniciar.setIcon(new ImageIcon("img\\play.png"));
		btIniciar.setBounds(538, 11, 226, 210);
		pnInformacion.add(btIniciar);
		
		answersPanel = getAnswersPanel();
		pnInformacion.add(answersPanel);
	}
	
	private JPanel getAnswersPanel()
	{
		JPanel panel = new JPanel();
		panel.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		panel.setBounds(9, 122, 519, 95);
		panel.setLayout(new GridLayout(0, 1, 0, 0));
		return panel;
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
		return 6;
	}
	
	
	private JPanel getPnBoard() {
		
		if(pnBoard == null){
			//FIXME
			BuilderBoard2D panelBoard = new BuilderBoard2D(FICHA_AMARILLA, FICHA_AZUL, FICHA_MARRON, FICHA_NARANJA, FICHA_ROSA, FICHA_VERDE);
			
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

		 List<String> listaNombreFichas = new ArrayList<String>();
		 for(int i=0; i< getControler().getNumUsers();i++)
			 fichasJugadores[i]=true;
		 int i = 0;
		if(fichasJugadores[0]){
			listaNombreFichas.add("Ficha_Verde");
			i++;
		} 
		if(fichasJugadores[1]){
			listaNombreFichas.add("Ficha_Marron");
			i++;
		}
		if(fichasJugadores[2]){
			listaNombreFichas.add("Ficha_Amarillo");
			i++;
		}
		if (fichasJugadores[3]) {
			listaNombreFichas.add("Ficha_Azul");
			i++;	
		}
		if (fichasJugadores[4]) {
			listaNombreFichas.add("Ficha_Naranja");
			i++;
		}
		if (fichasJugadores[5]) {
			listaNombreFichas.add("Ficha_Rosa");
			i++;
		}
		 
		nPlayers = i;
		nombreFichas = listaNombreFichas.toArray(nombreFichas);
		 
	}

	private void panelMouseClicked(java.awt.event.MouseEvent evt) {
	 
		if( !btnTirarDado.isEnabled() ){
			
			int id = pnBoard.getBoxId(evt.getPoint());
			if(id==73)
	    		return;
			boolean correct = false/*poner a false cuando funcione getNextPositions()*/;
			System.out.println("id: "+id);
			//descomentar más adelante
	    	for(int i=0; i< nexPositions.length;i++)
	    	{
	    		if(id == nexPositions[i])
	    		correct = true;
	    	}
	    	if(correct)
	    	{
	    		 String[] aux = new String[nPlayers];
	    	       
	 			switch (getControler().getCurrebtUserIndex()) {
	 			
	 			case JUGADOR_VERDE:
	 				aux = moverFicha(JUGADOR_VERDE);
	 				break;
	 			case JUGADOR_MARRON:	
	 				aux = moverFicha(JUGADOR_MARRON);
 				break;
	 			case JUGADOR_AMARILLA:
	 				aux = moverFicha(JUGADOR_AMARILLA);
	 				break;
	 			case JUGADOR_AZUL:
	 				aux = moverFicha(JUGADOR_AZUL);
	 				break;
	 			case JUGADOR_NARANJA:
	 				aux = moverFicha(JUGADOR_NARANJA);
	 				break;
	 			case JUGADOR_ROSA:
	 				aux = moverFicha(JUGADOR_ROSA);
	 				break;
	 			default:
	 				break;
	 			}
	 			
	 			try{
	 				getControler().moverUser(id);  //FIXME -- MOVER USER  id correcta¿
	 				
	 				pnBoard.repintarTablero(aux, id);
	 			}catch(Exception e){}
	 			nexPositions = null;
	 			showQuestion(id);
	    	}
	    	else
	    		JOptionPane.showMessageDialog(TestView2.this,"posición no válida");	
		}
		
		
	 }
	
	private String[] moverFicha(int index)
	{
		int count =1;
		String[] aux = new String[nPlayers];
		 
		 for(int i=0; i<aux.length ; i++)
			 	if(i!=index)
			 	{
			 		aux[count]=nombreFichas[i];
			 		count++;
			 	}
		
		 aux[0]=nombreFichas[index];
		 return aux;
		 
	}

	
	private void btnTirarDadoMouseClicked(MouseEvent evt) {
		
		int dado = getControler().tirarDado();
		btnTirarDado.setEnabled(false);
		labelDado.setIcon(new ImageIcon("img/dados/"+dado+".png"));
		
		//mostrar casillas posbles??
//		nexPositions = getControler().calculateNextPositions();
		int[] aux = getControler().calculateNextPositions();
		
		for (int i = 0 ; i < aux.length; i++){	//traducir a casillas graficas 
			
			if(aux[i] == 0){
				aux[i]= 72;
				System.out.println("\t"+aux[i]);
			}else if(aux[i] == 1){
				aux[i]= 41;
				System.out.println("\t"+aux[i]);
			}else if(aux[i]>=1 && aux[i]<=42){ 		//circunferencia
				aux[i]= (aux[i]-2%42);
				System.out.println("\t"+aux[i]);
			}else if(aux[i]>=43 && aux[i]<=71){
				aux[i]= aux[i]-1;
				System.out.println("\t"+aux[i]);
			}
		
		}
		nexPositions = aux;
		
	}
		
//	int turn = 0;
	private int nPlayers= 0;
	private JCheckBox chckbxNewCheckBox;
	//////

	//se llama cuando el usuario contesta a una pregunta
	@Override
	public void update() 
	{
		if(getControler().isWinner()){
			//fin del juego
		}
		String answer="";
		for(Component ch : answersPanel.getComponents())
			if(((JCheckBox) ch).isSelected())
				answer = ((JCheckBox) ch).getText();
		System.out.println(answer);
			
		if(getControler().isAcorrectAnswer(answer))
			JOptionPane.showMessageDialog(TestView2.this,"respuesta correcta, sigues jugando");
		else
			JOptionPane.showMessageDialog(TestView2.this,"Lo siento, has fallado");
		
		labelTituloJugador.setText(getControler().getCurrentUser().getName());
		setUserIcon();
		txQuestion.setText("");
		answersPanel.removeAll();
		labelDado.setText("");
		labelDado.setIcon(new ImageIcon("img/dados/dado.gif"));
		btnTirarDado.setEnabled(true);
	}
	
	private void setUserIcon()
	{
		ImageIcon icono = null;
		 
		 switch (getControler().getCurrebtUserIndex()) 
		 {
		 case 0:
			 icono = new ImageIcon(pnBoard.getIconActualPlayer("Ficha_Verde", 100));
			 break;
			case 1:
				icono = new ImageIcon(pnBoard.getIconActualPlayer("Ficha_Marron", 100));
				break;
			case 2:
				icono = new ImageIcon(pnBoard.getIconActualPlayer("Ficha_Amarillo", 100));
				break;
			case 3:
				icono = new ImageIcon(pnBoard.getIconActualPlayer("Ficha_Azul", 100));
				break;
			case 4:
				icono = new ImageIcon(pnBoard.getIconActualPlayer("Ficha_Naranja", 100));
				break;
			case 5:
				icono = new ImageIcon(pnBoard.getIconActualPlayer("Ficha_Rosa", 100));
				break;
			default:
				break;
		}
		 labelJugador.setIcon(icono);
	}

	@Override
	public void showQuestion() {
		
	}
	
	public void showQuestion(int index) {
		//hallar la categoría de la pregunta con el index
		currentQuestion = getControler().getQuestion(/*categoria*/"deportes"); //FIXME categorias de las preguntas
		txQuestion.setText(currentQuestion.getQuestion());
		for(Answer a : currentQuestion.getAnswers()){
			JCheckBox ch = new JCheckBox(a.getAnswer());
			ch.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					update();
				}
			});
			answersPanel.add(ch);
			}
	}
}
