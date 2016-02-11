/**
 * This class is a generic linked list node class
 * @author patel22y
 *
 * @param <T>
 */
public class DoublyLinkedListNode<T> extends LinkedListNode<T> {
	//pointer that each node has that points to the previous node
	//what makes the Doubly Linked List unique
	private DoublyLinkedListNode<T> previous;
	
	/**
	 * Get (pointer to) previous node.
	 * @param none
	 * @return LinkedListNode<T>
	 **/
	public DoublyLinkedListNode<T> getPrevious() {
		//return what the previous pointer is pointing to
		return previous;
		
	}
	 
	/**
	 * Set the previous pointer to passed node.
	 * @param previous node
	 * @return void
	 **/
	public void setPrevious( DoublyLinkedListNode<T> prevNode ) {
		//set whatever the current node's previous pointer to be the previous node
		this.previous = prevNode;
	}
	
}
