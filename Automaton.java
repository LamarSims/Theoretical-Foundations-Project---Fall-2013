package theoreticalFoundations;

/*Lamar A. Sims
 * November 19, 2013*/

public class Automaton{
	//declare and initialize variables
	private MySet[] states;
	private MySet terminals;
	private MySet stackAlpha;
	private int startState;
	private MySet finalStates;
	private boolean accpeted = false;
	private boolean rejected = false;
	
	/** Construct an Automaton object */
	public Automaton(){
		this.states = new MySet[1];
		this.states[0] = new MySet();
		this.terminals = new MySet();
		this.stackAlpha = new MySet();
		this.startState = 0;
		this.finalStates = new MySet(0);
	}
	
	/** getStates():
	 * 		Returns the number of states */
	public int getStates(){
		return this.states.length;
	}
	
	/** getTerminals():
	 * 		Returns the terminals of this Automaton */
	public String[] getTerminals(){
		String[] temp = new String[this.terminals.getSize()];
		for (int i = 0; i < this.terminals.getSize(); i++){
			temp[i] = (String)this.terminals.get(i);
		}
		return temp;
	}
	
	/** getStackAlpha():
	 * 		Returns the stack alphabet of this Automaton */
	public String[] getStackAlpha(){
		String[] temp = new String[this.stackAlpha.getSize()];
		for (int i = 0; i < this.stackAlpha.getSize(); i++){
			temp[i] = (String)this.stackAlpha.get(i);
		}
		return temp;
	}
	
	/** getStartState():
	 * 		Returns the start state of this Automaton */
	public int getStartState(){
		return this.startState;
	}
	
	/** getFinalStates():
	 * 		Returns the final states of this Automaton */
	public int[] getFinalStates(){
		int[] temp = new int[this.finalStates.getSize()];
		for (int i = 0; i < this.finalStates.getSize(); i++){
			temp[i] = (int)this.finalStates.get(i);
		}
		return temp;
	}
	
	/** setStates(int states):
	 * 		Sets the number of states for this Automaton */
	public void setStates(int states){
		//check for states being out of bounds
		if (states <= 0)
			throw new ArrayIndexOutOfBoundsException("Could not set states. States cannot be 0 or less than zero!");
		this.states = new MySet[states];
		for (int i = 0; i < states; i++){
			this.states[i] = new MySet();
		}
	}
	
	/** setTerminals(String[] terminals):
	 * 		Sets the terminals of this Automaton */
	public void setTerminals(String[] terminals){
		//check for null object
		if (terminals == null)
			throw new NullPointerException("Could not set the terminals. Terminals cannot be null!");
		this.terminals = new MySet(terminals); //set the specified terminals
	}
	
	/** setStackAlpha(String[] alphabet):
	 * 		Sets the stack alphabet for this Automaton */
	public void setStackAlpha(String[] alphabet){
		//check for null object
		if (alphabet == null)
			throw new NullPointerException("Could not set the stack alphabet. Alphabet cannot be null!");
		Object[] temp = new Object[alphabet.length];
		for (int i = 0; i < alphabet.length; i++){
			temp[i] = alphabet[i];
		}
		this.stackAlpha.add(alphabet); //set the stack alphabet
	}
	
	/** setStartState(int state):
	 * 		Sets the starting state of this Automaton */
	public void setStartState(int state){
		//check for state being out of bounds
		if (state < 0 || state >= this.states.length)
			throw new ArrayIndexOutOfBoundsException("Could not set the start state. Start state cannot be less than" +
					" zero and cannot be greater than or equal to the set amount of states!");
		this.startState = state;
	}
	
	/** setFinalStates(int[] finalStates):
	 * 		Sets the final states of this Automaton */
	public void setFinalStates(int[] finalStates){
		//check for null object and for any elements being out of bounds
		if (finalStates == null)
			throw new NullPointerException("Could not set the final states. finalsStates cannot be null!");
		for (int i = 0; i < finalStates.length; i++){
			if (finalStates[i] < 0 || finalStates[i] >= this.states.length)
				throw new ArrayIndexOutOfBoundsException("Could not set the final states. Final states cannot be less than " +
						"zero and cannot be greater than or equal the set amount of states!");
		}
		this.finalStates = new MySet();
		for (int i = 0; i < finalStates.length; i++){
			this.finalStates.add(finalStates[i]);
		}
	}
	
