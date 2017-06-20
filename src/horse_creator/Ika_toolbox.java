package horse_creator;
import java.awt.Container;
import javax.swing.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

/**
 *  ���������¡�������������
 * @author airzhangfish
 *
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
	private String aboutStr = "���������¡�������������\n  " + "Creator by airzhangfish \n " + " Version V0.03A in 2007-11-28 \n "
			+ " E-mail&MSN:airzhangfish@hotmail.com";

	// ����
	private String helpStr = "���������¡�������������   ʹ�ð���\n";

	public Ika_toolbox() {

		this.setSize(400,240); // ������Ĵ�С
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setResizable(false); // ���岻�ܸı��С
		this.setTitle("���������¡������������� "+myversion); // ���ñ���

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
		jtp.addTab("������������",javachanger);
		jtp.addTab("�������Ԥ����",race);
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
