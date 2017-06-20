package ika_toolbox;

import java.awt.*;
import java.awt.event.*;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStreamReader;
import java.util.Vector;
import javax.swing.*;

public class HZlib extends JPanel implements ActionListener
{

    private static final long serialVersionUID = 1L;
    private static JButton loadbin = new JButton("选取scv字库");
    private static JLabel step2label = new JLabel("生成字库自动生成在字库文件夹");
    private static JRadioButton x_RadioButton = new JRadioButton("X 坐标");
    private static JRadioButton y_RadioButton = new JRadioButton("Y坐标");
    JFormattedTextField field_draw = new JFormattedTextField();
    JFormattedTextField field_xy = new JFormattedTextField();
    ButtonGroup group = new ButtonGroup();

    public HZlib()
    {
        this.setLayout(new GridLayout(5, 1));
        this.add(new JLabel("字库生成工具"));
        this.add(new JLabel(""));
        this.add(new JLabel("Step 1: 将scv字库导入程序"));
        this.add(new JLabel(""));
        this.add(loadbin);
        this.add(new JLabel(""));
        this.add(step2label);
        loadbin.addActionListener(this);
        x_RadioButton.addActionListener(this);
        y_RadioButton.addActionListener(this);
    }

    public void actionPerformed(ActionEvent arg0)
    {
        Object obj = arg0.getSource();
        if (obj == x_RadioButton)
        {}
        if (obj == y_RadioButton)
        {}
        if (obj == loadbin)
        {
            open_file();
        }
    }

    public static Vector vec = null;

    private void open_file()
    {
        JFileChooser c = new JFileChooser();
        int rVal = c.showOpenDialog(this);
        if (rVal == JFileChooser.OPEN_DIALOG)
        {
            File file = c.getSelectedFile();
            if (file != null)
            {
                vec = readCSV(file, null);
                if (vec != null)
                {
                    saveLib(file, vec);
                }
            }
        }
    }

    /**
     * 保存2个文件,1个索引文件,一个字库文件
     * 
     * @param vec
     */
    public static void saveLib(File file, Vector vec)
    {
        String outputlib = "";
        byte[] indexbox = new byte[vec.size() * 2];
        int offset = 0;
        for (int i = 0; i < vec.size(); i++)
        {
            Vector vc = ( Vector ) vec.elementAt(i);
            String str = ( String ) vc.elementAt(1);
            outputlib = outputlib + str;
            int size = str.length();
            indexbox[offset] = ( byte ) ((size) >> 8);
            indexbox[offset + 1] = ( byte ) ((size) - (indexbox[offset] << 8));
            offset = offset + 2;
        }
        File indexFile = SDef.createFile(file.getParent() + "\\index.ime");
        try
        {
            FileOutputStream fo = new FileOutputStream(indexFile);
            fo.write(indexbox);
            fo.close();
        }
        catch (Exception ex)
        {
            ex.printStackTrace();
        }
// 保存字典文件
        File libFile = SDef.createFile(file.getParent() + "\\lib.ime");
        try
        {
            FileOutputStream fo = new FileOutputStream(libFile);
            System.out.println("输出:"+outputlib);
            fo.write(outputlib.getBytes());
            fo.close();
        }
        catch (Exception ex)
        {
            ex.printStackTrace();
        }
    }

    /**
     * CVS格式读取,反馈给一个以Vector嵌套的Vector里
     * 
     * @param path
     * @param encode
     * @return Vector
     */
    public static Vector readCSV(File path, String encode)
    {
        String[] list;
        list = readTxt2String(path, encode);// "GB2312"
        Vector root = new Vector();
        StringBuffer sbu = new StringBuffer();
        for (int i = 0; i < list.length; i++)
        {
            // str.substring(0, str.indexOf(";", 0))
            Vector child = new Vector();
            int start = 0;
            int end = 0;
            for (int j = 0;; j++)
            {
                sbu.delete(0, sbu.length());
                end = list[i].indexOf(",", start);
                if (end > 0)
                {
                    // 检测到分割
                    sbu.append(list[i].substring(start, end));
                    child.addElement(sbu.toString());
                    start = end + 1;
                }
                else
                {
                    // 后面没有检测到分割
                    sbu.append(list[i].substring(start, list[i].length()));
                    child.addElement(sbu.toString());
                    break;
                }
            }
            root.addElement(child);
        }
        return root;
    }

    /**
     * 读取txt 指定encode文本数据，自动按照文本分行
     * 
     * @param path
     * @return String[]
     */
    public static final String[] readTxt2String(File file, String encode)
    {
        String[] str_info = null;
        try
        {
            // 读取java文件内容
            InputStreamReader is = new InputStreamReader(new FileInputStream(file), "GB2312");// "ISO-8859-1"
            BufferedReader reader = new BufferedReader(is);
            int totalnumber = 0;
            for (int i = 0; i < 1000000; i++)
            {
                if (reader.read() != -1)
                {
                    totalnumber++;
                }
                else
                {
                    System.out.println(totalnumber);
                    break;
                }
            }
            System.out.println("close 1");
            reader.close();
            is.close();
            System.out.println("close 2");
            InputStreamReader iis = new InputStreamReader(new FileInputStream(file), "GB2312");
            BufferedReader readerr = new BufferedReader(iis);
            char[] word_uni = new char[totalnumber];
            readerr.read(word_uni);
            String buf = new String(word_uni);
            int strstart = 0;
            int strend = 0;
            int total = 0;
            // 统计多少行
            for (int i = 0;; i++)
            {
                strend = buf.indexOf("\n", strstart);
                if (strend == -1)
                {
                    total = i;
                    break;
                }
                else
                {
                    strstart = strend + 1;
                }
            }
            System.out.println("total:" + total);
            // 存入数据
            strstart = 0;
            strend = 0;
            str_info = new String[total];
            for (int i = 0; i < total; i++)
            {
                strend = buf.indexOf("\n", strstart);
                str_info[i] = buf.substring(strstart, strend - 1);
                strstart = strend + 1;
            }
        }
        catch (Exception ex)
        {
            System.out.println("load_script  script_load error");
            ex.printStackTrace();
        }
        return str_info;
    }
}
