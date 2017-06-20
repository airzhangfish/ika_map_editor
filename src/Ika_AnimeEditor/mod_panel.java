package Ika_AnimeEditor;
import javax.swing.*;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.imageio.ImageIO;
import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;
import java.awt.image.BufferedImage;
import javax.swing.filechooser.FileFilter;

import java.io.File;
import java.awt.event.KeyEvent;

/**
 * <p>
 * Title:ika �����༭��
 * </p>
 * <p>
 * Description: �༭ͼƬ��֡���γɶ�������������ֻ�����
 * </p>
 * <p>
 * Copyright: airzhangfish Copyright (c) 2007
 * </p>
 * <p>
 * blog: http://airzhangfish.spaces.live.com
 * </p>
 * <p>
 * Company: Comicfishing
 * </p>
 * <p>
 * author airzhangfish
 * </p>
 * <p>
 * version 0.03a standard
 * </p>
 * <p>
 * last updata 2007-8-23
 * </p>
 * mod_panel ���༭����
 */

public class mod_panel extends JPanel {

	private static final long serialVersionUID = 1L;
	mod_image BigImagePanel = new mod_image();
	static mod_one_image smalImagePanel = new mod_one_image();
	static JList wordList = new JList(SDef.mod_listModel);
	JScrollPane scrollPane = new JScrollPane(wordList);
	static JList ImageList = new JList(SDef.image_listModel);
	JScrollPane scrollPane2 = new JScrollPane(ImageList);

	JPanel rightPanel = new JPanel(new BorderLayout());
	JPanel leftPanel = new JPanel(new BorderLayout());
	JPanel buttonPanel = new JPanel(new GridLayout(1, 8));
	JPanel buttonPanel2 = new JPanel(new GridLayout(1, 3));
	JButton button_image_up = new JButton("����");
	JButton button_image_down = new JButton("����");
	JButton button_image_del = new JButton("ɾ��ͼƬ");

	JButton button_LOAD = new JButton("��ȡͼƬ");
	JButton button_BIG = new JButton("�Ŵ�"); // ��ݼ� =
	JButton button_SMALL = new JButton("��С"); // ��ݼ� -
	JButton button_DEL = new JButton("ԭ��");
	JButton button_ENTER = new JButton("ɾ�����"); // ��ݼ� Del

	AffineTransform transform;
	BufferedImage temp_bfimage;

	FileFilter flefilter = new FileFilter() {
		public boolean accept(File f) {
			return f.getName().toLowerCase().endsWith(".png") || f.isDirectory();
		}

		public String getDescription() {
			return "PNG Files";
		}
	};

