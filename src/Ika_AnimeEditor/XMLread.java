package Ika_AnimeEditor;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import javax.imageio.ImageIO;
import javax.swing.JOptionPane;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

public class XMLread {

	String[] frameString;
	String[] animeString;
	int ImageNumber = 0;
	int ModuleMaxNumber = 0;
	int MaxframeNumber = 0;
	int MaxanimeNumber = 0;

	public XMLread(String namepath) {

		DocumentBuilderFactory domfac = DocumentBuilderFactory.newInstance();
		try {
			DocumentBuilder dombuilder = domfac.newDocumentBuilder();
			InputStream is = new FileInputStream(namepath);
			Document doc = dombuilder.parse(is);
			Element root = doc.getDocumentElement();
			NodeList ikaanime = root.getChildNodes();

			if (ikaanime != null) {

				SDef.G_modbox = new int[1000][6];
				SDef.G_framebox = new int[1000][2];
				SDef.G_animebox = new int[1000][2];
				frameString = new String[1000];
				animeString = new String[1000];
				ImageNumber = 0;
				ModuleMaxNumber = 0;
				MaxframeNumber = 0;
				MaxanimeNumber = 0;
				for (int kk = 0; kk < ikaanime.getLength(); kk++) {
					Node book = ikaanime.item(kk);

					if (book.getNodeType() == Node.ELEMENT_NODE) {
						if (book.getNodeName().equals("Image")) {
							SDef.big_bfimage_path[ImageNumber] = book.getAttributes().getNamedItem("path").getNodeValue();
							File pngfile = new File(SDef.big_bfimage_path[ImageNumber]);
							SDef.mod_big_bfimage[ImageNumber] = ImageIO.read(pngfile);
							SDef.static_mod_big_bfimage[ImageNumber] = SDef.mod_big_bfimage[ImageNumber];
							ImageNumber++;
						} else if (book.getNodeName().equals("Module")) {
							// 物块
							SDef.G_modbox[ModuleMaxNumber][SDef.mod_X] = Integer.parseInt(book.getAttributes().getNamedItem("x").getNodeValue());
							SDef.G_modbox[ModuleMaxNumber][SDef.mod_Y] = Integer.parseInt(book.getAttributes().getNamedItem("y").getNodeValue());
							SDef.G_modbox[ModuleMaxNumber][SDef.mod_W] = Integer.parseInt(book.getAttributes().getNamedItem("w").getNodeValue());
							SDef.G_modbox[ModuleMaxNumber][SDef.mod_H] = Integer.parseInt(book.getAttributes().getNamedItem("h").getNodeValue());
							SDef.G_modbox[ModuleMaxNumber][SDef.mod_USE] = Integer.parseInt(book.getAttributes().getNamedItem("use").getNodeValue());
							SDef.G_modbox[ModuleMaxNumber][SDef.mod_Image] = Integer.parseInt(book.getAttributes().getNamedItem("Image")
									.getNodeValue());
							ModuleMaxNumber++;
						} else if (book.getNodeName().equals("Frame")) {
							// 帧
							frameString[MaxframeNumber] = book.getAttributes().getNamedItem("explain").getNodeValue();
							// 使用中的物块

							// 统计frame_mod_number数目
							int frame_mod_number = 0;
							for (Node node = book.getFirstChild(); node != null; node = node.getNextSibling()) {
								if (node.getNodeType() == Node.ELEMENT_NODE) {
									if (node.getNodeName().equals("mod")) {
										frame_mod_number++;
									}
								}
							}

							// 赋值
							SDef.G_framebox[MaxframeNumber] = new int[2 + 4 * frame_mod_number];
							SDef.G_framebox[MaxframeNumber][SDef.frame_USE] = 1;
							SDef.G_framebox[MaxframeNumber][SDef.frame_number] = frame_mod_number;

							int tmpcount=0;
							for (Node node = book.getFirstChild(); node != null; node = node.getNextSibling()) {
								if (node.getNodeType() == Node.ELEMENT_NODE) {
									if (node.getNodeName().equals("mod")) {							
											SDef.G_framebox[MaxframeNumber][2 + 4 * tmpcount] = Integer.parseInt(node.getAttributes().getNamedItem("id").getNodeValue());
											SDef.G_framebox[MaxframeNumber][2 + 4 * tmpcount + 1] = Integer.parseInt(node.getAttributes().getNamedItem("x").getNodeValue());
											SDef.G_framebox[MaxframeNumber][2 + 4 *tmpcount+ 2] = Integer.parseInt(node.getAttributes().getNamedItem("y").getNodeValue());
											SDef.G_framebox[MaxframeNumber][2 + 4 * tmpcount + 3] = Integer.parseInt(node.getAttributes().getNamedItem("tran").getNodeValue());
											tmpcount++;
									}
								}
							}
							MaxframeNumber++;
						} else if (book.getNodeName().equals("Anime")) {

							animeString[MaxframeNumber] = book.getAttributes().getNamedItem("explain").getNodeValue();
							// 使用中的物块

							// 统计frame_mod_number数目
							int anime_frame_number = 0;
							for (Node node = book.getFirstChild(); node != null; node = node.getNextSibling()) {
								if (node.getNodeType() == Node.ELEMENT_NODE) {
									if (node.getNodeName().equals("frame")) {
										anime_frame_number++;
									}
								}
							}

							// 赋值
							SDef.G_animebox[MaxanimeNumber] = new int[2 + anime_frame_number];
							SDef.G_animebox[MaxanimeNumber][SDef.anime_USE] = 1;
							SDef.G_animebox[MaxanimeNumber][SDef.anime_number] = anime_frame_number;
							int tmpcount=0;
							for (Node node = book.getFirstChild(); node != null; node = node.getNextSibling()) {
								if (node.getNodeType() == Node.ELEMENT_NODE) {
									if (node.getNodeName().equals("frame")) {
											SDef.G_animebox[MaxanimeNumber][2 +tmpcount] = Integer.parseInt(node.getAttributes().getNamedItem("id").getNodeValue());
											tmpcount++;
									}
								}
							}
							MaxanimeNumber++;
						}
					}
				}

				// 统计名字(图片)
				SDef.mod_Image_name = new String[ImageNumber];
				int temp_start = 0;
				int str_find_end = 0;
				int str_find_start = 1;
				for (int i = 0; i < SDef.mod_Image_name.length; i++) {
					str_find_end = 0;
					str_find_start = 1;
					for (int j = 0; j < 1000; j++) {
						if (str_find_start == 0) {
							break;
						} else {
							temp_start = str_find_start;
							str_find_start = SDef.big_bfimage_path[i].indexOf("\\", str_find_start);
							str_find_start++;
						}
					}
					str_find_end = SDef.big_bfimage_path[i].length();
					SDef.mod_Image_name[i] = "Image:" + SDef.big_bfimage_path[i].substring(temp_start, str_find_end);
				}
				
				
				SDef.image_listModel.clear();
				for (int i = 0; i < SDef.mod_Image_name.length; i++) {
					SDef.image_listModel.addElement(SDef.mod_Image_name[i]);
				}

				SDef.mod_words = new String[ModuleMaxNumber];
				for (int i = 0; i < SDef.mod_words.length; i++) {
					SDef.mod_words[i] = "mod_" + i + ":" + SDef.G_modbox[i][SDef.mod_X] + "," + SDef.G_modbox[i][SDef.mod_Y] + ","
							+ SDef.G_modbox[i][SDef.mod_W] + "," + SDef.G_modbox[i][SDef.mod_H];
					create_mod_image(i, SDef.G_modbox[i][SDef.mod_X], SDef.G_modbox[i][SDef.mod_Y], SDef.G_modbox[i][SDef.mod_W],
							SDef.G_modbox[i][SDef.mod_H]);

				}
				SDef.mod_listModel.clear();
				for (int i = 0; i < SDef.mod_words.length; i++) {
					SDef.mod_listModel.addElement(SDef.mod_words[i]);
				}
				SDef.frame_words = new String[MaxframeNumber];
				for (int i = 0; i < SDef.frame_words.length; i++) {
					SDef.frame_words[i] = "frame_" + i + ":";
				}
				SDef.frame_listModel.clear();
				for (int i = 0; i < SDef.frame_words.length; i++) {
					SDef.frame_listModel.addElement(SDef.frame_words[i]);
				}
				frame_panel.up_frame_list();

				SDef.anime_words = new String[MaxanimeNumber];
				for (int i = 0; i < SDef.anime_words.length; i++) {
					SDef.anime_words[i] = "ani_" + i + ":" + SDef.G_animebox_str[i] + "-";
				}
				SDef.anime_listModel.clear();
				for (int i = 0; i < SDef.anime_words.length; i++) {
					SDef.anime_listModel.addElement(SDef.anime_words[i]);
				}

			}
		} catch (ParserConfigurationException e) {
			JOptionPane.showMessageDialog(null, "数组出错 code=ParserConfigurationException", "出错", JOptionPane.ERROR_MESSAGE);
		//	e.printStackTrace();
		} catch (FileNotFoundException e) {
			JOptionPane.showMessageDialog(null, "数组出错 code=FileNotFoundException", "出错", JOptionPane.ERROR_MESSAGE);
			//e.printStackTrace();
		} catch (SAXException e) {
			JOptionPane.showMessageDialog(null, "数组出错 code=SAXException", "出错", JOptionPane.ERROR_MESSAGE);
		//	e.printStackTrace();
		} catch (IOException e) {
			JOptionPane.showMessageDialog(null, "数组出错 code=IOException", "出错", JOptionPane.ERROR_MESSAGE);
		//	e.printStackTrace();
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "数组出错 code=Exception", "出错", JOptionPane.ERROR_MESSAGE);
		//	e.printStackTrace();
		}
	}

	public static void create_mod_image(int i, int x, int y, int w, int h) {

		BufferedImage tag = new BufferedImage(w, h, BufferedImage.TYPE_INT_ARGB);
		tag.getGraphics().drawImage(SDef.static_mod_big_bfimage[SDef.G_modbox[i][SDef.mod_Image]], -x, -y,
				SDef.static_mod_big_bfimage[SDef.G_modbox[i][SDef.mod_Image]].getWidth(null),
				SDef.static_mod_big_bfimage[SDef.G_modbox[i][SDef.mod_Image]].getHeight(null), null);
		SDef.mod_bfimage[i] = tag;

	}

}
