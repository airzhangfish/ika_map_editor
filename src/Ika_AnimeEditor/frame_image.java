package Ika_AnimeEditor;
import javax.swing.*;

import java.awt.*;

import javax.swing.event.MouseInputAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;

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
 * </p>
 * 帧编辑界面，中间。
 */

public class frame_image extends JPanel {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public Thread thread;

	public frame_image() {
		addMouseListener(new MyListener());
		addMouseMotionListener(new MyListener());
	}

	
	int moveX;
	int moveY;
	static int frame_pressRect = -1;
	static boolean mod_canmove = false;
	int tp_x;
	int tp_y;

	class MyListener extends MouseInputAdapter {
		public void mouseMoved(MouseEvent e) {
			moveX = e.getX();
			moveY = e.getY();
			update_Srceen();
		}

		public void mousePressed(MouseEvent e) {
			// 左键，添加新物块
			if (SwingUtilities.isLeftMouseButton(e) && frame_mod_image.pressRect != -1) {
				if( frame_panel.wordList.getSelectedIndex() <0){
					JOptionPane.showMessageDialog(null, "请选择或者创建一个新帧来放置物块", "出错", JOptionPane.ERROR_MESSAGE);
					return;
				}
				
				
				int mod_frame_id = frame_mod_image.pressRect;
				int mod_frame_x = (e.getX()-SDef.frame_total_x)/SDef.frame_size;
				int mod_frame_y = (e.getY()-SDef.frame_total_y)/SDef.frame_size;
				int mod_frame_tran = 0;
				int[] temp = SDef.G_framebox[frame_panel.wordList.getSelectedIndex()];
				SDef.G_framebox[frame_panel.wordList.getSelectedIndex()] = new int[SDef.G_framebox[frame_panel.wordList.getSelectedIndex()].length + 4];
				for (int i = 0; i < temp.length; i++) {
					SDef.G_framebox[frame_panel.wordList.getSelectedIndex()][i] = temp[i];
				}
				SDef.G_framebox[frame_panel.wordList.getSelectedIndex()][temp.length] = mod_frame_id;
				SDef.G_framebox[frame_panel.wordList.getSelectedIndex()][temp.length + 1] = mod_frame_x;
				SDef.G_framebox[frame_panel.wordList.getSelectedIndex()][temp.length + 2] = mod_frame_y;
				SDef.G_framebox[frame_panel.wordList.getSelectedIndex()][temp.length + 3] = mod_frame_tran;
				SDef.G_framebox[frame_panel.wordList.getSelectedIndex()][SDef.frame_number]++;

				// use_mod_list
				SDef.use_mod_words = new String[(SDef.G_framebox[frame_panel.wordList.getSelectedIndex()].length - 2) / 4];
				for (int i = 0; i < SDef.use_mod_words.length; i++) {
					SDef.use_mod_words[i] = "use_mod:" + SDef.G_framebox[frame_panel.wordList.getSelectedIndex()][2 + i * 4];
				}
				SDef.use_modlist.clear();
				for (int i = 0; i < SDef.use_mod_words.length; i++) {
					SDef.use_modlist.addElement(SDef.use_mod_words[i]);
				}
				frame_panel.modlist.setSelectedIndex(0);
				frame_mod_image.pressRect = -1;
				frame_panel.up_frame_list();
			}

			// 选取已有物块
			if (SwingUtilities.isLeftMouseButton(e) && frame_mod_image.pressRect == -1 && frame_panel.wordList.getSelectedIndex() >= 0) {
				for (int i = 0; i < SDef.use_mod_words.length; i++) {

					int xx = SDef.G_framebox[frame_panel.wordList.getSelectedIndex()][2 + i * 4 + 1]*SDef.frame_size+SDef.frame_total_x;
					int ww = SDef.G_modbox[SDef.G_framebox[frame_panel.wordList.getSelectedIndex()][2 + i * 4]][SDef.mod_W]*SDef.frame_size;
					int yy = SDef.G_framebox[frame_panel.wordList.getSelectedIndex()][2 + i * 4 + 2]*SDef.frame_size+SDef.frame_total_y;
					int hh = SDef.G_modbox[SDef.G_framebox[frame_panel.wordList.getSelectedIndex()][2 + i * 4]][SDef.mod_H]*SDef.frame_size;

					if (
					// trans=0
					(e.getX() > xx && e.getX() < xx + ww && e.getY() > yy && e.getY() < yy + hh && SDef.G_framebox[frame_panel.wordList
							.getSelectedIndex()][2 + i * 4 + 3] == 0)
							||
							// trans=1
							(e.getX() > xx - ww && e.getX() < xx && e.getY() > yy && e.getY() < yy + hh && SDef.G_framebox[frame_panel.wordList
									.getSelectedIndex()][2 + i * 4 + 3] == 1) ||
							// trans=2
							(e.getX() > xx && e.getX() < xx + ww && e.getY() > yy - hh && e.getY() < yy && SDef.G_framebox[frame_panel.wordList
									.getSelectedIndex()][2 + i * 4 + 3] == 2) ||
							// trans=3
							(e.getX() > xx - ww && e.getX() < xx && e.getY() > yy - hh && e.getY() < yy && SDef.G_framebox[frame_panel.wordList
									.getSelectedIndex()][2 + i * 4 + 3] == 3) ||
							// trans=4
							(e.getX() > xx && e.getX() < xx + hh && e.getY() > yy && e.getY() < yy + ww && SDef.G_framebox[frame_panel.wordList
									.getSelectedIndex()][2 + i * 4 + 3] == 4) ||
							// trans=5
							(e.getX() > xx && e.getX() < xx + hh && e.getY() > yy && e.getY() < yy + ww && SDef.G_framebox[frame_panel.wordList
									.getSelectedIndex()][2 + i * 4 + 3] == 5) ||
							// trans=6
							(e.getX() > xx && e.getX() < xx + hh && e.getY() > yy && e.getY() < yy + ww && SDef.G_framebox[frame_panel.wordList
									.getSelectedIndex()][2 + i * 4 + 3] == 6) ||
							// trans=7
							(e.getX() > xx && e.getX() < xx + hh && e.getY() > yy && e.getY() < yy + ww && SDef.G_framebox[frame_panel.wordList
									.getSelectedIndex()][2 + i * 4 + 3] == 7)) {
						frame_pressRect = i;
						frame_panel.modlist.setSelectedIndex(frame_pressRect);
						mod_canmove = true;
						tp_x = e.getX() - SDef.G_framebox[frame_panel.wordList.getSelectedIndex()][2 + frame_pressRect * 4 + 1];
						tp_y = e.getY() - SDef.G_framebox[frame_panel.wordList.getSelectedIndex()][2 + frame_pressRect * 4 + 2];
						break;
					}
				}
			}

			if (SwingUtilities.isRightMouseButton(e)) {
				frame_mod_image.pressRect = -1;
				frame_pressRect = -1;
				frame_panel.modlist.setSelectedIndex(frame_pressRect);
				
				
				tmp_move_x=e.getX()-SDef.frame_total_x;
				tmp_move_y=e.getY()-SDef.frame_total_y;
			}
			update_Srceen();
		}

