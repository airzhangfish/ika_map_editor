package ika_toolbox;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.io.*;


public class PNGhacker extends JPanel   implements ActionListener {
	private static final long serialVersionUID = 1L;
	private static JButton loadbin=new JButton("读取数据包");
	private static JButton improtpng=new JButton("将资源导出");
		private static String lastpathname = "";
	private static JLabel step1label=new JLabel(""+lastpathname);
	private static JLabel step2label=new JLabel("Step 2: 如果发现图片资源,请点选资源导出;如果没有发现,按钮为灰色");
	public PNGhacker() {
		this.setLayout(new GridLayout(7, 1));
		this.add(new JLabel("手机游戏数据包破解工具"));
		this.add(new JLabel("Step 1: 选择要破解的游戏中的图片数据包"));
		this.add(loadbin);
		this.add(step1label);
		this.add(step2label);
		this.add(improtpng);
		improtpng.setEnabled(false);
		loadbin.addActionListener(this);
		improtpng.addActionListener(this);
	}

	public void actionPerformed(ActionEvent arg0) {
		 Object obj = arg0.getSource();
		    if (obj == loadbin) {
		      open_file();
		    }
		    if (obj == improtpng) {
		    	save_file() ;
			    }	
	}
	
	
	private static String lastDir = "";
	private void open_file() {
        JFileChooser c = new JFileChooser();
        int rVal = c.showOpenDialog(this);
        if (rVal == JFileChooser.OPEN_DIALOG) {
             String biname = c.getSelectedFile().getName();
           lastDir = c.getCurrentDirectory().toString();
          System.out.println("open file:"+c.getCurrentDirectory().toString() + "\\" + c.getSelectedFile().getName());
  		if (biname != null) {
  			lastpathname=lastDir+ "\\" + biname;
			loadPNG(lastDir+ "\\" + biname);
		}
		if (SDef.imagenumber <= 0) {
			step1label.setText(""+lastpathname);
			step2label.setText("Step 2: 没有在这个资源里发现图片资源");
			improtpng.setEnabled(false);
		} else {
			step1label.setText(""+lastpathname);
			step2label.setText("Step 2: 在这个资源包里面发现了"+SDef.imagenumber+"张PNG图片");
			improtpng.setEnabled(true);
		}	 	
        }else{
      	  System.out.println("open  file cancelled ");
        }
     
		
	}

	private void save_file() {
        JFileChooser c = new JFileChooser();
        int rVal = c.showSaveDialog(this);
        if (rVal == JFileChooser.SAVE_DIALOG) {
        	      String biname = c.getSelectedFile().getName();
           lastDir = c.getCurrentDirectory().toString();
          System.out.println("save file:"+c.getCurrentDirectory().toString() + "\\" + c.getSelectedFile().getName());
  		if (biname != null) {
  			savePNGs(lastDir+ "\\" + biname);
		}
        }else{
  	      String biname = c.getSelectedFile().getName();
          lastDir = c.getCurrentDirectory().toString();
         System.out.println("save file:"+c.getCurrentDirectory().toString() + "\\" + c.getSelectedFile().getName());
 		if (biname != null) {
 			savePNGs(lastDir+ "\\" + biname);
		}
  
        }
    
	
	}
	
	
	private static int[] start_matrix = new int[2000];
	private static byte[] png = { (byte) 0x89, (byte) 0x50, (byte) 0x4e, (byte) 0x47, (byte) 0x0d, (byte) 0x0a, (byte) 0x1a, (byte) 0x0a };

