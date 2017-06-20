package IkaMapEditor;
import javax.swing.*;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;
import java.awt.image.BufferedImage;

import javax.swing.event.MouseInputAdapter;

public class Map_Screen extends JPanel {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public Thread thread;

	public Map_Screen() {
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

	int Xline = 0;
	int Yline = 0;

	class MyListener extends MouseInputAdapter {

		public void mouseMoved(MouseEvent e) {
		}

		public void mousePressed(MouseEvent e) {

			if (SwingUtilities.isLeftMouseButton(e)) {
				int xs = e.getX() - xxx;
				int ys = e.getY() - yyy;

				SDef.maptileID = checkmapwhich(xs, ys);
				if (SDef.maptileID[0] != -1 && SDef.maptileID[1] != -1) {
					SDef.layer0[SDef.maptileID[0]][SDef.maptileID[1]] = (short) SDef.selcetID;
				}
				repaint();
			}

			if (SwingUtilities.isRightMouseButton(e)) {
				switch (SDef.Rightclick_State) {
				case SDef.Rightclick_move:
					change_xxx = e.getX() - xxx;
					change_yyy = e.getY() - yyy;
					break;
				case SDef.Rightclick_rag:
					if (SDef.maptileID[0] != -1 && SDef.maptileID[1] != -1) {
						// Ðý×ª
						short tmp = SDef.layer0[SDef.maptileID[0]][SDef.maptileID[1]];
						tmp = SDef.set_Trans((SDef.get_Trans(tmp) + 1) % 8, tmp);
						SDef.layer0[SDef.maptileID[0]][SDef.maptileID[1]] = tmp;
					}
					break;
				case SDef.Rightclick_del:
					int xs = e.getX() - xxx;
					int ys = e.getY() - yyy;

					SDef.maptileID = checkmapwhich(xs, ys);
					if (SDef.maptileID[0] != -1 && SDef.maptileID[1] != -1) {
						SDef.layer0[SDef.maptileID[0]][SDef.maptileID[1]] = -1;
					}
					break;
				case SDef.Rightclick_pass:
					int xss = e.getX() - xxx;
					int yss = e.getY() - yyy;
					SDef.maptileID = checkmapwhich(xss, yss);
					if (SDef.maptileID[0] != -1 && SDef.maptileID[1] != -1) {
						//Åö×²
						short tmp = SDef.layer0[SDef.maptileID[0]][SDef.maptileID[1]];
						tmp = SDef.set_Pass((SDef.get_Pass(tmp) + 1) % 2, tmp);
						System.out.println("pass:"+tmp);
						SDef.layer0[SDef.maptileID[0]][SDef.maptileID[1]] = tmp;
					}
					break;
				case SDef.Rightclick_rev:
					break;
				}
				repaint();
			}
		}

		public void mouseDragged(MouseEvent e) {
			if (SwingUtilities.isLeftMouseButton(e)) {
				int xs = e.getX() - xxx;
				int ys = e.getY() - yyy;
				SDef.maptileID = checkmapwhich(xs, ys);
				if (SDef.maptileID[0] != -1 && SDef.maptileID[1] != -1) {
					SDef.layer0[SDef.maptileID[0]][SDef.maptileID[1]] = (short) SDef.selcetID;
				}
				repaint();
			}

			if (SwingUtilities.isRightMouseButton(e)) {
				switch (SDef.Rightclick_State) {
				case SDef.Rightclick_move:
					xxx = e.getX() - change_xxx;
					yyy = e.getY() - change_yyy;
					break;
				case SDef.Rightclick_rag:
					break;
				case SDef.Rightclick_del:
					int xs = e.getX() - xxx;
					int ys = e.getY() - yyy;

					SDef.maptileID = checkmapwhich(xs, ys);
					if (SDef.maptileID[0] != -1 && SDef.maptileID[1] != -1) {
						SDef.layer0[SDef.maptileID[0]][SDef.maptileID[1]] = -1;
					}
					break;
				case SDef.Rightclick_pass:
					break;
				case SDef.Rightclick_rev:
					break;
				}
				repaint();
			}
		}

		public void mouseReleased(MouseEvent e) {
		}
	}

	public int[] checkmapwhich(int point_x, int point_y) {
		// layer0
		int[] xs = new int[2];
		for (int i = 0; i < SDef.map_SW; i++) {
			for (int j = 0; j < SDef.map_SH; j++) {
				int x = i * SDef.tile_SW;
				int y = j * SDef.tile_SH;
				int w = SDef.tile_SW;
				int h = SDef.tile_SH;
				if (point_x > x && point_x <= x + w && point_y > y && point_y <= y + h) {
					xs[0] = i;
					xs[1] = j;
					return xs;
				}
			}
		}
		xs[0] = -1;
		xs[1] = -1;
		return xs;
	}

	int xxx = 16;
	int yyy = 16;
	int change_xxx = 0;
	int change_yyy = 0;

	// Îï¿éÇÐ»»Ê±ºò×Ô¶¯ÇÐ»»Í¼Æ¬
	int oldnumber = -1;
	int oldnumber2 = -1;

	public void paint(Graphics g) {
		g.setColor(SDef.Color_HUI);
		g.setClip(0, 0, getWidth(), getHeight());
		g.clipRect(0, 0, getWidth(), getHeight());
		g.fillRect(0, 0, getWidth(), getHeight());
		Graphics2D g2 = (Graphics2D) g;
		// Í¼Ïñ¿ªÊ¼
		if (SDef.map_SW > 0 && SDef.map_SH > 0 && SDef.tile_SW > 0 && SDef.tile_SH > 0) {
			g2.setColor(SDef.Color_HEI);
			int swlength = SDef.map_SW * SDef.tile_SW;
			int shlength = SDef.map_SH * SDef.tile_SH;
			int bufferint = 0;
			int bufferintx = 0;
			int bufferinty = 0;
			g2.fillRect(xxx, yyy, swlength, shlength);
			if (SDef.mod_bfimage != null) {
				for (int i = 0; i < SDef.map_SW; i++) {
					for (int j = 0; j < SDef.map_SH; j++) {
						bufferintx = SDef.tile_SW * i;
						bufferinty = SDef.tile_SH * j;
						if (SDef.layer0[i][j] == -1) {
							g2.fillRect(xxx + bufferintx, yyy + bufferinty, SDef.tile_SW, SDef.tile_SH);
						} else {
							// g2.drawImage(SDef.mod_bfimage[SDef.layer0[i][j]], xxx + bufferintx, yyy + bufferinty, SDef.tile_SW,SDef.tile_SH, null);
							drawAniPart20(SDef.get_ID(SDef.layer0[i][j]), xxx + bufferintx, yyy + bufferinty, SDef.get_Trans(SDef.layer0[i][j]), g2);
						}
					}
				}
			}
			// Íø¸ñ
			if (Map_panel.line_RB.isSelected()) {
				g.setColor(SDef.Color_HUI);
				for (int i = 0; i <= SDef.map_SW; i++) {
					bufferint = xxx + SDef.tile_SW * i;
					g2.drawLine(bufferint, yyy, bufferint, yyy + shlength);
				}
				for (int i = 0; i <= SDef.map_SH; i++) {
					bufferint = yyy + SDef.tile_SH * i;
					g2.drawLine(xxx, bufferint, xxx + swlength, bufferint);
				}
			}

			// Åö×²
			if (Map_panel.col_RB.isSelected()) {
				for (int i = 0; i < SDef.map_SW; i++) {
					for (int j = 0; j < SDef.map_SH; j++) {
						bufferintx = SDef.tile_SW * i;
						bufferinty = SDef.tile_SH * j;
						g2.setColor(SDef.Color_HUI);
						if (SDef.get_Pass(SDef.layer0[i][j]) == 0) {
							//¿ÉÍ¨¹ý

						} else if (SDef.get_Pass(SDef.layer0[i][j]) == 1) {
							//²»¿ÉÍ¨¹ý
							g2.drawRoundRect(xxx + bufferintx + SDef.tile_SW / 4, yyy + bufferinty + SDef.tile_SH / 4, SDef.tile_SW / 2,
									SDef.tile_SH / 2, SDef.tile_SW / 3, SDef.tile_SH / 3);
						}
					}
				}
			}

			for (int i = 0; i < SDef.map_SW; i++) {
				for (int j = 0; j < SDef.map_SH; j++) {
					if (SDef.maptileID[0] == i && SDef.maptileID[1] == j) {
						bufferintx = SDef.tile_SW * i;
						bufferinty = SDef.tile_SH * j;
						g2.setColor(SDef.Color_BAI);
						g2.drawRect(xxx + bufferintx, yyy + bufferinty, SDef.tile_SW, SDef.tile_SH);
					}
				}
			}
		}
		g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
	}

	// »æ»­³ÉÏñ
	public static void drawAniPart20(int n, int x, int y, int trans, Graphics g) {
		if (trans == 0) {
			rotate90(SDef.mod_bfimage[n], 0, n);
			g.drawImage(filteredImage, x, y, null);
		} else {
			switch (trans) {
			case 1: // X¾µÏñ
				rotate90(SDef.mod_bfimage[n], 0, n);
				// g.drawImage(filteredImage, x - filteredImage.getWidth(), y, x, y + filteredImage.getHeight(), filteredImage.getWidth(), 0,
				// 0,filteredImage.getHeight(), null); // X¾µÏñ
				g.drawImage(filteredImage, x, y, x + filteredImage.getWidth(), y + filteredImage.getHeight(), filteredImage.getWidth(), 0, 0,
						filteredImage.getHeight(), null); // X¾µÏñ

				break;
			case 2: // Y¾µÏñ
				rotate90(SDef.mod_bfimage[n], 0, n);
				g.drawImage(filteredImage, x, y, x + filteredImage.getWidth(), y + filteredImage.getHeight(), 0, filteredImage.getHeight(),
						filteredImage.getWidth(), 0, null); // Y¾µÏñ
				break;
			case 3: // X¾µÏñ+Y¾µÏñ »òÕßÐý×ª180¶È
				rotate90(SDef.mod_bfimage[n], 180, n);
				g.drawImage(filteredImage, x, y, null);
				break;
			case 4: // Ðý×ª270+Y¾µÏñ
				rotate90(SDef.mod_bfimage[n], 270, n);
				g.drawImage(filteredImage, x, y, x + filteredImage.getWidth(), y + filteredImage.getHeight(), filteredImage.getWidth(), 0, 0,
						filteredImage.getHeight(), null); // Y¾µÏñ
				break;
			case 5: // Ðý×ª90
				rotate90(SDef.mod_bfimage[n], 90, n);
				g.drawImage(filteredImage, x, y, null);
				break;
			case 6: // Ðý×ª270
				rotate90(SDef.mod_bfimage[n], 270, n);
				g.drawImage(filteredImage, x, y, null);
				break;
			case 7: // Ðý×ª90+Y¾µÏñ
				rotate90(SDef.mod_bfimage[n], 90, n);
				g.drawImage(filteredImage, x, y, x + filteredImage.getWidth(), y + filteredImage.getHeight(), filteredImage.getWidth(), 0, 0,
						filteredImage.getHeight(), null); // Y¾µÏñ
				break;
			default:
				System.out.println("2.0trans error");
				break;
			}
		}
	}

	// Ðý×ª
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
}
