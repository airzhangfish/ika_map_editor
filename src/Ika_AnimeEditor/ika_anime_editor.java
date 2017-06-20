package Ika_AnimeEditor;
import java.awt.Color;
import java.awt.Container;
import java.awt.Graphics;

import javax.swing.*;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.FileDialog;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileInputStream;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;

/**
 * <p>
 * Title:ika �����༭��
 * </p>
 * <p>
 * Description: �༭ͼƬ��֡���γɶ�������������ֻ�����
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
 * last updata 2007-12-24
 * </p>
 * ����
 */

public class ika_anime_editor extends JFrame implements ActionListener {
	/**
	 * 
	 */
	

	private static final long serialVersionUID = 1L;
	private JTabbedPane jtp;
	private JMenuBar jMenuBar1 = new JMenuBar();
	private JMenu jMenuFile = new JMenu("�ļ�(F)");

	// private JMenuItem jMenuFileOpenika = new JMenuItem("��ȡika�ļ�...");
	// private JMenuItem jMenuFileSaveika = new JMenuItem("����Ϊika�ļ�...");
	private JMenuItem jMenuFileSaveika = new JMenuItem("����ika�ļ�..                  (Ctrl+E)");
	private JMenuItem jMenuFileSaveImage = new JMenuItem("����ͼƬ..                    (Ctrl+P)");
	private JMenuItem jMenuFileSavepack = new JMenuItem("���ͼƬ..                    ");
	private JMenuItem jMenuFileOpenxml = new JMenuItem("��ȡxml�ļ�..              (Ctrl+O)");
	private JMenuItem jMenuFileSavexml = new JMenuItem("����Ϊxml�ļ�..             (Ctrl+S)");
	private JMenuItem jMenuFileSaveGIF = new JMenuItem("ָ����������ΪGIF    (Ctrl+G)");
	private JMenuItem jMenuFileExit = new JMenuItem("�˳�           (Ctrl+X)");

	private JMenu jMenuOpinion = new JMenu("����(S)");
	private JMenuItem jMenuOpinionBGColor = new JMenuItem("���ñ���ɫ");
	private JMenuItem jMenuOpinionBoxColor = new JMenuItem("����դ����ɫ");
	private JMenuItem jMenuOpinionlineColor = new JMenuItem("��������ɫ");
	private JMenuItem jMenuOpinionScreen = new JMenuItem("�����ֻ���Ļ��С      (Ctrl+C)");
	private JMenuItem jMenuOpinionFrame = new JMenuItem("����֡�ӳ�                   (Ctrl+D)");

	private JMenu jMenuHelp = new JMenu("����(H)");
	private JMenuItem jMenuHelpAbout = new JMenuItem("����");
	private JMenuItem jMenuHelpHelp = new JMenuItem("����       (F6)");

	mod_panel mod_Panelx = new mod_panel();
	anime_panel anime_panelx = new anime_panel();
	frame_panel frame_panelx = new frame_panel();
	JPanel frame_Panel = new JPanel();
	JPanel anime_Panel = new JPanel();
	myThread mthread;

