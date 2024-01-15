import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class WinSearchDoro extends JDialog {
	private JTextField tfDoro;
	private JList list;
	private String sAddress;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					WinSearchDoro dialog = new WinSearchDoro();
					dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
					dialog.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	public String getAddress() {
		return sAddress;
	}

	/**
	 * Create the dialog.
	 */
	public WinSearchDoro() {
		setTitle("도로명 선택");
		setBounds(100, 100, 449, 327);
		getContentPane().setLayout(new BorderLayout(0, 0));
		
		JPanel panel = new JPanel();
		getContentPane().add(panel, BorderLayout.NORTH);
		
		tfDoro = new JTextField();
		tfDoro.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if(e.getKeyCode() == KeyEvent.VK_ENTER) {
					searchAddress();
				}
			}
		});
		panel.add(tfDoro);
		tfDoro.setColumns(10);
		
		JButton btnSearch = new JButton("탐색");
		btnSearch.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				searchAddress();
			}
		});
		panel.add(btnSearch);
		
		JPanel panelResult = new JPanel();
		getContentPane().add(panelResult, BorderLayout.CENTER);
		panelResult.setLayout(new BorderLayout(0, 0));
		
		JScrollPane scrollPane = new JScrollPane();
		panelResult.add(scrollPane, BorderLayout.CENTER);
		
		list = new JList();
		list.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if(e.getClickCount() == 2) {
					sAddress = list.getSelectedValue().toString();
					dispose();
				}
			}
		});
		list.setFont(new Font("굴림", Font.PLAIN, 16));
		scrollPane.setViewportView(list);

	}

	protected void searchAddress() {
		String doro = tfDoro.getText();
		//=================================
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/sqlDB","root","1234");						
			Statement stmt = con.createStatement();
			
			String sql = "SELECT * FROM addressTBL WHERE doro='" + doro + "'";
			ResultSet rs = stmt.executeQuery(sql);
			
			Vector<String> v = new Vector<>();
			
			while(rs.next()) {
				String sSi = rs.getString("si");
				String sGu = rs.getString("gu");
				String sDong = rs.getString("dong");
				
				v.add(sSi + " " + sGu + " " + sDong);
			}
			
			list.setListData(v);
			
		} catch (ClassNotFoundException | SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		//=================================
	}

}
