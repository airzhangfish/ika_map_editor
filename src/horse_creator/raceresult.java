package horse_creator;
import java.awt.*;
import java.awt.event.*;

import javax.swing.*;

public class raceresult extends JPanel implements ActionListener {

	private static final long serialVersionUID = 1L;

	public static int[][] race_info = new int[10][7];
	public static int[][] whowin = new int[10][3];
	Choice players = new Choice();
	public final static String[] playmatch = { "������", "������", "������", "������", "������", "������", "������", "������", "ʮ����" };
	private static JButton create = new JButton("��������");
	private static JButton watchdata = new JButton("����ͳ��");
	private static JButton result = new JButton("�������");
	ButtonGroup group = new ButtonGroup();

	public raceresult() {

		for (int i = 0; i < race_info.length; i++) {
			race_info[i][0] = 70;
			race_info[i][1] = 70;
			race_info[i][2] = 70;
			race_info[i][3] = 0;
			race_info[i][4] = 0;
			race_info[i][5] = 0;
		}

		this.setLayout(new GridLayout(6, 4));
		for (int i = 0; i < playmatch.length; i++) {
			players.add(playmatch[i]);
		}

		// ��1��
		this.add(new JLabel("�������Ԥ��"));
		this.add(new JLabel(""));
		this.add(new JLabel(""));
		this.add(new JLabel(""));

		// ��2��
		this.add(players);
		this.add(create);
		this.add(watchdata);
		this.add(result);
		create.addActionListener(this);
		watchdata.addActionListener(this);
		result.addActionListener(this);
	}

	public void actionPerformed(ActionEvent arg0) {
		Object obj = arg0.getSource();
		if (obj == create) {
			new creatematch(players.getSelectedIndex() + 2);
		}
		if (obj == watchdata) {
         new watchdata(players.getSelectedIndex() + 2);
		}
		if (obj == result) {
       new who_win(players.getSelectedIndex() + 2);
		}
	}

}
