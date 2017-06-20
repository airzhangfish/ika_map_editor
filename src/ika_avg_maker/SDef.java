package ika_avg_maker;
import javax.swing.*;
import java.io.File;
import javax.swing.JFrame;

public class SDef {

	public static int SYSTEM_DELAY = 2000;
	public static File binfile = null;
	public static int imagenumber = 0;
	public static int MO_X = 0;
	public static int MO_Y = 0;
	public static int MO_W = 0;
	public static int MO_H = 0;

	// 设置swing的外观. 暂时nember为0~2,三种
	public static void setMySkin(int number) {
		JFrame.setDefaultLookAndFeelDecorated(true);
		JDialog.setDefaultLookAndFeelDecorated(true);
		try {
			if (number == 0) {
				UIManager.setLookAndFeel("com.sun.java.swing.plaf.motif.MotifLookAndFeel");
			} else if (number == 1) {
				// UIManager.setLookAndFeel("javax.swing.plaf.metal.MetalLookAndFeel");
				UIManager.setLookAndFeel("com.birosoft.liquid.LiquidLookAndFeel");
			} else {
				UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
			}
		} catch (UnsupportedLookAndFeelException ex) {
		} catch (IllegalAccessException ex) {
		} catch (InstantiationException ex) {
		} catch (ClassNotFoundException ex) {
		}
	}

}
