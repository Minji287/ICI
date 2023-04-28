import java.awt.EventQueue;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Vector;

import javax.swing.JDialog;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JToolBar;
import javax.swing.table.DefaultTableModel;

import java.awt.BorderLayout;
import javax.swing.JButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;

public class WinMain extends JDialog {
	private JTable table;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					WinMain dialog = new WinMain();
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
	public WinMain() {
		setTitle("도서 관리 프로그램");
		setBounds(100, 100, 1049, 614);
		
		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		
		JMenu mnuFile = new JMenu("File");
		mnuFile.setMnemonic('F');
		menuBar.add(mnuFile);
		
		JMenuItem mnuExit = new JMenuItem("Exit");
		mnuFile.add(mnuExit);
		
		JMenu mnuBookManager = new JMenu("도서관리");
		menuBar.add(mnuBookManager);
		
		JMenuItem mntmNewMenuItem_1 = new JMenuItem("도서 등록...");
		mnuBookManager.add(mntmNewMenuItem_1);
		
		JMenuItem mntmNewMenuItem_2 = new JMenuItem("도서 삭제");
		mnuBookManager.add(mntmNewMenuItem_2);
		
		JMenuItem mntmNewMenuItem_4 = new JMenuItem("도서 변경");
		mnuBookManager.add(mntmNewMenuItem_4);
		
		JMenuItem mntmNewMenuItem = new JMenuItem("도서 조회");
		mnuBookManager.add(mntmNewMenuItem);
		
		JMenu mnNewMenu = new JMenu("Help");
		mnNewMenu.setMnemonic('H');
		menuBar.add(mnNewMenu);
		
		JMenuItem mntmNewMenuItem_3 = new JMenuItem("회사 정보...");
		mnNewMenu.add(mntmNewMenuItem_3);
		
		JToolBar toolBar = new JToolBar();
		getContentPane().add(toolBar, BorderLayout.NORTH);
		
		JButton btnNewButton = new JButton("종료");
		toolBar.add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("등록");
		toolBar.add(btnNewButton_1);
		
		JButton btnNewButton_2 = new JButton("변경");
		toolBar.add(btnNewButton_2);
		
		JScrollPane scrollPane = new JScrollPane();
		getContentPane().add(scrollPane, BorderLayout.CENTER);
		
		String columns[] = {"ISBN", "제목", "저자", "출판사", "이미지URL", "출판일", "가격", "책소개"};
		DefaultTableModel dtm = new DefaultTableModel(columns, 0);
		table = new JTable(dtm);
		scrollPane.setViewportView(table);
		
		showRecords(dtm);

	}

	private void showRecords(DefaultTableModel dtm) {
		//=================================
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/sqlDB","root","1234");						
			Statement stmt = con.createStatement();
			
			String sql = "SELECT * FROM bookTBL ORDER BY title ASC";
			ResultSet rs = stmt.executeQuery(sql);
			
			while(rs.next()) {
				Vector<String> vector = new Vector<>();
				for(int i = 1; i <= 8; i++) {
					vector.add(rs.getString(i));
				}
				dtm.addRow(vector);
			}
			
		} catch (ClassNotFoundException | SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		//=================================
	}

}
