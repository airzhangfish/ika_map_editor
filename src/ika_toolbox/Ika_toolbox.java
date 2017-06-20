package ika_toolbox;
import java.awt.Container;
import javax.swing.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

/**
 * @Title:ika toolbox 触手工具箱
 * @Description: 内置手机游戏图片数据包破解，图片数据打包，变换JAVA文件的数据，转换2进制数据，像素字体变换
 * @Copyright: airzhangfish Copyright (c) 2007
 * @blog: http://airzhangfish.spaces.live.com
 * @Company: Comicfishing.com
 * @author airzhangfish
 * @version 0.01 standard  last updata 2007-10-8
 * 主类
 */

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
	private String aboutStr = " IKA  toolbox 触手工具箱\n  " + "Creator by airzhangfish \n " + " Version V0.03A in 2007-10-8 \n "
			+ " E-mail&MSN:airzhangfish@hotmail.com";

	// 帮助
	private String helpStr = "IKA  toolbox 触手工具箱   使用帮助\n";

	public Ika_toolbox() {

		this.setSize(400,240); // 将窗体的大小
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setResizable(false); // 窗体不能改变大小
		this.setTitle("IKA  toolbox 触手工具箱 "+myversion); // 设置标题
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
		jtp.addTab("PNG hacker", pnghacker);
		jtp.addTab("PIC packer", picpaker);
//		jtp.addTab("JAVA changer",javachanger);
		jtp.addTab("HZlib", lib);
//		jtp.addTab("word pixer", new JPanel());
		contents.add(jtp);
		setVisible(true);
	}
	   HZlib lib=new HZlib();
//	JAVAchanger javachanger=new JAVAchanger();
	PNGhacker pnghacker=new PNGhacker();
	PICpaker picpaker=new PICpaker();
	public static void main(String args[]) {
	SDef.setMySkin(3);
		new Ika_toolbox();
	}
}
