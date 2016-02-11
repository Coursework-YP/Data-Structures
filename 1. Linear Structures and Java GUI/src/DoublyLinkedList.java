/**
 * Generic version of a doubly linked list that extends a generic linked list
 * class
 * 
 * @author patel22y
 *
 * @param <T>
 */
public class DoublyLinkedList<T> extends LinkedList<T> {

	// I did not use the tail in the 201 assignment so I don't know if I need it
	// at this point
	// private DoublyLinkedListNode<T> tail;

	/**
	 * Constructor calls the parent class
	 * 
	 * @param none
	 * @return none
	 */
	public DoublyLinkedList() {
		super();
	}

	/**
	 * Get data stored in head node of list.
	 * 
	 * @param none
	 * @return T
	 **/
	public T getFirst() {
		// if the head is not empty
		if (head != null) {
			// return the data in the head node of the list
			return getFirstNode().getData();
		}
		// otherwise return null
		else {
			return null;
		}
	}

	/**
	 * Get data stored in tail node of list.
	 * 
	 * @param none
	 * @return T
	 **/
	public T getLast() {
		// return the data in the last node
		return getLastNode().getData();
	}

	/**
	 * Get the tail node of the list.
	 * 
	 * @param none
	 * @return LinkedListNode<T>
	 **/
	public DoublyLinkedListNode<T> getLastNode() {
		// if the head is null
		if (head == null) {
			// return null
			return null;
		}
		// create a new linkedlistnode that's equal to the head
		DoublyLinkedListNode<T> node = (DoublyLinkedListNode<T>) head;
		// while the node after the current node isn't null
		while (node.getNext() != null) {
			// get the next node
			node = (DoublyLinkedListNode<T>) node.getNext();
		}
		// return the last node
		return node;
	}

	/**
	 * Get the head node of the list.
	 * 
	 * @param none
	 * @return LinkedListNode<T>
	 **/
	public DoublyLinkedListNode<T> getFirstNode() {
		// return the head node of the list
		return (DoublyLinkedListNode<T>) head;
	}

	/**
	 * @Override the insertFirst function Resets the pointers since this is a
	 *           DOUBLY linked list now
	 */
	public void insertFirst(T data) {
		// make a new node
		DoublyLinkedListNode<T> newNode = new DoublyLinkedListNode<T>();
		// /add data to this node
		newNode.setData(data);
		// if the head isn't null
		if (head != null) {
			// insert the new node at the head
			insertFirstNode(newNode);
		}
		// otherwise
		else {
			// the head is the new node
			head = newNode;
			// set the previous pointer of the head to be null
			((DoublyLinkedListNode<T>) head).setPrevious(null);
			// set the next pointer of the head to be null
			head.setNext(newNode.getNext());
		}
	}

	/**
	 * Inserts the new node at the beginning of the list
	 * 
	 * @param newNode
	 * @return void
	 */
	public void insertFirstNode(DoublyLinkedListNode<T> newNode) {
		// setting the previous pointer of the current head to the new node
		((DoublyLinkedListNode<T>) head).setPrevious(newNode);

		// set new node to point to the head
		newNode.setNext(head);
		// set new node's previous to be null
		newNode.setPrevious(null);
		// set head equal to the new node
		head = newNode;
	}

	/**
	 * Insert a node after the current node
	 * 
	 * @param currentNode
	 * @param data
	 * @return void
	 */
	public void insertAfter(DoublyLinkedListNode<T> currentNode, T data) {
		System.out.println("The current node is: " + currentNode
				+ " and the data being inserted is: " + data);
		System.out.println("The next node is: " + currentNode.getNext());

		// if the next points to null
		if (currentNode.getNext() == null) {
			// make a new node
			DoublyLinkedListNode<T> newNode = new DoublyLinkedListNode<T>();
			// /add data to this node
			newNode.setData(data);
			// set the node to be the new node
			currentNode.setNext(newNode);
			// set the new node to point to the current node
			newNode.setPrevious(currentNode);

		}

		else {
			// make a new node
			DoublyLinkedListNode<T> newNode = new DoublyLinkedListNode<T>();
			// /add data to this node
			newNode.setData(data);

			// set the pointer for the next node to point to the previous (new)
			// node
			((DoublyLinkedListNode<T>) currentNode.getNext())
					.setPrevious(newNode);

			// set the pointer for the new node to point to the next node
			newNode.setNext(currentNode.getNext());

			// set the current node to point to the new node
			currentNode.setNext(newNode);

			// set the new node to point to the current node
			newNode.setPrevious(currentNode);
		}
	}

	/**
	 * Insert the data at the last position in a node
	 * 
	 * @param data
	 * @return void
	 */
	public void insertLast(T data) {
		// make a new node
		DoublyLinkedListNode<T> lastNode = new DoublyLinkedListNode<T>();

		// if the list is empty
		if (head == null) {
			// insert at the first node
			insertFirst(data);
		}
		// otherwise
		else {
			// /add data to this node
			lastNode.setData(data);
			// invoke insertafter to link up the new node
			insertAfter(getLastNode(), data);
		}
	}

