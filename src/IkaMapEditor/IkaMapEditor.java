package IkaMapEditor;
import java.awt.Container;
import java.awt.FileDialog;

import javax.swing.*;
import javax.swing.filechooser.FileFilter;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;

public class IkaMapEditor extends JFrame implements ActionListener {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JTabbedPane jtp;
	private JMenuBar jMenu = new JMenuBar();
	private JMenu jMenuFile = new JMenu("文件");
	private JMenuItem file_new = new JMenuItem("新建...");
	private JMenuItem file_open = new JMenuItem("打开...");
	private JMenuItem file_save = new JMenuItem("另存为...");
	private JMenuItem file_import = new JMenuItem("导入PNG...");
	private JMenuItem file_export_png = new JMenuItem("导出PNG小图");
	private JMenuItem file_pack = new JMenuItem("打包PNG小图");
	private JMenuItem file_outbigimg = new JMenuItem("输出大地图");
	private JMenuItem file_exit = new JMenuItem("退出");
	private JMenu jMenuTools = new JMenu("地图工具");
	private JMenuItem tools_mapsetup = new JMenuItem("地图设置...");
	private JMenuItem tools_preview = new JMenuItem("预览");
	private JMenuItem tools_previewsetup = new JMenuItem("预览设置...");
	private JMenu jMenuHelp = new JMenu("帮助");
	private JMenuItem jMenuHelpAbout = new JMenuItem("关于");
	private JMenuItem jMenuHelpHelp = new JMenuItem("帮助文件");

	Map_panel map_panel;

	public void actionPerformed(ActionEvent actionEvent) {
		Object source = actionEvent.getSource();
		if (source == file_new) {
			new newMap(getX() + 100, getY() + 100);
		}
		if (source == file_open) {
			JFileChooser c = new JFileChooser();
			int rVal = c.showOpenDialog(this);
			if (rVal == JFileChooser.APPROVE_OPTION) {
				String mame = c.getCurrentDirectory().toString() + "\\" + c.getSelectedFile().getName();
				loaddata(mame);
			}
		}
		if (source == file_save) {
			JFileChooser c = new JFileChooser();
			int rVal = c.showSaveDialog(this);
			if (rVal == JFileChooser.APPROVE_OPTION) {
				String mame = c.getCurrentDirectory().toString() + "\\" + c.getSelectedFile().getName();
				savedata(mame);
			}
		}
		if (source == file_import) {
			openPNG();
		}

		if (source == file_export_png) {
			FileDialog xs = new FileDialog(this, "save Image file", FileDialog.SAVE);
			xs.setFile("*.*");
			xs.setVisible(true);
			String f = xs.getFile();
			String lastDir = xs.getDirectory();
			if (f != null) {
				saveImage(lastDir);
			}
		}
		if (source == file_pack) {
			if (Imagesaveover == true) {
				getPNG(pngfiles);
				output_Res(pngfiles);
			} else {
				JOptionPane.showMessageDialog(this, "请先导出PNG小图片", "打包错误提示", JOptionPane.ERROR_MESSAGE);
			}
		}

		if (source == file_outbigimg) {
			JFileChooser c = new JFileChooser();
			int rVal = c.showSaveDialog(this);
			if (rVal == JFileChooser.APPROVE_OPTION) {
				String mame = c.getCurrentDirectory().toString() + "\\" + c.getSelectedFile().getName();
				 savebigmap(mame); //输出大地图
			}
		}

		if (source == tools_mapsetup) {
			new setMap(getX() + 100, getY() + 100);
		}

		if (source == tools_previewsetup) {
			new ScreenSetup(getX() + 100, getY() + 20);
		}
		if (source == tools_preview) {
			new Map_review(getX() + 60, getY() + 60);
		}
		// 帮助
		if (source == jMenuHelpHelp) {
			JOptionPane.showMessageDialog(this, helpStr, "帮助", JOptionPane.INFORMATION_MESSAGE);
		}
		// 关于
		if (source == jMenuHelpAbout) {
			JOptionPane.showMessageDialog(this, aboutStr, "关于", JOptionPane.INFORMATION_MESSAGE);
		}

		// 软件退出
		if (source == file_exit) {
			System.exit(0);
		}
	}

	// 关于
	public String version = "Version 0.04C in 2008-09-18";
	private String aboutStr = " IKA 地图编辑器\n  " + "Creator by airzhangfish \n " + version + " \n " + " E-mail&MSN:airzhangfish@hotmail.com \nhttp://airzhangfish.spaces.live.com/\nhttp://www.ikags.com/";

