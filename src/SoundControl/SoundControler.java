package SoundControl;


import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JTabbedPane;

public class SoundControler extends JFrame implements ActionListener {

  
      private static final long serialVersionUID = 1L;
      private static final String myversion = "0.01standard ";
      private JTabbedPane jtp;
      private JMenuBar jMenuBar1 = new JMenuBar();
      private JMenu jMenuHelp = new JMenu("说明");
      private JMenuItem jMenuFileExit = new JMenuItem("退出");

      public void actionPerformed(ActionEvent actionEvent) {
          Object source = actionEvent.getSource();
          // 软件退出
          if (source == jMenuFileExit) {
              System.exit(0);
          }

      }

      public SoundControler() {

          this.setSize(200,240); // 将窗体的大小
          this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
          this.setResizable(false); // 窗体不能改变大小
          this.setTitle("声音控制台 "+myversion); // 设置标题


          this.setLocation(200, 200);
          jMenuHelp.add(jMenuFileExit);
          jMenuFileExit.addActionListener(this);
          jMenuBar1.add(jMenuHelp);
          this.setJMenuBar(jMenuBar1);
          Container contents = getContentPane();
          jtp = new JTabbedPane(JTabbedPane.TOP);
          sc=new SounderCon();
          jtp.addTab("声音控制台",sc);
          contents.add(jtp);
          setVisible(true);
      }
      
      SounderCon sc;
      
      
      public static void main(String args[]) {
      SDef.setMySkin(3);
          new SoundControler();
      }


  
  
  
  
  
  
  
  
}
