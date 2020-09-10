package TuringDecider;

public class Turing {
	public static final int tape = 100; // Initialize the size of the total tape
	public State state[];
	
	// Constructor
	public Turing(int stateNum) {
		state = new State[stateNum];
	}
	
	// Add the state to the set of the state
	public void addState(State input) {
		state[input.getState()] = input;
	}
	
	// Execute the function
	public String execute(String inTape) {
		State tempState = state[0];
		int headTape = 0;
		StringBuilder output = new StringBuilder();
		for(int i = 0; i < tape; i++) {
			if(i < inTape.length()) {
				output.append(inTape.charAt(i));
			}else {
				output.append('B');
			}
		}
		
		while(tempState.getState() != state.length) {
			Transition temp = tempState.transition[output.charAt(headTape)];
			output.setCharAt(headTape, temp.getOutput());
			headTape += temp.getAction();
			if(temp.getNextState() == state.length - 1) {
				break;
			}else {
				tempState = state[temp.getNextState()];
			}
			
		}
		return output.toString();
	}
	
	// Get the idea from 
	// https://www.youtube.com/watch?v=cR4Re0YfoOo&list=PLBlnK6fEyqRgp46KUv4ZY69yXmpwKOIev&index=98
	
	public static void main(String[] args) {
		Turing machine3 = new Turing(10);
		
		State s0 = new State(0);
		State s1 = new State(1);
		State s2 = new State(2);
		State s3 = new State(3);
		State s4 = new State(4);
		State s5 = new State(5);
		State s6 = new State(6);
		State s7 = new State(7);
		State s8 = new State(8);
		State s9 = new State(9);
		
		s0.addTransition(new Transition('0', '1', Transition.RIGHT, 1));
		s0.addTransition(new Transition('1','0',Transition.RIGHT,8));
		
		s1.addTransition(new Transition('0', '0', Transition.RIGHT, 1));
		s1.addTransition(new Transition('1', 'X', Transition.LEFT, 2));
		
		s2.addTransition(new Transition('X', 'X', Transition.LEFT, 2));
		s2.addTransition(new Transition('Y', 'Y', Transition.LEFT, 2));
		s2.addTransition(new Transition('0', 'Y', Transition.RIGHT, 3));
		s2.addTransition(new Transition('1', '1', Transition.RIGHT, 5));
		
		s3.addTransition(new Transition('X', 'X', Transition.RIGHT, 3));
		s3.addTransition(new Transition('Y', 'Y', Transition.RIGHT, 3));
		s3.addTransition(new Transition('B', 'B', Transition.LEFT, 4));
		s3.addTransition(new Transition('1','X',Transition.LEFT,2));
		
		s4.addTransition(new Transition('0', 'B', Transition.LEFT, 4));
		s4.addTransition(new Transition('Y', 'B', Transition.LEFT, 4));
		s4.addTransition(new Transition('X', 'B', Transition.LEFT, 4));
		s4.addTransition(new Transition('1', '0', Transition.RIGHT, 9));
		
		s5.addTransition(new Transition('X', 'B', Transition.RIGHT, 5));
		s5.addTransition(new Transition('Y', 'B', Transition.RIGHT, 5));
		s5.addTransition(new Transition('B', 'B', Transition.RIGHT, 9));
		s5.addTransition(new Transition('1','B',Transition.RIGHT,6));
		
		s6.addTransition(new Transition('1', 'B', Transition.RIGHT, 6));
		s6.addTransition(new Transition('B', 'B', Transition.LEFT, 7));
		
		s7.addTransition(new Transition('B', 'B', Transition.LEFT, 7));
		s7.addTransition(new Transition('X', 'X', Transition.LEFT, 7));
		s7.addTransition(new Transition('Y', 'Y', Transition.LEFT, 7));
		s7.addTransition(new Transition('1', '0', Transition.RIGHT, 9));
		
		s8.addTransition(new Transition('1', 'B', Transition.RIGHT, 8));
		s8.addTransition(new Transition('0', 'B', Transition.RIGHT, 8));
		s8.addTransition(new Transition('B', 'B', Transition.RIGHT, 9));
		
		machine3.addState(s0);
		machine3.addState(s1);
		machine3.addState(s2);
		machine3.addState(s3);
		machine3.addState(s4);
		machine3.addState(s5);
		machine3.addState(s6);
		machine3.addState(s7);
		machine3.addState(s8);
		machine3.addState(s9);
		
		String inTape = "0000011";     // Define some input
        System.out.println(inTape);
        String outTape = machine3.execute(inTape);  // Execute the machine
        System.out.println(outTape);  // Show the machine’s output
        System.out.println();
        
        String inTape1 = "01";     // Define some input
        System.out.println(inTape1);
        String outTape1 = machine3.execute(inTape);  // Execute the machine
        System.out.println(outTape1);  // Show the machine’s output
        System.out.println();
        
        String inTape2 = "10";     // Define some input
        System.out.println(inTape2);
        String outTape2 = machine3.execute(inTape2);  // Execute the machine
        System.out.println(outTape2);  // Show the machine’s output
        System.out.println();
        
        String inTape3 = "00000000001111111111";     // Define some input
        System.out.println(inTape3);
        String outTape3 = machine3.execute(inTape3);  // Execute the machine
        System.out.println(outTape3);  // Show the machine’s output
	}
}