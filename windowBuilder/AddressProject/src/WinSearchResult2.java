import java.awt.EventQueue;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Vector;

import javax.swing.JDialog;
import javax.swing.JScrollPane;
import java.awt.BorderLayout;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import com.mysql.cj.xdevapi.Result;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class WinSearchResult2 extends JDialog {
	private JTable table;
	private DefaultTableModel dtm;
	private JTextField tfSearch;
	private JComboBox cbSearch;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					WinSearchResult2 dialog = new WinSearchResult2();
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
	public WinSearchResult2() {
		setTitle("검색결과");
		setBounds(100, 100, 854, 396);
		
		JScrollPane scrollPane = new JScrollPane();
		getContentPane().add(scrollPane, BorderLayout.CENTER);
		
		String columnNames[] = {"ID", "이름", "전화번호", "이메일", "주소", "생일", "졸업년도"};
		dtm = new DefaultTableModel(columnNames, 0);
		table = new JTable(dtm);
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				int row = table.getSelectedRow();
				int id = Integer.parseInt(table.getValueAt(row, 0).toString());
				
				dispose();
				
				WinUpdateMember winUpdateMember = new WinUpdateMember(id);
				winUpdateMember.setModal(true);
				winUpdateMember.setVisible(true);
			}
		});
		scrollPane.setViewportView(table);
		
		JPanel panel = new JPanel();
		getContentPane().add(panel, BorderLayout.NORTH);
		
		JLabel lblNewLabel = new JLabel("검색방법:");
		panel.add(lblNewLabel);
		
		cbSearch = new JComboBox();
		cbSearch.setModel(new DefaultComboBoxModel(new String[] {"이름", "전화번호", "이메일", "주소", "졸업년도"}));
		panel.add(cbSearch);
		
		tfSearch = new JTextField();
		panel.add(tfSearch);
		tfSearch.setColumns(10);
		
		JButton btnSearch = new JButton("검색");
		btnSearch.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				showTable();
			}
		});
		panel.add(btnSearch);

	}

	private void showTable() {
		//=================================
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/sqlDB","root","1234");						
			Statement stmt = con.createStatement();
			
			String sql = "SELECT * FROM addrTBL";
			
			if(cbSearch.getSelectedItem().toString().equals("이름")) {
				sql = "SELECT * FROM addrTBL where name='" + tfSearch.getText() + "'";
			} else if(cbSearch.getSelectedItem().toString().equals("전화번호")) {
				sql = "SELECT * FROM addrTBL where mobile like '%" + tfSearch.getText() + "%'";
			} else if(cbSearch.getSelectedItem().toString().equals("이메일")) {
				sql = "SELECT * FROM addrTBL where email like %" + tfSearch.getText() + "%'";
			} else if(cbSearch.getSelectedItem().toString().equals("주소")) {
				sql = "SELECT * FROM addrTBL where address like '%" + tfSearch.getText() + "%'";
			} else {
				sql = "SELECT * FROM addrTBL where gradYear='" + tfSearch.getText() + "'";
			}
			
			ResultSet rs = stmt.executeQuery(sql);
			dtm.setRowCount(0);
			
			while(rs.next()) {
				Vector record = new Vector<>();
				
				for(int i = 1; i < 8; i++) {
					record.add(rs.getString(i));
				}
				dtm.addRow(record);
			}
			
		} catch (ClassNotFoundException | SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		//=================================
	}

}
