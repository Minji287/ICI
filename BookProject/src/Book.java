import java.awt.Color;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.filechooser.FileNameExtensionFilter;

public class Book extends JPanel {
	private JTextField tfISBN;
	private JTextField tfTitle;
	private JTextField tfAuthor;
	private JTextField tfPdate;
	private JTextField tfDiscount;
	private JTextArea taDescription;
	private JLabel lblImage;
	private JButton btnType;
	private JButton btnDup;
	private JButton btnCalendar;
	private JComboBox cbPublisher;
	private String filePath;
	
	public Book() {
		setBackground(new Color(230, 230, 250));
		setLayout(null);
		
		lblImage = new JLabel("");
		lblImage.addMouseListener(new MouseAdapter() {

			@Override
			public void mouseClicked(MouseEvent e) {
				if(e.getClickCount() == 2) { // 더블클릭하여 그림 선택
					JFileChooser chooser = new JFileChooser("C:\\Users\\ici\\Pictures");
					FileNameExtensionFilter filter = new FileNameExtensionFilter("그림파일", "jpg", "png", "gif");
					chooser.setFileFilter(filter);
					if(chooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {
						filePath = chooser.getSelectedFile().getPath();
						ImageIcon icon = new ImageIcon(filePath);
						Image image = icon.getImage();
						image = image.getScaledInstance(200, 250, Image.SCALE_SMOOTH);
						icon = new ImageIcon(image);
						lblImage.setIcon(icon);
					}
				}
			}
		});
		lblImage.setToolTipText("더블클릭해서 그림 선택하기");
		lblImage.setOpaque(true);
		lblImage.setBackground(Color.YELLOW);
		lblImage.setBounds(21, 30, 200, 250);
		add(lblImage);
		
		JLabel lblISBN = new JLabel("ISBN:");
		lblISBN.setBounds(233, 30, 57, 15);
		add(lblISBN);
		
		tfISBN = new JTextField();
		tfISBN.setHorizontalAlignment(SwingConstants.RIGHT);
		tfISBN.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				if(tfISBN.getText().length() == 13 && isDigit(tfISBN.getText())) {
					changeEnComps();
				} else {
					changeDisComps();
				}
			}
			@Override
			public void keyPressed(KeyEvent e) {
				if(e.getKeyCode() == KeyEvent.VK_ENTER) {
					checkDup();
				}
			}
		});
		tfISBN.setColumns(10);
		tfISBN.setBounds(302, 27, 124, 21);
		add(tfISBN);
		
		tfTitle = new JTextField();
		tfTitle.setColumns(10);
		tfTitle.setBounds(302, 72, 293, 21);
		add(tfTitle);
		
		JLabel lblTitle = new JLabel("책제목:");
		lblTitle.setBounds(233, 75, 57, 15);
		add(lblTitle);
		
		tfAuthor = new JTextField();
		tfAuthor.setColumns(10);
		tfAuthor.setBounds(302, 115, 293, 21);
		add(tfAuthor);
		
		JLabel lblAuthor = new JLabel("저자:");
		lblAuthor.setBounds(233, 118, 57, 15);
		add(lblAuthor);
		
		JLabel lblPublisher = new JLabel("출판사:");
		lblPublisher.setBounds(233, 165, 57, 15);
		add(lblPublisher);
		
		tfPdate = new JTextField();
		tfPdate.setHorizontalAlignment(SwingConstants.RIGHT);
		tfPdate.setColumns(10);
		tfPdate.setBounds(302, 211, 145, 21);
		add(tfPdate);
		
		JLabel lblPdate = new JLabel("출판일:");
		lblPdate.setBounds(233, 214, 57, 15);
		add(lblPdate);
		
		tfDiscount = new JTextField();
		tfDiscount.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) { // F2: 0000, F3: 000, F4: 00
				
				String amount = tfDiscount.getText();
				tfDiscount.setText(amount.replaceAll(",", ""));
				
				if(e.getKeyCode() == KeyEvent.VK_F2) {
					tfDiscount.setText(tfDiscount.getText() + "0000");
				} else if (e.getKeyCode() == KeyEvent.VK_F3) {
					tfDiscount.setText(tfDiscount.getText() + "000");
				} else if (e.getKeyCode() == KeyEvent.VK_F4) {
					tfDiscount.setText(tfDiscount.getText() + "00");
				}
				
				amount = tfDiscount.getText();
				amount = amount.replaceAll("\\B(?=(\\d{3})+(?!\\d))", ",");
				tfDiscount.setText(amount);
			}
		});
		tfDiscount.setHorizontalAlignment(SwingConstants.RIGHT);
		tfDiscount.setColumns(10);
		tfDiscount.setBounds(302, 259, 145, 21);
		add(tfDiscount);
		
		JLabel lblDiscount = new JLabel("가격:");
		lblDiscount.setBounds(233, 262, 57, 15);
		add(lblDiscount);
		
		JLabel lblDescription = new JLabel("<html><p style=\"font-size:12px\">책 소개:</FONT></html>");
		lblDescription.setBounds(21, 290, 70, 24);
		add(lblDescription);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(21, 324, 574, 257);
		add(scrollPane);
		
		taDescription = new JTextArea();
		taDescription.setLineWrap(true);
		scrollPane.setViewportView(taDescription);
		
		btnType = new JButton();
		btnType.setEnabled(false);
		btnType.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(btnType.getText().equals("도서 추가")) {
					insertBook();
				} else if(btnType.getText().equals("도서 삭제")) {
					deleteBook();
				} else if(btnType.getText().equals("도서 변경")) {
					updateBook();
				}
			}
		});
		btnType.setBounds(498, 294, 97, 23);
		add(btnType);
		
		btnDup = new JButton("중복확인...");
		btnDup.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				checkDup();
			}
		});
		btnDup.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if(e.getKeyCode() == KeyEvent.VK_ENTER) {
					checkDup();
				}
			}
		});
		btnDup.setEnabled(false);
		btnDup.setBounds(438, 26, 97, 23);
		add(btnDup);
		
		btnCalendar = new JButton("달력...");
		btnCalendar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				WinCalendar winCalendar = new WinCalendar();
				winCalendar.setModal(true);
				winCalendar.setVisible(true);
				tfPdate.setText(winCalendar.getDate());
			}
		});
		btnCalendar.setBounds(459, 210, 97, 23);
		add(btnCalendar);
		
		cbPublisher = new JComboBox();
		cbPublisher.setEditable(true);
		cbPublisher.setBounds(303, 161, 253, 23);
		add(cbPublisher);
		
