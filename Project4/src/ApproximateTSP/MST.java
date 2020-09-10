package ApproximateTSP;

import java.util.HashMap;

public class MST {
	  private double[]  minWeigh;
	  private int[]  parent;
	  
	  public double[] getMiniWeigh() {
	        return minWeigh;
	    }
	  public int[] getParent() {
	        return parent;
	    }
	  
	  public MST(Graph graph, int startVertex){
		  double[][] cost = graph.getWeighArray();
		  minHeap heap = new minHeap(graph.getSize());
		  //System.out.println(graph.getSize());
		  minWeigh = new double[graph.getSize()];
		 for(int i = 0; i<graph.getSize(); i++) { 
			 minWeigh[i] = 100000000000000000000000000000000000000000.0;
		  }
		  
		  minWeigh[startVertex] = 0.0;
		  
		  int j = 0;
		  while(j<graph.getSize()) {
			  heap.insert(new Vertex(j, minWeigh[j]));
			  j++;
		  }
		  
		  parent = new int[graph.getSize()];
		  parent[startVertex] = -1;
		
		  
		  while(heap.getSize() != 0) {
			  int obj = heap.deleteMin();
			//  System.out.println("heap: " +  obj);
			  int index;
			  //System.out.println("" +  graph.findNeighbors(obj)+ "~~~");
			  for(int v = 0; v< graph.findNeighbors(obj).size(); v++){
				  	 index = graph.findNeighbors(obj).get(v);
	                if(heap.contains(v) && (cost[obj][v]<minWeigh[v])){
	                    minWeigh[v] = cost[obj][v];
	                    parent[v] = obj;
	                    heap.newState(v, minWeigh[v]);
	                }
	            }
			  
		  }
	  
		  
	  }
	  
	  
	
	
}