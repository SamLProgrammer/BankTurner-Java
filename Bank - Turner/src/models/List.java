package models;

public class List<T> {

	private Node<T> headNode;
	
	public List() {
	}
	
	public void addNodeToHead(Node<T> node) {
		node.setNext(headNode);
		setHeadNode(node);
	}
	
	public void addNodeToTail(Node<T> node) {
		if(headNode == null) {
			headNode = node;
		}
		else {
			Node<T> auxNode = headNode;
			while(auxNode.getNext() != null) {
				auxNode = auxNode.getNext();
			}
			auxNode.setNext(node);
		}
	}
	
	public void addNodeToAny(Node<T> indexNode, Node<T> node) {
		Node<T> auxNode = headNode; 
		while(!auxNode.equals(indexNode)) {
			auxNode = auxNode.getNext();
		}
		node.setNext(auxNode.getNext());
		auxNode.setNext(node);
	}
	
	public Node<T> getTailNode() {
		Node<T> auxNode = headNode;
		while(auxNode.getNext() != null) {
			auxNode = auxNode.getNext();
		}
		return auxNode;
	}
	
	public void deleteHeadNode() {
		Node<T> auxNode = headNode;
		headNode = auxNode.getNext();
		auxNode = null;
	}
	
	public void deleteLastNode() {
		Node<T> auxNode = headNode;
		while(auxNode.getNext().getNext() != null) {
			auxNode = auxNode.getNext();
		}
		auxNode.setNext(null);
	}
	
	public void deleteAnyNode(Node<Turn> node) {
		if(node.equals(headNode)) {
			deleteHeadNode();
		}
		else {
			Node<T> auxNode = headNode;
			while(!auxNode.getNext().equals(node)) {
				auxNode = auxNode.getNext();
			}
			auxNode.setNext(auxNode.getNext().getNext());
		}
	}
	
	public int size() {
		int size = 0;
		if(headNode != null) {
			Node<T> auxNode = headNode;
			while(auxNode != null) {
				size++;
				auxNode = auxNode.getNext();
			}
		}
		return size;
	}
	
	public Node<T> getHeadNode() {
		return headNode;
	}

	public void setHeadNode(Node<T> headNode) {
		this.headNode = headNode;
	}
	
}