	/** addTerminal(String terminal):
	 * 		Add a terminal to this Automaton */
	public void addTerminal(String terminal){
		//check for null object
		if (terminal == null)
			throw new NullPointerException("Could not add terminal! Terminal cannot be null");
		this.terminals.add(terminal);
	}
	
	/** addSAlpha(String alphabet):
	 * 		Add a new element to the stack alphabet */
	public void addSAlpha(String alphabet){
		//check for null object
		if (alphabet == null)
			throw new NullPointerException("Could not add new stack element! Cannot add a null object.");
		this.stackAlpha.add(alphabet);
	}
	
	/** addToStates(int states):
	 * 		Adds more states to this Automatons states */
	public void addToStates(int states){
		//check if states is out of bounds
		if (states < 0){
			System.out.println("Could not add more states. Specified states cannot be negative.");
			System.exit(0);
		}
		MySet[] temp = new MySet[this.states.length + states];
		//set every element as a MySet
		for (int i = 0; i < temp.length; i++){
			temp[i] = new MySet();
		}
		//copy states elements to temp
		for (int i = 0; i < this.states.length; i++){
			temp[i] = this.states[i];
		}
		this.states = temp; //assign temp to states and copying elements
	}
	
	/** addTransition(int state, String terminal, String popSAlpha, int state, String pushSAlpah):
	 * 		Add a transition to this Automaton */
	public void addTransition(int currentState, String terminal, String popSAlpha, int newState, String pushSAlpha){
		//check if currentState is out of bounds
		if (currentState < 0 || currentState >= this.states.length)
			throw new ArrayIndexOutOfBoundsException("Could not add transition! Current state must not be less than zero " +
					"or greater than or equal to the amount of states.");
		//check if terminal is null or if it is not a member of the set of terminals
		if (terminal == null)
			throw new NullPointerException("Could not add transition! Terminal cannot be null");
		if (terminal != "" && !this.terminals.isMember(terminal)){
			System.out.println("Could not add transition! Terminal is not a member of the set of terminals.");
			System.exit(0);
		}
		//check if specified element to be popped is null or if it is not a member of the stack alphabet
		if (popSAlpha == null)
			throw new NullPointerException("Could not add transition! The specified element to be popped from the stack alphabet cannot be null.");
		if (popSAlpha != "" && !this.stackAlpha.isMember(popSAlpha)){
			System.out.println("Could not add transition! The specified element to be popped from the stack is not a member " +
					"of the stack alphabet.");
			System.exit(0);
		}
		//check if newState is out of bounds
		if (newState < 0 || newState >= this.states.length)
			throw new ArrayIndexOutOfBoundsException("Could not add transition! New state must not be less than zero " +
					"or greater than or equal to the amount of states.");
		//check if specified element to be popped is null or if it is not a member of the stack alphabet
		if (pushSAlpha == null)
			throw new NullPointerException("Could not add transition! The specified element to be pushed onto the stack alphabet cannot be null.");
		if (pushSAlpha != "" && !this.stackAlpha.isMember(pushSAlpha)){
			System.out.println("Could not add transition! The specified element to be pushed onto the stack is not a member " +
					"of the stack alphabet.");
			System.exit(0);
		}
		//add the transition to the position corresponding to currentState in the states set
		//create a new MySet to hold the transition
		java.util.ArrayList<Object> temp = new java.util.ArrayList<Object>();
		temp.add(terminal); temp.add(popSAlpha); temp.add(newState); temp.add(pushSAlpha);
		this.states[currentState].add(new Object[]{temp});
	}
	
