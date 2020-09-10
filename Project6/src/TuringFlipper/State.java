package TuringFlipper;

public class State {
	public int state;
	public Transition[] transition;
	
	// Constructor
	public State(int stateID) {
		this.state = stateID;
		this.transition = new Transition[100];
	}
	
	// Set the state number
	public void setState(int stateID) {
		this.state = stateID;
	}
	
	// Get the state number
	public int getState() {
		return state;
	}
	
	// Set the transition
	public void setTransitions(Transition[] transition) {
		this.transition = transition;
	}
	
	// Get the transition
	public Transition[] getTransitions() {
		return transition;
	}
	
	// Add the new transition to one state
	public void addTransition(Transition input){
        transition[input.getInput()] = input;
    }
}