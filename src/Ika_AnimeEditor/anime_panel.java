package Ika_AnimeEditor;
import javax.swing.*;

import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;

/**
 * <p>Title:ika �����༭��</p>
 * <p>Description: �༭ͼƬ��֡���γɶ�������������ֻ�����</p>
 * <p>Copyright: airzhangfish Copyright (c) 2007</p>
 * <p>blog: http://airzhangfish.spaces.live.com</p>
 * <p>Company: Comicfishing</p>
 * <p>author airzhangfish</p>
 * <p>version 0.03a standard</p>
 * <p>last updata 2007-8-23</p>
 * anime_panel �����༭����İ�ť�Ͳ˵�����
 */

public class anime_panel
    extends JPanel {
  /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

public Thread thread;

JFormattedTextField displayTextField = new JFormattedTextField();
  static JList framelist = new JList(SDef.frame_listModel);
  static JList useframelist = new JList(SDef.use_framelistModel);
  static JList animelist = new JList(SDef.anime_listModel);

  JScrollPane framelist_scrollPane = new JScrollPane(framelist);
  JScrollPane useframelist_scrollPane = new JScrollPane(useframelist);
  JScrollPane animelist_scrollPane = new JScrollPane(animelist);

  static anime_image BigImagePanel = new anime_image();
  JPanel bottonPanel = new JPanel(new GridLayout(2, 6));
  static anime_frame_image small_Imagepanel = new anime_frame_image();

  JPanel left_Jpanel = new JPanel(new BorderLayout());
  JPanel middle_Jpanel = new JPanel(new BorderLayout());
  JPanel right_Jpanel = new JPanel(new BorderLayout());

  JPanel bottonPanel2 = new JPanel(new GridLayout(2, 4));
  JPanel bottonPanel3 = new JPanel(new GridLayout(2, 3));

  JButton Botton_anime_jia = new JButton("��Ӷ���");
  JButton Botton_anime_jian = new JButton("ɾ������");
  JButton Botton_anime_shang = new JButton("���ƶ���");
  JButton Botton_anime_xia = new JButton("���ƶ���");
  JButton Botton_anime_fuzhi = new JButton("���ƶ���");
  JButton Botton_anime_play = new JButton("���Ŷ���");
  JButton Botton_anime_stop = new JButton("��ͣ����");

  JButton Botton_frame_shang = new JButton("����ʹ��֡");
  JButton Botton_frame_xia = new JButton("����ʹ��֡");
  JButton Botton_frame_del = new JButton("ɾ��ʹ��֡");
  JButton Botton_frame_jia = new JButton("��֡������");
  
  JButton Botton_anime_explain = new JButton("���ע��");

  ActionListener actionListener = new ActionListener() {
    public void actionPerformed(ActionEvent actionEvent) {
      Object source = actionEvent.getSource();

      //����
      if (source == Botton_anime_jia) {
        for (int i = 0; i < SDef.G_animebox.length; i++) {
          if (SDef.G_animebox[i][SDef.anime_USE] == 0) {
            SDef.G_animebox[i][SDef.anime_USE] = 1;
            SDef.anime_words = new String[i + 1];
            break;
          }
        }
        for (int i = 0; i < SDef.anime_words.length; i++) {
            SDef.anime_words[i] = "ani_" + i + ":"+ SDef.G_animebox_str[i]+"-";
        }
        SDef.anime_listModel.clear();
        for (int i = 0; i < SDef.anime_words.length; i++) {
          SDef.anime_listModel.addElement(SDef.anime_words[i]);
        }
        animelist.setSelectedIndex(SDef.anime_words.length - 1);
        useframelist.setSelectedIndex( -1);
      }
      if (source == Botton_anime_jian) {
        int[] temp = new int[animelist.getSelectedIndices().length];
        temp = animelist.getSelectedIndices();
        int tempseclet=animelist.getSelectedIndex();
        for (int j = 0; j < temp.length; j++) {
          for (int i = 0; i < SDef.anime_words.length; i++) {
            SDef.G_animebox[temp[j] + i - j] = new int[SDef.G_animebox[temp[j] + i - j + 1].length];
            for (int k = 0; k < SDef.G_animebox[temp[j] + i - j + 1].length; k++) {
              SDef.G_animebox[temp[j] + i - j][k] = SDef.G_animebox[temp[j] + i - j + 1][k];
            }
          }

          SDef.G_animebox[SDef.anime_words.length - 1][SDef.anime_USE] = 0;
          SDef.anime_words = new String[SDef.anime_words.length - 1];
          for (int i = 0; i < SDef.anime_words.length; i++) {
	            SDef.anime_words[i] = "ani_" + i + ":"+ SDef.G_animebox_str[tempseclet]+"-";
          }
        }
        SDef.anime_listModel.clear();
        for (int i = 0; i < SDef.anime_words.length; i++) {
          SDef.anime_listModel.addElement(SDef.anime_words[i]);
        }
        
        if(tempseclet<1){
        	animelist.setSelectedIndex(0);
       }else{
    	   animelist.setSelectedIndex(tempseclet-1);	
       }

      }
      if (source == Botton_anime_shang) {
        int getSI = animelist.getSelectedIndex();
        if (getSI <= 0) {
          return;
        }
        int[] temp_animebox = SDef.G_animebox[getSI];
        SDef.G_animebox[getSI] = SDef.G_animebox[getSI - 1];
        SDef.G_animebox[getSI - 1] = temp_animebox;

        for (int i = 0; i < SDef.anime_words.length; i++) {
            SDef.anime_words[i] = "ani_" + i + ":"+ SDef.G_animebox_str[i]+"-";
        }
        SDef.anime_listModel.clear();
        for (int i = 0; i < SDef.anime_words.length; i++) {
          SDef.anime_listModel.addElement(SDef.anime_words[i]);
        }
        animelist.setSelectedIndex(getSI - 1);
      }
      if (source == Botton_anime_xia) {

        int getSI = animelist.getSelectedIndex();
        if (getSI > SDef.anime_words.length - 2) {
          return;
        }
        int[] temp_animebox = SDef.G_animebox[getSI];
        SDef.G_animebox[getSI] = SDef.G_animebox[getSI + 1];
        SDef.G_animebox[getSI + 1] = temp_animebox;

        for (int i = 0; i < SDef.anime_words.length; i++) {
            SDef.anime_words[i] = "ani_" + i + ":"+ SDef.G_animebox_str[i]+"-";
        }
        SDef.anime_listModel.clear();
        for (int i = 0; i < SDef.anime_words.length; i++) {
          SDef.anime_listModel.addElement(SDef.anime_words[i]);
        }
        animelist.setSelectedIndex(getSI + 1);

      }
      if (source == Botton_anime_fuzhi) {
        int getSI = animelist.getSelectedIndex();
        int[] temp_animebox = SDef.G_animebox[getSI];

        for (int i = 0; i < SDef.G_animebox.length; i++) {
          if (SDef.G_animebox[i][SDef.anime_USE] == 0) {
            SDef.G_animebox[i][SDef.anime_USE] = 1;
            SDef.anime_words = new String[i + 1];
            SDef.G_animebox[i] = temp_animebox;
            break;
          }
        }
        for (int i = 0; i < SDef.anime_words.length; i++) {
            SDef.anime_words[i] = "ani_" + i + ":"+ SDef.G_animebox_str[i]+"-";
        }
        SDef.anime_listModel.clear();
        for (int i = 0; i < SDef.anime_words.length; i++) {
          SDef.anime_listModel.addElement(SDef.anime_words[i]);
        }
        animelist.setSelectedIndex(SDef.anime_words.length - 1);
      }
      if (source == Botton_anime_play) {
        anime_image.anime_play = !anime_image.anime_play;
      }
      if (source == Botton_anime_stop) {
        anime_image.anime_play = !anime_image.anime_play;
      }
// ֡����
      if (source == Botton_frame_shang) { //��
        int useframeSI = useframelist.getSelectedIndex();
        if (useframeSI < 1) {
          return;
        }
        int getAnimeSI = animelist.getSelectedIndex();
        if (getAnimeSI < 0) {
          return;
        }
        int temp_number = SDef.G_animebox[getAnimeSI][useframeSI + 2];
        SDef.G_animebox[getAnimeSI][useframeSI + 2] = SDef.G_animebox[getAnimeSI][useframeSI + 2 - 1];
        SDef.G_animebox[getAnimeSI][useframeSI + 2 - 1] = temp_number;

        //��ʵ����ˢ��
        for (int i = 0; i < SDef.use_framelist.length; i++) {
          SDef.use_framelist[i] = "use_fra:" + SDef.G_animebox[getAnimeSI][i + 2] + ":"+SDef.G_framebox_str[SDef.G_animebox[getAnimeSI][i + 2]];
        }
        SDef.use_framelistModel.clear();
        for (int i = 0; i < SDef.use_framelist.length; i++) {
          SDef.use_framelistModel.addElement(SDef.use_framelist[i]);
        }
        useframelist.setSelectedIndex(useframeSI - 1);
      }
      if (source == Botton_frame_xia) { //��
        int useframeSI = useframelist.getSelectedIndex();
        if (useframeSI < 0 || useframeSI >= SDef.use_framelist.length - 1) {
          return;
        }
        int getAnimeSI = animelist.getSelectedIndex();
        if (getAnimeSI < 0) {
          return;
        }
        int temp_number = SDef.G_animebox[getAnimeSI][useframeSI + 2];
        SDef.G_animebox[getAnimeSI][useframeSI + 2] = SDef.G_animebox[getAnimeSI][useframeSI + 2 + 1];
        SDef.G_animebox[getAnimeSI][useframeSI + 2 + 1] = temp_number;
        //��ʵ����ˢ��
        for (int i = 0; i < SDef.use_framelist.length; i++) {
        	  SDef.use_framelist[i] = "use_fra:" + SDef.G_animebox[getAnimeSI][i + 2] + ":"+SDef.G_framebox_str[SDef.G_animebox[getAnimeSI][i + 2]];
        	
        }
        SDef.use_framelistModel.clear();
        for (int i = 0; i < SDef.use_framelist.length; i++) {
          SDef.use_framelistModel.addElement(SDef.use_framelist[i]);
        }
        useframelist.setSelectedIndex(useframeSI + 1);
      }

      //ɾ��
      if (source == Botton_frame_del) {
        int useframeSI = useframelist.getSelectedIndex();
        if (useframeSI < 0) {
          return;
        }
        int getAnimeSI = animelist.getSelectedIndex();
        if (getAnimeSI < 0) {
          return;
        }
        int[] tempanimebox = new int[SDef.G_animebox[getAnimeSI].length - 1];
        int tempseclet=useframelist.getSelectedIndex();
        SDef.use_framelist = new String[tempanimebox.length - 2];
        for (int i = 0; i < tempanimebox.length; i++) {
          if (i > useframeSI + 1) {
            tempanimebox[i] = SDef.G_animebox[getAnimeSI][i + 1];
          }
          else {
            tempanimebox[i] = SDef.G_animebox[getAnimeSI][i];
          }
        }
        tempanimebox[SDef.anime_number]--;
        SDef.G_animebox[getAnimeSI] = tempanimebox;
        //��ʵ
        //��ʵ����ˢ��
        for (int i = 0; i < SDef.use_framelist.length; i++) {
          SDef.use_framelist[i] = "use_fra:" + SDef.G_animebox[getAnimeSI][i + 2] + ":"+SDef.G_framebox_str[SDef.G_animebox[getAnimeSI][i + 2]];
          
        }
        SDef.use_framelistModel.clear();
        for (int i = 0; i < SDef.use_framelist.length; i++) {
          SDef.use_framelistModel.addElement(SDef.use_framelist[i]);
        }
        
        if(tempseclet<1){
        	useframelist.setSelectedIndex(0);
       }else{
    	   useframelist.setSelectedIndex(tempseclet-1);	
       }
      }
      if (source == Botton_frame_jia) {
//��֡��ID���뵽G_animebox��
        int getframeSI = framelist.getSelectedIndex();
        if (getframeSI < 0) {
          return;
        }
        int getAnimeSI = animelist.getSelectedIndex();
        if (getAnimeSI < 0) {
          return;
        }
        int[] tempanimebox = new int[SDef.G_animebox[getAnimeSI].length + 1];
        SDef.use_framelist = new String[tempanimebox.length - 2];
        for (int i = 0; i < SDef.G_animebox[getAnimeSI].length; i++) {
          tempanimebox[i] = SDef.G_animebox[getAnimeSI][i];
        }
        tempanimebox[SDef.G_animebox[getAnimeSI].length] = getframeSI;
        tempanimebox[SDef.anime_number]++;
        SDef.G_animebox[getAnimeSI] = tempanimebox;
        //��ʵ
        //��ʵ����ˢ��
        for (int i = 0; i < SDef.use_framelist.length; i++) {
        	  SDef.use_framelist[i] = "use_fra:" + SDef.G_animebox[getAnimeSI][i + 2] + ":"+SDef.G_framebox_str[SDef.G_animebox[getAnimeSI][i + 2]];
        	    
        }
        SDef.use_framelistModel.clear();
        for (int i = 0; i < SDef.use_framelist.length; i++) {
          SDef.use_framelistModel.addElement(SDef.use_framelist[i]);
        }
        useframelist.setSelectedIndex(SDef.use_framelist.length - 1);
      }
      
      if (source == Botton_anime_explain) {
    	        int tempseclet=animelist.getSelectedIndex();
    	       if(tempseclet>-1){
    	   	  SDef.G_animebox_str[tempseclet]=   displayTextField.getText();
    	       } 
    	          for (int i = 0; i < SDef.anime_words.length; i++) {
    	            SDef.anime_words[i] = "ani_" + i + ":"+ SDef.G_animebox_str[i]+"-";
    	          }
    	        SDef.anime_listModel.clear();
    	        for (int i = 0; i < SDef.anime_words.length; i++) {
    	          SDef.anime_listModel.addElement(SDef.anime_words[i]);
    	        }
    	        animelist.setSelectedIndex(tempseclet);
      }
    }
  };

  public anime_panel() {
    setLayout(new BorderLayout());
    framelist_scrollPane.setPreferredSize(new Dimension(200, 700));
    //��߿������
    left_Jpanel.setPreferredSize(new Dimension(200, 700));
    bottonPanel3.setPreferredSize(new Dimension(200, 50));
    bottonPanel3.add(Botton_frame_shang);
    bottonPanel3.add(Botton_frame_xia);
    bottonPanel3.add(Botton_frame_del);
    bottonPanel3.add(Botton_frame_jia);
    Botton_frame_shang.setToolTipText("�����ƶ�ѡ�е�ʹ��֡");
    Botton_frame_xia.setToolTipText("�����ƶ�ѡ�е�ʹ��֡");
    Botton_frame_del.setToolTipText("ɾ��ѡ�е�ʹ��֡");
    Botton_frame_jia.setToolTipText("���ѡ�е�֡��ʹ��֡�б�");
    Botton_frame_shang.addActionListener(actionListener);
    Botton_frame_xia.addActionListener(actionListener);
    Botton_frame_del.addActionListener(actionListener);
    Botton_frame_jia.addActionListener(actionListener);
    left_Jpanel.add(bottonPanel3, BorderLayout.NORTH);
    left_Jpanel.add(framelist_scrollPane, BorderLayout.CENTER);
    left_Jpanel.add(displayTextField, BorderLayout.SOUTH);
    this.add(left_Jpanel, BorderLayout.WEST);

//�м�������
    useframelist_scrollPane.setPreferredSize(new Dimension(200, 350));
    animelist_scrollPane.setPreferredSize(new Dimension(200, 350));

    bottonPanel2.add(Botton_anime_jia);
    bottonPanel2.add(Botton_anime_jian);
    bottonPanel2.add(Botton_anime_shang);
    bottonPanel2.add(Botton_anime_xia);
    
    bottonPanel2.add(Botton_anime_explain);
    bottonPanel2.add(Botton_anime_fuzhi);
    bottonPanel2.add(Botton_anime_play);
    bottonPanel2.add(Botton_anime_stop);
    Botton_anime_jia.setToolTipText("����һ���¶���");
    Botton_anime_jian.setToolTipText("ɾ��һ��ѡ�еĶ���");
    Botton_anime_shang.setToolTipText("ѡ�ж��������ƶ�");
    Botton_anime_xia.setToolTipText("ѡ�ж��������ƶ�");
    Botton_anime_explain.setToolTipText("��һ��ѡ�ж������ע��");
    Botton_anime_fuzhi.setToolTipText("�����Ѿ�ѡ�еĶ������¶����ᱻ���������·�");
    Botton_anime_play.setToolTipText("���Ŷ���");
    Botton_anime_stop.setToolTipText("ֹͣ����");
    
        Botton_frame_jia.registerKeyboardAction(actionListener, "Botton_frame_jia", KeyStroke.getKeyStroke(KeyEvent.VK_A, java.awt.event.InputEvent.CTRL_MASK, false),
                                            JComponent.WHEN_IN_FOCUSED_WINDOW);
    Botton_frame_del.registerKeyboardAction(actionListener, "Botton_frame_del", KeyStroke.getKeyStroke(KeyEvent.VK_DELETE, java.awt.event.InputEvent.CTRL_MASK, false),
                                            JComponent.WHEN_IN_FOCUSED_WINDOW);
    Botton_frame_shang.registerKeyboardAction(actionListener, "Botton_frame_shang", KeyStroke.getKeyStroke(KeyEvent.VK_UP, java.awt.event.InputEvent.CTRL_MASK, false),
            JComponent.WHEN_IN_FOCUSED_WINDOW);
    Botton_frame_xia.registerKeyboardAction(actionListener, "Botton_frame_xia", KeyStroke.getKeyStroke(KeyEvent.VK_DOWN, java.awt.event.InputEvent.CTRL_MASK, false),
            JComponent.WHEN_IN_FOCUSED_WINDOW);
    
    
    
    Botton_anime_play.registerKeyboardAction(actionListener, "Botton_anime_play", KeyStroke.getKeyStroke(KeyEvent.VK_P, java.awt.event.InputEvent.CTRL_MASK, false),
                                             JComponent.WHEN_IN_FOCUSED_WINDOW);

    Botton_anime_jia.addActionListener(actionListener);
    Botton_anime_jian.addActionListener(actionListener);
    Botton_anime_shang.addActionListener(actionListener);
    Botton_anime_xia.addActionListener(actionListener);
    Botton_anime_fuzhi.addActionListener(actionListener);
    Botton_anime_play.addActionListener(actionListener);
    Botton_anime_stop.addActionListener(actionListener);
    Botton_anime_explain.addActionListener(actionListener);
    middle_Jpanel.add(useframelist_scrollPane, BorderLayout.NORTH);
    middle_Jpanel.add(animelist_scrollPane, BorderLayout.CENTER);
    middle_Jpanel.add(bottonPanel2, BorderLayout.SOUTH);
    this.add(middle_Jpanel, BorderLayout.CENTER);

    //�ұ߿������
    right_Jpanel.setPreferredSize(new Dimension(450, 100));
    small_Imagepanel.setPreferredSize(new Dimension(450, 350));
    BigImagePanel.setPreferredSize(new Dimension(450, 350));
    right_Jpanel.add(small_Imagepanel, BorderLayout.NORTH);
    right_Jpanel.add(BigImagePanel, BorderLayout.CENTER);
    this.add(right_Jpanel, BorderLayout.EAST);
  }
}
