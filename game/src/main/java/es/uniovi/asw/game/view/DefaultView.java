package es.uniovi.asw.game.view;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import es.uniovi.asw.game.aplication.Game;

public class DefaultView extends View {

	private JPanel contentPane;

	public DefaultView(Game game, String name) {
		super(game, name);
	}

	/**
	 * Create the frame.
	 */
	public DefaultView() {
		setTitle("DefaultView");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
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
