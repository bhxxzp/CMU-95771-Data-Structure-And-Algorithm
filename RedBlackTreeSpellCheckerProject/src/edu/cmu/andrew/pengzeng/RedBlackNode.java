/*
 * @Author: Peng Zeng
 * @AndrewID: pengzeng
 */
package edu.cmu.andrew.pengzeng;

public class RedBlackNode {
	public static final int BLACK = 0;
	public static final int RED = 1;
	private String data;
	private int color;
	private RedBlackNode p;
	private RedBlackNode lc;
	private RedBlackNode rc;

	public RedBlackNode(String data, int color, RedBlackNode p, RedBlackNode lc, RedBlackNode rc) {
		this.data = data;
		this.color = color;
		this.p = p;
		this.lc = lc;
		this.rc = rc;
	}
	
	/*
	 * @return Red or Black
	 */
	public int getColor() {
		return color;
	}
	
	/*
	 * @return Data in the node
	 */
	public String getData() {
		return data;
	}
	
	/*
	 *  @return Left child of node
	 */
	public RedBlackNode getLc() {
		return lc;
	}
	
	/*
	 *  @return Parent of node
	 */
	public RedBlackNode getP() {
		return p;
	}
	
	/*
	 *  @return Right child of node
	 */
	public RedBlackNode getRc() {
		return rc;
	}
	
	/*
	 * Sets the color of the RedBlackNode
	 */
	public void setColor(int color) {
		this.color = color;
	}
	
	/*
	 * Sets the Data or key of the RedBlackNode
	 */
	public void setData(java.lang.String data) {
		this.data = data;
	}
	
	/*
	 * Sets the Left child of the RedBlackNode
	 */
	public void setLc(RedBlackNode lc) {
		this.lc = lc;
	}
	
	/*
	 * Sets the Parent of the RedBlackNode
	 */
	public void setP(RedBlackNode p) {
		this.p = p;
	}
	
	/*
	 * Sets the Right child of the RedBlackNode
	 */
	public void setRc(RedBlackNode rc) {
		this.rc = rc;
	}

	public String toString() {
		return "[data = " + data +
				":Color = " + (color == RedBlackNode.RED ? "RED":"BLACK") +
				":Parent = " + p.getData() +
				": LC = " + lc.getData() + 
				": RC = " + rc.getData() +
				"]";
				
	}
}