	// 帮助
	private String helpStr = "IKA 地图编辑器  使用帮助\n" + "格式读取说明：\n固定标识[3 byte]+层数[2 byte]+tile长宽[2 byte+2byte]+\n地图长宽[2 byte+2byte]+数据[2byte*地图长*地图宽]\n数据2byte解析:4bit通过信息+4bit旋转信息+1byte ID图片数据" + "\n";
	File[] pngfiles;

	public void loaddata(String path) {
		File datamap = new File(path);
		byte[] tmpmap = new byte[13];
		try {
			FileInputStream fo = new FileInputStream(datamap);
			tmpmap = new byte[fo.available()];
			fo.read(tmpmap, 0, tmpmap.length);
			fo.close();
		} catch (Exception ex) {
			JOptionPane.showMessageDialog(this, "读取地图出错", "出错", JOptionPane.ERROR_MESSAGE);
		}
		int loadID = 13;
		if (tmpmap[0] == 0x49 && tmpmap[1] == 0x4B && tmpmap[2] == 0x41) {
			int layernumber = ((int) ((tmpmap[3] + 256) % 256) << 8) + (int) ((tmpmap[4] + 256) % 256);
			SDef.tile_SW = ((int) ((tmpmap[5] + 256) % 256) << 8) + (int) ((tmpmap[6] + 256) % 256);
			SDef.tile_SH = ((int) ((tmpmap[7] + 256) % 256) << 8) + (int) ((tmpmap[8] + 256) % 256);
			SDef.map_SW = ((int) ((tmpmap[9] + 256) % 256) << 8) + (int) ((tmpmap[10] + 256) % 256);
			SDef.map_SH = ((int) ((tmpmap[11] + 256) % 256) << 8) + (int) ((tmpmap[12] + 256) % 256);
			SDef.layer0 = new short[SDef.map_SW][SDef.map_SH];
			for (int i = 0; i < SDef.map_SW; i++) {
				for (int j = 0; j < SDef.map_SH; j++) {
					SDef.layer0[i][j] = (short) (((int) ((tmpmap[loadID] + 256) % 256) << 8) + (int) (tmpmap[loadID + 1] + 256) % 256);
					loadID = loadID + 2;
				}
			}
		} else {
			JOptionPane.showMessageDialog(this, "此文件不符合目前编辑器使用的格式", "出错", JOptionPane.ERROR_MESSAGE);
		}
	}

	// 保存数据
	public void savedata(String path) {
		int totallength = 0;
		int outID = 0;
		totallength = 3 + 2 + 2 + 2 + 2 + 2 + 2 * SDef.map_SH * SDef.map_SW;// 标识+层数+tile长宽+地图长宽+数据
		byte[] outputdata = new byte[totallength];
		// 标识
		outputdata[outID] = 0x49;
		outID++;
		outputdata[outID] = 0x4B;
		outID++;
		outputdata[outID] = 0x41;
		outID++;

		// 地图层数
		outputdata[outID] = 0x00;
		outID++;
		outputdata[outID] = 0x01;
		outID++;

		// tile长宽
		outputdata[outID] = (byte) (SDef.tile_SW >> 8);
		outID++;
		outputdata[outID] = (byte) (SDef.tile_SW - (((byte) (SDef.tile_SW >> 8)) << 8));
		outID++;
		outputdata[outID] = (byte) (SDef.tile_SH >> 8);
		outID++;
		outputdata[outID] = (byte) (SDef.tile_SH - (((byte) (SDef.tile_SH >> 8)) << 8));
		outID++;

		// 地图长宽
		outputdata[outID] = (byte) (SDef.map_SW >> 8);
		outID++;
		outputdata[outID] = (byte) (SDef.map_SW - (((byte) (SDef.map_SW >> 8)) << 8));
		outID++;
		outputdata[outID] = (byte) (SDef.map_SH >> 8);
		outID++;
		outputdata[outID] = (byte) (SDef.map_SH - (((byte) (SDef.map_SH >> 8)) << 8));
		outID++;

		for (int i = 0; i < SDef.map_SW; i++) {
			for (int j = 0; j < SDef.map_SH; j++) {
				outputdata[outID] = (byte) (SDef.layer0[i][j] >> 8);
				outID++;
				outputdata[outID] = (byte) (SDef.layer0[i][j] - (((byte) (SDef.layer0[i][j] >> 8)) << 8));
				outID++;
			}
		}

		File pakFile = SDef.createFile(path + ".map");
		try {
			FileOutputStream fo = new FileOutputStream(pakFile);
			fo.write(outputdata);
			fo.close();
		} catch (Exception ex) {
			JOptionPane.showMessageDialog(this, "数据保存出错", "出错", JOptionPane.ERROR_MESSAGE);
		}
	}

	// 保存xml
	String output = "";

