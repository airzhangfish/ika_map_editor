package tools;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileOutputStream;
import javax.imageio.ImageIO;

public class clipPic
{

    public static String path_big = null;
    public static String type = null; // png,jpg
    public static int tile_width = 64;
    public static int tile_height = 64;
    public static String folderpath = null;
    public static String outputfolder_path = null;
    public static String[] staticargs=null;
    /**
     * 剪切PNG图片,切割PNG图片等功能.
     * @param args
     */
    public static void main(String[] args)
    {
        try
        {
            staticargs=args;
            if (args.length != 5)
            {
                System.out.println("参数不为5个。。不对");
                return;
            }
            folderpath = System.getProperty("user.dir");
            System.out.println("app run:" + folderpath);

            path_big = args[0];
            type = args[1];
        
            tile_width = Integer.parseInt(args[2]);
            tile_height = Integer.parseInt(args[3]);
            outputfolder_path = folderpath + "\\" + args[4];
            File ofolder = new File(outputfolder_path);
            ofolder.mkdirs();
            String picfile = folderpath + "\\" + path_big;
            System.out.println(picfile);

            // 处理
            loadImage(picfile);
            saveImage(outputfolder_path + "\\");

        }
        catch (Exception ex)
        {
            ex.printStackTrace();
        }

    }

    public static BufferedImage PIC_bfimage;
    public static BufferedImage small_bfimage;
    public static String txt_str;
    public static String html_str;
    public static void loadImage(String path)
    {
        File pngfile = new File(path);
        try
        {
            PIC_bfimage = ImageIO.read(pngfile);
            ImageIO.write(PIC_bfimage, type, pngfile);

            System.out.println("read over:" + PIC_bfimage.getWidth() + "," + PIC_bfimage.getHeight());
        }
        catch (Exception ex)
        {
            ex.printStackTrace();
        }
    }

    private static int[] arrays;

    private static void explainPNG()
    {
        int size = PIC_bfimage.getWidth(null) * PIC_bfimage.getHeight(null);
        arrays = new int[size];
        PIC_bfimage.getRGB(0, 0, PIC_bfimage.getWidth(null), PIC_bfimage.getHeight(null), arrays, 0, PIC_bfimage.getWidth());
        // 运算
        for (int i = 0; i < size; i++)
        {
            if ((Integer.toHexString(arrays[i]).length() == 8 || Integer.toHexString(arrays[i]).length() == 7) && !Integer.toHexString(arrays[i]).substring(0, 2).equals("ff"))
            {
                arrays[i] = 0x00ffffff;
            }

        }
        // System.out.println("================================================================");
        // for (int i = 0; i < PIC_bfimage.getHeight(null); i++)
        // {
        // for (int j = 0; j < PIC_bfimage.getWidth(null); j++)
        // {
        // System.out.print(Integer.toHexString(arrays[i * PIC_bfimage.getHeight(null) + j]) + ",");
        // }
        // System.out.println("");
        // }
        PIC_bfimage.setRGB(0, 0, PIC_bfimage.getWidth(null), PIC_bfimage.getHeight(null), arrays, 0, PIC_bfimage.getWidth());
    }

    public static void saveImage(String path)
    {
        txt_str = "";
        html_str="<html><body><table border=0 cellspacing=0 cellpadding=0>\r\n";
        
        int wcount=(PIC_bfimage.getWidth() / tile_width) + 1;
        int hcount=(PIC_bfimage.getHeight() / tile_height) + 1;
        for (int i = 0; i <wcount; i++)
        {
            for (int j = 0; j < hcount; j++)
            {
                create_mod_image(path + "img" + i + "_" + j + "."+type, i * tile_width, j * tile_height, tile_width, tile_height);
                txt_str = txt_str + "PngMate -colors 128 " + path + "img" + i + "_" + j + ".png " + path + "img" + i + "_" + j + ".png\n\r";
            }
        }
        
        for (int j = 0; j < hcount; j++)
        {
            html_str=html_str+"<tr><td nowrap>\r\n";
        for (int i = 0; i <wcount; i++)
        {
          html_str=html_str+"<img src=img"+i+"_"+j+".png>"; 
        }
        html_str=html_str+"</td></tr>\r\n";
        }
        
        html_str=html_str+"\r\n</table></body></html>";
        
        
        try
        {
            File txtFile = createFile(path + "png.bat");
            FileOutputStream fo = new FileOutputStream(txtFile);
            fo.write(txt_str.getBytes("GB2312"));
            fo.close();
        }
        catch (Exception ex)
        {
            System.out.println("png txt error");
        }
        
        
        
        try
        {
            File txtFile = createFile(path + "index.html");
            FileOutputStream fo = new FileOutputStream(txtFile);
            fo.write(html_str.getBytes("GB2312"));
            fo.close();
        }
        catch (Exception ex)
        {
            System.out.println("png txt error");
        }
    }

    public static void create_mod_image(String path, int x, int y, int w, int h)
    {
        File pngfiles = createFile(path);
        BufferedImage tag = new BufferedImage(w, h, BufferedImage.TYPE_INT_ARGB);
        tag.getGraphics().drawImage(PIC_bfimage, -x, -y, PIC_bfimage.getWidth(null), PIC_bfimage.getHeight(null), null);
        small_bfimage = tag;
        try
        {
            ImageIO.write(small_bfimage,type, pngfiles);
            System.out.println("save over:" + path);
        }
        catch (Exception ex)
        {
            System.out.println("png save error");
        }
    }

    public static File createFile(String fileName)
    {
        File file = new File(fileName);
        if (file == null)
        {
            return null;
        }
        if (file.isDirectory())
        {
            return null;
        }
        if (file.exists())
        {
            file.delete();
        }
        try
        {
            file.createNewFile();
        }
        catch (Exception ex)
        {
            ex.printStackTrace();
        }
        return file;
    }
}
