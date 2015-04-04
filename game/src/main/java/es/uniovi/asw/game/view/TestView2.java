package es.uniovi.asw.game.view;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.Toolkit;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import es.uniovi.asw.game.aplication.Game;

public class TestView2 extends View {

	private JPanel contentPane;

	public TestView2(Game game, String name) {
		super(game, name);
	}

	/**
	 * Create the frame.
	 */
	public TestView2() {
		setTitle("Trivial.Vista 2");
		setIconImage(Toolkit.getDefaultToolkit().getImage("img\\icono_trivial.png"));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		// añade a la vista el menú por defecto de la interfáz view
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

}
