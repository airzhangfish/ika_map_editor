package IkaMapEditor;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

public class newMap extends JFrame implements ActionListener {
	private static final long serialVersionUID = 1L;
	JFormattedTextField field_MAP_SW = new JFormattedTextField("20");
	JFormattedTextField field_MAP_SH = new JFormattedTextField("20");
	JFormattedTextField field_PIC_SW = new JFormattedTextField("32");
	JFormattedTextField field_PIC_SH = new JFormattedTextField("32");
	JButton buttonOK = new JButton("ȷ��");
	JButton buttonCL = new JButton("ȡ��");

	public newMap(int x, int y) {

		this.setTitle("�µ�ͼ");
		this.setSize(300, 150);
		this.setAlwaysOnTop(true);
		this.setLocation(x, y);
		this.setResizable(false); // ���岻�ܸı��С
		this.setLayout(new GridLayout(6, 4));
		this.add(new JLabel("��д�����µ�"));
		this.add(new JLabel("ͼ������"));
		this.add(new JLabel(""));
		this.add(new JLabel(""));

		this.add(new JLabel("�����"));
		this.add(new JLabel(""));
		this.add(new JLabel("����"));
		this.add(new JLabel(""));

		this.add(field_PIC_SW);
		this.add(new JLabel("     X"));
		this.add(field_PIC_SH);
		this.add(new JLabel(""));

		this.add(new JLabel("���򷽸���"));
		this.add(new JLabel(""));
		this.add(new JLabel("���򷽸���"));
		this.add(new JLabel(""));

		this.add(field_MAP_SW);
		this.add(new JLabel("     X"));
		this.add(field_MAP_SH);
		this.add(new JLabel(""));

		this.add(new JLabel(""));
		this.add(new JLabel(""));
		this.add(buttonOK);
		this.add(buttonCL);
		buttonOK.addActionListener(this);
		buttonCL.addActionListener(this);
		setVisible(true);
	}

	public void actionPerformed(ActionEvent arg0) {
		Object obj = arg0.getSource();
		if (obj == buttonOK) {
			System.out.println("input x y w h enter");
			try {
				SDef.map_SW = Integer.parseInt(field_MAP_SW.getText());
				SDef.map_SH = Integer.parseInt(field_MAP_SH.getText());
				SDef.tile_SW = Integer.parseInt(field_PIC_SW.getText());
				SDef.tile_SH = Integer.parseInt(field_PIC_SH.getText());
				if (SDef.map_SW > 0 && SDef.map_SH > 0 && SDef.tile_SW > 0 && SDef.tile_SH > 0) {
					SDef.layer0=new short[SDef.map_SW][SDef.map_SH];
					for(int i=0;i<SDef.map_SW;i++){
						for(int j=0;j<SDef.map_SH;j++){
							SDef.layer0[i][j]=-1;
						}	
					}
					setVisible(false);
				} else {
					JOptionPane.showMessageDialog(this, "���ݲ���С��0", "����", JOptionPane.ERROR_MESSAGE);
				}
			} catch (Exception ex) {
				JOptionPane.showMessageDialog(this, "���ݶ�ȡ����", "����", JOptionPane.ERROR_MESSAGE);
			}

		}

		if (obj == buttonCL) {
			System.out.println("input x y w h cancel");
			setVisible(false);
		}
	}

}
