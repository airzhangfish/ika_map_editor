package IkaMapEditor;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

public class setMap extends JFrame implements ActionListener {
	private static final long serialVersionUID = 1L;
	JFormattedTextField field_MAP_SW;
	JFormattedTextField field_MAP_SH;
	JButton buttonOK = new JButton("ȷ��");
	JButton buttonCL = new JButton("ȡ��");

	public setMap(int x, int y) {
		field_MAP_SW = new JFormattedTextField(String.valueOf(SDef.map_SW));
		field_MAP_SH = new JFormattedTextField(String.valueOf(SDef.map_SH));
		this.setTitle("���õ�ͼ��С");
		this.setSize(300, 100);
		this.setAlwaysOnTop(true);
		this.setLocation(x, y);
		this.setResizable(false); // ���岻�ܸı��С
		this.setLayout(new GridLayout(4, 4));
		this.add(new JLabel("���õ�ͼ����"));
		this.add(new JLabel("����"));
		this.add(new JLabel(""));
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
				int tmpmap_SW = Integer.parseInt(field_MAP_SW.getText());
				int tmpmap_SH = Integer.parseInt(field_MAP_SH.getText());
				if (SDef.map_SW > 0 && SDef.map_SH > 0 && SDef.tile_SW > 0 && SDef.tile_SH > 0) {
					// �������õ�ͼ��С
					short[][] tmp = new short[tmpmap_SW][tmpmap_SH];
					for (int i = 0; i < tmpmap_SW; i++) {
						for (int j = 0; j < tmpmap_SH; j++) {
							if (i < SDef.map_SW && j < SDef.map_SH) {
								tmp[i][j] = SDef.layer0[i][j];
							} else {
								tmp[i][j] = -1;
							}
						}
					}
					SDef.map_SW = tmpmap_SW;
					SDef.map_SH = tmpmap_SH;
					SDef.layer0 = new short[SDef.map_SW][SDef.map_SH];
					for (int i = 0; i < SDef.map_SW; i++) {
						for (int j = 0; j < SDef.map_SH; j++) {	
							SDef.layer0[i][j]= tmp[i][j];
						}
					}
					setVisible(false);
				} else {
					JOptionPane.showMessageDialog(this, "û�д����µ�ͼ��������������С�ڵ���0", "����", JOptionPane.ERROR_MESSAGE);
				}
			} catch (Exception ex) {
				JOptionPane.showMessageDialog(this, "���ݶ�ȡ����", "����", JOptionPane.ERROR_MESSAGE);
			}

		}

		if (obj == buttonCL) {
			setVisible(false);
		}
	}

}
