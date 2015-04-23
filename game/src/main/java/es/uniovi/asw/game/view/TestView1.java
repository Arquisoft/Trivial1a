package es.uniovi.asw.game.view;

import java.awt.EventQueue;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import es.uniovi.asw.game.aplication.Game;

/**
 * 
 * @author Grupo 1a
 * @see <a href = "https://github.com/Arquisoft/Trivial1a/" /> Git Grupo 1a </a>
 */
@SuppressWarnings("serial")
public class TestView1 extends View {

	private JPanel contentPane;
	private JButton btnNewButton;
	private JLabel lblNewLabel;

	public TestView1(Game game, String name) {
		super(game, name);
	}

	/**
	 * Create the frame.
	 */
	public TestView1() {
		setTitle("Juego Trivial. Menu principal");
		setIconImage(Toolkit.getDefaultToolkit().getImage("img\\icono_trivial.png"));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//		setBounds(100, 100, 693, 469);
		setSize(693, 469);
		setLocationRelativeTo(null); 								//centrar en la pantalla	
		
		// añade a la vista el menú por defecto de la interfáz view
		setJMenuBar(getViewMenuBar());
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		contentPane.add(getLblNewLabel());
		contentPane.add(getBtnNewButton());
	}

	@Override
	public void render() {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TestView1 frame = new TestView1();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	@Override
	public int getNumColores() {
		return 2;
	}

	private JLabel getLblNewLabel() {
		if (lblNewLabel == null) {
			lblNewLabel = new JLabel("");
			lblNewLabel.setIcon(new ImageIcon(
					"img\\Trivial_Pursuit_by_muttormatt.jpg"));
			lblNewLabel.setBounds(10, 11, 657, 408);
		}
		return lblNewLabel;
	}

	private JButton getBtnNewButton() {
		if (btnNewButton == null) {
			btnNewButton = new JButton("");
			btnNewButton.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					if (IsEmptyUsers()) {
						JOptionPane
								.showMessageDialog(TestView1.this,
										"Es necesario definir algún usuario antes de comenzar la partida.");
						sowUserLogin();
					}
				}
			});
			btnNewButton.setIcon(new ImageIcon(
					"img\\play-now-gardenpartyworld-button.png"));
			btnNewButton.setBounds(210, 191, 272, 193);
		}
		return btnNewButton;
	}

	@Override
	public void update() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void showQuestion() {
		// TODO Auto-generated method stub
		
	}
}