	// 打开xml文件
	public void openPNG() {
		JFileChooser c = new JFileChooser();
		c.setFileFilter(flefilter);
		int rVal = c.showOpenDialog(this);
		if (rVal == JFileChooser.APPROVE_OPTION) {
			String mame = c.getCurrentDirectory().toString() + "\\" + c.getSelectedFile().getName();
			File pngfile = new File(mame);
			if (SDef.map_SW > 0 && SDef.map_SH > 0 && SDef.tile_SW > 0 && SDef.tile_SH > 0) {
				try {
					SDef.PIC_bfimage = ImageIO.read(pngfile);
					ImageIO.write(SDef.PIC_bfimage, "png", pngfile);
					int witch = SDef.PIC_bfimage.getWidth() / SDef.tile_SW;
					SDef.totalpiece = witch * (SDef.PIC_bfimage.getHeight() / SDef.tile_SH);
					if (SDef.totalpiece == 0) {
						JOptionPane.showMessageDialog(this, "地图设置物块大小不符合导致无法正常创建物块", "出错", JOptionPane.ERROR_MESSAGE);
					} else {
						SDef.mod_bfimage = new BufferedImage[SDef.totalpiece];
						for (int i = 0; i < SDef.totalpiece; i++) {
							create_mod_image(i, (i % witch) * SDef.tile_SW, (i / witch) * SDef.tile_SH, SDef.tile_SW, SDef.tile_SH);
						}
					}
				} catch (Exception ex) {
					JOptionPane.showMessageDialog(this, "图片读取出错", "出错", JOptionPane.ERROR_MESSAGE);
					ex.printStackTrace();
				}
			} else {
				JOptionPane.showMessageDialog(this, "请先创建新地图", "出错", JOptionPane.ERROR_MESSAGE);
			}
		}
	}

	public static void create_mod_image(int i, int x, int y, int w, int h) {
		BufferedImage tag = new BufferedImage(w, h, BufferedImage.TYPE_INT_ARGB);
		tag.getGraphics().drawImage(SDef.PIC_bfimage, -x, -y, SDef.PIC_bfimage.getWidth(null), SDef.PIC_bfimage.getHeight(null), null);
		SDef.mod_bfimage[i] = tag;
	}

	public IkaMapEditor() {

		this.setSize(640, 480); // 将窗体的大小
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setResizable(true); // 窗体不能改变大小
		this.setTitle("Ika Map Editor      -" + version); // 设置标题

		// menu菜单的选项
		// file
		map_panel = new Map_panel();

		jMenuHelpHelp.registerKeyboardAction(this, "jMenuHelpHelp", KeyStroke.getKeyStroke(KeyEvent.VK_F10, 0, false), JComponent.WHEN_IN_FOCUSED_WINDOW);
		// 设置

		jMenuFile.add(file_new);
		jMenuFile.add(file_open);
		jMenuFile.add(file_save);
		jMenuFile.addSeparator();
		jMenuFile.add(file_import);
		jMenuFile.addSeparator();
		jMenuFile.add(file_export_png);
		jMenuFile.add(file_pack);
		jMenuFile.add(file_outbigimg);
		jMenuFile.addSeparator();
		jMenuFile.add(file_exit);
		file_new.addActionListener(this);
		file_open.addActionListener(this);
		file_save.addActionListener(this);
		file_import.addActionListener(this);
		file_export_png.addActionListener(this);
		file_pack.addActionListener(this);
		file_outbigimg.addActionListener(this);
		file_exit.addActionListener(this);

		jMenuTools.add(tools_mapsetup);
		jMenuTools.addSeparator();
		jMenuTools.add(tools_preview);
		jMenuTools.add(tools_previewsetup);
		tools_mapsetup.addActionListener(this);
		tools_preview.addActionListener(this);
		tools_previewsetup.addActionListener(this);

		// help
		jMenuHelp.add(jMenuHelpHelp);
		jMenuHelp.add(jMenuHelpAbout);
		jMenuHelpHelp.addActionListener(this);
		jMenuHelpAbout.addActionListener(this);

		// 总工具栏
		jMenu.add(jMenuFile);
		jMenu.add(jMenuTools);
		jMenu.add(jMenuHelp);
		this.setJMenuBar(jMenu);
		Container contents = getContentPane();
		jtp = new JTabbedPane(JTabbedPane.TOP);
		jtp.addTab("地图编辑", map_panel);
		// jtp.addTab("地图动画编辑", new JCheckBox("脚本编辑"));
		contents.add(jtp);
		setVisible(true);
	}

	public static void main(String args[]) {
		SDef.setMySkin(3);
		new IkaMapEditor();
	}

