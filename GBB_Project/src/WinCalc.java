import java.awt.EventQueue;

import javax.swing.JDialog;
import java.awt.GridLayout;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.SwingConstants;
import java.awt.Color;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class WinCalc extends JDialog {
	private String sNum1 = new String();
	private String sNum2 = new String();
	private String sOp = new String();
	private boolean bStart = true;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					WinCalc dialog = new WinCalc();
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
	public WinCalc() {
		setTitle("계산기");
		setBounds(100, 100, 337, 449);
		getContentPane().setLayout(null);
		
		JLabel lblDisp = new JLabel("");
		lblDisp.setBackground(new Color(176, 224, 230));
		lblDisp.setForeground(new Color(0, 0, 0));
		lblDisp.setOpaque(true);
		lblDisp.setHorizontalAlignment(SwingConstants.RIGHT);
		lblDisp.setFont(new Font("굴림", Font.BOLD, 25));
		lblDisp.setBounds(12, 24, 294, 60);
		getContentPane().add(lblDisp);
		
		JButton btnAdd = new JButton("+");
		btnAdd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				sNum1 = lblDisp.getText();
				lblDisp.setText("");
				sOp = "+";
			}
		});
		btnAdd.setFont(new Font("굴림", Font.PLAIN, 30));
		btnAdd.setBounds(12, 106, 65, 43);
		getContentPane().add(btnAdd);
		
		JButton btnDiv = new JButton("/");
		btnDiv.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				sNum1 = lblDisp.getText();
				lblDisp.setText("");
				sOp = "/";
			}
		});
		btnDiv.setFont(new Font("굴림", Font.PLAIN, 30));
		btnDiv.setBounds(243, 106, 65, 43);
		getContentPane().add(btnDiv);
		
		JButton btnSub = new JButton("-");
		btnSub.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				sNum1 = lblDisp.getText();
				lblDisp.setText("");
				sOp = "-";
			}
		});
		btnSub.setFont(new Font("굴림", Font.PLAIN, 30));
		btnSub.setBounds(89, 106, 65, 43);
		getContentPane().add(btnSub);
		
		JButton btnMul = new JButton("*");
		btnMul.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				sNum1 = lblDisp.getText();
				lblDisp.setText("");
				sOp = "*";
			}
		});
		btnMul.setFont(new Font("굴림", Font.PLAIN, 30));
		btnMul.setBounds(166, 106, 65, 43);
		getContentPane().add(btnMul);
		
		JButton btn7 = new JButton("7");
		btn7.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(bStart) {
					lblDisp.setText("");
					bStart = false;
				}
				lblDisp.setText(lblDisp.getText() + "7");
			}
		});
		btn7.setFont(new Font("굴림", Font.PLAIN, 20));
		btn7.setBounds(40, 159, 71, 51);
		getContentPane().add(btn7);
		
		JButton btn8 = new JButton("8");
		btn8.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(bStart) {
					lblDisp.setText("");
					bStart = false;
				}
				lblDisp.setText(lblDisp.getText() + "8");
			}
		});
		btn8.setFont(new Font("굴림", Font.PLAIN, 20));
		btn8.setBounds(123, 159, 71, 51);
		getContentPane().add(btn8);
		
		JButton btn9 = new JButton("9");
		btn9.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(bStart) {
					lblDisp.setText("");
					bStart = false;
				}
				lblDisp.setText(lblDisp.getText() + "9");
			}
		});
		btn9.setFont(new Font("굴림", Font.PLAIN, 20));
		btn9.setBounds(206, 159, 71, 51);
		getContentPane().add(btn9);
		
		JButton btn4 = new JButton("4");
		btn4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(bStart) {
					lblDisp.setText("");
					bStart = false;
				}
				lblDisp.setText(lblDisp.getText() + "4");
			}
		});
		btn4.setFont(new Font("굴림", Font.PLAIN, 20));
		btn4.setBounds(40, 220, 71, 51);
		getContentPane().add(btn4);
		
		JButton btn5 = new JButton("5");
		btn5.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(bStart) {
					lblDisp.setText("");
					bStart = false;
				}
				lblDisp.setText(lblDisp.getText() + "5");
			}
		});
		btn5.setFont(new Font("굴림", Font.PLAIN, 20));
		btn5.setBounds(125, 220, 71, 51);
		getContentPane().add(btn5);
		
		JButton btn6 = new JButton("6");
		btn6.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(bStart) {
					lblDisp.setText("");
					bStart = false;
				}
				lblDisp.setText(lblDisp.getText() + "6");
			}
		});
		btn6.setFont(new Font("굴림", Font.PLAIN, 20));
		btn6.setBounds(206, 220, 71, 51);
		getContentPane().add(btn6);
		
		JButton btn1 = new JButton("1");
		btn1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(bStart) {
					lblDisp.setText("");
					bStart = false;
				}
				lblDisp.setText(lblDisp.getText() + "1");
			}
		});
		btn1.setFont(new Font("굴림", Font.PLAIN, 20));
		btn1.setBounds(40, 280, 71, 51);
		getContentPane().add(btn1);
		
		JButton btn2 = new JButton("2");
		btn2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(bStart) {
					lblDisp.setText("");
					bStart = false;
				}
				lblDisp.setText(lblDisp.getText() + "2");
			}
		});
		btn2.setFont(new Font("굴림", Font.PLAIN, 20));
		btn2.setBounds(125, 280, 71, 51);
		getContentPane().add(btn2);
		
		JButton btn3 = new JButton("3");
		btn3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(bStart) {
					lblDisp.setText("");
					bStart = false;
				}
				lblDisp.setText(lblDisp.getText() + "3");
			}
		});
		btn3.setFont(new Font("굴림", Font.PLAIN, 20));
		btn3.setBounds(206, 280, 71, 51);
		getContentPane().add(btn3);
		
		JButton btn0 = new JButton("0");
		btn0.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(bStart) {
					lblDisp.setText("");
					bStart = false;
				}
				if(!lblDisp.getText().equals("")) {
					lblDisp.setText(lblDisp.getText() + "0");
				}
			}
		});
		btn0.setFont(new Font("굴림", Font.PLAIN, 20));
		btn0.setBounds(125, 341, 71, 51);
		getContentPane().add(btn0);
		
		JButton btnResult = new JButton("=");
		btnResult.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				sNum2 = lblDisp.getText();
				double result = 0;
				switch(sOp) {
				case "+": 
					result = Integer.parseInt(sNum1)
							+ Integer.parseInt(sNum2);
					break;
				case "-":
					result = Integer.parseInt(sNum1)
							- Integer.parseInt(sNum2);
					break;
				case "*":
					result = Integer.parseInt(sNum1)
							* Integer.parseInt(sNum2);
					break;
				case "/":
					result = (double)Integer.parseInt(sNum1)
							/ Integer.parseInt(sNum2);
					break;
				}
				lblDisp.setText(Double.toString(result));
				bStart = true;
			}
		});
		btnResult.setFont(new Font("굴림", Font.PLAIN, 30));
		btnResult.setBounds(206, 341, 71, 51);
		getContentPane().add(btnResult);
		
		JButton btnClear = new JButton("C");
		btnClear.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				lblDisp.setText("");
			}
		});
		btnClear.setFont(new Font("굴림", Font.BOLD, 20));
		btnClear.setBounds(40, 341, 71, 51);
		getContentPane().add(btnClear);

	}
}
