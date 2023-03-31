import java.awt.EventQueue;

import javax.swing.JDialog;
import java.awt.FlowLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JTextField;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class WinF2C extends JDialog {
	private JTextField tfFahrenheit;
	private JTextField tfCelsius;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					WinF2C dialog = new WinF2C();
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
	public WinF2C() {
		setTitle("화씨를 섭씨로(F2C)");
		setBounds(100, 100, 322, 203);
		getContentPane().setLayout(null);
		
		JLabel lblF = new JLabel("화씨(F)");
		lblF.setBounds(47, 30, 57, 15);
		getContentPane().add(lblF);
		
		tfFahrenheit = new JTextField();
		tfFahrenheit.setBounds(116, 27, 148, 21);
		getContentPane().add(tfFahrenheit);
		tfFahrenheit.setColumns(10);
		
		JButton btnConvert = new JButton("C2F");
		btnConvert.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				double result = (Double.parseDouble(tfCelsius.getText()) * 9/5) + 32;
				tfFahrenheit.setText(Double.toString(result));
			}
		});
		btnConvert.setBounds(170, 69, 94, 23);
		getContentPane().add(btnConvert);
		
		JLabel lblC = new JLabel("섭씨(C)");
		lblC.setBounds(47, 116, 57, 15);
		getContentPane().add(lblC);
		
		tfCelsius = new JTextField();
		tfCelsius.setColumns(10);
		tfCelsius.setBounds(116, 113, 148, 21);
		getContentPane().add(tfCelsius);
		
		JButton btnConvert_1 = new JButton("F2C");
		btnConvert_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// 화씨를 가지고 와서 공식에 넣어 계산한 후, 결과를 섭씨에 넣는다.
				double result = (Double.parseDouble(tfFahrenheit.getText()) - 32) * 5/9;
				tfCelsius.setText(Double.toString(result));
			}
		});
		btnConvert_1.setBounds(47, 69, 94, 23);
		getContentPane().add(btnConvert_1);

	}
}
