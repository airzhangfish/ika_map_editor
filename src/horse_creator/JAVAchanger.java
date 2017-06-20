package horse_creator;
import java.awt.*;
import java.awt.event.*;

import javax.swing.*;

public class JAVAchanger extends JPanel implements ActionListener {

	private static final long serialVersionUID = 1L;

	JFormattedTextField txt_speed = new JFormattedTextField("70");
	JFormattedTextField txt_naili = new JFormattedTextField("70");
	JFormattedTextField txt_baofali = new JFormattedTextField("70");
	JFormattedTextField txt_manyi = new JFormattedTextField("50");
	JFormattedTextField txt_zhongcheng = new JFormattedTextField("50");
	JFormattedTextField txt_manfu = new JFormattedTextField("50");
	JFormattedTextField txt_horse_buy = new JFormattedTextField("5000");
	JFormattedTextField txt_money = new JFormattedTextField("10000");

	JFormattedTextField txt_win = new JFormattedTextField("");
	JFormattedTextField txt_lst = new JFormattedTextField("");
	JFormattedTextField txt_sell = new JFormattedTextField("");
	JFormattedTextField txt_tiaojiao = new JFormattedTextField("0");
	JFormattedTextField txt_weishi = new JFormattedTextField("0");
	JFormattedTextField txt_qingxi = new JFormattedTextField("0");
	
	JFormattedTextField txt_fjspeed = new JFormattedTextField("-15");
	JFormattedTextField txt_fjnaili = new JFormattedTextField("-15");
	JFormattedTextField txt_fjbaofali = new JFormattedTextField("-15");

	private static JButton jisuan = new JButton("����");
	private static JButton tiaojiao = new JButton("����");
	private static JButton weishi = new JButton("ιʳ");
	private static JButton qingxi = new JButton("��ϴ");

	ButtonGroup group = new ButtonGroup();

	public JAVAchanger() {
		this.setLayout(new GridLayout(8, 6));

		// ��1��
		this.add(new JLabel("��ƥ����"));
		this.add(new JLabel(""));
		this.add(new JLabel(""));
		this.add(new JLabel(""));
		this.add(new JLabel(""));
		this.add(new JLabel(""));
		// ��2��
		this.add(new JLabel("�ٶ�"));
		this.add(txt_speed);
		this.add(new JLabel("����"));
		this.add(txt_naili);
		this.add(new JLabel("������"));
		this.add(txt_baofali);

		// ��3��
		this.add(new JLabel("�����"));
		this.add(txt_manyi);
		this.add(new JLabel("�ҳ϶�"));
		this.add(txt_zhongcheng);
		this.add(new JLabel("������"));
		this.add(txt_manfu);

		// ��4��
		this.add(new JLabel(""));
		this.add(new JLabel(""));
		this.add(new JLabel("�����"));
		this.add(txt_horse_buy);
		this.add(new JLabel("�����"));
		this.add(txt_money);

		// ��5��
		this.add(new JLabel(""));
		this.add(jisuan);
		this.add(new JLabel(""));
		this.add(tiaojiao);
		this.add(weishi);
		this.add(qingxi);
		jisuan.addActionListener(this);
		tiaojiao.addActionListener(this);
		weishi.addActionListener(this);
		qingxi.addActionListener(this);
		// ��6��
		this.add(new JLabel("ʤ��"));
		this.add(txt_win);
		txt_win.setEditable(false);
		this.add(new JLabel("����"));
		this.add(txt_lst);
		txt_lst.setEditable(false);
		this.add(new JLabel("������"));
		this.add(txt_sell);
		txt_sell.setEditable(false);

		// ��7��
		this.add(new JLabel("���̴���"));
		this.add(txt_tiaojiao);
		txt_tiaojiao.setEditable(false);
		this.add(new JLabel("ιʳ����"));
		this.add(txt_weishi);
		txt_weishi.setEditable(false);
		this.add(new JLabel("��ϴ����"));
		this.add(txt_qingxi);
		txt_qingxi.setEditable(false);
		
		// ��8��
		this.add(new JLabel("�����ٶ�"));
		this.add(txt_fjspeed);
		txt_fjspeed.setEditable(false);
		this.add(new JLabel("��������"));
		this.add(txt_fjnaili);
		txt_fjnaili.setEditable(false);
		this.add(new JLabel("��������"));
		this.add(txt_fjbaofali);
		txt_fjbaofali.setEditable(false);		
		
		
		
		
		

	}

	

	
	
	
	
	
	
	
	
