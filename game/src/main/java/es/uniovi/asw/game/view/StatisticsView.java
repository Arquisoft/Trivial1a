package es.uniovi.asw.game.view;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import es.uniovi.asw.game.model.Question;
import es.uniovi.asw.game.model.User;
import es.uniovi.asw.game.view.models.UserListModel;

public class StatisticsView extends JFrame {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private View parentView;

	private JPanel contentPane;
	private JPanel pnUsuarios;
	private JLabel lblTitulo;
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
	private JTabbedPane tabbedPaneEstadisticas;
	private JPanel pnQuestions;
	private JLabel lblPreguntasRegistradas;
	private JScrollPane scrollPane;
	private JList<User> listUsers;
	private UserListModel listModel;
	private User user;
	private JTable tableQuestions;

	/**
	 * Create the frame.
	 */
	public StatisticsView(View parentView, User user) {
		this.user=user;
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
		contentPane.add(getTabbedPaneEstadisticas(), "name_87790377464293");
		loadUser(user);
		if(user.isAdmin()){
			loadUsers();
			loadQuestions();
		}
	}

	private JPanel getPnUsuarios() {
		if (pnUsuarios == null) {
			pnUsuarios = new JPanel();
			pnUsuarios.setBackground(Color.ORANGE);
			pnUsuarios.setForeground(Color.WHITE);
			pnUsuarios.setLayout(new BorderLayout(0, 0));
			pnUsuarios.add(getLblTitulo(), BorderLayout.NORTH);
			pnUsuarios.add(getList_1(), BorderLayout.CENTER);
		}
		return pnUsuarios;
	}
	private JLabel getLblTitulo() {
		if (lblTitulo == null) {
			lblTitulo = new JLabel("Usuarios registrados");
			lblTitulo.setBackground(Color.WHITE);
			lblTitulo.setForeground(Color.BLACK);
			lblTitulo.setFont(new Font("Nyala", Font.PLAIN, 35));
			lblTitulo.setHorizontalAlignment(SwingConstants.CENTER);
		}
		return lblTitulo;
	}
	private JPanel getPnUsuario() {
		if (pnUsuario == null) {
			pnUsuario = new JPanel();
			pnUsuario.setBackground(Color.ORANGE);
			pnUsuario.setLayout(new BorderLayout(0, 0));
			pnUsuario.add(getPnlNombre(), BorderLayout.NORTH);
			pnUsuario.add(getPnlEstadistica(), BorderLayout.CENTER);
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
	private JTabbedPane getTabbedPaneEstadisticas() {
		if (tabbedPaneEstadisticas == null) {
			tabbedPaneEstadisticas = new JTabbedPane(JTabbedPane.TOP);
			tabbedPaneEstadisticas.addTab("Mis estadísticas", null,getPnUsuario(),null);
			if(user.isAdmin()){
				tabbedPaneEstadisticas.addTab("Estadísticas de usuarios", null, getPnUsuarios(), null);
				tabbedPaneEstadisticas.addTab("Estadísticas de preguntas", null, getPnQuestions(), null);
				}				
		}
		return tabbedPaneEstadisticas;
	}
	private JPanel getPnQuestions() {
		if (pnQuestions == null) {
			pnQuestions = new JPanel();
			pnQuestions.setBackground(Color.ORANGE);
			pnQuestions.setLayout(new BorderLayout(0, 0));
			pnQuestions.add(getLblPreguntasRegistradas(), BorderLayout.NORTH);
			pnQuestions.add(getScrollPane(), BorderLayout.CENTER);
		}
		return pnQuestions;
	}
	private JLabel getLblPreguntasRegistradas() {
		if (lblPreguntasRegistradas == null) {
			lblPreguntasRegistradas = new JLabel("Preguntas registradas");
			lblPreguntasRegistradas.setHorizontalAlignment(SwingConstants.CENTER);
			lblPreguntasRegistradas.setForeground(Color.BLACK);
			lblPreguntasRegistradas.setFont(new Font("Nyala", Font.PLAIN, 35));
			lblPreguntasRegistradas.setBackground(Color.WHITE);
		}
		return lblPreguntasRegistradas;
	}
	private JScrollPane getScrollPane() {
		if (scrollPane == null) {
			scrollPane = new JScrollPane();
			scrollPane.setViewportView(getTableQuestions());
		}
		return scrollPane;
	}
	@SuppressWarnings("rawtypes")
	private JList getList_1() {
		if (listUsers == null) {
			listUsers = new JList<User>();
			listUsers.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent arg0) {
					user = listModel.getUserAt(listUsers.getSelectedIndex());
					new UserDialog(user);
				}
			});
		}
		return listUsers;
	}
	private JTable getTableQuestions() {
		if (tableQuestions == null) {
			tableQuestions = new JTable();
		}
		return tableQuestions;
	}
	
	private void loadUsers() {
			listModel = new UserListModel();
			List<User> users =  parentView.getControler().loadUsers();
			for (User u : users )
				listModel.addUser(u);
			listUsers.setModel(listModel);
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
	
	private void loadQuestions()
	{
		List<Question> questions = parentView.getControler().loadQuestions();
		String[] colums={"Pregunta","nºAciertos","nºFallos"};
		String[][]data = new String[questions.size()][3];
		for(int i=0; i< questions.size(); i++)
		{
			
			data[i][0]=questions.get(i).getName();
			data[i][1]=questions.get(i).getSuccesses()+"";
			data[i][2]=questions.get(i).getFailures()+"";
		}
			
		DefaultTableModel tablemodel = new DefaultTableModel(data,colums);
		tableQuestions.setModel(tablemodel);
	}
}