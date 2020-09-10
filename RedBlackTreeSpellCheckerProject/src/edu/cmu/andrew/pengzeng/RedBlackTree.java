/*
 * @Author: Peng Zeng
 * @AndrewID: pengzeng
 */
package edu.cmu.andrew.pengzeng;

/*
 *  Notes from CLR "Introduction To Algorithms"

 Red Black Trees
 A red-black tree is a binary search tree with an extra bit of storage per node.
 The extra bit represents the color of the node. It's either red or black.
 Each node contains the fields: color, key, left, right, and p. Any nil
 pointers are regarded as pointers to external nodes (leaves) and key bearing
 nodes are considered as internal nodes of the tree.

 Red-black tree properties:
 1. Every node is either red or black.
 2. The root is black.
 3. Every leaf (nil) is black.
 4. If a node is red then both of its children are black.
 5. For each node, all paths from the node to descendant leaves contain the
    same number of black nodes.

 From these properties, it can be shown (by an induction proof) that
 the tree has a height no more than 2 * Log(n + 1).

 In the implementation of the tree, we use a single node to represent all
 of the external nulls. Its color will always be black. The parent pointer (p)
 in the root will point to this node and so will all the internal nodes
 that would normally contain a left or right value of null. In other words,
 instead of containing a null pointer as a left child or a null pointer as a
 right child, these internal nodes will point to the one node that represents
 the external nulls.

 This constructor creates an empty RedBlackTree.
 It creates a RedBlackNode that represents null.
 It sets the internal variable tree to point to this
 node. This node that an empty tree points to will be 
 used as a sentinel node. That is, all pointers in the 
 tree that would normally contain the value null, will instead
 point to this sentinel node.
 */

public class RedBlackTree {

	
	private int size;
	private RedBlackNode root;
	private RedBlackNode nil;
	private int recentCompares = 0;
	
	/*
	 * Initialize the RedBlackTree
	 */
	public RedBlackTree() {
		nil = new RedBlackNode("-1", RedBlackNode.BLACK, null, null, null);
		root = nil;
	}
	
	/*
	 * Big Theta(1)
	 * @return number of values inserted into the tree
	 */
	public int getSize() {
		return size;
	}
	
	/*
	 * Perform an inorder traversal of the tree. 
	 * The inOrderTraversal(RedBlackNode) method is recursive 
	 * and displays the content of the tree. It makes calls on 
	 * System.out.println(). This method would normally be private.
	 * 
	 * Big Omega(n)
	 */
	public void inOrderTraversal(RedBlackNode t) {
		if (t == nil) {
			return;
		}
		inOrderTraversal(t.getLc());
		System.out.println(t.toString());
		inOrderTraversal(t.getRc());
	}
	
	/*
	 * The no argument inOrderTraversal() method calls 
	 * the recursive inOrderTraversal(RedBlackNode) - passing the root.
	 * 
	 * Big Omega(n)
	 */
	public void inOrderTraversal() {
		inOrderTraversal(root);
	}
	
	/*
	 * Perform a reverseOrder traversal of the tree. 
	 * The reverseOrderTraversal(RedBlackNode) method 
	 * is recursive and displays the content of the tree in reverse order. 
	 * This method would normally be private.
	 * 
	 * Big Omega(n)
	 */
	public void reverseOrderTraversal(RedBlackNode t) {
		if (t == nil) {
			return;
		}
		reverseOrderTraversal(t.getRc());
		System.out.println(t.toString());
		reverseOrderTraversal(t.getLc());
	}
	
	/*
	 * The no argument reverseOrderTraversal() method calls 
	 * the recursive reverseOrderTraversal(RedBlackNode) - passing the root.
	 * 
	 * Big Omega(n)
	 */
	public void reverseOrderTraversal() {
		inOrderTraversal(root);
	}
	
