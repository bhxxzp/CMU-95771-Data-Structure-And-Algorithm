package ApproximateTSP;

import java.util.LinkedList;

public class hamiltonianCycle {
	private double[][] distance;
	private int[] parent;
	private int root;
	private LinkedList tree;
	private boolean a = true;
	
	public hamiltonianCycle(Graph graph) {
		tree = new LinkedList();
		root = 0;
		distance = graph.getWeighArray();
		MST minSpan = new MST(graph, root);
		parent = minSpan.getParent();
		makePreOrderTree();
		
		
	}
	 
    public LinkedList getTree() {
    		tree.add(root);
        return tree;
    }
	
    private void makePreOrderTree() {
        LinkedList[] order = makingList();
        int start = root;
        tree.add(start);
        
       for(int i = 0; i < order.length; i++) {
       }
     
        while (a) {
            if (start == root && order[start].isEmpty()) {
                break;
            }
            int next ;
            if (order[start].peekFirst() != null) {
                next = (Integer) order[start].pollFirst();
                tree.add(next);
                //System.out.println(next);
                start = next;
            } else {
                start = parent[start];
            }
        }  
    }
	
	
	
	private LinkedList[] makingList() {
        LinkedList[] order = new LinkedList[parent.length];
        //initialize the linked list in the order array
        for (int j = 0; j < order.length; j++) {
            order[j] = new LinkedList();
        }
        for (int index = 0; index < parent.length; index++) {
            for (int i = 0; i < parent.length; i++) {
                if (parent[i] == index) {
                    order[index].add(i);
                }
            }

        }
        return order;
    }
	
	 public double getCycleLength() {
	        double result = 0.0;
	        int first = 0;
	        int next = 0;
	        for (int i = 0; i < tree.size() - 1; i++) {
	            first = (Integer) tree.get(i);
	            next = (Integer) tree.get(i + 1);
	            result += distance[first][next];
	        }
	        result += distance[next][root];
	        return result * 0.00018939;
	    }
	 
	  
	   
}