/*
 * @Author: Peng Zeng
 * @AndrewID: pengzeng
 */
package edu.cmu.andrew.pengzeng;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.*;
import java.io.IOException;
import java.io.InputStreamReader;

public class RedBlackTreeSpellChecker {

	public RedBlackTreeSpellChecker() {
		
	}
	
	/*
	 * readFile is function used to read the file.
	 */
	public RedBlackTree readFile(String fileroad, RedBlackTree tree) throws IOException {
		File file = new File(fileroad);
		InputStreamReader input=new InputStreamReader(new FileInputStream(file));
		BufferedReader reader=new BufferedReader(input);
		String line = "";
		while((line = reader.readLine()) != null) {
			tree.insert(line);
			}
		reader.close();
		return tree;
		}
	
	/*
	 * Collection of the comments
	 */
	public void Comment(RedBlackTree tree) {
		System.out.println("Red Black Tree is loaded with " + tree.getSize()+ " words");
		System.out.println("The height of the tree is " + tree.height());
		System.out.println("2 * Lg( n+1) = " + 2* (Math.log(tree.getSize() + 1) / Math.log(2)));
		System.out.println("Legal commands are: ");
		System.out.println("'d' display the entire word tree with a level order traversal.");
		System.out.println("'s' print the words of the tree in sorted order (use an inorder traversal).");
		System.out.println("'r' print the words of the tree in reverse sorted order.");
		System.out.println("'c' <word> to spell check this word");
		System.out.println("'a' <word> add word to tree.");
		System.out.println("'f' <fileName> to spell check a text file for spelling errors.");
		System.out.println("    You can input 'f src + filename' to run the test");
		System.out.println("'i' display the diameter of the tree.");
		System.out.println("'m' view this menu.");
		System.out.println("'!' to quit.");
	}
	
	/*
	 * Word Check function.
	 * To check whether the word is in the file or not
	 * 
	 * Best Case: Omega(1)
	 * Worst Case: Big Omega(log(n))
	 */
	public void wordCheck(String[] input, RedBlackTree tree) {
		if (input.length < 2) {
			System.out.println("You must add some words!");
		}else {	String temp = input[1];
			if (tree.contains(temp)) {
				System.out.println("Find '" + temp + "' for " + tree.getRecentCompares() + " times!");
			}else {
				System.out.println("'" + temp + "'" + " was not found in this dictionary. Maybe you find '"+ tree.closeBy(temp) + "'.");
			}
		}
	}
	
	/*
	 * Add the word into Tree
	 * 
	 * Big Omega(log(n))
	 */
	public void addWord(String[] input, RedBlackTree tree) {
		if (input.length < 2) {
			System.out.println("You must add some words!");
		}else {
			String temp = input[1];
			if (tree.contains(temp)) {
				System.out.println("'" + temp + "' exists in the dictionary. Please another words.");
			}else {
				tree.insert(temp);
				System.out.println("'" + temp +"' has been added in the dictionary!");
				tree.inOrderTraversal();
			}
		}
	}
	
	/*
	 * To check whether the word in file is in the tree
	 */
    public void BadWordsCheck(String[] input, RedBlackTree tree) throws FileNotFoundException{
        String temp = input[1];
        Scanner fileReader = new Scanner(new File(temp));
        boolean noSpellError = true;
        while(fileReader.hasNext()){
            String word = fileReader.next().replace('.', ' ').trim();
            if(!tree.contains(word)){
                noSpellError = false;
                System.out.printf(word + " was not found in dictionary.\n");
            }
        }
        if(noSpellError){
            System.out.println("No spelling errors found.");
        }


    }
	
	
	public static void main(String[] args) throws Exception {
		RedBlackTreeSpellChecker check = new RedBlackTreeSpellChecker();
		RedBlackTree tree = new RedBlackTree();
		System.out.println("Insert number to select txt file.\n"
				+ " '1' is for 'shortwords.txt'.\n"
				+ " '2' is for 'words.txt'.\n"
				+ " '3' is for 'badwords.txt'.\n");
		Scanner number = new Scanner(System.in);
		String select = number.nextLine();
		if (select.equals("1")) {
			tree = check.readFile("src/edu/cmu/andrew/pengzeng/shortwordsn.txt", tree);
		}else if (select.equals("2")){
			tree = check.readFile("src/edu/cmu/andrew/pengzeng/words.txt", tree);
		}else if (select.equals("3")) {
			tree = check.readFile("src/edu/cmu/andrew/pengzeng/badwords.txt", tree);
		}
		
		check.Comment(tree);
		
		while(true) {
			Scanner in = new Scanner(System.in);
			String inputRaw = in.nextLine();
			String[] input = inputRaw.split(" ");
			if (input[0].equals("!")) {
				System.out.println("The End!\n");
				break;
			}
			if (input[0].equals("d")) {
				System.out.println("Display the entire word tree with a level order traversal.\n");
				tree.levelOrderTraversal();
			}
			if (input[0].equals("s")) {
				System.out.println("Print the words of the tree in sorted order (use an inorder traversal).\n");
				tree.inOrderTraversal();
			}
			if (input[0].equals("r")) {
				System.out.println("Print the words of the tree in reverse sorted order.\n");
				tree.reverseOrderTraversal();
			}
			if (input[0].equals("c")) {
				System.out.println("Spell check this word.\n");
				check.wordCheck(input, tree);
			}
			if (input[0].equals("a")) {
				System.out.println("Add word to tree.\n");
				check.addWord(input, tree);
			}
			if (input[0].equals("f")) {
				System.out.println("Spell check a text file for spelling errors.\n");
				check.BadWordsCheck(input, tree);
			}
			if (input[0].equals("i")) {
				System.out.println("Display the diameter of the tree\n");
				System.out.println("The diameter = " + tree.Diameter());
			}
			if (input[0].equals("m")) {
				System.out.println("View this menu again.\n");
				check.Comment(tree);
			}
		}
	}
}
