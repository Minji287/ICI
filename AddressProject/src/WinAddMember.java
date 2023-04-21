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
import java.util.Calendar;

import javax.imageio.ImageIO;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.filechooser.FileNameExtensionFilter;

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
		setBounds(100, 100, 490, 353);
		getContentPane().setLayout(null);
		
		JLabel lblPic = new JLabel("");
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
				WinCalendar winCalendar = new WinCalendar();
				winCalendar.setModal(true);
				winCalendar.setVisible(true);
				tfBirth.setText(winCalendar.getDate());
			}
		});
		btnCalender.setBounds(357, 127, 97, 23);
		getContentPane().add(btnCalender);
		
		JButton btnInsert = new JButton("회원가입");
		btnInsert.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				insertRecord();
			}
		});
		btnInsert.setBounds(184, 252, 128, 35);
		getContentPane().add(btnInsert);
		
		cbGradYear = new JComboBox();
		cbGradYear.setBounds(232, 163, 116, 19);
		getContentPane().add(cbGradYear);
		
		cbMobile1 = new JComboBox();
		cbMobile1.setModel(new DefaultComboBoxModel(new String[] {"010"}));
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
		
		
		//=========================================
		Calendar cal = Calendar.getInstance();
		int year = cal.get(Calendar.YEAR);
		
		for(int i = 2000; i <= year; i++) {
			cbGradYear.addItem(i);
			cbGradYear.setSelectedItem(year);
		}
		
	}

	protected void insertRecord() {
		String sName = tfName.getText();
		String sMobile = cbMobile1.getSelectedItem().toString() + tfMobile2.getText() + tfMobile3.getText();
		String sEmail = tfEmailId.getText() + "@" + cbEmailCompany.getSelectedItem().toString();
		String sBirth = tfBirth.getText();
		String sGradYear = cbGradYear.getSelectedItem().toString();
		String sAddress = tfAddress.getText();
		String sPath = filePath;
		
		String sql = sName + "," + sMobile + "," + sEmail + "," + sBirth + "," + sGradYear + "," + sAddress + "," + sPath;
		
	}
}
