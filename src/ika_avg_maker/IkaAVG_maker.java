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
	private JMenu jMenuFile = new JMenu("�ļ�");
	private JMenuItem jMenuFileSaveMVG = new JMenuItem("����MVG�ļ�..");
	private JMenuItem jMenuFileOpenAVG = new JMenuItem("��ȡAVG�ļ�..");
	private JMenuItem jMenuFileSaveAVG = new JMenuItem("����ΪAVG�ļ�..");
	private JMenuItem jMenuFileExit = new JMenuItem("�˳�");
	private JMenu jMenuOpinion = new JMenu("����");
	private JMenuItem jMenuOpinionScreen = new JMenuItem("�����ֻ���Ļ��С");
	private JMenu jMenuHelp = new JMenu("����");
	private JMenuItem jMenuHelpAbout = new JMenuItem("����");
	private JMenuItem jMenuHelpHelp = new JMenuItem("����");
	script_panel scriptpanel = new script_panel();
	public static String lastDir="";
	public void actionPerformed(ActionEvent actionEvent) {
		Object source = actionEvent.getSource();
		// ����AVG����
		if (source == jMenuFileSaveAVG) {
			FileDialog xs = new FileDialog(this, "����ΪAVG�ļ�", FileDialog.SAVE);
			xs.setFile("*.*");
			xs.setVisible(true);
			String f = xs.getFile();
			 lastDir = xs.getDirectory();
			if (f != null) {
				saveAVG(lastDir + f);
			}
		}
		// ��ȡAVG����
		if (source == jMenuFileOpenAVG) {
			FileDialog xs = new FileDialog(this, "��ȡAVG�ļ�", FileDialog.LOAD);
			xs.setFile("*.AVG");
			xs.setVisible(true);
			String f = xs.getFile();
			 lastDir = xs.getDirectory();
			if (f != null) {
				openAVG(lastDir + f);
			}
		}

		// ����MVG�ļ�
		if (source == jMenuFileSaveMVG) {
			FileDialog xs = new FileDialog(this, "����ΪMVG�ļ�", FileDialog.SAVE);
			xs.setFile("*.*");
			xs.setVisible(true);
			String f = xs.getFile();
			 lastDir = xs.getDirectory();
			if (f != null) {
				savedata(lastDir + f);
			}
		}
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

		if (source == jMenuOpinionScreen) { // ��Ļ��С�趨
			ScreenSelect xxx = new ScreenSelect();
			xxx.setVisible(true);
		}

	}

	// ����
	private String aboutStr = " IKA AVG�ű��༭��\n  " + "Creator by airzhangfish \n " + " Version V0.02A in 2007-9-22 \n "
			+ " E-mail&MSN:airzhangfish@hotmail.com";

	// ����
	private String helpStr = "IKA AVG�ű��༭��   ʹ�ð���\n" + "��ݼ�˵����\n";

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

	// ��AVG�ļ�
	int bufferlength = 1000;

	public void openAVG(String path) {
		File file = new File(path);
		try {
			// ��ȡ����
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

	// ��������
	public void savedata(String path) {

	}

	public IkaAVG_maker() {
		this.setSize(800, 600); // ������Ĵ�С
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setResizable(true); // ���岻�ܸı��С
		this.setTitle("Ika AVG�ű��༭��"); // ���ñ���

		// menu�˵���ѡ��
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
		// ����
		jMenuOpinion.add(jMenuOpinionScreen);
		jMenuOpinionScreen.addActionListener(this);

		// help
		jMenuHelp.add(jMenuHelpHelp);
		jMenuHelp.add(jMenuHelpAbout);
		jMenuHelpHelp.addActionListener(this);
		jMenuHelpAbout.addActionListener(this);

		// �ܹ�����
		jMenuBar1.add(jMenuFile);
		jMenuBar1.add(jMenuOpinion);
		jMenuBar1.add(jMenuHelp);
		this.setJMenuBar(jMenuBar1);

		Container contents = getContentPane();
		jtp = new JTabbedPane(JTabbedPane.BOTTOM);
		jtp.addTab("AVG�ű��༭", scriptpanel);
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
