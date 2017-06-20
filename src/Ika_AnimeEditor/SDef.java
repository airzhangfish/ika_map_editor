package Ika_AnimeEditor;
import javax.swing.*;
import java.io.File;
import java.awt.image.BufferedImage;
import javax.swing.JFrame;

/**
 * <p>
 * Title:ika 动画编辑器
 * </p>
 * <p>
 * Description: 编辑图片和帧，形成动画，导出后给手机调用
 * </p>
 * <p>
 * Copyright: airzhangfish Copyright (c) 2007
 * </p>
 * <p>
 * blog: http://airzhangfish.spaces.live.com
 * </p>
 * <p>
 * Company: Comicfishing
 * </p>
 * <p>
 * author airzhangfish
 * </p>
 * <p>
 * version 0.03a standard
 * </p>
 * <p>
 * last updata 2007-8-23
 * </p>
 * 定义界面
 */

public class SDef {

	public static int SYSTEM_DELAY = 80;
	public static File binfile = null;
	public static int imagenumber = 0;
	public static int fangfa = 0;
	public static byte offset = 0;
	public static BufferedImage[] mod_big_bfimage = new BufferedImage[1000];
	public static BufferedImage[] static_mod_big_bfimage = new BufferedImage[1000];
	public static String[] big_bfimage_path = new String[1000];
	public static int BG_R = 144;
	public static int BG_G = 144;
	public static int BG_B = 144;

	public static int SG_R = 160;
	public static int SG_G = 96;
	public static int SG_B = 160;

	public static int LN_R = 0;
	public static int LN_G = 0;
	public static int LN_B = 208;

	public static int MO_X = -120;
	public static int MO_Y = -320;
	public static int MO_W = 240;
	public static int MO_H = 320;

	public static BufferedImage[] mod_bfimage2 = new BufferedImage[1000];
	public static BufferedImage[] mod_bfimage = new BufferedImage[1000];
	public static BufferedImage[] frame_bfimage = new BufferedImage[1000];
	public static int mod_size = 1; // 放大倍数
	public static int frame_size = 1; // 放大倍数

	// mod panel
	static DefaultListModel mod_listModel = new DefaultListModel();
	static DefaultListModel image_listModel = new DefaultListModel();
	public static int[][] G_modbox = new int[1000][6];
	public final static int mod_X = 0;
	public final static int mod_Y = 1;
	public final static int mod_W = 2;
	public final static int mod_H = 3;
	public final static int mod_USE = 4;
	public final static int mod_Image = 5;
	public static String[] mod_words = { "空" };
	public static String[] mod_Image_name = { "空" };

	// frame panel
	static DefaultListModel frame_listModel = new DefaultListModel();
	public static String[] frame_words = { "空" };

	static DefaultListModel use_modlist = new DefaultListModel();
	public static String[] use_mod_words = { "空" };

	public static int[][] G_framebox = new int[1000][2];
	public static String[] G_framebox_str = new String[1000];
	public final static int frame_USE = 0;
	public final static int frame_number = 1;

	// anime panel
	public static int[][] G_animebox = new int[1000][2];
	public static String[] G_animebox_str = new String[1000];
	public final static int anime_USE = 0;
	public final static int anime_number = 1;
	public static String[] anime_words = { "空" };
	public static String[] use_framelist = { "没有正在使用的帧" };
	public static DefaultListModel use_framelistModel = new DefaultListModel();
	static DefaultListModel anime_listModel = new DefaultListModel();

	public static void reset_new() {
		binfile = null;
		imagenumber = 0;
	 mod_size = 1; // 放大倍数
 frame_size = 1; // 放大倍数
		mod_big_bfimage = new BufferedImage[1000];
		static_mod_big_bfimage = new BufferedImage[1000];
		big_bfimage_path = new String[1000];
		mod_bfimage2 = new BufferedImage[1000];
		mod_bfimage = new BufferedImage[1000];
		frame_bfimage = new BufferedImage[1000];
		mod_listModel = new DefaultListModel();
		image_listModel = new DefaultListModel();
		G_modbox = new int[1000][6];
		G_framebox = new int[1000][2];
		G_framebox_str = new String[1000];
		G_animebox = new int[1000][2];
		G_animebox_str = new String[1000];
	}

	public static int frame_total_x = 300;
	public static int frame_total_y = 320;

	// 设置swing的外观. 暂时nember为0~2,三种
	public static void setMySkin(int number) {
		for (int i = 0; i < G_framebox_str.length; i++) {
			G_framebox_str[i] = "";
			G_animebox_str[i] = "";
		}

		JFrame.setDefaultLookAndFeelDecorated(true);
		JDialog.setDefaultLookAndFeelDecorated(true);
		try {
			if (number == 0) {
				UIManager.setLookAndFeel("com.sun.java.swing.plaf.motif.MotifLookAndFeel");
			} else if (number == 1) {
				// UIManager.setLookAndFeel("javax.swing.plaf.metal.MetalLookAndFeel");
				UIManager.setLookAndFeel("com.oyoaha.swing.plaf.oyoaha.OyoahaLookAndFeel");
			} else if (number == 2) {
				UIManager.setLookAndFeel("javax.swing.plaf.metal.MetalLookAndFeel");
			} else {
				UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
			}
		} catch (UnsupportedLookAndFeelException ex) {
		} catch (IllegalAccessException ex) {
		} catch (InstantiationException ex) {
		} catch (ClassNotFoundException ex) {
		}
	}
	
	
	
    public static byte[] intToByte(int i) {
        byte[] abyte0 = new byte[4];
        abyte0[0] = (byte) (0xff & i);
        abyte0[1] = (byte) ((0xff00 & i) >> 8);
        abyte0[2] = (byte) ((0xff0000 & i) >> 16);
        abyte0[3] = (byte) ((0xff000000 & i) >> 24);
        return abyte0;
    }

   public  static int bytesToInt(byte[] bytes) {
        int addr = bytes[0] & 0xFF;
        addr |= ((bytes[1] << 8) & 0xFF00);
        addr |= ((bytes[2] << 16) & 0xFF0000);
        addr |= ((bytes[3] << 24) & 0xFF000000);
        return addr;
    }

   public static byte[] shortToByte(short i) {
       byte[] abyte0 = new byte[2];
       abyte0[0] = (byte) (0xff & i);
       abyte0[1] = (byte) ((0xff00 & i) >> 8);
       return abyte0;
   }


   
   
}
