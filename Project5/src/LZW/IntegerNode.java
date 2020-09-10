package LZW;

public class IntegerNode {
	private Integer data;
	private String key;
	private IntegerNode nextNode;
	
	// Initialize the IntegerNode
	public IntegerNode() {
		data = 0;
		key = null;
		nextNode = null;
	}
	
	// Constructor
	public IntegerNode(Integer data, String key, IntegerNode next) {
		this.data = data;
		this.key = key;
		this.nextNode = next;
	}
	
	// Set the node data
	public void setData(Integer data) {
		this.data = data;
		return;
	}
	
	
	// Set the node key
	public void setKey(String key) {
		this.key = key;
		return;
	}
	
	// Set the next node of node
	public void setNextNode(IntegerNode next) {
		this.nextNode = next;
		return;
	}
	
	// Get the node data
	public Integer getData() {
		return this.data;
	}
	
	// Get the node key
	public String getKey() {
		return this.key;
	}
	
	// Get the next node of node
	public IntegerNode getNextNode() {
		return (this.nextNode == null) ? null : this.nextNode;
	}

	@Override
	public String toString() {
		return "IntegerNode [data=" + data + "]" + "[key=" + key + "]";
	}
}