//		btnUpdate = new JButton("도서 변경");
//		btnUpdate.setBounds(498, 294, 97, 23);
//		add(btnUpdate);
		
//		btnRemove = new JButton("도서 삭제");
//		btnRemove.setBounds(389, 294, 97, 23);
//		add(btnRemove);
//		
//		btnAdd = new JButton("도서 추가");
//		btnAdd.setBounds(280, 294, 97, 23);
//		add(btnAdd);
	}
	
	protected void updateBook() {
		//=================================
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/sqlDB","root","1234");						

			String sql = "UPDATE bookTBL set title=?, author=?, publisher=?, image=?, pDate=?, discount=?, description=? WHERE isbn=?";
			
			PreparedStatement pstmt = con.prepareStatement(sql);

			pstmt.setString(1, tfTitle.getText().toString());
			pstmt.setString(2, tfAuthor.getText().toString());
			pstmt.setString(3, cbPublisher.getSelectedItem().toString());
			pstmt.setString(4, filePath);
			pstmt.setString(5, tfPdate.getText().toString());
			
			tfDiscount.setText(tfDiscount.getText().replaceAll(",", ""));
			pstmt.setString(6, tfDiscount.getText().toString());
			
			pstmt.setString(7, taDescription.getText().toString());
			pstmt.setString(8, tfISBN.getText().toString());
			
			if(pstmt.executeUpdate() >= 0) {
				JOptionPane.showMessageDialog(null, "변경되었습니다.");
			} else {
				JOptionPane.showMessageDialog(null, "질의어 오류인것 같습니다. \n확인하세요!");
			}
			
		} catch (ClassNotFoundException | SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		//=================================
	}

	protected void deleteBook() {
		//=================================
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/sqlDB","root","1234");						

			String sql = "DELETE FROM bookTBL WHERE isbn=?";
			
			PreparedStatement pstmt = con.prepareStatement(sql);

			pstmt.setString(1, tfISBN.getText().toString());
			
			if(pstmt.executeUpdate() >= 0) {
				JOptionPane.showMessageDialog(null, "삭제되었습니다.");
			} else {
				JOptionPane.showMessageDialog(null, "질의어 오류인것 같습니다. \n확인하세요!");
			}
			
		} catch (ClassNotFoundException | SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		//=================================
	}

	protected void insertBook() {
		//=================================
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/sqlDB","root","1234");						

			String sql = "INSERT INTO bookTBL VALUES(?, ?, ?, ?, ?, ?, ?, ?)";
			
			PreparedStatement pstmt = con.prepareStatement(sql);

			pstmt.setString(1, tfISBN.getText().toString());
			pstmt.setString(2, tfTitle.getText().toString());
			pstmt.setString(3, tfAuthor.getText().toString());
			pstmt.setString(4, cbPublisher.getSelectedItem().toString());
			pstmt.setString(5, filePath);
			pstmt.setString(6, tfPdate.getText().toString());
			
			tfDiscount.setText(tfDiscount.getText().replaceAll(",", ""));
			pstmt.setString(7, tfDiscount.getText().toString());
			
			pstmt.setString(8, taDescription.getText().toString());
			
			if(pstmt.executeUpdate() >= 0) {
				JOptionPane.showMessageDialog(null, "등록되었습니다.");
			} else {
				JOptionPane.showMessageDialog(null, "질의어 오류인것 같습니다. \n확인하세요!");
			}
			
		} catch (ClassNotFoundException | SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		//=================================
		
	}

	protected void checkDup() {
		//=================================
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/sqlDB","root","1234");						
			Statement stmt = con.createStatement();
			
			String sql = "SELECT COUNT(*) FROM bookTBL WHERE isbn='" + tfISBN.getText() + "'";
			ResultSet rs = stmt.executeQuery(sql);
			
			while(rs.next()) {
				if(rs.getInt(1) == 0) {
					tfTitle.requestFocus();
				} else {
					JOptionPane.showMessageDialog(null, "이미 존재하는 ISBN 입니다.");
					tfISBN.setSelectionStart(0); // 첫번째 글자부터
					tfISBN.setSelectionEnd(tfISBN.getText().length()); // 마지막 글자까지 블록으로 선택
					tfISBN.requestFocus();
				}
			}
			
		} catch (ClassNotFoundException | SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		//=================================
	}
	
	protected boolean isDigit(String strISBN) {
		return strISBN.matches("\\d*"); // 숫자가 맞는지 정규표현식
		
//		for(int i = 0; i < strISBN.length(); i++) {
//			if(strISBN.charAt(i) >= '0' && strISBN.charAt(i) <= '9') {
//				continue;
//			} else {
//				return false;
//			}
//		}
//		return true;
	}
	
	protected void changeDisComps() {
		btnDup.setEnabled(false);
		tfTitle.setEnabled(false);
		tfAuthor.setEnabled(false);
//		tfPublisher.setEnabled(false);
		cbPublisher.setEnabled(false);
		tfPdate.setEnabled(false);
		tfDiscount.setEnabled(false);
		taDescription.setEnabled(false);
		btnCalendar.setEnabled(false);
		btnType.setEnabled(false);
	}
	protected void changeEnComps() {
		btnDup.setEnabled(true);
		tfTitle.setEnabled(true);
		tfAuthor.setEnabled(true);
//		tfPublisher.setEnabled(true);
		cbPublisher.setEnabled(true);
		tfPdate.setEnabled(true);
		tfDiscount.setEnabled(true);
		taDescription.setEnabled(true);
		btnCalendar.setEnabled(true);
		btnType.setEnabled(true);
		
		showPublishers();
	}
	
	private void showPublishers() {
		//=================================
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/sqlDB","root","1234");						
			Statement stmt = con.createStatement();
			
			String sql = "SELECT DISTINCT publisher FROM bookTBL ORDER BY publisher";
			ResultSet rs = stmt.executeQuery(sql);
			
			cbPublisher.removeAllItems();
			while(rs.next()) {
				cbPublisher.addItem(rs.getString("publisher"));
			}
			
		} catch (ClassNotFoundException | SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		//=================================
	}

	public Book(String string, String title, String publisher, String author,
			String image, String pDate, String discount, String desc) {
		this();
		tfISBN.setText(string);
		tfTitle.setText(title);
		tfAuthor.setText(author);
//		tfPublisher.setText(publisher);
		cbPublisher.setSelectedItem(publisher);
		tfPdate.setText(pDate);
		tfDiscount.setText(discount);
		taDescription.setText(desc);

		String html = "<html><body><img src='" + image + "' width=200 height=250></html>";
		lblImage.setText(html);
	}
	public Book(String[] arrBook) {
		this();
		changeEnComps();
		tfISBN.setText(arrBook[0]);
		tfTitle.setText(arrBook[1]);
		tfAuthor.setText(arrBook[2]);
//		tfPublisher.setText(arrBook[3]);
		cbPublisher.setSelectedItem(arrBook[3]);
		tfPdate.setText(arrBook[5]);
		tfDiscount.setText(arrBook[6]);
		taDescription.setText(arrBook[7]);

		String html = "<html><body><img src='" + arrBook[4] + "' width=200 height=250></html>";
		lblImage.setText(html);
	}
	public Book(int number) {
		this();
		if(number == 1) { // insert
			btnType.setText("도서 추가");
		} else if(number == 2) { // delete
			btnType.setText("도서 삭제");
			btnDup.setEnabled(false);
			btnCalendar.setVisible(false);
		} else if(number == 3) { // update
			btnType.setText("도서 변경");
			btnDup.setEnabled(false);
		}
	}
}
