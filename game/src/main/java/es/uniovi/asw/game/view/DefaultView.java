package es.uniovi.asw.game.view;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.Toolkit;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import es.uniovi.asw.game.aplication.Game;

/**
 * 
 * @author Grupo 1a
 * @see <a href = "https://github.com/Arquisoft/Trivial1a/" /> Git Grupo 1a </a>
 */
public class DefaultView extends View {

	private JPanel contentPane;

	public DefaultView(Game game, String name) {
		super(game, name);
	}

	/**
	 * Create the frame.
	 */
	public DefaultView() {
		setTitle("Trivial.Menú principal");
		setIconImage(Toolkit.getDefaultToolkit().getImage("img\\icono_trivial.png"));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//		setBounds(100, 100, 450, 300);
//		setLocationRelativeTo(null);
		setSize(450, 300);
		setLocationRelativeTo(null); 								//centrar en la pantalla	
		setJMenuBar(getViewMenuBar());
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
	}

	/**
	 * Launch the application.
	 */
	@Override
	public void render() {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					DefaultView frame = new DefaultView();
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
		return 0;
	}

}