	private static byte[] png2 = { (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x0d, (byte) 0x49, (byte) 0x48, (byte) 0x44, (byte) 0x52 };

	private static byte[] png3 = { (byte) 0x50, (byte) 0x4c, (byte) 0x54, (byte) 0x45 };

	private static byte[] png_rsp = { (byte) 0x31, (byte) 0x2e, (byte) 0x32, (byte) 0x30 };

	private static byte[] PNGpakFiles;

	private static void loadPNG(String str) {
		SDef.binfile = null;
		SDef.imagenumber = 0;
		// 读取资源完毕
		SDef.binfile = new File(str);
		try {
			FileInputStream fo = new FileInputStream(SDef.binfile);
			PNGpakFiles = new byte[fo.available()];
			fo.read(PNGpakFiles, 0, PNGpakFiles.length);
			fo.close();
		} catch (Exception ex) {
			System.out.println("read pak error");
		}
		// 分析有几张图片,开始位置
		// 方法0,改进版本
		for (int i = 0; i < PNGpakFiles.length - 8; i++) {
			if (PNGpakFiles[i] == png2[0] && PNGpakFiles[i + 1] == png2[1] && PNGpakFiles[i + 2] == png2[2] && PNGpakFiles[i + 3] == png2[3]
					&& PNGpakFiles[i + 4] == png2[4] && PNGpakFiles[i + 5] == png2[5] && PNGpakFiles[i + 6] == png2[6]
					&& PNGpakFiles[i + 7] == png2[7]) {
				SDef.imagenumber++;
				start_matrix[SDef.imagenumber - 1] = i;
			}
		}
		// 方法2
		if (SDef.imagenumber == 0) {
			for (int i = 0; i < PNGpakFiles.length - 10; i++) {
				if (PNGpakFiles[i] == png3[0] && PNGpakFiles[i + 1] == png3[1] && PNGpakFiles[i + 2] == png3[2] && PNGpakFiles[i + 3] == png3[3]) {
					SDef.imagenumber++;
					start_matrix[SDef.imagenumber - 1] = i;
					SDef.fangfa = 2;
				}
			}
		}

		// 方法1
		if (SDef.imagenumber == 0) {
			for (int j = 0; j < 255; j++) {

				for (int i = 0; i < PNGpakFiles.length - 8; i++) {
					if (PNGpakFiles[i] == (byte) (png2[0] + j) && PNGpakFiles[i + 1] == (byte) (png2[1] + j)
							&& PNGpakFiles[i + 2] == (byte) (png2[2] + j) && PNGpakFiles[i + 3] == (byte) (png2[3] + j)
							&& PNGpakFiles[i + 4] == (byte) (png2[4] + j) && PNGpakFiles[i + 5] == (byte) (png2[5] + j)
							&& PNGpakFiles[i + 6] == (byte) (png2[6] + j) && PNGpakFiles[i + 7] == (byte) (png2[7] + j)) {
						SDef.imagenumber++;
						SDef.offset = (byte) j;
						start_matrix[SDef.imagenumber - 1] = i;
					}
				}
				if (SDef.imagenumber > 0) {
					SDef.fangfa = 1;
					break;
				}
			}
		}
		// 方法3 RSP破解法
		if (SDef.imagenumber == 0) {
			if (PNGpakFiles[0] == png_rsp[0] && PNGpakFiles[1] == png_rsp[1] && PNGpakFiles[2] == png_rsp[2] && PNGpakFiles[3] == png_rsp[3]) {
				SDef.fangfa = 3; // rsp破解法
				SDef.imagenumber = 1;
			}
		}

	}

	
	
	
	
	private void savePNGs(String str) {
		// 分离bytes
		// 方法0
		if (SDef.fangfa == 0) {
			byte[][] imagess = new byte[SDef.imagenumber][];
			for (int i = 0; i < SDef.imagenumber; i++) {
				if (i == SDef.imagenumber - 1) {
					imagess[i] = new byte[PNGpakFiles.length - start_matrix[i] + 8];
				} else {
					imagess[i] = new byte[start_matrix[i + 1] - start_matrix[i] + 8];
				}

				imagess[i][0] = png[0];
				imagess[i][1] = png[1];
				imagess[i][2] = png[2];
				imagess[i][3] = png[3];
				imagess[i][4] = png[4];
				imagess[i][5] = png[5];
				imagess[i][6] = png[6];
				imagess[i][7] = png[7];
				for (int j = 0; j < imagess[i].length - 8; j++) {
					imagess[i][j + 8] = PNGpakFiles[start_matrix[i] + j];
				}

				// 保存PNG
			}

			for (int i = 0; i < SDef.imagenumber; i++) {
				File pakFile = SDef.createFile(str + "_" + i + ".png");
				try {
					FileOutputStream fo = new FileOutputStream(pakFile);
					fo.write(imagess[i]);
					fo.close();
				} catch (Exception ex) {
					System.out.println("save png error");
				}
			}
		} else if (SDef.fangfa == 1) { //
			// 方法1
			byte[][] imagess = new byte[SDef.imagenumber][];
			for (int i = 0; i < SDef.imagenumber; i++) {
				if (i == SDef.imagenumber - 1) {
					imagess[i] = new byte[PNGpakFiles.length - start_matrix[i] + 8];
				} else {
					imagess[i] = new byte[start_matrix[i + 1] - start_matrix[i] + 8];
				}

				imagess[i][0] = (byte) (png[0]);
				imagess[i][1] = (byte) (png[1]);
				imagess[i][2] = (byte) (png[2]);
				imagess[i][3] = (byte) (png[3]);
				imagess[i][4] = (byte) (png[4]);
				imagess[i][5] = (byte) (png[5]);
				imagess[i][6] = (byte) (png[6]);
				imagess[i][7] = (byte) (png[7]);
				for (int j = 0; j < imagess[i].length - 8; j++) {
					imagess[i][j + 8] = (byte) (PNGpakFiles[start_matrix[i] + j] - SDef.offset);
				}
				// 保存PNG
			}
			for (int i = 0; i < SDef.imagenumber; i++) {
				File pakFile = SDef.createFile(str + "_" + i + ".png");
				try {
					FileOutputStream fo = new FileOutputStream(pakFile);
					fo.write(imagess[i]);
					fo.close();
				} catch (Exception ex) {
					System.out.println("save png error");
				}
			}
		} else if (SDef.fangfa == 2) { // 方法2
			byte[][] imagess = new byte[SDef.imagenumber][];
			for (int i = 0; i < SDef.imagenumber; i++) {
				if (i == SDef.imagenumber - 1) {
					imagess[i] = new byte[PNGpakFiles.length - start_matrix[i] + 16];
				} else {
					imagess[i] = new byte[start_matrix[i + 1] - start_matrix[i] + 16];
				}

				imagess[i][0] = png[0];
				imagess[i][1] = png[1];
				imagess[i][2] = png[2];
				imagess[i][3] = png[3];
				imagess[i][4] = png[4];
				imagess[i][5] = png[5];
				imagess[i][6] = png[6];
				imagess[i][7] = png[7];
				imagess[i][8] = png2[0];
				imagess[i][9] = png2[1];
				imagess[i][10] = png2[2];
				imagess[i][11] = png2[3];
				imagess[i][12] = png2[4];
				imagess[i][13] = png2[5];
				imagess[i][14] = png2[6];
				imagess[i][15] = png2[7];
				System.out.println( imagess[i].length);
				for (int j = 0; j < imagess[i].length - 20; j++) {
				//	if(start_matrix[i] + j - 21>=0){
					imagess[i][j + 16] = PNGpakFiles[start_matrix[i] + j - 21];
				}

				// 保存PNG
			}

			for (int i = 0; i < SDef.imagenumber; i++) {
				File pakFile = SDef.createFile(str + "_" + i + ".png");
				try {
					FileOutputStream fo = new FileOutputStream(pakFile);
					fo.write(imagess[i]);
					fo.close();
				} catch (Exception ex) {
					System.out.println("save png error");
				}
			}
		} else if (SDef.fangfa == 3) { // rsp算法
			// 方法3
			byte[][] imagess = new byte[SDef.imagenumber][];
			for (int i = 0; i < SDef.imagenumber; i++) {
				start_matrix[i] = 0;
				imagess[i] = new byte[PNGpakFiles.length + 8];
		
				imagess[i][0] = png[0];
				imagess[i][1] = png[1];
				imagess[i][2] = png[2];
				imagess[i][3] = png[3];
				imagess[i][4] = png[4];
				imagess[i][5] = png[5];
				imagess[i][6] = png[6];
				imagess[i][7] = png[7];
				imagess[i][8] = png2[0];
				imagess[i][9] = png2[1];
				imagess[i][10] = png2[2];
				imagess[i][11] = png2[3];
				imagess[i][12] = png2[4];
				imagess[i][13] = png2[5];
				imagess[i][14] = png2[6];
				imagess[i][15] = png2[7];
				for (int j = 0; j < imagess[i].length - 16; j++) {
					imagess[i][j + 16] = PNGpakFiles[start_matrix[i] + j + 8];
				}
				for (int j = 16; j < imagess[i].length; j = j + 8) {
					imagess[i][j] = (byte) (~(imagess[i][j] - 170));
				}
				// 保存PNG
			}

			for (int i = 0; i < SDef.imagenumber; i++) {
				File pakFile = SDef.createFile(str + "_" + i + ".png");
				try {
					FileOutputStream fo = new FileOutputStream(pakFile);
					fo.write(imagess[i]);
					fo.close();
				} catch (Exception ex) {
					System.out.println("save png error");
				}
			}

		}

		else {
			System.out.println("方法错误");
		}
	}



}
