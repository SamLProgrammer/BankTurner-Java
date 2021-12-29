package views;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JFrame;

import models.Node;
import models.Turn;

public class MainFrame extends JFrame{

	private ContainerPanel containerPanel;
	
	public MainFrame(ActionListener listener, Node<Turn> headNode) {
		initComponents(listener);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setIconImage(new ImageIcon(getClass().getResource("/img/bank.png")).getImage());
		locate();
//		updateQueuePanel(headNode, false);
		setVisible(true);
	}
	
	public void initComponents(ActionListener listener) {
		containerPanel = new ContainerPanel(listener, this);
		add(containerPanel, BorderLayout.CENTER);
	}
	
	public void locate() {
		Dimension screenDimension = Toolkit.getDefaultToolkit().getScreenSize();
		setExtendedState(MAXIMIZED_BOTH);
		setSize(1024,768);
		setLocation((int)(screenDimension.getWidth()/2 - this.getWidth()/2), 
				(int)(screenDimension.getHeight()/2 - this.getHeight()/2));
	}
	
	public void updateQueuePanel(Node<Turn> headNode, boolean flag) {
		containerPanel.updateQueuePanel(headNode, flag);
	}
	
	public void expandSouthPanel(boolean flag) {
		containerPanel.expandSouthPanel(flag);
	}
	
	public void updateDataLabel(String string) {
		containerPanel.updateDataLabel(string);
	}
	
	public void switchAttendButton(boolean flag) {
		containerPanel.switchAttendButton(flag);
	}
	
	public void updateTable(Node<Turn> headNode) {
		containerPanel.updateTable(headNode);
	}
}
