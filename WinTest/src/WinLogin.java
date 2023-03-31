import java.awt.EventQueue;

import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JSeparator;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class WinLogin extends JDialog {
	private JTextField tfId;
	private JTextField tfPw;

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
		setTitle("로그인");
		setBounds(100, 100, 450, 300);
		getContentPane().setLayout(null);
		
		JLabel lblId = new JLabel("아이디");
		lblId.setBounds(63, 98, 57, 15);
		getContentPane().add(lblId);
		
		JLabel lblPw = new JLabel("비밀번호");
		lblPw.setBounds(63, 123, 57, 15);
		getContentPane().add(lblPw);
		
		tfId = new JTextField();
		tfId.setBounds(134, 95, 116, 21);
		getContentPane().add(tfId);
		tfId.setColumns(10);
		
		JButton btnLogin = new JButton("LOGIN");
		btnLogin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String sId = tfId.getText();
				String sPw = tfPw.getText();
				if(sId.equals("test") && sPw.equals("1234")) {
					JOptionPane.showMessageDialog(null, "로그인 되었습니다.");
					WinF2C winF2C = new WinF2C();
					winF2C.setModal(true);
					winF2C.setVisible(true);
				}else if(sId.equals("") && sPw.equals("")) {
					JOptionPane.showMessageDialog(null, "회원정보가 입력되지 않았습니다.", "입력", JOptionPane.ERROR_MESSAGE);
				}else {
					JOptionPane.showMessageDialog(null, "회원정보가 일치하지 않습니다.", "불일치", JOptionPane.ERROR_MESSAGE);
					WinQuadraticF winQuadraticF = new WinQuadraticF();
					winQuadraticF.setModal(true);
					winQuadraticF.setVisible(true);
				}
			}
		});
		btnLogin.setBounds(262, 94, 97, 44);
		getContentPane().add(btnLogin);
		
		JCheckBox chckbxSaveId = new JCheckBox("아이디 저장");
		chckbxSaveId.setBounds(95, 159, 115, 23);
		getContentPane().add(chckbxSaveId);
		
		JButton btnSignIn = new JButton("회원가입...");
		btnSignIn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				WinSignIn signin = new WinSignIn();
				signin.setModal(true);
				signin.setVisible(true);
			}
		});
		btnSignIn.setBounds(81, 192, 97, 23);
		getContentPane().add(btnSignIn);
		
		JButton btnFindIdPw = new JButton("아이디/비밀번호 찾기...");
		btnFindIdPw.setBounds(190, 192, 169, 23);
		getContentPane().add(btnFindIdPw);
		
		JSeparator separator = new JSeparator();
		separator.setBounds(38, 79, 352, 2);
		getContentPane().add(separator);
		
		JLabel lblLogin = new JLabel("로그인");
		lblLogin.setFont(new Font("맑은 고딕", Font.BOLD, 27));
		lblLogin.setBounds(38, 25, 124, 44);
		getContentPane().add(lblLogin);
		
		tfPw = new JTextField();
		tfPw.setBounds(134, 120, 116, 21);
		getContentPane().add(tfPw);
		tfPw.setColumns(10);

	}
}
