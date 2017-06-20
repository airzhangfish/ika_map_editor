package horse_creator;
import java.awt.Choice;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

import javax.swing.JButton;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.tree.DefaultMutableTreeNode;

public class who_win extends JFrame implements ActionListener {

	private static final long serialVersionUID = 1L;
	JFormattedTextField[] winner;
	JButton buttonOK = new JButton("确定");
	Choice talkman = new Choice();
	int horse_number = 2;

	public who_win(int number) {
		horse_number = number;
		this.setTitle("信息统计");
		this.setSize(200, 60 + 30 * number);
		this.setAlwaysOnTop(true);
		this.setResizable(false); // 窗体不能改变大小
		this.setLayout(new GridLayout(number + 2, 2));

		this.add(new JLabel("名次"));
		this.add(new JLabel("获胜马匹"));

		// 计算数据
		Random ram=new Random();
	int[] winne=new int[number];
	int[][] tmp=new int[number][2];
for(int i=0;i<number;i++){
	tmp[i][0]=i;
	tmp[i][1]=raceresult.whowin[i][2];
}
//开始计算
for(int i=0;i<number;i++){
	int total=0;

	for(int j=0;j<number;j++){
		total=total+tmp[j][1];
	}
	int point=((Math.abs(ram.nextInt()))%total)+1;
	for(int j=0;j<number;j++){
		int perfr=0;
		for(int k=0;k<j+1;k++){
			perfr=perfr+tmp[k][1];
		}
	if(point<=perfr){
		//确定是这个，抽出，保存
		winne[i]=tmp[j][0];//名次保存
		tmp[j][1]=0;
		break;
	}
	}
}

		// 计算数据结束

		winner = new JFormattedTextField[number];
		for (int i = 0; i < number; i++) {
			winner[i] = new JFormattedTextField((winne[i]+1)+"号马 (" + raceresult.whowin[winne[i]][2] + "%)");
			winner[i].setEditable(false);
			this.add(new JLabel("第" + (i + 1) + "名"));
			this.add(winner[i]);
		}

		this.add(new JLabel(""));
		this.add(buttonOK);

		buttonOK.addActionListener(this);
		setVisible(true);
	}

	
	/*
	 * 输出马匹的比赛胜率，计算出比赛结果
	 */
	Random ram=new Random();	
	public int[] result_winner(int[] permatch){
		// 计算数据
	int[] winne=new int[permatch.length];
	int[][] tmp=new int[permatch.length][2];
for(int i=0;i<permatch.length;i++){
	tmp[i][0]=i;
	tmp[i][1]=permatch[i];
}
//开始计算
for(int i=0;i<permatch.length;i++){
	int total=0;

	for(int j=0;j<permatch.length;j++){
		total=total+tmp[j][1];
	}
	int point=((Math.abs(ram.nextInt()))%total)+1;
	for(int j=0;j<permatch.length;j++){
		int perfr=0;
		for(int k=0;k<j+1;k++){
			perfr=perfr+tmp[k][1];
		}
	if(point<=perfr){
		//确定是这个，抽出，保存
		winne[i]=tmp[j][0];//名次保存
		tmp[j][1]=0;
		break;
	}
	}
}
return winne;
	}
	
	
	
	
	
	public void actionPerformed(ActionEvent arg0) {
		Object obj = arg0.getSource();
		if (obj == buttonOK) {
			try {
				setVisible(false);
			} catch (Exception e) {
				JOptionPane.showMessageDialog(null, "数据不正确", "出错", JOptionPane.ERROR_MESSAGE);
			}
		}
	}

}
