import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

public class WinQuadraticF extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTextField tfResult1;
	private JTextField tfA;
	private JTextField tfB;
	private JTextField tfC;
	private JTextField tfResult2;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			WinQuadraticF dialog = new WinQuadraticF();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public WinQuadraticF() {
		setBounds(100, 100, 450, 300);
		getContentPane().setLayout(null);
		contentPanel.setBounds(0, 0, 434, 228);
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel);
		contentPanel.setLayout(null);
		{
			JLabel lblQuadraticE = new JLabel("이차방정식");
			lblQuadraticE.setBounds(187, 10, 83, 15);
			contentPanel.add(lblQuadraticE);
		}
		{
			JButton btnConvert = new JButton("근의 공식 값");
			btnConvert.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					double a = Double.parseDouble(tfA.getText());
					double b = Double.parseDouble(tfB.getText());
					double c = Double.parseDouble(tfC.getText());
					double resultP;
					double resultM;
					if((b*b - 4*a*c) >= 0 && a != 0) {
						resultP = (-b + Math.sqrt(b*b - 4*a*c)) / (2 * a);
						resultM = (-b - Math.sqrt(b*b - 4*a*c)) / (2 * a);
						tfResult1.setText(Double.toString(resultP));
						tfResult2.setText(Double.toString(resultM));
					} else {
						JOptionPane.showMessageDialog(null, "허수가 나왔네요", "오류!!!", JOptionPane.ERROR_MESSAGE);
					}
				}
			});
			btnConvert.setBounds(154, 107, 129, 42);
			contentPanel.add(btnConvert);
		}
		{
			tfResult1 = new JTextField();
			tfResult1.setBounds(121, 171, 83, 33);
			contentPanel.add(tfResult1);
			tfResult1.setColumns(10);
		}
		{
			tfA = new JTextField();
			tfA.setColumns(10);
			tfA.setBounds(91, 46, 32, 33);
			contentPanel.add(tfA);
		}
		{
			tfB = new JTextField();
			tfB.setColumns(10);
			tfB.setBounds(172, 46, 32, 33);
			contentPanel.add(tfB);
		}
		{
			tfC = new JTextField();
			tfC.setColumns(10);
			tfC.setBounds(251, 46, 32, 33);
			contentPanel.add(tfC);
		}
		{
			JLabel lblXX = new JLabel("x^2 +");
			lblXX.setBounds(135, 64, 40, 15);
			contentPanel.add(lblXX);
		}
		{
			JLabel lblX = new JLabel("+ x +");
			lblX.setBounds(216, 64, 32, 15);
			contentPanel.add(lblX);
		}
		{
			JLabel lblZero = new JLabel("= 0");
			lblZero.setBounds(286, 64, 40, 15);
			contentPanel.add(lblZero);
		}
		{
			JLabel lblxResult = new JLabel("x =");
			lblxResult.setBounds(91, 180, 32, 15);
			contentPanel.add(lblxResult);
		}
		{
			tfResult2 = new JTextField();
			tfResult2.setColumns(10);
			tfResult2.setBounds(236, 171, 90, 33);
			contentPanel.add(tfResult2);
		}
		{
			JLabel lblOr = new JLabel("or");
			lblOr.setBounds(210, 180, 24, 15);
			contentPanel.add(lblOr);
		}
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setBounds(0, 228, 434, 33);
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane);
			{
				JButton okButton = new JButton("OK");
				okButton.setActionCommand("OK");
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
			}
			{
				JButton cancelButton = new JButton("Cancel");
				cancelButton.setActionCommand("Cancel");
				buttonPane.add(cancelButton);
			}
		}
	}

}
