package edu.cmu.andrew.pengzeng;

/*
 * pengzeng but adapted from Michael Main's class.
 * 
 * SinglyLinkedList has a head and tail pointer.
 * 
 * Head and tail are both ObjectNodes.
 * 
 * Class invariants:
 * 
 * The head pointer is null or points to the first element on the list. 
 * The tail pointer is null or points to the last node on the list. 
 * An integer countNodes is maintained to keep count of the number of nodes added to the list. 
 * This provided an O(1) count to the caller.
 * 
 * This class provides iterator methods:
 * 
 * reset: to reset the iteration - starting from the head of the list.
 * 
 * next: returns the element pointed to by the iterator and increments the iterator to the next node
 * 
 * hasNext: returns true if the iterator points to an existing node and false otherwise.
 */

public class SinglyLinkedList {
	/*
	 * The head is null or the first node;
	 * 
	 * The tail is null or the last node;
	 * 
	 * countNodes is the number of the nodes;
	 * 
	 * current is what the point position now.
	 */
	
	private ObjectNode head;
	private ObjectNode tail;
	private int countNodes = 0;
	private ObjectNode current;

	public SinglyLinkedList() {
		
	}
	
	/*
	 * @preconditions
	 * the type input is ObjectNode
	 * @[pstconditions
	 * return a list
	 * Big theta = 1
	 */
	public void addAtEndNode(Object c) {
		// If End is null, create newNode.
		// Otherwise, combine newNode to the last one.
		ObjectNode newNode = new ObjectNode(c, null);
		if (head == null) {
			head = newNode;
			tail = head;
		} else {
			tail.setLink(newNode);
			tail = tail.getLink();
		}
		
		current = head;
		countNodes++;
	}
	
	/*
	 * @preconditions
	 * the type input is ObjectNode
	 * @[pstconditions
	 * return a list
	 * Big theta = 1
	 */
	
	public void addAtFrontNode(Object c) {
		// If head is null, create newNode
		// Otherwise, combine newNode as the head 
		ObjectNode newNode = new ObjectNode(c, null);
		if (head == null) {
			head = newNode;
			tail = newNode;
		} else {
			ObjectNode temp = head;
			head = newNode;
			head.setLink(temp);
		}
		
		current = head;
		countNodes++;
	}
	
	/*
	 * @preconditions
	 * the type input nothing
	 * @[pstconditions
	 * return an int
	 * Big theta = 1
	 */
	
	public int countNodes() {
		// Return the number of nodes
		if (head == null) {
			return 0;
		} else {
			return countNodes;
		}
	}
	
	public Object getLast() {
		// Get the last node data
		return tail.getData();
	}
	
	/*
	 * @preconditions
	 * the type input is int
	 * @[pstconditions
	 * return a Data
	 * Big theta = n
	 */
	
	public Object getObjectAt(int i) {
		// get the nth node data
		if (i < 0) {
			throw new IllegalArgumentException("i should be greater than 0");
		} else if (i == 0) {
			return head.getData();
		} else {
			// Look through each node until the destination
			ObjectNode temp = head;
			for (int index = 0; index < i; index++) {
				temp = temp.getLink();
			}
			current = temp;
			return temp.getData();
		}
	}
	
	/*
	 * @preconditions
	 * the type input is nothing
	 * @[pstconditions
	 * return a boolean
	 * Big theta = 1
	 */
	
	public boolean hasNext() {
		// if there is a next one? 
		if (current.getLink() == null) {
			return false;
		} else {
			return true;
		}
	}
	
	public Object next() {
		// Return the next node data
		ObjectNode nextNode = current.getLink();
		if (nextNode == null) {
			throw new IllegalArgumentException("Nothing");
		} else {
			current = nextNode;
			return nextNode.getData();
		}
	}
	
	public void reset() {
		// Move the point to head
		current = head;
	}
	
	
	/*
	 * @preconditions
	 * the type input is ObjectNode
	 * @[pstconditions
	 * return a list
	 * Big theta = n
	 */
	public String toString() {
		// Print out each node as String
        String result = "";
        for (int i = 0; i < countNodes; i++) {
            result += this.getObjectAt(i);
        }
        return result;
    }
	
	
	
	public static void main(String[] args) {
		SinglyLinkedList test = new SinglyLinkedList();
		
		for (int i = 0; i < 26; i++) {
			test.addAtEndNode((char)('A' + i));
		}
		
		for (int i = 0; i < 26; i++) {
			test.addAtFrontNode((char)('a' + i));
		}
		
		System.out.println("The node :" + test.toString());
		System.out.println("The total number = " + test.countNodes());
		System.out.println("The last one = " + test.getLast());
		test.reset();
		System.out.println("Do we have the next one?  " + test.hasNext());
		System.out.println("The next one = " + test.next());
		System.out.println("The 35th letter = " + test.getObjectAt(35));
		System.out.println("The next one = " + test.next());
		System.out.println();
		System.out.println("Now we start to print out all the letter iteratively");
		test.reset();
		while(test.hasNext()) {
			System.out.println(test.next());
			}
		
	}

}
