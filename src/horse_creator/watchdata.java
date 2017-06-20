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


public class watchdata  extends JFrame implements ActionListener {



	private static final long serialVersionUID = 1L;
	JFormattedTextField[] perwin ;
	JFormattedTextField[] matchwin ;
	JFormattedTextField[] lost ;
	JButton buttonOK = new JButton("确定");
	 Choice  talkman   =   new   Choice();   
int horse_number=2;
		
	public watchdata(int number) {
		horse_number=number;
		this.setTitle("信息统计");
		this.setSize(500, 60+30*number);
		this.setAlwaysOnTop(true);
		this.setResizable(false); // 窗体不能改变大小
		this.setLayout(new GridLayout(number+2,4));
		
		this.add(new JLabel("赛马编号"));
		this.add(new JLabel("个人胜率"));
		this.add(new JLabel("个人赔率"));
		this.add(new JLabel("当场比赛胜率"));

		perwin =new JFormattedTextField[number];
		matchwin =new JFormattedTextField[number];
		lost =new JFormattedTextField[number];
		for(int i=0;i<number;i++){
		//统计
		raceresult.whowin[i][0]=winit(raceresult.race_info[i]);
		raceresult.whowin[i][1]=lostit(raceresult.race_info[i]);
		raceresult.whowin[i][2]=1;
		
		//统计完毕	
			perwin[i]=new JFormattedTextField(""+raceresult.whowin[i][0]+"%");
			lost[i]=new JFormattedTextField("1:"+(float)(raceresult.whowin[i][1]/100));
			matchwin[i]=new JFormattedTextField(""+raceresult.whowin[i][2]);

			perwin[i].setEditable(false);
			matchwin[i].setEditable(false);
			lost[i].setEditable(false);
			this.add(new JLabel((i+1)+"号"));
			this.add(perwin[i]);
			this.add(lost[i]);
			this.add(matchwin[i]);
		}
		
		
		
		
		int total=0;
		for(int i=0;i<number;i++){
			total=total+raceresult.whowin[i][0];
		}

		for(int i=0;i<number;i++){
			raceresult.whowin[i][2]=(raceresult.whowin[i][0]*100)/total;
			matchwin[i].setText(raceresult.whowin[i][2]+"%");
		}
		
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
			setVisible(false);
			}catch(Exception e){
				JOptionPane.showMessageDialog(null, "数据不正确", "出错", JOptionPane.ERROR_MESSAGE);
			}
		}
	}
	
	
	
	public int winit(int[] info){
		int[] fin=new int[3];
	    	fin[0]=info[0];
		    fin[1]=info[1];
		    fin[2]=info[2];
		if(info[3]<50){
			fin[0]=fin[0]-5;
			fin[2]=fin[2]-5;
		}else if(info[3]>=90){
			fin[0]=fin[0]+5;
			fin[2]=fin[2]+5;	
		}	
			
		if(info[4]<50){
			fin[1]=fin[1]-5;
		}else if(info[4]>=90){
			fin[1]=fin[1]+5;
		}			
	
		if(info[5]<50){
			fin[0]=fin[0]-10;
			fin[1]=fin[1]-10;
			fin[2]=fin[2]-10;
		}else  if(info[5]>=90){
			fin[0]=fin[0]-5;
			fin[1]=fin[1]+5;
			fin[2]=fin[2]+2;	
		}
			int win=10+((fin[0]+fin[1]+fin[2]-200)/10);
		return win;
	}

	public int lostit(int[] info){
		int[] fin=new int[3];
		fin[0]=info[0];
		    fin[1]=info[1];
		    fin[2]=info[2];
		if(info[3]<50){
			fin[0]=fin[0]-5;
			fin[2]=fin[2]-5;
		}else if(info[3]>=90){
			fin[0]=fin[0]+5;
			fin[2]=fin[2]+5;	
		}	
			
		if(info[4]<50){
			fin[1]=fin[1]-5;
		}else if(info[4]>=90){
			fin[1]=fin[1]+5;
		}			
	
		if(info[5]<50){
			fin[0]=fin[0]-10;
			fin[1]=fin[1]-10;
			fin[2]=fin[2]-10;
		}else  if(info[5]>=90){
			fin[0]=fin[0]-5;
			fin[1]=fin[1]+5;
			fin[2]=fin[2]+2;	
		}
			float lst=40/(((float)fin[0]+(float)fin[1]+(float)fin[2])*3/100);
			return (int)(lst*100);
	}
	
	
	
	
	
	/*
	 * 输入马匹的相关属性，输出马匹的胜率%
	 */
	public int result_win(int speed,int stamina,int burst,int satis,int loyalty,int bellyful){
		int[] fin=new int[3];
	    	fin[0]=speed;
		    fin[1]=stamina;
		    fin[2]=burst;
		if(satis<50){
			fin[0]=fin[0]-5;
			fin[2]=fin[2]-5;
		}else if(satis>=90){
			fin[0]=fin[0]+5;
			fin[2]=fin[2]+5;	
		}	
			
		if(loyalty<50){
			fin[1]=fin[1]-5;
		}else if(loyalty>=90){
			fin[1]=fin[1]+5;
		}			
	
		if(bellyful<50){
			fin[0]=fin[0]-10;
			fin[1]=fin[1]-10;
			fin[2]=fin[2]-10;
		}else  if(bellyful>=90){
			fin[0]=fin[0]-5;
			fin[1]=fin[1]+5;
			fin[2]=fin[2]+2;	
		}
			int win=10+((fin[0]+fin[1]+fin[2]-200)/10);
		return win;
	}
	
	
	
	/*
	 * 输入马匹的相关属性，输出马匹的赔率%
	 */
	public int result_lost(int speed,int stamina,int burst,int satis,int loyalty,int bellyful){
		int[] fin=new int[3];
	    	fin[0]=speed;
		    fin[1]=stamina;
		    fin[2]=burst;
		if(satis<50){
			fin[0]=fin[0]-5;
			fin[2]=fin[2]-5;
		}else if(satis>=90){
			fin[0]=fin[0]+5;
			fin[2]=fin[2]+5;	
		}	
			
		if(loyalty<50){
			fin[1]=fin[1]-5;
		}else if(loyalty>=90){
			fin[1]=fin[1]+5;
		}			
	
		if(bellyful<50){
			fin[0]=fin[0]-10;
			fin[1]=fin[1]-10;
			fin[2]=fin[2]-10;
		}else  if(bellyful>=90){
			fin[0]=fin[0]-5;
			fin[1]=fin[1]+5;
			fin[2]=fin[2]+2;	
		}
		float lst=40/(((float)fin[0]+(float)fin[1]+(float)fin[2])*3/100);
		return (int)(lst*100);
	}
	
	/*
	 * 输入马匹的个人胜率属性数组，输出马匹的比赛属性数组
	 * 有几匹马参加比赛就有就有几个数组元素
	 */
	public int[] result_permatch(int[] perwin){
		int total=0;
		int[] permatch=new int[perwin.length];
		for(int i=0;i<perwin.length;i++){
			total=total+perwin[i];
		}
		for(int i=0;i<perwin.length;i++){
			permatch[i]=(perwin[i]*100)/total;
		}
			return permatch;
	}

}