	/** convertGrammar(Grammar g):
	 * 		Converts a specified grammar to an equivalent Automaton. 
	 * 		If the specified grammar is not in GNF, conversion might take a really long time
	 * 		for all the rules that are to be generated when the specified grammar is copied and converted to GNF.
	 * 		Giving a specified grammar in CNF will also allow for conversion to an Automaton and can also be much
	 * 		faster since there is no need for a conversion to GNF. */
	public void convertGrammar(Grammar grammar){
		//check for null object
		if (grammar == null)
			throw new NullPointerException("Could not convert grammar. Grammar object cannot be null.");
		Grammar g = new Grammar(grammar); //create a copy of the specified Grammar object
		//convert grammar to GNF if not already
		if (!g.isGNF()) g.toGNF();
		//construct the automaton data fields from the grammar's data fields
		setStates(2);
		setTerminals(g.getTerminals());
		setStackAlpha(g.getNonTerminals());
		setFinalStates(new int[]{1});
		//get automaton's transitions from the grammar's rules
		Rule[] temp = g.getRules();
		int prev = 0, last = 2; //keeps track of the states being transitioned from and to
		//get the transitions for the starting rule
		for (int i = 0; i < temp[0].getNumOfRules(); i++){
			StringBuilder tempR = new StringBuilder(temp[0].ruleAt(i));
			if (tempR.length() == 0){ //if start symbol has lambda rule then add lambda transition
				addTransition(0, "", "", 1, "");
				continue;
			}
			if (tempR.length() == 1){ //if the rule only has a length of 1 then it is only a terminal
				addTransition(0, tempR.toString(), "", 1, "");
				continue;
			}
			int offset = 0;
			String tempT = new String();
			//find the terminal at this rules position 0
			for (int y = 0; y < this.terminals.getSize(); y++){
				int newOffset = hasString(tempR.toString(), (String)this.terminals.get(y), 0);
				if (newOffset > offset) {
					offset = newOffset;
					tempT = (String)this.terminals.get(y);
				}
			}
			tempR.delete(0, offset); //remove the terminal to only contain the non-terminals to be pushed onto the stack
			//create an array of the non-terminals left after the first terminal has been removed
			java.util.ArrayList<String> var = new java.util.ArrayList<String>();
			for (int y = 0; y < tempR.length();){
				offset = 0;
				String tempVar = new String();
				//find which terminal is at this rules position 0
				for (int k = 0; k < this.stackAlpha.getSize(); k++){
					int newOffset = hasString(tempR.toString(), (String)this.stackAlpha.get(k), 0);
					if (newOffset > offset) {
						offset = newOffset;
						tempVar = (String)this.stackAlpha.get(k);
					}
				}
				tempR.delete(0, offset);
				var.add(tempVar);
			}
			//add the transitions
			if(var.size() == 1){
				addTransition(0, tempT, "", 1, var.get(0)); //add a transition from the start state 0 to the final state 1
				continue; //once transition is added go to next rule to be evaluated
			}
			addToStates(var.size()-1); //add more states for the non-terminals of this rule
			addTransition(0, tempT, "", last, var.get(var.size()-1)); //add the transition to the last state created
			prev = last;
			last++;
			for (int y = var.size()-2; y >= 1; y--){ //add transitions for the rest of the non-terminals
				addTransition(prev, "", "", last, var.get(y));
				prev = last;
				last++;
			}
			addTransition(prev, "", "", 1, var.get(0)); //add the last transition for this rule and have it transition to the final state 1
		}
		
		//cycle through the Rule objects after the starting rule to create the rest of the transitions
		for (int i = 0; i < temp.length; i++){
			//cycle through the rules for each Rule object
			for (int y = 0; y < temp[i].getNumOfRules(); y++){
				StringBuilder tempR = new StringBuilder(temp[i].ruleAt(y));
				if (tempR.length() == 1){ //if the rule only has a length of 1 then it is only a terminal
					if (temp[i].getSymbol() == g.getStartSymbol()){
						addTransition(1, tempR.toString(), "", 1, "");
						continue;
					}
					addTransition(1, tempR.toString(), temp[i].getSymbol(), 1, "");
					continue;
				}
				int offset = 0;
				String tempT = new String();
				//find the terminal at this rules position 0
				for (int k = 0; k < this.terminals.getSize(); k++){
					int newOffset = hasString(tempR.toString(), (String)this.terminals.get(k), 0);
					if (newOffset > offset) {
						offset = newOffset;
						tempT = (String)this.terminals.get(k);
					}
				}
				tempR.delete(0, offset); //remove the terminal to only contain the non-terminals to be pushed onto the stack
				//create an array of the non-terminals left after the first terminal has been removed
				java.util.ArrayList<String> var = new java.util.ArrayList<String>();
				for (int k = 0; k < tempR.length();){
					offset = 0;
					String tempVar = new String();
					//find which terminal is at this rules position 0
					for (int x = 0; x < this.stackAlpha.getSize(); x++){
						int newOffset = hasString(tempR.toString(), (String)this.stackAlpha.get(x), 0);
						if (newOffset > offset) {
							offset = newOffset;
							tempVar = (String)this.stackAlpha.get(x);
						}
					}
					tempR.delete(0, offset);
					var.add(tempVar);
				}
				//add the transitions
				if(var.size() == 1){
					addTransition(1, tempT, temp[i].getSymbol(), 1, var.get(0)); //add a transition from the start state 0 to the final state 1
					continue; //once transition is added go to next rule to be evaluated
				}
				addToStates(var.size()-1); //add more states for the non-terminals of this rule
				addTransition(1, tempT, temp[i].getSymbol(), last, var.get(var.size()-1)); //add the transition to the last state created
				prev = last;
				last++;
				for (int k = var.size()-2; k >= 1; k--){ //add transitions for the rest of the non-terminals
					addTransition(prev, "", "", last, var.get(k));
					prev = last;
					last++;
				}
				addTransition(prev, "", "", 1, var.get(0)); //add the last transition for this rule and have it transition to the final state 1
			}
		}
		this.stackAlpha = this.stackAlpha.difference(new MySet(new Object[]{g.getStartSymbol()}));
	}
	
