package control;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import models.Node;
import models.QueuesLogic;
import models.Turn;
import views.FormularyDialog;
import views.MainFrame;

public class Controller implements ActionListener{

	private MainFrame mainFrame;
	private FormularyDialog formularyDialog;
	private QueuesLogic queuesLogic;
	
	public Controller() {
		queuesLogic = new QueuesLogic();
		mainFrame = new MainFrame(this,queuesLogic.getQueueHeadNode());
		formularyDialog = new FormularyDialog(this);
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		switch (Actions.valueOf(e.getActionCommand())) {
		case ADD_NEW_TURN:
			showFormularyDialog();
			break;
		case NEXT_TURN_BUTTON:
			nextTurn();
			break;
		case ACCEPT_FORMULARY_BUTTON:
			addNewTurn();
			break;
		case CANCEL_FORMULARY_BUTTON:
			hideFormularyDialog();
			break;
		case EXPAND_BUTTON_UP:
			expandSouthPanel();
			break;
		case EXPAND_BUTTON_DOWN:
			compressSouthPanel();
			break;
		case LOAD_BUTTON:
			burnData();
			break;
		case SAVE_BUTTON:
			break;
		case SWITCH_DOWN_BUTTON_DIALOG:
			switchClientDialogPanelDown();
			break;
		case SWITCH_UP_BUTTON_DIALOG:
			switchClientDialogPanelUp();
			break;
		case TURN_ITEM_SELECTED:
			updateStatsLabel();
			break;
		}
	}
	
	private void showFormularyDialog() {
		formularyDialog.clean();
		formularyDialog.setVisible(true);
	}
	
	private void hideFormularyDialog() {
		formularyDialog.setVisible(false);
	}
	private void addNewTurn() {
		queuesLogic.addNodetoTailQueue(new Node<Turn>(queuesLogic.getQueue().createTurn(formularyDialog.getSwitchPanel().getIdFieldData(),
				formularyDialog.getSwitchPanel().getNameFieldData())));
		mainFrame.updateQueuePanel(queuesLogic.getQueueHeadNode(), true);
		formularyDialog.getSwitchPanel().updateTurnsBox(queuesLogic.getQueueHeadNode());
		if(queuesLogic.getQueueHeadNode() != null) {
			mainFrame.switchAttendButton(true);
		}
	}
	
	private void expandSouthPanel() {
		mainFrame.expandSouthPanel(true);
		if(queuesLogic.getQueueHeadNode() == null) {
			mainFrame.switchAttendButton(false);
		}
		if(queuesLogic.getHistoryQueueHeadNode() != null) {
			mainFrame.updateTable(queuesLogic.getHistoryQueueHeadNode());
		}
	}
	
	private void compressSouthPanel() {
		mainFrame.expandSouthPanel(false);
	}
	
	private void nextTurn() {
		if(queuesLogic.getQueueHeadNode() == null) {
			switchAvgFlag(true);
			queuesLogic.getHistoryQueueHeadNode().getData().setFinalTime();
			mainFrame.switchAttendButton(false);
			mainFrame.updateTable(queuesLogic.getHistoryQueueHeadNode());
			queuesLogic.updateAverageAttentTime(queuesLogic.getHistoryQueueHeadNode().getData());
		}
		else {
			Node<Turn> node = new Node<>();
			node.setT(queuesLogic.getQueueHeadNode().getData());
			queuesLogic.getHistoryQueue().addNodeToHead(node);
			queuesLogic.getHistoryQueueHeadNode().getData().setInitTime();
			if(queuesLogic.getHistoryQueueHeadNode().attachedNodesNumber() > 1) {
				if(queuesLogic.getHistoryQueueHeadNode().getNext().getData().getFinalTime() == null) {
				queuesLogic.getHistoryQueueHeadNode().getNext().getData().setFinalTime();
				}
				queuesLogic.updateAverageAttentTime(
				queuesLogic.getHistoryQueueHeadNode().getNext().getData());
			}
			queuesLogic.deleteHeadNodeQueue();
			mainFrame.updateQueuePanel(queuesLogic.getQueueHeadNode(),false);
			formularyDialog.getSwitchPanel().updateTurnsBox(queuesLogic.getQueueHeadNode());
			mainFrame.updateTable(queuesLogic.getHistoryQueueHeadNode());
		}
	}
	
	private void switchClientDialogPanelUp() {
		formularyDialog.switchPanel(false);
	}
	
	private void switchClientDialogPanelDown() {
		formularyDialog.switchPanel(true);
		if(queuesLogic.getQueueHeadNode() != null) {
		updateStatsLabel();
		}
	}
	
	private void updateStatsLabel() {
		try {
			formularyDialog.updateLabelStats(queuesLogic.stringLabelInfo(formularyDialog.getTurnsBoxSelectedItem()));
		} catch (Exception e) {
		}
	}
	
	private void switchAvgFlag(boolean flag) {
		queuesLogic.setAvgFlag(flag);
	}
	
	private void burnData() {
		String id = "", name = "";
		for (int i = 0; i < 4; i++) {
			id += 123456+i;
			name += (char)(i+65);
			queuesLogic.addNodetoTailQueue(new Node<Turn>(queuesLogic.getQueue().createTurn(id,
					name)));
			mainFrame.updateQueuePanel(queuesLogic.getQueueHeadNode(), true);
			formularyDialog.getSwitchPanel().updateTurnsBox(queuesLogic.getQueueHeadNode());
			if(queuesLogic.getQueueHeadNode() != null) {
				mainFrame.switchAttendButton(true);
			}
			id = "";
			name = "";
		}
	}
}