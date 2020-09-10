package mt.project;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import edu.cmu.andrew.pengzeng.*;

public class MerkleTreeProject {

	public static void main(String[] args) throws IOException, NoSuchAlgorithmException {
		String[] fileName = new String[4];
        fileName[0] = "CrimeLatLonXY.csv";
        fileName[1] = "CrimeLatLonXY1990_Size2.csv";
        fileName[2] = "CrimeLatLonXY1990_Size3.csv";
        fileName[3] = "smallFile.txt";
        // You can see the "smallFile.txt" can be generated root
        // loop for each file
        for (String filename : fileName) {
            SinglyLinkedList lineList = readfile(filename);
            // check if it is even
            lineList = check(lineList);
            SinglyLinkedList hashBaseList = new SinglyLinkedList();
            String s1, s2, s;
            // loop to the top in other branch
            for (int k = 0; k < lineList.countNodes(); k += 2) {
                s1 = h((String) lineList.getObjectAt(k));
                s2 = h((String) lineList.getObjectAt(k + 1));
                s = h((String)(s1 + s2));
                hashBaseList.addAtEndNode(s);
            }
            String root;
            if (hashBaseList.countNodes() > 1) {
                lineList = createRoot(hashBaseList);
                // combine each branch
                root = h((((String) lineList.getObjectAt(0) + ((String) lineList.getObjectAt(1)))));
            } else {
                root = hashBaseList.getObjectAt(0).toString();
            }
            System.out.println("The Merkel root is: " + root);
            // justify if what we want
            if ("A5A74A770E0C3922362202DAD62A97655F8652064CCCBE7D3EA2B588C7E07B58".equals(root)) {
                System.out.println("The file " + filename + " is what we want!");
                System.out.println();
            } else {
                System.out.println("The file " + filename + " is not useful!");
                System.out.println();
            }
        }

    }
	
	public static SinglyLinkedList readfile(String filepath) throws IOException {
		String line;
		BufferedReader bufferReader = new BufferedReader(new FileReader(filepath));
		SinglyLinkedList lineList = new SinglyLinkedList();
		// if line equal the bufferReader each line and not null, add to the lineList
		while((line = bufferReader.readLine()) != null) {
			lineList.addAtEndNode(line);
		}
		bufferReader.close();
		return lineList;
	}
	
	public static SinglyLinkedList createRoot(SinglyLinkedList lineList) throws NoSuchAlgorithmException {
		String a, b, c;
		while(lineList.countNodes() != 2) {
			SinglyLinkedList hashList = new SinglyLinkedList();
			// combine and hash to the top
			for (int i = 0; i < lineList.countNodes(); i += 2) {
				a = (String) lineList.getObjectAt(i);
				b = (String) lineList.getObjectAt(i + 1);
				c = h((String)(a + b));
				hashList.addAtEndNode(c);
			}
			
            lineList = check(hashList);
            lineList = createRoot(lineList);
		}
		return lineList;
	}
	
	
	public static SinglyLinkedList check(SinglyLinkedList hashList) {
        int nodeNumber = hashList.countNodes();
        //if node number not even, add the last one to the the end
        if (nodeNumber % 2 == 1) {
        	hashList.addAtEndNode(hashList.getLast());
        }
        return hashList;
    }
	
    public static String h(String text) throws NoSuchAlgorithmException {
        MessageDigest digest = MessageDigest.getInstance("SHA-256");
        byte[] hash = digest.digest(text.getBytes(StandardCharsets.UTF_8));
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i <= 31; i++) {
            byte b = hash[i];
            sb.append(String.format("%02X", b));
        }
        return sb.toString();
    }

}