	/** Determines if a specified string is a apart of a target string starting from
	 *  some offset. If specified string is apart of target string returns a integer
	 *  value to specify the offset from the specified offset to where the specified
	 *  string ends */
	private int hasString(String target, String str, int offset){
		int offset1 = 0, i = offset, y = 0;
		while(i < target.length() && y < str.length()){
			//if any character is found to not be equivalent return 0 to indicate the
			//specified string is not apart of the target string starting from the offset
			if (((Character)str.charAt(y)).compareTo((Character)target.charAt(i)) != 0){
				return 0;
			}
			offset1++;
			y++;
			i++;
		}
		if (y == str.length()) return offset1; //the specified string was completely read
		return 0;
	}
	
	/** accepted(String string):
	 * 		Determines if the specified string is accepted by this automaton */
	public boolean accpeted(String string){
		run(string);
		if (this.accpeted){
			this.accpeted = false;
			return true;
		}
		this.rejected = false;
		return false;
	}
	
	/** run(String string):
	 * 		Runs through this Automatons set of states and transitions to determine if 
	 * 		a string is accepted by this automaton */
	private void run(String string){
		java.util.Stack<String> stack = new java.util.Stack<String>();
		
		//if the string to be evaluated is initially empty with the stack empty and
		//the start state being a member of the set of final states then the string is accepted
		if (string.length() == 0 && this.finalStates.isMember(this.startState)){
			this.accpeted = true;
			return;
		}
		//traverse all of the transitions at the start state to determine if the
		//string to evaluated can be accepted from the start states transitions
		for (int i = 0; i < this.states[this.startState].getSize(); i++){
			StringBuilder tempS = new StringBuilder(string); //get the string to be evaluated
			//check if the terminal for this transition is the empty string or if the terminal for the transition
			//is the same string at the initial position for tempS 
			int offset = hasString(tempS.toString(), (String)((java.util.ArrayList<Object>)this.states[this.startState].get(i)).get(0), 0);
			if (((String)((java.util.ArrayList<Object>)this.states[this.startState].get(i)).get(0)).length() == 0 ||
					offset > 0){
				//make sure the initial transitions from the start state are not trying to pop anything off the stack
				if (((String)((java.util.ArrayList<Object>)this.states[this.startState].get(i)).get(1)).length() == 0){
					//remove the terminal specified by the transition if it's not the empty string
					if (((String)((java.util.ArrayList<Object>)this.states[this.startState].get(i)).get(0)).length() != 0){
						tempS.delete(0, offset);
					}
					//push the element specified by the transition if it's not the empty string
					boolean pushed = false;
					if (((String)((java.util.ArrayList<Object>)this.states[this.startState].get(i)).get(3)).length() != 0){
						stack.push(((String)((java.util.ArrayList<Object>)this.states[this.startState].get(i)).get(3)));
						pushed = true;
					}
					int nextState = ((int)((java.util.ArrayList<Object>)this.states[this.startState].get(i)).get(2)); //get the next state to transition to
					java.util.Stack<String> tempStr = new java.util.Stack<String>(); //keeps track of how much of the string was read after for each state transition
					java.util.Stack<Integer> statesTransTo = new java.util.Stack<Integer>(); //keeps track of each state visited
					java.util.Stack<Integer> numOfStates = new java.util.Stack<Integer>(); //keeps track of the number of states read for each state
					java.util.Stack<Boolean> pushedEle = new java.util.Stack<Boolean>(); //keeps track if a element was pushed during the transition
					java.util.Stack<Object> poppedEle = new java.util.Stack<Object>(); //stores and keeps track of elements if popped during the transition
					tempStr.push(tempS.toString()); //store the contents of the string up to this point
					statesTransTo.push(nextState); //store the next state being transitioned to
					numOfStates.push(0); //store the number of the states evaluated
					pushedEle.push(pushed); //store if an element was pushed
					poppedEle.push(false); //store if an element was popped
					//check if the acceptance conditions have been met
					if (tempS.length() == 0 && stack.size() == 0 && this.finalStates.isMember(nextState)){
						this.accpeted = true;
						return;
					}
					//if all of the states transitioned to int statesTransTo get popped, then that means none of the transitions
					//evaluated reached the conditions for acceptance
					while (statesTransTo.size() != 0){
						//evaluate through the rest of the transitions to look for acceptance
						int y = numOfStates.peek();
						while (y < this.states[statesTransTo.peek()].getSize()){
							boolean transitioned = false; //determines if there was a transition
							tempS = new StringBuilder(tempStr.peek()); //get the string to be evaluated
							//check if the terminal for this transition is the empty string or if the terminal for the transition
							//is the same string at the initial position for tempS
							offset = hasString(tempS.toString(), (String)((java.util.ArrayList<Object>)this.states[statesTransTo.peek()].get(y)).get(0), 0);
							if (((String)((java.util.ArrayList<Object>)this.states[statesTransTo.peek()].get(y)).get(0)).length() == 0 ||
									offset > 0){
								//remove the terminal specified by the transition if it's not the empty string
								if (((String)((java.util.ArrayList<Object>)this.states[statesTransTo.peek()].get(y)).get(0)).length() != 0){
									tempS.delete(0, offset);
								}
								//pop the element specified by the transition if it's not the empty string
								boolean popped = false;
								if (((String)((java.util.ArrayList<Object>)this.states[statesTransTo.peek()].get(y)).get(1)).length() != 0){
									//if the stack size is empty or the top stack element is not the element specified by the transition
									//then nothing can be popped so this must be the wrong transition, so go to next transition to be evaluated
									if (stack.size() == 0 || 
											!stack.peek().equals(((String)((java.util.ArrayList<Object>)this.states[statesTransTo.peek()].get(y)).get(1)))){
										y++;
										continue;
									}
									poppedEle.push(stack.pop()); //pop the element from the stack then push it into poppedEle to be stored
									popped = true;
								}
								//push the element specified by the transition if it's not the empty string
								pushed = false;
								if (((String)((java.util.ArrayList<Object>)this.states[statesTransTo.peek()].get(y)).get(3)).length() != 0){
									stack.push(((String)((java.util.ArrayList<Object>)this.states[statesTransTo.peek()].get(y)).get(3)));
									pushed = true;
								}
								nextState = ((int)((java.util.ArrayList<Object>)this.states[statesTransTo.peek()].get(y)).get(2)); //get the next state to transition to
								tempStr.push(tempS.toString()); //store the contents of the string up to this point
								statesTransTo.push(nextState); //store the next state being transitioned to
								numOfStates.pop(); numOfStates.push(++y); //store next state to be evaluated if the new transition fails and backtracks
								numOfStates.push(0); //push the state to be evaluated for the new transition
								pushedEle.push(pushed); //store if an element was pushed
								poppedEle.push(popped); //store if an element was popped
								//check if the acceptance conditions have been met
								if (tempS.length() == 0 && stack.size() == 0 && this.finalStates.isMember(nextState)){
									this.accpeted = true;
									return;
								}
								y = 0; //set y to 0 for the number of transitions evaluated for this after-transition state
								transitioned = true; //a transition occurred
							}
							if (!transitioned) y++;
						}
						//backtrack if a states transitions never produced transitions that meet the acceptance conditions
						//then go search again for transitions to meet the acceptance conditions
						tempStr.pop(); //remove the string that was stored for the transition to get to this state
						statesTransTo.pop(); //remove the state that was stored to go back to the state before the transition
						numOfStates.pop(); //remove the number of states that were evaluated for this state; go back to the pre-transition state's number of evaluated states
						if (pushedEle.peek()) stack.pop(); //if there was a element pushed on the stack for the earlier transition then pop it from the stack
						pushedEle.pop(); //remove the element to get the pre-transition state's pushed value
						if ((boolean)poppedEle.peek() == true){ //if there was an element popped from the transition then that element must be pushed back on the stack
							poppedEle.pop(); //remove the truth value
							stack.push((String)poppedEle.peek()); //push the removed element back onto the top of the stack
						}
						poppedEle.pop(); //removes either a false value or the element that was popped from the stack
					}
				}
			}
		}
		
		//if every transition has been evaluated
		this.rejected = true;
	}
	
