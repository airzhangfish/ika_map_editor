package IkaMapEditor;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.image.BufferedImage;

import javax.swing.JFrame;
import javax.swing.event.MouseInputAdapter;

public class Map_review extends JFrame implements Runnable {

	private static final long serialVersionUID = 1L;
	Thread th;
	BufferedImage bfimg ;
	public Map_review(int x, int y) {

		this.setTitle("预览画面设置");
		switch (SDef.srceensize) {
		case 0:
			this.setSize(101, 80);
			break;
		case 1:
			this.setSize(176, 204);
			break;
		case 2:
			this.setSize(176, 208);
			break;
		case 3:
			this.setSize(176, 220);
			break;
		case 4:
			this.setSize(128, 116);
			break;
		case 5:
			this.setSize(128, 128);
			break;
		case 6:
			this.setSize(128, 160);
			break;
		case 7:
			this.setSize(208, 208);
			break;
		case 8:
			this.setSize(208, 320);
			break;
		case 9:
			this.setSize(240, 320);
			break;
		}
		this.setAlwaysOnTop(true);
		this.setLocation(x, y);
		this.setResizable(false); // 窗体不能改变大小
		runing = true;

		MyListener myListener = new MyListener();
		addMouseListener(myListener);
		addMouseMotionListener(myListener);
		MAmouseX = getX() + 10;
		MAmouseY = getY() + 10;
		 bfimg = new BufferedImage(this.getWidth(), this.getHeight(), BufferedImage.TYPE_INT_ARGB);
		setVisible(true);
		th = new Thread(this);
		addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				runing = false;
				th = null;
				this.windowClosed(e);
			}
		});
		th.start();
	}

	int MAmouseX = 0;
	int MAmouseY = 0;

	class MyListener extends MouseInputAdapter {
		public void mouseMoved(MouseEvent e) {
			MAmouseX = e.getX();
			MAmouseY = e.getY();
		}
	}

	int map_moveX = 0;
	int map_moveY= 0;
	public void paint(Graphics g) {
		Graphics gg = bfimg.getGraphics();
		Graphics2D g2 = (Graphics2D) gg;	
		g2.setColor(SDef.Color_HEI);
		g2.setClip(0, 0, getWidth(), getHeight());
		g2.clipRect(0, 0, getWidth(), getHeight());
		g2.fillRect(0, 0, getWidth(), getHeight());

		// 图像开始
		if (SDef.map_SW > 0 && SDef.map_SH > 0 && SDef.tile_SW > 0 && SDef.tile_SH > 0) {
			g2.setColor(SDef.Color_HEI);
			int swlength = SDef.map_SW * SDef.tile_SW;
			int shlength = SDef.map_SH * SDef.tile_SH;
			int bufferintx = 0;
			int bufferinty = 0;
			switch(SCR_state){
			case SCR_stop:
				break;
			case SCR_up:	
				map_moveY=map_moveY-3;
				break;
			case SCR_down:
				map_moveY=map_moveY+3;
				break;
			case SCR_left:
				map_moveX=map_moveX-3;
				break;
			case SCR_right:
				map_moveX=map_moveX+3;
				break;
			}	
			g2.fillRect(map_moveX, map_moveY, swlength, shlength);
			if (SDef.mod_bfimage != null) {
				for (int i = 0; i < SDef.map_SW; i++) {
					for (int j = 0; j < SDef.map_SH; j++) {
						bufferintx = SDef.tile_SW * i;
						bufferinty = SDef.tile_SH * j;
						if (SDef.layer0[i][j] == -1) {
							g2.fillRect(map_moveX + bufferintx, map_moveY + bufferinty, SDef.tile_SW, SDef.tile_SH);
						} else {
							// g2.drawImage(SDef.mod_bfimage[SDef.layer0[i][j]], xxx + bufferintx, yyy + bufferinty, SDef.tile_SW,SDef.tile_SH, null);
							Map_Screen.drawAniPart20(SDef.get_ID(SDef.layer0[i][j]), map_moveX + bufferintx, map_moveY + bufferinty, SDef
									.get_Trans(SDef.layer0[i][j]), g2);
						}
					}
				}
			}
		}
		g2.setColor(SDef.Color_BAI);
		g2.drawString(map_moveX+","+map_moveY+":", 30, 30);
		g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		g.drawImage(bfimg, 0, 0, null);
	}

	public boolean runing = true;
	public int box_X = 0;
	public int box_Y = 0;
	public int box_W = 0;
	public int box_H = 0;
	public void mousemovemap() {
		box_X =this.getWidth()/4;
		box_Y = this.getHeight()/4;
		box_W = this.getWidth()/2;
		box_H = this.getHeight()/2;
		int mouseX=MAmouseX;
		int mouseY=MAmouseY;
//		System.out.println("mouse:"+mouseX+","+mouseY);
//		System.out.println("getbox:"+this.getX()+","+this.getY()+","+this.getWidth()+","+this.getHeight());
//if(mouseX>box_X && mouseX<box_X+box_W &&mouseY>box_Y&&mouseY<box_Y+box_H){
//	//停止
//	SCR_state=SCR_stop;
//}else 
		if(mouseX>box_X && mouseX<box_X+box_W &&mouseY<box_Y){
	//向上	
	SCR_state=SCR_up;
}else if(mouseX>box_X && mouseX<box_X+box_W &&mouseY>box_Y+box_H){
	//向下
	SCR_state=SCR_down;
}else if( mouseX<box_X&&mouseY>box_Y&&mouseY<box_Y+box_H){
	//左边
	SCR_state=SCR_left;
}else if( mouseX>box_X+box_W&&mouseY>box_Y&&mouseY<box_Y+box_H){
	//右边
	SCR_state=SCR_right;
}else{
	SCR_state=SCR_stop;
}	
	}

	public byte SCR_state = 0;
	public final byte SCR_stop = 0;
	public final byte SCR_up = 1;
	public final byte SCR_down = 2;
	public final byte SCR_left = 3;
	public final byte SCR_right = 4;

	public void run() {
		while (runing) {
			mousemovemap();
			repaint();
			
			try {
				Thread.sleep(SDef.SYSTEM_DELAY);
			} catch (Exception ex) {
			}
		}
	}
}
