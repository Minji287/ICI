import java.awt.Color;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Vector;

import com.thehowtotutorial.splashscreen.JSplash;


public class Splash {
   
   public static void main(String[] args) throws InterruptedException {
      JSplash splash = new JSplash(Splash.class.getResource("images.jpg"),
            true,true,false,"V1",null, Color.YELLOW, Color.BLUE);
      splash.splashOn();
      //================================
      try {
         Class.forName("com.mysql.cj.jdbc.Driver");
         Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/sqlDB","root","1234");                  
         Statement stmt = con.createStatement();
         
         String sql = "select * from bookTBL order by title asc";
         ResultSet rs = stmt.executeQuery(sql);
         int num=0;
         while(rs.next()) {
            splash.setProgress(num,rs.getString("title"));
            Thread.sleep(10);
            num++;
         }
      } catch (ClassNotFoundException | SQLException e1) {
         // TODO Auto-generated catch block
         e1.printStackTrace();
      }
      //=========================
      
      splash.splashOff();
      
      WinMain winMain = new WinMain();
      winMain.setModal(true);
      winMain.setVisible(true);
   }

}