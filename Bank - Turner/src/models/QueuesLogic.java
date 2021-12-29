package models;

public class QueuesLogic {
	
	private Queue<Turn> queue;
	private Queue<Turn> historyQueue;
	private TimeManager timeManager;
	boolean avgFlag;
	
	public QueuesLogic() {
		queue = new Queue<Turn>();
		historyQueue = new Queue<Turn>();
		timeManager =  new TimeManager();
	}

	public void updateAverageAttentTime(Turn turn) {
		if(turn.getFinalTime() != null) {
//			System.out.println("aja " + timeManager.timeToString(timeManager.substractHours(turn.getInitTime(),turn.getFinalTime())));
		timeManager.addTime(timeManager.substractHours(turn.getInitTime(),turn.getFinalTime()));
//		System.out.println("avg" + timeManager.timeToString(timeManager.getElapsedTime()));
		}
	}
	
	public int remainingTurns(Node<Turn> node) {
		int counter = 0;
		Node<Turn> auxNode = queue.getHeadNode();
		while(auxNode != node) {
			counter++;
			auxNode = auxNode.getNext();
		}
		return counter;
	}
	
	public String getActualAvgTime() {
		String string = "0:0:0";
		if(avgFlag) {
			string = timeManager.timeToString(timeManager.divideElapsedTime(historyQueue.size()));
		}
		else {
			string = timeManager.timeToString(timeManager.divideElapsedTime(historyQueue.size()-1));
		}
		return string;
	}
	
	public String stringLabelInfo(int itemSelected) {
		Node<Turn> node = queue.getHeadNode();
		while(node.getData().getTurnNumber() != itemSelected) {
			node = node.getNext();
		}
		return "<html><p style=\"font-size: 14px; text-align: center;\">TU NOMBRE: " + node.getData().getName() + 
				"<br>TURNOS RESTANTES: " + remainingTurns(node) + "</br>"
				+ "<br>TIEMPO POR TURNO: " + getActualAvgTime() + "</br></p></html>";
	}
	
	public Queue<Turn> getQueue() {
		return queue;
	}

	public Queue<Turn> getHistoryQueue() {
		return historyQueue;
	}
	
	public Node<Turn> getQueueHeadNode() {
		return queue.getHeadNode();
	}

	public void addNodeToHeadHistoryQueue(Node<Turn> node) {
		historyQueue.addNodeToHead(node);
	}
	
	public void deleteHeadNodeQueue() {
		queue.deleteHeadNode();
	}
	
	public void addNodetoTailQueue(Node<Turn> node) {
		queue.addNodeToTail(node);
		if(queue.size() == 1) {
			avgFlag = false;
		}
	}
	
	public Node<Turn> getHistoryQueueHeadNode() {
		return historyQueue.getHeadNode();
	}
	
	public void resetTimes() {
		timeManager.resetElapsedTime();
	}
	
	public void setAvgFlag(boolean flag) {
		avgFlag = flag;
	}
}
