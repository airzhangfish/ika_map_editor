package ika_avg_maker;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JPanel;

public class sc_select extends JPanel implements ActionListener {

	private static final long serialVersionUID = 1L;
	JButton seclet_add = new JButton("@���ѡ��@");
	JButton seclet_seclet2 = new JButton("����");
	JButton seclet_seclet3 = new JButton("����");
	JButton seclet_seclet4 = new JButton("����");

	public sc_select() {
		this.setPreferredSize(new Dimension(200, 400));
		this.setLayout(new GridLayout(10, 2));
		this.add(seclet_add);
		this.add(seclet_seclet2);
		this.add(seclet_seclet3);
		this.add(seclet_seclet4);
		seclet_add.addActionListener(this);
		seclet_seclet2.addActionListener(this);
		seclet_seclet3.addActionListener(this);
		seclet_seclet4.addActionListener(this);
	}

	public void actionPerformed(ActionEvent e) {
		Object source = e.getSource();
		if (source == seclet_add) {
			// ��õ�ǰ�еĲ���������һ�����һ�д���
			txt_panel.addtxt("\nѡ��:");
		}
		if (source == seclet_seclet2) {
			txt_panel.addtxt("<2,��תA,��תB></>Aѡ��|Bѡ��<//>");
		}
		if (source == seclet_seclet3) {
			txt_panel.addtxt("<3,��תA,��תB,��תC></>Aѡ��|Bѡ��|Cѡ��<//>");
		}
		if (source == seclet_seclet4) {
			txt_panel.addtxt("<4,��תA,��תB,��תC,��תD></>Aѡ��|Bѡ��|Cѡ��|Dѡ��<//>");
		}
	}
}