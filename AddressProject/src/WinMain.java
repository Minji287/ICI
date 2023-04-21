import java.awt.EventQueue;

import javax.swing.JDialog;
import java.awt.GridLayout;
import java.awt.Image;

import javax.swing.JButton;
import javax.swing.ImageIcon;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

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

	/**
	 * Create the dialog.
	 */
	public WinMain() {
		setTitle("ICI 주소록");
		setBounds(100, 100, 233, 253);
		getContentPane().setLayout(new GridLayout(2, 2, 0, 0));
		
		JButton btnAdd = new JButton("");
		btnAdd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				WinAddMember winAddMember = new WinAddMember();
				winAddMember.setModal(true);
				winAddMember.setVisible(true);
			}
		});
		ImageIcon icon = new ImageIcon(WinMain.class.getResource("/images/add-friend.png"));
		Image image = icon.getImage();
		image = image.getScaledInstance(100, 100, image.SCALE_SMOOTH);
		ImageIcon pic = new ImageIcon(image);
		btnAdd.setIcon(pic);
		getContentPane().add(btnAdd);
		
		JButton btnDrop = new JButton("");
		btnDrop.setIcon(new ImageIcon(WinMain.class.getResource("/images/user.png")));
		getContentPane().add(btnDrop);
		
		JButton btnAlter = new JButton("");
		btnAlter.setIcon(new ImageIcon(WinMain.class.getResource("/images/edit.png")));
		getContentPane().add(btnAlter);
		
		JButton btnSearch = new JButton("");
		btnSearch.setIcon(new ImageIcon(WinMain.class.getResource("/images/search.png")));
		getContentPane().add(btnSearch);

	}

}
