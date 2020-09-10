package LZW;

public class SinglyLinkedList {
	private IntegerNode head;
	private IntegerNode tail;
	public int size;
	
	// Initialize the linked list
	public SinglyLinkedList() {
		head = null;
		tail = null;
		size = 0;
	}
	
	// Check if linked list empty
	public boolean isEmpty() {
		return size == 0;
	}
	
	// Add the node at the end of the linked list
	public void addNodeEnd(Integer data, String key) {
		IntegerNode temp = new IntegerNode(data, key, null);
		if(head == null) {
			head = temp;
			tail = temp;
			size++;
			return;
		}else {
			tail.setNextNode(temp);
			tail = temp;
			size++;
			return;
		}
	}
	
	// Add the node to the head
	public void addNodeStart(Integer data, String key) {
		IntegerNode temp = new IntegerNode(data, key, null);
		if(head == null) {
			head = temp;
			tail = temp;
			size++;
			return;
		}else {
			temp.setNextNode(head);
			head = temp;
			size++;
			return;
		}
	}
	
	// Return the size of the linked list
	public int getSize() {
		return size;
	}
	
	// Delete the node with the specific data
	public boolean deleteNode(Integer data) {
		if (this.head == null) {
			return false;
		}
		IntegerNode temp = this.head;
		IntegerNode prev = null;
		while(temp != null) {
			if(temp.getData().equals(data)) {
				// If it is the head
				if(prev == null) {
					head = temp.getNextNode();
				}else if(temp.getNextNode() == null) { // If it is the tail
					prev.setNextNode(null);
					tail = prev;
				}
				
				else { // Not head
					prev.setNextNode(temp.getNextNode());
				}
				size--;
				return true;
			}else {
				prev = temp;
				temp = temp.getNextNode();
			}
		}
		return false;
	}
	
	// Get the specific node
	public IntegerNode get(int position) {
		if(head == null) {
			return null;
		}
		IntegerNode temp = head;
		for(int i = 0; i < position; i++) {
			temp = temp.getNextNode();
//			if(temp == null) {
//				return null;
//			}
		}
		return temp;
	}
	
	
	@Override
	public String toString() {
		return "SinglyLinkedList [head=" + head + ", tail=" + tail + ", size=" + size + "]";
	}

	// Testing function
	public static void main(String[] args) {
		SinglyLinkedList sol = new SinglyLinkedList();
		System.out.println("Is it empty? " + sol.isEmpty());
		System.out.println("Add something");
		sol.addNodeStart(1, "sdf");
		sol.addNodeStart(0, "asd");
		sol.addNodeEnd(2, "dfg");
		System.out.println(sol);
		System.out.println("Is it empty? " + sol.isEmpty());
		System.out.println("Size is " + sol.getSize());
		System.out.println("Position 2 is " + sol.get(2));
		System.out.println("Delete 2");
		System.out.println(sol.deleteNode(2));
		System.out.println("Size is " + sol.getSize());
		System.out.println(sol);
	}

	
	
}