		public void mouseDragged(MouseEvent e) {
			if (SwingUtilities.isLeftMouseButton(e) && mod_canmove == true) {
				SDef.G_framebox[frame_panel.wordList.getSelectedIndex()][2 + frame_pressRect * 4 + 1] = e.getX() - tp_x;
				SDef.G_framebox[frame_panel.wordList.getSelectedIndex()][2 + frame_pressRect * 4 + 2] = e.getY() - tp_y;
			}
			
			if (SwingUtilities.isRightMouseButton(e) ) {
				//SDef.frame_total_x;
				SDef.frame_total_x=e.getX()-tmp_move_x;
				SDef.frame_total_y=e.getY()-tmp_move_y;
			}
			moveX = e.getX();
			moveY = e.getY();
			update_Srceen();
		}

		
		public void mouseReleased(MouseEvent e) {
			mod_canmove = false;
			update_Srceen();
		}
	}

	
public static int tmp_move_x;
public static int tmp_move_y;			
	// 更新资料
	int temp_seclet_list = 0;

	public void updata_use_mod_list() {
		if (temp_seclet_list != frame_panel.wordList.getSelectedIndex()) {
			frame_pressRect = -1;
			temp_seclet_list = frame_panel.wordList.getSelectedIndex();
			if (frame_panel.wordList.getSelectedIndex() < 0) {
				return;
			}
			SDef.use_mod_words = new String[(SDef.G_framebox[frame_panel.wordList.getSelectedIndex()].length - 2) / 4];
			for (int i = 0; i < SDef.use_mod_words.length; i++) {
				SDef.use_mod_words[i] = "use_mod:" + SDef.G_framebox[frame_panel.wordList.getSelectedIndex()][2 + i * 4];
			}
			SDef.use_modlist.clear();
			for (int i = 0; i < SDef.use_mod_words.length; i++) {
				SDef.use_modlist.addElement(SDef.use_mod_words[i]);
			}
			frame_panel.modlist.setSelectedIndex(0);
		}
	}