	/** toString():
	 * 		Returns the string representation of this string */
	public String toString(){
		String output = "Number of States: " + this.states.length;
		output += "\nTerminals: \n";
		output += this.terminals.toString();
		output += "\nStack Alphabet: \n";
		output += this.stackAlpha.toString();
		output += "\nStart State: " + this.startState;
		output += "\nFinal States: \n";
		output += this.finalStates.toString();
		output += "\nTransitions: \n";
		for (int i = 0; i < this.states.length; i++){
			output += "State " + i + " transitions:\n";
			output += this.states[i].toString() + "\n";
		}
		return output;
	}
	
	//public static void main(String[] args){
		/*Automaton a = new Automaton();
		a.setStates(2);
		a.setTerminals(new String[]{"a", "b"});
		a.setStackAlpha(new String[]{"A"});
		a.setFinalStates(new int[]{0,1});
		a.addTransition(0, "a", "", 0, "A");
		a.addTransition(0, "b", "A", 1, "");
		a.addTransition(1, "b", "A", 1, "");
		System.out.println(a.toString());
		a.addSAlpha("B");
		a.addSAlpha("C");
		a.addTerminal("c");
		a.addTerminal("d");
		a.addToStates(2);
		System.out.println(a.toString());*/
		
		/*String[] nonT = {"S", "A", "B"};
		String[] term = {"a", "b"};
		Rule[] r = new Rule[3];
		r[0] = new Rule("S", new String[]{"aAB", "aB"});
		r[1] = new Rule("A", new String[]{"aAB", "aB"});
		r[2] = new Rule("B", new String[]{"b"});
		String startS = "S";
		Grammar g = new Grammar(nonT, term, r, startS);
		g.setGNF(true);
		Automaton a = new Automaton();
		a.convertGrammar(g);
		System.out.println(a.toString());
		//a.accpeted("b");
		System.out.println("Accept \"aaabbb\": " + a.accpeted("aaabbb"));
		System.out.println("Accept \"a\": " + a.accpeted("a"));
		System.out.println("Accept \"b\": " + a.accpeted("b"));
		System.out.println("Accept \"ab\": " + a.accpeted("ab"));
		System.out.println("Accept \"abb\": " + a.accpeted("abb"));*/
		
