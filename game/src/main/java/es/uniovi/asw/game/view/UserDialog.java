package es.uniovi.asw.game.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Toolkit;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import es.uniovi.asw.game.model.User;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.CardLayout;
import javax.swing.BoxLayout;

public class UserDialog extends JDialog {

	private JPanel pnUsuario;
	private JPanel pnlNombre;
	private JLabel lblUsuario;
	private JTextField txtUsuario;
	private JPanel pnlEstadistica;
	private JLabel lblCantidad;
	private JLabel lblAcertadas;
	private JLabel lblFalladas;
	private JTextField txtAcertadas;
	private JTextField txtFalladas;
	private JTextField txtCantidad;
	/**
	 * Create the dialog.
	 */
	@SuppressWarnings("deprecation")
	public UserDialog(User user) {
		setIconImage(Toolkit.getDefaultToolkit().getImage("img\\icono_trivial.png"));
		setSize(557, 400);
		setLocationRelativeTo(null);
		getContentPane().setLayout(new BorderLayout(0, 0));
		
		pnUsuario = getPnUsuario();
		pnUsuario.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(pnUsuario, BorderLayout.CENTER);
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setBackground(Color.ORANGE);
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton okButton = new JButton("Aceptar");
				okButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent arg0) {
						dispose();
					}
				});
				okButton.setActionCommand("OK");
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
			}
		}
		loadUser(user);
		show();
	}
	
	private JPanel getPnUsuario() {
		if (pnUsuario == null) {
			pnUsuario = new JPanel();
			pnUsuario.setBackground(Color.ORANGE);
			pnUsuario.setLayout(new BorderLayout(0, 0));
			pnUsuario.add(getPnlNombre(), BorderLayout.NORTH);
			pnUsuario.add(getPnlEstadistica());
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
			txtUsuario.setHorizontalAlignment(SwingConstants.CENTER);
			txtUsuario.setEditable(false);
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
			pnlEstadistica.add(getLblCantidad());
			pnlEstadistica.add(getTxtCantidad());
		}
		return pnlEstadistica;
	}
	
	private JLabel getLblCantidad() {
		if (lblCantidad == null) {
			lblCantidad = new JLabel("Preguntas Respondidas");
			lblCantidad.setFont(new Font("Nyala", Font.PLAIN, 25));
		}
		return lblCantidad;
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
	private JTextField getTxtCantidad() {
		if (txtCantidad == null) {
			txtCantidad = new JTextField();
			txtCantidad.setFont(new Font("Nyala", Font.PLAIN, 25));
			txtCantidad.setBackground(Color.ORANGE);
			txtCantidad.setColumns(10);
		}
		return txtCantidad;
	}
	
	private void loadUser(User user)
	{
		txtUsuario.setText(user.getName());
		txtCantidad.setText(""+user.getRightQuestions()+user.getFailedQuestions());
		if(user.getRightQuestions()+user.getFailedQuestions()!=0){
			txtAcertadas.setText(""+user.getRightQuestions()*100/(user.getRightQuestions()+user.getFailedQuestions()));
			txtFalladas.setText(""+user.getFailedQuestions()*100/(user.getRightQuestions()+user.getFailedQuestions()));
		}
		else{
			txtAcertadas.setText("ninguna pregunta contestada");
			txtFalladas.setText("ninguna pregunta contestada");
			}
	}
}
