package ApproximateTSP;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class FileParser {
	public static int i;
	public static double x;
	public static double y;
	public static int time;
	public static String street;
	public static String offense;
	public static String date;
	public static int tract;
	public static double latitude;
	public static double longtitude;
	static boolean sflag = false;
	static boolean eflag = false;
	static int size;
	
	public double getY() {
		return y;
	}
	
	public double getX() {
		return x;
	}
	
	public static Graph parserFile( ) throws FileNotFoundException {

		Scanner keyboard = new Scanner(System.in);
		System.out.println("Enter start date:");
	    String startDate = keyboard.nextLine();
	    System.out.println("Enter end date:");
	    String endDate = keyboard.nextLine();
	    System.out.println("");
	    System.out.println("Crime records between "+startDate+" and "+endDate);
	    File crimeRecordsFile = new File("CrimeLatLonXY1990.csv");
	    Scanner input = new Scanner(crimeRecordsFile);
	    String Nuse = input.nextLine();
	    
	    ArrayList<Double> xlist = new ArrayList<Double>();
	    ArrayList<Double> ylist = new ArrayList<Double>();
	    	do{
	    		String line = input.nextLine();
	    		String[] part = line.split(",");
	    	   	x = Double.valueOf(part[0]);
	    	    y = Double.valueOf(part[1]);
	    	    time = Integer.valueOf(part[2]);
	    	    street = part[3];
	    	    offense = part[4];
	    	    date = part[5];
	    	    tract = Integer.valueOf(part[6]);
	    	    latitude = Double.valueOf(part[7]);
	    	    longtitude = Double.valueOf(part[8]);
	    	  
	    	    if(date.equals(startDate)&& !startDate.equals(endDate)) {
	    	    		sflag = true;
	    	    }
	    	    else if(date.equals(endDate)) {
	    	    		sflag = false;
	    	    		eflag = true;
	    	    		
	    	    }
	    	    if(sflag || eflag) {
	    	    	   // System.out.print(date+" ");
	    	    		System.out.println(line);
	    	    		xlist.add(x);
	    	    		ylist.add(y);
	    	    		size++;
	    	    		//System.out.print(size);
	    	    		
	    	    }
	    	    eflag = false;
	    	    
	    }while( input.hasNextLine());
	    	
	    	//build graph
	    	Graph tree = new Graph(size);
	    	double x1, x2, y1, y2;
	    	for (int i = 0; i < size; i++) {
					x1 = xlist.get(i);
					y1 = ylist.get(i);
				for (int j = 0; j < size; j++) {
							x2 = xlist.get(j);
							y2 = ylist.get(j);
					double distance = Math.sqrt(Math.pow( x1- x2, 2) + Math.pow(y1 - y2, 2)); 
				    tree.addEdge(i, j, distance);

				}
			}

	    	return tree;
	}
	

	
}