	static int imagetotal = 0;
	ActionListener actionListener = new ActionListener() {
		public void actionPerformed(ActionEvent actionEvent) {
			Object source = actionEvent.getSource();
              System.gc();
			// ��ȡ��Դ
			if (source == button_LOAD) {
				JFileChooser c = new JFileChooser();
				c.setFileFilter(flefilter);
				int rVal = c.showOpenDialog(mod_panel.this);
				if (rVal == JFileChooser.APPROVE_OPTION) {
					String mame = c.getCurrentDirectory().toString() + "\\" + c.getSelectedFile().getName();
					System.out.println(mame);
					File pngfile = new File(mame);
					try {
						for (int i = 0; i < 1000; i++) {
							if (SDef.mod_big_bfimage[i] == null) {
								imagetotal = i;
								break;
							}
						}
						SDef.big_bfimage_path[imagetotal] = mame;
						SDef.mod_big_bfimage[imagetotal] = ImageIO.read(pngfile);
						ImageIO.write(SDef.mod_big_bfimage[imagetotal], "png", pngfile);
						SDef.static_mod_big_bfimage[imagetotal] = SDef.mod_big_bfimage[imagetotal];
						// �����б�
						String[] tempstring = new String[imagetotal + 1];
						for (int i = 0; i < tempstring.length; i++) {
							if (i == tempstring.length - 1) {
								tempstring[i] = "Image:" + c.getSelectedFile().getName();
							} else {
								tempstring[i] = SDef.mod_Image_name[i];
							}
						}
						SDef.mod_Image_name = tempstring;
						SDef.image_listModel.clear();
						for (int i = 0; i < SDef.mod_Image_name.length; i++) {
							SDef.image_listModel.addElement(SDef.mod_Image_name[i]);
						}
						ImageList.setSelectedIndex(imagetotal);
					} catch (Exception ex) {
						JOptionPane.showMessageDialog(null, "��ȡ��Դ���������Ƿ��ʽ����", "����", JOptionPane.ERROR_MESSAGE);
					}
					BigImagePanel.update_Srceen();
				}
			}
			// Image�Ŵ�
			if (source == button_BIG) {
				if (SDef.mod_big_bfimage == null) {
					return;
				}
				SDef.mod_size++;
				if (SDef.mod_size > 8) {
					SDef.mod_size = 8;
				}
				temp_bfimage = null;
					temp_bfimage = new BufferedImage((int) (SDef.static_mod_big_bfimage[ImageList.getSelectedIndex()].getWidth() * SDef.mod_size),
							(int) (SDef.static_mod_big_bfimage[ImageList.getSelectedIndex()].getHeight() * SDef.mod_size),
							BufferedImage.TYPE_INT_ARGB);
					transform.setToScale(SDef.mod_size, SDef.mod_size);
					AffineTransformOp op = new AffineTransformOp(transform, null);
					op.filter(SDef.static_mod_big_bfimage[ImageList.getSelectedIndex()], temp_bfimage);
					SDef.mod_big_bfimage[ImageList.getSelectedIndex()] = temp_bfimage;
					BigImagePanel.update_Srceen();
					System.gc();
			}
			// Image��С
			if (source == button_SMALL) {
				SDef.mod_size--;
				if (SDef.mod_size < 1) {
					SDef.mod_size = 1;
				}
				temp_bfimage = null;
				temp_bfimage = new BufferedImage((int) (SDef.static_mod_big_bfimage[ImageList.getSelectedIndex()].getWidth() * SDef.mod_size),
						(int) (SDef.static_mod_big_bfimage[ImageList.getSelectedIndex()].getHeight() * SDef.mod_size), BufferedImage.TYPE_INT_ARGB);
				transform.setToScale(SDef.mod_size, SDef.mod_size);
				AffineTransformOp op = new AffineTransformOp(transform, null);
				op.filter(SDef.static_mod_big_bfimage[ImageList.getSelectedIndex()], temp_bfimage);
				SDef.mod_big_bfimage[ImageList.getSelectedIndex()] = temp_bfimage;
				BigImagePanel.update_Srceen();
				System.gc();
			}

			// ��ԭͼƬ
			if (source == button_DEL) {
				SDef.mod_size = 1;
				SDef.mod_big_bfimage = SDef.static_mod_big_bfimage;
				BigImagePanel.update_Srceen();
			}

			// ɾ��
			if (source == button_ENTER) {
				int[] temp = new int[wordList.getSelectedIndices().length];
				temp = wordList.getSelectedIndices();
				
		        //����Ƿ�����ʹ��
		        String usedframe="";
		        	  boolean can_del=true;
		              for (int j = 0; j < temp.length; j++) {
		            	  for(int i=0;i<SDef.G_framebox.length;i++){
		            		  for(int k=2;k<SDef.G_framebox[i].length;k=k+4){
		            		  if(temp[j]==SDef.G_framebox[i][k]){
		            			  can_del=false;
		            			  usedframe=usedframe+i+",";
		            			  }
		            		  }
		            	  }
		              }
		        	  
		              usedframe="���ڵ��ô�����֡�У�\n"+usedframe +"\n����ɾ��";
		        	  if(can_del==false){
		        		  	JOptionPane.showMessageDialog(null, usedframe, "����", JOptionPane.ERROR_MESSAGE);
		        	  }else{

				for (int j = 0; j < temp.length; j++) {
					for (int i = 0; i < SDef.mod_words.length; i++) {
						SDef.G_modbox[temp[j] + i - j][SDef.mod_X] = SDef.G_modbox[temp[j] + i - j + 1][SDef.mod_X];
						SDef.G_modbox[temp[j] + i - j][SDef.mod_Y] = SDef.G_modbox[temp[j] + i - j + 1][SDef.mod_Y];
						SDef.G_modbox[temp[j] + i - j][SDef.mod_W] = SDef.G_modbox[temp[j] + i - j + 1][SDef.mod_W];
						SDef.G_modbox[temp[j] + i - j][SDef.mod_H] = SDef.G_modbox[temp[j] + i - j + 1][SDef.mod_H];
						SDef.mod_bfimage[temp[j] + i - j]=SDef.mod_bfimage[temp[j] + i - j + 1];
					}

					if (SDef.mod_words.length < 1) {
						return;
					}
					SDef.G_modbox[SDef.mod_words.length - 1][SDef.mod_USE] = 0;
					SDef.mod_words = new String[SDef.mod_words.length - 1];
					for (int i = 0; i < SDef.mod_words.length; i++) {
						SDef.mod_words[i] = "mod_" + i + ":" + SDef.G_modbox[i][SDef.mod_X] + "," + SDef.G_modbox[i][SDef.mod_Y] + ","
								+ SDef.G_modbox[i][SDef.mod_W] + "," + SDef.G_modbox[i][SDef.mod_H];
					}
				}
				SDef.mod_listModel.clear();
				for (int i = 0; i < SDef.mod_words.length; i++) {
					SDef.mod_listModel.addElement(SDef.mod_words[i]);
				}
				wordList.setSelectedIndex(temp[0]);
				
				//֡�е����ݸ���
	              for (int j = 0; j < temp.length; j++) {
	            	  for(int i=0;i<SDef.G_framebox.length;i++){
	            		  for(int k=2;k<SDef.G_framebox[i].length;k=k+4){
	            		  if(SDef.G_framebox[i][k]>temp[j]){
	            			  SDef.G_framebox[i][k]--;
	            			  }
	            		  }
	            	  }
	              }
				
	              
	              
	              
				}
			}
		        	  
		        	  
			if (source == button_image_del) {
				int ImageSI = ImageList.getSelectedIndex();
				SDef.mod_big_bfimage[ImageSI] = null;
				SDef.static_mod_big_bfimage[ImageSI] = null;
				SDef.big_bfimage_path[ImageSI] = "";
				for (int i = ImageSI; i < 1000; i++) {
					SDef.mod_big_bfimage[ImageSI] = SDef.mod_big_bfimage[ImageSI + 1];
					SDef.static_mod_big_bfimage[ImageSI] = SDef.static_mod_big_bfimage[ImageSI + 1];
					SDef.big_bfimage_path[ImageSI] = SDef.big_bfimage_path[ImageSI + 1];
				}
				String[] tempstr = new String[SDef.mod_Image_name.length - 1];
				for (int i = 0; i < tempstr.length; i++) {
					if (i < ImageSI) {
						tempstr[i] = SDef.mod_Image_name[i];
					} else if (i >= ImageSI) {
						tempstr[i] = SDef.mod_Image_name[i + 1];
					}
				}
				SDef.mod_Image_name = tempstr;
				SDef.image_listModel.clear();
				for (int i = 0; i < SDef.mod_Image_name.length; i++) {
					SDef.image_listModel.addElement(SDef.mod_Image_name[i]);
				}
				ImageList.setSelectedIndex(imagetotal);

			}
		}
	};