	/**
	 * @param none
	 * @return void
	 */
	public void deleteFirst() {
		// if the head isn't null
		if (!head.equals(null)) {
			if (head.getNext() != null) {
				// have the head be whatever the next node is
				head = head.getNext();
				((DoublyLinkedListNode<T>) head).setPrevious(null);
			} else {
				head = null;
			}
		}
	}

	/**
	 * @param node
	 * @return void
	 */
	public void deleteNext(DoublyLinkedListNode<T> currentNode) {
		// if the node after the current node is not null and if the node after
		// the node after the current node isn't null
		if (!currentNode.getNext().equals(null)
				&& !currentNode.getNext().getNext().equals(null)) {
			// have the current node point to the node after the node after the
			// current node
			currentNode.setNext(currentNode.getNext().getNext());
			// set the previous pointer of the current node's next node to point
			// to the current node
			((DoublyLinkedListNode<T>) currentNode.getNext().getNext())
					.setPrevious(currentNode);
		}
	}

	/**
	 * @param none
	 * @return void
	 */
	public void deleteLast() {
		// if the head is null
		if (head == null) {
			// just stop, leave the function
			return;
		}
		// if the node after the head is null
		if (head.getNext() == null) {
			// set the head to be null
			head = null;
			// just stop, leave the function
			return;
		}

		// Declare variables
		// node goes through to get the last node
		// previous holds the value of the previous node
		DoublyLinkedListNode<T> node = (DoublyLinkedListNode<T>) head;
		DoublyLinkedListNode<T> previous = (DoublyLinkedListNode<T>) head;
		// while the node after the current node isn't null
		while (node.getNext() != null) {
			// set previous equal to node
			previous = node;
			// have node equal the node after the current node
			node = (DoublyLinkedListNode<T>) node.getNext();
		}
		// leave the while loop if the node after current node is null
		// set the previous node (before incrementing) to null
		previous.setNext(null);
	}

	/**
	 * Return the number of nodes in this list.
	 * 
	 * @param none
	 * @return int
	 **/
	public int size() {
		// if the head isn't null
		if (head != null) {
			// create a node that's set to the head
			DoublyLinkedListNode<T> node = (DoublyLinkedListNode<T>) head;
			// set number of nodes to 0
			int numNodes = 0;

			// while the node isn't null
			while (node != null) {
				// increment the number of nodes
				numNodes++;
				// set node to the next node
				node = (DoublyLinkedListNode<T>) node.getNext();
			}
			// return the number of nodes
			return numNodes;
		}
		// otherwise, the head is null meaning the list is empty
		else {
			// size is 0 (empty)
			return 0;
		}
	}

	/**
	 * Return a String representation of the list.
	 * 
	 * @param none
	 * @return String of the data
	 **/
	public String toString() {
		// create a string that holds the value of the list
		// held inside curly braces for my viewing pleasure
		String s = "";
		// if the list is not empty
		if (isEmpty() != true) {
			// create a node and set it to the head
			DoublyLinkedListNode<T> curNode = (DoublyLinkedListNode<T>) head;
			// while the current node isn't null
			while (curNode != null) {
				// add the data in the node to my string
				s += curNode.getData();
				// set current node to be the next node
				curNode = (DoublyLinkedListNode<T>) curNode.getNext();
				s += "\n";
			}
		}
		// return the final string
		return s;
	}

	/**
	 * Deletes the previous node
	 * 
	 * @param none
	 * @return void
	 */
	public void deletePrev(DoublyLinkedListNode<T> currentNode) {
		// if the node after the current node is not null and if the node after
		// the node after the current node isn't null
		if (currentNode.equals(null) && !currentNode.getPrevious().equals(null)
				&& !currentNode.getPrevious().getPrevious().equals(null)) {

			// have the current node point to the node after the node after the
			// current node
			currentNode.setPrevious(currentNode.getPrevious().getPrevious());
			// set the previous pointer of the current node's Previous node to
			// point to the current node
			((DoublyLinkedListNode<T>) currentNode.getPrevious().getPrevious())
					.setNext(currentNode);
		}

		// if the current node is the second element in the list (prev = head)
		else if (currentNode.getPrevious() == head) {

			// set head to be the current node
			head = currentNode;
			((DoublyLinkedListNode<T>) head).setPrevious(null);
		}
		// otherwise
		else {
			// have the current node point to the node after the node after the
			// current node
			currentNode.setPrevious(currentNode.getPrevious().getPrevious());
			// set the previous pointer of the current node's Previous node to
			// point to the current node
			currentNode.getPrevious().setNext(currentNode);
	
			// exit
			return;
		}
	}
}
