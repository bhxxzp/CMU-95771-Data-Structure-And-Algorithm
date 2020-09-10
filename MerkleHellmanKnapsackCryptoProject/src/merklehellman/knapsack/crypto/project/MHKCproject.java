package merklehellman.knapsack.crypto.project;

import java.util.Scanner;
import edu.cmu.andrew.pengzeng.*;
import java.math.BigInteger;

public class MHKCproject {
	
	public static void main(String[] args) {
		
		MHKCproject newProject = new MHKCproject();
		Scanner input = new Scanner(System.in);
		System.out.println("Enter a String and I will encrypt it as a large Integer.");
		String lineInput = input.nextLine();
		if (lineInput.length() > 80) {
			System.out.println("You must input length less than 80");
			lineInput = input.nextLine();
		}
		 
		System.out.println("Clear text: ");
        System.out.println(lineInput);
        System.out.println("Number of clear text bytes = " + lineInput.length());
        
        
        SinglyLinkedList w = newProject.getW();
        BigInteger ws = newProject.getWS(w);
        BigInteger q = newProject.getQ(ws);
        BigInteger r = newProject.getR(q);
        SinglyLinkedList beta = newProject.getBeta(w, r, q);
        
        String encryptResult = encryptOutput(lineInput, w, beta);
        String decryptResult = decryptOutput(lineInput, w, beta, r, q);
        System.out.println(lineInput + " is encrypted as " + encryptResult);
        System.out.println("Result of decryption: " + decryptResult);

		
		input.close();
	}
	
	// Create w
	public SinglyLinkedList getW(){
		
		SinglyLinkedList w = new SinglyLinkedList();
		BigInteger step = BigInteger.ONE;
		BigInteger n = BigInteger.TWO;
		
		for (int i = 0; i < 640; i++) {
			w.addAtEndNode(step);
			step = step.multiply(n);
		}
		
		return w;
	}
	
	// create sum of w
	public BigInteger getWS(SinglyLinkedList w) {
		
		w.reset();
		BigInteger ws = BigInteger.ZERO;
		
		for(int i = 0; i < w.countNodes(); i++) {
			ws = ws.add((BigInteger) w.getObjectAt(i));
		}
		
		return ws;
	}
	
	// create Q
	public BigInteger getQ(BigInteger ws) {
		BigInteger q = ws.nextProbablePrime();
		return q;
	}
	
	public BigInteger getR(BigInteger q) {
		BigInteger r = q.divide(BigInteger.TEN).nextProbablePrime();
		return r;
	}
	
	// create Beta
	public SinglyLinkedList getBeta(SinglyLinkedList w, BigInteger r, BigInteger q) {
		w.reset();
		SinglyLinkedList beta = new SinglyLinkedList();
		
		for (int i = 0; i < w.countNodes(); i++) {
			BigInteger newW = new BigInteger(w.getObjectAt(i).toString());
			beta.addAtEndNode((newW).multiply(r).mod(q));
		}
		return beta;
	}
	
	// change Decimal to Binary
	public String DecimalToBinary(String input) {
		int i = Integer.parseInt(input.trim());
		String binaryInput = Integer.toBinaryString(i);
		return binaryInput;
	}
	
	// BigInteger a is the each number of input.
	// BigInteger b is the each number of beta.
	// result is encrypting code. 
	public static BigInteger encrypt(String input, SinglyLinkedList beta) {
		BigInteger result = BigInteger.ZERO;
		for (int i = 0; i < input.length(); i++) {
			BigInteger a = new BigInteger(String.valueOf(input.charAt(i)));
			BigInteger b = (BigInteger)(beta.getObjectAt(i));
			result = result.add(a.multiply(b));
		}
		return result;
		
	}
	
	// change binary to char
	public static String binaryToChar(String binaryResult) {
        String result = "";
        char next;
        next = (char) Integer.parseInt(binaryResult.substring(0, 8), 2);
        result += next;

        return result;
    }

	// decrypt
	public static String decrypt(BigInteger code, BigInteger r, BigInteger q, SinglyLinkedList w) {
		BigInteger rmod = r.modInverse(q);
		BigInteger temp = code.multiply(rmod).mod(q);
		
		w.reset();
		SinglyLinkedList tempBinary = new SinglyLinkedList();
		for (int i = w.countNodes(); i > 0; i--) {
            BigInteger temp2 = temp.subtract((BigInteger) w.getObjectAt(i - 1));
            if (temp2.compareTo(BigInteger.ZERO) == -1) {
            	tempBinary.addAtFrontNode(0);
            } else {
            	tempBinary.addAtFrontNode(1);
            	temp = temp2;
            }
        }
		String Result = "";
        for (int i = 0; i < tempBinary.countNodes(); i++) {
        	Result += tempBinary.getObjectAt(i);
        }

        String outputResult = binaryToChar(Result);

        return outputResult;
		
	}
	
	// output encryptCode
	public static String encryptOutput(String lineInput,SinglyLinkedList w,SinglyLinkedList Beta) {
		String encryptResult = "";
		byte[] bytes = lineInput.getBytes();
        //Loop based on user input String, one by one
        for (int i = 0; i < bytes.length; i++) {
            byte newByte = bytes[i];
            //format byte into appropriate String
            String binary = String.format("%8s", Integer.toBinaryString(newByte)).replace(" ", "0");
            //create subset of w list, used for this character
            SinglyLinkedList w0 = new SinglyLinkedList();
            for (int j = 0; j < 8; j++) {
                w0.addAtEndNode(w.getObjectAt(i * 8 + j));
            }

            //encrypt
            BigInteger encrypt = encrypt(binary, Beta);
            encryptResult += encrypt;
        }
        return encryptResult;
	}
	
	// Output decryptCode
	public static String decryptOutput(String lineInput,SinglyLinkedList w,SinglyLinkedList Beta, BigInteger r, BigInteger q) {
			String decryptResult = "";
			byte[] bytes = lineInput.getBytes();
			//Loop based on user input String, one by one
			for (int i = 0; i < bytes.length; i++) {
			    byte newByte = bytes[i];
			    //format byte into appropriate String
			    String binary = String.format("%8s", Integer.toBinaryString(newByte)).replace(" ", "0");
			    //create subset of w list, used for this character
			    SinglyLinkedList w0 = new SinglyLinkedList();
			    for (int j = 0; j < 8; j++) {
			        w0.addAtEndNode(w.getObjectAt(i * 8 + j));
			    }
			    BigInteger encrypt = encrypt(binary, Beta);
			    String temp = decrypt(encrypt, r, q, w);
			    decryptResult += temp;
			}
			return decryptResult;
	}
}
