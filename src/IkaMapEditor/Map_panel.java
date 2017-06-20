package IkaMapEditor;
import javax.swing.*;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;



public class Map_panel
    extends JPanel {

	private static final long serialVersionUID = 1L;
	private static Map_Screen map_screen = new Map_Screen();
	private static Map_mod map_mod = new Map_mod();
	private static JScrollPane ScreenPanel;
	private static JScrollPane modPanel;
	private static JPanel bottonPanel = new JPanel(new GridLayout(1, 10));
	private static JLabel rightClick1=new JLabel("右键功能:");
	private static JLabel rightClick2=new JLabel("移动");
	private static JButton Botton_move = new JButton("移动");
	private static JButton Botton_rag = new JButton("旋转");
	private static JButton Botton_del = new JButton("清除");
	private static JButton Botton_pass = new JButton("通过");
	private static JButton Botton_rev = new JButton("演示");
    public static JRadioButton line_RB = new JRadioButton("网格");
    public  static JRadioButton col_RB = new JRadioButton("碰撞");
 
 
  ActionListener actionListener = new ActionListener() {
    public void actionPerformed(ActionEvent actionEvent) {
      Object source = actionEvent.getSource();
     if(source==Botton_move){
    	 rightClick2.setText("移动");
    	 SDef.Rightclick_State=SDef.Rightclick_move;
     }
     if(source==Botton_rag){
    	 rightClick2.setText("旋转");
       	 SDef.Rightclick_State=SDef.Rightclick_rag;
     }
     if(source==Botton_del){
    	 rightClick2.setText("清除");
       	 SDef.Rightclick_State=SDef.Rightclick_del;
     }
     if(source==Botton_pass){
    	 rightClick2.setText("通过");
       	 SDef.Rightclick_State=SDef.Rightclick_pass;
       	col_RB.setSelected(true);
     }
     if(source==Botton_rev){
    	// rightClick2.setText("演示");
    	new Map_review(getX() + 60, getY() + 60);
       	// SDef.Rightclick_State=SDef.Rightclick_rev;
     } 
    }
  };

  public Map_panel() {
	   setLayout(new BorderLayout());
	   bottonPanel.add(rightClick1);
	   bottonPanel.add(rightClick2);
	   bottonPanel.add(Botton_move);
	   bottonPanel.add(Botton_rag);
	   bottonPanel.add(Botton_del);
	   bottonPanel.add(Botton_pass);
	   bottonPanel.add(new JLabel(""));
	   bottonPanel.add(Botton_rev);
	   line_RB.setSelected(true);
	   bottonPanel.add(line_RB);
	   bottonPanel.add(col_RB);   
	   line_RB.addActionListener(actionListener);
	   col_RB.addActionListener(actionListener);
	   Botton_move.addActionListener(actionListener);
	   Botton_rag.addActionListener(actionListener); 
	   Botton_del.addActionListener(actionListener);
	   Botton_pass.addActionListener(actionListener);
	   Botton_rev.addActionListener(actionListener);
	  ScreenPanel = new JScrollPane(map_screen);
	  modPanel = new JScrollPane(map_mod);
	  ScreenPanel.setPreferredSize(SDef.mainSize);
	  modPanel.setPreferredSize(SDef.modSize);
	  this.add(bottonPanel,BorderLayout.NORTH);
        this.add(ScreenPanel, BorderLayout.CENTER);
        this.add(modPanel, BorderLayout.EAST);
  }
  } 

