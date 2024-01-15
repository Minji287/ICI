import java.awt.EventQueue;

import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.GridLayout;

public class WinMultiSearch extends JDialog {
	private JTextField tfName;
	private JTextField tfMobile;
	private JTextField tfGradYear;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					WinMultiSearch dialog = new WinMultiSearch();
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
	public WinMultiSearch() {
		setTitle("회원검색");
		setBounds(100, 100, 328, 175);
		getContentPane().setLayout(new GridLayout(4, 2, 0, 0));
		
		JLabel lblName = new JLabel("이름:");
		getContentPane().add(lblName);
		
		tfName = new JTextField();
		getContentPane().add(tfName);
		tfName.setColumns(10);
		
		JLabel lblMobile = new JLabel("전화번호:");
		getContentPane().add(lblMobile);
		
		tfMobile = new JTextField();
		getContentPane().add(tfMobile);
		tfMobile.setColumns(10);
		
		JLabel lblGradYear = new JLabel("졸업년도:");
		getContentPane().add(lblGradYear);
		
		tfGradYear = new JTextField();
		getContentPane().add(tfGradYear);
		tfGradYear.setColumns(10);
		
		JButton btnExit = new JButton("취소");
		btnExit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		getContentPane().add(btnExit);
		
		JButton btnSearch = new JButton("검색");
		btnSearch.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int type = 0;
				String value = "";
				
				if(!tfName.getText().equals("")) {
					type = 1;
					value = tfName.getText();
				} else if(!tfMobile.getText().equals("")) {
					type = 2;
					value = tfMobile.getText();
				} else if(!tfGradYear.getText().equals("")) {
					type = 3;
					value = tfGradYear.getText();
				}
				dispose();
				
				WinSearchResult winSearchResult = new WinSearchResult(type, value);
				winSearchResult.setModal(true);
				winSearchResult.setVisible(true);
			}
		});
		getContentPane().add(btnSearch);
		
	}
}
