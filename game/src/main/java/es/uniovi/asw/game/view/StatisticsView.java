package es.uniovi.asw.game.view;

import java.awt.BorderLayout;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.CardLayout;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import java.awt.Font;
import java.awt.Color;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JButton;
import javax.swing.JTextField;
import java.awt.GridLayout;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Toolkit;

public class StatisticsView extends JFrame {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private View parentView;

	private JPanel contentPane;
	private JPanel pnPrincipal;
	private JLabel lblTitulo;
	private JScrollPane scrollpnTabla;
	private JTable tablaUsuarios;
	private JPanel pnUsuario;
	private JPanel pnlNombre;
	private JLabel lblUsuario;
	private JTextField txtUsuario;
	private JPanel pnlEstadistica;
	private JLabel lblCatDificil;
	private JLabel lblAcertadas;
	private JLabel lblFalladas;
	private JTextField txtAcertadas;
	private JTextField txtFalladas;
	private JTextField txtCatDificil;
	private JButton btnAtrs;

	/**
	 * Create the frame.
	 */
	public StatisticsView(View parentView) {
		
		setTitle("Estad\u00EDsticas sobre Trivial");
		setIconImage(Toolkit.getDefaultToolkit().getImage("img\\icono_trivial.png"));
		this.parentView = parentView;
//		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//		setLocationRelativeTo(null);
//		setBounds(100, 100, 557, 400);
		setSize(557, 400);
		setLocationRelativeTo(null); 								//centrar en la pantalla	
		contentPane = new JPanel();
		contentPane.setBackground(Color.ORANGE);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);	
		contentPane.setLayout(new CardLayout(0, 0));
		contentPane.add(getPnPrincipal(), "Panel Principal");
		contentPane.add(getPnUsuario(), "Panel Usuario");
	}

	private JPanel getPnPrincipal() {
		if (pnPrincipal == null) {
			pnPrincipal = new JPanel();
			pnPrincipal.setBackground(Color.ORANGE);
			pnPrincipal.setForeground(Color.WHITE);
			pnPrincipal.setLayout(new BorderLayout(0, 0));
			pnPrincipal.add(getLblTitulo(), BorderLayout.NORTH);
			pnPrincipal.add(getScrollpnTabla(), BorderLayout.CENTER);
		}
		return pnPrincipal;
	}
	private JLabel getLblTitulo() {
		if (lblTitulo == null) {
			lblTitulo = new JLabel("Usuarios que han jugado");
			lblTitulo.setBackground(Color.WHITE);
			lblTitulo.setForeground(Color.BLACK);
			lblTitulo.setFont(new Font("Nyala", Font.PLAIN, 35));
			lblTitulo.setHorizontalAlignment(SwingConstants.CENTER);
		}
		return lblTitulo;
	}
	private JScrollPane getScrollpnTabla() {
		if (scrollpnTabla == null) {
			scrollpnTabla = new JScrollPane();
			scrollpnTabla.setViewportView(getTablaUsuarios());
		}
		return scrollpnTabla;
	}
	private JTable getTablaUsuarios() {
		if (tablaUsuarios == null) {
			tablaUsuarios = new JTable();
			tablaUsuarios.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent arg0) {
					//Paso a la siguiente "ventana" que es la del usuario 
					//propio con sus estadísticas
					((CardLayout) contentPane.getLayout()).show(contentPane, "Panel Usuario");
				}
			});
		}
		return tablaUsuarios;
	}
	private JPanel getPnUsuario() {
		if (pnUsuario == null) {
			pnUsuario = new JPanel();
			pnUsuario.setBackground(Color.ORANGE);
			pnUsuario.setLayout(new BorderLayout(0, 0));
			pnUsuario.add(getPnlNombre(), BorderLayout.NORTH);
			pnUsuario.add(getPnlEstadistica(), BorderLayout.CENTER);
			pnUsuario.add(getBtnAtrs(), BorderLayout.SOUTH);
		}
		return pnUsuario;
	}
	private JPanel getPnlNombre() {
		if (pnlNombre == null) {
			pnlNombre = new JPanel();
			pnlNombre.setBackground(Color.ORANGE);
			pnlNombre.add(getLblUsuario());
			pnlNombre.add(getTxtUsuario());
		}
		return pnlNombre;
	}
	private JLabel getLblUsuario() {
		if (lblUsuario == null) {
			lblUsuario = new JLabel("Usuario:");
			lblUsuario.setFont(new Font("Nyala", Font.PLAIN, 35));
		}
		return lblUsuario;
	}
	private JTextField getTxtUsuario() {
		if (txtUsuario == null) {
			txtUsuario = new JTextField();
			txtUsuario.setFont(new Font("Nyala", Font.PLAIN, 35));
			txtUsuario.setColumns(10);
		}
		return txtUsuario;
	}
	private JPanel getPnlEstadistica() {
		if (pnlEstadistica == null) {
			pnlEstadistica = new JPanel();
			pnlEstadistica.setBackground(Color.ORANGE);
			pnlEstadistica.setLayout(new GridLayout(3, 2, 0, 0));
			pnlEstadistica.add(getLblAcertadas());
			pnlEstadistica.add(getTxtAcertadas());
			pnlEstadistica.add(getLblFalladas());
			pnlEstadistica.add(getTxtFalladas());
			pnlEstadistica.add(getLblCatDificil());
			pnlEstadistica.add(getTxtCatDificil());
		}
		return pnlEstadistica;
	}
	private JLabel getLblCatDificil() {
		if (lblCatDificil == null) {
			lblCatDificil = new JLabel("Categor\u00EDa m\u00E1s dificil");
			lblCatDificil.setFont(new Font("Nyala", Font.PLAIN, 25));
		}
		return lblCatDificil;
	}
	private JLabel getLblAcertadas() {
		if (lblAcertadas == null) {
			lblAcertadas = new JLabel("% Preguntas acertadas");
			lblAcertadas.setFont(new Font("Nyala", Font.PLAIN, 25));
		}
		return lblAcertadas;
	}
	private JLabel getLblFalladas() {
		if (lblFalladas == null) {
			lblFalladas = new JLabel("% Preguntas falladas");
			lblFalladas.setFont(new Font("Nyala", Font.PLAIN, 25));
		}
		return lblFalladas;
	}
	private JTextField getTxtAcertadas() {
		if (txtAcertadas == null) {
			txtAcertadas = new JTextField();
			txtAcertadas.setFont(new Font("Nyala", Font.PLAIN, 25));
			txtAcertadas.setBackground(Color.ORANGE);
			txtAcertadas.setColumns(10);
		}
		return txtAcertadas;
	}
	private JTextField getTxtFalladas() {
		if (txtFalladas == null) {
			txtFalladas = new JTextField();
			txtFalladas.setFont(new Font("Nyala", Font.PLAIN, 25));
			txtFalladas.setBackground(Color.ORANGE);
			txtFalladas.setColumns(10);
		}
		return txtFalladas;
	}
	private JTextField getTxtCatDificil() {
		if (txtCatDificil == null) {
			txtCatDificil = new JTextField();
			txtCatDificil.setFont(new Font("Nyala", Font.PLAIN, 25));
			txtCatDificil.setBackground(Color.ORANGE);
			txtCatDificil.setColumns(10);
		}
		return txtCatDificil;
	}
	private JButton getBtnAtrs() {
		if (btnAtrs == null) {
			btnAtrs = new JButton("Atr\u00E1s");
			btnAtrs.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					//volvemos al panel principal
					((CardLayout) contentPane.getLayout()).show(contentPane, "Panel Principal");
				}
			});
			btnAtrs.setFont(new Font("Nyala", Font.PLAIN, 30));
		}
		return btnAtrs;
	}
}
