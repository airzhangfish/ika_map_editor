package Ika_AnimeEditor;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

/**
 * <p>
 * Title:ika 动画编辑器
 * Description: 编辑图片和帧，形成动画，导出后给手机调用
 * Copyright: airzhangfish Copyright (c) 2007
 * blog: http://airzhangfish.spaces.live.com
 * Company: Comicfishing
 * author airzhangfish
 * version 0.03a standard
 * last updata 2007-8-23
 * anime_image 动画编辑界面-右下，显示已经加入动画的帧。
 */

public class anime_image extends JPanel implements Runnable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public Thread thread;

	public anime_image() {
		// this.setPreferredSize(new Dimension(820, 670));
		thread = new Thread(this);
		thread.start();
		bfimg = new BufferedImage(820, 670, BufferedImage.TYPE_INT_ARGB);
	}

	static BufferedImage bfimg;
	static boolean anime_play = false;
	int reflash = -1;

	public void paint(Graphics g) {
		if (anime_panel.animelist.getSelectedIndex() != reflash) {
			anime_panel.useframelist.setSelectedIndex(-1);
			reflash = anime_panel.animelist.getSelectedIndex();
		}
		
			Graphics2D g2 = (Graphics2D) g;
		Graphics gg = bfimg.getGraphics();	
				
		gg.setColor(new Color(SDef.BG_R, SDef.BG_G, SDef.BG_B));
		gg.fillRect(0, 0, 820, 670);

		gg.setColor(new Color(Math.abs(SDef.BG_R + 10), Math.abs(SDef.BG_G + 10), Math.abs(SDef.BG_B + 10)));
		gg.fillRect(SDef.MO_X + 300 - 70, SDef.MO_Y + 320, SDef.MO_W, SDef.MO_H);
		gg.setColor(new Color(SDef.SG_R, SDef.SG_G, SDef.SG_B));
		for (int i = 0; i < 82; i++) {
			gg.drawLine(0, 10 * i, 820, 10 * i);
			gg.drawLine(10 * i, 0, 10 * i, 820);
		}

		// g2.setColor(color2);
		// g.drawLine(410, 0, 410, 820);
		// g.drawLine(0, 350, 820, 350);
		if (anime_play == true) {
	    	if(anime_panel.animelist.getSelectedIndex()<0){
	    		anime_panel.animelist.setSelectedIndex(0);
	    	}
			int temp_length = SDef.G_animebox[anime_panel.animelist.getSelectedIndex()].length - 2;
			if (temp_length == 0) {
				anime_play = false;
				return;
			}
			int temp_number = runcount % temp_length;
			anime_panel.useframelist.setSelectedIndex(temp_number);
		}
		int drawsm = 0;
		if (SDef.mod_big_bfimage != null && anime_panel.animelist.getSelectedIndex() >= 0 && anime_panel.useframelist.getSelectedIndex() >= 0) {
			if (anime_panel.useframelist.getSelectedIndex() + 2 >= SDef.G_animebox[anime_panel.animelist.getSelectedIndex()].length) {
				return;
			}
			drawsm = SDef.G_animebox[anime_panel.animelist.getSelectedIndex()][anime_panel.useframelist.getSelectedIndex() + 2];


			for (int i = 0; i < (SDef.G_framebox[drawsm].length - 2) / 4; i++) {
				frame_image.drawAniPart20(SDef.G_framebox[drawsm][2 + i * 4], SDef.G_framebox[drawsm][2 + i * 4 + 1] * SDef.frame_size + 300 - 70,
						SDef.G_framebox[drawsm][2 + i * 4 + 2] * SDef.frame_size + 320, SDef.G_framebox[drawsm][2 + i * 4 + 3], gg);
			}
		}
		g.drawImage(bfimg, 0, 0, null);
		g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
	}

	public void update_Srceen() {
		repaint();
	}

	static int runcount = 0;
	private boolean isrun = true;

	public void run() {
		while (isrun == true) {
			runcount++;
			if (runcount > 655350) {
				runcount = 0;
			}
			repaint();
			try {
				Thread.sleep(SDef.SYSTEM_DELAY);
			} catch (InterruptedException ex) {
			}
		}
	}

}
