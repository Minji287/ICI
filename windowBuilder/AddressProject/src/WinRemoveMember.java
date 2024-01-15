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
import javax.swing.table.DefaultTableModel;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class WinRemoveMember extends JDialog {
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
	private String sID;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					WinRemoveMember dialog = new WinRemoveMember();
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
	public WinRemoveMember() {
		setTitle("회원탈퇴");
		setBounds(100, 100, 490, 353);
		getContentPane().setLayout(null);
		
		lblPic = new JLabel("");
		lblPic.setIcon(new ImageIcon(WinAddMember.class.getResource("/images/userpic.png")));
		lblPic.setOpaque(true);
		lblPic.setBackground(new Color(255, 255, 255));
		lblPic.setBounds(22, 31, 120, 140);
		getContentPane().add(lblPic);
		
		JLabel lblName = new JLabel("이름:");
		lblName.setBounds(163, 31, 57, 15);
		getContentPane().add(lblName);
		
		tfName = new JTextField();
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
		tfEmailId.setColumns(10);
		tfEmailId.setBounds(232, 92, 88, 21);
		getContentPane().add(tfEmailId);
		
		JLabel lblBirth = new JLabel("생년월일:");
		lblBirth.setBounds(163, 132, 57, 15);
		getContentPane().add(lblBirth);
		
		tfBirth = new JTextField();
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
		tfAddress.setColumns(10);
		tfAddress.setBounds(83, 208, 218, 21);
		getContentPane().add(tfAddress);
		
		JButton btnRemove = new JButton("회원탈퇴");
		btnRemove.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				deleteMember(sID);
			}
		});
		btnRemove.setBounds(83, 252, 128, 35);
		getContentPane().add(btnRemove);
		
		cbGradYear = new JComboBox();
		cbGradYear.setBounds(232, 163, 116, 19);
		getContentPane().add(cbGradYear);
		
		cbMobile1 = new JComboBox();
		cbMobile1.setModel(new DefaultComboBoxModel(new String[] {"010", "02", "032", "031"}));
		cbMobile1.setBounds(232, 58, 58, 23);
		getContentPane().add(cbMobile1);
		
		tfMobile2 = new JTextField();
		tfMobile2.setHorizontalAlignment(SwingConstants.CENTER);
		tfMobile2.setColumns(10);
		tfMobile2.setBounds(302, 59, 63, 21);
		getContentPane().add(tfMobile2);
		
		tfMobile3 = new JTextField();
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
		
		JButton btnSearchMember = new JButton("찾기...");
		btnSearchMember.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				WinSearchMember winSearchMember = new WinSearchMember();
				winSearchMember.setModal(true);
				winSearchMember.setVisible(true);

				sID = winSearchMember.getID();
				showRecordID(sID);
			}
		});
		btnSearchMember.setBounds(360, 27, 82, 23);
		getContentPane().add(btnSearchMember);
		
		//=========================================
		Calendar cal = Calendar.getInstance();
		int year = cal.get(Calendar.YEAR);
		
		for(int i = 2000; i <= year; i++) {
			cbGradYear.addItem(i);
			cbGradYear.setSelectedItem(year);
		}
	}
	
	protected void clearAll() {
		tfName.setText("");
		tfMobile2.setText("");
		tfMobile3.setText("");
		tfEmailId.setText("");
		tfBirth.setText("");
		tfAddress.setText("");
		tfName.requestFocus();
		
		filePath = "";
		ImageIcon icon = new ImageIcon(filePath);
		lblPic.setIcon(icon);
		
	}

	protected void deleteMember(String id) {
		//=================================
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/sqlDB","root","1234");						
			Statement stmt = con.createStatement();
			
			String sql = "DELETE FROM addrTBL WHERE idx=" + id;
			
			if(stmt.executeUpdate(sql) < 1) {
				JOptionPane.showMessageDialog(null, "삭제 오류");
			} else {
				clearAll();
				JOptionPane.showMessageDialog(null, "삭제되었습니다.");
			}
			
		} catch (ClassNotFoundException | SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		//=================================

	}

	protected void showRecordID(String id) {
		// id를 이용해서 테이블에서 레코드를 찾은 후, 각 텍스트 필드에 넣는다.
		//=================================
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/sqlDB","root","1234");						
			Statement stmt = con.createStatement();
			
			String sql = "SELECT * FROM addrTBL WHERE idx=" + id;
			ResultSet rs = stmt.executeQuery(sql);
			
			while(rs.next()) {
				tfName.setText(rs.getString("name"));
							
				tfAddress.setText(rs.getString("address"));
				tfBirth.setText(rs.getString("birth"));
				cbGradYear.setSelectedItem(rs.getInt("gradYear"));
				
				ImageIcon icon = new ImageIcon(rs.getString("pic"));
				Image image = icon.getImage();
				image = image.getScaledInstance(120, 140, Image.SCALE_SMOOTH);
				icon = new ImageIcon(image);
				lblPic.setIcon(icon);
				
				String sEmail[] = rs.getString("email").split("@");
				tfEmailId.setText(sEmail[0]);
				cbEmailCompany.setSelectedItem(sEmail[1]);
				
				String sMobile = rs.getString("mobile");
				if(sMobile.substring(0, 2).equals("02")) {
					cbMobile1.setSelectedItem("02");  //021234567
					tfMobile2.setText(sMobile.substring(2,sMobile.length()-4));
					tfMobile3.setText(sMobile.substring(sMobile.length()-4));
				}else {
					cbMobile1.setSelectedItem(sMobile.substring(0, 3));  //010 1234 4567, 032-525-1234
					tfMobile2.setText(sMobile.substring(3,sMobile.length()-4));
					tfMobile3.setText(sMobile.substring(sMobile.length()-4));
				}
					
			}
			
		} catch (ClassNotFoundException | SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		//=================================
	}

	protected void showCalendar() {
		WinCalendar winCalendar = new WinCalendar();
		winCalendar.setModal(true);
		winCalendar.setVisible(true);
		tfBirth.setText(winCalendar.getDate());
		tfAddress.requestFocus();
	}
}
