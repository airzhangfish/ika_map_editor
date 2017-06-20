package horse_creator;
import java.awt.Container;
import javax.swing.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

/**
 *  《流金岁月》赛马创建计算器
 * @author airzhangfish
 *
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
	private String aboutStr = "《流金岁月》赛马创建计算器\n  " + "Creator by airzhangfish \n " + " Version V0.03A in 2007-11-28 \n "
			+ " E-mail&MSN:airzhangfish@hotmail.com";

	// 帮助
	private String helpStr = "《流金岁月》赛马创建计算器   使用帮助\n";

	public Ika_toolbox() {

		this.setSize(400,240); // 将窗体的大小
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setResizable(false); // 窗体不能改变大小
		this.setTitle("《流金岁月》赛马创建计算器 "+myversion); // 设置标题

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
		jtp.addTab("赛马创建计算器",javachanger);
		jtp.addTab("比赛结果预测器",race);
		contents.add(jtp);
		setVisible(true);
	}
	JAVAchanger javachanger=new JAVAchanger();
	raceresult race=new raceresult();
	public static void main(String args[]) {
	SDef.setMySkin(2);
		new Ika_toolbox();
	}
}
