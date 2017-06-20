package ika_avg_maker;
import javax.swing.*;

import java.awt.*;


public class script_panel extends JPanel {

	private static final long serialVersionUID = 1L;

	private JPanel leftPanel = new JPanel(new BorderLayout());
	private JPanel rightPanel = new JPanel(new BorderLayout());
	private script_image scriptimage = new script_image();
	private JPanel rightup_Panel = new JPanel(new BorderLayout());
	private JPanel rightdown_Panel = new JPanel(new BorderLayout());
	private JTabbedPane jtp;
	sc_load scload = new sc_load();
	sc_select scselect = new sc_select();
	sc_talk sctalk = new sc_talk();
	txt_panel txtpanel = new txt_panel();


	public script_panel() {
		setLayout(new BorderLayout());
		leftPanel.add(txtpanel);
		rightup_Panel.setPreferredSize(new Dimension(240, 320));
		rightup_Panel.add(scriptimage);
		jtp = new JTabbedPane(JTabbedPane.TOP);
		jtp.setPreferredSize(new Dimension(240, 500));
		jtp.addTab("对话脚本", sctalk);
		jtp.addTab("载入脚本", scload);
		jtp.addTab("选项脚本", scselect);
		rightdown_Panel.add(jtp);
		rightPanel.add(rightup_Panel, BorderLayout.NORTH);
		rightPanel.add(rightdown_Panel, BorderLayout.CENTER);
		this.add(leftPanel, BorderLayout.CENTER);
		this.add(rightPanel, BorderLayout.EAST);
	}

}
