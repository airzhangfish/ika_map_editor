package Ika_AnimeEditor;
import javax.swing.*;
import java.awt.*;
import javax.swing.event.MouseInputAdapter;
import java.awt.event.MouseEvent;

/**
 * <p>Title:ika 动画编辑器</p>
 * <p>Description: 编辑图片和帧，形成动画，导出后给手机调用</p>
 * <p>Copyright: airzhangfish Copyright (c) 2007</p>
 * <p>blog: http://airzhangfish.spaces.live.com</p>
 * <p>Company: Comicfishing</p>
 * <p>author airzhangfish</p>
 * <p>version 0.03a standard</p>
 * <p>last updata 2007-8-23</p>
* frame_mod_image帧编辑界面右上角，物块选取
 *
 */

public class frame_mod_image
    extends JPanel {
  /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
public Thread thread;

  public frame_mod_image() {
    //  this.setPreferredSize(new Dimension(820, 670));
    MyListener myListener = new MyListener();
    addMouseListener(myListener);
    addMouseMotionListener(myListener);
  }

  static int pressRect = -1;
  int change_xxx = 0;
  int change_yyy = 0;
  class MyListener
      extends MouseInputAdapter {

    public void mousePressed(MouseEvent e) {
      if (SwingUtilities.isMiddleMouseButton(e)) {
        if (SDef.mod_Image_name.length < 1) {
          return;
        }
        mod_panel.ImageList.setSelectedIndex( (mod_panel.ImageList.getSelectedIndex() + 1) % SDef.mod_Image_name.length);

      }
      if (SwingUtilities.isLeftMouseButton(e)) {
        for (int i = 0; i < SDef.mod_words.length; i++) {
          if (e.getX() > SDef.G_modbox[i][SDef.mod_X] * SDef.mod_size + xxx &&
              e.getX() < SDef.G_modbox[i][SDef.mod_X] * SDef.mod_size + xxx + SDef.G_modbox[i][SDef.mod_W] * SDef.mod_size
              && e.getY() > SDef.G_modbox[i][SDef.mod_Y] * SDef.mod_size + yyy &&
              e.getY() < SDef.G_modbox[i][SDef.mod_Y] * SDef.mod_size + yyy + SDef.G_modbox[i][SDef.mod_H] * SDef.mod_size
              && SDef.G_modbox[i][SDef.mod_Image] == mod_panel.ImageList.getSelectedIndex()) {
            pressRect = i;
            mod_panel.wordList.setSelectedIndex(pressRect);
            break;
          }
        }
        frame_image.frame_pressRect = -1;
      }

      if (SwingUtilities.isRightMouseButton(e)) {
        change_xxx = e.getX() - xxx;
        change_yyy = e.getY() - yyy;
      }
      update_Srceen();
    }

    public void mouseDragged(MouseEvent e) {
      if (SwingUtilities.isRightMouseButton(e)) {
        xxx = e.getX() - change_xxx;
        yyy = e.getY() - change_yyy;

      }
           update_Srceen();
    }
   
  }

  int xxx = 10;
  int yyy = 10;
  public void paint(Graphics g) {
    g.setColor(new Color(SDef.BG_R, SDef.BG_G, SDef.BG_B));
    g.fillRect(0, 0, 820, 670);
    Graphics2D g2 = (Graphics2D) g;

//大图
    if (mod_panel.ImageList.getSelectedIndex() >= 0 && SDef.mod_big_bfimage[mod_panel.ImageList.getSelectedIndex()] != null) {
      g.drawImage(SDef.mod_big_bfimage[mod_panel.ImageList.getSelectedIndex()], xxx, yyy, null);
    }

    g.setColor(new Color(88, 88, 88));
    for (int i = 0; i < SDef.mod_words.length; i++) {
      if (SDef.G_modbox[i][SDef.mod_Image] == mod_panel.ImageList.getSelectedIndex()) {
        g.drawRect( (int) (SDef.G_modbox[i][SDef.mod_X] * SDef.mod_size + xxx), (int) (SDef.G_modbox[i][SDef.mod_Y] * SDef.mod_size + yyy),
                   (int) (SDef.G_modbox[i][SDef.mod_W] * SDef.mod_size), (int) (SDef.G_modbox[i][SDef.mod_H] * SDef.mod_size));
      }
    }
    g.setColor(new Color(188, 188, 188));
    if (pressRect != -1) {
      if (SDef.G_modbox[pressRect][SDef.mod_Image] == mod_panel.ImageList.getSelectedIndex()) {
        g.drawRect( (int) (SDef.G_modbox[pressRect][SDef.mod_X] * SDef.mod_size + xxx),
                   (int) (SDef.G_modbox[pressRect][SDef.mod_Y] * SDef.mod_size + yyy),
                   (int) (SDef.G_modbox[pressRect][SDef.mod_W] * SDef.mod_size), (int) (SDef.G_modbox[pressRect][SDef.mod_H] * SDef.mod_size));
      }
    }
    g2.setColor(new Color(0, 0, 0));
    g2.drawString(xxx + "," + yyy + " X" + SDef.mod_size, 50, 10);
    g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                        RenderingHints.VALUE_ANTIALIAS_ON);
  }

  public void update_Srceen() {
    repaint();
  }

}
