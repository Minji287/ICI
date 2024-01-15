import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Vector;

import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class WinMain extends JDialog {
	private JTable table;	
	private DefaultTableModel dtm;

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
		setTitle("회원 대화상자");
		setBounds(100, 100, 846, 545);
		
		JScrollPane scrollPane = new JScrollPane();
		getContentPane().add(scrollPane, BorderLayout.CENTER);
		
		String columnNames[] = {"아이디","패스워드","이름","이메일","전화번호"};
		dtm = new DefaultTableModel(columnNames, 0);
		
		table = new JTable(dtm);
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if(JOptionPane.showConfirmDialog(null, "삭제하시겠습니까?") == JOptionPane.YES_OPTION) {
					String sID = table.getValueAt(table.getSelectedRow(), 0).toString();
					dtm.removeRow(table.getSelectedRow());
					deleteRecord(sID);
				}
			}
		});
		
		scrollPane.setViewportView(table);
		
		showRecords();
	}

	public WinMain(String sName) { // 초기화 시킬 때 생성자 만듦
		this();
		setTitle("로그인: " + sName);
	}

	protected void deleteRecord(String sID) {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/sqldb", "root", "1234");
			System.out.println("DB 연결 완료");
			Statement stmt = con.createStatement();
			
			String sql = "DELETE FROM membertbl WHERE id='" + sID + "'";

			if(stmt.executeUpdate(sql) <= 0) {
				System.out.println("삭제 오류 발생");
			} else {
				JOptionPane.showMessageDialog(null, "삭제되었습니다.");
			}
			
		} catch (ClassNotFoundException | SQLException e) {
			System.out.println("DB 문제(테이블 이름확인, 쿼리 확인, 데이터베이스 확인)");
		}
	}

	private void showRecords() {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/sqldb", "root", "1234");
			System.out.println("DB 연결 완료");
			
			String sql = "select * from membertbl";
			Statement stmt = con.createStatement();
			ResultSet rs =  stmt.executeQuery(sql);
			
			while(rs.next()) {
//				Vector<String> vec = new Vector<>();
				String sArr[] = new String[5];
				for(int i = 0; i < sArr.length; i++) {
					sArr[i] = rs.getString(i+1);
				}
				dtm.addRow(sArr);
			}
			
		} catch (ClassNotFoundException | SQLException e) {
			System.out.println("DB 문제(테이블 이름확인, 쿼리 확인, 데이터베이스 확인)");
		}
	}

}
