package ika_avg_maker;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JPanel;

public class sc_talk extends JPanel implements ActionListener {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	JButton talk_add = new JButton("@��ӶԻ�@");
	JButton talk_txt = new JButton("�Ի�");
	JButton talk_person1 = new JButton("����1");
	JButton talk_person2 = new JButton("����2");
	JButton talk_bg = new JButton("����");
	JButton talk_talkbox = new JButton("�Ի���");
	JButton talk_sound = new JButton("����");
	JButton talk_jump = new JButton("�Ի���ת�趨");

	public sc_talk() {
		this.setPreferredSize(new Dimension(200, 400));
		this.setLayout(new GridLayout(10, 2));
		this.add(talk_add);
		this.add(talk_txt);
		this.add(talk_person1);
		this.add(talk_person2);
		this.add(talk_bg);
		this.add(talk_sound);
		this.add(talk_talkbox);
		this.add(talk_jump);
		talk_add.addActionListener(this);
		talk_txt.addActionListener(this);
		talk_person1.addActionListener(this);
		talk_person2.addActionListener(this);
		talk_bg.addActionListener(this);
		talk_sound.addActionListener(this);
		talk_talkbox.addActionListener(this);
		talk_jump.addActionListener(this);
	}



	public void actionPerformed(ActionEvent e) {
		Object source = e.getSource();
		if (source == talk_add) {
			// ��õ�ǰ�еĲ���������һ�����һ�д���
			txt_panel.addtxt("\n�Ի�:");
		}
		if (source == talk_txt) {
			txt_panel.addtxt("</><//>");
		}
		if (source == talk_person1) {
			txt_panel.addtxt("����1<id,x,y>");
		}
		if (source == talk_person2) {
			txt_panel.addtxt("����2<id,x,y>");
		}
		if (source == talk_bg) {
			txt_panel.addtxt("����<id,x,y>");
		}
		if (source == talk_talkbox) {
			txt_panel.addtxt("��<id,x,y>");
		}
		if (source == talk_sound) {
			txt_panel.addtxt("����<id>");
		}
		if (source == talk_jump) {
			txt_panel.addtxt("�Ի�<x,y,color,speed,�´���תID,�ı�ID>");
		}
	}
}