	public mod_panel() {

		transform = new AffineTransform();
		wordList.setDragEnabled(true);
		setLayout(new BorderLayout());
		scrollPane.setPreferredSize(new Dimension(200, 100));
		scrollPane2.setPreferredSize(new Dimension(200, 100));
		buttonPanel.setPreferredSize(new Dimension(100, 25));
		buttonPanel2.setPreferredSize(new Dimension(100, 25));
		smalImagePanel.setPreferredSize(new Dimension(200, 200));
		buttonPanel.setLayout(new GridLayout(1, 8));
		buttonPanel.add(button_LOAD);
		button_LOAD.setToolTipText("��ȡ��Դ��ͼ");
		buttonPanel.add(button_BIG);
		button_BIG.setToolTipText("�Ŵ�ͼƬ��ע�ⲻҪ�Ŵ���ർ���ڴ�ռ�ó�������");
		buttonPanel.add(button_SMALL);
		button_SMALL.setToolTipText("��СͼƬ");
		buttonPanel.add(button_DEL);
		button_DEL.setToolTipText("��ʾԭʼ��С");
		buttonPanel.add(button_ENTER);
		button_ENTER.setToolTipText("ɾ����ѡ�е�ͼƬ");
		button_LOAD.registerKeyboardAction(actionListener, "button_LOAD", KeyStroke.getKeyStroke(KeyEvent.VK_F2, 0, false),
				JComponent.WHEN_IN_FOCUSED_WINDOW);
		button_BIG.registerKeyboardAction(actionListener, "button_BIG", KeyStroke.getKeyStroke(KeyEvent.VK_EQUALS, 0, false),
				JComponent.WHEN_IN_FOCUSED_WINDOW);
		button_SMALL.registerKeyboardAction(actionListener, "button_SMALL", KeyStroke.getKeyStroke(KeyEvent.VK_MINUS, 0, false),
				JComponent.WHEN_IN_FOCUSED_WINDOW);
		button_ENTER.registerKeyboardAction(actionListener, "button_ENTER", KeyStroke.getKeyStroke(KeyEvent.VK_DELETE, 0, false),
				JComponent.WHEN_IN_FOCUSED_WINDOW);

		button_LOAD.addActionListener(actionListener);
		button_BIG.addActionListener(actionListener);
		button_SMALL.addActionListener(actionListener);
		button_DEL.addActionListener(actionListener);
		button_ENTER.addActionListener(actionListener);

		// buttonPanel2.add(button_image_up);
		// buttonPanel2.add(button_image_down);
		// buttonPanel2.add(button_image_del);
		// button_image_up.addActionListener(actionListener);
		// button_image_down.addActionListener(actionListener);
		// button_image_del.addActionListener(actionListener);

		leftPanel.add(buttonPanel2, BorderLayout.SOUTH);
		leftPanel.add(scrollPane2, BorderLayout.CENTER);

		rightPanel.add(smalImagePanel, BorderLayout.NORTH);
		rightPanel.add(scrollPane, BorderLayout.CENTER);

		this.add(leftPanel, BorderLayout.WEST);
		this.add(buttonPanel, BorderLayout.SOUTH);
		this.add(BigImagePanel, BorderLayout.CENTER);
		this.add(rightPanel, BorderLayout.EAST);
	}
}
