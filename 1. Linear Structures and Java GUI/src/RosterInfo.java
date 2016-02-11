/**
 * This class holds the backend information on how the rosters are: Sorted Held
 * in memory
 * 
 * @author patel22y
 */
public class RosterInfo {

	// The registered class list
	protected DoublyLinkedList<String> enrolled = new DoublyLinkedList<String>();;
	// The waitlisted class list
	protected DoublyLinkedList<String> waitlisted = new DoublyLinkedList<String>();
	// variable that holds the amount of students allowed to enroll in the class
	protected int numAllowed = 5;

	/**
	 * CONSTRUCTOR
	 */
	public RosterInfo() {
	}

	/**
	 * Gets the enrolled students for the class
	 */
	public String getEnrolled() {
		return enrolled.toString();
	}
	
	/**
	 * Gets the waitlisted students for the class
	 */
	public String getWaitlisted() {
		return waitlisted.toString();
	}
	
	/**
	 * Alphabetizes the roster: sort the list alphabetically & insert the name
	 * after the appropriate node
	 */
	public void sortRoster(DoublyLinkedList<String> list, String name) {
		// Get the first node of the list
		DoublyLinkedListNode<String> currentNode = list.getFirstNode();
		// boolean shows if the list is sorted
		boolean sorted = false;

		// if the name needs to be at the start of the list:
		if (name.compareToIgnoreCase(list.getFirst()) < 0) {
			// create a new node
			DoublyLinkedListNode<String> first = new DoublyLinkedListNode<String>();
			// set the data of the node to be the name
			first.setData(name);
			// insert the new node at the first node position
			list.insertFirstNode(first);
			// set sorted to true
			sorted = true;
			return;
		}
		// if the name is at the end of the list
		else if (name.compareToIgnoreCase(list.getLast()) > 0) {
			// insert the new data at the last node position
			list.insertLast(name);
			// set sorted to true
			sorted = true;
			return;
		}
		// otherwise, it's somewhere in the middle...
		else {
			// while it's not sorted
			while (!sorted) {
				// if the name goes after the current node
				if (name.compareToIgnoreCase(currentNode.getData()) > 0) {
					// if the name goes before the next node
					if (name.compareToIgnoreCase(currentNode.getNext()
							.getData()) < 0) {
						// SORTED!!! Insert at the position after the current
						// node
						list.insertAfter(currentNode, name);
						// it is sorted
						sorted = true;
						// stop the while loop
						break;
					}
					// update current node to be the next node
					currentNode = (DoublyLinkedListNode<String>) currentNode.getNext();
				}
			}
		}
	}

	/**
	 * Update either the enrolled or waitlisted doubly linked list behavior =
	 * "add" or "remove"
	 * 
	 * @param String
	 *            student's name
	 * @param String
	 *            behavior type
	 */
	public void updateStudent(String name, String behavior) {
		if (behavior.equals("add")) {
			addStudent(name);
		} 
		else if (behavior.equals("remove")) {
			removeStudent(name);
		}
	}

	/**
	 * add a student to either the registered or waitlisted doubly linked lists
	 * 
	 * @param name
	 */
	public void addStudent(String name) {
		// If there are less students than allowed, allow the student into the
		// class
		if (enrolled.size() < numAllowed) {
			// if there are no students enrolled
			if (enrolled.getFirstNode() == null) {
				// insert the name in the first slot
				enrolled.insertFirst(name);
			}
			// otherwise
			else {
				// sort the list alphabetically & insert the name after the
				// appropriate node
				sortRoster(enrolled, name);
			}
		}
		// otherwise waitlist the student
		else {
			// insert the student at the last spot
			// Does NOT need to be sorted!!!!
			waitlisted.insertLast(name);
		}
	}

	/**
	 * removes the student from either list
	 * 
	 * @param name
	 */
	public void removeStudent(String name) {
		// current node variable
		DoublyLinkedListNode<String> currentNode = enrolled.getFirstNode();
		// if the name exists = true
		boolean exists = false;
		// if the name exists = true
		boolean isPresent = false;

		if (enrolled.isEmpty() == false && enrolled.getFirst().equals(name)) {
			enrolled.deleteFirst();
			return;
		}
		
		if (waitlisted.isEmpty() == false && waitlisted.getFirst().equals(name)) {
			waitlisted.deleteFirst();
			return;
		}

		// check if the name is in the enrolled list
		while (!exists) {
			if (currentNode.getData().equals(name)) 
			{
				if (currentNode == enrolled.getLastNode()) {
					enrolled.deleteLast();
				} 
				else {
					// if so, remove the name by setting the next pointer from
					// the previous node to the node after the current node
					enrolled.deletePrev((DoublyLinkedListNode<String>) currentNode.getNext());
				}
				// check the size of the list. If it is less than the allowed
				// amount, take a student from the waitlisted list
				while (enrolled.size() < numAllowed && waitlisted.isEmpty() == false) {
					System.out.println("IN THE WHILE");
						addStudent(waitlisted.getFirstNode().getData());
						// remove the student (firstnode of waitlisted)
						waitlisted.deleteFirst();
				}
				exists = true;
				break;
			}

			// if the current node is the last in the list
			else if (currentNode.getNext() == null) 
			{
				// break from the while loop
				break;
			}
		
				currentNode = (DoublyLinkedListNode<String>) currentNode.getNext();
		}

		// update current node to be the waitlisted first node
		currentNode = waitlisted.getFirstNode();

		// check if the waitlist is not empty.
		if (waitlisted.getFirstNode() != null) {
			// check if the name is in the waitlisted list
			while (!isPresent) {		
				if (currentNode.getData().equals(name)) {
					if (currentNode == waitlisted.getLastNode()) {
						waitlisted.deleteLast();
						isPresent=true;
					}
					else {
						// if so, remove the name by setting the next pointer from
						// the previous node to the node after the current node
						waitlisted.deletePrev((DoublyLinkedListNode<String>) currentNode
										.getNext());
						isPresent = true;
						break;
					}
				}
				// if the current node is the last in the list
				if (currentNode.getNext() == null) {
					// break from the while loop
					break;
				}
				currentNode = (DoublyLinkedListNode<String>) currentNode
						.getNext();
			}
		}
	}
}
