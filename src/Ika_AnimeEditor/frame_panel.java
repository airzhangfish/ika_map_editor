package Ika_AnimeEditor;
import javax.swing.*;

import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.awt.geom.AffineTransform;

/**
 * <p>Title:ika 动画编辑器</p>
 * <p>Description: 编辑图片和帧，形成动画，导出后给手机调用</p>
 * <p>Copyright: airzhangfish Copyright (c) 2007</p>
 * <p>blog: http://airzhangfish.spaces.live.com</p>
 * <p>Company: Comicfishing</p>
 * <p>author airzhangfish</p>
 * <p>version 0.03a standard</p>
 * <p>last updata 2007-8-23</p>
 *  frame_panel 帧控制面板
 */

public class frame_panel
    extends JPanel {
  /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

public Thread thread;

  static JList wordList = new JList(SDef.frame_listModel);
  static JList modlist = new JList(SDef.use_modlist);
  JScrollPane list_frame_scrollPane = new JScrollPane(wordList);
  static frame_image BigImagePanel = new frame_image();
  JPanel bottonPanel = new JPanel(new GridLayout(2, 6));
  static frame_mod_image small_Imagepanel = new frame_mod_image();

  JButton Botton_MOVE_UP = new JButton("上移");
  JButton Botton_MOVE_DOWN = new JButton("下移");
  JButton Botton_MOVE_LEFT = new JButton("左移");
  JButton Botton_MOVE_RIGHT = new JButton("右移");
  JButton Botton_MOD_RAT = new JButton("旋转镜像");
  JButton Botton_MOD_DEL = new JButton("删除物块");
  JButton Botton_BIG = new JButton("浏览放大");
  JButton Botton_SMALL = new JButton("浏览缩小");
  JButton Botton_MOD_UP = new JButton("上移一层");
  JButton Botton_MOD_DOWN = new JButton("下移一层");
  JButton Botton_EXPLAIN = new JButton("添加注释");
  JButton Botton_NULL1 = new JButton("");
  JButton Botton_NULL2 = new JButton("");

  //JTextField displayTextField = new JTextField("升级版本预留功能位置");
	JFormattedTextField displayTextField = new JFormattedTextField();
  
  JPanel left_Jpanel = new JPanel(new BorderLayout());
  JPanel middle_Jpanel = new JPanel(new BorderLayout());
  JPanel right_Jpanel = new JPanel(new BorderLayout());

  JPanel bottonPanel2 = new JPanel(new GridLayout(2, 3));
  JButton Botton_jia = new JButton("添加帧");
  JButton Botton_jian = new JButton("删除帧");
  JButton Botton_shang = new JButton("上移帧");
  JButton Botton_xia = new JButton("下移帧");
  JButton Botton_fuzhi = new JButton("复制帧");

  JScrollPane list_usemod_scrollPane = new JScrollPane(modlist);

  ActionListener actionListener = new ActionListener() {
    public void actionPerformed(ActionEvent actionEvent) {
      Object source = actionEvent.getSource();
      if (source == Botton_jia) {
        for (int i = 0; i < SDef.G_framebox.length; i++) {
          if (SDef.G_framebox[i][SDef.frame_USE] == 0) {
            SDef.G_framebox[i][SDef.frame_USE] = 1;
            SDef.G_framebox_str[i]="";
            SDef.frame_words = new String[i + 1];
            break;
          }
        }
        for (int i = 0; i < SDef.frame_words.length; i++) {
          SDef.frame_words[i] = "fra_" + i + ":"+ SDef.G_framebox_str[i]+"-";
        }
        SDef.frame_listModel.clear();
        for (int i = 0; i < SDef.frame_words.length; i++) {
          SDef.frame_listModel.addElement(SDef.frame_words[i]);
        }
        up_frame_list();
        wordList.setSelectedIndex(SDef.frame_words.length - 1);
      }
      
      
      if (source == Botton_jian) {
    
        int[] temp = new int[wordList.getSelectedIndices().length];
        temp = wordList.getSelectedIndices();

        //检查是否有人使用
        String usedanime="";
        	  boolean can_del=true;
              for (int j = 0; j < temp.length; j++) {
            	  for(int i=0;i<SDef.G_animebox.length;i++){
            		  for(int k=2;k<SDef.G_animebox[i].length;k++){
            		  if(temp[j]==SDef.G_animebox[i][k]){
            			  can_del=false;
            			  usedanime=usedanime+i+",";
            			  }
            		  }
            	  }
              }
        	  
              usedanime="正在调用此帧的动画有：\n"+usedanime +"\n不能删除";
        	  if(can_del==false){
        		  	JOptionPane.showMessageDialog(null, usedanime, "出错", JOptionPane.ERROR_MESSAGE);
        	  }else{
        	  //如果没有人使用，删除
        int tempseclet=wordList.getSelectedIndex();
        for (int j = 0; j < temp.length; j++) {
        	
          for (int i = 0; i < SDef.frame_words.length; i++) {
            SDef.G_framebox[temp[j] + i - j] = new int[SDef.G_framebox[temp[j] + i - j + 1].length];
            for (int k = 0; k < SDef.G_framebox[temp[j] + i - j + 1].length; k++) {
              SDef.G_framebox[temp[j] + i - j][k] = SDef.G_framebox[temp[j] + i - j + 1][k];
            } 
            SDef.G_framebox_str[temp[j] + i - j] = SDef.G_framebox_str[temp[j] + i - j + 1];
          }

          SDef.G_framebox[SDef.frame_words.length - 1][SDef.frame_USE] = 0;
          SDef.frame_words = new String[SDef.frame_words.length - 1];
          for (int i = 0; i < SDef.frame_words.length; i++) {
            SDef.frame_words[i] = "fra_" + i + ":"+ SDef.G_framebox_str[i]+"-";
          }
        }
        SDef.frame_listModel.clear();
        for (int i = 0; i < SDef.frame_words.length; i++) {
          SDef.frame_listModel.addElement(SDef.frame_words[i]);
        }
        up_frame_list();
        if(tempseclet<1){
        	 wordList.setSelectedIndex(0);
        }else{
        	wordList.setSelectedIndex(tempseclet-1);	
        }
        
        //移动所有动画数据库中的数据
        for (int j = 0; j < temp.length; j++) {
      	  for(int i=0;i<SDef.G_animebox.length;i++){
      		  for(int k=2;k<SDef.G_animebox[i].length;k++){
      		  if(SDef.G_animebox[i][k]>temp[j]){
      			SDef.G_animebox[i][k]--;
      			  }
      		  }
      	  }
        }
        	  }
      }
      
      
      if (source == Botton_shang) {
        int getSI = wordList.getSelectedIndex();
        if (getSI <= 0) {
          return;
        }
        int[] temp_framebox = SDef.G_framebox[getSI];
        SDef.G_framebox[getSI] = SDef.G_framebox[getSI - 1];
        SDef.G_framebox[getSI - 1] = temp_framebox;

        String temp_str = SDef.G_framebox_str[getSI];
        SDef.G_framebox_str[getSI] = SDef.G_framebox_str[getSI - 1];
        SDef.G_framebox_str[getSI - 1] = temp_str;
        
        
        for (int i = 0; i < SDef.frame_words.length; i++) {
          SDef.frame_words[i] = "fra_" + i + ":"+ SDef.G_framebox_str[i]+"-";
        }
        SDef.frame_listModel.clear();
        for (int i = 0; i < SDef.frame_words.length; i++) {
          SDef.frame_listModel.addElement(SDef.frame_words[i]);
        }
        up_frame_list();
        wordList.setSelectedIndex(getSI - 1);
        
        //动画移动
        	  for(int i=0;i<SDef.G_animebox.length;i++){
        		  for(int k=2;k<SDef.G_animebox[i].length;k++){
        			  if(SDef.G_animebox[i][k]==getSI){
              			SDef.G_animebox[i][k]=getSI-1;
              			  }else if(SDef.G_animebox[i][k]==getSI-1){
        			SDef.G_animebox[i][k]=getSI;
        			  }
        		  }
        	  }

        
        
        

      }
      if (source == Botton_xia) {

        int getSI = wordList.getSelectedIndex();
        if (getSI > SDef.frame_words.length - 2) {
          return;
        }
        int[] temp_framebox = SDef.G_framebox[getSI];
        SDef.G_framebox[getSI] = SDef.G_framebox[getSI + 1];
        SDef.G_framebox[getSI + 1] = temp_framebox;
       

String temp_str = SDef.G_framebox_str[getSI];
SDef.G_framebox_str[getSI] = SDef.G_framebox_str[getSI + 1];
SDef.G_framebox_str[getSI + 1] = temp_str;


        for (int i = 0; i < SDef.frame_words.length; i++) {
          SDef.frame_words[i] = "fra_" + i + ":"+ SDef.G_framebox_str[i]+"-";
        }
        SDef.frame_listModel.clear();
        for (int i = 0; i < SDef.frame_words.length; i++) {
          SDef.frame_listModel.addElement(SDef.frame_words[i]);
        }
        up_frame_list();
        wordList.setSelectedIndex(getSI + 1);

        //动画移动
  	  for(int i=0;i<SDef.G_animebox.length;i++){
  		  for(int k=2;k<SDef.G_animebox[i].length;k++){
  			  if(SDef.G_animebox[i][k]==getSI){
        			SDef.G_animebox[i][k]=getSI+1;
        			  }else if(SDef.G_animebox[i][k]==getSI+1){
  			SDef.G_animebox[i][k]=getSI;
  			  }
  		  }
  	  }
        
      }
      
      
      if (source == Botton_fuzhi) {
        int getSI = wordList.getSelectedIndex();
       // int[] temp_framebox= SDef.G_framebox[getSI];
        for (int i = 0; i < SDef.G_framebox.length; i++) {
          if (SDef.G_framebox[i][SDef.frame_USE] == 0) {
            SDef.G_framebox[i][SDef.frame_USE] = 1;
            SDef.frame_words = new String[i + 1];
           // SDef.G_framebox[i] = temp_framebox;
            SDef.G_framebox[i]=new int[SDef.G_framebox[getSI].length];
            
           for(int j=0;j<SDef.G_framebox[getSI].length;j++){
        	   SDef.G_framebox[i][j] = SDef.G_framebox[getSI][j];
           }
           SDef.G_framebox_str[i] = SDef.G_framebox_str[getSI];
            break;
          }
        }
        for (int i = 0; i < SDef.frame_words.length; i++) {
          SDef.frame_words[i] = "fra_" + i + ":"+ SDef.G_framebox_str[i]+"-";
        }
        SDef.frame_listModel.clear();
        for (int i = 0; i < SDef.frame_words.length; i++) {
          SDef.frame_listModel.addElement(SDef.frame_words[i]);
        }
        up_frame_list();
        wordList.setSelectedIndex(SDef.frame_words.length - 1);
      }

      //面板信息
      if (source == Botton_MOVE_UP) {
        if (wordList.getSelectedIndex() >= 0 && frame_image.frame_pressRect != -1) {
          SDef.G_framebox[wordList.getSelectedIndex()][2 + 4 * frame_image.frame_pressRect + 2]--;
        }
        BigImagePanel.update_Srceen();
      }
      if (source == Botton_MOVE_DOWN) {
        if (wordList.getSelectedIndex() >= 0 && frame_image.frame_pressRect != -1) {
          SDef.G_framebox[wordList.getSelectedIndex()][2 + 4 * frame_image.frame_pressRect + 2]++;
        }
        BigImagePanel.update_Srceen();
      }
      if (source == Botton_MOVE_LEFT) {
        if (wordList.getSelectedIndex() >= 0 && frame_image.frame_pressRect != -1) {
          SDef.G_framebox[wordList.getSelectedIndex()][2 + 4 * frame_image.frame_pressRect + 1]--;
        }
        BigImagePanel.update_Srceen();
      }
      if (source == Botton_MOVE_RIGHT) {
        if (wordList.getSelectedIndex() >= 0 && frame_image.frame_pressRect != -1) {
          SDef.G_framebox[wordList.getSelectedIndex()][2 + 4 * frame_image.frame_pressRect + 1]++;
        }
        BigImagePanel.update_Srceen();
      }

      if (source == Botton_MOD_DEL) {
        if (wordList.getSelectedIndex() >= 0 && frame_image.frame_pressRect != -1) {
          int[] tempframe = SDef.G_framebox[wordList.getSelectedIndex()];
          SDef.G_framebox[wordList.getSelectedIndex()] = new int[SDef.G_framebox[wordList.getSelectedIndex()].length - 4];
          for (int i = 0; i < 2 + 4 * (frame_image.frame_pressRect); i++) {
            SDef.G_framebox[wordList.getSelectedIndex()][i] = tempframe[i];
          }
          for (int i = 2 + 4 * (frame_image.frame_pressRect); i < SDef.G_framebox[wordList.getSelectedIndex()].length; i++) {
            SDef.G_framebox[wordList.getSelectedIndex()][i] = tempframe[i + 4];
          }
          SDef.G_framebox[wordList.getSelectedIndex()][SDef.frame_number]--;
          frame_image.frame_pressRect = -1;
        }
        //更新列表
        SDef.use_mod_words = new String[ (SDef.G_framebox[frame_panel.wordList.getSelectedIndex()].length - 2) / 4];
        for (int i = 0; i < SDef.use_mod_words.length; i++) {
          SDef.use_mod_words[i] = "use_mod:" + SDef.G_framebox[frame_panel.wordList.getSelectedIndex()][2 + i * 4];
        }
        SDef.use_modlist.clear();
        for (int i = 0; i < SDef.use_mod_words.length; i++) {
          SDef.use_modlist.addElement(SDef.use_mod_words[i]);
        }
        modlist.setSelectedIndex(0);
        up_frame_list();
        BigImagePanel.update_Srceen();
      }
      
      
      if (source == Botton_BIG) { //放大
    	  SDef.frame_size=2;
    	  for(int i=0;i<SDef.mod_listModel.getSize();i++){
    		  SDef.mod_bfimage2[i]=SDef.mod_bfimage[i];
    	  }
    	  for(int i=0;i<SDef.mod_listModel.getSize();i++){
    		  SDef.mod_bfimage[i]=frame_image.getbigimage( SDef.mod_bfimage[i]);
    	  }
    	  Botton_BIG.setEnabled(false);
    	  Botton_SMALL.setEnabled(true);
    	  BigImagePanel.update_Srceen();
      }
      
      
      if (source == Botton_SMALL) { //缩小
    	  SDef.frame_size=1;
    	  for(int i=0;i<SDef.mod_listModel.getSize();i++){
    		  SDef.mod_bfimage[i]=  SDef.mod_bfimage2[i];
    	  }  
       	  Botton_BIG.setEnabled(true);
    	  Botton_SMALL.setEnabled(false);
    	  BigImagePanel.update_Srceen();
      }
      
      
      if (source == Botton_MOD_RAT) { //旋转处理
        if (wordList.getSelectedIndex() >= 0 && frame_image.frame_pressRect >= 0) {
          SDef.G_framebox[wordList.getSelectedIndex()][2 + 4 * frame_image.frame_pressRect + 3]++;
          SDef.G_framebox[wordList.getSelectedIndex()][2 + 4 * frame_image.frame_pressRect + 3] = SDef.G_framebox[wordList.getSelectedIndex()][2 +
              4 * frame_image.frame_pressRect + 3] % 8;
        }
        BigImagePanel.update_Srceen();
      }
      if (source == Botton_MOD_UP) {
        if (wordList.getSelectedIndex() >= 0 && frame_image.frame_pressRect > 0) {
          int[] tempxx = new int[4];
          tempxx[0] = SDef.G_framebox[wordList.getSelectedIndex()][2 + 4 * frame_image.frame_pressRect];
          tempxx[1] = SDef.G_framebox[wordList.getSelectedIndex()][2 + 4 * frame_image.frame_pressRect + 1];
          tempxx[2] = SDef.G_framebox[wordList.getSelectedIndex()][2 + 4 * frame_image.frame_pressRect + 2];
          tempxx[3] = SDef.G_framebox[wordList.getSelectedIndex()][2 + 4 * frame_image.frame_pressRect + 3];

          SDef.G_framebox[wordList.getSelectedIndex()][2 + 4 * frame_image.frame_pressRect] = SDef.G_framebox[wordList.getSelectedIndex()][2 +
              4 * frame_image.frame_pressRect - 4];
          SDef.G_framebox[wordList.getSelectedIndex()][2 + 4 * frame_image.frame_pressRect + 1] = SDef.G_framebox[wordList.getSelectedIndex()][2 +
              4 * frame_image.frame_pressRect - 4 + 1];
          SDef.G_framebox[wordList.getSelectedIndex()][2 + 4 * frame_image.frame_pressRect + 2] = SDef.G_framebox[wordList.getSelectedIndex()][2 +
              4 * frame_image.frame_pressRect - 4 + 2];
          SDef.G_framebox[wordList.getSelectedIndex()][2 + 4 * frame_image.frame_pressRect + 3] = SDef.G_framebox[wordList.getSelectedIndex()][2 +
              4 * frame_image.frame_pressRect - 4 + 3];

          SDef.G_framebox[wordList.getSelectedIndex()][2 + 4 * frame_image.frame_pressRect - 4] = tempxx[0];
          SDef.G_framebox[wordList.getSelectedIndex()][2 + 4 * frame_image.frame_pressRect - 4 + 1] = tempxx[1];
          SDef.G_framebox[wordList.getSelectedIndex()][2 + 4 * frame_image.frame_pressRect - 4 + 2] = tempxx[2];
          SDef.G_framebox[wordList.getSelectedIndex()][2 + 4 * frame_image.frame_pressRect - 4 + 3] = tempxx[3];
          //更新列表
          SDef.use_mod_words = new String[ (SDef.G_framebox[wordList.getSelectedIndex()].length - 2) / 4];
          for (int i = 0; i < SDef.use_mod_words.length; i++) {
            SDef.use_mod_words[i] = "use_mod:" + SDef.G_framebox[frame_panel.wordList.getSelectedIndex()][2 + i * 4];
          }
          SDef.use_modlist.clear();
          for (int i = 0; i < SDef.use_mod_words.length; i++) {
            SDef.use_modlist.addElement(SDef.use_mod_words[i]);
          }
          frame_image.frame_pressRect--;
          modlist.setSelectedIndex(frame_image.frame_pressRect);
          up_frame_list();
        }
        BigImagePanel.update_Srceen();
      }
      if (source == Botton_EXPLAIN) {
    	  SDef.G_framebox_str[wordList.getSelectedIndex()]=displayTextField.getText();
    	  up_frame_list();
      }
      
      
      if (source == Botton_MOD_DOWN) {

        if (wordList.getSelectedIndex() >= 0 && frame_image.frame_pressRect >= 0 && frame_image.frame_pressRect < SDef.use_mod_words.length - 1) {
          int[] tempxx = new int[4];
          tempxx[0] = SDef.G_framebox[wordList.getSelectedIndex()][2 + 4 * frame_image.frame_pressRect];
          tempxx[1] = SDef.G_framebox[wordList.getSelectedIndex()][2 + 4 * frame_image.frame_pressRect + 1];
          tempxx[2] = SDef.G_framebox[wordList.getSelectedIndex()][2 + 4 * frame_image.frame_pressRect + 2];
          tempxx[3] = SDef.G_framebox[wordList.getSelectedIndex()][2 + 4 * frame_image.frame_pressRect + 3];

          SDef.G_framebox[wordList.getSelectedIndex()][2 + 4 * frame_image.frame_pressRect] = SDef.G_framebox[wordList.getSelectedIndex()][2 +
              4 * frame_image.frame_pressRect + 4];
          SDef.G_framebox[wordList.getSelectedIndex()][2 + 4 * frame_image.frame_pressRect + 1] = SDef.G_framebox[wordList.getSelectedIndex()][2 +
              4 * frame_image.frame_pressRect + 4 + 1];
          SDef.G_framebox[wordList.getSelectedIndex()][2 + 4 * frame_image.frame_pressRect + 2] = SDef.G_framebox[wordList.getSelectedIndex()][2 +
              4 * frame_image.frame_pressRect + 4 + 2];
          SDef.G_framebox[wordList.getSelectedIndex()][2 + 4 * frame_image.frame_pressRect + 3] = SDef.G_framebox[wordList.getSelectedIndex()][2 +
              4 * frame_image.frame_pressRect + 4 + 3];

          SDef.G_framebox[wordList.getSelectedIndex()][2 + 4 * frame_image.frame_pressRect + 4] = tempxx[0];
          SDef.G_framebox[wordList.getSelectedIndex()][2 + 4 * frame_image.frame_pressRect + 4 + 1] = tempxx[1];
          SDef.G_framebox[wordList.getSelectedIndex()][2 + 4 * frame_image.frame_pressRect + 4 + 2] = tempxx[2];
          SDef.G_framebox[wordList.getSelectedIndex()][2 + 4 * frame_image.frame_pressRect + 4 + 3] = tempxx[3];
          //更新列表
          SDef.use_mod_words = new String[ (SDef.G_framebox[wordList.getSelectedIndex()].length - 2) / 4];
          for (int i = 0; i < SDef.use_mod_words.length; i++) {
            SDef.use_mod_words[i] = "use_mod:" + SDef.G_framebox[frame_panel.wordList.getSelectedIndex()][2 + i * 4];
          }
          SDef.use_modlist.clear();
          for (int i = 0; i < SDef.use_mod_words.length; i++) {
            SDef.use_modlist.addElement(SDef.use_mod_words[i]);
          }
          frame_image.frame_pressRect++;
          modlist.setSelectedIndex(frame_image.frame_pressRect);
          up_frame_list();
        }
        BigImagePanel.update_Srceen();
      }
    }
  };
  public static void up_frame_list() {
    int tempindex = frame_panel.wordList.getSelectedIndex();
    for (int i = 0; i < SDef.frame_words.length; i++) {
      for (int j = -1; j < (SDef.G_framebox[i].length - 2) / 4; j++) {
        if (j == -1) {
          SDef.frame_words[i] = "fra_" + i + ":"+SDef.G_framebox_str[i]+"-";
        }
        else {
          SDef.frame_words[i] = SDef.frame_words[i] + SDef.G_framebox[i][2 + j * 4] + ",";
        }
      }
    }
    SDef.frame_listModel.clear();
    for (int i = 0; i < SDef.frame_words.length; i++) {
      SDef.frame_listModel.addElement(SDef.frame_words[i]);
    }
    frame_panel.wordList.setSelectedIndex(tempindex);
  }

  AffineTransform transform;
  public frame_panel() {
    setLayout(new BorderLayout());
    list_frame_scrollPane.setPreferredSize(new Dimension(200, 600));
    transform = new AffineTransform();
    //左边控制面板
    left_Jpanel.add(list_frame_scrollPane, BorderLayout.NORTH);
    bottonPanel2.add(Botton_jia);
    bottonPanel2.add(Botton_jian);
    bottonPanel2.add(Botton_shang);
    bottonPanel2.add(Botton_xia);
    bottonPanel2.add(Botton_fuzhi);
    Botton_jia.setToolTipText("添加一帧新帧,允许使用者放置物块在这个帧");
    Botton_jian.setToolTipText("删除已存在帧");
    Botton_shang.setToolTipText("此帧向上移动");
    Botton_xia.setToolTipText("此帧向下移动");
    Botton_fuzhi.setToolTipText("复制选中帧，新帧被放置在最下方");
    Botton_jia.addActionListener(actionListener);
    Botton_jian.addActionListener(actionListener);
    Botton_shang.addActionListener(actionListener);
    Botton_xia.addActionListener(actionListener);
    Botton_fuzhi.addActionListener(actionListener);

    left_Jpanel.add(bottonPanel2, BorderLayout.CENTER);
    this.add(left_Jpanel, BorderLayout.WEST);

    //按钮控制
    bottonPanel.setPreferredSize(new Dimension(50, 50));
    bottonPanel.add(Botton_BIG);
    bottonPanel.add(Botton_MOVE_UP);
    bottonPanel.add(Botton_SMALL);
    bottonPanel.add(Botton_MOD_RAT);
    bottonPanel.add(Botton_MOD_UP);
   bottonPanel.add(Botton_EXPLAIN);
    bottonPanel.add(Botton_MOVE_LEFT);
    bottonPanel.add(Botton_MOVE_DOWN);
    bottonPanel.add(Botton_MOVE_RIGHT);
    bottonPanel.add(Botton_MOD_DEL);
    bottonPanel.add(Botton_MOD_DOWN);
    
    Botton_BIG.setToolTipText("用户帧界面放大");
    Botton_MOVE_UP.setToolTipText("选中物块向上移动");
    Botton_SMALL.setToolTipText("用户帧界面缩小");
    Botton_MOD_RAT.setToolTipText("选中物块旋转");
    Botton_MOD_UP.setToolTipText("选中物块向里层移动");
    Botton_EXPLAIN.setToolTipText("向此帧添加注释");
    Botton_MOVE_LEFT.setToolTipText("选中物块向左移动");
    Botton_MOVE_DOWN.setToolTipText("选中物块向下移动");
    Botton_MOVE_RIGHT.setToolTipText("选中物块向右移动");
    Botton_MOD_DEL.setToolTipText("选中物块向上移动");
    Botton_MOD_DOWN.setToolTipText("选中物块向表层移动");

    
 	  Botton_BIG.setEnabled(true);
	  Botton_SMALL.setEnabled(false);
    bottonPanel.add(Botton_NULL1);
    Botton_MOVE_UP.registerKeyboardAction(actionListener, "Botton_MOVE_UP", KeyStroke.getKeyStroke(KeyEvent.VK_UP, 0, false),
                                          JComponent.WHEN_IN_FOCUSED_WINDOW);
    Botton_MOVE_DOWN.registerKeyboardAction(actionListener, "Botton_MOVE_DOWN", KeyStroke.getKeyStroke(KeyEvent.VK_DOWN, 0, false),
                                            JComponent.WHEN_IN_FOCUSED_WINDOW);
    Botton_MOVE_LEFT.registerKeyboardAction(actionListener, "Botton_MOVE_LEFT", KeyStroke.getKeyStroke(KeyEvent.VK_LEFT, 0, false),
                                            JComponent.WHEN_IN_FOCUSED_WINDOW);
    Botton_MOVE_RIGHT.registerKeyboardAction(actionListener, "Botton_MOVE_RIGHT", KeyStroke.getKeyStroke(KeyEvent.VK_RIGHT, 0, false),
                                             JComponent.WHEN_IN_FOCUSED_WINDOW);
    Botton_MOD_DEL.registerKeyboardAction(actionListener, "Botton_MOD_DEL", KeyStroke.getKeyStroke(KeyEvent.VK_DELETE, 0, false),
                                          JComponent.WHEN_IN_FOCUSED_WINDOW);
    Botton_MOVE_UP.addActionListener(actionListener);
    Botton_MOVE_DOWN.addActionListener(actionListener);
    Botton_MOVE_LEFT.addActionListener(actionListener);
    Botton_MOVE_RIGHT.addActionListener(actionListener);
    Botton_MOD_RAT.addActionListener(actionListener);
    Botton_MOD_DEL.addActionListener(actionListener);
    Botton_BIG.addActionListener(actionListener);
    Botton_SMALL.addActionListener(actionListener);
    Botton_MOD_UP.addActionListener(actionListener);
    Botton_MOD_DOWN.addActionListener(actionListener);
    Botton_EXPLAIN.addActionListener(actionListener);
    //中间控制面板
    middle_Jpanel.add(BigImagePanel, BorderLayout.CENTER);
    middle_Jpanel.add(bottonPanel, BorderLayout.SOUTH);
    this.add(middle_Jpanel, BorderLayout.CENTER);

    //右边控制面板效率
    right_Jpanel.setPreferredSize(new Dimension(200, 100));
    small_Imagepanel.setPreferredSize(new Dimension(200, 200));
    //displayTextField.setPreferredSize(new Dimension(200, 200));
    right_Jpanel.add(small_Imagepanel, BorderLayout.NORTH);
    right_Jpanel.add(list_usemod_scrollPane, BorderLayout.CENTER);
    right_Jpanel.add(displayTextField, BorderLayout.SOUTH);
    this.add(right_Jpanel, BorderLayout.EAST);
    this.registerKeyboardAction(actionListener, KeyStroke.getKeyStroke(KeyEvent.VK_F1, 0, false), JComponent.WHEN_IN_FOCUSED_WINDOW);
  }
}
