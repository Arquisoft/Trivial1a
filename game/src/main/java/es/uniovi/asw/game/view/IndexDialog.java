package es.uniovi.asw.game.view;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.JTextField;
import javax.swing.JPasswordField;

import es.uniovi.asw.game.infrastructure.Factory;
import es.uniovi.asw.game.model.User;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class IndexDialog extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTextField txUsuario;
	private JPasswordField txContrasenia;
	private User user = null;

	/**
	 * Launch the application.
	 */

	/**
	 * Create the dialog.
	 */
	public IndexDialog(final View parent, String Title) {
		super(parent,Title);
		setBounds(100, 100, 334, 277);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		{
			JLabel lbTitulo = new JLabel("es necesario logearse para acceder");
			lbTitulo.setHorizontalAlignment(SwingConstants.CENTER);
			lbTitulo.setBounds(10, 10, 298, 50);
			contentPanel.add(lbTitulo);
		}
		{
			JLabel lbUsuario = new JLabel("Usuario:");
			lbUsuario.setBounds(10, 81, 77, 21);
			contentPanel.add(lbUsuario);
		}
		{
			JLabel lbContraseña = new JLabel("Contrase\u00F1a:");
			lbContraseña.setBounds(10, 148, 77, 21);
			contentPanel.add(lbContraseña);
		}
		{
			txUsuario = new JTextField();
			txUsuario.setBounds(97, 81, 200, 21);
			contentPanel.add(txUsuario);
			txUsuario.setColumns(10);
		}
		{
			txContrasenia = new JPasswordField();
			txContrasenia.setBounds(97, 148, 200, 20);
			contentPanel.add(txContrasenia);
		}
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton okButton = new JButton("Aceptar");
				okButton.addActionListener(new ActionListener() {
					@SuppressWarnings("deprecation")
					public void actionPerformed(ActionEvent arg0) {
						if(!txUsuario.getText().equals("")&&!txContrasenia.getText().equals(""))
						{
							user = Factory.persistence.createTrivialDAO().loadUser(txUsuario.getText(), txContrasenia.getText());
						}
						parent.showStatistics(user);
						dispose();
					}
				});
				okButton.setActionCommand("OK");
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
			}
			
			{
				JButton cancelButton = new JButton("Cancelar");
				cancelButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent arg0) {
						dispose();
					}
				});
				cancelButton.setActionCommand("Cancel");
				buttonPane.add(cancelButton);
			}
		}
	}

}
