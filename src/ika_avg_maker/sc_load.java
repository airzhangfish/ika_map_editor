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
	JButton load_add = new JButton("@添加载入@");
	JButton load_bg = new JButton("背景");
	JButton load_talkbox = new JButton("对话框");
	JButton load_person = new JButton("人物");
	JButton load_txt = new JButton("文本");
	JButton load_sound = new JButton("声音");

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
			// 获得当前行的参数，再下一行添加一行代码
			txt_panel.addtxt("\n载入:");
		}
		if (source == load_bg) {
			txt_panel.addtxt("背景<>");
		}
		if (source == load_talkbox) {
			txt_panel.addtxt("框<>");
		}
		if (source == load_person) {
			txt_panel.addtxt("人物<>");
		}
		if (source == load_txt) {
			txt_panel.addtxt("文本<文本ID,语句ID>");
		}
		if (source == load_sound) {
			txt_panel.addtxt("声音<>");
		}
	}
	
	
	
}