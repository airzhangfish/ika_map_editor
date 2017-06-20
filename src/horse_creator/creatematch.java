package horse_creator;

import java.awt.Choice;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.tree.DefaultMutableTreeNode;


public class creatematch  extends JFrame implements ActionListener {



	private static final long serialVersionUID = 1L;
	JFormattedTextField[] speed ;
	JFormattedTextField[] naili ;
	JFormattedTextField[] baofali ;
	JFormattedTextField[] manyi ;
	JFormattedTextField[] zhongcheng ;
	JFormattedTextField[] manfu ;
	JButton buttonOK = new JButton("确定");
	 Choice  talkman   =   new   Choice();   
int horse_number=2;
		
	public creatematch(int number) {
		horse_number=number;
		this.setTitle("马匹信息输入");
		this.setSize(500, 60+30*number);
		this.setAlwaysOnTop(true);
		this.setResizable(false); // 窗体不能改变大小
		this.setLayout(new GridLayout(2+number, 7));
		
		this.add(new JLabel("马编号"));
		this.add(new JLabel("速度"));
		this.add(new JLabel("耐力"));
		this.add(new JLabel("爆发力"));
		this.add(new JLabel("满意度"));
		this.add(new JLabel("忠诚度"));
		this.add(new JLabel("满腹度"));
		
		speed =new JFormattedTextField[number];
		naili =new JFormattedTextField[number];
		baofali =new JFormattedTextField[number];
		manyi =new JFormattedTextField[number];
		zhongcheng =new JFormattedTextField[number];
		manfu =new JFormattedTextField[number];
		
		for(int i=0;i<number;i++){
			speed[i]=new JFormattedTextField(""+raceresult.race_info[i][0]);
			naili[i]=new JFormattedTextField(""+raceresult.race_info[i][1]);
			baofali[i]=new JFormattedTextField(""+raceresult.race_info[i][2]);
			manyi[i]=new JFormattedTextField(""+raceresult.race_info[i][3]);
			zhongcheng[i]=new JFormattedTextField(""+raceresult.race_info[i][4]);
			manfu[i]=new JFormattedTextField(""+raceresult.race_info[i][5]);
			this.add(new JLabel((i+1)+"号"));
			this.add(speed[i]);
			this.add(naili[i]);
			this.add(baofali[i]);
			this.add(manyi[i]);
			this.add(zhongcheng[i]);
			this.add(manfu[i]);
		}
		this.add(new JLabel(""));
		this.add(new JLabel(""));
		this.add(new JLabel(""));
		this.add(new JLabel(""));
		this.add(new JLabel(""));
		this.add(buttonOK);
		this.add(new JLabel(""));
		buttonOK.addActionListener(this);
		setVisible(true);
	}

	public void actionPerformed(ActionEvent arg0) {
		Object obj = arg0.getSource();
		if (obj == buttonOK) {
			try{
			for(int i=0;i<horse_number;i++){
				raceresult.race_info[i][0]=Integer.parseInt(speed[i].getText());
				raceresult.race_info[i][1]=Integer.parseInt(naili[i].getText());
				raceresult.race_info[i][2]=Integer.parseInt(baofali[i].getText());
				raceresult.race_info[i][3]=Integer.parseInt(manyi[i].getText());
				raceresult.race_info[i][4]=Integer.parseInt(zhongcheng[i].getText());
				raceresult.race_info[i][5]=Integer.parseInt(manfu[i].getText());
			}
			setVisible(false);
			}catch(Exception e){
				JOptionPane.showMessageDialog(null, "数据不正确", "出错", JOptionPane.ERROR_MESSAGE);
			}
		}
	}
}
