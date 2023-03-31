import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JButton;
import javax.swing.JDialog;

public class WinDBTest extends JDialog {

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					WinDBTest dialog = new WinDBTest();
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
	public WinDBTest() {
		setBounds(100, 100, 450, 300);
		
		JButton btnConnection = new JButton("연결");
		btnConnection.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					Class.forName("com.mysql.cj.jdbc.Driver");
					Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/sqldb", "root", "1234");
					
					System.out.println("DB 연결 완료!");
					
					String sql = "select * from usertbl";
					Statement stmt = con.createStatement();
					ResultSet rs = stmt.executeQuery(sql);
					
					while(rs.next()) {
						System.out.println(rs.getString("addr"));
					}
					
				} catch (ClassNotFoundException e1) {
					System.out.println("JDBC 드라이버 로드 에러");
				} catch (SQLException e1) {
					System.out.println("DB 연결 에러");
				}
				
			}
		});
		getContentPane().add(btnConnection, BorderLayout.NORTH);

	}

}