	public void paint(Graphics g) {

		updata_use_mod_list();
		g.setColor(new Color(SDef.BG_R, SDef.BG_G, SDef.BG_B));
		g.fillRect(0, 0, 820, 670);
		Graphics2D g2 = (Graphics2D) g;
		g.setColor(new Color(Math.abs(SDef.BG_R + 10), Math.abs(SDef.BG_G + 10), Math.abs(SDef.BG_B + 10)));
		g.fillRect(SDef.MO_X*SDef.frame_size+SDef.frame_total_x, SDef.MO_Y*SDef.frame_size+SDef.frame_total_y, SDef.MO_W*SDef.frame_size, SDef.MO_H*SDef.frame_size);
		g2.setColor(new Color(SDef.SG_R, SDef.SG_G, SDef.SG_B));
		for (int i = 0; i < 82; i++) {
			g.drawLine(0, 10 * i, 820, 10 * i);
			g.drawLine(10 * i, 0, 10 * i, 820);
		}
		g2.setColor(new Color(SDef.LN_R, SDef.LN_G, SDef.LN_B));
		
		
		
		g.drawLine(SDef.frame_total_x, 0, SDef.frame_total_x, 1000);
		g.drawLine(0, SDef.frame_total_y, 1000, SDef.frame_total_y);
		g.drawLine(SDef.frame_total_x, 0, SDef.frame_total_x, 820);
		g.drawLine(0, SDef.frame_total_y, 1000, SDef.frame_total_y);

		// g.drawRect(SDef.MO_X, SDef.MO_Y, SDef.MO_W, SDef.MO_H);
		// 绘画
		g.setClip(0, 0, 1000, 1000);
		if (SDef.mod_big_bfimage != null && frame_panel.wordList.getSelectedIndex() >= 0) {
			for (int i = 0; i < (SDef.G_framebox[frame_panel.wordList.getSelectedIndex()].length - 2) / 4; i++) {
				drawAniPart20(SDef.G_framebox[frame_panel.wordList.getSelectedIndex()][2 + i * 4], SDef.G_framebox[frame_panel.wordList
						.getSelectedIndex()][2 + i * 4 + 1]*SDef.frame_size+SDef.frame_total_x, SDef.G_framebox[frame_panel.wordList.getSelectedIndex()][2 + i * 4 + 2]*SDef.frame_size+SDef.frame_total_y,
						SDef.G_framebox[frame_panel.wordList.getSelectedIndex()][2 + i * 4 + 3], g);
			}
		}

		// frame_pressRect 选取物块，白边范围
		if (SDef.mod_big_bfimage != null && frame_panel.wordList.getSelectedIndex() >= 0 && frame_pressRect != -1) {
			g.setColor(new Color(250, 250, 250));
			g.setClip(0, 0, 1000, 1000);

			switch (SDef.G_framebox[frame_panel.wordList.getSelectedIndex()][2 + frame_pressRect * 4 + 3]) {
			case 0:
				g.drawRect(SDef.G_framebox[frame_panel.wordList.getSelectedIndex()][2 + frame_pressRect * 4 + 1]*SDef.frame_size+SDef.frame_total_x,
						SDef.G_framebox[frame_panel.wordList.getSelectedIndex()][2 + frame_pressRect * 4 + 2]*SDef.frame_size+SDef.frame_total_y,
						SDef.G_modbox[SDef.G_framebox[frame_panel.wordList.getSelectedIndex()][2 + frame_pressRect * 4]][SDef.mod_W]*SDef.frame_size,
						SDef.G_modbox[SDef.G_framebox[frame_panel.wordList.getSelectedIndex()][2 + frame_pressRect * 4]][SDef.mod_H]*SDef.frame_size);
				break;
			case 1:
				g.drawRect(SDef.G_framebox[frame_panel.wordList.getSelectedIndex()][2 + frame_pressRect * 4 + 1]
						- SDef.G_modbox[SDef.G_framebox[frame_panel.wordList.getSelectedIndex()][2 + frame_pressRect * 4]][SDef.mod_W]*SDef.frame_size+SDef.frame_total_x,
						SDef.G_framebox[frame_panel.wordList.getSelectedIndex()][2 + frame_pressRect * 4 + 2]*SDef.frame_size+SDef.frame_total_y,
						SDef.G_modbox[SDef.G_framebox[frame_panel.wordList.getSelectedIndex()][2 + frame_pressRect * 4]][SDef.mod_W]*SDef.frame_size,
						SDef.G_modbox[SDef.G_framebox[frame_panel.wordList.getSelectedIndex()][2 + frame_pressRect * 4]][SDef.mod_H*SDef.frame_size]);
				break;
			case 2:
				g.drawRect(SDef.G_framebox[frame_panel.wordList.getSelectedIndex()][2 + frame_pressRect * 4 + 1]*SDef.frame_size+SDef.frame_total_x,
						SDef.G_framebox[frame_panel.wordList.getSelectedIndex()][2 + frame_pressRect * 4 + 2]
								- SDef.G_modbox[SDef.G_framebox[frame_panel.wordList.getSelectedIndex()][2 + frame_pressRect * 4]][SDef.mod_H]*SDef.frame_size+SDef.frame_total_y,
						SDef.G_modbox[SDef.G_framebox[frame_panel.wordList.getSelectedIndex()][2 + frame_pressRect * 4]][SDef.mod_W]*SDef.frame_size,
						SDef.G_modbox[SDef.G_framebox[frame_panel.wordList.getSelectedIndex()][2 + frame_pressRect * 4]][SDef.mod_H]*SDef.frame_size);
				break;
			case 3:
				g.drawRect(SDef.G_framebox[frame_panel.wordList.getSelectedIndex()][2 + frame_pressRect * 4 + 1]
						- SDef.G_modbox[SDef.G_framebox[frame_panel.wordList.getSelectedIndex()][2 + frame_pressRect * 4]][SDef.mod_W]*SDef.frame_size+SDef.frame_total_x,
						SDef.G_framebox[frame_panel.wordList.getSelectedIndex()][2 + frame_pressRect * 4 + 2]
								- SDef.G_modbox[SDef.G_framebox[frame_panel.wordList.getSelectedIndex()][2 + frame_pressRect * 4]][SDef.mod_H]*SDef.frame_size+SDef.frame_total_y,
						SDef.G_modbox[SDef.G_framebox[frame_panel.wordList.getSelectedIndex()][2 + frame_pressRect * 4]][SDef.mod_W]*SDef.frame_size,
						SDef.G_modbox[SDef.G_framebox[frame_panel.wordList.getSelectedIndex()][2 + frame_pressRect * 4]][SDef.mod_H]*SDef.frame_size);
				break;
			case 4:
			case 5:
			case 6:
			case 7:
				g.drawRect(SDef.G_framebox[frame_panel.wordList.getSelectedIndex()][2 + frame_pressRect * 4 + 1]*SDef.frame_size+SDef.frame_total_x,
						SDef.G_framebox[frame_panel.wordList.getSelectedIndex()][2 + frame_pressRect * 4 + 2]*SDef.frame_size+SDef.frame_total_y,
						SDef.G_modbox[SDef.G_framebox[frame_panel.wordList.getSelectedIndex()][2 + frame_pressRect * 4]][SDef.mod_H]*SDef.frame_size,
						SDef.G_modbox[SDef.G_framebox[frame_panel.wordList.getSelectedIndex()][2 + frame_pressRect * 4]][SDef.mod_W]*SDef.frame_size);
				break;
			}
		}
		// 鼠标跟随的图片
		if (SDef.mod_big_bfimage != null && frame_mod_image.pressRect != -1) {
			g.drawImage(SDef.mod_bfimage[frame_mod_image.pressRect], moveX, moveY, null);
			g.setColor(new Color(250, 250, 250));
		}

		g2.setColor(new Color(SDef.LN_R, 100, 50));
		g2.drawLine(moveX, 0, moveX, 1000);
		g2.drawLine(0, moveY, 1000, moveY);

		g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
	}

