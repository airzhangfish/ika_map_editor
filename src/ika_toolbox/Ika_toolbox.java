package ika_toolbox;
import java.awt.Container;
import javax.swing.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

/**
 * @Title:ika toolbox ���ֹ�����
 * @Description: �����ֻ���ϷͼƬ���ݰ��ƽ⣬ͼƬ���ݴ�����任JAVA�ļ������ݣ�ת��2�������ݣ���������任
 * @Copyright: airzhangfish Copyright (c) 2007
 * @blog: http://airzhangfish.spaces.live.com
 * @Company: Comicfishing.com
 * @author airzhangfish
 * @version 0.01 standard  last updata 2007-10-8
 * ����
 */

public class Ika_toolbox extends JFrame implements ActionListener {
	private static final long serialVersionUID = 1L;
	private static final String myversion = "0.01standard ";
	private JTabbedPane jtp;
	private JMenuBar jMenuBar1 = new JMenuBar();
	private JMenu jMenuHelp = new JMenu("˵��");
	private JMenuItem jMenuHelpAbout = new JMenuItem("����");
	private JMenuItem jMenuHelpHelp = new JMenuItem("����");
	private JMenuItem jMenuFileExit = new JMenuItem("�˳�");

	public void actionPerformed(ActionEvent actionEvent) {
		Object source = actionEvent.getSource();
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

	}

	// ����
	private String aboutStr = " IKA  toolbox ���ֹ�����\n  " + "Creator by airzhangfish \n " + " Version V0.03A in 2007-10-8 \n "
			+ " E-mail&MSN:airzhangfish@hotmail.com";

	// ����
	private String helpStr = "IKA  toolbox ���ֹ�����   ʹ�ð���\n";

	public Ika_toolbox() {

		this.setSize(400,240); // ������Ĵ�С
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setResizable(false); // ���岻�ܸı��С
		this.setTitle("IKA  toolbox ���ֹ����� "+myversion); // ���ñ���
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
