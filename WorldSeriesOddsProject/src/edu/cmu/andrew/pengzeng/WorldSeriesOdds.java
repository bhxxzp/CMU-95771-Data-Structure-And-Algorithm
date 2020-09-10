/*
 * @Author: Peng Zeng
 * @AndrewID: pengzeng
 */
package edu.cmu.andrew.pengzeng;

import java.util.*;

public class WorldSeriesOdds {
	// Result type is double
	private double[][] result;

	// The recursion function
	public double recursiveFunction(int i, int j) {
		if (i == 0 && j > 0) {
			return 1;
		}else if(i > 0 && j == 0){
			return 0;
		} else {
			return (recursiveFunction(i - 1, j) + recursiveFunction(i, j - 1)) / 2;
		}
	}
	
	// The Dynamic function
	public double TwoDFunction(int i, int j) {
		int size = (int)Math.max(i + 1, j + 1);
		if (result == null) {
			result = new double[size][size];
			for (int k = 0; k < size; k++) {
				result[0][k] = 1;
			}
			for (int l = 1; l < size; l++) {
				result[l][0] = 0;
			}
			for (int a = 1; a < size; a++) {
				for (int b = 1; b < size; b++) {
					result[a][b] = (result[a - 1][b] + result[a][b - 1]) / 2;
				}
			}
		}
		return (double)result[i][j];
	}

	public static void main(String[] args) {
		while(true) {
			System.out.println("Input two number to calculate!");
			Scanner in = new Scanner(System.in);
			String input = in.nextLine();
			String[] number = input.split(" ");
			System.out.println("Print in " + number[0] + " and " + number[1]);
			System.out.println("");
			int i = Integer.parseInt(number[0]);
			int j = Integer.parseInt(number[1]);
			WorldSeriesOdds game = new WorldSeriesOdds();
			
			// Function1:
			double start1 = System.currentTimeMillis();
			double end1 = System.currentTimeMillis();
			System.out.println("The Recursion");
			System.out.println("Result = " + game.recursiveFunction(i, j));
			System.out.println("Time = "+ (end1 - start1) * 0.001 + "s");
			System.out.println("");
			
			// 2D Function:
			double start2 = System.currentTimeMillis();
			double end2 = System.currentTimeMillis();
			System.out.println("The Dynamic Programming");
			System.out.println("Result = " + game.TwoDFunction(i, j));
			System.out.println("Time = "+ (end2 - start2) * 0.001 + "s");
			break;
		}
	}

}
