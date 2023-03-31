import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;
import java.io.FileReader;
import java.io.IOException;

import javax.swing.ImageIcon;
import javax.swing.JDialog;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.KeyStroke;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.JTextArea;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;
import javax.swing.JSeparator;

public class WinMain extends JDialog {

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					WinMain dialog = new WinMain();
					dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
					dialog.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	private JLabel lbPic;
	private JTextArea taContents;

	/**
	 * Create the dialog.
	 */
	public WinMain() {
		setBounds(100, 100, 613, 403);
		
		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		
		JMenu mnFile = new JMenu("File");
		mnFile.setMnemonic('F');
		menuBar.add(mnFile);
		
		JMenuItem mntmOpen = new JMenuItem("Open Image...");
		mntmOpen.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JFileChooser chooser = new JFileChooser();
				FileNameExtensionFilter filter = new FileNameExtensionFilter("이미지파일", "png", "gif", "jpg");
				chooser.setFileFilter(filter);
				if(chooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {
					ImageIcon icon = new ImageIcon(chooser.getSelectedFile().getPath());
					Image img = icon.getImage();
					img = img.getScaledInstance(300, 300, Image.SCALE_SMOOTH);
					ImageIcon pic = new ImageIcon(img);
					lbPic.setIcon(pic);
				}
			}
		});
		mnFile.add(mntmOpen);
		
		JMenuItem mntmOpentxt = new JMenuItem("Open txt file...");
		mntmOpentxt.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// 텍스트 파일(txt, java)을 읽어와서 taContents에 넣으시오.
				JFileChooser chooser = new JFileChooser("C:\\FileIO");
				FileNameExtensionFilter filter = new FileNameExtensionFilter("텍스트파일", "txt", "java");
				chooser.setFileFilter(filter);
				if(JFileChooser.APPROVE_OPTION == chooser.showOpenDialog(null)) {
					try {
						FileReader fr = new FileReader(chooser.getSelectedFile().getPath());
						int c;
						String content = "";
						while((c = fr.read()) != -1) {
							content = content + (char)c;
							taContents.setText(content);
						}
					} catch (IOException e1) {
						e1.printStackTrace();
					}
				}
			}
		});
		mntmOpentxt.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_T, InputEvent.CTRL_DOWN_MASK));
		mnFile.add(mntmOpentxt);
		
		mnFile.addSeparator();
		
		JMenuItem mntmSave = new JMenuItem("Save");
		mnFile.add(mntmSave);
		
		mnFile.addSeparator();
		
		JMenuItem mntmExit = new JMenuItem("Exit");
		mntmExit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
		mntmExit.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_F2, 0));
		mnFile.add(mntmExit);
		
		JMenu mnColor = new JMenu("Color");
		mnColor.setMnemonic('C');
		menuBar.add(mnColor);
		
		JMenuItem mntmRed = new JMenuItem("Red");
		mntmRed.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_R, InputEvent.CTRL_DOWN_MASK));
		mntmRed.setMnemonic('R');
		mntmRed.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				getContentPane().setBackground(new Color(255, 0, 0));
			}
		});
		mnColor.add(mntmRed);
		
		JMenuItem mntmGreen = new JMenuItem("Green");
		mntmGreen.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_G, InputEvent.CTRL_DOWN_MASK));
		mntmGreen.setMnemonic('G');
		mntmGreen.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				getContentPane().setBackground(Color.green);
			}
		});
		mnColor.add(mntmGreen);
		
		JMenuItem mntmBlue = new JMenuItem("Blue");
		mntmBlue.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_B, InputEvent.CTRL_DOWN_MASK));
		mntmBlue.setMnemonic('B');
		mntmBlue.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				getContentPane().setBackground(Color.BLUE);
			}
		});
		mnColor.add(mntmBlue);
		
		JMenu mnHelp = new JMenu("Help");
		mnHelp.setMnemonic('e');
		menuBar.add(mnHelp);
		
		JMenuItem mntmInfo = new JMenuItem("Info...");
		mntmInfo.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_F1, 0));
		mntmInfo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
//				JOptionPane.showMessageDialog(null, "만든사람: 홍길동");
				WinGBB winGBB = new WinGBB();
				winGBB.setModal(true);
				winGBB.setVisible(true);
				
			}
		});
		mnHelp.add(mntmInfo);
		
		lbPic = new JLabel("");
		getContentPane().add(lbPic, BorderLayout.WEST);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		getContentPane().add(scrollPane, BorderLayout.CENTER);
		
		taContents = new JTextArea();
		taContents.setLineWrap(true);
		scrollPane.setViewportView(taContents);

	}

}
