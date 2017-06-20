package Ika_AnimeEditor;
import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import javax.swing.event.MouseInputAdapter;
import java.awt.image.BufferedImage;
/**
 * <p>Title:ika 动画编辑器</p>
 * <p>Description: 编辑图片和帧，形成动画，导出后给手机调用</p>
 * <p>Copyright: airzhangfish Copyright (c) 2007</p>
 * <p>blog: http://airzhangfish.spaces.live.com</p>
 * <p>Company: Comicfishing</p>
 * <p>author airzhangfish</p>
 * <p>version 0.03a standard</p>
 * <p>last updata 2007-8-23</p>
*  物块编辑面板中间，图像部分
 */

public class mod_image
    extends JPanel {
  /**
	 * 
	 */
	
	
	private static final long serialVersionUID = 1L;
public Thread thread;

  public mod_image() {
    this.setAutoscrolls(true);
    MyListener myListener = new MyListener();
    addMouseListener(myListener);
    addMouseMotionListener(myListener);
  }

  int Rect_X = 0;
  int Rect_Y = 0;
  int Rect_W = 0;
  int Rect_H = 0;

  int tp_X = 0;
  int tp_Y = 0;
  int tp_W = 0;
  int tp_H = 0;

  boolean insideRect = false;
  boolean drawRecting = false;
  static int pressRect = -1;
  boolean movemodRect = false;
  int temp_point_x;
  int temp_point_y;
mod_one_image moimage;
  int Xline = 0;
  int Yline = 0;
  class MyListener
      extends MouseInputAdapter {

    public void mouseMoved(MouseEvent e) {
      Xline = e.getX() / SDef.mod_size * SDef.mod_size;
      Yline = e.getY() / SDef.mod_size * SDef.mod_size;
      update_Srceen();
    }

    public void mousePressed(MouseEvent e) {

      insideRect = false;
      if (SwingUtilities.isLeftMouseButton(e) && drawRecting == false) {
        //如果在已有的mod的范围内
        for (int i = 0; i < SDef.mod_words.length; i++) {
//按照比例放大
          if (e.getX() > SDef.G_modbox[i][SDef.mod_X] * SDef.mod_size + xxx &&
              e.getX() < SDef.G_modbox[i][SDef.mod_X] * SDef.mod_size + xxx + SDef.G_modbox[i][SDef.mod_W] * SDef.mod_size
              && e.getY() > SDef.G_modbox[i][SDef.mod_Y] * SDef.mod_size + yyy &&
              e.getY() < SDef.G_modbox[i][SDef.mod_Y] * SDef.mod_size + yyy + SDef.G_modbox[i][SDef.mod_H] * SDef.mod_size
              && SDef.G_modbox[i][SDef.mod_Image] == mod_panel.ImageList.getSelectedIndex()) {
            pressRect = i;
            insideRect = true;
            movemodRect = true;
            temp_point_x = (e.getX() - xxx) / SDef.mod_size;
            temp_point_y = (e.getY() - yyy) / SDef.mod_size;
            tp_X = SDef.G_modbox[pressRect][SDef.mod_X];
            tp_Y = SDef.G_modbox[pressRect][SDef.mod_Y];
            tp_W = SDef.G_modbox[pressRect][SDef.mod_W];
            tp_H = SDef.G_modbox[pressRect][SDef.mod_H];
            mod_panel.wordList.setSelectedIndex(pressRect);
            break;

          }
        }
        update_Srceen();
        mod_panel.smalImagePanel.update_Srceen();
      }
      //如果不在已有的mod范围内，就创建新的范围
      if (SwingUtilities.isLeftMouseButton(e) && drawRecting == false && insideRect == false) {
        Rect_X = (e.getX() - xxx) / SDef.mod_size;
        Rect_Y = (e.getY() - yyy) / SDef.mod_size;
        Rect_W = 0;
        Rect_H = 0;
        drawRecting = true;
        update_Srceen();
      }
      //拖拽功能，方便整体移动。
      else if (SwingUtilities.isRightMouseButton(e)) {
        change_xxx = e.getX() - xxx;
        change_yyy = e.getY() - yyy;
        update_Srceen();
      }
    }

//鼠标拖拽
    public void mouseDragged(MouseEvent e) {
      //左键创建新区域，用鼠标绘画范围
      if (SwingUtilities.isLeftMouseButton(e) && drawRecting == true) {
        Rect_W = (e.getX() - Rect_X * SDef.mod_size - xxx) / SDef.mod_size;
        Rect_H = (e.getY() - Rect_Y * SDef.mod_size - yyy) / SDef.mod_size;
      }
      //右键拖拽全局
      if (SwingUtilities.isRightMouseButton(e)) {
        xxx = (e.getX() - change_xxx) / SDef.mod_size * SDef.mod_size;
        yyy = (e.getY() - change_yyy) / SDef.mod_size * SDef.mod_size;
      }
      //左键选择选取后拖动范围
      if (SwingUtilities.isLeftMouseButton(e) && movemodRect == true && insideRect == true) {
        SDef.G_modbox[pressRect][SDef.mod_X] = (e.getX() - xxx) / SDef.mod_size - (temp_point_x - tp_X);
        SDef.G_modbox[pressRect][SDef.mod_Y] = (e.getY() - yyy) / SDef.mod_size - (temp_point_y - tp_Y);
        //list更新
        for (int i = 0; i < SDef.mod_words.length; i++) {
          SDef.mod_words[i] = "mod_" + i + ":" + SDef.G_modbox[i][SDef.mod_X] + "," + SDef.G_modbox[i][SDef.mod_Y] + ";" +
              SDef.G_modbox[i][SDef.mod_W] +
              "," + SDef.G_modbox[i][SDef.mod_H];
        }
        SDef.mod_listModel.clear();
        for (int i = 0; i < SDef.mod_words.length; i++) {
          SDef.mod_listModel.addElement(SDef.mod_words[i]);
        }
        mod_panel.wordList.setSelectedIndex(pressRect);
        create_mod_image(pressRect, SDef.G_modbox[pressRect][SDef.mod_X], SDef.G_modbox[pressRect][SDef.mod_Y], SDef.G_modbox[pressRect][SDef.mod_W],
                         SDef.G_modbox[pressRect][SDef.mod_H]);
      }
      update_Srceen();
    }

    public void mouseReleased(MouseEvent e) {

      movemodRect = false; //选定改变设置
      //左键，绘画新切片
      if (SwingUtilities.isLeftMouseButton(e) && drawRecting == true && insideRect == false) {
        //不允许长度宽度为0的动编出现
        if (Rect_W <= 0 || Rect_H <= 0) {
          drawRecting = false;
          return;
        }
        for (int i = 0; i < SDef.G_modbox.length; i++) {
          if (SDef.G_modbox[i][SDef.mod_USE] == 0) {
            SDef.G_modbox[i][SDef.mod_X] = Rect_X;
            SDef.G_modbox[i][SDef.mod_Y] = Rect_Y;
            SDef.G_modbox[i][SDef.mod_W] = Rect_W;
            SDef.G_modbox[i][SDef.mod_H] = Rect_H;
            SDef.G_modbox[i][SDef.mod_USE] = 1;
            SDef.G_modbox[i][SDef.mod_Image] = mod_panel.ImageList.getSelectedIndex();
            SDef.mod_words = new String[i + 1];
            create_mod_image(i, SDef.G_modbox[i][SDef.mod_X], SDef.G_modbox[i][SDef.mod_Y], SDef.G_modbox[i][SDef.mod_W], SDef.G_modbox[i][SDef.mod_H]);

            break;
          }
        }
        //list更新
        for (int i = 0; i < SDef.mod_words.length; i++) {
          SDef.mod_words[i] = "mod_" + i + ":" + SDef.G_modbox[i][SDef.mod_X] + "," + SDef.G_modbox[i][SDef.mod_Y] + ";" +
              SDef.G_modbox[i][SDef.mod_W] +
              "," + SDef.G_modbox[i][SDef.mod_H];
        }
        SDef.mod_listModel.clear();
        for (int i = 0; i < SDef.mod_words.length; i++) {
          SDef.mod_listModel.addElement(SDef.mod_words[i]);
        }
        drawRecting = false;
        update_Srceen();
      }
    }

  }

  int xxx = 16;
  int yyy = 16;
  int change_xxx = 0;
  int change_yyy = 0;

//物块切换时候自动切换图片
  int oldnumber = -1;
    int oldnumber2 = -1;
  public void updata() {
    if (mod_panel.ImageList.getSelectedIndex() < 0) {
      return;
    }
    if (mod_panel.wordList.getSelectedIndex() < 0) {
      return;
    }
    if (oldnumber2 != mod_panel.ImageList.getSelectedIndex()) {
      SDef.mod_size=1;
      SDef.mod_big_bfimage[mod_panel.ImageList.getSelectedIndex()] = SDef.static_mod_big_bfimage[mod_panel.ImageList.getSelectedIndex()];
      update_Srceen();
      oldnumber2= mod_panel.ImageList.getSelectedIndex();
    }




    if (oldnumber != SDef.G_modbox[mod_panel.wordList.getSelectedIndex()][SDef.mod_Image]) {
      mod_panel.ImageList.setSelectedIndex(SDef.G_modbox[mod_panel.wordList.getSelectedIndex()][SDef.mod_Image]);
      SDef.mod_size=1;
      SDef.mod_big_bfimage[SDef.G_modbox[mod_panel.wordList.getSelectedIndex()][SDef.mod_Image]] = SDef.static_mod_big_bfimage[SDef.G_modbox[mod_panel.wordList.getSelectedIndex()][SDef.mod_Image]];
      update_Srceen();
      oldnumber = SDef.G_modbox[mod_panel.wordList.getSelectedIndex()][SDef.mod_Image];
    }

  }

  public void paint(Graphics g) {
    g.setColor(new Color(SDef.BG_R, SDef.BG_G, SDef.BG_B));
    g.fillRect(0, 0, 820, 670);
    Graphics2D g2 = (Graphics2D) g;
    g2.setColor(new Color(SDef.SG_R, SDef.SG_G, SDef.SG_B));
    for (int i = 0; i < 82; i++) {
      g2.drawLine(0, 16 * i, 820, 16 * i);
      g2.drawLine(16 * i, 0, 16 * i, 820);
    }
    updata();
    //图像开始
    if (mod_panel.ImageList.getSelectedIndex() >= 0) {
      if (SDef.mod_big_bfimage[mod_panel.ImageList.getSelectedIndex()] != null) {
        g2.drawImage(SDef.mod_big_bfimage[mod_panel.ImageList.getSelectedIndex()], xxx, yyy, null);
      }
    }
    int[] temp = new int[mod_panel.wordList.getSelectedIndices().length];
    temp = mod_panel.wordList.getSelectedIndices();
    //屏幕上的物块
    g2.setColor(new Color(88, 88, 88));
    for (int i = 0; i < SDef.mod_words.length; i++) {
      if (SDef.G_modbox[i][SDef.mod_Image] == mod_panel.ImageList.getSelectedIndex()) {
        g2.drawRect(SDef.G_modbox[i][SDef.mod_X] * SDef.mod_size + xxx, SDef.G_modbox[i][SDef.mod_Y] * SDef.mod_size + yyy,
                    SDef.G_modbox[i][SDef.mod_W] * SDef.mod_size, SDef.G_modbox[i][SDef.mod_H] * SDef.mod_size);
      }
    }
    //选定的物块
    g2.setColor(new Color(188, 188, 188));
    for (int i = 0; i < temp.length; i++) {
      if (SDef.G_modbox[temp[i]][SDef.mod_Image] == mod_panel.ImageList.getSelectedIndex()) {
        g2.drawRect(SDef.G_modbox[temp[i]][SDef.mod_X] * SDef.mod_size + xxx, SDef.G_modbox[temp[i]][SDef.mod_Y] * SDef.mod_size + yyy,
                    SDef.G_modbox[temp[i]][SDef.mod_W] * SDef.mod_size, SDef.G_modbox[temp[i]][SDef.mod_H] * SDef.mod_size);
      }
    }
//正在绘画
    if (drawRecting == true) {
      g2.setColor(new Color(44, 44, 44));
      g2.drawRect(Rect_X * SDef.mod_size + xxx, Rect_Y * SDef.mod_size + yyy, Rect_W * SDef.mod_size, Rect_H * SDef.mod_size);
    }
    g2.setColor(new Color(SDef.LN_R, SDef.LN_G, SDef.LN_B));
    g2.drawLine(Xline, 0, Xline, 1000);
    g2.drawLine(0, Yline, 1000, Yline);

    //图像结束

//    g2.setColor(color2);
//    g.drawLine(410, 0, 410, 820);
    g2.setColor(new Color(0, 0, 0));
    g2.drawString(xxx + "," + yyy + ",X " + SDef.mod_size, 50, 10);
    g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                        RenderingHints.VALUE_ANTIALIAS_ON);
  }

  public static void create_mod_image(int i, int x, int y, int w, int h) {
    if (mod_panel.ImageList.getSelectedIndex() < 0) {
      return;
    }
    if (SDef.static_mod_big_bfimage[mod_panel.ImageList.getSelectedIndex()] == null) {
      return;
    }
    BufferedImage tag = new BufferedImage(w, h, BufferedImage.TYPE_INT_ARGB);
    tag.getGraphics().drawImage(SDef.static_mod_big_bfimage[mod_panel.ImageList.getSelectedIndex()], -x, -y,
                                SDef.static_mod_big_bfimage[mod_panel.ImageList.getSelectedIndex()].getWidth(null),
                                SDef.static_mod_big_bfimage[mod_panel.ImageList.getSelectedIndex()].getHeight(null), null);
    SDef.mod_bfimage[i] = tag;

  }

  public void update_Srceen() {
    repaint();
    xxx =xxx/ SDef.mod_size * SDef.mod_size;
    yyy =yyy / SDef.mod_size * SDef.mod_size;
  }

}
