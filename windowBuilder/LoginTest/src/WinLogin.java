import java.awt.EventQueue;

import javax.swing.JDialog;
import java.awt.BorderLayout;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import com.mysql.cj.protocol.Resultset;

import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.awt.event.ActionEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JPasswordField;

public class WinLogin extends JDialog {
	private JTextField tfID;
	private JPasswordField tfPassword;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					WinLogin dialog = new WinLogin();
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
	public WinLogin() {
		setTitle("로그인 창");
		setBounds(100, 100, 450, 234);
		getContentPane().setLayout(new BorderLayout(0, 0));
		
		JPanel panel = new JPanel();
		getContentPane().add(panel, BorderLayout.CENTER);
		panel.setLayout(null);
		
		JLabel lblID = new JLabel("ID:");
		lblID.setBounds(71, 73, 57, 15);
		panel.add(lblID);
		
		tfID = new JTextField();
		tfID.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if(e.getKeyCode() == KeyEvent.VK_ENTER) {
					tfPassword.requestFocus();
				}
			}
		});
		tfID.setBounds(152, 70, 116, 21);
		panel.add(tfID);
		tfID.setColumns(10);
		
		JLabel lblPassword = new JLabel("Password:");
		lblPassword.setBounds(71, 104, 69, 15);
		panel.add(lblPassword);
		
		JButton btnLogin = new JButton("로그인...");
		btnLogin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// 데이터베이스 연결
				connectDB();
			}
		});
		btnLogin.setBounds(282, 69, 82, 53);
		panel.add(btnLogin);
		
		JLabel lblJoinMember = new JLabel("회원가입...");
		lblJoinMember.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				WinMemberJoin winMemberJoin = new WinMemberJoin();
				winMemberJoin.setModal(true);
				winMemberJoin.setVisible(true);
			}
		});
		lblJoinMember.setBounds(99, 148, 69, 15);
		panel.add(lblJoinMember);
		
		tfPassword = new JPasswordField();
		tfPassword.setBounds(152, 101, 116, 21);
		panel.add(tfPassword);

	}

	protected void connectDB() {
		// 데이터베이스 연결
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/sqldb", "root", "1234");
			System.out.println("DB 연결 완료");
			
			String sql = "select * from membertbl where id='" + tfID.getText() + "'";
			Statement stmt = con.createStatement();
			ResultSet rs =  stmt.executeQuery(sql);
			
			if(rs.next()) {
				String pw = rs.getString("password");
				String sName = rs.getString("name");
				
				if(pw.equals(new String(tfPassword.getPassword()))){
					dispose();
					WinMain winMain = new WinMain(sName);
					winMain.setModal(true);
					winMain.setVisible(true);
				} else {
					JOptionPane.showMessageDialog(null, "패스워드를 확인하세요");
					tfPassword.setText("");
					tfPassword.requestFocus();
				}
			} else {
				JOptionPane.showMessageDialog(null, "아이디를 확인하세요");
			}
			
		} catch (ClassNotFoundException | SQLException e) {
			System.out.println("DB 문제");
		}
	}
}
