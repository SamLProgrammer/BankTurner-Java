package models;

public class Queue<T> extends List<Turn>{
	
	private int turnNumber;
	
	public Queue() {
		initQueue();
		turnNumber = 1;
	}
	
	public Turn createTurn(String id, String name) {
		return new Turn(turnNumber++,name, id);
	}
	
	public void toStringQueue() {
		Node<Turn> auxNode = getHeadNode();
		while(auxNode != null) {
			auxNode = auxNode.getNext();
		}
	}
	
	public Node<Turn> getTurnByIndex(int turnNumber) {
		Node<Turn> auxNode = getHeadNode();
		while(auxNode.getData().getTurnNumber() != turnNumber) {
			auxNode = auxNode.getNext();
		}
		return auxNode;
	}
	
	public void initQueue() {
		
	}
	
}
