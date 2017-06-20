package ika_avg_maker;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JPanel;

public class sc_select extends JPanel implements ActionListener {

	private static final long serialVersionUID = 1L;
	JButton seclet_add = new JButton("@添加选择@");
	JButton seclet_seclet2 = new JButton("二择");
	JButton seclet_seclet3 = new JButton("三择");
	JButton seclet_seclet4 = new JButton("四择");

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
			// 获得当前行的参数，再下一行添加一行代码
			txt_panel.addtxt("\n选择:");
		}
		if (source == seclet_seclet2) {
			txt_panel.addtxt("<2,跳转A,跳转B></>A选择|B选择<//>");
		}
		if (source == seclet_seclet3) {
			txt_panel.addtxt("<3,跳转A,跳转B,跳转C></>A选择|B选择|C选择<//>");
		}
		if (source == seclet_seclet4) {
			txt_panel.addtxt("<4,跳转A,跳转B,跳转C,跳转D></>A选择|B选择|C选择|D选择<//>");
		}
	}
}