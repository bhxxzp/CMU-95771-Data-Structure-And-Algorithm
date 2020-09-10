import java.util.List;
import java.util.NoSuchElementException;

/*
 * @Author: Peng Zeng
 * AndrewID: pengzeng
 * 
 * This class is the implementation of a dynamic stack
 * Initial size is 6, top is -1. Double the size when it is full
 */

public class DynamicStack {

	private Object[] stack;
	private int top;
	private int count;

	/*
	 * Constructor
	 * Big Theta(1)
	 */
	public DynamicStack() {
		// Initiate the size of stack as 6, top as -1
		// count is to count the number of the value in stack
		stack = new Object[6];
		top = -1;		
		count = 0;
	}
	
	/*
	 * Big Theta(1)
	 * 
	 * To check whether the stack is empty
	 */
	public boolean isEmpty() {
		return top == -1;
	}
	
	/*
	 * Big Theta(1)
	 * 
	 * To check whether the stack is full
	 */
	public boolean isFull() {
		return count == stack.length;
	}
	
	/*
	 * Big Theta(1)
	 * 
	 * Pop the top element in stack
	 */
	public Object pop() {
		if (isEmpty()) {
			throw new NoSuchElementException();
		}
		
		Object rmItem = stack[top];
		stack[top] = null;
		count--;
		top = (top - 1) % stack.length;
		return rmItem;
	}
	
	/*
	 * Big Theta(1)
	 * 
	 * Push the element into top stack
	 */
	public void push(Object object) {
		if (object == null) {
			System.out.println("You should insert something.");
			return;
		}
		
		if (isEmpty()) {
			top = 0;
			stack[top] = object;
			count++;
		} else if (isFull()) {
			Object[] newstack = new Object[stack.length * 2];
			for (int i = 0; i < stack.length; i++) {
				newstack[i] = stack[i];
			}
			top++;
			newstack[top] = object;
			stack = newstack;
			count++;
		}else {
			top++;
			stack[top] = object;
			count++;
		}
	}
	
	/*
	 * Get the top element of the stack
	 * 
	 * Big Theta(1)
	 */
	public Object gettop() {
		return stack[top];
	}
	
	/*
	 * Get the number of element
	 * 
	 * Big Theta(1)
	 */
	public int getcount() {
		return count;
	}
	
	
	/*
	 * Big Theta(n)
	 * 
	 * Print out the stack
	 */
	public String toString() {
		StringBuilder item = new StringBuilder("");
		for (int i = 0; i < count; i++) {
			item.append(stack[i].toString() + (", "));
		}
		return item.toString();
	}
	
	
	public static void main(String[] args) {
		DynamicStack s = new DynamicStack();
		System.out.println("Start Testing");
		System.out.println("Is stack empty? " + s.isEmpty());
		System.out.println("The value of top is " + s.top);
		System.out.println("Input 1000 values!");
		for (int i = 0; i < 1000; i++) {
			s.push(i);
		}
		System.out.println("The value of top is " + s.gettop());
		System.out.println("The stack is " + s.toString());
		System.out.println();
		System.out.println("Now pop the top 10 values");
		System.out.println();
		int i = 990;
		while(i < s.count) {
			System.out.println("The pop value is " + s.pop());
		}
		System.out.println();
		System.out.println("The size of stack should be 990? " + (s.count == 990));
		System.out.println("The value of top is " + s.top);
		System.out.println("Pop all the elements in stack");
		while(!s.isEmpty()) {
			s.pop();
		}
		System.out.println("Is stack empty again? " + s.isEmpty());

	}
	
}