	/*
	 * Places a data item into the tree.
	 * 
	 * Best case: Omega(1) for only the first one
	 * Worst case: Omega(log(n))
	 */
	public void insert(String value) {
		RedBlackNode y = nil;
		RedBlackNode x = root;
		while(x != nil) {
			y = x;
			if(value.compareTo(x.getData()) < 0) {
				x = x.getLc();
			} else {
				x = x.getRc();
			}
		}
		RedBlackNode z = new RedBlackNode(value, RedBlackNode.RED, y, nil, nil);
		if (y == nil) {
			z.setColor(RedBlackNode.BLACK);
			root = z;
		}else {
			if(value.compareTo(y.getData()) < 0) {
				y.setLc(z);
			}else {
				y.setRc(z);
			}
		}
		size++;
		RBInsertFixup(z);
	}
	
	/*
	 * Performs a single left rotation
	 * Precondition: x.getRc() != nil and root.getP != nil
	 * 
	 * Big Omega(1)
	 */
	public void leftRotate(RedBlackNode x) {
		RedBlackNode y = x.getRc();
		x.setRc(y.getLc());
		y.getLc().setP(x);
		y.setP(x.getP());
		
		if (x.getP() == nil) {
			root = y;
		} else {
			if (x == x.getP().getLc()) {
				x.getP().setLc(y);
			} else {
				x.getP().setRc(y);
			}
		}
		y.setLc(x);
		x.setP(y);
	}
	
	/*
	 * Performs a single Right rotation
	 * Precondition: x.getLc() != nil and root.getP != nil
	 * 
	 * Big Omega(1)
	 */
	public void rightRotate(RedBlackNode x) {
		RedBlackNode y = x.getLc();
		x.setLc(y.getRc());
		y.getRc().setP(x);
		y.setP(x.getP());
		
		if (x.getP() == nil) {
			root = y;
		} else {
			if (x == x.getP().getRc()) {
				x.getP().setRc(y);
			} else {
				x.getP().setLc(y);
			}
		}
		y.setRc(x);
		x.setP(y);
	}
	
	/*
	 * Fixing up the tree so that Red Black Properties are preserved.
	 * Precondition: z != null
	 * Best case: Omega(1)
	 * Worst case: Big Omega(log(n))
	 */
	public void RBInsertFixup(RedBlackNode z) {
		while (z.getP().getColor() == RedBlackNode.RED) {
			if (z.getP() == z.getP().getP().getLc()) {
				RedBlackNode y = z.getP().getP().getRc();
				if (y.getColor() == RedBlackNode.RED) {
					z.getP().setColor(RedBlackNode.BLACK);
					y.setColor(RedBlackNode.BLACK);
					z.getP().getP().setColor(RedBlackNode.RED);
					z = z.getP().getP();
				} else {
					if (z == z.getP().getRc()) {
						z = z.getP();
						leftRotate(z);
					}
					z.getP().setColor(RedBlackNode.BLACK);
					z.getP().getP().setColor(RedBlackNode.RED);
					rightRotate(z.getP().getP());
				}
			} else {
				RedBlackNode y = z.getP().getP().getLc();
				if (y.getColor() == RedBlackNode.RED) {
					z.getP().setColor(RedBlackNode.BLACK);
					y.setColor(RedBlackNode.BLACK);
					z.getP().getP().setColor(RedBlackNode.RED);
					z = z.getP().getP();
				} else {
					if (z == z.getP().getLc()) {
						z = z.getP();
						rightRotate(z);
					}
					z.getP().setColor(RedBlackNode.BLACK);
					z.getP().getP().setColor(RedBlackNode.RED);
					leftRotate(z.getP().getP());
				}
			}
			
		}
		root.setColor(RedBlackNode.BLACK);
	}
	
	/*
	 * @return true if the String v is in the RedBlackTree and false otherwise. 
	 * It counts each comparison it makes in the variable recentCompares.
	 * 
	 * Best case: Omega(1)
	 * Worst case: Big Omega(log(n))
	 */
	public boolean contains(String v) {
		this.recentCompares = 0;
		RedBlackNode item = root;
		while(item != nil) {
			this.recentCompares++;
			if (v.compareTo(item.getData()) == 0) {
				return true;
			} else if (v.compareTo(item.getData()) < 0){
				item = item.getLc();
			} else {
				item = item.getRc();
			}
		}
		return false;
	}
	
	/*
	 * @return number of comparisons made in last call on the contains method.
	 * Big Omega(1)
	 */
	public int getRecentCompares() {
		return this.recentCompares;
	}
	
