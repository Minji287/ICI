import java.awt.EventQueue;

import javax.swing.JDialog;
import javax.swing.JScrollPane;
import java.awt.BorderLayout;
import javax.swing.JTable;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JComboBox;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import javax.swing.DefaultComboBoxModel;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Vector;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class WinRentalSearch extends JDialog {
	private final JPanel panel = new JPanel();
	private final JScrollPane scrollPane = new JScrollPane();
	private final JTable table = new JTable();
	private final JLabel lblCondition = new JLabel("검색조건");
	private final JComboBox cbCondition = new JComboBox();
	private final JTextField tfConditionValue = new JTextField();
	protected String sID;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					WinRentalSearch dialog = new WinRentalSearch();
					dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
					dialog.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public String getID() {
		return sID;
	}
	/**
	 * Create the dialog.
	 */
	public WinRentalSearch() {
		tfConditionValue.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if(e.getKeyCode() == KeyEvent.VK_ENTER)
					showRecords();
			}
		});
		tfConditionValue.setColumns(10);
		setTitle("ID 찾기");
		setBounds(100, 100, 607, 423);
		
		getContentPane().add(panel, BorderLayout.NORTH);
		
		panel.add(lblCondition);
		cbCondition.setModel(new DefaultComboBoxModel(new String[] {"이름", "전화번호", "이메일"}));
		
		panel.add(cbCondition);
		
		panel.add(tfConditionValue);
		
		getContentPane().add(scrollPane, BorderLayout.CENTER);
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				int row = table.getSelectedRow();
				if(row != -1)
					sID = table.getValueAt(row, 0).toString(); // id
				dispose();
			}
		});
		
		scrollPane.setViewportView(table);

	}

	protected void showRecords() {
		String cols[] = {"ID","이름","전화번호","이메일","주소"};
		DefaultTableModel dtm = new DefaultTableModel(cols,0);
		table.setModel(dtm);
		
		
		
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/sqlDB","root","1234");						
			Statement stmt = con.createStatement();			
			String sql = "";
			if(cbCondition.getSelectedItem().toString().equals("이름"))
				sql = "select * from memberTBL where name like '%" + tfConditionValue.getText() + "%'";
			else if(cbCondition.getSelectedItem().toString().equals("전화번호"))
				sql = "select * from memberTBL where mobile like '%" + tfConditionValue.getText() + "%'";
			else if(cbCondition.getSelectedItem().toString().equals("이메일"))
				sql = "select * from memberTBL where email like '%" + tfConditionValue.getText() + "%'";
			
			ResultSet rs = stmt.executeQuery(sql);			
			while(rs.next()) {
				Vector<String> vector = new Vector<>();
				vector.add(rs.getString("id"));
				vector.add(rs.getString("name"));
				vector.add(rs.getString("mobile"));
				vector.add(rs.getString("email"));
				vector.add(rs.getString("address"));
				dtm.addRow(vector);
			}
		} catch (ClassNotFoundException | SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}

}
