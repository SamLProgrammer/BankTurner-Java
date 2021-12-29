package views;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.UIManager;

import control.Actions;
import models.Node;
import models.Turn;

public class SwitchingPanel extends JPanel implements KeyListener{
	private JTextField nameField, idField;
	private JLabel daysLabel, xFieldLabel, turnStatsLabel;
	private JComboBox<Integer> turnsBox;
	private DCButton acceptB, cancelB;
	private LabelPanel labelPanel;
	private ActionListener listener;
	private Node<Turn> node;
	private JPanel switchedPanel;
	boolean flag;
	
	public SwitchingPanel(ActionListener listener) {
		setSize(550, 700);
		this.listener = listener;
		setUIManager();
		setLayout(new BorderLayout());
		initComponents(listener);
	}
	
	private void initComponents(ActionListener listener) {
		JPanel mainPanel = new JPanel();
		mainPanel.setLayout(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();
        
		labelPanel = new LabelPanel("<html><p style=\"text-align: center;\">AGREGUE<br>SU TURNO</br></p></html");
		
		nameField = new RoundJTextField(12);
		nameField.setFont(new Font("Oswald", Font.PLAIN, 18));
		nameField.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
		nameField.setHorizontalAlignment(JTextField.CENTER);
		nameField.addKeyListener(this);
		idField = new RoundJTextField(12);
		idField.setFont(new Font("Oswald", Font.PLAIN, 18));
		idField.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
		idField.setHorizontalAlignment(JTextField.CENTER);
		idField.addKeyListener(this);
		daysLabel = new JLabel(" # DOCUMENTO ");
		daysLabel.setHorizontalAlignment(JLabel.CENTER);
		xFieldLabel = new JLabel("NOMBRE - CLIENTE");
		xFieldLabel.setHorizontalAlignment(JLabel.CENTER);
		acceptB = new DCButton("#EF2D2D");
		acceptB.setText("HECHO");
		acceptB.setActionCommand(Actions.ACCEPT_FORMULARY_BUTTON.name());
		acceptB.addActionListener(listener);
		acceptB.setForeground(Color.decode("#6FE3FF"));
		cancelB = new DCButton("#17CD47");
		cancelB.setText("CANCELAR");
		cancelB.setActionCommand(Actions.CANCEL_FORMULARY_BUTTON.name());
		cancelB.addActionListener(listener);
		cancelB.setForeground(Color.decode("#EF2D2D"));
		
		turnStatsLabel = new JLabel("", SwingConstants.CENTER);
		turnStatsLabel.setBackground(Color.white);
		turnStatsLabel.setOpaque(true);
		
		JPanel statsLabelPanel = new JPanel();
		statsLabelPanel.setLayout(new BorderLayout());
		statsLabelPanel.add(turnStatsLabel);
		statsLabelPanel.setBorder(BorderFactory.createEmptyBorder(0, this.getWidth()/6, 10, this.getWidth()/6));
		
		JButton expandUpButton = new JButton();
		expandUpButton.setContentAreaFilled(false);
		expandUpButton.setFocusPainted(false);
		expandUpButton.setIcon(new ImageIcon(getClass().getResource("/img/UpExpandArrow2.png")));
		expandUpButton.setHorizontalTextPosition(SwingConstants.CENTER);
		expandUpButton.setVerticalTextPosition(SwingConstants.BOTTOM);
		expandUpButton.setAlignmentX(JButton.CENTER_ALIGNMENT);
		expandUpButton.setActionCommand(Actions.SWITCH_UP_BUTTON_DIALOG.name());
		expandUpButton.addActionListener(listener);
		
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
		expandDownButton.setActionCommand(Actions.SWITCH_DOWN_BUTTON_DIALOG.name());
		expandDownButton.addActionListener(listener);
		
		JPanel expandDownButtonPanel = new JPanel();
		expandDownButtonPanel.setLayout(new BorderLayout());
		expandDownButtonPanel.setOpaque(false);
		expandDownButtonPanel.add(expandDownButton);
		
		switchedPanel = new JPanel();
		switchedPanel.setLayout(new GridLayout(2, 1));
		JLabel boxTitleLabel = new JLabel("ENCUENTRE SU TURNO", SwingConstants.CENTER);
		JLabel statsTitleLabel = new JLabel("ESTADÍSTICAS", SwingConstants.CENTER);
		turnsBox = new JComboBox<>();
		updateTurnsBox(node);
		turnsBox.setFont(new Font("Oswald", Font.BOLD, 50));
		turnsBox.addActionListener(listener);
		turnsBox.setActionCommand(Actions.TURN_ITEM_SELECTED.name());
		((JLabel)turnsBox.getRenderer()).setHorizontalAlignment(JLabel.CENTER);
		turnsBox.getComponent(0).setBackground(Color.decode("#A42626"));
		JPanel boxPanel = new JPanel();
		boxPanel.setLayout(new GridLayout(2, 1));
		boxPanel.add(boxTitleLabel);
		boxPanel.add(turnsBox);
		boxPanel.setBorder(BorderFactory.createEmptyBorder(0, this.getWidth()/3, 0, this.getWidth()/3));
		JPanel statsPanel = new JPanel();
		statsPanel.setLayout(new GridLayout(2, 1));
		statsPanel.add(statsTitleLabel);
		statsPanel.add(statsLabelPanel);
		turnStatsLabel.setOpaque(true);
		turnStatsLabel.setBackground(Color.decode("#202020"));
		switchedPanel.add(boxPanel);
		switchedPanel.add(statsPanel);
		
		JPanel xFieldLabelPanel = new JPanel();
		xFieldLabelPanel.setLayout(new BorderLayout());
		xFieldLabelPanel.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 0));
		JPanel xFieldPanel = new JPanel();
		xFieldPanel.setLayout(new GridLayout(2, 1));
		xFieldPanel.setBorder(BorderFactory.createEmptyBorder(0, 100, 10, 100));
		JPanel daysLabelPanel = new JPanel();
		daysLabelPanel.setLayout(new BorderLayout());
		daysLabelPanel.setBorder(BorderFactory.createEmptyBorder(10, 20, 0, 20));
		JPanel daysBoxPanel = new JPanel();
		daysBoxPanel.setLayout(new GridLayout(2,1));
		daysBoxPanel.setBorder(BorderFactory.createEmptyBorder(0, 100, 10, 100));
		JPanel buttonsPanel = new JPanel();
		buttonsPanel.setLayout(new BoxLayout(buttonsPanel, BoxLayout.X_AXIS));
		buttonsPanel.setBorder(BorderFactory.createEmptyBorder(10, 100, 20, 100));
		