		/*String[] nonT = {"S'", "S", "B", "T", "A", "C", "Z"};
		String[] term = {"a", "b", "c"};
		Rule[] rules = new Rule[7];
		rules[0] = new Rule("S'", new String[]{"ST", "SA", "AB", "a"});
		rules[1] = new Rule("S", new String[]{"ABZ", "aZ", "AB", "a"});
		rules[2] = new Rule("B", new String[]{"CB", "b"});
		rules[3] = new Rule("T", new String[]{"AB"});
		rules[4] = new Rule("A", new String[]{"a"});
		rules[5] = new Rule("C", new String[]{"b"});
		rules[6] = new Rule("Z", new String[]{"TZ", "AZ", "T", "A"});
		String startSym = "S'";
		Grammar g = new Grammar(nonT, term, rules, startSym);
		System.out.println(g.toString() + "\n");
		Automaton a = new Automaton();
		a.convertGrammar(g);
		System.out.println(a.toString() + "\n");
		System.out.println("Accept \"abaaba\": " + a.accpeted("abaaba"));
		System.out.println("Accept \"a\": " + a.accpeted("a"));
		System.out.println("Accept \"aba\": " + a.accpeted("aba"));
		System.out.println("Accept \"bb\": " + a.accpeted("bb"));
		System.out.println("Accept \"abb\": " + a.accpeted("abb"));
		System.out.println("Accept \"b\": " + a.accpeted("b"));*/
		
