package IkaMapEditor;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JRadioButton;

public class ScreenSetup extends JFrame implements ActionListener {
	ButtonGroup group = new ButtonGroup();
	private static JRadioButton sizeRB_10180 = new JRadioButton("101*80");
	private static JRadioButton sizeRB_176204 = new JRadioButton("176*204");
	private static JRadioButton sizeRB_176208 = new JRadioButton("176*208");
	private static JRadioButton sizeRB_176220 = new JRadioButton("176*220");
	private static JRadioButton sizeRB_128116 = new JRadioButton("128*116");
	private static JRadioButton sizeRB_128128 = new JRadioButton("128*128");
	private static JRadioButton sizeRB_128160 = new JRadioButton("128*160");
	private static JRadioButton sizeRB_208208 = new JRadioButton("208*208");
	private static JRadioButton sizeRB_208320 = new JRadioButton("208*320");
	private static JRadioButton sizeRB_240320 = new JRadioButton("240*320");
	private static final long serialVersionUID = 1L;
	private static JButton Botton_OK = new JButton("确定");

	public ScreenSetup(int x, int y) {

		this.setTitle("预览画面设置");
		this.setSize(70, 250);
		this.setAlwaysOnTop(true);
		this.setLocation(x, y);
		this.setResizable(false); // 窗体不能改变大小
		this.setLayout(new GridLayout(11, 1));
		addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				this.windowClosed(e);
			}
		});

		group.add(sizeRB_10180);
		group.add(sizeRB_176204);
		group.add(sizeRB_176208);
		group.add(sizeRB_176220);
		group.add(sizeRB_128116);
		group.add(sizeRB_128128);
		group.add(sizeRB_128160);
		group.add(sizeRB_208208);
		group.add(sizeRB_208320);
		group.add(sizeRB_240320);
		sizeRB_176208.setSelected(true);
		this.add(sizeRB_10180);
		this.add(sizeRB_176204);
		this.add(sizeRB_176208);
		this.add(sizeRB_176220);
		this.add(sizeRB_128116);
		this.add(sizeRB_128128);
		this.add(sizeRB_128160);
		this.add(sizeRB_208208);
		this.add(sizeRB_208320);
		this.add(sizeRB_240320);
		this.add(Botton_OK);
		sizeRB_10180.addActionListener(this);
		sizeRB_176204.addActionListener(this);
		sizeRB_176208.addActionListener(this);
		sizeRB_176220.addActionListener(this);
		sizeRB_128116.addActionListener(this);
		sizeRB_128128.addActionListener(this);
		sizeRB_128160.addActionListener(this);
		sizeRB_208208.addActionListener(this);
		sizeRB_208320.addActionListener(this);
		sizeRB_240320.addActionListener(this);
		Botton_OK.addActionListener(this);
		setVisible(true);
	}

	public void actionPerformed(ActionEvent e) {
		Object source = e.getSource();
		if (source == Botton_OK) {
			if (sizeRB_10180.isSelected()) {
				SDef.srceensize = 0;
			}
			if (sizeRB_176204.isSelected()) {
				SDef.srceensize = 1;
			}
			if (sizeRB_176208.isSelected()) {
				SDef.srceensize = 2;
			}
			if (sizeRB_176220.isSelected()) {
				SDef.srceensize = 3;
			}
			if (sizeRB_128116.isSelected()) {
				SDef.srceensize = 4;
			}
			if (sizeRB_128128.isSelected()) {
				SDef.srceensize = 5;
			}
			if (sizeRB_128160.isSelected()) {
				SDef.srceensize = 6;
			}
			if (sizeRB_208208.isSelected()) {
				SDef.srceensize = 7;
			}

			if (sizeRB_208320.isSelected()) {
				SDef.srceensize = 8;
			}
			if (sizeRB_240320.isSelected()) {
				SDef.srceensize = 9;
			}
			setVisible(false);
		}
	}
}