	public void actionPerformed(ActionEvent actionEvent) {
		Object source = actionEvent.getSource();
		// ����xml����
		if (source == jMenuFileSavexml) {
			FileDialog xs = new FileDialog(this, "save xml file", FileDialog.SAVE);
			xs.setFile("*.*");
			xs.setVisible(true);
			String f = xs.getFile();
			String lastDir = xs.getDirectory();
			if (f != null) {
				savexml(lastDir + f);
			}
		}
		// ��ȡxml����
		if (source == jMenuFileOpenxml) {
			FileDialog xs = new FileDialog(this, "load xml file", FileDialog.LOAD);
			xs.setFile("*.xml*");
			xs.setVisible(true);
			String f = xs.getFile();
			String lastDir = xs.getDirectory();
			if (f != null) {
				// SDef.reset_new();
				new XMLread(lastDir + f);
			}
		}

		// ����ika�ļ�
		if (source == jMenuFileSaveika) {
			FileDialog xs = new FileDialog(this, "save ika file", FileDialog.SAVE);
			xs.setFile("*.*");
			xs.setVisible(true);
			String f = xs.getFile();
			String lastDir = xs.getDirectory();
			if (f != null) {
				savedata(lastDir + f);
			}
		}
		// ����ͼƬ�ļ�
		if (source == jMenuFileSaveImage) {
			FileDialog xs = new FileDialog(this, "save Image file", FileDialog.SAVE);
			xs.setFile("*.*");
			xs.setVisible(true);
			String f = xs.getFile();
			String lastDir = xs.getDirectory();
			if (f != null) {
				saveImage(lastDir);
			}
		}

		// ����ͼƬ����ļ�
		if (source == jMenuFileSavepack) {
			
			if (canpack == true) {
				getPNG(pngfiles);
				output_Res(pngfiles);
			} else {
				JOptionPane.showMessageDialog(this, "���ȵ���PNGСͼƬ", "���������ʾ", JOptionPane.ERROR_MESSAGE);
			}
		}
		
		
		
		
		// ����ͼƬ�ļ�
		if (source == jMenuFileSaveGIF) {
			FileDialog xs = new FileDialog(this, "save GIF file", FileDialog.SAVE);
			xs.setFile("*.*");
			xs.setVisible(true);
			String f = xs.getFile();
			String lastDir = xs.getDirectory();
			if (f != null) {
				saveGIF(lastDir + f);
			}
		}

		// ��ȡika�ļ�
		// if (source == jMenuFileOpenika) {
		// FileDialog xs = new FileDialog(this, "load ika file", FileDialog.LOAD);
		// xs.setFile("*.ika*");
		// xs.show();
		// String f = xs.getFile();
		// String lastDir = xs.getDirectory();
		// if (f != null) {
		// opendata(lastDir + f);
		// }
		// }

		// ����
		if (source == jMenuHelpHelp) {
			JOptionPane.showMessageDialog(this, helpStr, "����", JOptionPane.INFORMATION_MESSAGE);
		}
		// ����
		if (source == jMenuHelpAbout) {
			JOptionPane.showMessageDialog(this, aboutStr, "����", JOptionPane.INFORMATION_MESSAGE);
		}
		// ����˳�
		if (source == jMenuFileExit) {
			System.exit(0);
		}

		// ������ɫ����
		if (source == jMenuOpinionBGColor) { // ����ɫ�趨
			ColorSelect1 xxx = new ColorSelect1();
			xxx.setVisible(true);
		}
		if (source == jMenuOpinionBoxColor) { // դ��ɫ�趨
			ColorSelect2 xxx = new ColorSelect2();
			xxx.setVisible(true);
		}
		if (source == jMenuOpinionlineColor) { // ����ɫ�趨
			ColorSelect3 xxx = new ColorSelect3();
			xxx.setVisible(true);
		}
		if (source == jMenuOpinionScreen) { // ��Ļ��С�趨
			ScreenSelect xxx = new ScreenSelect();
			xxx.setVisible(true);
		}
		if (source == jMenuOpinionFrame) { // �ӳ��趨
			DelaySelect xxx = new DelaySelect();
			xxx.setVisible(true);
		}

	}
	
	//TODO ����ͼƬ����
	//֧������ܴ�СΪ2G,֧�����32767��ͼƬ
	
	
	public void getPNG(File[] file) {
		total_PNG_length = 0;
		PNGlong = 0;
		for (int i = 0; i <total_number; i++) {
			total_PNG_length = total_PNG_length + file[i].length();
		}
		PNGlong = (int) total_PNG_length + 4 * (total_number) + 2;
		PNGbox = new byte[PNGlong];
		int PNGBOXID = 0;
		PNGbox[PNGBOXID] = (byte) ((total_number) >> 8);
		PNGBOXID++;
		PNGbox[PNGBOXID] = (byte) ((total_number) - (PNGbox[PNGBOXID] << 8));
		PNGBOXID++;
		for (int i = 0; i < total_number; i++) {
			byte[] bys=SDef.intToByte((int)file[i].length());
			PNGbox[PNGBOXID] =bys[0];
			PNGBOXID++;
			PNGbox[PNGBOXID] =bys[1];
			PNGBOXID++;
			PNGbox[PNGBOXID] =bys[2];
			PNGBOXID++;
			PNGbox[PNGBOXID] =bys[3];
			PNGBOXID++;

			try {
				FileInputStream fo = new FileInputStream(file[i]);
				byte[] mx = new byte[fo.available()];
				fo.read(mx);
				for (int j = 0; j < mx.length; j++) {
					PNGbox[PNGBOXID] = mx[j];
					PNGBOXID++;
				}
			} catch (Exception ex) {
				JOptionPane.showMessageDialog(this, "���ʧ��", "����", JOptionPane.ERROR_MESSAGE);
				System.out.println("read " + file[i] + " error");
				ex.printStackTrace();
			}
		}
	}

	
	
