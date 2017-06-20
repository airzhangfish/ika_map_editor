package ika_avg_maker;
import java.awt.Container;
import javax.swing.*;
import javax.swing.text.BadLocationException;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.FileDialog;
import java.awt.event.KeyEvent;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStreamReader;

public class IkaAVG_maker extends JFrame implements ActionListener {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JTabbedPane jtp;
	private JMenuBar jMenuBar1 = new JMenuBar();
	private JMenu jMenuFile = new JMenu("文件");
	private JMenuItem jMenuFileSaveMVG = new JMenuItem("导出MVG文件..");
	private JMenuItem jMenuFileOpenAVG = new JMenuItem("读取AVG文件..");
	private JMenuItem jMenuFileSaveAVG = new JMenuItem("保存为AVG文件..");
	private JMenuItem jMenuFileExit = new JMenuItem("退出");
	private JMenu jMenuOpinion = new JMenu("设置");
	private JMenuItem jMenuOpinionScreen = new JMenuItem("设置手机屏幕大小");
	private JMenu jMenuHelp = new JMenu("其它");
	private JMenuItem jMenuHelpAbout = new JMenuItem("关于");
	private JMenuItem jMenuHelpHelp = new JMenuItem("帮助");
	script_panel scriptpanel = new script_panel();
	public static String lastDir="";
	public void actionPerformed(ActionEvent actionEvent) {
		Object source = actionEvent.getSource();
		// 保存AVG数据
		if (source == jMenuFileSaveAVG) {
			FileDialog xs = new FileDialog(this, "保存为AVG文件", FileDialog.SAVE);
			xs.setFile("*.*");
			xs.setVisible(true);
			String f = xs.getFile();
			 lastDir = xs.getDirectory();
			if (f != null) {
				saveAVG(lastDir + f);
			}
		}
		// 读取AVG数据
		if (source == jMenuFileOpenAVG) {
			FileDialog xs = new FileDialog(this, "读取AVG文件", FileDialog.LOAD);
			xs.setFile("*.AVG");
			xs.setVisible(true);
			String f = xs.getFile();
			 lastDir = xs.getDirectory();
			if (f != null) {
				openAVG(lastDir + f);
			}
		}

		// 保存MVG文件
		if (source == jMenuFileSaveMVG) {
			FileDialog xs = new FileDialog(this, "保存为MVG文件", FileDialog.SAVE);
			xs.setFile("*.*");
			xs.setVisible(true);
			String f = xs.getFile();
			 lastDir = xs.getDirectory();
			if (f != null) {
				savedata(lastDir + f);
			}
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
		if (source == jMenuFileExit) {
			System.exit(0);
		}

		if (source == jMenuOpinionScreen) { // 屏幕大小设定
			ScreenSelect xxx = new ScreenSelect();
			xxx.setVisible(true);
		}

	}

	// 关于
	private String aboutStr = " IKA AVG脚本编辑器\n  " + "Creator by airzhangfish \n " + " Version V0.02A in 2007-9-22 \n "
			+ " E-mail&MSN:airzhangfish@hotmail.com";

	// 帮助
	private String helpStr = "IKA AVG脚本编辑器   使用帮助\n" + "快捷键说明：\n";

	public void saveAVG(String path) {
		try {
			txt_panel.text = txt_panel.doc.getText(0, txt_panel.doc.getLength());
		} catch (BadLocationException e) {
		}
		File pakFile = createFile(path + ".avg");
		try {
			FileOutputStream fo = new FileOutputStream(pakFile);
			fo.write(txt_panel.text.getBytes("UTF-8"));
			fo.close();
		} catch (Exception ex) {
			System.out.println("save xml error");
		}
	}

	// 打开AVG文件
	int bufferlength = 1000;

	public void openAVG(String path) {
		File file = new File(path);
		try {
			// 读取数组
			InputStreamReader is = new InputStreamReader(new FileInputStream(file), "UTF-8");
			BufferedReader reader = new BufferedReader(is);
			char[] word_uni = new char[(int) file.length()];
			reader.read(word_uni);
			txt_panel.text = new String(word_uni);
			System.out.println("xx:" + txt_panel.text.length());
		} catch (Exception e) {
			System.out.println("matrix error");
		}
		try {
			// txt_panel.doc.insertString(0, txt_panel.text, null);
			txt_panel.doc.replace(0, txt_panel.doc.getLength(), txt_panel.text, txt_panel.mainStyle);
		} catch (BadLocationException e) {
			System.out.println("doc string  error");
		}
	}

	// 保存数据
	public void savedata(String path) {

	}

	public IkaAVG_maker() {
		this.setSize(800, 600); // 将窗体的大小
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setResizable(true); // 窗体不能改变大小
		this.setTitle("Ika AVG脚本编辑器"); // 设置标题

		// menu菜单的选项
		// file
		jMenuFile.add(jMenuFileOpenAVG);
		jMenuFile.add(jMenuFileSaveAVG);
		jMenuFile.add(jMenuFileSaveMVG);
		jMenuFile.add(jMenuFileExit);

		jMenuFileOpenAVG.addActionListener(this);
		jMenuFileSaveAVG.addActionListener(this);
		jMenuFileSaveMVG.addActionListener(this);
		jMenuFileExit.addActionListener(this);

		jMenuFileSaveMVG.registerKeyboardAction(this, "jMenuFileSaveMVG", KeyStroke.getKeyStroke(KeyEvent.VK_F6, 0, false),
				JComponent.WHEN_IN_FOCUSED_WINDOW);
		jMenuFileOpenAVG.registerKeyboardAction(this, "jMenuFileOpenAVG", KeyStroke.getKeyStroke(KeyEvent.VK_F4, 0, false),
				JComponent.WHEN_IN_FOCUSED_WINDOW);
		jMenuFileSaveAVG.registerKeyboardAction(this, "jMenuFileSaveAVG", KeyStroke.getKeyStroke(KeyEvent.VK_F5, 0, false),
				JComponent.WHEN_IN_FOCUSED_WINDOW);
		jMenuFileExit.registerKeyboardAction(this, "jMenuFileExit", KeyStroke.getKeyStroke(KeyEvent.VK_F8, 0, false),
				JComponent.WHEN_IN_FOCUSED_WINDOW);
		jMenuHelpHelp.registerKeyboardAction(this, "jMenuHelpHelp", KeyStroke.getKeyStroke(KeyEvent.VK_F10, 0, false),
				JComponent.WHEN_IN_FOCUSED_WINDOW);
		// 设置
		jMenuOpinion.add(jMenuOpinionScreen);
		jMenuOpinionScreen.addActionListener(this);

		// help
		jMenuHelp.add(jMenuHelpHelp);
		jMenuHelp.add(jMenuHelpAbout);
		jMenuHelpHelp.addActionListener(this);
		jMenuHelpAbout.addActionListener(this);

		// 总工具栏
		jMenuBar1.add(jMenuFile);
		jMenuBar1.add(jMenuOpinion);
		jMenuBar1.add(jMenuHelp);
		this.setJMenuBar(jMenuBar1);

		Container contents = getContentPane();
		jtp = new JTabbedPane(JTabbedPane.BOTTOM);
		jtp.addTab("AVG脚本编辑", scriptpanel);
		contents.add(jtp);
		setVisible(true);
	}

	public static void main(String args[]) {
		SDef.setMySkin(1);
		new IkaAVG_maker();
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
