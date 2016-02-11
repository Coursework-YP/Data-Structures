/**
 * Generic LinkedList<T> class
 * @author patel22y
 *
 * @param <T>
 **/
public class LinkedList<T> {
	//the single instance field which holds the head node
	protected LinkedListNode<T> head;
	
	/**
	 * Get data stored in head node of list.
	 * @param none
	 * @return T
	 **/
	public T getFirst() {
		//if the head is not empty
		if (head!= null) {
			//return the data in the head node of the list
			return head.getData();
		}
		//otherwise return null
		else { return null; }
	}
	 
	/**
	 * Get the head node of the list.
	 * @param none
	 * @return LinkedListNode<T>
	 **/
	public LinkedListNode<T> getFirstNode() {
		//return the head node of the list
		return head;		
	}
	 
	/**
	 * Get data stored in tail node of list.
	 * @param none
	 * @return T
	 **/
	public T getLast() {
		//return the data in the last node
		return getLastNode().getData();
	}
	 
	/**
	 * Get the tail node of the list.
	 * @param none
	 * @return LinkedListNode<T> 
	 **/
	public LinkedListNode<T> getLastNode() {
		//if the head is null
		if (head == null) {
			//return null
			return null;
		}
		//create a new linkedlistnode that's equal to the head
		LinkedListNode<T> node = head;
		//while the node after the current node isn't null
		while (node.getNext() != null) {
			//get the next node
			node = node.getNext();
		}
		//return the last node
		return node;
	}
	 
	/**
	 * Insert a new node with data at the head of the list.
	 * @param Data to be inserted (of type T)
	 * @return void
	 **/
	public void insertFirst( T data ) {
		//make a new node 
		LinkedListNode<T> newNode = new LinkedListNode<T>();
		///add data to this node
		newNode.setData(data);
		//if the head isn't null
		if(head != null){
			//insert the new node at the head
			insertFirstNode(newNode);
		}
		//otherwise
		else{
			//the head is the new node
			head = newNode;
		}
	}
	
	/**
	 * Insert a node at the first node spot
	 * @param The node to be inserted (of type LinkedListNode<T>)
	 * @return void
	 */
	public void insertFirstNode (LinkedListNode<T> newNode ) {
		//set new node to point to the head
		newNode.setNext(head);
		//set head equal to the new node
		head = newNode;
	}
	/**
	 * Insert a new node with data after currentNode
	 * @param The current node (of type LinkedListNode<T>, and the data (of type T)
	 * @return void
	 **/
	public void insertAfter( LinkedListNode<T> currentNode, T data ) {
		//if somebody accidently called insert after with an empty list
		if (currentNode == null) {
			//correct their mistake and insert the data at the first spot
			insertFirst (data);
		}
		//otherwise
		else {
		//make a new node 
		LinkedListNode<T> newNode = new LinkedListNode<T>();
		///add data to this node
		newNode.setData(data);
		//set new node to point to the node after the current node
		newNode.setNext(currentNode.getNext());
		//set the current node to point to the new node
		currentNode.setNext(newNode);
		}
	}
	
	/**
	 * Insert a new node with data at the tail of the list.
	 * @param data to be inserted (of type T)
	 * @return void
	 **/
	public void insertLast( T data ) {
		//make a new node 
		LinkedListNode<T> lastNode = new LinkedListNode<T>();
		//if somebody accidently called insert after with an empty list

		///add data to this node
		lastNode.setData(data);	
		//invoke insertfirstnode to link up the new node
		insertAfter (getLastNode(), data);
		
	}
	 
	/**
	 * Remove head node
	 * @param none
	 * @return void
	 **/
	public void deleteFirst() {
		//if the head isn't null
		if (!head.equals(null)) {
			//have the head be whatever the next node is
			head = head.getNext();
		}
	}
	 
	/**
	 * Remove tail node
	 * @param none
	 * @return void
	 **/
	public void deleteLast() {
		//if the head is null
		if (head == null) {
			//just stop, leave the function
			return;
		}
		//if the node after the head is null
		if (head.getNext() == null) {
			//set the head to be null
			head = null;
			//just stop, leave the function
			return;
		}
		
		//Declare variables 
			//node goes through to get the last node
			//previous holds the value of the previous node
		LinkedListNode<T> node = head;
		LinkedListNode<T> previous = head;
		//while the node after the current node isn't null
		while (node.getNext() != null) {
			//set previous equal to node
			previous = node;
			//have node equal the node after the current node
			node = node.getNext();
		}
		//leave the while loop if the node after current node is null
		//set the previous node (before incrementing) to null
		previous.setNext(null);
	}
	 
	/**
	 * Remove node following currentNode
	 * If no node exists (i.e., currentNode is the tail), do nothing
	 * @param the current node (of type LinkedListNode<T>
	 * @return void
	 **/
	public void deleteNext( LinkedListNode<T> currentNode ) {
		//if the node after the current node is not null and if the node after the node after the current node isn't null
		if (!currentNode.getNext().equals( null) && !currentNode.getNext().getNext().equals(null)) {
			//have the current node point to the node after the node after the current node
			currentNode.setNext(currentNode.getNext().getNext());
		}
	}
	 
	/**
	 * Return the number of nodes in this list.
	 * @param none
	 * @return int
	 **/
	public int size() {
		//if the head isn't null
		if (head != null) {
			//create a node that's set to the head
			LinkedListNode<T> node = head;
			//set number of nodes to 0
			int numNodes = 0;

			//while the node isn't null
			while (node != null) {
				//increment the number of nodes
				numNodes++;
				//set node to the next node
				node = node.getNext();
			}
			//return the number of nodes
			return numNodes;
		}
		//otherwise, the head is null meaning the list is empty
		else {
			//size is 0 (empty)
			return 0;
		}
	}
	 
	/**
	 * Check if list is empty.
	 * @param none
	 * @return true if list contains no items.
	 **/
	public boolean isEmpty() {
		//if head is null
		if (head == null) {
			//return true, no items in list
			return true;
		}
		//otherwise
		return false;
	}
	 
	/**
	 * Return a String representation of the list.
	 * @param none
	 * @return String of the data
	 **/
	public String toString() {
//		if (isEmpty()) {
//			return null;
//		}
		//create a string that holds the value of the list
		//held inside curly braces for my viewing pleasure
		String s = "";
		//if the list is not empty
		if (isEmpty()!= true) {
			//create a node and set it to the head
			LinkedListNode<T> curNode = head;
				//while the current node isn't null
				while (curNode != null) {
					//add the data in the node to my string
					s += curNode.getData();
					//set current node to be the next node
					curNode = curNode.getNext();
				}
		}
		//return the final string
		return s;
	}
	
	/**
	 * Reset the list, make it empty
	 * @param data
	 * @return void
	 **/
	public void clearList ( T data ) {
		//set head to be null
		head = null;
	}
}