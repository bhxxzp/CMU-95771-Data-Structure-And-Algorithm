package TuringSubtractor;

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
	
	public static void main(String[] args) {
		Turing machine2 = new Turing(7);
		
		State s0 = new State(0);
		State s1 = new State(1);
		State s2 = new State(2);
		State s3 = new State(3);
		State s4 = new State(4);
		State s5 = new State(5);
		State s6 = new State(6);
		
		s0.addTransition(new Transition('0', 'B', Transition.RIGHT, 1));
		s0.addTransition(new Transition('1', 'B', Transition.RIGHT, 5));
		s1.addTransition(new Transition('0', '0', Transition.RIGHT, 1));
		s1.addTransition(new Transition('1', '1', Transition.RIGHT, 2));
		s2.addTransition(new Transition('1', '1', Transition.RIGHT, 2));
		s2.addTransition(new Transition('0', '1', Transition.LEFT, 3));
		s2.addTransition(new Transition('B', 'B', Transition.LEFT, 4));
		s3.addTransition(new Transition('1', '1', Transition.LEFT, 3));
		s3.addTransition(new Transition('0', '0', Transition.LEFT, 3));
		s3.addTransition(new Transition('B', 'B', Transition.RIGHT, 0));
		s4.addTransition(new Transition('1', 'B', Transition.LEFT, 4));
		s4.addTransition(new Transition('0', '0', Transition.LEFT, 4));
		s4.addTransition(new Transition('B', '0', Transition.LEFT, 6));
		s5.addTransition(new Transition('0', 'B', Transition.RIGHT, 5));
		s5.addTransition(new Transition('1', 'B', Transition.RIGHT, 5));
		s5.addTransition(new Transition('B', 'B', Transition.RIGHT, 6));
		
		machine2.addState(s0);
		machine2.addState(s1);
		machine2.addState(s2);
		machine2.addState(s3);
		machine2.addState(s4);
		machine2.addState(s5);
		machine2.addState(s6);
		
        String inTape = "00001000";     // Define some input

        System.out.println(inTape);

        String outTape = machine2.execute(inTape);  // Execute the machine

        System.out.println(outTape);  // Show the machine’s output
		
        

	}
	
	
}