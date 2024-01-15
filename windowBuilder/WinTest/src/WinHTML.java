import java.awt.EventQueue;

import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JTextArea;
import javax.swing.JTextPane;

public class WinHTML extends JDialog {

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					WinHTML dialog = new WinHTML();
					dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
					dialog.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	private JTextArea textArea;
	private JTextPane textPane;

	/**
	 * Create the dialog.
	 */
	public WinHTML() {
		setBounds(100, 100, 555, 601);
		getContentPane().setLayout(null);
		
		JLabel lblHTML = new JLabel("New label");
		lblHTML.setBounds(35, 45, 222, 148);
		getContentPane().add(lblHTML);
		
		JButton btnNewButton = new JButton("New button");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String initialText = "<html>\n" +
		                "Color and font test:\n" +
		                "<ul>\n" +
		                "<li><font color=red>red</font>\n" +
		                "<li><font color=blue>blue</font>\n" +
		                "<li><font color=green>green</font>\n" +
		                "<li><font size=-2>small</font>\n" +
		                "<li><font size=+2>large</font>\n" +
		                "<li><i>italic</i>\n" +
		                "<li><b>bold</b>\n" +
		                "</ul>\n";
				
				textArea.setText(initialText);
				textPane.setText(initialText);
			}
		});
		btnNewButton.setBounds(308, 108, 97, 23);
		getContentPane().add(btnNewButton);
		
		textArea = new JTextArea();
		textArea.setBounds(29, 224, 228, 285);
		getContentPane().add(textArea);
		
		textPane = new JTextPane();
		textPane.setContentType("text/html");
		textPane.setBounds(269, 225, 236, 284);
		getContentPane().add(textPane);

	}
}
