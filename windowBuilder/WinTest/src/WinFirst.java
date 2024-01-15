import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTextField;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JPasswordField;

public class WinFirst extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTextField tfNumber1;
	private JTextField tfNumber2;
	private JTextField tfResult;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			WinFirst dialog = new WinFirst();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public WinFirst() {
		setTitle("홍길동 만세!!!");
		setBounds(100, 100, 450, 300);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setLayout(new FlowLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.NORTH);
		{
			tfNumber1 = new JTextField();
			contentPanel.add(tfNumber1);
			tfNumber1.setColumns(10);
		}
		{
			tfNumber2 = new JTextField();
			contentPanel.add(tfNumber2);
			tfNumber2.setColumns(10);
		}
		{
			tfResult = new JTextField();
			contentPanel.add(tfResult);
			tfResult.setColumns(10);
		}
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton okButton = new JButton("OK");
				okButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						int su1 = Integer.parseInt(tfNumber1.getText());
						int su2 = Integer.parseInt(tfNumber2.getText());
						int result = su1 + su2;
						System.out.println(result);
						tfResult.setText(Integer.toString(result));
					}
				});
				okButton.setActionCommand("OK");
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
			}
			{
				JButton cancelButton = new JButton("Cancel");
				cancelButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
					}
				});
				cancelButton.setActionCommand("Cancel");
				buttonPane.add(cancelButton);
			}
		}
	}

}
