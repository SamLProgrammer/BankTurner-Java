package views;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionListener;
import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import control.Actions;
import models.Node;
import models.Turn;

public class SouthPanel extends JPanel{
	
	private ActionListener controller;
	private boolean flag;
	private JLabel infoLabel;
	private RoundJButton addRegistryBtn;
	private TablePanel tablePanel;
	
	
	public SouthPanel(ActionListener controller) {
		setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		setBackground(Color.decode("#262B2C"));
		this.controller = controller;
		initComponents(controller);
	}
	
	private void initComponents(ActionListener controller) {	
		
		JPanel containerPanel = new JPanel();
		containerPanel.setLayout(new GridLayout(1, 2, 20, 0));
		containerPanel.setBorder(BorderFactory.createEmptyBorder(0, 0, 20, 20));
		
		JPanel globalPanel = new JPanel();
		globalPanel.setLayout(new GridLayout(2, 1));
		globalPanel.setOpaque(false);
		
		JPanel abovePanel = new JPanel();
		abovePanel.setLayout(new BoxLayout(abovePanel, BoxLayout.X_AXIS));
		abovePanel.setBorder(BorderFactory.createEmptyBorder(0, 0, 10, 0));
		abovePanel.setOpaque(false);
		JPanel belowPanel = new JPanel();
		belowPanel.setLayout(new BoxLayout(belowPanel, BoxLayout.X_AXIS));
		belowPanel.setOpaque(false);
		belowPanel.setBorder(BorderFactory.createEmptyBorder(10, 0, 0, 0));
		JPanel addPanelBtn = new JPanel();
		addPanelBtn.setLayout(new BorderLayout());
		addPanelBtn.setOpaque(false);
		addPanelBtn.setBorder(BorderFactory.createEmptyBorder(0, 55, 0, 10));
		JPanel reportPanelBtn = new JPanel();
		reportPanelBtn.setLayout(new BorderLayout());
		reportPanelBtn.setOpaque(false);
		reportPanelBtn.setBorder(BorderFactory.createEmptyBorder(0, 10, 0, 10));
		JPanel savePanelBtn = new JPanel();
		savePanelBtn.setLayout(new BorderLayout());
		savePanelBtn.setOpaque(false);
		savePanelBtn.setBorder(BorderFactory.createEmptyBorder(0, 55, 0, 10));
		JPanel loadPanelBtn = new JPanel();
		loadPanelBtn.setLayout(new BorderLayout());
		loadPanelBtn.setOpaque(false);
		loadPanelBtn.setBorder(BorderFactory.createEmptyBorder(0, 10, 0, 10));
		savePanelBtn.setAlignmentX(CENTER_ALIGNMENT);
		
		infoLabel = new JLabel("", SwingConstants.CENTER);
		infoLabel.setOpaque(true);
		infoLabel.setBackground(Color.decode("#202020"));
		
		addRegistryBtn = new RoundJButton("#EEF3F7", "/img/Next.png",12);
		addRegistryBtn.setContentAreaFilled(false);
		addRegistryBtn.setActionCommand(Actions.NEXT_TURN_BUTTON.name());
		addRegistryBtn.addActionListener(controller);
		addRegistryBtn.setText("<html><p style=\"text-align: center;\">ATENDER<br>SIGUIENTE</br></p></html>");
		addRegistryBtn.setForeground(Color.decode("#EEF3F7"));
		
		tablePanel = new TablePanel();
		tablePanel.setPreferredSize(new Dimension(getWidth()/4,getHeight()/5));
		
		RoundJButton reportBtn = new RoundJButton("#EEF3F7","/img/Turns.png",12);
		reportBtn.setContentAreaFilled(false);
		reportBtn.setActionCommand(Actions.ADD_NEW_TURN.name());
		reportBtn.addActionListener(controller);
		reportBtn.setText("<html><p style=\"text-align: center;\">NUEVO<br>TURNO</br></p></html");
		reportBtn.setForeground(Color.decode("#EEF3F7"));
		RoundJButton saveBtn = new RoundJButton("#EE3F3F7","/img/save.png",12);
		saveBtn.setContentAreaFilled(false);
		saveBtn.setActionCommand(Actions.SAVE_BUTTON.name());
		saveBtn.addActionListener(controller);
		saveBtn.setText("GUARDAR DATOS");
		saveBtn.setAlignmentX(SwingConstants.CENTER);
		saveBtn.setForeground(Color.decode("#EEF3F7"));
		RoundJButton loadBtn = new RoundJButton("#EE3F3F7","/img/Load.png",12);
		loadBtn.setContentAreaFilled(false);
		loadBtn.setActionCommand(Actions.LOAD_BUTTON.name());
		loadBtn.addActionListener(controller);
		loadBtn.setText("CARGAR DATOS");
		loadBtn.setForeground(Color.decode("#EEF3F7"));	
		
		JButton expandUpButton = new JButton();
		expandUpButton.setContentAreaFilled(false);
		expandUpButton.setFocusPainted(false);
		expandUpButton.setIcon(new ImageIcon(getClass().getResource("/img/UpExpandArrow2.png")));
		expandUpButton.setHorizontalTextPosition(SwingConstants.CENTER);
		expandUpButton.setVerticalTextPosition(SwingConstants.BOTTOM);
		expandUpButton.setAlignmentX(JButton.CENTER_ALIGNMENT);
		expandUpButton.setActionCommand(Actions.EXPAND_BUTTON_UP.name());
		expandUpButton.addActionListener(controller);
		
		JPanel expandUpButtonPanel = new JPanel();
		expandUpButtonPanel.setLayout(new BorderLayout());
		expandUpButtonPanel.setOpaque(false);
		expandUpButtonPanel.add(expandUpButton);
		
		JButton expandDownButton = new JButton();
		expandDownButton.setContentAreaFilled(false);
		expandDownButton.setFocusPainted(false);
		expandDownButton.setIcon(new ImageIcon(getClass().getResource("/img/DownExpandArrow2.png")));
		expandDownButton.setHorizontalTextPosition(SwingConstants.CENTER);
		expandDownButton.setVerticalTextPosition(SwingConstants.BOTTOM);
		expandDownButton.setAlignmentX(JButton.CENTER_ALIGNMENT);
		expandDownButton.setActionCommand(Actions.EXPAND_BUTTON_DOWN.name());
		expandDownButton.addActionListener(controller);
		
		JPanel expandDownButtonPanel = new JPanel();
		expandDownButtonPanel.setLayout(new BorderLayout());
		expandDownButtonPanel.setOpaque(false);
		expandDownButtonPanel.add(expandDownButton);
		expandDownButtonPanel.setBorder(BorderFactory.createEmptyBorder(0, 0, 20, 0));
		
		addPanelBtn.add(addRegistryBtn);
		reportPanelBtn.add(reportBtn);
		savePanelBtn.add(saveBtn);
		loadPanelBtn.add(loadBtn);
		
		abovePanel.add(addPanelBtn);
		abovePanel.add(Box.createRigidArea(new Dimension(35, 0)));
		abovePanel.add(reportPanelBtn);

		belowPanel.add(savePanelBtn);
		belowPanel.add(Box.createRigidArea(new Dimension(35, 0)));
		belowPanel.add(loadPanelBtn);;
		
		globalPanel.add(abovePanel);
		globalPanel.add(belowPanel);
		
		containerPanel.add(globalPanel);
		containerPanel.add(tablePanel);
		containerPanel.add(infoLabel);
		
		if(flag) {
		add(expandDownButtonPanel);
		add(containerPanel);
		}
		else {
			add(expandUpButtonPanel);
		}
	}
	
	public void setFlag(boolean flag) {
		this.flag = flag;
		removeAll();
		initComponents(controller);
		updateUI();
	}
	
	public boolean isFlag() {
		return flag;
	}
	
	public void updateDataLabel(String string) {
		infoLabel.setText(string);
		repaint();
	}
	
	public void switchAttendButton(boolean flag) {
		addRegistryBtn.setEnabled(flag);
	}
	
	public void updateTable(Node<Turn> headNode) {
		tablePanel.updateRowsTable(headNode);
	}

}