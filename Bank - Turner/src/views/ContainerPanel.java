package views;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionListener;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import control.Actions;
import models.Node;
import models.Turn;

public class ContainerPanel extends JPanel{

	QueuePanel queuePanel;
	MainFrame mainFrame;
	JScrollPane scrollPanel;
	SouthPanel buttonsPanel;
	
	public ContainerPanel(ActionListener listener,MainFrame mainFrame) {
		setLayout(new BorderLayout());
		this.mainFrame = mainFrame;
		initComponents(listener);
	}
	
	public void initComponents(ActionListener listener) {
		queuePanel = new QueuePanel(mainFrame);
		
		scrollPanel = new JScrollPane();
		scrollPanel.setViewportView(queuePanel);
		scrollPanel.setPreferredSize(queuePanel.getPreferredSize());
		
		JButton addTurnBtn = new JButton("NUEVO TURNO");
		
		LabelPanel labelPanel = new LabelPanel("FILA DE TURNOS");
		
		buttonsPanel = new SouthPanel(listener);
		
		JPanel buttonPanel = new JPanel();
		buttonPanel.setLayout(new BorderLayout());
		buttonPanel.add(addTurnBtn);
		buttonPanel.setBackground(Color.decode("#DED8D5"));
		buttonPanel.setBorder(BorderFactory.createEmptyBorder(0, 
				250, 0, 250));			
		addTurnBtn.setActionCommand(Actions.ADD_NEW_TURN.name());
		addTurnBtn.addActionListener(listener);
		addTurnBtn.setFocusPainted(false);
		addTurnBtn.setFont(new Font("Oswald", Font.BOLD, 20));
		addTurnBtn.setForeground(Color.decode("#A42626"));
		addTurnBtn.setBackground(Color.decode("#262B2C"));
		
		
		add(labelPanel, BorderLayout.NORTH);
		add(scrollPanel, BorderLayout.CENTER);
		add(buttonsPanel, BorderLayout.SOUTH);
	}
	
	public void updateQueuePanel(Node<Turn> headNode, boolean flag) {
		queuePanel.setHeadNode(headNode, flag);
		scrollPanel.revalidate();
	}
	
	public void expandSouthPanel(boolean flag) {
		buttonsPanel.setFlag(flag);
	}
	
	public void updateDataLabel(String string) {
		buttonsPanel.updateDataLabel(string);
	}
	
	public void switchAttendButton(boolean flag) {
		buttonsPanel.switchAttendButton(flag);
	}
	
	public void updateTable(Node<Turn> headNode) {
		buttonsPanel.updateTable(headNode);
	}
	
}
