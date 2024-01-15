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
	private JTextField tfId;
	private JTextField tfName;
	private JTextField tfEmail;
	private JTextField tfMobile;
	private JPasswordField tfPw;
	private JButton btnJoin;
	private JTextField tfBirth;

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
		setTitle("회원 가입창");
		setBounds(100, 100, 422, 314);
		getContentPane().setLayout(null);
		
		JLabel lblId = new JLabel("Id:");
		lblId.setBounds(25, 22, 57, 15);
		getContentPane().add(lblId);
		
		tfId = new JTextField();
		tfId.setBounds(112, 19, 116, 21);
		getContentPane().add(tfId);
		tfId.setColumns(10);
		
		JLabel lblPw = new JLabel("Pw:");
		lblPw.setBounds(25, 58, 57, 15);
		getContentPane().add(lblPw);
		
		tfName = new JTextField();
		tfName.setEnabled(false);
		tfName.setColumns(10);
		tfName.setBounds(112, 96, 116, 21);
		getContentPane().add(tfName);
		
		JLabel lblName = new JLabel("Name:");
		lblName.setBounds(25, 99, 57, 15);
		getContentPane().add(lblName);
		
		tfEmail = new JTextField();
		tfEmail.setEnabled(false);
		tfEmail.setColumns(10);
		tfEmail.setBounds(112, 138, 214, 21);
		getContentPane().add(tfEmail);
		
		JLabel lblEmail = new JLabel("Email:");
		lblEmail.setBounds(25, 141, 57, 15);
		getContentPane().add(lblEmail);
		
		tfMobile = new JTextField();
		tfMobile.setEnabled(false);
		tfMobile.setColumns(10);
		tfMobile.setBounds(112, 177, 152, 21);
		getContentPane().add(tfMobile);
		
		JLabel lblMobile = new JLabel("Mobile:");
		lblMobile.setBounds(25, 180, 57, 15);
		getContentPane().add(lblMobile);
		
		btnJoin = new JButton("가입");
		btnJoin.setEnabled(false);
		btnJoin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					Class.forName("com.mysql.cj.jdbc.Driver");
					Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/sqlDB","root","1234");
					System.out.println("DB 연결 완료");
					Statement stmt = con.createStatement();
					
					String temp = tfMobile.getText(); // 01011112222 => 010-1111-2222
					if(temp.length() == 11)
						temp = temp.substring(0, 3) + "-" + temp.substring(3, 7) + "-" + temp.substring(7) ;
					else
						temp = "000-0000-0000";
					
					String sql = "INSERT INTO membertbl VALUES('" + tfId.getText() + "','";
					sql = sql + new String(tfPw.getPassword()) + "','" + tfName.getText() + "','";
					sql = sql + tfEmail.getText() + "','" + temp + "')";
					
					if(stmt.executeUpdate(sql) <= 0)
						System.out.println("삽입 오류 발생");
					
				} catch (ClassNotFoundException | SQLException e1) {
					System.out.println("DB 문제1");
				}
			}
		});
		btnJoin.setBounds(276, 169, 97, 30);
		getContentPane().add(btnJoin);
		
		tfPw = new JPasswordField();
		tfPw.setEnabled(false);
		tfPw.setBounds(112, 55, 116, 21);
		getContentPane().add(tfPw);
		
		JButton btnDup = new JButton("중복확인");
		btnDup.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					Class.forName("com.mysql.cj.jdbc.Driver");
					Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/sqlDB","root","1234");
					System.out.println("DB 연결 완료");
					Statement stmt = con.createStatement();
					String sql = "select * from memberTBL where id='" + tfId.getText() + "'";
					ResultSet rs = stmt.executeQuery(sql);
					if(!rs.next()) {
						enableEdit();
					}else {
						tfId.setSelectionStart(0);  // 블록 시작
						tfId.setSelectionEnd(tfId.getText().length()); // 블록 끝
						tfId.requestFocus();
					}					
					
				} catch (ClassNotFoundException | SQLException e1) {
					System.out.println("DB 문제1");
				}
			}
		});
		btnDup.setBounds(245, 18, 97, 23);
		getContentPane().add(btnDup);
		
		JLabel lblBirth = new JLabel("생일:");
		lblBirth.setBounds(25, 231, 57, 15);
		getContentPane().add(lblBirth);
		
		tfBirth = new JTextField();
		tfBirth.setColumns(10);
		tfBirth.setBounds(112, 228, 152, 21);
		getContentPane().add(tfBirth);
		
		JButton btnCalendar = new JButton("달력...");
		btnCalendar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				WinCalendar winCalendar = new WinCalendar();
				winCalendar.setModal(true);
				winCalendar.setVisible(true);
				
				tfBirth.setText(winCalendar.getDate());
			}
		});
		btnCalendar.setBounds(276, 227, 97, 23);
		getContentPane().add(btnCalendar);

	}

	protected void enableEdit() {
		btnJoin.setEnabled(true);
		tfPw.setEnabled(true);
		tfName.setEnabled(true);
		tfEmail.setEnabled(true);
		tfMobile.setEnabled(true);
	}
}
