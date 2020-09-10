package ApproximateTSP;

import java.util.ArrayList;

public class Graph {
	
	     public double [][] weigh;
	     public int V; 
	     public int E; 
	     public String crimeRecord[];
	     
	     public Graph(int V) {
	    	 weigh = new double [V][V];
	    	 crimeRecord = new String[V];
	    	 this.E = 0;
	    	 this.V = V;
	    
	     }
	     public ArrayList<Integer> findNeighbors(int target) {
	    	 ArrayList<Integer> neighbors = new ArrayList<Integer>(); 
	    
	    	 	 for (int i = 0; i < V; i++) {
	    	            if (weigh[target][i] != 0 &&  !neighbors.contains(i)) {
	    	                neighbors.add(i);
	    	            }
	    	        }

			return neighbors; 	
	     }
	     
	     public void addEdge(int a, int b, double distance) {
	    	 	weigh[a][b] = distance;
	    	 	weigh[b][a] = distance;
	    	 	E++;
	     }
	     
	     public void deleteEdge(int a, int b) {
	    	 	weigh[a][b] = 0;
	    	 	weigh[b][a] = 0;
	     }
	     
	     public int getSize() {
	    	 	return V;
	     }
	     
	     public void addCrimeRecord(int v, String num) {
	    	 	 crimeRecord[v] = num;
	     }
	     
	     public double getWeigh(int a, int b) {
	    	 	return weigh[a][b];
	     }
	     
	     public double[][] getWeighArray() {
	    	 	return weigh;
	     }
	
	
}