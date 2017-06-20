package SoundControl;
import java.awt.*;
import java.awt.event.*;
import java.io.IOException;

import javax.swing.*;

public class SounderCon extends JPanel implements ActionListener {

	private static final long serialVersionUID = 1L;
	private static JButton sound_Control = new JButton("音量控制");
	private static JButton reco_Control = new JButton("麦克风控制");
	ButtonGroup group = new ButtonGroup();

	public SounderCon() {
		this.setLayout(new GridLayout(6, 1));


		// 第1行
		this.add(new JLabel("音量控制"));
		this.add(sound_Control);
		this.add(new JLabel("麦克风控制"));
		this.add(reco_Control);
		  this.add(new JLabel(""));
		  this.add(new JLabel(""));
		sound_Control.addActionListener(this);
		reco_Control.addActionListener(this);
		
		
		
	}

	public void actionPerformed(ActionEvent arg0) {
		Object obj = arg0.getSource();
		if (obj == sound_Control) {
        try
        {
          Runtime.getRuntime().exec("sndvol32.exe");
        }
        catch (IOException e)
        {
          e.printStackTrace();
        }
        
		}
		if (obj == reco_Control) {
	        try
	        {
	          Runtime.getRuntime().exec("sndvol32.exe /r");
	        }
	        catch (IOException e)
	        {
	          e.printStackTrace();
	        }
		}
	}

}
