package horse_creator;
import java.awt.*;
import java.awt.event.*;

import javax.swing.*;

public class raceresult extends JPanel implements ActionListener {

	private static final long serialVersionUID = 1L;

	public static int[][] race_info = new int[10][7];
	public static int[][] whowin = new int[10][3];
	Choice players = new Choice();
	public final static String[] playmatch = { "二人赛", "三人赛", "四人赛", "五人赛", "六人赛", "七人赛", "八人赛", "九人赛", "十人赛" };
	private static JButton create = new JButton("建立比赛");
	private static JButton watchdata = new JButton("数据统计");
	private static JButton result = new JButton("比赛结果");
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

		// 第1行
		this.add(new JLabel("比赛结果预测"));
		this.add(new JLabel(""));
		this.add(new JLabel(""));
		this.add(new JLabel(""));

		// 第2行
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
