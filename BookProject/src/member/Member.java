package member;

import java.awt.EventQueue;
import java.awt.Image;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JDialog;
import javax.swing.JFileChooser;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Color;
import javax.swing.JTextField;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;

import java.awt.SystemColor;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JPasswordField;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Member extends JPanel {
	private JTextField tfId;
	private JTextField tfName;
	private JTextField tfEmail;
	private JTextField tfMobile;
	private JTextField tfBirth;
	private JTextField tfAddress;
	private JTextField tfAddressDetail;
	private JTextField tfSolarBirth;
	private String filePath;
	private JPasswordField tfPassword;
	private JPasswordField tfPassword_1;
	private JComboBox cbEmail;
	/**
	 * Create the dialog.
	 */
	public Member() {
		setBackground(SystemColor.inactiveCaption);
		setBounds(100, 100, 549, 389);
		setLayout(null);
		
		JLabel lblPic = new JLabel("");
		lblPic.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if(e.getClickCount() == 2) {
					JFileChooser chooser = new JFileChooser("C:\\Users\\ici\\Pictures");
					FileNameExtensionFilter filter = new FileNameExtensionFilter("이미지파일", "jpg" , "png", "gif");
					chooser.setFileFilter(filter);
					if(chooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {
						
						filePath = chooser.getSelectedFile().getPath();
						
						filePath = filePath.replaceAll("\\\\", "\\\\\\\\");
						
						ImageIcon icon = new ImageIcon(filePath);
			            Image image = icon.getImage();
			            image = image.getScaledInstance(150, 200, image.SCALE_SMOOTH);
			            icon = new ImageIcon(image);
			            lblPic.setIcon(icon);
					}
				}
			}
		});
		lblPic.setBackground(Color.YELLOW);
		lblPic.setOpaque(true);
		lblPic.setBounds(24, 27, 150, 200);
		add(lblPic);
		
		JLabel lblId = new JLabel("아이디 :");
		lblId.setBounds(213, 53, 57, 15);
		add(lblId);
		
		tfId = new JTextField();
		tfId.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if(e.getKeyCode() == KeyEvent.VK_ENTER) {
					tfPassword.requestFocus();
				}
			}
		});
		tfId.setBounds(282, 50, 116, 21);
		add(tfId);
		tfId.setColumns(10);
		
		JLabel lblPassword = new JLabel("비밀번호 :");
		lblPassword.setBounds(213, 84, 57, 15);
		add(lblPassword);
		
		JLabel lblPasswordCheck = new JLabel("비밀번호 확인 :");
		lblPasswordCheck.setBounds(186, 115, 84, 15);
		add(lblPasswordCheck);
		
		JLabel lblName = new JLabel("이름 :");
		lblName.setBounds(213, 146, 57, 15);
		add(lblName);
		
		tfName = new JTextField();
		tfName.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if(e.getKeyCode() == KeyEvent.VK_ENTER) {
					tfEmail.requestFocus();
				}
			}
		});
		tfName.setColumns(10);
		tfName.setBounds(282, 143, 116, 21);
		add(tfName);
		
		JButton btnDup = new JButton("중복확인");
		btnDup.setBounds(407, 49, 97, 23);
		add(btnDup);
		
		JLabel lblEmail = new JLabel("이메일 :");
		lblEmail.setBounds(213, 174, 57, 15);
		add(lblEmail);
		
		tfEmail = new JTextField();
		tfEmail.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if(e.getKeyCode() == KeyEvent.VK_ENTER) {
					cbEmail.requestFocus();
				}
			}
		});
		tfEmail.setColumns(10);
		tfEmail.setBounds(282, 171, 116, 21);
		add(tfEmail);
		
		JLabel lblMobile = new JLabel("전화번호 :");
		lblMobile.setBounds(213, 205, 57, 15);
		add(lblMobile);
		
		tfMobile = new JTextField();
		tfMobile.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if(e.getKeyCode() == KeyEvent.VK_ENTER) {
					// 하이픈 다 없애고 전화번호의 개수가 11개인지 확인 3/4/4
					if(isCorrect(tfMobile.getText())) {
						tfBirth.requestFocus();
					} else {
						JOptionPane.showMessageDialog(null, "전화번호에 문제가 있습니다.");
						tfMobile.setSelectionStart(0);
						tfMobile.setSelectionEnd(tfMobile.getText().length());
					}
				}
			}
		});
		tfMobile.setColumns(10);
		tfMobile.setBounds(282, 202, 116, 21);
		add(tfMobile);
		
		JLabel lblBirth = new JLabel("생일 :");
		lblBirth.setBounds(72, 247, 57, 15);
		add(lblBirth);
		
		tfBirth = new JTextField();
		tfBirth.setColumns(10);
		tfBirth.setBounds(114, 240, 116, 21);
		add(tfBirth);
		
		JLabel lblAddress = new JLabel("주소 :");
		lblAddress.setBounds(72, 277, 40, 15);
		add(lblAddress);
		
		tfAddress = new JTextField();
		tfAddress.setColumns(10);
		tfAddress.setBounds(114, 274, 284, 21);
		add(tfAddress);
		
		tfAddressDetail = new JTextField();
		tfAddressDetail.setColumns(10);
		tfAddressDetail.setBounds(114, 305, 284, 21);
		add(tfAddressDetail);
		
		JButton btnSearchAddress = new JButton("주소 찾기...");
		btnSearchAddress.setBounds(407, 273, 97, 53);
		add(btnSearchAddress);
		
		JCheckBox chkSolarLunar = new JCheckBox("양력");
		chkSolarLunar.setBounds(239, 239, 55, 23);
		add(chkSolarLunar);
		
		JButton btnCalendar = new JButton("달력...");
		btnCalendar.setBounds(301, 239, 97, 23);
		add(btnCalendar);
		
		JLabel lblAt = new JLabel("@");
		lblAt.setBounds(407, 174, 12, 15);
		add(lblAt);
		
		cbEmail = new JComboBox();
		cbEmail.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(cbEmail.getSelectedItem().toString().equals("직접 입력")) {
					cbEmail.setSelectedItem("");
				} else {
					tfMobile.requestFocus();
				}
			}
		});
		cbEmail.setModel(new DefaultComboBoxModel(new String[] {"naver.com", "gmail.com", "daum.net", "korea.com", "paran.com", "ici.re.kr", "sorry.com", "happy.net", "직접 입력"}));
		cbEmail.setEditable(true);
		cbEmail.setBounds(431, 170, 90, 23);
		add(cbEmail);
		
		tfSolarBirth = new JTextField();
		tfSolarBirth.setColumns(10);
		tfSolarBirth.setBounds(405, 241, 116, 21);
		add(tfSolarBirth);
		
		JLabel lblAddress_1 = new JLabel("상세 주소 :");
		lblAddress_1.setBounds(44, 308, 68, 15);
		add(lblAddress_1);
		
		tfPassword = new JPasswordField();
		tfPassword.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if(e.getKeyCode() == KeyEvent.VK_ENTER) {
					String pw2 = new String(tfPassword_1.getPassword());
					
					tfPassword_1.setSelectionStart(0);
					tfPassword_1.setSelectionEnd(pw2.length());
					tfPassword_1.requestFocus();
				}
			}
		});
		tfPassword.setBounds(282, 81, 116, 21);
		add(tfPassword);
		
		tfPassword_1 = new JPasswordField();
		tfPassword_1.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if(e.getKeyCode() == KeyEvent.VK_ENTER) {
					String pw1 = new String(tfPassword.getPassword());
					String pw2 = new String(tfPassword_1.getPassword());
					
					if(pw1.equals(pw2)) {
						tfName.requestFocus();
					} else {
						JOptionPane.showMessageDialog(null, "암호가 일치하지 않습니다.");
						tfPassword.setSelectionStart(0);
						tfPassword.setSelectionEnd(pw1.length());
						tfPassword.requestFocus();
					}
				}
			}
		});
		tfPassword_1.setBounds(282, 112, 116, 21);
		add(tfPassword_1);

	}
	protected boolean isCorrect(String text) {
		text = text.replace("-", "");
		if(text.length() == 8) {
			text = "010" + text;
		}
		text = text.substring(0, 3) + "-" + text.substring(3, 7) + "-" + text.substring(7);
		if(text.length() == 13) {
			tfMobile.setText(text);
			return true;
		} else {
			return false;
		}
	}
	protected int getCountHyphen(String text) {
		int count = 0;
		
		for(int i = 0; i < text.length(); i++) {
			if(text.charAt(i) == '-') {
				count++;
			}
		}
		return count;
	}
}
