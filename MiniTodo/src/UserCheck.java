import java.awt.EventQueue;

import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.awt.event.ActionEvent;

public class UserCheck extends JDialog {
	private JTextField tfUserId;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					UserCheck dialog = new UserCheck();
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
	public UserCheck() {
		setTitle("Todo List 사용자 확인");
		setBounds(100, 100, 359, 161);
		getContentPane().setLayout(null);
		
		JLabel lblTodo = new JLabel("Todo List");
		lblTodo.setFont(new Font("맑은 고딕", Font.BOLD, 20));
		lblTodo.setBounds(119, 21, 97, 34);
		getContentPane().add(lblTodo);
		
		JLabel lblUserId = new JLabel("아이디 :");
		lblUserId.setBounds(39, 77, 57, 15);
		getContentPane().add(lblUserId);
		
		tfUserId = new JTextField();
		tfUserId.setBounds(92, 74, 116, 21);
		getContentPane().add(tfUserId);
		tfUserId.setColumns(10);
		
		JButton btnCheck = new JButton("로그인");
		btnCheck.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				checkUser();
			}
		});
		btnCheck.setBounds(218, 73, 97, 23);
		getContentPane().add(btnCheck);

	}

	protected void checkUser() {
		//=================================
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/sqlDB","root","1234");	
			
			String sql = "SELECT * FROM todoTBL WHERE user=?";
			
			PreparedStatement pstmt = con.prepareStatement(sql);
			
			pstmt.setString(1, tfUserId.getText().toString());
			
			ResultSet rs = pstmt.executeQuery();
			
			if(rs.next()) {
				TodoList todoList = new TodoList();
				todoList.setTitle(tfUserId.getText().toString() + "의 " + todoList.getTitle());
				dispose();
				todoList.setVisible(true);
			} else {
				JOptionPane.showMessageDialog(null, "사용자가 존재하지 않습니다.");
			}
			
		} catch (ClassNotFoundException | SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		//=================================
	}
}
