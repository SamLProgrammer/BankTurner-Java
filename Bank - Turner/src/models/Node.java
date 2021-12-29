package models;

public class Node<T> {

	private T data;
	
	private Node<T> next;
	
	public Node(T data) {
		this.data = data;
	}

	public Node() {
	}

	public int attachedNodesNumber() {
		int counter = 0;
		Node<T> auxNode = this;
		while(auxNode != null) {
			counter++;
			auxNode = auxNode.getNext();
		}
		return counter;
	}

	public T getData() {
		return data;
	}

	public Node<T> getNext() {
		return next;
	}

	public void setT(T data) {
		this.data = data;
	}

	public void setNext(Node<T> next) {
		this.next = next;
	}
	
}
