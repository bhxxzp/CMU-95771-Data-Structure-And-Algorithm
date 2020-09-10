package TuringFlipper;

public class Transition {
	private char input;
	private char output;
	public int nextState;
	public int action;
	public static final int RIGHT = 1;
	public static final int LEFT = -1;
	
	// Constructor
	public Transition(char input_char, char output_char, int action, int nextState) {
		super();
		this.input = input_char;
		this.output = output_char;
		this.nextState = nextState;
		this.action = action;
	}
	
	// Set the transition input
	public void setInput(char input) {
		this.input = input;
	}
	
	// Get the transition input
	public char getInput() {
		return input;
	}
	
	// Set the transition output
	public void setOutput(char output) {
		this.output = output;
	}
	
	// Get the transition output
	public char getOutput() {
		return output;
	}
	
	// Set the action
	public void setAction(int input) {
		this.action = input;
	}
	
	// Get the action
	public int getAction() {
		return action;
	}
	
	// Set the next state
	public void setNextState(int input) {
		this.nextState = input;
	}
	
	// Get the next state
	public int getNextState() {
		return nextState;
	}
	
}