	public void actionPerformed(ActionEvent arg0) {
		Object obj = arg0.getSource();
		if (obj == jisuan) {
			try {
				//��������������
				int speed=Integer.parseInt(txt_speed.getText());
				int naili=Integer.parseInt(txt_naili.getText());
				int baofali=Integer.parseInt(txt_baofali.getText());
				int manyi=Integer.parseInt(txt_manyi.getText());
				int zhongcheng=Integer.parseInt(txt_zhongcheng.getText());
				int manfu=Integer.parseInt(txt_manfu.getText());
				int horse_buy=Integer.parseInt(txt_horse_buy.getText());
				int money=Integer.parseInt(txt_money.getText());
				
				
				
				
				//����
				
				
				//ʤ��
				
				
                //������
				
				
				
				
				
				
				
				
				int fjspeed=0;
				int fjnaili=0;
				int fjbaofali=0;	
				
		
			if(manyi<50){
				fjspeed=fjspeed-5;
				fjbaofali=fjbaofali-5;
			}else if(manyi>=90){
				fjspeed=fjspeed+5;
				fjbaofali=fjbaofali+5;	
			}	
				
			if(zhongcheng<50){
				fjnaili=fjnaili-5;
			}else if(zhongcheng>=90){
				fjnaili=fjnaili+5;
			}			
		
			if(manfu<50){
				fjspeed=fjspeed-10;
				fjnaili=fjnaili-10;
				fjbaofali=fjbaofali-10;
			}else  if(manfu>=90){
				fjspeed=fjspeed-5;
				fjnaili=fjnaili+5;
				fjbaofali=fjbaofali+2;	
			}
			
				int win=10+((speed+fjspeed+naili+fjnaili+baofali+fjbaofali-200)/10);
				float lst=40/(((float)speed+(float)fjspeed+(float)naili+(float)fjnaili+(float)baofali+(float)fjbaofali)*3/100);
				System.out.println("lst:"+lst);
				String txtst1=lst+"";
				int sell=(int)(((float)horse_buy/2)+((float)horse_buy/2)*(((float)manyi+(float)zhongcheng+(float)manfu)/300));
				txt_win.setText(win+"%");
				txt_lst.setText("1:"+txtst1.substring(0, 3));
				txt_sell.setText(sell+"");
				
				
				txt_fjspeed.setText(fjspeed+"");
				txt_fjnaili.setText(fjnaili+"");
				txt_fjbaofali.setText(fjbaofali+"");
				
			} catch (Exception e) {
				JOptionPane.showMessageDialog(null, "���ݲ���ȷ", "����", JOptionPane.ERROR_MESSAGE);
			}
		}

		if (obj == tiaojiao) {
			try {
				// ����
				int speed=Integer.parseInt(txt_speed.getText());
				int naili=Integer.parseInt(txt_naili.getText());
				int baofali=Integer.parseInt(txt_baofali.getText());
				int manyi=Integer.parseInt(txt_manyi.getText());
				int zhongcheng=Integer.parseInt(txt_zhongcheng.getText());
				int manfu=Integer.parseInt(txt_manfu.getText());
				int horse_buy=Integer.parseInt(txt_horse_buy.getText());
				int money=Integer.parseInt(txt_money.getText());
				
				int inttiaojiao=Integer.parseInt(txt_tiaojiao.getText());
				int intweishi=Integer.parseInt(txt_weishi.getText());
				int intqingxi=Integer.parseInt(txt_qingxi.getText());			
				
				txt_tiaojiao.setText(inttiaojiao+1+"");
				txt_manyi.setText(manyi-3+"");
				txt_zhongcheng.setText(zhongcheng+3+"");
				txt_manfu.setText(manfu-3+"");
				txt_money.setText(money-100+"");
				
				int fjspeed=0;
				int fjnaili=0;
				int fjbaofali=0;	
				
				
				if(manyi<50){
					fjspeed=fjspeed-5;
					fjbaofali=fjbaofali-5;
				}else if(manyi>=90){
					fjspeed=fjspeed+5;
					fjbaofali=fjbaofali+5;	
				}	
					
				if(zhongcheng<50){
					fjnaili=fjnaili-5;
				}else if(zhongcheng>=90){
					fjnaili=fjnaili+5;
				}			
			
				if(manfu<50){
					fjspeed=fjspeed-10;
					fjnaili=fjnaili-10;
					fjbaofali=fjbaofali-10;
				}else  if(manfu>=90){
					fjspeed=fjspeed-5;
					fjnaili=fjnaili+5;
					fjbaofali=fjbaofali+2;	
				}
			
				int win=10+((speed+fjspeed+naili+fjnaili+baofali+fjbaofali-200)/10);
				float lst=40/(((float)speed+(float)fjspeed+(float)naili+(float)fjnaili+(float)baofali+(float)fjbaofali)*3/100);
				String txtst1=lst+"";
				int sell=(int)(((float)horse_buy/2)+((float)horse_buy/2)*(((float)manyi+(float)zhongcheng+(float)manfu)/300));
				txt_win.setText(win+"%");
				txt_lst.setText("1:"+txtst1.substring(0, 3));
				txt_sell.setText(sell+"");
				
				
				txt_fjspeed.setText(fjspeed+"");
				txt_fjnaili.setText(fjnaili+"");
				txt_fjbaofali.setText(fjbaofali+"");
				
			} catch (Exception e) {
				JOptionPane.showMessageDialog(null, "���ݲ���ȷ", "����", JOptionPane.ERROR_MESSAGE);
			}
		}
		
		if (obj == weishi) {
			try {
				int speed=Integer.parseInt(txt_speed.getText());
				int naili=Integer.parseInt(txt_naili.getText());
				int baofali=Integer.parseInt(txt_baofali.getText());
				int manyi=Integer.parseInt(txt_manyi.getText());
				int zhongcheng=Integer.parseInt(txt_zhongcheng.getText());
				int manfu=Integer.parseInt(txt_manfu.getText());
				int horse_buy=Integer.parseInt(txt_horse_buy.getText());
				int money=Integer.parseInt(txt_money.getText());
				
				int inttiaojiao=Integer.parseInt(txt_tiaojiao.getText());
				int intweishi=Integer.parseInt(txt_weishi.getText());
				int intqingxi=Integer.parseInt(txt_qingxi.getText());			
				
				txt_weishi.setText(intweishi+1+"");
				txt_manyi.setText(manyi+2+"");
				txt_zhongcheng.setText(zhongcheng-1+"");
				txt_manfu.setText(manfu+3+"");
				txt_money.setText(money-50+"");
				
				
				int fjspeed=0;
				int fjnaili=0;
				int fjbaofali=0;	
				
				if(manyi<50){
					fjspeed=fjspeed-5;
					fjbaofali=fjbaofali-5;
				}else if(manyi>=90){
					fjspeed=fjspeed+5;
					fjbaofali=fjbaofali+5;	
				}	
					
				if(zhongcheng<50){
					fjnaili=fjnaili-5;
				}else if(zhongcheng>=90){
					fjnaili=fjnaili+5;
				}			
			
				if(manfu<50){
					fjspeed=fjspeed-10;
					fjnaili=fjnaili-10;
					fjbaofali=fjbaofali-10;
				}else  if(manfu>=90){
					fjspeed=fjspeed-5;
					fjnaili=fjnaili+5;
					fjbaofali=fjbaofali+2;	
				}
			
				int win=10+((speed+fjspeed+naili+fjnaili+baofali+fjbaofali-200)/10);
				float lst=40/(((float)speed+(float)fjspeed+(float)naili+(float)fjnaili+(float)baofali+(float)fjbaofali)*3/100);
				String txtst1=lst+"";
				int sell=(int)(((float)horse_buy/2)+((float)horse_buy/2)*(((float)manyi+(float)zhongcheng+(float)manfu)/300));
				txt_win.setText(win+"%");
				txt_lst.setText("1:"+txtst1.substring(0, 3));
				txt_sell.setText(sell+"");
				
				
				txt_fjspeed.setText(fjspeed+"");
				txt_fjnaili.setText(fjnaili+"");
				txt_fjbaofali.setText(fjbaofali+"");
			} catch (Exception e) {
				JOptionPane.showMessageDialog(null, "���ݲ���ȷ", "����", JOptionPane.ERROR_MESSAGE);
			}
		}
		
		if (obj == qingxi) {
			try {
				int speed=Integer.parseInt(txt_speed.getText());
				int naili=Integer.parseInt(txt_naili.getText());
				int baofali=Integer.parseInt(txt_baofali.getText());
				int manyi=Integer.parseInt(txt_manyi.getText());
				int zhongcheng=Integer.parseInt(txt_zhongcheng.getText());
				int manfu=Integer.parseInt(txt_manfu.getText());
				int horse_buy=Integer.parseInt(txt_horse_buy.getText());
				int money=Integer.parseInt(txt_money.getText());

				int inttiaojiao=Integer.parseInt(txt_tiaojiao.getText());
				int intweishi=Integer.parseInt(txt_weishi.getText());
				int intqingxi=Integer.parseInt(txt_qingxi.getText());			
				
				txt_qingxi.setText(intqingxi+1+"");
				txt_manyi.setText(manyi+1+"");
				txt_zhongcheng.setText(zhongcheng+1+"");
				txt_manfu.setText(manfu-1+"");
				txt_money.setText(money-50+"");

				int fjspeed=0;
				int fjnaili=0;
				int fjbaofali=0;	
				
				if(manyi<50){
					fjspeed=fjspeed-5;
					fjbaofali=fjbaofali-5;
				}else if(manyi>=90){
					fjspeed=fjspeed+5;
					fjbaofali=fjbaofali+5;	
				}	
					
				if(zhongcheng<50){
					fjnaili=fjnaili-5;
				}else if(zhongcheng>=90){
					fjnaili=fjnaili+5;
				}			
			
				if(manfu<50){
					fjspeed=fjspeed-10;
					fjnaili=fjnaili-10;
					fjbaofali=fjbaofali-10;
				}else  if(manfu>=90){
					fjspeed=fjspeed-5;
					fjnaili=fjnaili+5;
					fjbaofali=fjbaofali+2;	
				}
				int win=10+((speed+fjspeed+naili+fjnaili+baofali+fjbaofali-200)/10);
				float lst=40/(((float)speed+(float)fjspeed+(float)naili+(float)fjnaili+(float)baofali+(float)fjbaofali)*3/100);
				String txtst1=lst+"";
				int sell=(int)(((float)horse_buy/2)+((float)horse_buy/2)*(((float)manyi+(float)zhongcheng+(float)manfu)/300));
				txt_win.setText(win+"%");
				txt_lst.setText("1:"+txtst1.substring(0, 3));
				txt_sell.setText(sell+"");
				
				
				txt_fjspeed.setText(fjspeed+"");
				txt_fjnaili.setText(fjnaili+"");
				txt_fjbaofali.setText(fjbaofali+"");
			} catch (Exception e) {
				JOptionPane.showMessageDialog(null, "���ݲ���ȷ", "����", JOptionPane.ERROR_MESSAGE);
			}
		}
		
	}

}