	// 旋转
	public static AffineTransform transform;
	public static AffineTransformOp op;
	public static BufferedImage filteredImage;

	public static void rotate90(BufferedImage img, int angle, int n) {
		switch (angle) {
		case 0:
			transform = AffineTransform.getRotateInstance(Math.toRadians(0), 0, 0);
			op = new AffineTransformOp(transform, AffineTransformOp.TYPE_BILINEAR);
			filteredImage = new BufferedImage(img.getWidth(), img.getHeight(), img.getType());
			op.filter(img, filteredImage);
			break;
		case 90:
			transform = AffineTransform.getRotateInstance(Math.toRadians(90), img.getHeight() / 2, img.getHeight() / 2);
			op = new AffineTransformOp(transform, AffineTransformOp.TYPE_BILINEAR);
			filteredImage = new BufferedImage(img.getHeight(), img.getWidth(), img.getType());
			op.filter(img, filteredImage);
			// img = filteredImage;
			break;
		case 180:
			transform = AffineTransform.getRotateInstance(Math.toRadians(180), img.getWidth() / 2, img.getHeight() / 2);
			op = new AffineTransformOp(transform, AffineTransformOp.TYPE_BILINEAR);
			filteredImage = new BufferedImage(img.getWidth(), img.getHeight(), img.getType());
			op.filter(img, filteredImage);
			// img = filteredImage;
			break;
		case 270:
			transform = AffineTransform.getRotateInstance(Math.toRadians(270), img.getWidth() / 2, img.getWidth() / 2);
			op = new AffineTransformOp(transform, AffineTransformOp.TYPE_BILINEAR);
			filteredImage = new BufferedImage(img.getHeight(), img.getWidth(), img.getType());
			op.filter(img, filteredImage);
			// img = filteredImage;
			break;

		}
	}

