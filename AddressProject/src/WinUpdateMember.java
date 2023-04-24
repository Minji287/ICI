import java.awt.EventQueue;
import java.awt.Image;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Calendar;

import javax.swing.JDialog;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Color;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.ActionEvent;

public class WinUpdateMember extends JDialog {
	private JTextField tfName;
	private JTextField tfEmailID;
	private JTextField tfBirth;
	private JTextField tfAddress;
	private JTextField tfMobile2;
	private JTextField tfMobile3;
	private JComboBox cbEmailCompany;
	private JLabel lblNewLabel;
	private JComboBox cbMobile1;
	private JComboBox cbGradYear;
	private JLabel lblPic;
	private String filePath;
	private int gid;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					WinUpdateMember dialog = new WinUpdateMember();
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
	public WinUpdateMember() {
		setTitle("회원 정보 변경");
		setBounds(100, 100, 510, 364);
		getContentPane().setLayout(null);
		
		lblPic = new JLabel("");
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
		lblPic.setBackground(Color.WHITE);
		lblPic.setBounds(33, 33, 120, 140);
		getContentPane().add(lblPic);
		
		JLabel lblName = new JLabel("이름:");
		lblName.setBounds(174, 33, 57, 15);
		getContentPane().add(lblName);
		
		tfName = new JTextField();
		tfName.setColumns(10);
		tfName.setBounds(243, 30, 116, 21);
		getContentPane().add(tfName);
		
		JLabel lblMobile = new JLabel("전화번호:");
		lblMobile.setBounds(174, 64, 57, 15);
		getContentPane().add(lblMobile);
		
		JLabel lblEmail = new JLabel("이메일:");
		lblEmail.setBounds(174, 97, 57, 15);
		getContentPane().add(lblEmail);
		
		tfEmailID = new JTextField();
		tfEmailID.setColumns(10);
		tfEmailID.setBounds(243, 94, 88, 21);
		getContentPane().add(tfEmailID);
		
		JLabel lblBirth = new JLabel("생년월일:");
		lblBirth.setBounds(174, 134, 57, 15);
		getContentPane().add(lblBirth);
		
		tfBirth = new JTextField();
		tfBirth.setHorizontalAlignment(SwingConstants.RIGHT);
		tfBirth.setColumns(10);
		tfBirth.setBounds(243, 131, 116, 21);
		getContentPane().add(tfBirth);
		
		JLabel lblGradYear = new JLabel("졸업년도:");
		lblGradYear.setBounds(174, 169, 57, 15);
		getContentPane().add(lblGradYear);
		
		JLabel lblAddress = new JLabel("주소:");
		lblAddress.setBounds(54, 213, 57, 15);
		getContentPane().add(lblAddress);
		
		tfAddress = new JTextField();
		tfAddress.setColumns(10);
		tfAddress.setBounds(94, 210, 218, 21);
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
		btnSearchDoro.setBounds(324, 209, 122, 23);
		getContentPane().add(btnSearchDoro);
		
		JButton btnCalender = new JButton("달력...");
		btnCalender.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				showCalendar();
			}
		});
		btnCalender.setBounds(368, 129, 97, 23);
		getContentPane().add(btnCalender);
		
		JButton btnAlter = new JButton("수정");
		btnAlter.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				updateRecord();
			}
		});
		btnAlter.setBounds(94, 254, 128, 35);
		getContentPane().add(btnAlter);
		
		cbGradYear = new JComboBox();
		cbGradYear.setBounds(243, 165, 116, 19);
		getContentPane().add(cbGradYear);
		
		cbMobile1 = new JComboBox();
		cbMobile1.setModel(new DefaultComboBoxModel(new String[] {"010", "02", "032", "031"}));
		cbMobile1.setBounds(243, 60, 58, 23);
		getContentPane().add(cbMobile1);
		
		tfMobile2 = new JTextField();
		tfMobile2.setHorizontalAlignment(SwingConstants.CENTER);
		tfMobile2.setColumns(10);
		tfMobile2.setBounds(313, 61, 63, 21);
		getContentPane().add(tfMobile2);
		
		tfMobile3 = new JTextField();
		tfMobile3.setHorizontalAlignment(SwingConstants.CENTER);
		tfMobile3.setColumns(10);
		tfMobile3.setBounds(383, 61, 63, 21);
		getContentPane().add(tfMobile3);
		
		lblNewLabel = new JLabel("@");
		lblNewLabel.setBounds(336, 97, 29, 15);
		getContentPane().add(lblNewLabel);
		
		cbEmailCompany = new JComboBox();
		cbEmailCompany.setEditable(true);
		cbEmailCompany.setBounds(356, 93, 97, 23);
		getContentPane().add(cbEmailCompany);
		
		JButton btnExit = new JButton("종료");
		btnExit.setBounds(243, 254, 128, 35);
		getContentPane().add(btnExit);
		
		//=========================================
		Calendar cal = Calendar.getInstance();
		int year = cal.get(Calendar.YEAR);
		
		for(int i = 2000; i <= year; i++) {
			cbGradYear.addItem(i);
			cbGradYear.setSelectedItem(year);
		}

	}

	protected void updateRecord() {
		String sName = tfName.getText();
		String sMobile = cbMobile1.getSelectedItem().toString() + tfMobile2.getText() + tfMobile3.getText();
		String sEmail = tfEmailID.getText() + "@" + cbEmailCompany.getSelectedItem().toString();
		String sBirth = tfBirth.getText();
		String sGradYear = cbGradYear.getSelectedItem().toString();
		String sAddress = tfAddress.getText();
		String sPath = filePath;
		
		//=================================
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/sqlDB","root","1234");						
			Statement stmt = con.createStatement();
			
			String sql = "UPDATE addrTBL SET name='" + sName + "', mobile='" + sMobile + "', address='" + sAddress + "', birth='"
			+ sBirth + "', gradYear=" + sGradYear + ", pic='" + sPath + "' WHERE idx=" + gid;
			
			if(stmt.executeUpdate(sql) < 1) {
				JOptionPane.showMessageDialog(null, "변경되지 않았습니다.");
			}
			JOptionPane.showMessageDialog(null, "변경되었습니다.");
			dispose();
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

	public WinUpdateMember(int id) {
		this();
		setTitle("회원 정보 변경: " + id);
		gid = id;
		showRecord(id); // showRecordID(id)랑 WinAddMember 클래스 이용해서 작성할 것.
	}

	private void showRecord(int id) {
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
				
				filePath = rs.getString("pic");
				filePath = filePath.replaceAll("\\\\", "\\\\\\\\");
				
				ImageIcon icon = new ImageIcon(filePath);
				Image image = icon.getImage();
				image = image.getScaledInstance(120, 140, Image.SCALE_SMOOTH);
				icon = new ImageIcon(image);
				lblPic.setIcon(icon);
				
				String sEmail[] = rs.getString("email").split("@");
				tfEmailID.setText(sEmail[0]);
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
	
	

}