	static byte[] PNGbox;
	static int PNGlong = 0;
	static long total_PNG_length = 0;
	
	public void output_Res(File[] file) {
		File pakFile = createFile(file[0].getParent() + "\\imgpak.bin");
		try {
			FileOutputStream fo = new FileOutputStream(pakFile);
			fo.write(PNGbox);
			fo.close();
			System.out.println("pak over");

		} catch (Exception ex) {
			JOptionPane.showMessageDialog(this, "���ʧ��", "����", JOptionPane.ERROR_MESSAGE);
			System.out.println("output_Res error");
		}
	}
	
	public static File[] pngfiles=new File[1000];	
	public static boolean canpack=false;
	public void saveGIF(String path) {
		try {
			BufferedImage bfimg = new BufferedImage(SDef.MO_W, SDef.MO_H, BufferedImage.TYPE_INT_ARGB);
			AnimatedGifEncoder gifs = new AnimatedGifEncoder();
			gifs.setRepeat(0);
			gifs.start(path + ".gif");
			gifs.setQuality(1); // �������
			System.out.println("xs:"+SDef.G_animebox[anime_panel.animelist.getSelectedIndex()].length);
			for (int j = 2; j < SDef.G_animebox[anime_panel.animelist.getSelectedIndex()].length; j++) {
				Graphics gg = bfimg.getGraphics();
				gg.setColor(new Color(0x000000));
				gg.fillRect(0, 0, bfimg.getWidth(), bfimg.getHeight());
				int drawsm = SDef.G_animebox[anime_panel.animelist.getSelectedIndex()][j];
				for (int i = 0; i < (SDef.G_framebox[drawsm].length - 2) / 4; i++) {

					frame_image.drawAniPart20(SDef.G_framebox[drawsm][2 + i * 4], SDef.G_framebox[drawsm][2 + i * 4 + 1] - SDef.MO_X,
							SDef.G_framebox[drawsm][2 + i * 4 + 2] - SDef.MO_Y, SDef.G_framebox[drawsm][2 + i * 4 + 3], gg);

				}
				gifs.setDelay(SDef.SYSTEM_DELAY);
				gifs.addFrame(bfimg);
			}
			gifs.finish();

		} catch (Exception ex) {
			JOptionPane.showMessageDialog(this, "����GIF����", "����", JOptionPane.ERROR_MESSAGE);
		}

	}

	// ����
	private String aboutStr = " IKA �����༭��\n  " + "Creator by airzhangfish \n " + " Version V0.10 in 2012-8-13\n "
			+ " E-mail&MSN:airzhangfish@hotmail.com\n http://www.ikags.com/";
	// ����
	private String helpStr = "IKA �����༭��   ʹ�ð���\n" + "��ݼ�˵����\n" + "\n"  + "���༭��\n" + "��ȡͼƬ            F2\n"
			+ "�Ŵ�                    =  \n" + "��С                    -\n" + "ɾ��                   Delete\n" + "\n" + "֡�༭��\n"
			+ "�ƶ�                   ��������\n" + "ɾ�����            Delete\n" + "ͼƬ�л�            ����м�\n" + "\n" + "�����༭��\n" + "������֡ɾ��       Delete\n"
			+ "��������             C\n" + "������ͣ             V\n" + "�����м���֡       A\n";

	

	// ����ͼƬ
	public static int total_number=0;
	public void saveImage(String path) {
		total_number=0;
		File txtFile = createFile(path + "png.bat");
		String outString = "";
		for (int i = 0; i < 1000; i++) {
			if (SDef.mod_bfimage[i] == null) {
				break;
			} else {

				pngfiles[i] = createFile(path + "Mod_" + i + ".png");
				try {
					outString = outString + "PngMate -colors 128 " + "Mod_" + i + ".png " + "Mod_" + i + ".png\n\r";
					ImageIO.write(SDef.mod_bfimage[i], "png", pngfiles[i]);
					total_number++;
				} catch (Exception ex) {
					System.out.println("png save error");
					JOptionPane.showMessageDialog(this, "ͼƬ�������", "����", JOptionPane.ERROR_MESSAGE);
				}
			}
		}
		System.out.println("png save over");

		try {
			FileOutputStream fo = new FileOutputStream(txtFile);
			fo.write(outString.getBytes("GB2312"));
			fo.close();
			canpack=true;
		} catch (Exception ex) {
			System.out.println("png txt error");
			JOptionPane.showMessageDialog(this, "ת���ļ��������", "����", JOptionPane.ERROR_MESSAGE);
		}
		System.out.println("png txt over");
	}

