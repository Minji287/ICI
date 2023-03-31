import java.awt.EventQueue;
import java.awt.TextArea;

import javax.swing.JDialog;
import java.awt.BorderLayout;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JTextArea;
import javax.swing.JScrollBar;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class WinJuminCheck extends JDialog {
	private JTextField tfJumin;
	private JTextArea textArea;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					WinJuminCheck dialog = new WinJuminCheck();
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
	public WinJuminCheck() {
		setTitle("주민번호 체커기");
		setBounds(100, 100, 450, 300);
		getContentPane().setLayout(null);
		
		JLabel lblJumin = new JLabel("주민번호 입력");
		lblJumin.setBounds(23, 26, 88, 15);
		getContentPane().add(lblJumin);
		
		tfJumin = new JTextField();
		tfJumin.setBounds(110, 23, 183, 21);
		getContentPane().add(tfJumin);
		tfJumin.setColumns(10);
		
		JButton btnRun = new JButton("확인");
		btnRun.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String strJumin = tfJumin.getText();
//				if(strJumin.length() == 13) {
					int sum = 0, i, check = 0;
					int arr[] = {2,3,4,5,6,7,8,9,2,3,4,5};
					int arrJumin[] = new int[13];
					for(i = 0; i < 13; i++) {
						arrJumin[i] = (int)(Math.random()*9);
					}
					for(i = 0; i < 12; i++) {
						sum = sum + arrJumin[i] * arr[i];
						check = (11 - sum % 11) % 10;
					}
					String createJumin = "";
					for(i = 0; i < 12; i++) {
						createJumin = createJumin + arrJumin[i];
					}
					createJumin = createJumin + check;
					tfJumin.setText(createJumin);
					textArea.setText(textArea.getText() + "\n" + createJumin);
//					int lastSu = Integer.parseInt(strJumin.substring(i, i+1));
//					if(lastSu == check) {
//						JOptionPane.showMessageDialog(null, "주민번호 일치");
//					}else {
//						JOptionPane.showMessageDialog(null, "주민번호 불일치");
//					}
//				}else {
//					JOptionPane.showMessageDialog(null, "주민번호를 다시 입력하세요.");
//					tfJumin.requestFocus();
//				}
			}
		});
		btnRun.setBounds(305, 22, 104, 23);
		getContentPane().add(btnRun);
		
		textArea = new JTextArea();
		textArea.setBounds(23, 51, 386, 189);
		getContentPane().add(textArea);

	}
}
