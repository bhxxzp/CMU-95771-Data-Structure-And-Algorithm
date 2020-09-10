package ApproximateTSP;

import java.io.FileNotFoundException;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

public class mainTSP {
	public static void main(String[] args) throws FileNotFoundException {
		Graph tree = FileParser.parserFile();
		
	        System.out.println();
	        
	        double[][] cost = tree.getWeighArray();

	        hamiltonianCycle treeCycle = new hamiltonianCycle(tree);
	        System.out.println("Hamiltonan Cycle (not necessarily optimum):");
	        LinkedList result = treeCycle.getTree();
	        for (int i = 0; i < result.size(); i++) {
	            System.out.print(result.get(i) + " ");
	        }
	        System.out.println("");
	        System.out.print("Length of Cycle:");
	        System.out.println(treeCycle.getCycleLength() + " miles");
	}

}