package Ika_AnimeEditor;

public class myThread implements Runnable {
  /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
public Thread thread;

  /**
   * <p>Title:ika �����༭��</p>
   * <p>Description: �༭ͼƬ��֡���γɶ�������������ֻ�����</p>
   * <p>Copyright: airzhangfish Copyright (c) 2007</p>
   * <p>blog: http://airzhangfish.spaces.live.com</p>
   * <p>Company: Comicfishing</p>
   * <p>author airzhangfish</p>
   * <p>version 0.03a standard</p>
   * <p>last updata 2007-8-23</p>
   * �߳̿��ơ���Ҫ���ڸ���һЩ��������
   */


  public myThread() {
    thread = new Thread(this);
    thread.start();
  }

  int  mod_panel_wordList_getSelectedIndex=-1;
  int  frame_panel_modList_getSelectedIndex=-1;
  int  frame_panel_wordList_getSelectedIndex=-1;
  public void check(){
	  //��� mod panel��һЩ���ݴ���ѡ�
	  if(mod_panel.wordList.getSelectedIndex()!=mod_panel_wordList_getSelectedIndex){
		  mod_panel_wordList_getSelectedIndex=mod_panel.wordList.getSelectedIndex();
		  mod_panel.smalImagePanel.update_Srceen();
	    }
	  //��� frame_ panel��һЩ���ݴ���ѡ�
	  if(frame_panel.modlist.getSelectedIndex()!=frame_panel_modList_getSelectedIndex){
		  frame_panel_modList_getSelectedIndex=frame_panel.modlist.getSelectedIndex();
		  frame_image.frame_pressRect= frame_panel_modList_getSelectedIndex;
		  frame_panel.small_Imagepanel.update_Srceen();
		  frame_panel.BigImagePanel.update_Srceen();
	    }
	  
	  if(frame_panel.wordList.getSelectedIndex()!=frame_panel_wordList_getSelectedIndex){
		  frame_panel_wordList_getSelectedIndex=frame_panel.wordList.getSelectedIndex();
		  frame_panel.small_Imagepanel.update_Srceen();
		  frame_panel.BigImagePanel.update_Srceen();
	    }
  }
  
  
  
  private boolean isrun = true;
  public void run() {
    while (isrun == true) {
    	check();
      try {
        Thread.sleep(SDef.SYSTEM_DELAY);
      }
      catch (InterruptedException ex) {
      }
    }
  }

}
