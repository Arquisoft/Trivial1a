package es.uniovi.asw.game.view;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Toolkit;

import javax.swing.JButton;
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

	private static final long serialVersionUID = 1L;
	
	private JPanel contentPane;
	private JButton btnNewButton;
	private JButton button;
	private JButton button_1;
	private JButton button_2;
	private JButton button_3;
	private JButton button_4;
	private JButton button_5;
	private JButton button_6;
	private JButton button_7;
	private JButton button_8;
	private JButton button_9;
	private JButton button_10;
	private JButton button_11;
	private JButton button_12;
	private JButton button_13;
	private JButton button_14;
	private JButton button_15;
	private JButton button_16;
	private JButton button_17;
	private JButton button_18;
	private JButton button_19;

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
		setSize(525, 481);
		setLocationRelativeTo(null); 								//centrar en la pantalla	
		setJMenuBar(getViewMenuBar());
		contentPane = new JPanel();
		contentPane.setBackground(Color.GRAY);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		contentPane.add(getBtnNewButton());
		contentPane.add(getButton());
		contentPane.add(getButton_1());
		contentPane.add(getButton_2());
		contentPane.add(getButton_3());
		contentPane.add(getButton_4());
		contentPane.add(getButton_5());
		contentPane.add(getButton_6());
		contentPane.add(getButton_7());
		contentPane.add(getButton_8());
		contentPane.add(getButton_9());
		contentPane.add(getButton_10());
		contentPane.add(getButton_11());
		contentPane.add(getButton_12());
		contentPane.add(getButton_13());
		contentPane.add(getButton_14());
		contentPane.add(getButton_15());
		contentPane.add(getButton_16());
		contentPane.add(getButton_17());
		contentPane.add(getButton_18());
		contentPane.add(getButton_19());
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
		return 0;
	}

	@Override
	public void update() {
		
	}

	@Override
	public void showQuestion() {
		
	}
	private JButton getBtnNewButton() {
		if (btnNewButton == null) {
			btnNewButton = new JButton("");
			btnNewButton.setBackground(Color.GREEN);
			btnNewButton.setBounds(10, 11, 89, 75);
		}
		return btnNewButton;
	}
	private JButton getButton() {
		if (button == null) {
			button = new JButton("");
			button.setBounds(109, 11, 89, 75);
		}
		return button;
	}
	private JButton getButton_1() {
		if (button_1 == null) {
			button_1 = new JButton("");
			button_1.setBackground(Color.BLUE);
			button_1.setBounds(208, 11, 89, 75);
		}
		return button_1;
	}
	private JButton getButton_2() {
		if (button_2 == null) {
			button_2 = new JButton("");
			button_2.setBackground(Color.RED);
			button_2.setBounds(307, 11, 89, 75);
		}
		return button_2;
	}
	private JButton getButton_3() {
		if (button_3 == null) {
			button_3 = new JButton("");
			button_3.setBackground(Color.GREEN);
			button_3.setBounds(406, 11, 89, 75);
		}
		return button_3;
	}
	private JButton getButton_4() {
		if (button_4 == null) {
			button_4 = new JButton("");
			button_4.setBackground(Color.RED);
			button_4.setBounds(10, 97, 89, 75);
		}
		return button_4;
	}
	private JButton getButton_5() {
		if (button_5 == null) {
			button_5 = new JButton("");
			button_5.setBackground(Color.BLUE);
			button_5.setBounds(10, 183, 89, 75);
		}
		return button_5;
	}
	private JButton getButton_6() {
		if (button_6 == null) {
			button_6 = new JButton("");
			button_6.setBackground(Color.GREEN);
			button_6.setBounds(10, 269, 89, 75);
		}
		return button_6;
	}
	private JButton getButton_7() {
		if (button_7 == null) {
			button_7 = new JButton("");
			button_7.setBackground(Color.RED);
			button_7.setBounds(10, 355, 89, 75);
		}
		return button_7;
	}
	private JButton getButton_8() {
		if (button_8 == null) {
			button_8 = new JButton("");
			button_8.setBackground(Color.BLUE);
			button_8.setBounds(109, 355, 89, 75);
		}
		return button_8;
	}
	private JButton getButton_9() {
		if (button_9 == null) {
			button_9 = new JButton("");
			button_9.setBackground(Color.GREEN);
			button_9.setBounds(208, 355, 89, 75);
		}
		return button_9;
	}
	private JButton getButton_10() {
		if (button_10 == null) {
			button_10 = new JButton("");
			button_10.setBackground(Color.RED);
			button_10.setBounds(307, 355, 89, 75);
		}
		return button_10;
	}
	private JButton getButton_11() {
		if (button_11 == null) {
			button_11 = new JButton("");
			button_11.setBackground(Color.BLUE);
			button_11.setBounds(406, 355, 89, 75);
		}
		return button_11;
	}
	private JButton getButton_12() {
		if (button_12 == null) {
			button_12 = new JButton("");
			button_12.setBackground(Color.GREEN);
			button_12.setBounds(406, 269, 89, 75);
		}
		return button_12;
	}
	private JButton getButton_13() {
		if (button_13 == null) {
			button_13 = new JButton("");
			button_13.setBackground(Color.RED);
			button_13.setBounds(406, 183, 89, 75);
		}
		return button_13;
	}
	private JButton getButton_14() {
		if (button_14 == null) {
			button_14 = new JButton("");
			button_14.setBackground(Color.BLUE);
			button_14.setBounds(406, 97, 89, 75);
		}
		return button_14;
	}
	private JButton getButton_15() {
		if (button_15 == null) {
			button_15 = new JButton("");
			button_15.setBackground(Color.GREEN);
			button_15.setBounds(208, 97, 89, 75);
		}
		return button_15;
	}
	private JButton getButton_16() {
		if (button_16 == null) {
			button_16 = new JButton("");
			button_16.setBackground(Color.YELLOW);
			button_16.setBounds(208, 183, 89, 75);
		}
		return button_16;
	}
	private JButton getButton_17() {
		if (button_17 == null) {
			button_17 = new JButton("");
			button_17.setBackground(Color.GREEN);
			button_17.setBounds(109, 183, 89, 75);
		}
		return button_17;
	}
	private JButton getButton_18() {
		if (button_18 == null) {
			button_18 = new JButton("");
			button_18.setBackground(Color.BLUE);
			button_18.setBounds(307, 183, 89, 75);
		}
		return button_18;
	}
	private JButton getButton_19() {
		if (button_19 == null) {
			button_19 = new JButton("");
			button_19.setBackground(Color.RED);
			button_19.setBounds(208, 269, 89, 75);
		}
		return button_19;
	}
}
