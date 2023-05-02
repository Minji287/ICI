import java.awt.EventQueue;

import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextArea;
import java.awt.Font;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JSeparator;
import java.awt.Color;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.awt.event.ActionEvent;
import javax.swing.JTextField;

public class TodoList extends JDialog {

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TodoList dialog = new TodoList();
					dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
					dialog.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	private JTextArea taMemo;
	private JCheckBox chbxTodo1, chbxTodo2, chbxTodo3, chbxTodo4, chbxTodo5, chbxTodo6;
	private JTextField tfTodo1, tfTodo2, tfTodo3, tfTodo4, tfTodo5, tfTodo6;
	private JTextArea taDone;

	/**
	 * Create the dialog.
	 */
	public TodoList() {
		setTitle("오늘의 할일");
		setBounds(100, 100, 789, 614);
		getContentPane().setLayout(null);
		
		JLabel lblMemo = new JLabel("오늘의 메모 :");
		lblMemo.setBounds(28, 406, 86, 15);
		getContentPane().add(lblMemo);
		
		taMemo = new JTextArea();
		taMemo.setLineWrap(true);
		taMemo.setBounds(28, 431, 524, 116);
		getContentPane().add(taMemo);
		
		JLabel lblTitle = new JLabel("Todo List");
		lblTitle.setFont(new Font("맑은 고딕", Font.BOLD, 25));
		lblTitle.setBounds(12, 10, 118, 34);
		getContentPane().add(lblTitle);
		
		JButton btnSave = new JButton("저장");
		btnSave.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				saveData();
			}
		});
		btnSave.setBounds(608, 432, 111, 34);
		getContentPane().add(btnSave);
		
		JButton btnExit = new JButton("나가기");
		btnExit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int result = JOptionPane.showConfirmDialog(null, "종료하시겠습니까?", "종료", JOptionPane.YES_NO_OPTION);
				
				if(result == JOptionPane.YES_OPTION) {
					dispose();
				}
			}
		});
		btnExit.setBounds(608, 520, 111, 27);
		getContentPane().add(btnExit);
		
		JButton btnDeleteAll = new JButton("모두 삭제");
		btnDeleteAll.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int result = JOptionPane.showConfirmDialog(null, "모두 삭제하시겠습니까?", "모두 삭제", JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE);
				
				if(result == JOptionPane.YES_OPTION) {
					tfTodo1.setText("");
					tfTodo2.setText("");
					tfTodo3.setText("");
					tfTodo4.setText("");
					tfTodo5.setText("");
					tfTodo6.setText("");
					taMemo.setText("");
				}
			}
		});
		btnDeleteAll.setBounds(608, 476, 111, 34);
		getContentPane().add(btnDeleteAll);
		
		chbxTodo1 = new JCheckBox("");
		chbxTodo1.setFont(new Font("맑은 고딕", Font.PLAIN, 14));
		chbxTodo1.setBounds(28, 61, 21, 51);
		getContentPane().add(chbxTodo1);
		
		chbxTodo2 = new JCheckBox("");
		chbxTodo2.setFont(new Font("맑은 고딕", Font.PLAIN, 14));
		chbxTodo2.setBounds(28, 114, 21, 51);
		getContentPane().add(chbxTodo2);
		
		chbxTodo3 = new JCheckBox("");
		chbxTodo3.setFont(new Font("맑은 고딕", Font.PLAIN, 14));
		chbxTodo3.setBounds(28, 167, 25, 51);
		getContentPane().add(chbxTodo3);
		
		chbxTodo4 = new JCheckBox("");
		chbxTodo4.setFont(new Font("맑은 고딕", Font.PLAIN, 14));
		chbxTodo4.setBounds(28, 220, 25, 51);
		getContentPane().add(chbxTodo4);
		
		chbxTodo5 = new JCheckBox("");
		chbxTodo5.setFont(new Font("맑은 고딕", Font.PLAIN, 14));
		chbxTodo5.setBounds(28, 273, 25, 51);
		getContentPane().add(chbxTodo5);
		
		chbxTodo6 = new JCheckBox("");
		chbxTodo6.setFont(new Font("맑은 고딕", Font.PLAIN, 14));
		chbxTodo6.setBounds(28, 326, 25, 51);
		getContentPane().add(chbxTodo6);
		
		JSeparator separator = new JSeparator();
		separator.setOpaque(true);
		separator.setForeground(new Color(0, 0, 0));
		separator.setBackground(new Color(0, 0, 0));
		separator.setBounds(395, 61, 1, 331);
		getContentPane().add(separator);
		
		tfTodo1 = new JTextField();
		tfTodo1.setBackground(new Color(255, 255, 255));
		tfTodo1.setFont(new Font("맑은 고딕", Font.PLAIN, 14));
		tfTodo1.setBounds(55, 61, 332, 51);
		getContentPane().add(tfTodo1);
		tfTodo1.setColumns(10);
		
		tfTodo2 = new JTextField();
		tfTodo2.setFont(new Font("맑은 고딕", Font.PLAIN, 14));
		tfTodo2.setColumns(10);
		tfTodo2.setBackground(Color.WHITE);
		tfTodo2.setBounds(55, 114, 332, 51);
		getContentPane().add(tfTodo2);
		
		tfTodo3 = new JTextField();
		tfTodo3.setFont(new Font("맑은 고딕", Font.PLAIN, 14));
		tfTodo3.setColumns(10);
		tfTodo3.setBackground(Color.WHITE);
		tfTodo3.setBounds(55, 167, 332, 51);
		getContentPane().add(tfTodo3);
		
		tfTodo4 = new JTextField();
		tfTodo4.setFont(new Font("맑은 고딕", Font.PLAIN, 14));
		tfTodo4.setColumns(10);
		tfTodo4.setBackground(Color.WHITE);
		tfTodo4.setBounds(55, 220, 332, 51);
		getContentPane().add(tfTodo4);
		
		tfTodo5 = new JTextField();
		tfTodo5.setFont(new Font("맑은 고딕", Font.PLAIN, 14));
		tfTodo5.setColumns(10);
		tfTodo5.setBackground(Color.WHITE);
		tfTodo5.setBounds(55, 273, 332, 51);
		getContentPane().add(tfTodo5);
		
		tfTodo6 = new JTextField();
		tfTodo6.setFont(new Font("맑은 고딕", Font.PLAIN, 14));
		tfTodo6.setColumns(10);
		tfTodo6.setBackground(Color.WHITE);
		tfTodo6.setBounds(55, 326, 332, 51);
		getContentPane().add(tfTodo6);
		
		taDone = new JTextArea();
		taDone.setEditable(false);
		taDone.setBounds(408, 61, 332, 318);
		getContentPane().add(taDone);

	}

	protected void saveData() {
		//=================================
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/sqlDB","root","1234");	
			
			String sql = "UPDATE todoTBL SET todo=?, done=?, memo=? WHERE user=?";
			
			PreparedStatement pstmt = con.prepareStatement(sql);
			
			ArrayList<String> list = new ArrayList<>();
			list.add(tfTodo1.getText().toString());
			list.add(tfTodo2.getText().toString());
			list.add(tfTodo3.getText().toString());
			list.add(tfTodo4.getText().toString());
			list.add(tfTodo5.getText().toString());
			list.add(tfTodo6.getText().toString());
			
			int idx = getTitle().indexOf('의');
			String user = getTitle().substring(0, idx);
			
			pstmt.setString(1, list.toString());
			pstmt.setString(2, taDone.getText().toString());
			pstmt.setString(3, taMemo.getText().toString());
			pstmt.setString(4, user);
			
			System.out.println(pstmt);
			
			if(pstmt.executeUpdate() >= 0) {
				JOptionPane.showMessageDialog(null, "저장되었습니다.");
			}
			
		} catch (ClassNotFoundException | SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		//=================================
	}
}