	// ����xml
	String output = "";

	public void savexml(String path) {
		int modlength = 0;
		output = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>\r\n";
		output =output +"<Ikaanime>\r\n";
		// ���Image��ַ
		for (int i = 0; i < 1000; i++) {
			if (SDef.mod_big_bfimage[i] == null) {
				break;
			} else {
				output = output + "<Image path=\"" + SDef.big_bfimage_path[i] + "\" />\r\n";
			}
		}

		// ������
		for (int i = 0; i < 1000; i++) {
			if (SDef.G_modbox[i][SDef.mod_USE] == 0) {
				modlength = i * 6;
				break;
			}
		}
		for (int i = 0; i < modlength / 6; i++) {
			output = output + "<Module" + " x=\"" + SDef.G_modbox[i][SDef.mod_X] + "\" y=\"" + SDef.G_modbox[i][SDef.mod_Y] + "\" w=\""
					+ SDef.G_modbox[i][SDef.mod_W] + "\" h=\"" + SDef.G_modbox[i][SDef.mod_H] + "\" use=\"" + SDef.G_modbox[i][SDef.mod_USE] + "\" Image=\""
					+ SDef.G_modbox[i][SDef.mod_Image] + "\" />\r\n";
		}
		// ���֡
		for (int i = 0; i < SDef.frame_words.length; i++) {
			output = output + "<Frame explain=\""+SDef.G_framebox_str[i]+"\">\r\n";
			for (int j = 0; j < SDef.G_framebox[i][SDef.frame_number]; j++) {
				output = output + "   <mod id=\"" + SDef.G_framebox[i][2 + j * 4] + "\" x=\"" + (SDef.G_framebox[i][2 + j * 4 + 1]) + "\" y=\""
						+ (SDef.G_framebox[i][2 + j * 4 + 2]) + "\" tran=\"" + SDef.G_framebox[i][2 + j * 4 + 3] + "\" />\r\n";
			}
			output = output + "</Frame>\r\n";
		}
		// �������
		for (int i = 0; i < SDef.anime_words.length; i++) {
			output = output + "<Anime explain=\""+SDef.G_animebox_str[i]+"\">\r\n";
			for (int j = 0; j < SDef.G_animebox[i][SDef.anime_number]; j++) {
				output = output + "   <frame id=\"" + SDef.G_animebox[i][j + 2] + "\" />\r\n";
			}
			output = output + "</Anime>\r\n";
		}
		output =output +"</Ikaanime>\r\n";
		   if(path.substring(path.length()-4, path.length()).equals(".xml")){
			   path=path.substring(0, path.length()-4);
		   }
		File pakFile =createFile(path + ".xml");
		try {
			FileOutputStream fo = new FileOutputStream(pakFile);
			fo.write(output.getBytes("UTF-8"));
			fo.close();
		} catch (Exception ex) {
			System.out.println("save xml error");
			JOptionPane.showMessageDialog(this, "xml�ļ��������", "����", JOptionPane.ERROR_MESSAGE);
		}
	}




