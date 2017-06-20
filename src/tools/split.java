package tools;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileOutputStream;
import javax.imageio.ImageIO;



public class split
{
	

public static void main(String[] args){
	System.out.println("app run");
	 loadImage("D:\\gzmap.png");
	 saveImage("D:\\small\\");
}


public static BufferedImage PIC_bfimage;
public static BufferedImage small_bfimage;
public static String txt_str;

public static void  loadImage(String path){
	File pngfile = new File(path);
	try {
		PIC_bfimage = ImageIO.read(pngfile);
		ImageIO.write(PIC_bfimage, "png", pngfile);
		System.out.println("read over:"+PIC_bfimage.getWidth()+","+PIC_bfimage.getHeight());
	}catch (Exception ex) {
		ex.printStackTrace();
	}
}



public static void saveImage(String path){
	txt_str="";
	for(int i=0;i<PIC_bfimage.getWidth()/128;i++){
		for(int j=0;j<PIC_bfimage.getHeight()/128;j++){
			
			create_mod_image(path+"img"+i+"_"+j+".png",i*128, j*128,128, 128);
			txt_str = txt_str + "PngMate -colors 128 " + path+"img"+i+"_"+j+".png " + path+"img"+i+"_"+j+".png\n\r";
		}	
	}
	
	
	try {
		File txtFile = createFile(path + "png.bat");
		FileOutputStream fo = new FileOutputStream(txtFile);
		fo.write(txt_str.getBytes("GB2312"));
		fo.close();
	} catch (Exception ex) {
		System.out.println("png txt error");
	}
	
}




public static void create_mod_image(String path,int x, int y, int w, int h) {
	File pngfiles = createFile(path);
	
	BufferedImage tag = new BufferedImage(w, h, BufferedImage.TYPE_INT_ARGB);
	tag.getGraphics().drawImage(PIC_bfimage, -x, -y, PIC_bfimage.getWidth(null), PIC_bfimage.getHeight(null), null);
	small_bfimage = tag;
	
	try {
		ImageIO.write(small_bfimage, "png", pngfiles);
		System.out.println("save over:"+path);
	} catch (Exception ex) {
		System.out.println("png save error");
	}
}













public static File createFile(String fileName) {
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
	} catch (Exception ex) {
		ex.printStackTrace();
	}
	return file;
}




}
