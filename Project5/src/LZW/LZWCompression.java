package LZW;

import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

// Use the Packages BinaryIn and BinaryOut to
// realize the read and write the input and output file 

public class LZWCompression {
	private int CompressCount;
	private int DeompressCount;
	private int array_size = 127;
	private int chunksize = (int) Math.pow(2, 12);
	private int BinarySize = 256;
	private int W = 12;
	private int inputComfilesize = 0; // Count the input file size
	private int outputComfilesize = 0; // Count the input file size
	private int inputDeComfilesize = 0; // Count the input file size
	private int outputDeComfilesize = 0; // Count the input file size
	
	// Constructor
	public LZWCompression() {}
	
	/**
	 * Compression function
	 * @param String
	 * @param output
	 * @param detail
	 * @throws IOException
	 * 
	 */
	public void LZW_compress(String input, String output, boolean detail) throws IOException {
		inputComfilesize = 0; // Initialize the input size count
		outputComfilesize = 0;// Initialize the output size count
		
		// Create the dictionary to store the key, vale 
		ArrayList<SinglyLinkedList> dictionary = new ArrayList<SinglyLinkedList>();
		for(int i = 0; i < array_size; i++) {
			dictionary.add(new SinglyLinkedList());
		}
		
		// Initialize the dictionary
		for(int i = 0; i < BinarySize; i++) {
			String temp = "" + (char)i;
			int index = Math.abs((temp.hashCode())) % array_size;
			dictionary.get(index).addNodeEnd(i, temp);
		}
		
		CompressCount = BinarySize;
		
		// Read the input file
		BinaryIn inBinary = new BinaryIn(input);
		
		// Create the output file
		BinaryOut outBinary = new BinaryOut(output);
		
		// Local Variables 
		byte input_Byte;
		String temp = "";
		String next = "";
		
		try {
			// Read the First Character from input file into the String
			input_Byte = inBinary.readByte();
			inputComfilesize++;
			if(input_Byte < 0) {
				input_Byte += BinarySize;
			}
			temp = "" + (char)input_Byte;
			
			while(true) {
				input_Byte = inBinary.readByte();
				inputComfilesize++;
				if(input_Byte < 0) {
					input_Byte += BinarySize;
				}
				next = "" + (char)input_Byte;
				
				String tempString = temp + next;
//				boolean tempHasKey = hasKey(dictionary, tempString);
//				boolean tempHastemp = hasKey(dictionary, temp);
//				String beforeString = tempString;
				if(hasKey(dictionary, tempString)) {
					while(hasKey(dictionary, tempString)) {
						input_Byte = inBinary.readByte();
						inputComfilesize++;
						if(input_Byte < 0) {
							input_Byte += BinarySize;
						}
						next = "" + (char)input_Byte;
						temp = tempString;
						tempString = tempString + next;
					}					
					int innumber = getData(dictionary, temp);
					outBinary.write(innumber, W);
					outputComfilesize++;
					if(CompressCount < chunksize - 1) {
						CompressCount++;
						dictionary.get(Math.abs((temp + next).hashCode()) % array_size).addNodeEnd(CompressCount, tempString);
					}
					temp = next;
				}else {
					int innumber = getData(dictionary, temp);
					outBinary.write(innumber, W);
					outputComfilesize++;
					if(CompressCount < chunksize - 1) {
						CompressCount++;
						dictionary.get(Math.abs((temp + next).hashCode()) % array_size).addNodeEnd(CompressCount, temp + next);
					}
					temp = next;
				}
			}
		} catch (Exception e) {
			String tempString = next;
			int innumber;
			if(hasKey(dictionary, tempString)) {
				innumber = getData(dictionary, tempString);
				outBinary.write(innumber, W);
				outputComfilesize++;
			}else {
				innumber = getData(dictionary, temp);
				outBinary.write(innumber, W);
				outputComfilesize++;
				innumber = getData(dictionary, next);
				outBinary.write(innumber, W);
				outputComfilesize++;
			}
			if(detail) {
				System.out.println("bytes read = " + inputComfilesize + ", bytes written = " + outputComfilesize);
			}
			outBinary.close();
			System.out.println("Compression finish");
		}
	}
	
	public void LZW_decompress(String input, String output, boolean detail) throws IOException {
		String[] st = new String[4096];
		int i;
		for(i = 0; i < 256; i++) {
			st[i] = "" + (char)i;
		}
		st[i++] = "";
		// Read the input file
		BinaryIn inBinary = new BinaryIn(input);
		
		// Create the output file
		BinaryOut outBinary = new BinaryOut(output);
		
		int codeword = inBinary.readInt(12);
		String temp = st[codeword];
		try {
			while(true) {
				String writein = temp;
				outBinary.write(writein);
				codeword = inBinary.readInt(12);
				if(codeword == 256) break;
				
				String s= st[codeword];
				if(i == codeword) {
					s = temp + temp.charAt(0);
				}
				if(i < 4096) {
					String tempString = temp + s.charAt(0);
					st[i++] = tempString;
				}
				temp = s;
			}
		}catch (Exception e) {
			// TODO: handle exception
			outBinary.close();
			System.out.println("Complete");
		}
			
		
		
	}
	