	// 绘画成像
	public static void drawAniPart20(int n, int x, int y, int trans, Graphics g) {
	      if(SDef.mod_bfimage[n]==null){
    	   System.out.println("drawAniPart20 image  null");
	    	  return;
       }	
		if (trans == 0) {
			rotate90(SDef.mod_bfimage[n], 0, n);
			g.drawImage(filteredImage, x, y, null);
		} else {
			switch (trans) {
			case 1: // X镜像
				rotate90(SDef.mod_bfimage[n], 0, n);
				g.drawImage(filteredImage, x - filteredImage.getWidth(), y, x, y + filteredImage.getHeight(), filteredImage.getWidth(), 0, 0,
						filteredImage.getHeight(), null); // X镜像
				break;
			case 2: // Y镜像
				rotate90(SDef.mod_bfimage[n], 0, n);
				g.drawImage(filteredImage, x, y - filteredImage.getHeight(), x + filteredImage.getWidth(), y, 0, filteredImage.getHeight(),
						filteredImage.getWidth(), 0, null); // Y镜像
				break;
			case 3: // X镜像+Y镜像 或者旋转180度
				rotate90(SDef.mod_bfimage[n], 180, n);
				g.drawImage(filteredImage, x - filteredImage.getWidth(), y - filteredImage.getHeight(), null);
				break;
			case 4: // 旋转270+Y镜像
				rotate90(SDef.mod_bfimage[n], 270, n);
				g.drawImage(filteredImage, x, y, x + filteredImage.getWidth(), y + filteredImage.getHeight(), filteredImage.getWidth(), 0, 0,
						filteredImage.getHeight(), null); // Y镜像
				break;
			case 5: // 旋转90
				rotate90(SDef.mod_bfimage[n], 90, n);
				g.drawImage(filteredImage, x, y, null);
				break;
			case 6: // 旋转270
				rotate90(SDef.mod_bfimage[n], 270, n);
				g.drawImage(filteredImage, x, y, null);
				break;
			case 7: // 旋转90+Y镜像
				rotate90(SDef.mod_bfimage[n], 90, n);
				g.drawImage(filteredImage, x, y, x + filteredImage.getWidth(), y + filteredImage.getHeight(), filteredImage.getWidth(), 0, 0,
						filteredImage.getHeight(), null); // Y镜像
				break;
			default:
				System.out.println("2.0trans error");
				break;
			}
		}
	}

	public static BufferedImage getbigimage(BufferedImage bfimage){
				BufferedImage temp_bfimage = null;
		try{
		temp_bfimage = new BufferedImage((int) (bfimage.getWidth() * SDef.frame_size),
				(int) (bfimage.getHeight() * SDef.frame_size),
				BufferedImage.TYPE_INT_ARGB);
		transform.setToScale(SDef.frame_size, SDef.frame_size);
		AffineTransformOp op = new AffineTransformOp(transform, null);
		op.filter(bfimage, temp_bfimage);
		}catch(Exception e){
			System.out.println("getbigimage error");
		}
		return  temp_bfimage;
	}
	
	
	
	
	public void update_Srceen() {
		repaint();
	}

}