	FileFilter flefilter = new FileFilter() {
		public boolean accept(File f) {
			return f.getName().toLowerCase().endsWith(".png") || f.isDirectory();
		}

		public String getDescription() {
			return "PNG Files";
		}
	};

	public static boolean Imagesaveover = false;

	// 保存图片
	public void saveImage(String path) {
		File txtFile = SDef.createFile(path + "png.bat");
		String outString = "";
		pngfiles = new File[SDef.mod_bfimage.length];
		for (int i = 0; i < SDef.mod_bfimage.length; i++) {
			// File pngFile = SDef.createFile(path + "Map_" + i + ".png");
			pngfiles[i] = SDef.createFile(path + "Map_" + i + ".png");
			try {
				outString = outString + "PngMate -colors 128 " + "Map_" + i + ".png " + "Map_" + i + ".png\n\r";
				ImageIO.write(SDef.mod_bfimage[i], "png", pngfiles[i]);
			} catch (Exception ex) {
				System.out.println("png save error");
				JOptionPane.showMessageDialog(this, "图片保存出错", "出错", JOptionPane.ERROR_MESSAGE);
			}
		}
		System.out.println("png save over");
		try {
			FileOutputStream fo = new FileOutputStream(txtFile);
			fo.write(outString.getBytes("GB2312"));
			fo.close();
			Imagesaveover = true;
		} catch (Exception ex) {
			System.out.println("png txt error");
			JOptionPane.showMessageDialog(this, "bat文件保存出错", "出错", JOptionPane.ERROR_MESSAGE);
		}
		System.out.println("png txt over");
	}

	static byte[] PNGbox;
	static int PNGlong = 0;
	static long total_PNG_length = 0;

	public void getPNG(File[] file) {
		total_PNG_length = 0;
		PNGlong = 0;
		for (int i = 0; i < file.length; i++) {
			total_PNG_length = total_PNG_length + file[i].length();
		}
		PNGlong = (int) total_PNG_length + 2 * (file.length) + 2;
		PNGbox = new byte[PNGlong];
		int PNGBOXID = 0;
		PNGbox[PNGBOXID] = (byte) ((file.length) >> 8);
		PNGBOXID++;
		PNGbox[PNGBOXID] = (byte) ((file.length) - (PNGbox[PNGBOXID] << 8));
		PNGBOXID++;
		for (int i = 0; i < file.length; i++) {
			PNGbox[PNGBOXID] = (byte) (file[i].length() >> 8);
			PNGBOXID++;
			PNGbox[PNGBOXID] = (byte) (file[i].length() - (((int) PNGbox[PNGBOXID]) << 8));
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
				JOptionPane.showMessageDialog(this, "打包失败", "出错", JOptionPane.ERROR_MESSAGE);
				System.out.println("read " + file[i] + " error");
			}
		}
	}

	public void output_Res(File[] file) {
		File pakFile = SDef.createFile(file[0].getParent() + "\\imgpak.bin");
		try {
			FileOutputStream fo = new FileOutputStream(pakFile);
			fo.write(PNGbox);
			fo.close();
			System.out.println("pak over");

		} catch (Exception ex) {
			JOptionPane.showMessageDialog(this, "打包失败", "出错", JOptionPane.ERROR_MESSAGE);
			System.out.println("output_Res error");
		}
	}

	public static BufferedImage bigbmap;
	public void savebigmap(String path) {
		//地图大图输出
		File bigmap= SDef.createFile(path+".png");
		try {
			 bigbmap = new BufferedImage(SDef.map_SW*SDef.tile_SW, SDef.map_SH*SDef.tile_SH, BufferedImage.TYPE_INT_ARGB);
				if (SDef.mod_bfimage != null) {
					for (int i = 0; i < SDef.map_SW; i++) {
						for (int j = 0; j < SDef.map_SH; j++) {
							int bufferintx = SDef.tile_SW * i;
							int bufferinty = SDef.tile_SH * j;
							if (SDef.layer0[i][j] == -1) {
								bigbmap.getGraphics().setColor(SDef.Color_HEI);
								 bigbmap.getGraphics().fillRect(bufferintx,  bufferinty, SDef.tile_SW, SDef.tile_SH);
							} else {
								Map_Screen.drawAniPart20(SDef.get_ID(SDef.layer0[i][j]), bufferintx,  bufferinty, SDef
										.get_Trans(SDef.layer0[i][j]), bigbmap.getGraphics());
							}
						}
					}
				}
			ImageIO.write(bigbmap, "png", bigmap);
		} catch (Exception ex) {
			System.out.println("big png save error");
			JOptionPane.showMessageDialog(this, "大地图保存出错", "出错", JOptionPane.ERROR_MESSAGE);
		}
		
		
		
	}
	
}