	/**
	 * Decompression function
	 * @param input
	 * @param output
	 * @param detail
	 * @throws IOException
	 */
//	public void LZW_decompress(String input, String output, boolean detail) throws IOException {
//		inputDeComfilesize = 0; // Initialize the input size count
//		outputDeComfilesize = 0; // Initialize the output size count
//		
//		// Create the dictionary to store the read word
//		ArrayList<SinglyLinkedList> dictionary = new ArrayList<SinglyLinkedList>();
//		for(int i = 0; i < array_size; i++) {
//			dictionary.add(new SinglyLinkedList());
//		}
//		
//		// Initialize the dictionary
//		for(int i = 0; i < BinarySize; i++) {
//			String temp = "" + (char)i;
//			int index = Math.abs((temp.hashCode())) % array_size;
//			dictionary.get(index).addNodeEnd(i, temp);
//		}
//		
//		DeompressCount = BinarySize;
//		
//		// Read the input file
//		BinaryIn inBinary = new BinaryIn(input);
//		
//		// Create the output file
//		BinaryOut outBinary = new BinaryOut(output);
//		
//		String temp = "";
//		String prev = "";
//		int code;
//		
//		/** Read the First Character from input file into the String */
//		code = inBinary.readInt(12);
//		inputDeComfilesize++;
//		temp = getKey(dictionary, code);
//		outBinary.write(temp);
//		outputDeComfilesize++;
//		prev = temp;
//		try {
//			while(true) {
//				code = inBinary.readInt(12);
//				inputDeComfilesize++;
////				boolean temphasData = hasData(dictionary, code);
//				if(hasData(dictionary, code)) {
//					temp = getKey(dictionary, code);
//					System.out.println(temp);
//					outBinary.write(temp);
//					outputDeComfilesize++;
//					String tempString = prev + temp.substring(0, 1);
//					if(DeompressCount < chunksize - 1) {
//						DeompressCount++;
//						dictionary.get(Math.abs((tempString).hashCode()) % array_size).addNodeEnd(DeompressCount, tempString);
//					}
//					prev = temp;
//				}
//			outBinary.flush();
//			}
//		} catch (Exception e) {
//			if (detail) {
//				System.out.println("bytes read = " + inputDeComfilesize + ", bytes written = " + outputDeComfilesize);
//			}
//			outBinary.close();
//			System.out.println("Decompress finish");
//		}
//	}
	
	/**
	 * To justify whether the code in the dictionary
	 * @param dictionary
	 * @param code
	 * @return if the dictionary has the specific code, return true
	 */
	public boolean hasData(ArrayList<SinglyLinkedList> dictionary, int code) {
		for(int i = 0; i < dictionary.size(); i++) {
			for(int j = 0; j < dictionary.get(i).size; j++) {
				int data = dictionary.get(i).get(j).getData();
				if(data == code) {
					return true;
				}
				
			}
		}
		return false;
	}
	
	/**
	 * To return the Key corresponding to the code in the dictionary
	 * @param dictionary
	 * @param code
	 * @return String
	 */
	private String getKey(ArrayList<SinglyLinkedList> dictionary, int code) {
		for(int i = 0; i < dictionary.size(); i++) {
			for(int j = 0; j < dictionary.get(i).size; j++) {
				int data = dictionary.get(i).get(j).getData();
				if(data == code) {
					return dictionary.get(i).get(j).getKey();
				}
				
			}
		}
		return null;
	}
	
	/**
	 * to justify whether there is a specific key in dictionary
	 * @param dictionary
	 * @param key
	 * @return if the key in dictionary, return true
	 */
	public boolean hasKey(ArrayList<SinglyLinkedList> dictionary, String key) {
		for(int i = 0; i < dictionary.get(Math.abs(key.hashCode()) % array_size).size; i++) {
			if(dictionary.get(Math.abs(key.hashCode()) % array_size).get(i).getKey().equals(key)) {
				return true;
			}
		}
		return false;
	}
	
	/**
	 * return the data corresponding to the key in dictionary
	 * @param dictionary
	 * @param key
	 * @returnreturn the data
	 */
	public int getData(ArrayList<SinglyLinkedList> dictionary, String key) {
		for(int i = 0; i < dictionary.get(Math.abs(key.hashCode()) % array_size).size; i++) {
			if(dictionary.get(Math.abs(key.hashCode()) % array_size).get(i).getKey().equals(key)) {
				return dictionary.get(Math.abs(key.hashCode()) % array_size).get(i).getData();
			}
		}
		return 0;
	}
	
	public static void main(String[] args) throws IOException {
		 //TODO Auto-generated method stub
		LZWCompression lzw = new LZWCompression();

		System.out.println("Please enter the command");
		System.out.println("Command for Compression is: java LZWCompression -c shortwords.txt zippedFile.txt");
		System.out.println("Command for Decmpression is : java LZWCompression -d zippedFile.txt unzippedFile.txt");

		/** Scanner object is created to read the values from the keyboard */
		Scanner in = new Scanner(System.in);
		while (in.hasNext()) {
			String line = in.nextLine();
			String arg[] = line.split(" ");
			boolean detail = false;
			if (arg.length == 5) {
				detail = false;
				if (arg[2].equals("-c")) {
					lzw.LZW_compress(arg[3], arg[4], detail);
				}
				if (arg[2].equals("-d")) {
					lzw.LZW_decompress(arg[3], arg[4], detail);
				}
			}else {
				detail = true;
				if (arg[2].equals("-c")) {
					lzw.LZW_compress(arg[4], arg[5], detail);
				}
				if (arg[2].equals("-d")) {
					lzw.LZW_decompress(arg[4], arg[5], detail);
				}
			}
		}
	  in.close();
	}	
}
