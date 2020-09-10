import java.math.BigInteger;
import java.util.Scanner;
/*
 * @author Peng Zeng
 * AndrewID: pengzeng
 */

public class ReversePolishNotation {
	
	RedBlackTree rbt = new RedBlackTree();
	
	
	/*
	 * calculate the input
	 */
	public BigInteger calculate(String input) throws Exception {
		DynamicStack stack = new DynamicStack();
		String[]inputnumber = input.split(" ");
		for (int i = 0; i < inputnumber.length; i++) {
			if (inputnumber[i].matches("^[0-9]*$")) {
				// if the input is number, then push into stack
				BigInteger temp0 = new BigInteger(inputnumber[i]);
				stack.push(temp0);
			}else if(inputnumber[i].matches("\\+")) {
				// pop out 2 elements in stack, then make the calculation and push into stack again
				if(!BigInteger.class.isInstance(stack.gettop())) throw new Exception("no variable " + stack.gettop());
				BigInteger temp2 = (BigInteger) stack.pop();
				BigInteger temp1 = (BigInteger) stack.pop();
				BigInteger result = temp1.add(temp2);
				stack.push(result);	
			}else if(inputnumber[i].matches("\\-")) {
				// pop out 2 elements in stack, then make the calculation and push into stack again
				BigInteger temp2 = (BigInteger) stack.pop();
				BigInteger temp1 = (BigInteger) stack.pop();
				BigInteger result = temp1.subtract(temp2);
				stack.push(result);
			}else if(inputnumber[i].matches("\\*")) {
				// pop out 2 elements in stack, then make the calculation and push into stack again
				BigInteger temp2 = (BigInteger) stack.pop();
				BigInteger temp1 = (BigInteger) stack.pop();
				BigInteger result = temp1.multiply(temp2);
				stack.push(result);
			}else if(inputnumber[i].matches("\\/")) {
				// pop out 2 elements in stack, then make the calculation and push into stack again
				BigInteger temp2 = (BigInteger) stack.pop();
				BigInteger temp1 = (BigInteger) stack.pop();
				BigInteger result = temp1.divide(temp2);
				stack.push(result);
			}else if(inputnumber[i].matches("\\%")) {
				// pop out 2 elements in stack, then make the calculation and push into stack again
				BigInteger temp2 = (BigInteger) stack.pop();
				BigInteger temp1 = (BigInteger) stack.pop();
				BigInteger result = temp1.mod(temp2);
				stack.push(result);
			}else if(inputnumber[i].matches("\\~")) {
				// pop out 1 elements in stack, then make the calculation and push into stack again
				BigInteger temp1 = (BigInteger) stack.pop();
				BigInteger result = temp1.negate();
				stack.push(result);
			}else if(inputnumber[i].matches("\\^")) {
				// pop out 3 elements in stack, then make the calculation and push into stack again
				if(stack.getcount() != 3)throw new Exception("stack underflow exception");
				BigInteger temp3 = (BigInteger) stack.pop();
				BigInteger temp2 = (BigInteger) stack.pop();
				BigInteger temp1 = (BigInteger) stack.pop();
				BigInteger result = temp1.modPow(temp2, temp3);
				stack.push(result);
			}else if(inputnumber[i].matches("\\=")) {
				// pop out 2 elements in stack, then make the calculation and push into stack again
				// if the second element is the number, throw out the Exception
				BigInteger temp2 = (BigInteger) stack.pop();
				if (BigInteger.class.isInstance(stack.gettop())) throw new Exception(stack.gettop() + " not an lvalue");
				String temp1 = (String) stack.pop();
				rbt.insert(temp1, temp2);
				stack.push(temp2);
			}else {
				if(!rbt.contains(inputnumber[i])) {
					stack.push(inputnumber[i]);
				}else {
						BigInteger temp1 = rbt.lookup(inputnumber[i]);
						stack.push(temp1);
				}
			}
		}
		// output all the calculation result
		return (BigInteger)stack.pop();
	}
	public static void main(String[] args) throws Exception {
		ReversePolishNotation RPN = new ReversePolishNotation();
		Scanner input = new Scanner(System.in);
		while(true) {
			System.out.println("java ReversePolishNotation");
			String temp = input.nextLine();
			// press return to stop the processing
			if (temp.equals(null)||temp.isEmpty()) {
				System.out.println("terminating");
				break;
			}else {
				try {
				BigInteger result = RPN.calculate(temp);
				System.out.println(result);
				}catch(Exception e) {
					System.out.println("Exception in thread \"main\" java.lang.Exception: " + "error: " + e.getMessage());
					}
				}
		}
	}
	
}
