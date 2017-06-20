package IkaMapEditor;
import java.awt.Color;
import java.awt.Dimension;

import java.awt.image.BufferedImage;
import java.io.File;
import javax.swing.*;

public class SDef {

	public static short[][] layer0;
//获取数字
	public static int get_ID(short number) {
		return number - ((number >> 8) << 8);
	}

	public static int get_Trans(short number) {
		return (number >> 8) - ((number >> 12) << 4);
	}

	public static int get_Pass(short number) {
		return (number >> 12) % 2;
	}
//设置数字
	public static short set_ID(int  ID,short number) {
		return (short)(ID+ ((number >> 8) << 8));
	}

	public static short set_Trans(int Trans,short number) {
		int tmp= ((number >> 12)<<12)+get_ID(number)+(Trans<<8);		
		return (short)(tmp);
	}

	public static short set_Pass(int Pass,short number) {
		int tmp=number-(((number >> 12) << 12))+(Pass<<12);
		return (short)tmp;
	}

	public static int[] maptileID = new int[2];
	final static Dimension mainSize = new Dimension(640, 480);
	final static Dimension modSize = new Dimension(200, 480);
	public static int SYSTEM_DELAY = 60;
	public static BufferedImage PIC_bfimage;
	public static BufferedImage[] mod_bfimage;
	public static BufferedImage[] mod_big_bfimage;
	public static int selcetID = -1;
	public static int totalpiece = 0;
	public static int map_SW = 0;
	public static int map_SH = 0;
	public static int tile_SW = 0;
	public static int tile_SH = 0;
	public static Color Color_HUI = new Color(100, 100, 100);
	public static Color Color_HEI = new Color(0, 0, 0);
	public static Color Color_BAI = new Color(255, 255, 255);
//	屏幕大小设置
	public  static byte srceensize=2; 

	public static byte Rightclick_State = 0;
	public static final byte Rightclick_move = 0;
	public static final byte Rightclick_rag = 1;
	public static final byte Rightclick_del = 2;
	public static final byte Rightclick_pass = 3;
	public static final byte Rightclick_rev = 4;

	// 设置swing的外观. 暂时nember为0~3,三种
	public static void setMySkin(int number) {
		JFrame.setDefaultLookAndFeelDecorated(true);
		JDialog.setDefaultLookAndFeelDecorated(true);
		try {
			if (number == 0) {
				UIManager.setLookAndFeel("com.sun.java.swing.plaf.motif.MotifLookAndFeel");
			} else if (number == 1) {
				UIManager.setLookAndFeel("com.birosoft.liquid.LiquidLookAndFeel");
			} else if (number == 2) {
				UIManager.setLookAndFeel("javax.swing.plaf.metal.MetalLookAndFeel");
			} else {
				UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
			}
		} catch (Exception e) {
			System.out.println("setMySkin error:" + number);
			e.printStackTrace();
		}
	}

	public static File createFile(String fileName) {
		File file = new File(fileName);
		if (file == null) {
			return null;
		}
		if (file.isDirectory()) {
			return null;
		}
		if (file.exists()) {
			file.delete();
		}
		try {
			file.createNewFile();
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return file;
	}

}
