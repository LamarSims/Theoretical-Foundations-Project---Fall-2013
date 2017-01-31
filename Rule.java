package theoreticalFoundations;

public class Rule {
	//declare and initialize data fields
	private String symbol;
	private String[] rules;
	private int numOfRules;
	
	/** Construct a Rules object */
	public Rule(){
		this.symbol = new String();
		this.rules = new String[1];
	}
	
	/** Construct a Rules object with specified symbol and rules */
	public Rule(String symbol, String[] rules){
		//check for null object
		if (symbol == null && rules == null){
			System.out.println("Error! Could not construct a Rules object. Symbol and rules cannot be null!");
			System.exit(0);
		}
		this.symbol = symbol;
		//copy specified rules to this objects rules
		this.rules = new String[rules.length];
		for (int i = 0; i < rules.length; i++){
			add(rules[i]);
		}
	}
	
	/** Copies a specified Rules object */
	public Rule(Rule rules){
		//check for null object
		if (rules == null){
			System.out.println("Error! Could not copy object. Specified Rules object cannot be null!");
			System.exit(0);
		}
		//copy information
		this.symbol = rules.symbol;
		this.rules = rules.getRules();
		this.numOfRules = rules.numOfRules;
	}
	
	/** getSymbol():
	 * 		Returns a copy of the symbol for this Rules object */
	public String getSymbol(){
		return this.symbol;
	}
	
	/** getRules():
	 * 		Returns a copy of the rules for this Rules object */
	public String[] getRules(){
		String[] temp = new String[this.numOfRules];
		for (int i = 0; i < this.numOfRules; i++){
			temp[i] = this.rules[i];
		}
		return temp;
	}
	
	/** getNumOfRules():
	 * 		Returns a copy of the number of rules for this Rules object */
	public int getNumOfRules(){
		return this.numOfRules;
	}
	
	/** setSymbol(String symbol):
	 * 		Set symbol of this Rules object */
	public void setSymbol(String symbol){
		//check for null object
		if (symbol == null){
			System.out.println("Error setSymbol(String symbol)! Specified symbol cannot be null!");
			System.exit(0);
		}
		this.symbol = symbol;
	}
	
	/** setRules(String[] rules):
	 * 		Set rules of this Rules object*/
	public void setRules(String[] rules){
		//check for null object
		if (rules == null){
			System.out.println("Error setRules(String[] rules)! Specified rules cannot be null!");
			System.exit(0);
		}
		for (int i = 0; i < rules.length; i++){
			add(rules[i]);
		}
	}
	
	/** add(String rule):
	 * 		Add a rule to this Rules object */
	public void add(String rule){
		//check for null object
		if (rule == null){
			System.out.println("Error add(String rule)! Cannot add null object!");
			return;
		}
		//check for duplicate rules
		if (isRule(rule)){
			//System.out.println("Cannot have duplicate rules.");
			return;
		}
		//check for overflow
		if (this.numOfRules == this.rules.length){
			//create temp array with greater capacity and copy original values to it
			String[] temp = new String[this.rules.length * 2];
			for (int i = 0; i < this.rules.length; i++){
				temp[i] = this.rules[i];
			}
			//copy temp to rules
			this.rules = temp;
		}
		//add the new rule and increment the number of rules
		this.rules[this.numOfRules++] = rule;
	}
	
	/** add(String[] rules):
	 * 		 Add rules to this Rules object */
	public void add(String[] rules){
		//check for null object
		if (rules == null){
			System.out.println("Error add(String[] rules)! Specified object cannot be null!");
			System.exit(0);
		}
		//add rules
		for (int i = 0; i < rules.length; i++){
			add(rules[i]);
		}
	}
	
	/** remove(String rule):
	 * 		Removes a specified rule from this Rules object */
	public void remove(String rule){
		//check for null object and zero rules
		if (rule == null){
			System.out.println("Error remove(String rule)! Specfied object cannot be null!");
			System.exit(0);
		}
		
		//find rule to be removed and remove it
		for (int i = 0; i < this.numOfRules; i++){
			if (rule.equals(this.rules[i])){
				//put the last rule in the place of the rule being removed
				this.rules[i] = this.rules[--this.numOfRules];
				this.rules[this.numOfRules] = null; //set the last rule to be null
				return;
			}
		}
	}
	
	/** remove(int index)
	 * 		Removes a rule at a specified index */
	public void remove(int index){
		if (index >= this.numOfRules) return;
		//put the last rule in the place of the rule being removed
		this.rules[index] = this.rules[--this.numOfRules];
		this.rules[this.numOfRules] = null; //set the last rule to be null
	}
	
	/** isRule(String rule):
	 * 		Determines if the specified string is a part of the rules */
	public boolean isRule(String rule){
		for (int i = 0; i < this.numOfRules; i++){
			if (rule.equals(this.rules[i])) return true;
		}
		return false;
	}
	
	/** ruleAt(int index):
	 * 		Returns the rule at a specified index */
	public String ruleAt(int index){
		return this.rules[index];
	}
	
	/** toString():
	 * 		String representation of this Rule object */
	public String toString(){
		String output = new String();
		int i = 0;
		output += "Rule " + this.symbol + ":\n\t>";
		while (i < this.numOfRules){
			output += ((i + 1) == this.numOfRules) ? (this.rules[i] == "") ? "lambda" : this.rules[i] : (this.rules[i] == "") ? "lambda | " : this.rules[i] + " | ";
			i++;
			if (i % 10 == 0) output += "\n\t>";
		}
		return output;
	}
	
	/*public static void main(String[] args){
		String[] nonT = {"A", "B", "C", "D", "E", "F", "$", "_"};
		String[] t = {"a", "b", "x", "y", "z"};
		Rule r = new Rule();
		r.setSymbol("A");
		r.setRules(new String[]{"a", "b", "aBB", "bCC"});
		String[] temp = r.getRules();
		for (int i = 0; i < temp.length; i++){
			System.out.print(temp[i] + " ");
		}
		r.add("41");
		System.out.println("\n" + r.toString());
		r.add(t);
		System.out.println("\n" + r.toString());
		r.add(nonT);
		System.out.println("\n" + r.toString());
		Rule r1 = new Rule(r);
		r1.setSymbol("B");
		System.out.println("\n" + r1.toString());
		r1.remove("bCC");
		r1.remove("y");
		System.out.println("\n" + r1.toString());
		r1.remove(4);
		r1.remove(1);
		System.out.println("\n" + r1.toString()); 
		System.out.println("\n" + r.toString());
		
	}*/
}
