import java.awt.EventQueue;

import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.awt.event.ActionEvent;
import javax.swing.JPasswordField;

public class WinMemberJoin extends JDialog {
	private JTextField tfID;
	private JTextField tfName;
	private JTextField tfEmail;
	private JTextField tfMobile;
	private JPasswordField tfPw;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					WinMemberJoin dialog = new WinMemberJoin();
					dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
					dialog.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the dialog.
	 */
	public WinMemberJoin() {
		setTitle("회원가입 창");
		setBounds(100, 100, 450, 311);
		getContentPane().setLayout(null);
		
		JLabel lblID = new JLabel("Id:");
		lblID.setBounds(53, 46, 57, 15);
		getContentPane().add(lblID);
		
		tfID = new JTextField();
		tfID.setBounds(140, 43, 116, 21);
		getContentPane().add(tfID);
		tfID.setColumns(10);
		
		JLabel lblPw = new JLabel("Pw:");
		lblPw.setBounds(53, 85, 57, 15);
		getContentPane().add(lblPw);
		
		JLabel lblName = new JLabel("Name:");
		lblName.setBounds(53, 131, 57, 15);
		getContentPane().add(lblName);
		
		tfName = new JTextField();
		tfName.setColumns(10);
		tfName.setBounds(140, 128, 116, 21);
		getContentPane().add(tfName);
		
		JLabel lblEmail = new JLabel("Email:");
		lblEmail.setBounds(53, 173, 57, 15);
		getContentPane().add(lblEmail);
		
		tfEmail = new JTextField();
		tfEmail.setColumns(10);
		tfEmail.setBounds(140, 170, 190, 21);
		getContentPane().add(tfEmail);
		
		JLabel lblMobile = new JLabel("Mobile:");
		lblMobile.setBounds(53, 216, 57, 15);
		getContentPane().add(lblMobile);
		
		tfMobile = new JTextField();
		tfMobile.setColumns(10);
		tfMobile.setBounds(140, 213, 116, 21);
		getContentPane().add(tfMobile);
		
		JButton btnJoin = new JButton("가입");
		btnJoin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					Class.forName("com.mysql.cj.jdbc.Driver");
					Connection con = DriverManager.getConnection("jdbc:mysql://localhost/sqldb", "root", "1234");
					System.out.println("DB 연결 완료");
					Statement stmt = con.createStatement();
					
					String sql = "INSERT INTO membertbl VALUES('" + tfID.getText() + "','";
					sql = sql + new String(tfPw.getPassword()) + "','" + tfName.getText() + "','";
					sql = sql + tfEmail.getText() + "','" + tfMobile.getText() + "')";
					
					if(stmt.executeUpdate(sql) <= 0) {
						System.out.println("삽입 오류 발생");
					}
					
					
				} catch (ClassNotFoundException | SQLException e1) {
					e1.printStackTrace();
					System.out.println("DB 문제");
				}
			}
		});
		btnJoin.setBounds(288, 212, 97, 23);
		getContentPane().add(btnJoin);
		
		tfPw = new JPasswordField();
		tfPw.setBounds(140, 82, 116, 21);
		getContentPane().add(tfPw);

	}
}
