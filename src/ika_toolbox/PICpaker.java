package ika_toolbox;
import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import javax.swing.*;

public class PICpaker extends JPanel implements ActionListener {
	private static final long serialVersionUID = 1L;
	private static JButton loadbin = new JButton("选取打包文件");
	private static JLabel step2label = new JLabel("Step 2: 打包说明");

	public PICpaker() {
		this.setLayout(new GridLayout(7, 1));
		this.add(new JLabel("图片资源打包工具"));
		this.add(new JLabel("Step 1: 选择要打包的文件"));
		this.add(loadbin);
		this.add(step2label);
		loadbin.addActionListener(this);
	}

	public void actionPerformed(ActionEvent arg0) {
		Object obj = arg0.getSource();
		// Open..
		if (obj == loadbin) {
			open_file();
		}
	}

	private void open_file() {
		JFileChooser c = new JFileChooser();
		c.setMultiSelectionEnabled(true);
		int rVal = c.showOpenDialog(this);
		if (rVal == JFileChooser.APPROVE_OPTION) {
			File[] files = c.getSelectedFiles();
			if (files != null) {
				pic_pack(files);
			}
		}

	}
public void pic_pack(File[] file){
	  getPNG(file);
	  output_Res(file);
	  output_TXT(file);
}


static byte[] PNGbox;
static int PNGlong = 0;
static long total_PNG_length = 0;

public static void getPNG(File[] file) {
	total_PNG_length=0;
	PNGlong = 0;
  for (int i = 0; i < file.length; i++) {
    total_PNG_length = total_PNG_length + file[i].length();
  }
  PNGlong = (int) total_PNG_length + 2 * (file.length) + 2;
  PNGbox = new byte[PNGlong];
  int PNGBOXID = 0;
  PNGbox[PNGBOXID] = (byte) ( (file.length) >> 8);
  PNGBOXID++;
  PNGbox[PNGBOXID] = (byte) ( (file.length) - (PNGbox[PNGBOXID] << 8));
  PNGBOXID++;
  for (int i = 0; i < file.length; i++) {
    PNGbox[PNGBOXID] = (byte) (file[i].length() >> 8);
    PNGBOXID++;
    PNGbox[PNGBOXID] = (byte) (file[i].length() - ( ( (int) PNGbox[PNGBOXID]) << 8));
    PNGBOXID++;
    try {
      FileInputStream fo = new FileInputStream(file[i]);
      byte[] mx=new byte[fo.available()];
      fo.read(mx);
      for (int j = 0; j < mx.length; j++) {
        PNGbox[PNGBOXID] = mx[j];
        PNGBOXID++;
      }
    }
    catch (Exception ex) {
    	step2label.setText("Step 2: 打包失败，请检查选取文件的状态");
      System.out.println("read " + file[i] + " error");
    }
  }
}

public static void output_TXT(File[] file) {
  File txtFile = SDef.createFile(file[0].getParent()+"\\imgpak.txt");
  String outputpackerinfo = "//图片打包数据\r\n\r\n";
  for (int i = 0; i < file.length; i++) {
    outputpackerinfo = outputpackerinfo + " public static final byte " + file[i].getName().substring(0, file[i].getName().length() - 4) + " = " + i + ";   //" +  file[i].getName()+ "\r\n";
  }
  outputpackerinfo=outputpackerinfo+addsrc;
  try {
    FileOutputStream fo = new FileOutputStream(txtFile);
    fo.write(outputpackerinfo.getBytes());
    fo.close();
    System.out.println("paktxt over");
  }
  catch (Exception ex) {
		step2label.setText("Step 2: 打包失败，请检查选取文件的状态");
    System.out.println("paktxt error");
  }
}

public static void output_Res(File[] file) {
  File pakFile = SDef.createFile(file[0].getParent()+"\\imgpak.bin");
  try {
    FileOutputStream fo = new FileOutputStream(pakFile);
    fo.write(PNGbox);
    fo.close();
    System.out.println("pak over");
	step2label.setText("Step 2: 打包成功，请查看文件夹下的imgpak.txt和imgpak.bin文件");
  }
  catch (Exception ex) {
		step2label.setText("Step 2: 打包失败，请检查选取文件的状态");
    System.out.println("output_Res error");
  }
}



static String addsrc="\r\n\r\n\r\n//copy到代码里面读取打包资源，如果要读取其中的文件可以用skip()自行添加\r\nImage[] imgpak;"+"\r\n"+
"public void loadImagepak(String path) {"+"\r\n"+
 " InputStream is = this.getClass().getResourceAsStream(path);"+"\r\n"+
"  try {"+"\r\n"+
"    int totalImage = is.read();"+"\r\n"+
"    imgpak=new Image[totalImage];"+"\r\n"+
"    for (int i = 0; i < totalImage; i++) {"+"\r\n"+
"      int imagelength = (is.read() << 8) + is.read();"+"\r\n"+
"      byte[] imgmrtix = new byte[imagelength];"+"\r\n"+
 "     is.read(imgmrtix, 0, imagelength);"+"\r\n"+
 "     imgpak[i] = Image.createImage(imgmrtix, 0, imagelength);"+"\r\n"+
 "   }"+"\r\n"+
"  }"+"\r\n"+
"  catch (Exception e) {"+"\r\n"+
"    System.out.println(\"imagepak read error\");"+"\r\n"+
 " }"+"\r\n"+
"}"+"\r\n";

}