	// ��������
	public void savedata(String path) {
		int[] outputintdata;
		int modlength = 0;
		int framelength = 0;
		int animelength = 0;

		// mod����,modbboxΪ���int����
		for (int i = 0; i < 1000; i++) {
			if (SDef.G_modbox[i][SDef.mod_USE] == 0) {
				modlength = i * 6;
				break;
			}
		}
		modlength = modlength + 1;
		int[] modbbox = new int[modlength];
		modbbox[0] = (modlength - 1) / 6;
		for (int i = 0; i < (modlength - 1) / 6; i++) {
			for (int j = 0; j < 6; j++) {
				modbbox[i * 6 + j + 1] = SDef.G_modbox[i][j];
				// System.out.print(modbbox[i * 5 + j + 1] + ",");
			}
		}

		// frame����,framebboxΪ���int����
		for (int i = 0; i < SDef.frame_words.length; i++) {
			framelength = framelength + SDef.G_framebox[i][SDef.frame_number] * 4 + 2;
		}
		framelength = framelength + 1;

		int[] framebbox = new int[framelength];
		framebbox[0] = SDef.frame_words.length;
		int addframeID = 1;
		for (int i = 0; i < framebbox[0]; i++) {
			for (int j = 0; j < SDef.G_framebox[i][SDef.frame_number] * 4 + 2; j++) {
				framebbox[addframeID] = SDef.G_framebox[i][j];
				addframeID++;
			}
		}

		// anime����
		for (int i = 0; i < SDef.anime_words.length; i++) {
			animelength = animelength + SDef.G_animebox[i][SDef.anime_number] + 2;
		}
		animelength = animelength + 1;

		int[] animebbox = new int[animelength];
		animebbox[0] = SDef.anime_words.length;

		int addanimeID = 1;
		for (int i = 0; i < animebbox[0]; i++) {
			for (int j = 0; j < SDef.G_animebox[i][SDef.anime_number] + 2; j++) {
				animebbox[addanimeID] = SDef.G_animebox[i][j];
				addanimeID++;
			}
		}

		// ����int��
		outputintdata = new int[modlength + framelength + animelength];
		for (int i = 0; i < outputintdata.length; i++) {
			if (i < modlength) {
				outputintdata[i] = modbbox[i];
			} else if (i < modlength + framelength) {
				outputintdata[i] = framebbox[i - modlength];
			} else {
				outputintdata[i] = animebbox[i - modlength - framelength];
			}
		}

		byte[] outputdata = new byte[outputintdata.length * 2];
		for (int i = 0; i < outputintdata.length; i++) {
			outputdata[i * 2] = (byte) (outputintdata[i] >> 8);
			outputdata[i * 2 + 1] = (byte) ((outputintdata[i] - (outputdata[i * 2] << 8)));
		}
		File pakFile = createFile(path + ".ika");
		try {
			FileOutputStream fo = new FileOutputStream(pakFile);
			fo.write(outputdata);
			fo.close();
		} catch (Exception ex) {
			System.out.println("save ika error");
			JOptionPane.showMessageDialog(this, "iak�ļ��������", "����", JOptionPane.ERROR_MESSAGE);
		}

	}

	// ������
	public void opendata(String path) {
		int[] inputintdata;
		File pakFile = new File(path);
		try {
			FileInputStream fo = new FileInputStream(pakFile);
			inputintdata = new int[fo.available() / 4];
			for (int i = 0; i < inputintdata.length; i++) {
				inputintdata[i] = (fo.read() << 24) + (fo.read() << 16) + (fo.read() << 8) + fo.read();
			}

			int modlength = 0;
			int readID = 0;
			modlength = inputintdata[readID];
			readID++;
			fo.close();
			for (int i = 0; i < modlength; i++) {
				for (int j = 0; j < 6; j++) {
					SDef.G_modbox[i][j] = inputintdata[readID];
					readID++;
				}
			}
			SDef.mod_words = new String[modlength];
			for (int i = 0; i < SDef.mod_words.length; i++) {
				SDef.mod_words[i] = "mod_" + i + ":" + SDef.G_modbox[i][SDef.mod_X] + "," + SDef.G_modbox[i][SDef.mod_Y] + ","
						+ SDef.G_modbox[i][SDef.mod_W] + "," + SDef.G_modbox[i][SDef.mod_H];
				create_mod_image(i, SDef.G_modbox[i][SDef.mod_X], SDef.G_modbox[i][SDef.mod_Y], SDef.G_modbox[i][SDef.mod_W],
						SDef.G_modbox[i][SDef.mod_H]);

			}
			SDef.mod_listModel.clear();
			for (int i = 0; i < SDef.mod_words.length; i++) {
				SDef.mod_listModel.addElement(SDef.mod_words[i]);
			}

			int framelength = 0;
			framelength = inputintdata[readID];
			readID++;
			for (int i = 0; i < framelength; i++) {
				int temp_length = inputintdata[readID + 1];
				SDef.G_framebox[i] = new int[temp_length * 4 + 2];
				for (int j = 0; j < temp_length * 4 + 2; j++) {
					SDef.G_framebox[i][j] = inputintdata[readID];
					readID++;
				}
			}

			SDef.frame_words = new String[framelength];
			for (int i = 0; i < SDef.frame_words.length; i++) {
				SDef.frame_words[i] = "fra_" + i + ":" + SDef.G_framebox_str[i] + "-";
			}
			SDef.frame_listModel.clear();
			for (int i = 0; i < SDef.frame_words.length; i++) {
				SDef.frame_listModel.addElement(SDef.frame_words[i]);
			}
			frame_panel.up_frame_list();

			int animelength = 0;
			animelength = inputintdata[readID];

			readID++;
			for (int i = 0; i < animelength; i++) {
				int temp_length = inputintdata[readID + 1];
				SDef.G_animebox[i] = new int[temp_length + 2];
				for (int j = 0; j < temp_length + 2; j++) {
					SDef.G_animebox[i][j] = inputintdata[readID];
					readID++;
				}
			}

		} catch (Exception ex) {
			System.out.println("read ika error");
			JOptionPane.showMessageDialog(this, "ika�ļ���ȡ����", "����", JOptionPane.ERROR_MESSAGE);
		}
	}

