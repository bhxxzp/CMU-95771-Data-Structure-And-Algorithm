import java.math.BigInteger;

/*
 * @Author: Peng Zeng
 * @AndrewID: pengzeng
 */


public class RedBlackNode {
	public static final int BLACK = 0;
	public static final int RED = 1;
	private String key;
	private BigInteger value;
	private int color;
	private RedBlackNode p;
	private RedBlackNode lc;
	private RedBlackNode rc;

	public RedBlackNode(String key, BigInteger value, int color, RedBlackNode p, RedBlackNode lc, RedBlackNode rc) {
		this.key = key;
		this.value = value;
		this.color = color;
		this.p = p;
		this.lc = lc;
		this.rc = rc;
	}
	
	/*
	 * @return Value
	 */
	public BigInteger getValue() {
		return value;
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
	public String getKey() {
		return key;
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
	public void setKey(String key) {
		this.key = key;
	}
	
	/*
	 * Sets the Value of the RedBlackNode
	 */
	public void setValue(BigInteger value) {
		this.value = value;
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
		return "[Key = " + key +
				":Value = " + value +
				":Color = " + (color == RedBlackNode.RED ? "RED":"BLACK") +
				":Parent = " + p.getKey() +
				": LC = " + lc.getKey() + 
				": RC = " + rc.getKey() +
				"]";
				
	}
}
