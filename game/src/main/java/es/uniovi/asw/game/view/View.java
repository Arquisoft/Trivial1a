package es.uniovi.asw.game.view;

import java.awt.GraphicsConfiguration;
import java.awt.HeadlessException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JCheckBoxMenuItem;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;

import es.uniovi.asw.game.aplication.Game;
import es.uniovi.asw.game.model.User;

/**
 * 
 * @author Grupo 1a
 * @see <a href = "https://github.com/Arquisoft/Trivial1a/" /> Git Grupo 1a </a>
 */
public abstract class View extends JFrame {

	private static final long serialVersionUID = 1L;
	
	private static Game controler;
	private String viewName;

	private static JMenuBar menuBar;
	private static JMenu menuSettings;
	private static JMenuItem mItemChangeView;
	private static JMenu mnUsuarios;
	private static JCheckBoxMenuItem chMItemSounds;
	private static JMenuItem MitemAddUsers;
	private static JMenuItem mItemReset;
	private static JMenu mnInformacin;
	private static JMenuItem mItemAcercaDe;
	private static JMenuItem mItemEstadisticas;

	/* constructores de la super clase Jframe */
	public View() throws HeadlessException {
		super();
	}

	public View(GraphicsConfiguration arg0) {
		super(arg0);
	}

	public View(String arg0, GraphicsConfiguration arg1) {
		super(arg0, arg1);
	}

	public View(String arg0) throws HeadlessException {
		super(arg0);
	}

	@SuppressWarnings("static-access")
	public View(Game controler, String name) {
		this.controler = controler;
		viewName = name;
	}

	public abstract void render();

	public abstract int getNumColores();

	protected boolean IsEmptyUsers() {
		for (User u : controler.getUsers())
			if (u != null)
				return false;
		return true;
	}

	protected void sowUserLogin() {
		UserLogin userLogin = new UserLogin(this);
		userLogin.setVisible(true);
	}

	public void notifyLostUsers(int n) {
		JOptionPane.showMessageDialog(this, "esta vista admite  " + n
				+ " usuarios menos que la anteriór.\n se eliminarán los que se excedan.");
	}

	public void updateUsers() {

	}
	
	public void showStatistics()
	{
		StatisticsView estadisticas = new StatisticsView(this);
		estadisticas.setVisible(true);
	}

	protected JMenuBar getViewMenuBar() {
		if (menuBar == null) {
			menuBar = new JMenuBar();
			menuBar.add(getMenuSettings());
			menuBar.add(getMnUsuarios());
			menuBar.add(getMnInformacin());
		}
		return menuBar;
	}

	private JMenu getMenuSettings() {
		if (menuSettings == null) {
			menuSettings = new JMenu("Ajustes");
			menuSettings.add(getMItemChangeView());
			menuSettings.add(getChMItemSounds());
			menuSettings.add(getMItemReset());
		}
		return menuSettings;
	}

	private JMenuItem getMItemChangeView() {
		if (mItemChangeView == null) {
			mItemChangeView = new JMenuItem("Cambiar Vista del juego");
			mItemChangeView.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {

					View[] possibilities = {
							ViewFactory.getTestView1(controler),
							ViewFactory.getTestView2(controler),
							ViewFactory.getDeffaultView(controler) };
					View v = (View) JOptionPane.showInputDialog(View.this,
							"Seleccione la vista deseada", "Cambiar vista",
							JOptionPane.PLAIN_MESSAGE, null, possibilities,
							ViewFactory.getTestView1(controler));
					controler.setView(v);
				}
			});
		}
		return mItemChangeView;
	}

	private JMenu getMnUsuarios() {
		if (mnUsuarios == null) {
			mnUsuarios = new JMenu("Usuarios");
			mnUsuarios.add(getMitemAddUsers());
		}
		return mnUsuarios;
	}

	private JCheckBoxMenuItem getChMItemSounds() {
		if (chMItemSounds == null) {
			chMItemSounds = new JCheckBoxMenuItem("Sonido habilitado");
			chMItemSounds.setSelected(true);
		}
		return chMItemSounds;
	}

	private JMenuItem getMitemAddUsers() {
		if (MitemAddUsers == null) {
			MitemAddUsers = new JMenuItem("a\u00F1adir o eliminar usuarios");
			MitemAddUsers.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					sowUserLogin();
				}
			});
		}
		return MitemAddUsers;
	}

	private JMenuItem getMItemReset() {
		if (mItemReset == null) {
			mItemReset = new JMenuItem("Reset");
		}
		return mItemReset;
	}

	private JMenu getMnInformacin() {
		if (mnInformacin == null) {
			mnInformacin = new JMenu("Informaci\u00F3n");
			mnInformacin.add(getMItemAcercaDe());
			mnInformacin.add(getMItemEstadisticas());
		}
		return mnInformacin;
	}

	private JMenuItem getMItemAcercaDe() {
		if (mItemAcercaDe == null) {
			mItemAcercaDe = new JMenuItem("Acerca de");
			mItemAcercaDe.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					JOptionPane.showMessageDialog(null, "Desarrollado por: Trivial1a\n"
							+ "Componentes:\nSergio Cueto Lopez de Bustamante\n"
							+ "Robert Stefanita Ene\n"
							+ "Alejandro Garcia Toriello\n"
							+ "Francisco Javier Gil Gala\n"
							+ "Diego Jaular Orgueira\n"
							+ "Ignacio Rodríguez Vázquez\n"
							+ "Andrés Velasco Fernández\n"
							+ "Jenifer Ramos Martínez\n"
							+ "Isabel del Álamo Rancaño\n"
							+ "Ingenieria Informática del Software - Escuela Ingerniería Informática\n"
							+ "Universidad de Oviedo");
				}
			});
		}
		return mItemAcercaDe;
	}
	
	private JMenuItem getMItemEstadisticas(){
		if(mItemEstadisticas == null){
			mItemEstadisticas = new JMenuItem("Estadisticas");
			mItemEstadisticas.addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent arg0) {
					showStatistics();
				}
			});
		}
		return mItemEstadisticas;
	}

	public Game getControler() {
		return controler;
	}
	
	public abstract void update();
	public abstract void showQuestion();

	@Override
	public String toString() {
		return viewName;
	}
}
