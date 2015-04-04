package es.uniovi.asw.game.view;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JList;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.JPasswordField;
import javax.swing.JButton;

import es.uniovi.asw.game.model.User;
import es.uniovi.asw.game.view.models.UserListModel;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class UserLogin extends JFrame {
	//la vista que se está usando en este momento. se accede al controlador(Game) a traves de ella
	private View parentView;
	
	private JPanel UserLogcontentPane;
	private JList ListUsers;
	private JPanel UsersLoginPanel;
	private JLabel lbUser;
	private JTextField txName;
	private JLabel lblNewLabel;
	private JLabel lbPasswd;
	private JPasswordField txPasswd;
	private JButton btAniadir;
	private JLabel lblNewLabel_1;
	private JButton btSignIn;
	private JLabel lblO;
	private JButton btContinue;
	private JButton btEliminar;
	private JLabel label;
	private UserListModel listModel;

	/**
	 * Create the frame.
	 */
	public UserLogin(View parentView) {
		this.parentView = parentView;
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 412, 469);
		UserLogcontentPane = new JPanel();
		UserLogcontentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(UserLogcontentPane);
		UserLogcontentPane.setLayout(null);
		UserLogcontentPane.add(getListUsers());
		UserLogcontentPane.add(getUsersLoginPanel());
		UserLogcontentPane.add(getLabel_1());
		loadUsers();
	}

	private JList getListUsers() {
		if (ListUsers == null) {
			ListUsers = new JList();
			ListUsers.setBounds(10, 80, 188, 339);
		}
		return ListUsers;
	}

	private JPanel getUsersLoginPanel() {
		if (UsersLoginPanel == null) {
			UsersLoginPanel = new JPanel();
			UsersLoginPanel.setBounds(208, 80, 178, 339);
			UsersLoginPanel.setLayout(null);
			UsersLoginPanel.add(getLbUser());
			UsersLoginPanel.add(getTxName());
			UsersLoginPanel.add(getLbPasswd());
			UsersLoginPanel.add(getTxPasswd());
			UsersLoginPanel.add(getBtAniadir());
			UsersLoginPanel.add(getLblNewLabel_1());
			UsersLoginPanel.add(getBtSignIn());
			UsersLoginPanel.add(getLblO());
			UsersLoginPanel.add(getBtContinue());
			UsersLoginPanel.add(getBtEliminar());
			UsersLoginPanel.add(getLabel());
		}
		return UsersLoginPanel;
	}

	private JLabel getLbUser() {
		if (lbUser == null) {
			lbUser = new JLabel("Usuario:");
			lbUser.setBounds(10, 11, 65, 14);
		}
		return lbUser;
	}

	private JTextField getTxName() {
		if (txName == null) {
			txName = new JTextField();
			txName.setBounds(10, 36, 158, 20);
			txName.setColumns(10);
		}
		return txName;
	}

	private JLabel getLabel_1() {
		if (lblNewLabel == null) {
			lblNewLabel = new JLabel("Jugadores Para La Partida");
			lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
			lblNewLabel.setBounds(79, 11, 229, 58);
		}
		return lblNewLabel;
	}

	private JLabel getLbPasswd() {
		if (lbPasswd == null) {
			lbPasswd = new JLabel("Contrase\u00F1a:");
			lbPasswd.setBounds(10, 67, 103, 14);
		}
		return lbPasswd;
	}

	private JPasswordField getTxPasswd() {
		if (txPasswd == null) {
			txPasswd = new JPasswordField();
			txPasswd.setBounds(10, 92, 158, 20);
		}
		return txPasswd;
	}

	private JButton getBtAniadir() {
		if (btAniadir == null) {
			btAniadir = new JButton("A\u00F1adir a la partida");
			btAniadir.addActionListener(new ActionListener() {
				@SuppressWarnings("deprecation")
				public void actionPerformed(ActionEvent arg0) 
				{
					if(parentView.getControler().addUserToGame(txName.getText(), txPasswd.getText()))
						JOptionPane.showMessageDialog(UserLogin.this,"No se pueden añadir más usuaros a la partida");
					else
						JOptionPane.showMessageDialog(UserLogin.this,"Usuario añadido correctamente");
					loadUsers();
					
				}
			});
			btAniadir.setEnabled(false);
			btAniadir.setBounds(33, 135, 135, 23);
		}
		return btAniadir;
	}

	private JLabel getLblNewLabel_1() {
		if (lblNewLabel_1 == null) {
			lblNewLabel_1 = new JLabel("<<");
			lblNewLabel_1.setBounds(10, 137, 24, 19);
		}
		return lblNewLabel_1;
	}

	private JButton getBtSignIn() {
		if (btSignIn == null) {
			btSignIn = new JButton("Reg\u00EDstrate ahora");
			btSignIn.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
				}
			});
			btSignIn.setBounds(33, 228, 135, 23);
		}
		return btSignIn;
	}

	private JLabel getLblO() {
		if (lblO == null) {
			lblO = new JLabel("O");
			lblO.setBounds(95, 203, 18, 14);
		}
		return lblO;
	}

	private JButton getBtContinue() {
		if (btContinue == null) {
			btContinue = new JButton("Continuar");
			btContinue.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					dispose();
				}
			});
			btContinue.setBounds(10, 276, 158, 52);
		}
		return btContinue;
	}

	private JButton getBtEliminar() {
		if (btEliminar == null) {
			btEliminar = new JButton("Eliminar de la partida");
			btEliminar.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) 
				{
					parentView.getControler().deleteUserToGame(listModel.getUserAt(ListUsers.getSelectedIndex()));
					loadUsers();
				}
			});
			btEliminar.setBounds(33, 169, 135, 23);
			btEliminar.setEnabled(false);
		}
		return btEliminar;
	}

	private JLabel getLabel() {
		if (label == null) {
			label = new JLabel(">>");
			label.setBounds(10, 173, 24, 19);
		}
		return label;
	}

	private void loadUsers() {
		listModel = new UserListModel();
		for (User u : parentView.getControler().getUsers())
			listModel.addUser(u);
		// usuario de ejemplo
		listModel.addUser(new User("user ejemplo 1", "1", false));
		listModel.addUser(new User("user ejemplo 2", "1", false));
		ListUsers.setModel(listModel);
	}
}
