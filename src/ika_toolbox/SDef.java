package ika_toolbox;
import java.io.File;

import javax.swing.*;

public class SDef {

	public	static File binfile;
	public static int imagenumber=0;
	public static	int fangfa=0;
	public static	byte offset=0;
	  public static int SYSTEM_DELAY = 100;//ˢ���ӳ�
	  public static byte[] binfileMatirx=null;//��ȡ���ݱ��洦
	
	

	// ����swing�����. ��ʱnemberΪ0~3,����
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

	
	public  static File createFile(String fileName) {
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