	/*
	 * @return a value close to v in the tree. If v is found in the tree it returns v.
	 * 
	 * Best case: Big Omega(log(n))
	 * Worst case: Big Omega(log(n))
	 */
	public String closeBy(String v){
		RedBlackNode result = root;
		RedBlackNode temp = root;
		int lastCompare = temp.getData().compareTo(v);
		while(temp != nil) {
			if (lastCompare == 0) {
				return temp.getData();
			}else if(temp.getData().compareTo(v) < 0) {
				if (lastCompare > 0) {
					return temp.getData();
				}else {
					result = temp;
					temp = temp.getRc();
					lastCompare = temp.getData().compareTo(v);
				}
			}else {
				if (lastCompare < 0) {
					return temp.getData();
				}else {
					result = temp;
					temp = temp.getLc();
					lastCompare = temp.getData().compareTo(v);
				}
			}
		}
		return result.getData();
	}
	
	/*
	 * This a recursive routine that is used to compute 
	 * the height of the red black tree. It is called by 
	 * the height() method. The height() method passes 
	 * the root of the tree to this method.
	 * 
	 * Big Omega(log(n))
	 */
	public int height(RedBlackNode t) {
        if(t == nil){
            return -1;
        }
        int leftHeight = height(t.getLc());
        int rightHeight = height(t.getRc());
        //return Math.abs(leftHeight - rightHeight);
        if(leftHeight > rightHeight){
            return leftHeight + 1;
        } else {
            return rightHeight + 1;
        }
    }
	
	public boolean isBalanced() {
		return isBalanced(root);
	}
	
	public boolean isBalanced(RedBlackNode t) {
		if(t == nil) {
			return true;
		}
		int left = height(t.getLc());
		int right = height(t.getRc());
		int dif = Math.abs(left - right);
		if(dif > 1) {
			return false;
		}
		return isBalanced(t.getLc()) && isBalanced(t.getRc());
	}
	
	
	
	/*
	 * Precondition: root != nil
	 * Postcondition: returns height of tree, or -1 if root is nil
	 * 
	 * @return the height of the red black tree.
	 */
	public int height() {
		return height(root);
	}
	
	public int Diameter() {
		return getDiameter(root);
	}
	
	/*
	 * @return the Diameter of the Tree
	 * Big Omega(log(n))
	 */
    public int getDiameter(RedBlackNode t){
        if(t == nil) {
        	return 0;
        }
        int leftheight = height(t.getLc());
        int rightheight = height(t.getRc());

        int leftdiameter = getDiameter(t.getLc());
        int rightdiameter = getDiameter(t.getRc());

        return Math.max(leftheight + rightheight + 1, Math.max(leftdiameter, rightdiameter));
    }
	
	/*
	 * This method displays the RedBlackTree in level order.
	 * It first displays the root. Then it displays all children of the root.
	 * Then it displays all nodes on level 3 and so on.
	 * It is not recursive. It uses a queue.
	 * 
	 * Big Omega(log(n))
	 */
	public void levelOrderTraversal() {
		Queue result = new Queue();
		result.enQueue(root);
		while(result.isEmpty() == false) {
			RedBlackNode q = (RedBlackNode) result.deQueue();
			System.out.println(q);
			if (q.getLc() != nil) {
				result.enQueue(q.getLc());
			}
			
			if (q.getRc() != nil) {
				result.enQueue(q.getRc());
			}
		}
	}
	
	  public static void main(String[] args) {

	        RedBlackTree rbt = new RedBlackTree();

	        for(int j = 1; j <= 5; j++) rbt.insert(""+j);
	        
	        // after 1..5 are inserted
	        System.out.println("RBT in order");
	        rbt.inOrderTraversal();
	        System.out.println("RBT level order");
	        rbt.levelOrderTraversal();
	        
	       // is 3 in the tree
	        
	        if(rbt.contains(""+3)) System.out.println("Found 3");
	        else System.out.println("No 3 found"); 
	        
	        // display the height
	        System.out.println("The height is " + rbt.height());   
	        System.out.println("Is it Balanced? " + rbt.isBalanced());
	        
	    }

}
