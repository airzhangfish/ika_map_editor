package Ika_AnimeEditor;
import javax.swing.*;
import java.awt.*;

public class anime_frame_image
    extends JPanel
    implements Runnable {
  /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
public Thread thread;

  /**
   * <p>Title:ika 动画编辑器</p>
   * <p>Description: 编辑图片和帧，形成动画，导出后给手机调用</p>
   * <p>Copyright: airzhangfish Copyright (c) 2007</p>
   * <p>blog: http://airzhangfish.spaces.live.com</p>
   * <p>Company: Comicfishing</p>
   * <p>author airzhangfish</p>
   * <p>version 0.03a standard</p>
   * <p>last updata 2007-8-23</p>
   *  anime_frame_image 动画编辑界面-右上，显示选择的帧。
   */


  public anime_frame_image() {
    //  this.setPreferredSize(new Dimension(820, 670));
    thread = new Thread(this);
    thread.start();
  }

  int reflash = -1;
  public void paint(Graphics g) {
    if (SDef.mod_big_bfimage != null &&anime_panel.animelist.getSelectedIndex() != reflash) {
    	if(anime_panel.animelist.getSelectedIndex()<0){
    		anime_panel.animelist.setSelectedIndex(0);
    	}
    	
      SDef.use_framelist = new String[SDef.G_animebox[anime_panel.animelist.getSelectedIndex()].length - 2];
      for (int i = 0; i < SDef.G_animebox[anime_panel.animelist.getSelectedIndex()].length - 2; i++) {
  	  SDef.use_framelist[i] = "use_fra:" + SDef.G_animebox[anime_panel.animelist.getSelectedIndex()][i + 2] + ":"+SDef.G_framebox_str[SDef.G_animebox[anime_panel.animelist.getSelectedIndex()][i + 2]];
      }
      SDef.use_framelistModel.clear();
      for (int i = 0; i < SDef.use_framelist.length; i++) {
        SDef.use_framelistModel.addElement(SDef.use_framelist[i]);
      }
      anime_panel.useframelist.setSelectedIndex( -1);
      reflash = anime_panel.animelist.getSelectedIndex();
    }
    g.setColor(new Color(SDef.BG_R, SDef.BG_G, SDef.BG_B));
    g.fillRect(0, 0, 820, 670);
    g.setColor(new Color(Math.abs(SDef.BG_R + 10), Math.abs(SDef.BG_G + 10), Math.abs(SDef.BG_B + 10)));
    g.fillRect(SDef.MO_X+300-70, SDef.MO_Y+320+20, SDef.MO_W, SDef.MO_H);
    Graphics2D g2 = (Graphics2D) g;
    //绘画
    if (SDef.mod_big_bfimage != null && anime_panel.framelist.getSelectedIndex() >= 0) {
      for (int i = 0; i < (SDef.G_framebox[anime_panel.framelist.getSelectedIndex()].length - 2) / 4; i++) {

        frame_image.drawAniPart20(SDef.G_framebox[anime_panel.framelist.getSelectedIndex()][2 + i * 4],
                      SDef.G_framebox[anime_panel.framelist.getSelectedIndex()][2 + i * 4 + 1]*SDef.frame_size+300-70,
                      SDef.G_framebox[anime_panel.framelist.getSelectedIndex()][2 + i * 4 + 2]*SDef.frame_size+320+20,
                      SDef.G_framebox[anime_panel.framelist.getSelectedIndex()][2 + i * 4 + 3], g);
      }
    }
    g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                        RenderingHints.VALUE_ANTIALIAS_ON);
  }

  public void update_Srceen() {
    repaint();
  }

  private boolean isrun = true;
  public void run() {
    while (isrun == true) {
      repaint();
      try {
        Thread.sleep(SDef.SYSTEM_DELAY);
      }
      catch (InterruptedException ex) {
      }
    }
  }

}