	public static void create_mod_image(int i, int x, int y, int w, int h) {

		BufferedImage tag = new BufferedImage(w, h, BufferedImage.TYPE_INT_ARGB);
		tag.getGraphics().drawImage(SDef.static_mod_big_bfimage[SDef.G_modbox[i][SDef.mod_Image]], -x, -y,
				SDef.static_mod_big_bfimage[SDef.G_modbox[i][SDef.mod_Image]].getWidth(null),
				SDef.static_mod_big_bfimage[SDef.G_modbox[i][SDef.mod_Image]].getHeight(null), null);
		SDef.mod_bfimage[i] = tag;
	}

	public ika_anime_editor() {

		this.setSize(1024, 768); // ������Ĵ�С
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setResizable(true); // ���岻�ܸı��С
		this.setTitle("Ika �����༭��    -Version 0.09 in 2012-8-13"); // ���ñ���
		
		enableInputMethods(true);
		
		// menu�˵���ѡ��
		// file
			// jMenuFileOpenika.registerKeyboardAction(this, "jMenuFileOpenika", KeyStroke.getKeyStroke(KeyEvent.VK_F6, 0, false),
		// JComponent.WHEN_IN_FOCUSED_WINDOW);
		jMenuFileSaveika.registerKeyboardAction(this, "jMenuFileSaveika", KeyStroke.getKeyStroke(KeyEvent.VK_E, java.awt.event.InputEvent.CTRL_MASK, false),
				JComponent.WHEN_IN_FOCUSED_WINDOW);
		jMenuFileOpenxml.registerKeyboardAction(this, "jMenuFileOpenxml", KeyStroke.getKeyStroke(KeyEvent.VK_O, java.awt.event.InputEvent.CTRL_MASK, false),
				JComponent.WHEN_IN_FOCUSED_WINDOW);
		jMenuFileSavexml.registerKeyboardAction(this, "jMenuFileSavexml", KeyStroke.getKeyStroke(KeyEvent.VK_S, java.awt.event.InputEvent.CTRL_MASK, false),
				JComponent.WHEN_IN_FOCUSED_WINDOW);
		jMenuFileExit.registerKeyboardAction(this, "jMenuFileExit", KeyStroke.getKeyStroke(KeyEvent.VK_X, java.awt.event.InputEvent.CTRL_MASK, false),
				JComponent.WHEN_IN_FOCUSED_WINDOW);
		jMenuFileSaveImage.registerKeyboardAction(this, "jMenuFileSaveImage", KeyStroke.getKeyStroke(KeyEvent.VK_P,java.awt.event.InputEvent.CTRL_MASK, false),
				JComponent.WHEN_IN_FOCUSED_WINDOW);
		jMenuFileSaveGIF.registerKeyboardAction(this, "jMenuFileSaveGIF", KeyStroke.getKeyStroke(KeyEvent.VK_G, java.awt.event.InputEvent.CTRL_MASK, false),
				JComponent.WHEN_IN_FOCUSED_WINDOW);
		
		jMenuOpinionScreen.registerKeyboardAction(this, "jMenuOpinionScreen", KeyStroke.getKeyStroke(KeyEvent.VK_C, java.awt.event.InputEvent.CTRL_MASK, false),
				JComponent.WHEN_IN_FOCUSED_WINDOW);
		jMenuOpinionFrame.registerKeyboardAction(this, "jMenuOpinionFrame", KeyStroke.getKeyStroke(KeyEvent.VK_D,java.awt.event.InputEvent.CTRL_MASK, false),
				JComponent.WHEN_IN_FOCUSED_WINDOW);
		
		jMenuHelpHelp.registerKeyboardAction(this, "jMenuHelpHelp", KeyStroke.getKeyStroke(KeyEvent.VK_F6, 0, false),
				JComponent.WHEN_IN_FOCUSED_WINDOW);
		jMenuFile.add(jMenuFileOpenxml);
		jMenuFileOpenxml.setToolTipText("��һ��ר�õ�xml�ļ�");
		
		jMenuFile.add(jMenuFileSavexml);
		jMenuFileSavexml.setToolTipText("����Ϊר�õ�xml�ļ�");
		// jMenuFile.add(jMenuFileOpenika);
		jMenuFile.add(jMenuFileSaveika);
		jMenuFileSaveika.setToolTipText("����Ϊ�������ļ�");
		jMenuFile.add(jMenuFileSaveImage);
		jMenuFile.add(jMenuFileSavepack);
		jMenuFileSaveImage.setToolTipText("�����и��С���ͼ��");
		jMenuFile.add(jMenuFileSaveGIF);
		jMenuFileSaveGIF.setToolTipText("ѡ��һ�������ļ���������ΪGIF");
		jMenuFile.add(jMenuFileExit);
		jMenuFileExit.setToolTipText("�˳�");
		jMenuFileSavepack.addActionListener(this);
		
		
		jMenuFileSaveGIF.addActionListener(this);
		jMenuFileOpenxml.addActionListener(this);
		jMenuFileSavexml.addActionListener(this);
		// jMenuFileOpenika.addActionListener(this);
		jMenuFileSaveImage.addActionListener(this);
		jMenuFileSaveika.addActionListener(this);
		jMenuFileExit.addActionListener(this);



		// ����
		jMenuOpinion.add(jMenuOpinionBGColor);
		jMenuOpinionBGColor.setToolTipText("���ñ༭���ı�����ɫ");
		jMenuOpinion.add(jMenuOpinionBoxColor);
		jMenuOpinionBoxColor.setToolTipText("���������ߵ���ɫ");
		jMenuOpinion.add(jMenuOpinionlineColor);
		jMenuOpinionlineColor.setToolTipText("�������ߵ���ɫ");
		jMenuOpinion.add(jMenuOpinionScreen);
		jMenuOpinionScreen.setToolTipText("�����ֻ���Ļ��С");
		jMenuOpinion.add(jMenuOpinionFrame);
		jMenuOpinionFrame.setToolTipText("���ö������ŵ�ÿ֡�ٶ�");
		jMenuOpinionBGColor.addActionListener(this);
		jMenuOpinionBoxColor.addActionListener(this);
		jMenuOpinionlineColor.addActionListener(this);
		jMenuOpinionScreen.addActionListener(this);
		jMenuOpinionFrame.addActionListener(this);
		

		// help
		jMenuHelp.add(jMenuHelpHelp);
		jMenuOpinionFrame.setToolTipText("Ika�����༭��ʹ�ð���");
		jMenuHelp.add(jMenuHelpAbout);
		jMenuFile.setMnemonic('F');
		jMenuOpinion.setMnemonic('S');
		jMenuHelp.setMnemonic('H');
		jMenuOpinionFrame.setToolTipText("Ika�����༭������");
		jMenuHelpHelp.addActionListener(this);
		jMenuHelpAbout.addActionListener(this);

		// �ܹ�����

		jMenuBar1.add(jMenuFile);
		jMenuBar1.add(jMenuOpinion);
		jMenuBar1.add(jMenuHelp);
		this.setJMenuBar(jMenuBar1);

		Container contents = getContentPane();
		jtp = new JTabbedPane(JTabbedPane.BOTTOM);
		jtp.addTab("���༭", mod_Panelx);
		jtp.addTab("֡�༭", frame_panelx);
		jtp.addTab("�����༭", anime_panelx);
		// jtp.addTab("�ű��༭", new JCheckBox("�ű��༭"));
		mthread = new myThread();
		contents.add(jtp);
		setVisible(true);
	}

	public static void main(String args[]) {
		SDef.setMySkin(2);
		new ika_anime_editor();
	}

	private static File createFile(String fileName) {
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
