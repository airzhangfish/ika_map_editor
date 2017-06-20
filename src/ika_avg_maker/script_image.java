package ika_avg_maker;
import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import javax.swing.event.MouseInputAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class script_image extends JPanel implements Runnable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public Thread thread;

	public script_image() {
		// this.setPreferredSize(new Dimension(820, 670));
		MyListener myListener = new MyListener();
		addMouseListener(myListener);
		addMouseMotionListener(myListener);
		thread = new Thread(this);
		thread.start();
	}

	static int pressRect = -1;
	int change_xxx = 0;
	int change_yyy = 0;

	class MyListener extends MouseInputAdapter {

		public void mousePressed(MouseEvent e) {
			if (SwingUtilities.isRightMouseButton(e)) {
				change_xxx = e.getX() - xxx;
				change_yyy = e.getY() - yyy;
			}
		}

		public void mouseDragged(MouseEvent e) {
			if (SwingUtilities.isRightMouseButton(e)) {
				xxx = e.getX() - change_xxx;
				yyy = e.getY() - change_yyy;
			}
		}
	}

	public static int[] recBG = new int[3];
	public static int[] recPerson1 = new int[3];
	public static int[] recPerson2 = new int[3];
	public static int[] recTalkBox = new int[3];
	public static String talkString="";
	public BufferedImage bg_img, person1_img, person2_img, recTalkBox_img;

	public void creatImage(int number, int kind) {

	}

	int xxx = 10;
	int yyy = 10;
	int buffer_RecBg = 0;
	int buffer_RecPerson1 = 0;
	int buffer_RecPerson2 = 0;
	int buffer_recTalkBox = 0;
	File pngFile;

	public void paint(Graphics g) {
		g.setColor(new Color(0, 0, 0));
		g.fillRect(0, 0, 820, 670);
		Graphics2D g2 = (Graphics2D) g;
		g2.setColor(new Color(255, 255, 255));
		g2.drawString("当前字符数:" + txt_panel.pane.getCaretPosition(), 10, 280);
		g2.drawString("当前行数:" + txt_panel.line_number, 10, 290);
		g2.drawString("内容:" + txt_panel.thisline, 10, 300);
		// 大图
		if (txt_panel.exStr.equals("对话:")) {
			// 画背景
			if (script_image.recBG[0] > 0) {
				if (bg_img == null || script_image.recBG[0] != buffer_RecBg) {
					buffer_RecBg = script_image.recBG[0];
					pngFile = null;
					pngFile = new File(System.getProperty("user.dir") + "/bin/bg/back_" + script_image.recBG[0] + ".png");
					System.out.println();
					try {
						bg_img = ImageIO.read(pngFile);
					} catch (IOException ex) {
						System.out.println("png save error");
					}
				}
				if (bg_img != null) {
					g2.drawImage(bg_img, script_image.recBG[1], script_image.recBG[2], null);
				}
			} else if (script_image.recBG[0] < 0) {
				g2.setColor(new Color(0, 0, 0));
				g2.fillRect(0, 0, 400, 400);
			}

			// 画Person1
			if (script_image.recPerson1[0] > 0) {
				if (person1_img == null || script_image.recPerson1[0] != buffer_RecPerson1) {
					buffer_RecPerson1 = script_image.recPerson1[0];
					pngFile = null;
					pngFile = new File(System.getProperty("user.dir") + "/bin/person/pp_" + script_image.recPerson1[0] + ".png");
					System.out.println();
					try {
						person1_img = ImageIO.read(pngFile);
					} catch (IOException ex) {
						System.out.println("png save error");
					}
				}
				if (person1_img != null) {
					g2.drawImage(person1_img, script_image.recPerson1[1], script_image.recPerson1[2], null);
				}
			}
			// 画Person2
			if (script_image.recPerson2[0] > 0) {
				if (person2_img == null || script_image.recPerson2[0] != buffer_RecPerson2) {
					buffer_RecPerson2 = script_image.recPerson2[0];
					pngFile = null;
					pngFile = new File(System.getProperty("user.dir") + "/bin/person/pp_" + script_image.recPerson2[0] + ".png");
					System.out.println();
					try {
						person2_img = ImageIO.read(pngFile);
					} catch (IOException ex) {
						System.out.println("png save error");
					}
				}
				if (person2_img != null) {
					g2.drawImage(person2_img, script_image.recPerson2[1], script_image.recPerson2[2], null);
				}
			}

			// 画框
			if (script_image.recTalkBox[0] > 0) {
				if (recTalkBox_img == null || script_image.recTalkBox[0] != buffer_recTalkBox) {
					buffer_recTalkBox = script_image.recTalkBox[0];
					pngFile = null;
					pngFile = new File(System.getProperty("user.dir") + "/bin/box/box_" + script_image.recTalkBox[0] + ".png");
					try {
						recTalkBox_img = ImageIO.read(pngFile);
					} catch (IOException ex) {
						System.out.println("png save error");
					}
				}
				if (recTalkBox_img != null) {
					g2.drawImage(recTalkBox_img, script_image.recTalkBox[1], script_image.recTalkBox[2], null);
				}
			}
	        g2.setColor(new Color(255,255,255));
			g2.drawString(""+talkString, 5+1, 180);
			g2.drawString(""+talkString, 5-1, 180);
			g2.drawString(""+talkString, 5, 180+1);
			g2.drawString(""+talkString, 5, 180-1);
	        g2.setColor(new Color(0,0,0));
			g2.drawString(""+talkString, 5, 180);
		}

		g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

	}

	public void update_Srceen() {
		repaint();
	}

	private boolean isrun = true;

	public void run() {
		while (isrun) {
			repaint();
			try {
				Thread.sleep(SDef.SYSTEM_DELAY);
			} catch (InterruptedException ex) {
			}
		}
	}

}
