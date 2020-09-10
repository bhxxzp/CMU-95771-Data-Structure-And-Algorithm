package TuringFlipper;

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
		Turing machine1 = new Turing(2);    // A two state machine

        State s0 = new State(0);                 // Only s0 has transitions
   
        s0.addTransition(new Transition('0','1',Transition.RIGHT,0));
        s0.addTransition(new Transition('1','0',Transition.RIGHT,0));
        s0.addTransition(new Transition('B','B',Transition.RIGHT,1));
        machine1.addState(s0);                 // Add the state to the machine
        
        String inTape = "0101010101010";     // Define some input

        System.out.println(inTape);

        String outTape = machine1.execute(inTape);  // Execute the machine

        System.out.println(outTape);  // Show the machine’s output

	}
	
	
}