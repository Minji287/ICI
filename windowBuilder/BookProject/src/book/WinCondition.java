package book;
import java.awt.EventQueue;

import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import javax.swing.JButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Vector;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class WinCondition extends JDialog {
	private JTextField tfPrice1;
	private JTextField tfPrice2;
	private JTable table;
	private int type;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					WinCondition dialog = new WinCondition();
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
	public WinCondition() {
		setTitle("검색조건 입력");
		setBounds(100, 100, 491, 522);
		getContentPane().setLayout(null);
		
		JLabel lblPrice = new JLabel("가격 :");
		lblPrice.setBounds(28, 36, 57, 15);
		getContentPane().add(lblPrice);
		
		tfPrice1 = new JTextField();
		tfPrice1.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if(e.getKeyCode() == KeyEvent.VK_ENTER) {
					tfPrice2.requestFocus();
				}
			}
		});
		tfPrice1.setBounds(68, 33, 116, 21);
		getContentPane().add(tfPrice1);
		tfPrice1.setColumns(10);
		
		tfPrice2 = new JTextField();
		tfPrice2.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if(e.getKeyCode() == KeyEvent.VK_ENTER) {
					SearchISBN();
				}
			}
		});
		tfPrice2.setBounds(216, 33, 116, 21);
		getContentPane().add(tfPrice2);
		tfPrice2.setColumns(10);
		
		JButton btnSearch = new JButton("검색");
		btnSearch.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				SearchISBN();
			}
		});
		btnSearch.setBounds(344, 32, 97, 23);
		getContentPane().add(btnSearch);
		
		JLabel lblNewLabel_1 = new JLabel("~");
		lblNewLabel_1.setBounds(196, 36, 19, 15);
		getContentPane().add(lblNewLabel_1);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(28, 85, 412, 366);
		getContentPane().add(scrollPane);
		
		String columnNames[] = { "ISBN", "Title", "Price" };
		DefaultTableModel dtm = new DefaultTableModel(columnNames, 0);
		table = new JTable();
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				int row = table.getSelectedRow();
				
				if(row == -1) {
					
				} else {
					String sTitle = table.getValueAt(row, 1).toString();
					if(JOptionPane.showConfirmDialog(null, sTitle + "(으)로 이동하시겠습니까?") == JOptionPane.YES_OPTION) {
						String sISBN = table.getValueAt(row, 0).toString();
						
						dispose();
						
						if(type == 2) {
							WinBookDelete winBookDelete = new WinBookDelete(sISBN);
							winBookDelete.setModal(true);
							winBookDelete.setVisible(true);
						} else if(type == 3) {
							WinBookUpdate WinBookUpdate = new WinBookUpdate(sISBN);
							WinBookUpdate.setModal(true);
							WinBookUpdate.setVisible(true);
						}
					}
				}
			}
		});
		table.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"ISBN", "Title", "Price"
			}
		));
		scrollPane.setViewportView(table);
		
	}

	public WinCondition(int number) {
		this();
		type = number;
	}

	protected void SearchISBN() {
		// 조건에 맞는 레코드들을 찾아서 테이블에 표시
		
		//=================================
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/sqlDB","root","1234");						
			Statement stmt = con.createStatement();
			
			String sql = "SELECT isbn, title, discount FROM bookTBL WHERE discount >= " +
							tfPrice1.getText() + " AND discount < " + tfPrice2.getText() + " ORDER BY discount DESC";
			ResultSet rs = stmt.executeQuery(sql);
			
			DefaultTableModel dtm = (DefaultTableModel)table.getModel();
			dtm.setRowCount(0);
			
			while(rs.next()) {
				Vector<String> vector = new Vector<>();
				vector.add(rs.getString("isbn"));
				vector.add(rs.getString("title"));
				vector.add(rs.getString("discount"));
				dtm.addRow(vector);
			}
			
		} catch (ClassNotFoundException | SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		//=================================
	}
}
