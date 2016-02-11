/**
 * This class is a generic linked list node class
 * @author patel22y
 *
 * @param <T>
 */
public class LinkedListNode<T> {
	//holds the data stored in the node
	protected T data;
	//pointer to the next node in the list
	protected LinkedListNode<T> next;
	
	/**
	 * Get the data stored at this node.
	 * @param none
	 * @return T
	 **/
	public T getData() {
		//return the data
		return data;
		
	}
	 
	/**
	 * Set the data stored at this node.
	 * @param data
	 * @return void
	 **/
	public void setData( T data ) {
		this.data = data;
	}
	 
	/**
	 * Get (pointer to) next node.
	 * @param none
	 * @return LinkedListNode<T>
	 **/
	public LinkedListNode<T> getNext() {
		return next;
		
	}
	 
	/**
	 * Set the next pointer to passed node.
	 * @param node
	 * @return void
	 **/
	public void setNext( LinkedListNode<T> node ) {
		next = node;
	}
	 
	/**
	 * Returns a String representation of this node.
	 * @param none
	 * @return String representation of the data
	 **/
	public String toString() {
		return data.toString();
		
	}
}