package Ika_AnimeEditor;
import java.awt.Adjustable;
import java.awt.Container;
import java.awt.GridLayout;
import java.awt.event.AdjustmentEvent;
import java.awt.event.AdjustmentListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollBar;
/**
 * <p>Title:ika 动画编辑器</p>
 * <p>Description: 编辑图片和帧，形成动画，导出后给手机调用</p>
 * <p>Copyright: airzhangfish Copyright (c) 2007</p>
 * <p>blog: http://airzhangfish.spaces.live.com</p>
 * <p>Company: Comicfishing</p>
 * <p>author airzhangfish</p>
 * <p>version 0.03a standard</p>
 * <p>last updata 2007-8-23</p>
* 手机屏幕大小设置
 */

public class ScreenSelect
    extends JFrame
    implements AdjustmentListener {
  /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
private JLabel XLabel;
  private JLabel YLabel;
  private JLabel WLabel;
  private JLabel HLabel;
  private JScrollBar red;
  private JScrollBar green;
  private JScrollBar blue;
  private JScrollBar HHH;
  public ScreenSelect() {
    setTitle("手机屏幕大小设置");
    setSize(300, 120);
    this.setResizable(false); //窗体不能改变大小
    addWindowListener(new WindowAdapter() {
      public void windowClosing(WindowEvent e) {
        this.windowClosed(e);
      }
    });

    Container contentPane = getContentPane();

    JPanel p = new JPanel();
    p.setLayout(new GridLayout(4, 4));

    p.add(XLabel = new JLabel("X坐标 " + SDef.MO_X));
    p.add(red = new JScrollBar(Adjustable.HORIZONTAL, SDef.MO_X, 0, -500, 500));
    red.setBlockIncrement(16);
    red.addAdjustmentListener(this);

    p.add(YLabel = new JLabel("Y坐标 " + SDef.MO_Y));
    p.add(green = new JScrollBar(Adjustable.HORIZONTAL, SDef.MO_Y, 0,  -500, 500));
    green.setBlockIncrement(16);
    green.addAdjustmentListener(this);

    p.add(WLabel = new JLabel("屏幕宽 " + SDef.MO_W));
    p.add(blue = new JScrollBar(Adjustable.HORIZONTAL, SDef.MO_W, 0, 0, 1000));
    blue.setBlockIncrement(16);
    blue.addAdjustmentListener(this);

    p.add(HLabel = new JLabel("屏幕长  " + SDef.MO_H));
    p.add(HHH = new JScrollBar(Adjustable.HORIZONTAL, SDef.MO_H, 0, 0, 1000));
    HHH.setBlockIncrement(16);
    HHH.addAdjustmentListener(this);
    contentPane.add(p, "South");
  }

  public void adjustmentValueChanged(AdjustmentEvent evt) {
    XLabel.setText("X坐标 " + red.getValue());
    YLabel.setText("Y坐标 " + green.getValue());
    WLabel.setText("屏幕宽 " + blue.getValue());
    HLabel.setText("屏幕长 " + HHH.getValue());
    SDef.MO_X = red.getValue();
    SDef.MO_Y = green.getValue();
    SDef.MO_W = blue.getValue();
    SDef.MO_H = HHH.getValue();
    frame_panel.BigImagePanel.update_Srceen();
    anime_panel.BigImagePanel.update_Srceen();
    anime_panel.small_Imagepanel.update_Srceen();
  }
}
