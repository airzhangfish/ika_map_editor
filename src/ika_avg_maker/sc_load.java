package ika_avg_maker;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JPanel;

public class sc_load extends JPanel  implements ActionListener {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	JButton load_add = new JButton("@�������@");
	JButton load_bg = new JButton("����");
	JButton load_talkbox = new JButton("�Ի���");
	JButton load_person = new JButton("����");
	JButton load_txt = new JButton("�ı�");
	JButton load_sound = new JButton("����");

	public sc_load() {
		this.setPreferredSize(new Dimension(200, 400));
		this.setLayout(new GridLayout(10, 2));
		this.add(load_add);
		this.add(load_bg);
		this.add(load_talkbox);
		this.add(load_person);
		this.add(load_txt);
		this.add(load_sound);
		load_add.addActionListener(this);
		load_bg.addActionListener(this);
		load_talkbox.addActionListener(this);
		load_person.addActionListener(this);
		load_txt.addActionListener(this);
		load_sound.addActionListener(this);
	}

	public void actionPerformed(ActionEvent e) {
		Object source = e.getSource();
		if (source == load_add) {
			// ��õ�ǰ�еĲ���������һ�����һ�д���
			txt_panel.addtxt("\n����:");
		}
		if (source == load_bg) {
			txt_panel.addtxt("����<>");
		}
		if (source == load_talkbox) {
			txt_panel.addtxt("��<>");
		}
		if (source == load_person) {
			txt_panel.addtxt("����<>");
		}
		if (source == load_txt) {
			txt_panel.addtxt("�ı�<�ı�ID,���ID>");
		}
		if (source == load_sound) {
			txt_panel.addtxt("����<>");
		}
	}
	
	
	
}