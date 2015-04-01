package es.uniovi.asw.game.view;

import java.awt.GraphicsConfiguration;
import java.awt.HeadlessException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;

import javax.swing.JCheckBoxMenuItem;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;

import org.openqa.selenium.internal.seleniumemulation.Close;

import es.uniovi.asw.game.aplication.Game;
import es.uniovi.asw.game.model.User;

public abstract class View extends JFrame {

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

	/* constructores de la super clase Jframe */
	public View() throws HeadlessException {
		super();
		// TODO Auto-generated constructor stub
	}

	public View(GraphicsConfiguration arg0) {
		super(arg0);
		// TODO Auto-generated constructor stub
	}

	public View(String arg0, GraphicsConfiguration arg1) {
		super(arg0, arg1);
		// TODO Auto-generated constructor stub
	}

	public View(String arg0) throws HeadlessException {
		super(arg0);
		// TODO Auto-generated constructor stub
	}

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
		JOptionPane.showMessageDialog(this, "se han perdido los últimos " + n
				+ " usuarios introducidos");
	}

	public void updateUsers() {

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
							ViewFactory.getTeView2(controler),
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
		}
		return mnInformacin;
	}

	private JMenuItem getMItemAcercaDe() {
		if (mItemAcercaDe == null) {
			mItemAcercaDe = new JMenuItem("Acerca de");
			mItemAcercaDe.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					// mostrar acerca de
				}
			});
		}
		return mItemAcercaDe;
	}

	public Game getControler() {
		return controler;
	}

	@Override
	public String toString() {
		return viewName;
	}
}
