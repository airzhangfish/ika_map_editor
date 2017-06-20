package ika_toolbox;

import javax.swing.*;
import java.awt.*;

public class MyDisplay
    extends JPanel
    implements Runnable {
  public Thread thread;

  private Color color = new Color(255, 255, 0);
  private Font font = new Font("SimHei", Font.PLAIN, 13);

  public MyDisplay() {
    this.setMinimumSize(new Dimension(300, 100));
    this.setPreferredSize(new Dimension(300, 100));
    thread = new Thread(this);
    thread.start();
  }

  public void paint(Graphics g) {

    g.setColor(new Color(0, 0, 0));
    g.fillRect(0, 0, 300, 100);
    Graphics2D g2 = (Graphics2D) g;
    g2.setColor(color);
    g2.setFont(font);

    if (SDef.binfileMatirx == null) {
      g2.drawString("请载入文件或者只包含1和0的TXT文件", 10, 15);
    }
    else {
      g2.drawString("文件已载入，请选择output导出", 10, 15);
    }

    g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
  }

  public void update_Srceen() {
    repaint();
  }

  private boolean isrun = true;
  public void run() {
    while (isrun == true) {
      repaint();
      try {
        thread.sleep(SDef.SYSTEM_DELAY);
      }
      catch (InterruptedException ex) {
      }
    }
  }

}
