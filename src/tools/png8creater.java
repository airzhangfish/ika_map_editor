package tools;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileOutputStream;
import javax.imageio.ImageIO;

public class png8creater
{
    private static String[] iconsmall = new String[] { "E:\\eclipse_works\\uiengine\\res\\menu\\login_small.png", "E:\\eclipse_works\\uiengine\\res\\menu\\exit_small.png", "E:\\eclipse_works\\uiengine\\res\\menu\\refresh_small.png", "E:\\eclipse_works\\uiengine\\res\\menu\\tellafriendIconSmall.png",
    "E:\\eclipse_works\\uiengine\\res\\menu\\geoNavigation_small.png", "E:\\eclipse_works\\uiengine\\res\\menu\\listView_small.png", "E:\\eclipse_works\\uiengine\\res\\menu\\geoNavigation_small.png" };
    public static void main(String[] args)
    {
        System.out.println("app run");
//        for(int i=0;i<iconsmall.length;i++){
//            createPNG8(iconsmall[i]); 
//        }
 createPNG8("D:\\followersList_small.png");
 createPNG8("D:\\chronoNavigation_small.png");
 createPNG8("D:\\directMessages_small.png");
// createPNG8("D:\\icon1_05.png");
// createPNG8("D:\\icon1_06.png");
// createPNG8("D:\\icon1_07.png");
// createPNG8("D:\\icon1_08.png");
// createPNG8("D:\\icon1_09.png");
    }

    public static void createPNG8(String path)
    {
        loadImage(path);
        explainPNG();
        create_mod_image(path, 0, 0, PIC_bfimage.getWidth(null), PIC_bfimage.getHeight(null));
    }

    public static BufferedImage PIC_bfimage;
    public static BufferedImage small_bfimage;
    public static String txt_str;

    public static void loadImage(String path)
    {
        File pngfile = new File(path);
        try
        {
            PIC_bfimage = ImageIO.read(pngfile);
            ImageIO.write(PIC_bfimage, "png", pngfile);
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
        // ²é¿´
//        for (int i = 0; i < PIC_bfimage.getHeight(null); i++)
//        {
//            for (int j = 0; j < PIC_bfimage.getWidth(null); j++)
//            {
//                System.out.print(Integer.toHexString(arrays[i * PIC_bfimage.getHeight(null) + j]) + ",");
//            }
//            System.out.println("");
//        }
        // ÔËËã
        for (int i = 0; i < size; i++)
        {
            if ((Integer.toHexString(arrays[i]).length() == 8 || Integer.toHexString(arrays[i]).length() == 7) && !Integer.toHexString(arrays[i]).substring(0, 2).equals("ff"))
            {
                arrays[i] = 0x00ffffff;
            }
        
        }
//        System.out.println("================================================================");
//        for (int i = 0; i < PIC_bfimage.getHeight(null); i++)
//        {
//            for (int j = 0; j < PIC_bfimage.getWidth(null); j++)
//            {
//                System.out.print(Integer.toHexString(arrays[i * PIC_bfimage.getHeight(null) + j]) + ",");
//            }
//            System.out.println("");
//        }
        PIC_bfimage.setRGB(0, 0, PIC_bfimage.getWidth(null), PIC_bfimage.getHeight(null), arrays, 0, PIC_bfimage.getWidth());
    }

    public static void saveImage(String path)
    {
        txt_str = "";
        for (int i = 0; i < PIC_bfimage.getWidth() / 128; i++)
        {
            for (int j = 0; j < PIC_bfimage.getHeight() / 128; j++)
            {
                create_mod_image(path + "img" + i + "_" + j + ".png", i * 128, j * 128, 128, 128);
                txt_str = txt_str + "PngMate -colors 128 " + path + "img" + i + "_" + j + ".png " + path + "img" + i + "_" + j + ".png\n\r";
            }
        }
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
    }

    public static void create_mod_image(String path, int x, int y, int w, int h)
    {
        File pngfiles = createFile(path);
        BufferedImage tag = new BufferedImage(w, h, BufferedImage.TYPE_INT_ARGB);
        tag.getGraphics().drawImage(PIC_bfimage, -x, -y, PIC_bfimage.getWidth(null), PIC_bfimage.getHeight(null), null);
        small_bfimage = tag;
        try
        {
            ImageIO.write(small_bfimage, "png", pngfiles);
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
