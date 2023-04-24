import java.awt.EventQueue;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;

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
		btnDrop.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				WinRemoveMember winRemoveMember = new WinRemoveMember();
				winRemoveMember.setModal(true);
				winRemoveMember.setVisible(true);
			}
		});
		btnDrop.setIcon(new ImageIcon(WinMain.class.getResource("/images/user.png")));
		getContentPane().add(btnDrop);
		
		JButton btnAlter = new JButton("");
		btnAlter.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				WinMultiSearch winMultiSearch = new WinMultiSearch();
				winMultiSearch.setModal(true);
				winMultiSearch.setVisible(true);
			}
		});
		btnAlter.setIcon(new ImageIcon(WinMain.class.getResource("/images/edit.png")));
		getContentPane().add(btnAlter);
		
		JButton btnSearch = new JButton("");
		btnSearch.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				WinSearchResult2 wsr2 = new WinSearchResult2();
				wsr2.setModal(true);
				wsr2.setVisible(true);
			}
		});
		btnSearch.setIcon(new ImageIcon(WinMain.class.getResource("/images/search.png")));
		getContentPane().add(btnSearch);

	}

}
