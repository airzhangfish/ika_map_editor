package ika_toolbox;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.io.*;

public class drawFrame
    extends JFrame
    implements ActionListener {

  public static void main(String[] args) {
    drawFrame drawframe = new drawFrame();
    drawframe.show();
  }

//menu�˵���ѡ��
  private JMenuBar jMenuBar1 = new JMenuBar();
  private JMenu jMenuFile = new JMenu("File");
  private JMenuItem jMenuFileOpenFile = new JMenuItem("Open File");
  private JMenuItem jMenuFileOpenTXT = new JMenuItem("Open TXT");
  private JMenuItem jMenuFileExit = new JMenuItem("Exit");

  private JMenu jMenuOutput = new JMenu("Output");
  private JMenuItem jMenuOutputFile = new JMenuItem("Save to File");
  private JMenuItem jMenuOutputTXT = new JMenuItem("Save to TXT");

  private JMenu jMenuHelp = new JMenu("Help");
  private JMenuItem jMenuHelpAbout = new JMenuItem("About");
  private JMenuItem jMenuHelpHelp = new JMenuItem("Help");
  private MyDisplay mydisplay = new MyDisplay();
  public drawFrame() {
    SDef.setMySkin(0);
    this.setSize(300, 100); //������Ĵ�С
    this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    this.setResizable(false); //���岻�ܸı��С
    this.setTitle("ika�ļ�������ת������"); //���ñ���

    //file
    jMenuFile.add(jMenuFileOpenFile);
    jMenuFile.add(jMenuFileOpenTXT);
    jMenuFile.add(jMenuFileExit);
    jMenuFileOpenFile.addActionListener(this);
    jMenuFileOpenTXT.addActionListener(this);
    jMenuFileExit.addActionListener(this);

    //output
    jMenuOutput.add(jMenuOutputFile);
    jMenuOutput.add(jMenuOutputTXT);
    jMenuOutputFile.addActionListener(this);
    jMenuOutputTXT.addActionListener(this);
    jMenuOutputFile.setEnabled(false);
    jMenuOutputTXT.setEnabled(false);

    //help
    jMenuHelp.add(jMenuHelpHelp);
    jMenuHelp.add(jMenuHelpAbout);
    jMenuHelpHelp.addActionListener(this);
    jMenuHelpAbout.addActionListener(this);

//�ܹ�����
    jMenuBar1.add(jMenuFile);
    jMenuBar1.add(jMenuOutput);
    jMenuBar1.add(jMenuHelp);
    this.setJMenuBar(jMenuBar1);

    //����
    JPanel actionPanel = new JPanel(); //�½�JPanel�͵Ŀؼ�
    actionPanel.setPreferredSize(new Dimension(300, 100)); //���ô�С
    actionPanel.add(mydisplay, null);
    this.getContentPane().add(actionPanel, BorderLayout.CENTER);

  }

  private String lastDir = "";
  public void actionPerformed(ActionEvent e) {
    Object obj = e.getSource();
//Open..
    if (obj == jMenuFileOpenFile) {
      jMenuOutputTXT.setEnabled(false);
      jMenuOutputFile.setEnabled(false);
      open_file();
    }

    if (obj == jMenuFileOpenTXT) {
      jMenuOutputTXT.setEnabled(false);
      jMenuOutputFile.setEnabled(false);
      open_TXT();
    }

//save..
    if (obj == jMenuOutputFile) {
      FileDialog d = new FileDialog(this, "save to File", FileDialog.LOAD);
      d.setFile("*.*");
      d.show();
      String f = d.getFile();
      lastDir = d.getDirectory();
      if (f != null) {
        savetofile(lastDir + f);
      }
    }

    if (obj == jMenuOutputTXT) {
      FileDialog d = new FileDialog(this, "save to TXT", FileDialog.LOAD);
      d.setFile("*.*");
      d.show();
      String f = d.getFile();
      lastDir = d.getDirectory();
      if (f != null) {
        savetotxt(lastDir + f);
      }
    }

    //����
    if (obj == jMenuHelpHelp) {
      JOptionPane.showMessageDialog(this, "��������������ļ�ת������1��0���ɵ�txt��\n��.Ҳ��������1��0���ɵ�txt�ı�ת�����ļ�\n���ļ�ת�����txt�ı�����Դ�ļ���С��8��.\n����ʲô���������BUG��E-mail����MSN:\nairzhangfish@hotmail.com,ʮ�ָ�л", "����",
                                    JOptionPane.INFORMATION_MESSAGE);
    }
    //����
    if (obj == jMenuHelpAbout) {
      JOptionPane.showMessageDialog(this, "  ika�ļ�������ת������\n     by airzhangfish \n   �汾 V0.01", "����", JOptionPane.INFORMATION_MESSAGE);
    }
    //����˳�
    if (obj == jMenuFileExit) {
      System.exit(0);
    }

  }

  private void open_file() {
    FileDialog d = new FileDialog(this, "Open File", FileDialog.LOAD);
    d.setFile("*.*");
    d.setDirectory(lastDir);
    d.show();
    String f = d.getFile();
    lastDir = d.getDirectory();
    if (f != null) {
      load_file(lastDir + f);
    }
    if (SDef.binfileMatirx != null) {
      jMenuOutputTXT.setEnabled(true);
    }
    else {
      jMenuOutputTXT.setEnabled(false);
    }
  }

  private void open_TXT() {
    FileDialog d = new FileDialog(this, "Open TXT only incloud 1 and 0", FileDialog.LOAD);
    d.setFile("*.txt");
    d.setDirectory(lastDir);
    d.show();
    String f = d.getFile();
    lastDir = d.getDirectory();
    if (f != null) {
      load_file(lastDir + f);
    }
    if (SDef.binfileMatirx != null) {
      jMenuOutputFile.setEnabled(true);
    }
    else {
      jMenuOutputFile.setEnabled(false);
    }
  }

  public void load_file(String str) {
    SDef.binfileMatirx = null;
//��ȡ��Դ
    File binfile = new File(str);
    try {
      FileInputStream fo = new FileInputStream(binfile);
      SDef.binfileMatirx = new byte[fo.available()];
      fo.read(SDef.binfileMatirx, 0, SDef.binfileMatirx.length);
      fo.close();
    }
    catch (Exception ex) {
      System.out.println("read file or txt error");
    }
  }

  public byte byteXX(byte byte0, byte byte1, byte byte2, byte byte3) {
    byte retrunbyte = 0x00;
    if (byte0 == 0x30 && byte1 == 0x30 && byte2 == 0x30 && byte3 == 0x30) {
      retrunbyte = 0x00;
    }
    if (byte0 == 0x30 && byte1 == 0x30 && byte2 == 0x30 && byte3 == 0x31) {
      retrunbyte = 0x01;
    }
    if (byte0 == 0x30 && byte1 == 0x30 && byte2 == 0x31 && byte3 == 0x30) {
      retrunbyte = 0x02;
    }
    if (byte0 == 0x30 && byte1 == 0x30 && byte2 == 0x31 && byte3 == 0x31) {
      retrunbyte = 0x03;
    }
    if (byte0 == 0x30 && byte1 == 0x31 && byte2 == 0x30 && byte3 == 0x30) {
      retrunbyte = 0x04;
    }
    if (byte0 == 0x30 && byte1 == 0x31 && byte2 == 0x30 && byte3 == 0x31) {
      retrunbyte = 0x05;
    }
    if (byte0 == 0x30 && byte1 == 0x31 && byte2 == 0x31 && byte3 == 0x30) {
      retrunbyte = 0x06;
    }
    if (byte0 == 0x30 && byte1 == 0x31 && byte2 == 0x31 && byte3 == 0x31) {
      retrunbyte = 0x07;
    }
    if (byte0 == 0x31 && byte1 == 0x30 && byte2 == 0x30 && byte3 == 0x30) {
      retrunbyte = 0x08;
    }
    if (byte0 == 0x31 && byte1 == 0x30 && byte2 == 0x30 && byte3 == 0x31) {
      retrunbyte = 0x09;
    }
    if (byte0 == 0x31 && byte1 == 0x30 && byte2 == 0x31 && byte3 == 0x30) {
      retrunbyte = 0x0a;
    }
    if (byte0 == 0x31 && byte1 == 0x30 && byte2 == 0x31 && byte3 == 0x31) {
      retrunbyte = 0x0b;
    }
    if (byte0 == 0x31 && byte1 == 0x31 && byte2 == 0x30 && byte3 == 0x30) {
      retrunbyte = 0x0c;
    }
    if (byte0 == 0x31 && byte1 == 0x31 && byte2 == 0x30 && byte3 == 0x31) {
      retrunbyte = 0x0d;
    }
    if (byte0 == 0x31 && byte1 == 0x31 && byte2 == 0x31 && byte3 == 0x30) {
      retrunbyte = 0x0e;
    }
    if (byte0 == 0x31 && byte1 == 0x31 && byte2 == 0x31 && byte3 == 0x31) {
      retrunbyte = 0x0f;
    }

    return retrunbyte;
  }

  public void savetofile(String str) {

//ת��
    byte[] Char4byte = new byte[SDef.binfileMatirx.length / 4];
    for (int i = 0; i < SDef.binfileMatirx.length / 4; i++) {
      Char4byte[i] = byteXX(SDef.binfileMatirx[i * 4], SDef.binfileMatirx[i * 4 + 1], SDef.binfileMatirx[i * 4 + 2], SDef.binfileMatirx[i * 4 + 3]);
    }
    byte[] char8byte = new byte[Char4byte.length / 2];
    for (int i = 0; i < char8byte.length; i++) {
      char8byte[i] = (byte) ( (Char4byte[i * 2] << 4) + Char4byte[i * 2 + 1]);
    }
//������ļ�
    File pakFile = createFile(str);
    try {
      FileOutputStream fo = new FileOutputStream(pakFile);
      fo.write(char8byte);
      fo.close();
    }
    catch (Exception ex) {
      System.out.println("save file error");
    }

  }

  public void savetotxt(String str) {
//ת��

    byte[] tempMatrix = new byte[SDef.binfileMatirx.length * 8];
    for (int i = 0; i < SDef.binfileMatirx.length; i++) {
      byte tempbyte = (byte) ( ( (SDef.binfileMatirx[i] + 256) >> 4)%16);
      if(tempbyte<0x00||tempbyte>0x0f){
      System.out.println("error top: byte="+tempbyte);
      }
      if (tempbyte == 0x00) {
        tempMatrix[i * 8 + 0] = 0x30;
        tempMatrix[i * 8 + 1] = 0x30;
        tempMatrix[i * 8 + 2] = 0x30;
        tempMatrix[i * 8 + 3] = 0x30;
      }
      if (tempbyte == 0x01) {
        tempMatrix[i * 8 + 0] = 0x30;
        tempMatrix[i * 8 + 1] = 0x30;
        tempMatrix[i * 8 + 2] = 0x30;
        tempMatrix[i * 8 + 3] = 0x31;
      }
      if (tempbyte == 0x02) {
        tempMatrix[i * 8 + 0] = 0x30;
        tempMatrix[i * 8 + 1] = 0x30;
        tempMatrix[i * 8 + 2] = 0x31;
        tempMatrix[i * 8 + 3] = 0x30;
      }
      if (tempbyte == 0x03) {
        tempMatrix[i * 8 + 0] = 0x30;
        tempMatrix[i * 8 + 1] = 0x30;
        tempMatrix[i * 8 + 2] = 0x31;
        tempMatrix[i * 8 + 3] = 0x31;
      }
      if (tempbyte == 0x04) {
        tempMatrix[i * 8 + 0] = 0x30;
        tempMatrix[i * 8 + 1] = 0x31;
        tempMatrix[i * 8 + 2] = 0x30;
        tempMatrix[i * 8 + 3] = 0x30;
      }
      if (tempbyte == 0x05) {
        tempMatrix[i * 8 + 0] = 0x30;
        tempMatrix[i * 8 + 1] = 0x31;
        tempMatrix[i * 8 + 2] = 0x30;
        tempMatrix[i * 8 + 3] = 0x31;
      }
      if (tempbyte == 0x06) {
        tempMatrix[i * 8 + 0] = 0x30;
        tempMatrix[i * 8 + 1] = 0x31;
        tempMatrix[i * 8 + 2] = 0x31;
        tempMatrix[i * 8 + 3] = 0x30;
      }
      if (tempbyte == 0x07) {
        tempMatrix[i * 8 + 0] = 0x30;
        tempMatrix[i * 8 + 1] = 0x31;
        tempMatrix[i * 8 + 2] = 0x31;
        tempMatrix[i * 8 + 3] = 0x31;
      }
      if (tempbyte == 0x08) {
        tempMatrix[i * 8 + 0] = 0x31;
        tempMatrix[i * 8 + 1] = 0x30;
        tempMatrix[i * 8 + 2] = 0x30;
        tempMatrix[i * 8 + 3] = 0x30;
      }
      if (tempbyte == 0x09) {
        tempMatrix[i * 8 + 0] = 0x31;
        tempMatrix[i * 8 + 1] = 0x30;
        tempMatrix[i * 8 + 2] = 0x30;
        tempMatrix[i * 8 + 3] = 0x31;
      }
      if (tempbyte == 0x0a) {
        tempMatrix[i * 8 + 0] = 0x31;
        tempMatrix[i * 8 + 1] = 0x30;
        tempMatrix[i * 8 + 2] = 0x31;
        tempMatrix[i * 8 + 3] = 0x30;
      }
      if (tempbyte == 0x0b) {
        tempMatrix[i * 8 + 0] = 0x31;
        tempMatrix[i * 8 + 1] = 0x30;
        tempMatrix[i * 8 + 2] = 0x31;
        tempMatrix[i * 8 + 3] = 0x31;
      }
      if (tempbyte == 0x0c) {
        tempMatrix[i * 8 + 0] = 0x31;
        tempMatrix[i * 8 + 1] = 0x31;
        tempMatrix[i * 8 + 2] = 0x30;
        tempMatrix[i * 8 + 3] = 0x30;
      }
      if (tempbyte == 0x0d) {
        tempMatrix[i * 8 + 0] = 0x31;
        tempMatrix[i * 8 + 1] = 0x31;
        tempMatrix[i * 8 + 2] = 0x30;
        tempMatrix[i * 8 + 3] = 0x31;
      }
      if (tempbyte == 0x0e) {
        tempMatrix[i * 8 + 0] = 0x31;
        tempMatrix[i * 8 + 1] = 0x31;
        tempMatrix[i * 8 + 2] = 0x31;
        tempMatrix[i * 8 + 3] = 0x30;
      }
      if (tempbyte == 0x0f) {
        tempMatrix[i * 8 + 0] = 0x31;
        tempMatrix[i * 8 + 1] = 0x31;
        tempMatrix[i * 8 + 2] = 0x31;
        tempMatrix[i * 8 + 3] = 0x31;
      }
      tempbyte = (byte) (SDef.binfileMatirx[i] - (tempbyte << 4) + 256);
      if(tempbyte<0x00||tempbyte>0x0f){
System.out.println("error last: byte="+tempbyte);
}
      if (tempbyte == 0x00) {
        tempMatrix[i * 8 + 0 + 4] = 0x30;
        tempMatrix[i * 8 + 1 + 4] = 0x30;
        tempMatrix[i * 8 + 2 + 4] = 0x30;
        tempMatrix[i * 8 + 3 + 4] = 0x30;
      }
      if (tempbyte == 0x01) {
        tempMatrix[i * 8 + 0 + 4] = 0x30;
        tempMatrix[i * 8 + 1 + 4] = 0x30;
        tempMatrix[i * 8 + 2 + 4] = 0x30;
        tempMatrix[i * 8 + 3 + 4] = 0x31;
      }
      if (tempbyte == 0x02) {
        tempMatrix[i * 8 + 0 + 4] = 0x30;
        tempMatrix[i * 8 + 1 + 4] = 0x30;
        tempMatrix[i * 8 + 2 + 4] = 0x31;
        tempMatrix[i * 8 + 3 + 4] = 0x30;
      }
      if (tempbyte == 0x03) {
        tempMatrix[i * 8 + 0 + 4] = 0x30;
        tempMatrix[i * 8 + 1 + 4] = 0x30;
        tempMatrix[i * 8 + 2 + 4] = 0x31;
        tempMatrix[i * 8 + 3 + 4] = 0x31;
      }
      if (tempbyte == 0x04) {
        tempMatrix[i * 8 + 0 + 4] = 0x30;
        tempMatrix[i * 8 + 1 + 4] = 0x31;
        tempMatrix[i * 8 + 2 + 4] = 0x30;
        tempMatrix[i * 8 + 3 + 4] = 0x30;
      }
      if (tempbyte == 0x05) {
        tempMatrix[i * 8 + 0 + 4] = 0x30;
        tempMatrix[i * 8 + 1 + 4] = 0x31;
        tempMatrix[i * 8 + 2 + 4] = 0x30;
        tempMatrix[i * 8 + 3 + 4] = 0x31;
      }
      if (tempbyte == 0x06) {
        tempMatrix[i * 8 + 0 + 4] = 0x30;
        tempMatrix[i * 8 + 1 + 4] = 0x31;
        tempMatrix[i * 8 + 2 + 4] = 0x31;
        tempMatrix[i * 8 + 3 + 4] = 0x30;
      }
      if (tempbyte == 0x07) {
        tempMatrix[i * 8 + 0 + 4] = 0x30;
        tempMatrix[i * 8 + 1 + 4] = 0x31;
        tempMatrix[i * 8 + 2 + 4] = 0x31;
        tempMatrix[i * 8 + 3 + 4] = 0x31;
      }
      if (tempbyte == 0x08) {
        tempMatrix[i * 8 + 0 + 4] = 0x31;
        tempMatrix[i * 8 + 1 + 4] = 0x30;
        tempMatrix[i * 8 + 2 + 4] = 0x30;
        tempMatrix[i * 8 + 3 + 4] = 0x30;
      }
      if (tempbyte == 0x09) {
        tempMatrix[i * 8 + 0 + 4] = 0x31;
        tempMatrix[i * 8 + 1 + 4] = 0x30;
        tempMatrix[i * 8 + 2 + 4] = 0x30;
        tempMatrix[i * 8 + 3 + 4] = 0x31;
      }
      if (tempbyte == 0x0a) {
        tempMatrix[i * 8 + 0 + 4] = 0x31;
        tempMatrix[i * 8 + 1 + 4] = 0x30;
        tempMatrix[i * 8 + 2 + 4] = 0x31;
        tempMatrix[i * 8 + 3 + 4] = 0x30;
      }
      if (tempbyte == 0x0b) {
        tempMatrix[i * 8 + 0 + 4] = 0x31;
        tempMatrix[i * 8 + 1 + 4] = 0x30;
        tempMatrix[i * 8 + 2 + 4] = 0x31;
        tempMatrix[i * 8 + 3 + 4] = 0x31;
      }
      if (tempbyte == 0x0c) {
        tempMatrix[i * 8 + 0 + 4] = 0x31;
        tempMatrix[i * 8 + 1 + 4] = 0x31;
        tempMatrix[i * 8 + 2 + 4] = 0x30;
        tempMatrix[i * 8 + 3 + 4] = 0x30;
      }
      if (tempbyte == 0x0d) {
        tempMatrix[i * 8 + 0 + 4] = 0x31;
        tempMatrix[i * 8 + 1 + 4] = 0x31;
        tempMatrix[i * 8 + 2 + 4] = 0x30;
        tempMatrix[i * 8 + 3 + 4] = 0x31;
      }
      if (tempbyte == 0x0e) {
        tempMatrix[i * 8 + 0 + 4] = 0x31;
        tempMatrix[i * 8 + 1 + 4] = 0x31;
        tempMatrix[i * 8 + 2 + 4] = 0x31;
        tempMatrix[i * 8 + 3 + 4] = 0x30;
      }
      if (tempbyte == 0x0f) {
        tempMatrix[i * 8 + 0 + 4] = 0x31;
        tempMatrix[i * 8 + 1 + 4] = 0x31;
        tempMatrix[i * 8 + 2 + 4] = 0x31;
        tempMatrix[i * 8 + 3 + 4] = 0x31;
      }
    }

    File pakFile = createFile(str + ".txt");
    try {
      FileOutputStream fo = new FileOutputStream(pakFile);
      fo.write(tempMatrix); //byte[]
      fo.close();
    }
    catch (Exception ex) {
      System.out.println("save txt error");
    }

  }

  private static File createFile(String fileName) {
    File file = new File(fileName);
    if (file == null) {
      return null;
    }
    if (file.isDirectory()) {
      return null;
    }
    if (file.exists()) {
      file.delete();
    }
    try {
      file.createNewFile();
    }
    catch (Exception ex) {
      ex.printStackTrace();
    }
    return file;
  }

}
