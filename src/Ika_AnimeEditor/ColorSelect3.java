package Ika_AnimeEditor;
import java.awt.Adjustable;
import java.awt.Color;
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
* 轴颜色设定面板
 */

public class ColorSelect3
    extends JFrame
    implements AdjustmentListener {
  /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
private JLabel redLabel;
  private JLabel greenLabel;
  private JLabel blueLabel;
  private JScrollBar red;
  private JScrollBar green;
  private JScrollBar blue;
  private JPanel colorPanel;
  public ColorSelect3() {
    setTitle("轴颜色设定");
    setSize(300, 200);
   this.setResizable(false); //窗体不能改变大小
    addWindowListener(new WindowAdapter() {
      public void windowClosing(WindowEvent e) {
        this.windowClosed(e);
      }
    });

    Container contentPane = getContentPane();

    JPanel p = new JPanel();
    p.setLayout(new GridLayout(3, 3));

    p.add(redLabel = new JLabel("红 "+SDef.LN_R));
    p.add(red = new JScrollBar(Adjustable.HORIZONTAL,  SDef.LN_R, 0, 0, 255));
    red.setBlockIncrement(16);
    red.addAdjustmentListener(this);

    p.add(greenLabel = new JLabel("绿 "+SDef.LN_G));
    p.add(green = new JScrollBar(Adjustable.HORIZONTAL,  SDef.LN_G, 0, 0, 255));
    green.setBlockIncrement(16);
    green.addAdjustmentListener(this);

    p.add(blueLabel = new JLabel("蓝 "+SDef.LN_B));
    p.add(blue = new JScrollBar(Adjustable.HORIZONTAL,  SDef.LN_B, 0, 0, 255));
    blue.setBlockIncrement(16);
    blue.addAdjustmentListener(this);

    contentPane.add(p, "South");
    colorPanel = new JPanel();
    colorPanel.setBackground(new Color(SDef.LN_R, SDef.LN_G, SDef.LN_B));
    contentPane.add(colorPanel, "Center");
  }

  public void adjustmentValueChanged(AdjustmentEvent evt) {
    redLabel.setText("红 " + red.getValue());
    greenLabel.setText("绿 " + green.getValue());
    blueLabel.setText("蓝 " + blue.getValue());
    colorPanel.setBackground(new Color(red.getValue(), green.getValue(),
                                       blue.getValue()));
    colorPanel.repaint();
    SDef.LN_R = red.getValue();
    SDef.LN_G = green.getValue();
    SDef.LN_B = blue.getValue();
  }

}
