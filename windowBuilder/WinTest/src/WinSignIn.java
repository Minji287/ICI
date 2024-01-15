import java.awt.EventQueue;

import javax.swing.JDialog;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JSeparator;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JTextField;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class WinSignIn extends JDialog {
	private JTextField tfID;
	private JTextField tfPW;
	private JTextField tfName;
	private JTextField tfPhone;
	private JTextField tfAddress;
	private JTextField tfEmail;
	private JTextField tfBday;
	private JButton btnSubmit;
	private JButton btnCancle;
	private JButton btnCheckId;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					WinSignIn dialog = new WinSignIn();
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
	public WinSignIn() {
		setTitle("회원가입");
		setBounds(100, 100, 434, 502);
		getContentPane().setLayout(null);
		
		JLabel lblNewLabel = new JLabel("회원가입");
		lblNewLabel.setFont(new Font("맑은 고딕", Font.BOLD, 20));
		lblNewLabel.setBounds(170, 11, 94, 27);
		getContentPane().add(lblNewLabel);
		
		JSeparator separator = new JSeparator();
		separator.setBounds(12, 48, 394, 2);
		getContentPane().add(separator);
		
		JLabel lblID = new JLabel("아이디:");
		lblID.setBounds(38, 79, 57, 15);
		getContentPane().add(lblID);
		
		JLabel lblPW = new JLabel("비밀번호:");
		lblPW.setBounds(38, 118, 57, 15);
		getContentPane().add(lblPW);
		
		JLabel lblName = new JLabel("이름:");
		lblName.setBounds(38, 161, 75, 15);
		getContentPane().add(lblName);
		
		JLabel lblPhone = new JLabel("연락처:");
		lblPhone.setBounds(38, 206, 75, 15);
		getContentPane().add(lblPhone);
		
		JLabel lblAddress = new JLabel("주소:");
		lblAddress.setBounds(38, 251, 75, 15);
		getContentPane().add(lblAddress);
		
		JLabel lblEmail = new JLabel("이메일:");
		lblEmail.setBounds(38, 295, 75, 15);
		getContentPane().add(lblEmail);
		
		JLabel lblBday = new JLabel("생일:");
		lblBday.setBounds(38, 342, 75, 15);
		getContentPane().add(lblBday);
		
		btnCheckId = new JButton("중복확인");
		btnCheckId.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnCheckId.setBounds(276, 75, 97, 23);
		getContentPane().add(btnCheckId);
		
		btnSubmit = new JButton("회원가입");
		btnSubmit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String sId = tfID.getText();
				String sPw = tfPW.getText();
				String sName = tfName.getText();
				String sPhone = tfPhone.getText();
				String sAddress = tfAddress.getText();
				String sEmail = tfEmail.getText();
				String sBday = tfBday.getText();
				
				String sql = "insert into XXXTBL values('" + sId + "', '";
				sql = sql + sPw + "','" + sName + "')";
				System.out.println(sql);
			}
		});
		btnSubmit.setBounds(95, 400, 97, 23);
		getContentPane().add(btnSubmit);
		
		btnCancle = new JButton("취소");
		btnCancle.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnCancle.setBounds(222, 400, 97, 23);
		getContentPane().add(btnCancle);
		
		tfID = new JTextField();
		tfID.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if(e.getKeyCode() == KeyEvent.VK_ENTER) { // 엔터키를 누르면
					tfPW.requestFocus();
				}
			}
		});
		tfID.setBounds(107, 76, 157, 21);
		getContentPane().add(tfID);
		tfID.setColumns(10);
		
		tfPW = new JTextField();
		tfPW.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if(e.getKeyCode() == KeyEvent.VK_ENTER) { // 엔터키를 누르면
					tfName.requestFocus();
				}
			}
		});
		tfPW.setColumns(10);
		tfPW.setBounds(107, 115, 157, 21);
		getContentPane().add(tfPW);
		
		tfName = new JTextField();
		tfName.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if(e.getKeyCode() == KeyEvent.VK_ENTER) { // 엔터키를 누르면
					tfPhone.requestFocus();
				}
			}
		});
		tfName.setColumns(10);
		tfName.setBounds(107, 158, 157, 21);
		getContentPane().add(tfName);
		
		tfPhone = new JTextField();
		tfPhone.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if(e.getKeyCode() == KeyEvent.VK_ENTER) { // 엔터키를 누르면
					tfAddress.requestFocus();
				}
			}
		});
		tfPhone.setColumns(10);
		tfPhone.setBounds(107, 203, 157, 21);
		getContentPane().add(tfPhone);
		
		tfAddress = new JTextField();
		tfAddress.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if(e.getKeyCode() == KeyEvent.VK_ENTER) { // 엔터키를 누르면
					tfEmail.requestFocus();
				}
			}
		});
		tfAddress.setColumns(10);
		tfAddress.setBounds(107, 248, 157, 21);
		getContentPane().add(tfAddress);
		
		tfEmail = new JTextField();
		tfEmail.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if(e.getKeyCode() == KeyEvent.VK_ENTER) { // 엔터키를 누르면
					tfBday.requestFocus();
				}
			}
		});
		tfEmail.setColumns(10);
		tfEmail.setBounds(107, 292, 157, 21);
		getContentPane().add(tfEmail);
		
		tfBday = new JTextField();
		tfBday.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if(e.getKeyCode() == KeyEvent.VK_ENTER) { // 엔터키를 누르면
					btnSubmit.requestFocus();
				}
			}
		});
		tfBday.setColumns(10);
		tfBday.setBounds(107, 339, 157, 21);
		getContentPane().add(tfBday);

	}
}