		/*String[] nonT = {"<F>", "<E>", "<T>", "<n>", "<v>"};
		String[] term = {"+", "-", "*", "/", "(", ")", "0", "1", "2", "3", "4", "5", "6", "7", "8", "9",
				"a", "b", "c", "d", "e", "f", "g", "h", "i", "j", "k", "l", "m", "n", "o", "p", "q", "r", "s", "t", "u", "v", "w", "x", "y", "z",
				"A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K", "L", "M", "N", "O", "P", "Q", "R", "S", "T", "U", "V", "W", "X", "Y", "Z"};
		Rule[] r = new Rule[5];
		r[0] = new Rule("<E>", new String[]{"<E>+<T>", "<E>-<T>", "<T>"});
		r[1] = new Rule("<F>", new String[]{"<n>", "(<E>)", "<v>"});
		r[2] = new Rule("<T>", new String[]{"<T>*<F>", "<T>/<F>", "<F>"});
		r[3] = new Rule("<n>", new String[]{"0", "1", "2", "3", "4", "5", "6", "7", "8", "9"});
		r[4] = new Rule("<v>", new String[]{"a", "b", "c", "d", "e", "f", "g", "h", "i", "j", "k", "l", "m", "n", "o", "p", "q", "r", "s", "t", "u", "v", "w", "x", "y", "z",
				"A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K", "L", "M", "N", "O", "P", "Q", "R", "S", "T", "U", "V", "W", "X", "Y", "Z"});
		String startS = "<E>";
		Grammar g = new Grammar(nonT, term, r, startS);
		System.out.println(g.toString() + "\n");
		g.toGNF();
		System.out.println(g.toString() + "\n");
		Automaton a = new Automaton();
		a.convertGrammar(g);
		System.out.println(a.toString() + "\n");*/
		//System.out.println("Accept \"1+1\": " + a.accpeted("1+1"));
		//System.out.println("Accept \"((2*2)+1)*8/2\": " + a.accpeted("((2*2)+1)*8/2"));
		//System.out.println("Accept \"a/v\": " + a.accpeted("a/v"));
		//System.out.println("Accept \"11\": " + a.accpeted("11"));
		//System.out.println("Accept \"132/15\": " + a.accpeted("132/15"));
		//System.out.println("Accept \"13v\": " + a.accpeted("13v"));
		//System.out.println("Accept \"300x*400y\": " + a.accpeted("300x*400y"));
		//System.out.println("Accept \"((900/300)*2+5/2)/(55*3x)\": " + a.accpeted("((900/300)*2+5/2)/(55*3x)"));
		//System.out.println("Accept \"(xyz/abc)*lmnop\": " + a.accpeted("(xyz/abc)*lmnop"));
		//System.out.println("Accept \"(x*y*z/a*b*c)*l*m*n*o*p\": " + a.accpeted("(x*y*z/a*b*c)*l*m*n*o*p"));
		//System.out.println("Accept \"((1+1)\": " + a.accpeted("((1+1)"));
		//System.out.println("Accept \"m*/Y+-k\": " + a.accpeted("m*/Y+-k"));
		//System.out.println("Accept \"+\": " + a.accpeted("+"));
		//System.out.println("Accept \"v/1)\": " + a.accpeted("v/1)"));
	//}
}

