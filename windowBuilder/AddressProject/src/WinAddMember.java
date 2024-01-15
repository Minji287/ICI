import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Calendar;
import java.util.Vector;

import javax.imageio.ImageIO;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class WinAddMember extends JDialog {
	private JTextField tfName;
	private JTextField tfEmailId;
	private JTextField tfBirth;
	private JTextField tfAddress;
	private JTextField tfMobile2;
	private JTextField tfMobile3;
	private JComboBox cbEmailCompany;
	private JComboBox cbMobile1;
	private JComboBox cbGradYear;
	protected String filePath;
	private JLabel lblPic;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					WinAddMember dialog = new WinAddMember();
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
	public WinAddMember() {
		setTitle("회원가입");
		setBounds(100, 100, 490, 353);
		getContentPane().setLayout(null);
		
		lblPic = new JLabel("");
		lblPic.setIcon(new ImageIcon(WinAddMember.class.getResource("/images/userpic.png")));
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
			            image = image.getScaledInstance(120, 140, image.SCALE_SMOOTH);
			            icon = new ImageIcon(image);
			            lblPic.setIcon(icon);
					}
				}
			}
		});
		lblPic.setToolTipText("더블클릭해서 사진 선택");
		lblPic.setOpaque(true);
		lblPic.setBackground(new Color(255, 255, 255));
		lblPic.setBounds(22, 31, 120, 140);
		getContentPane().add(lblPic);
		
		JLabel lblName = new JLabel("이름:");
		lblName.setBounds(163, 31, 57, 15);
		getContentPane().add(lblName);
		
		tfName = new JTextField();
		tfName.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if(e.getKeyCode() == KeyEvent.VK_ENTER) {
					tfMobile2.requestFocus();
				}
			}
		});
		tfName.setBounds(232, 28, 116, 21);
		getContentPane().add(tfName);
		tfName.setColumns(10);
		
		JLabel lblMobile = new JLabel("전화번호:");
		lblMobile.setBounds(163, 62, 57, 15);
		getContentPane().add(lblMobile);
		
		JLabel lblEmail = new JLabel("이메일:");
		lblEmail.setBounds(163, 95, 57, 15);
		getContentPane().add(lblEmail);
		
		tfEmailId = new JTextField();
		tfEmailId.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if(e.getKeyCode() == KeyEvent.VK_ENTER) {
					tfBirth.requestFocus();
				}
			}
		});
		tfEmailId.setColumns(10);
		tfEmailId.setBounds(232, 92, 88, 21);
		getContentPane().add(tfEmailId);
		
		JLabel lblBirth = new JLabel("생년월일:");
		lblBirth.setBounds(163, 132, 57, 15);
		getContentPane().add(lblBirth);
		
		tfBirth = new JTextField();
		tfBirth.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if(e.getKeyCode() == KeyEvent.VK_ENTER) {
					showCalendar();
				}
			}
		});
		tfBirth.setHorizontalAlignment(SwingConstants.RIGHT);
		tfBirth.setColumns(10);
		tfBirth.setBounds(232, 129, 116, 21);
		getContentPane().add(tfBirth);
		
		JLabel lblGradYear = new JLabel("졸업년도:");
		lblGradYear.setBounds(163, 167, 57, 15);
		getContentPane().add(lblGradYear);
		
		JLabel lblAddress = new JLabel("주소:");
		lblAddress.setBounds(43, 211, 57, 15);
		getContentPane().add(lblAddress);
		
		tfAddress = new JTextField();
		tfAddress.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if(e.getKeyCode() == KeyEvent.VK_ENTER) {
					if(isOk()) {
						insertRecord();
						clearAll();
					} else {
						JOptionPane.showMessageDialog(null, "필수 입력란을 확인하세요\n이름, 전화번호, 이메일, 생년월일, 주소");
					}
				}
			}
		});
		tfAddress.setColumns(10);
		tfAddress.setBounds(83, 208, 218, 21);
		getContentPane().add(tfAddress);
		
		JButton btnSearchDoro = new JButton("도로명찾기...");
		btnSearchDoro.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				WinSearchDoro winSearchDoro = new WinSearchDoro();
				winSearchDoro.setModal(true);
				winSearchDoro.setVisible(true);
				tfAddress.setText(winSearchDoro.getAddress() + " ");
				tfAddress.requestFocus();
			}
		});
		btnSearchDoro.setBounds(313, 207, 122, 23);
		getContentPane().add(btnSearchDoro);
		
		JButton btnCalender = new JButton("달력...");
		btnCalender.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				showCalendar();
			}
		});
		btnCalender.setBounds(357, 127, 97, 23);
		getContentPane().add(btnCalender);
		
		JButton btnInsert = new JButton("회원가입");
		btnInsert.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(isOk()) {
					insertRecord();
					clearAll();
				} else {
					JOptionPane.showMessageDialog(null, "필수 입력란을 확인하세요\n이름, 전화번호, 이메일, 생년월일, 주소");
				}
			}
		});
		btnInsert.setBounds(83, 252, 128, 35);
		getContentPane().add(btnInsert);
		
		cbGradYear = new JComboBox();
		cbGradYear.setBounds(232, 163, 116, 19);
		getContentPane().add(cbGradYear);
		
		cbMobile1 = new JComboBox();
		cbMobile1.setModel(new DefaultComboBoxModel(new String[] {"010", "02", "032", "031"}));
		cbMobile1.setBounds(232, 58, 58, 23);
		getContentPane().add(cbMobile1);
		
		tfMobile2 = new JTextField();
		tfMobile2.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if(e.getKeyCode() == KeyEvent.VK_ENTER) {
					tfMobile3.requestFocus();
				}
			}
		});
		tfMobile2.setHorizontalAlignment(SwingConstants.CENTER);
		tfMobile2.setColumns(10);
		tfMobile2.setBounds(302, 59, 63, 21);
		getContentPane().add(tfMobile2);
		
		tfMobile3 = new JTextField();
		tfMobile3.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if(e.getKeyCode() == KeyEvent.VK_ENTER) {
					tfEmailId.requestFocus();
				}
			}
		});
		tfMobile3.setHorizontalAlignment(SwingConstants.CENTER);
		tfMobile3.setColumns(10);
		tfMobile3.setBounds(372, 59, 63, 21);
		getContentPane().add(tfMobile3);
		
		JLabel lblNewLabel = new JLabel("@");
		lblNewLabel.setBounds(325, 95, 29, 15);
		getContentPane().add(lblNewLabel);
		
		cbEmailCompany = new JComboBox();
		cbEmailCompany.setEditable(true);
		cbEmailCompany.setModel(new DefaultComboBoxModel(new String[] {"naver.com", "daum.net", "gmail.com", "ici.com", "nate.com", "직접입력"}));
		cbEmailCompany.setBounds(345, 91, 97, 23);
		getContentPane().add(cbEmailCompany);
		
		JButton btnExit = new JButton("종료");
		btnExit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		btnExit.setBounds(232, 252, 128, 35);
		getContentPane().add(btnExit);
		
		
		//=========================================
		Calendar cal = Calendar.getInstance();
		int year = cal.get(Calendar.YEAR);
		
		for(int i = 2000; i <= year; i++) {
			cbGradYear.addItem(i);
			cbGradYear.setSelectedItem(year);
		}
		
	}

	protected void showCalendar() {
		WinCalendar winCalendar = new WinCalendar();
		winCalendar.setModal(true);
		winCalendar.setVisible(true);
		tfBirth.setText(winCalendar.getDate());
		tfAddress.requestFocus();
	}

	protected void clearAll() {
		tfName.setText("");
		tfMobile2.setText("");
		tfMobile3.setText("");
		tfEmailId.setText("");
		tfBirth.setText("");
		tfAddress.setText("");
		tfName.requestFocus();
		
		filePath = "C:\\eclipse-workspace\\WindowBuilder\\AddressProject\\src\\images\\userpic.png";
		ImageIcon icon = new ImageIcon(filePath);
		Image image = icon.getImage();
		image = image.getScaledInstance(120, 140, image.SCALE_SMOOTH);
		icon = new ImageIcon(image);
		lblPic.setIcon(icon);
		
	}

	protected boolean isOk() {
		boolean bOk = true;
		
		if(tfName.getText().equals("")) {
			bOk = false;
		} else if(tfMobile2.getText().equals("")) {
			bOk = false;
		} else if(tfMobile3.getText().equals("")) {
			bOk = false;
		} else if(tfEmailId.getText().equals("")) {
			bOk = false;
		} else if(tfBirth.getText().equals("")) {
			bOk = false;
		} else if(tfAddress.getText().equals("")) {
			bOk = false;
		}
		
		return bOk;
	}

	protected void insertRecord() {
		String sName = tfName.getText();
		String sMobile = cbMobile1.getSelectedItem().toString() + tfMobile2.getText() + tfMobile3.getText();
		String sEmail = tfEmailId.getText() + "@" + cbEmailCompany.getSelectedItem().toString();
		String sBirth = tfBirth.getText();
		String sGradYear = cbGradYear.getSelectedItem().toString();
		String sAddress = tfAddress.getText();
		String sPath = filePath;
		
		//=================================
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/sqlDB","root","1234");						
			Statement stmt = con.createStatement();
			
			String sql = "INSERT INTO addrTBL VALUES(null,'" + sName + "','" + sMobile + "','" + sEmail
					+ "','" + sAddress + "','" + sBirth + "'," + sGradYear + ",'" + sPath + "')";
			
			if(stmt.executeUpdate(sql) < 1) {
				JOptionPane.showMessageDialog(null, "회원 가입 오류");
			}
			
		} catch (ClassNotFoundException | SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		//=================================
	}
}
