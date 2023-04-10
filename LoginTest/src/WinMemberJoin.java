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
	private JButton btnJoin;

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
		tfName.setEnabled(false);
		tfName.setColumns(10);
		tfName.setBounds(140, 128, 116, 21);
		getContentPane().add(tfName);
		
		JLabel lblEmail = new JLabel("Email:");
		lblEmail.setBounds(53, 173, 57, 15);
		getContentPane().add(lblEmail);
		
		tfEmail = new JTextField();
		tfEmail.setEnabled(false);
		tfEmail.setColumns(10);
		tfEmail.setBounds(140, 170, 190, 21);
		getContentPane().add(tfEmail);
		
		JLabel lblMobile = new JLabel("Mobile:");
		lblMobile.setBounds(53, 216, 57, 15);
		getContentPane().add(lblMobile);
		
		tfMobile = new JTextField();
		tfMobile.setEnabled(false);
		tfMobile.setColumns(10);
		tfMobile.setBounds(140, 213, 116, 21);
		getContentPane().add(tfMobile);
		
		btnJoin = new JButton("가입");
		btnJoin.setEnabled(false);
		btnJoin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					Class.forName("com.mysql.cj.jdbc.Driver");
					Connection con = DriverManager.getConnection("jdbc:mysql://localhost/sqldb", "root", "1234");
					System.out.println("DB 연결 완료");
					Statement stmt = con.createStatement();
					
					String temp = tfMobile.getText(); // 01011112222 -> 010-1111-2222
					if(temp.length() == 11) {
						temp = temp.substring(0, 3) + "-" + temp.substring(3, 7) + "-" + temp.substring(7);
					} else {
						temp = "010-0000-0000";
					}
					
					String sql = "INSERT INTO membertbl VALUES('" + tfID.getText() + "','";
					sql = sql + new String(tfPw.getPassword()) + "','" + tfName.getText() + "','";
					sql = sql + tfEmail.getText() + "','" + temp + "')";
					
					if(stmt.executeUpdate(sql) <= 0) {
						System.out.println("삽입 오류 발생");
					}
					
					
				} catch (ClassNotFoundException | SQLException e1) {
					e1.printStackTrace();
					System.out.println("DB 문제");
				}
			}
		});
		btnJoin.setBounds(268, 205, 106, 37);
		getContentPane().add(btnJoin);
		
		tfPw = new JPasswordField();
		tfPw.setEnabled(false);
		tfPw.setBounds(140, 82, 116, 21);
		getContentPane().add(tfPw);
		
		JButton btnDup = new JButton("중복확인");
		btnDup.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					Class.forName("com.mysql.cj.jdbc.Driver");
					Connection con = DriverManager.getConnection("jdbc:mysql://localhost/sqldb", "root", "1234");
					System.out.println("DB 연결 완료");
					Statement stmt = con.createStatement();
					
					String sql = "SELECT * from membertbl WHERE id='" + tfID.getText() + "'";
					
					ResultSet rs = stmt.executeQuery(sql);
					
					if(rs.next()) {
						disableEdit();
						JOptionPane.showMessageDialog(null, "이미 존재하는 아이디 입니다.");
						tfID.setSelectionStart(0); // 블록 시작
						tfID.setSelectionEnd(tfID.getText().length()); // 블록 끝
						tfID.requestFocus(); // 블록 잡기
					} else {
						JOptionPane.showMessageDialog(null, "사용 가능한 아이디 입니다.");
						enableEdit();
					}
					
				} catch (ClassNotFoundException | SQLException e1) {
					e1.printStackTrace();
					System.out.println("DB 문제");
				}
			}
		});
		btnDup.setBounds(268, 42, 97, 23);
		getContentPane().add(btnDup);

	}

	protected void disableEdit() {
		btnJoin.setEnabled(false);
		tfPw.setEnabled(false);
		tfName.setEnabled(false);
		tfEmail.setEnabled(false);
		tfMobile.setEnabled(false);
		
	}

	protected void enableEdit() {
		btnJoin.setEnabled(true);
		tfPw.setEnabled(true);
		tfName.setEnabled(true);
		tfEmail.setEnabled(true);
		tfMobile.setEnabled(true);
	}
}
