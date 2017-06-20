package ika_avg_maker;
import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.text.*;

import java.awt.*;

public class txt_panel extends JPanel implements Runnable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private DocumentListener Doclistener = new DocumentListener() {
		public void changedUpdate(DocumentEvent arg0) {
		}

		public void insertUpdate(DocumentEvent arg0) {
			System.out.println("up:" + doc.getLength());
			try {
				text = doc.getText(0, doc.getLength());
			} catch (BadLocationException e) {
				System.out.println("get doc error");
			}
		}

		public void removeUpdate(DocumentEvent arg0) {
			try {
				text = doc.getText(0, doc.getLength());
			} catch (BadLocationException e) {
				System.out.println("get doc error");
			}
		}

	};

	public static StyleContext sc = new StyleContext();
	public static DefaultStyledDocument doc = new DefaultStyledDocument(sc);
	public static JTextPane pane = new JTextPane(doc);
	Style defaultStyle;
	static Style mainStyle;
	static Style TalkStyle;
	static Style SecletStyle;
	static Style LoadStyle;
	static Style HighlineStyle;
	static Style TalkinStyle;
	public Thread thread;
	JScrollPane ScrollPane;

	public txt_panel() {
		// �����ĵ��ַ����
		defaultStyle = sc.getStyle(StyleContext.DEFAULT_STYLE);
		mainStyle = sc.addStyle("MainStyle", defaultStyle);
		StyleConstants.setFontFamily(mainStyle, "����");
		StyleConstants.setForeground(mainStyle, new Color(0x000000));
		StyleConstants.setFontSize(mainStyle, 13);
		StyleConstants.setBold(mainStyle, false);

		// �����Ի���Ŀ����
		TalkStyle = sc.addStyle("TalkStyle", null);
		StyleConstants.setFontFamily(TalkStyle, "����");
		StyleConstants.setForeground(TalkStyle, new Color(0x7b0052));
		StyleConstants.setFontSize(TalkStyle, 13);
		StyleConstants.setBold(TalkStyle, true);

		// ������ȡ��Ŀ����
		LoadStyle = sc.addStyle("LoadStyle", null);
		StyleConstants.setFontFamily(LoadStyle, "����");
		StyleConstants.setForeground(LoadStyle, new Color(0x815472));
		StyleConstants.setFontSize(LoadStyle, 13);
		StyleConstants.setBold(LoadStyle, true);
		// ����ѡ����Ŀ����
		SecletStyle = sc.addStyle("SecletStyle", null);
		StyleConstants.setFontFamily(SecletStyle, "����");
		StyleConstants.setForeground(SecletStyle, new Color(0x15040f));
		StyleConstants.setFontSize(SecletStyle, 13);
		StyleConstants.setBold(SecletStyle, true);
		// ����������
		HighlineStyle = sc.addStyle("HighlineStyle", null);
		StyleConstants.setFontFamily(HighlineStyle, "����");
		StyleConstants.setForeground(HighlineStyle, new Color(0x0000c6));
		StyleConstants.setFontSize(HighlineStyle, 13);
		// �����Ի�֮��ķ���
		TalkinStyle = sc.addStyle("TalkinStyle", null);
		StyleConstants.setForeground(TalkinStyle, new Color(0x2900ff));
		StyleConstants.setFontSize(TalkinStyle, 13);
		StyleConstants.setFontFamily(TalkinStyle, "����");

		pane.setAutoscrolls(true);
		pane.setDragEnabled(true);

		pane.setPreferredSize(new Dimension(500, 500));
		ScrollPane = new JScrollPane(pane);
		ScrollPane.setPreferredSize(new Dimension(520, 520));
		this.add(ScrollPane, BorderLayout.CENTER);
		this.setVisible(true);
		// Set the logical style
		doc.setLogicalStyle(0, mainStyle);
		try {
			doc.insertString(0, text, null);
		} catch (BadLocationException e) {
			e.printStackTrace();
		}
		thread = new Thread(this);
		thread.start();
		System.out.println("thread start");
		doc.addDocumentListener(Doclistener);
	}

	public static String text = "�Ի�:</>Hello World<//>";
	boolean isrun = true;
	long show_fps;
	private long thread_startTime;
	private long thread_endTime;

	// ��õ�ǰ����
	public static int lineCount = 0;
	public static int str_count_start = 0;
	public static int str_count_end = 0;
	public static String str = "";
	public static String thisline = "";
	public static int bufferStart = 0;

	public static int line_number = 0;

	public static void doline_check() {
		lineCount = 0;
		str_count_start = 0;
		str_count_end = 0;
		int temp_number = pane.getCaretPosition();
		bufferStart = 0;
		str = text.substring(0, temp_number);
		for (int i = 0; i < temp_number / 4; i++) {
			str_count_start = str.indexOf("<//>", str_count_start);
			if (str_count_start == -1) {
				break;
			} else {
				bufferStart = str_count_start;
			}
			str_count_start = str_count_start + 1;
			lineCount++;
		}
		// ��õ�ǰ�е�����<//>~<//>
		bufferStart++;
		str_count_end = text.indexOf("<//>", bufferStart);
		if (str_count_end != -1) {
			thisline = text.substring(bufferStart, str_count_end);
		}
		line_number = lineCount;
		explanString(thisline);
	}

	public static String exStr = "";

	public static void explanString(String str) {
		exStr = "";
		exStr = str.substring(4, 7);
		str = str.substring(7, str.length());
		if (exStr.equals("�Ի�:")) {
			// ���"����"
			str_count_start = 0;
			str_count_end = 0;
			str_count_start = str.indexOf("����", str_count_start);
			if (str_count_start != -1) {
				str_count_end = str.indexOf(",", str_count_start);

				script_image.recBG[0]= Integer.parseInt(str.substring(str_count_start+3, str_count_end));
				str_count_start=str_count_end+1;
				str_count_end = str.indexOf(",", str_count_start);
				script_image.recBG[1]= Integer.parseInt(str.substring(str_count_start, str_count_end));	
				str_count_start=str_count_end+1;
				str_count_end = str.indexOf(">", str_count_start);
				script_image.recBG[2]= Integer.parseInt(str.substring(str_count_start, str_count_end));
			}
			
			// ���"����1"
			str_count_start = 0;
			str_count_end = 0;
			str_count_start = str.indexOf("����1", str_count_start);
			if (str_count_start != -1) {
				str_count_end = str.indexOf(",", str_count_start);
				script_image.recPerson1[0]= Integer.parseInt(str.substring(str_count_start+4, str_count_end));
				str_count_start=str_count_end+1;
				str_count_end = str.indexOf(",", str_count_start);
				script_image.recPerson1[1]= Integer.parseInt(str.substring(str_count_start, str_count_end));	
				str_count_start=str_count_end+1;
				str_count_end = str.indexOf(">", str_count_start);
				script_image.recPerson1[2]= Integer.parseInt(str.substring(str_count_start, str_count_end));	
			}
			
			// ���"����2"
			str_count_start = 0;
			str_count_end = 0;
			str_count_start = str.indexOf("����2", str_count_start);
			if (str_count_start != -1) {
				str_count_end = str.indexOf(",", str_count_start);
				script_image.recPerson2[0]= Integer.parseInt(str.substring(str_count_start+4, str_count_end));
				str_count_start=str_count_end+1;
				str_count_end = str.indexOf(",", str_count_start);
				script_image.recPerson2[1]= Integer.parseInt(str.substring(str_count_start, str_count_end));	
				str_count_start=str_count_end+1;
				str_count_end = str.indexOf(">", str_count_start);
				script_image.recPerson2[2]= Integer.parseInt(str.substring(str_count_start, str_count_end));	
			}
		
			// ���"��"
			str_count_start = 0;
			str_count_end = 0;
			str_count_start = str.indexOf("��", str_count_start);
			if (str_count_start != -1) {
				str_count_end = str.indexOf(",", str_count_start);
				script_image.recTalkBox[0]= Integer.parseInt(str.substring(str_count_start+2, str_count_end));
				str_count_start=str_count_end+1;
				str_count_end = str.indexOf(",", str_count_start);
				script_image.recTalkBox[1]= Integer.parseInt(str.substring(str_count_start, str_count_end));	
				str_count_start=str_count_end+1;
				str_count_end = str.indexOf(">", str_count_start);
				script_image.recTalkBox[2]= Integer.parseInt(str.substring(str_count_start, str_count_end));	
			}

			
			// ���"�Ի�����"
			str_count_start = 0;
			str_count_end = 0;
			str_count_start = str.indexOf("</>", str_count_start);
			if (str_count_start != -1) {
				script_image.talkString=str.substring(str_count_start+3,str.length());
			}
			
			// script_image.recBG[0]= Integer.parseInt(str.substring(str_count_start+3, str_count_end ));

		}

	}

	// str_find_start = strAnima.indexOf("<Module x=", str_find_start) + 10;
	// str_find_end = strAnima.indexOf("y=", str_find_start);
	// SDef.G_modbox[i][SDef.mod_X] = Integer.parseInt(strAnima.substring(str_find_start, str_find_end - 1));

	public static void addtxt(String str) {
		try {
			doc.insertString(pane.getCaretPosition(), str, mainStyle);
		} catch (BadLocationException e1) {
		}
	}

	// ��������
	public void checkColor(String highword, Style thisstyle) {
		int str_start = 0;
		int str_end = 0;
		for (int i = 0; i < 65535; i++) {
			str_end = text.indexOf(highword, str_start);
			if (str_end != -1) {
				doc.setCharacterAttributes(str_end, highword.length(), thisstyle, false);
				str_start = str_end + 1;
			} else {
				break;
			}
		}
	}

	// Style mainStyle;
	// Style TalkStyle;
	// Style SecletStyle;
	// Style LoadStyle;
	// Style HighlineStyle;
	// Style TalkinStyle;

	public void run() {
		while (isrun) {
			thread_startTime = System.currentTimeMillis();
			doline_check();
			doc.setCharacterAttributes(0, text.length(), mainStyle, false);
			checkColor("����", HighlineStyle);
			checkColor("��", HighlineStyle);
			checkColor("����1", HighlineStyle);
			checkColor("����2", HighlineStyle);
			checkColor("����", HighlineStyle);
			checkColor("�ı�", HighlineStyle);
			checkColor("����", HighlineStyle);
			checkColor("�Ի�", HighlineStyle);
			checkColor("����:", TalkStyle);
			checkColor("�Ի�:", LoadStyle);
			checkColor("ѡ��:", SecletStyle);
			checkColor("</>", TalkinStyle);
			checkColor("<//>", TalkinStyle);
			// doc. pane.getCaretPosition()

			System.gc();
			try {
				thread_endTime = System.currentTimeMillis();
				if ((thread_endTime - thread_startTime) < 2000) {
					Thread.sleep(2000 - (thread_endTime - thread_startTime));
				}
			} catch (InterruptedException ex1) {
			}
		}

	}

}
