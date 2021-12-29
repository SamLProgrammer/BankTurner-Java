package views;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JDialog;

public class FormularyDialog extends JDialog{
	
	SwitchingPanel switchPanel;
	
	MainFrame mf;

	public FormularyDialog(ActionListener listener) {
		setLayout(new BorderLayout());
		setIconImage(new ImageIcon(getClass().getResource("/img/bank.png")).getImage());
		locate();
		initComponents(listener);
		setModal(true);
	}
	
	public void initComponents(ActionListener listener) {
		switchPanel = new SwitchingPanel(listener);
		add(switchPanel, BorderLayout.CENTER);
	}
	
	private void locate() {
		Dimension screenDimension = Toolkit.getDefaultToolkit().getScreenSize();
		setSize((int)screenDimension.getWidth()/3, (int)(2*screenDimension.getHeight()/3));
		setLocation((int)(screenDimension.getWidth()/2 - this.getWidth()/2),
				(int)(screenDimension.getHeight()/2 - this.getHeight()/2 - screenDimension.getHeight()/20));
	}
	
	public void switchPanel(boolean flag) {
		switchPanel.switchFlag(flag);
	}
	
	public SwitchingPanel getSwitchPanel() {
		return switchPanel;
	}
	
	public void updateLabelStats(String string) {
		switchPanel.updateStatsLabel(string);
	}
	
	public void clean() {
		switchPanel.cleanData();
	}
	
	public int getTurnsBoxSelectedItem() {
		return switchPanel.getSelectedItemBox();
	}
	
}