		xFieldLabelPanel.add(xFieldLabel);
		xFieldPanel.add(xFieldLabelPanel);
		xFieldPanel.add(nameField);
		
		daysLabelPanel.add(daysLabel);
		daysBoxPanel.add(daysLabelPanel);
		daysBoxPanel.add(idField);
		
		buttonsPanel.add(acceptB);
		buttonsPanel.add(Box.createRigidArea(new Dimension(20, 0)));
		buttonsPanel.add(cancelB);
		
		c.gridx = 0;
		c.gridy = 0;
		c.fill = GridBagConstraints.BOTH;
		c.weightx = 1;
		c.weighty = 1;
		mainPanel.add(labelPanel, c);

		c.gridx = 0;
		c.gridy = 1;
		c.weightx = 1;
		c.weighty = 1;
		mainPanel.add(xFieldPanel, c);

		c.gridx = 0;
		c.gridy = 2;
		c.weightx = 1;
		c.weighty = 1;
		mainPanel.add(daysBoxPanel, c);

		c.gridx = 0;
		c.gridy = 4;
		c.weightx = 1;
		c.weighty = 2;
		c.gridheight = 2;
		c.insets = new Insets(30, 0, 0, 0);
		c.fill = GridBagConstraints.VERTICAL;
		mainPanel.add(buttonsPanel, c);
		
		if(flag) {
			add(labelPanel, BorderLayout.NORTH);
			add(switchedPanel,BorderLayout.CENTER);
			add(expandUpButtonPanel, BorderLayout.SOUTH);
		}
		else {
			add(mainPanel, BorderLayout.CENTER);
			add(expandDownButtonPanel, BorderLayout.NORTH);
		}
	}
	
	private void setUIManager() {
		UIManager.put("TextField.border", new RoundedBorder(15,"#6C8BAB"));
		UIManager.put("Label.font", new Font("Oswald", Font.BOLD, 15));
		UIManager.put("Panel.background", Color.decode("#262B2C"));
		UIManager.put("Label.foreground", Color.decode("#8F969B"));
	}
	
	public void cleanData() {
		switchFlag(false);
		nameField.setText("");
		idField.setText("");
	}
	
	public void updateTurnsBox(Node<Turn> node) {
		Node<Turn> auxNode = node;
		this.node = node;
		DefaultComboBoxModel<Integer> modelBox = new DefaultComboBoxModel<>();
		while(auxNode != null) {
			modelBox.addElement(auxNode.getData().getTurnNumber());
			auxNode = auxNode.getNext();
		}
		turnsBox = new JComboBox<>(modelBox);
	}
	
	public void switchFlag(boolean flag) {
		this.flag = flag;
		removeAll();
		initComponents(listener);
		updateUI();
	}
	
	public String getNameFieldData() {
		return nameField.getText();
	}
	
	public String getIdFieldData() {
		return idField.getText();
	}
	
	@Override
	public void keyTyped(KeyEvent e) {
		 if (!Character.isDigit(e.getKeyChar()) && 
			 !Character.isLetter(e.getKeyChar()) && !(e.getKeyChar() == 32)) {
             e.consume();
         }
	}

	@Override
	public void keyPressed(KeyEvent e) {
	}

	@Override
	public void keyReleased(KeyEvent e) {
	}
	
	public void updateStatsLabel(String string) {
		turnStatsLabel.setText(string);
		repaint();
	}
	
	public int getSelectedItemBox() {
		return (int)turnsBox.getSelectedItem();
	}
	
}
