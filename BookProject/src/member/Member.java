package member;

import java.awt.EventQueue;
import java.awt.Image;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JDialog;
import javax.swing.JFileChooser;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Color;
import javax.swing.JTextField;
import javax.swing.filechooser.FileNameExtensionFilter;

import util.WinCalendar;
import util.WinSearchDoro;

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
import java.awt.event.ItemListener;
import java.awt.event.ItemEvent;

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
	private JButton btnMemberChoice;
	private int number; // type 값 : 1(등록) 2(탈퇴) 3(변경) 4(검색)
	private JCheckBox chkSolarLunar;
	private boolean bOne;
	private int zero;
	private String sEmail;
	
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
		lblPic.setBounds(22, 20, 150, 200);
		add(lblPic);
		
		JLabel lblId = new JLabel("아이디 :");
		lblId.setBounds(211, 46, 57, 15);
		add(lblId);
		
		tfId = new JTextField();
		tfId.setBackground(Color.LIGHT_GRAY);
		tfId.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if(e.getKeyCode() == KeyEvent.VK_ENTER) {
					tfPassword.requestFocus();
				}
			}
		});
		tfId.setBounds(280, 43, 116, 21);
		add(tfId);
		tfId.setColumns(10);
		
		JLabel lblPassword = new JLabel("비밀번호 :");
		lblPassword.setBounds(211, 77, 57, 15);
		add(lblPassword);
		
		JLabel lblPasswordCheck = new JLabel("비밀번호 확인 :");
		lblPasswordCheck.setBounds(184, 108, 84, 15);
		add(lblPasswordCheck);
		
		JLabel lblName = new JLabel("이름 :");
		lblName.setBounds(211, 139, 57, 15);
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
		tfName.setBounds(280, 136, 116, 21);
		add(tfName);
		
		JButton btnDup = new JButton("중복확인");
		btnDup.setBounds(405, 42, 97, 23);
		add(btnDup);
		
		JLabel lblEmail = new JLabel("이메일 :");
		lblEmail.setBounds(211, 167, 57, 15);
		add(lblEmail);
		
		tfEmail = new JTextField();
		tfEmail.setBackground(Color.LIGHT_GRAY);
		tfEmail.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if(e.getKeyCode() == KeyEvent.VK_ENTER) {
					sEmail = tfEmail.getText() + "@" + cbEmail.getSelectedItem();
					
					//=================================
					try {
						Class.forName("com.mysql.cj.jdbc.Driver");
						Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/sqlDB","root","1234");						

						String sql = "SELECT COUNT(*) FROM memberTBL WHERE email=?";
						
						PreparedStatement pstmt = con.prepareStatement(sql);
						
						pstmt.setString(1, sEmail);
						
						ResultSet rs = pstmt.executeQuery();
						
						bOne = false;
						zero = 1;
						while(rs.next()) {
							if(rs.getInt(1) == 1) {
								bOne = true;
							} else if(rs.getInt(1) > 1) {
								bOne = false;
							} else {
								zero = 0;
							}
						}
						
						System.out.println(sql);
						
//						if(pstmt.executeUpdate() >= 0) {
//							JOptionPane.showMessageDialog(null, "가입되었습니다.");
//						} else {
//							JOptionPane.showMessageDialog(null, "오류!");
//						}
						
					} catch (ClassNotFoundException | SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					if(zero == 1 && bOne) {
						showRecord();
					}
					//=================================
				}
			}
		});
		tfEmail.setColumns(10);
		tfEmail.setBounds(280, 164, 116, 21);
		add(tfEmail);
		
		JLabel lblMobile = new JLabel("전화번호 :");
		lblMobile.setBounds(211, 198, 57, 15);
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
		tfMobile.setBounds(280, 195, 116, 21);
		add(tfMobile);
		
		JLabel lblBirth = new JLabel("생일 :");
		lblBirth.setBounds(70, 240, 57, 15);
		add(lblBirth);
		
		tfBirth = new JTextField();
		tfBirth.setColumns(10);
		tfBirth.setBounds(112, 233, 116, 21);
		add(tfBirth);
		
		JLabel lblAddress = new JLabel("주소 :");
		lblAddress.setBounds(70, 270, 40, 15);
		add(lblAddress);
		
		tfAddress = new JTextField();
		tfAddress.setColumns(10);
		tfAddress.setBounds(112, 267, 284, 21);
		add(tfAddress);
		
		tfAddressDetail = new JTextField();
		tfAddressDetail.setColumns(10);
		tfAddressDetail.setBounds(112, 298, 284, 21);
		add(tfAddressDetail);
		
		JButton btnSearchAddress = new JButton("주소 찾기...");
		btnSearchAddress.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				WinSearchDoro winSearchDoro = new WinSearchDoro();
				winSearchDoro.setModal(true);
				winSearchDoro.setVisible(true);
				
				tfAddress.setText(winSearchDoro.getAddress());
				tfAddressDetail.requestFocus();
			}
		});
		btnSearchAddress.setBounds(405, 266, 97, 53);
		add(btnSearchAddress);
		
		chkSolarLunar = new JCheckBox("양력");
		chkSolarLunar.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				if(chkSolarLunar.isSelected()) {
					chkSolarLunar.setText("음력");
					tfSolarBirth.setText("나중에...");
				} else {
					chkSolarLunar.setText("양력");
					tfSolarBirth.setText(tfBirth.getText());
				}
				tfAddress.requestFocus();
			}
		});
		chkSolarLunar.setBounds(237, 232, 55, 23);
		add(chkSolarLunar);
		
		JButton btnCalendar = new JButton("달력...");
		btnCalendar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				WinCalendar winCalendar = new WinCalendar();
				winCalendar.setModal(true);
				winCalendar.setVisible(true);
				tfBirth.setText(winCalendar.getDate());
				tfSolarBirth.setText(winCalendar.getDate());
				
				tfAddress.requestFocus();
			}
		});
		btnCalendar.setBounds(299, 232, 97, 23);
		add(btnCalendar);
		
		JLabel lblAt = new JLabel("@");
		lblAt.setBounds(405, 167, 12, 15);
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
		cbEmail.setBounds(429, 163, 90, 23);
		add(cbEmail);
		
		tfSolarBirth = new JTextField();
		tfSolarBirth.setColumns(10);
		tfSolarBirth.setBounds(403, 234, 116, 21);
		add(tfSolarBirth);
		
		JLabel lblAddress_1 = new JLabel("상세 주소 :");
		lblAddress_1.setBounds(42, 301, 68, 15);
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
		tfPassword.setBounds(280, 74, 116, 21);
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
		tfPassword_1.setBounds(280, 105, 116, 21);
		add(tfPassword_1);
		
		btnMemberChoice = new JButton("회원 등록"); // 회원 등록/탈퇴/변경/조회로 바뀜
		btnMemberChoice.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(number == 1) {
					insertRecord();
				} else if(number == 2) {
					deleteRecord();
				} else if(number == 3) {
					
				} else if(number == 4) {
					
				}
			}
		});
		btnMemberChoice.setBounds(224, 329, 97, 32);
		add(btnMemberChoice);

	}
	protected void showRecord() {
		//=================================
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/sqlDB","root","1234");						
	
			Statement stmt = con.createStatement();
			
			String sql = "SELECT * FROM memberTBL WHERE email='" + sEmail + "'";
			ResultSet rs = stmt.executeQuery(sql);
			
			while(rs.next()) {
				tfId.setText(rs.getString("id"));
				tfName.setText(rs.getString("name"));
				tfMobile.setText(rs.getString("mobile"));
				tfBirth.setText(rs.getString("birth"));
				tfAddress.setText(rs.getString("address"));
				if(rs.getInt("lsType") == 0) {
					chkSolarLunar.setSelected(false);
				} else {
					chkSolarLunar.setSelected(true);
				}
			}
			
	//					if(pstmt.executeUpdate() >= 0) {
	//						JOptionPane.showMessageDialog(null, "탈퇴하셨습니다.");
	//					} else {
	//						JOptionPane.showMessageDialog(null, "오류!");
	//					}
			
		} catch (ClassNotFoundException | SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		//=================================
	}
	protected void deleteRecord() {
		//=================================
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/sqlDB","root","1234");						

			String sql = "DELETE FROM memberTBL WHERE id=?";
			
			PreparedStatement pstmt = con.prepareStatement(sql);
			
			pstmt.setString(1, tfId.getText());
			
			System.out.println(sql);
			
//			if(pstmt.executeUpdate() >= 0) {
//				JOptionPane.showMessageDialog(null, "탈퇴하셨습니다.");
//			} else {
//				JOptionPane.showMessageDialog(null, "오류!");
//			}
			
		} catch (ClassNotFoundException | SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		//=================================
	}
	protected void insertRecord() {
		//=================================
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/sqlDB","root","1234");						

			String sql = "INSERT INTO memberTBL VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?)";
			
			PreparedStatement pstmt = con.prepareStatement(sql);

			pstmt.setString(1, tfId.getText());
			pstmt.setString(2, new String(tfPassword.getPassword()));
			pstmt.setString(3, tfName.getText());
			
			String email = tfEmail.getText() + "@" + cbEmail.getSelectedItem();
			pstmt.setString(4, email);
			
			pstmt.setString(5, tfMobile.getText());
			pstmt.setString(6, tfBirth.getText());
			
			String check = "0";
			if(!chkSolarLunar.isSelected()) {
				check = "1";
			}
			pstmt.setString(7, check);
			
			String address = tfAddress.getText() + " " + tfAddressDetail.getText();
			pstmt.setString(8, address);
			pstmt.setString(9, filePath);
			
			System.out.println(sql);
			
//			if(pstmt.executeUpdate() >= 0) {
//				JOptionPane.showMessageDialog(null, "가입되었습니다.");
//			} else {
//				JOptionPane.showMessageDialog(null, "오류!");
//			}
			
		} catch (ClassNotFoundException | SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		//=================================
	}
	public Member(int type) {
		this();
		if(type == 1) {
			btnMemberChoice.setText("회원 가입");
		} else if(type == 2) {
			btnMemberChoice.setText("회원 탈퇴");
		} else if(type == 3) {
			btnMemberChoice.setText("회원 정보 변경");
		} else if(type == 4) {
			btnMemberChoice.setText("회원 검색");
		}
		number = type;
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
