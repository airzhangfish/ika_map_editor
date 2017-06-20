package IkaMapEditor;
import javax.swing.*;
import java.awt.*;
import javax.swing.event.MouseInputAdapter;
import java.awt.event.MouseEvent;

public class Map_mod extends JPanel {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public Map_mod() {
		MyListener myListener = new MyListener();
		addMouseListener(myListener);
		addMouseMotionListener(myListener);
	}

	static int pressRect = -1;
	int change_xxx = 0;
	int change_yyy = 0;

	class MyListener extends MouseInputAdapter {

		public void mousePressed(MouseEvent e) {
			if (SwingUtilities.isRightMouseButton(e)) {
				change_xxx = e.getX() - xxx;
				change_yyy = e.getY() - yyy;
				repaint();
			}

			if (SwingUtilities.isLeftMouseButton(e)) {
				int xs = e.getX() - xxx;
				int ys = e.getY() - yyy;
				System.out.println(xs + "," + ys);
				SDef.selcetID = checkwhich(xs, ys);
				System.out.println("selcetID:"+SDef.selcetID);
				repaint();
			}
		}

		public void mouseDragged(MouseEvent e) {
			if (SwingUtilities.isRightMouseButton(e)) {
				xxx = e.getX() - change_xxx;
				yyy = e.getY() - change_yyy;
				repaint();
			}
		}
	}

	int xxx = 10;
	int yyy = 10;

	public int checkwhich(int point_x, int point_y) {
		int witch_piece = map_mod_length / (SDef.mod_bfimage[0].getWidth() + 3);
		for (int i = 0; i < SDef.mod_bfimage.length; i++) {
			int x = (i % witch_piece) * (SDef.mod_bfimage[i].getWidth() + 2);
			int y = (i / witch_piece) * (SDef.mod_bfimage[i].getHeight() + 2);
			int w = SDef.mod_bfimage[i].getWidth();
			int h = SDef.mod_bfimage[i].getHeight();
			if (point_x > x && point_x < x + w && point_y > y && point_y < y + h) {
				return i;
			}
		}
		return -1;
	}

	int map_mod_length = 0;

	public void paint(Graphics g) {
		g.setColor(new Color(0, 0, 0));
		g.setClip(0, 0, getWidth(), getHeight());
		g.clipRect(0, 0, getWidth(), getHeight());
		g.fillRect(0, 0, getWidth(), getHeight());
		Graphics2D g2 = (Graphics2D) g;
		map_mod_length = this.getWidth();
		// ´óÍ¼
		if (SDef.PIC_bfimage != null && SDef.map_SW > 0 && SDef.map_SH > 0 && SDef.tile_SW > 0 && SDef.tile_SH > 0&&SDef.totalpiece>0 ) {
			g.setColor(new Color(100, 100, 100));
			int witch_piece = map_mod_length / (SDef.mod_bfimage[0].getWidth() + 3);
			if(witch_piece<1){
				witch_piece=1;
			}
			for (int i = 0; i < SDef.mod_bfimage.length; i++) {
				g2.drawImage(SDef.mod_bfimage[i], xxx + (i % witch_piece) * (SDef.mod_bfimage[i].getWidth() + 2), yyy + (i / witch_piece)
						* (SDef.mod_bfimage[i].getHeight() + 2), SDef.mod_bfimage[i].getWidth(), SDef.mod_bfimage[i].getHeight(), null);
				if (i == SDef.selcetID) {
					g.setColor(new Color(255, 255, 255));
					g2.drawRect(xxx + (i % witch_piece) * (SDef.mod_bfimage[i].getWidth() + 2)-1, yyy + (i / witch_piece)
							* (SDef.mod_bfimage[i].getHeight() + 2)-1, SDef.mod_bfimage[i].getWidth()+1, SDef.mod_bfimage[i].getHeight()+1);
				}
			}
		}

	}

	public void update_Srceen() {
		repaint();
	}
}
