import java.awt.EventQueue;

import javax.swing.JDialog;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.util.Scanner;
import java.awt.event.ActionEvent;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;

public class WinGBB extends JDialog {
   private JTextField tfComputer;
   private JComboBox cbHuman;
   private JLabel lblHumanImg;
   private JLabel lblComImg;

   /**
    * Launch the application.
    */
   public static void main(String[] args) {
      EventQueue.invokeLater(new Runnable() {
         public void run() {
            try {
               WinGBB dialog = new WinGBB();
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
   public WinGBB() {
      setTitle("가위바위보 게임");
      setBounds(100, 100, 551, 447);
      getContentPane().setLayout(null);
      
      JButton btnRun = new JButton("실행");
      btnRun.addActionListener(new ActionListener() {
         public void actionPerformed(ActionEvent e) {
            //Scanner scanner = new Scanner(System.in);
            //System.out.print("가위(0)바위(1)보(2) 입력:");
            //int human = scanner.nextInt();
            int human = Integer.parseInt(cbHuman.getSelectedItem().toString());            
            int computer = (int) (Math.random()*(2-0+1)+0) ;  // (int) (Math.random()*(최대값-최소값+1)+최소값)
            
            // 사람이 선택한 그림을 표시
            ImageIcon iconHuman = new ImageIcon(WinGBB.class.getResource("/images/"+human+".PNG"));
            lblHumanImg.setIcon(iconHuman);
            ImageIcon iconComputer = new ImageIcon(WinGBB.class.getResource("/images/"+computer+".PNG"));
            lblComImg.setIcon(iconComputer);
            
            tfComputer.setText(Integer.toString(computer));
            
            if(computer == human)
               setTitle("비김");      
            else if((computer+1)%3==human)
            	setTitle("이김");   
            else 
            	setTitle("짐");
         }
      });
      btnRun.setBounds(303, 72, 97, 23);
      getContentPane().add(btnRun);
      
      JLabel lblHuman = new JLabel("인간:");
      lblHuman.setBounds(69, 76, 57, 15);
      getContentPane().add(lblHuman);
      
      JLabel lblComputer = new JLabel("컴퓨터:");
      lblComputer.setBounds(69, 29, 57, 15);
      getContentPane().add(lblComputer);
      
      tfComputer = new JTextField();
      tfComputer.setEditable(false);
      tfComputer.setHorizontalAlignment(SwingConstants.RIGHT);
      tfComputer.setColumns(10);
      tfComputer.setBounds(163, 26, 116, 21);
      getContentPane().add(tfComputer);
      
      cbHuman = new JComboBox();
      cbHuman.setModel(new DefaultComboBoxModel(new String[] {"0", "1", "2"}));
      cbHuman.setBounds(163, 72, 116, 23);
      getContentPane().add(cbHuman);
      
      lblComImg = new JLabel("");
      lblComImg.setIcon(new ImageIcon(WinGBB.class.getResource("/images/0.PNG")));
      lblComImg.setBounds(12, 115, 197, 235);
      getContentPane().add(lblComImg);
      
      lblHumanImg = new JLabel("");
      lblHumanImg.setIcon(new ImageIcon(WinGBB.class.getResource("/images/1.PNG")));
      lblHumanImg.setBounds(308, 116, 190, 211);
      getContentPane().add(lblHumanImg);
      
      JButton btnExit = new JButton("종료");
      btnExit.addActionListener(new ActionListener() {
      	public void actionPerformed(ActionEvent e) {
//      		System.exit(0);
      		dispose();
      	}
      });
      btnExit.setBounds(303, 25, 97, 23);
      getContentPane().add(btnExit);

   }
}






