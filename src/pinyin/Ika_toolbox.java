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
	private String aboutStr = " �ֿ⹤�� by airzhangfish";

	// ����
	private String helpStr = "����CSV�ֿ�,Ȼ�������Զ�\n���ļ�Ŀ¼������2���ļ�,һ��Ϊindex.ime�����ļ�,\nһ��Ϊlib.ime�ֿ��ļ�\n�����ļ�Ϊ��A��Z,ÿ��ƴ���������е��ֵ�����\n��lib�ļ��������к��ֵļ���";

	public Ika_toolbox() {

		this.setSize(400,240); // ������Ĵ�С
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setResizable(false); // ���岻�ܸı��С
		this.setTitle("�ֿ⹤�� "+myversion); // ���ñ���
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
