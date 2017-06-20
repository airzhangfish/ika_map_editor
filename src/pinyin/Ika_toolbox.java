package pinyin;


import java.awt.Container;
import javax.swing.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;



public class Ika_toolbox extends JFrame implements ActionListener {
	private static final long serialVersionUID = 1L;
	private static final String myversion = "0.01standard ";
	private JTabbedPane jtp;
	private JMenuBar jMenuBar1 = new JMenuBar();
	private JMenu jMenuHelp = new JMenu("说明");
	private JMenuItem jMenuHelpAbout = new JMenuItem("关于");
	private JMenuItem jMenuHelpHelp = new JMenuItem("帮助");
	private JMenuItem jMenuFileExit = new JMenuItem("退出");

	public void actionPerformed(ActionEvent actionEvent) {
		Object source = actionEvent.getSource();
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

	}

	// 关于
	private String aboutStr = " 字库工具 by airzhangfish";

	// 帮助
	private String helpStr = "导入CSV字库,然后程序会自动\n在文件目录下生成2个文件,一个为index.ime索引文件,\n一个为lib.ime字库文件\n索引文件为从A到Z,每个拼音下所含有的字的总数\n而lib文件则是所有汉字的集合";

	public Ika_toolbox() {

		this.setSize(400,240); // 将窗体的大小
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setResizable(false); // 窗体不能改变大小
		this.setTitle("字库工具 "+myversion); // 设置标题
		// help
		jMenuHelp.add(jMenuHelpHelp);
		jMenuHelp.add(jMenuHelpAbout);
		jMenuHelp.add(jMenuFileExit);
		jMenuHelpHelp.addActionListener(this);
		jMenuHelpAbout.addActionListener(this);
		jMenuFileExit.addActionListener(this);
		jMenuBar1.add(jMenuHelp);
		this.setJMenuBar(jMenuBar1);

		Container contents = getContentPane();
		jtp = new JTabbedPane(JTabbedPane.TOP);
		jtp.addTab("HZlib", lib);
		contents.add(jtp);
		setVisible(true);
	}
	   HZlib lib=new HZlib();
	public static void main(String args[]) {
	SDef.setMySkin(3);
		new Ika_toolbox();
	}
}
