package theoreticalFoundations;
/*Lamar A. Sims
 * November 19, 2013*/

public class Grammar {
	//declare and initialize data fields
	private MySet nonTerminals;
	private MySet terminals;
	private MySet rules;
	private String startSymbol;
	private boolean isNonContrac;
	private boolean hasChain;
	private boolean hasUslessSym;
	private boolean CNF;
	private boolean DLR;
	private boolean GNF;
	
	/** Construct a MyGrammar object */
	public Grammar(){
		this.nonTerminals = new MySet();
		this.terminals = new MySet();
		this.rules = new MySet();
		this.startSymbol = "";
	}
	
	/** Construct a MyGrammar object with specified set of nonTerminals(Symbols),
	 * terminals(alphabet), rules, and the startSymbol */
	public Grammar(MySet nonTerminals, MySet terminals, Rule[] rules, String startSymbol){
		//check for null objects
		if (nonTerminals == null || terminals == null || rules == null || startSymbol == null)
			throw new NullPointerException("Error! Could not construct Grammar object. None of the specified objects can be null!");
		//set the non-terminals, terminals, rules, and start symbol
		setNonTerminals(nonTerminals);
		setTerminals(terminals);
		setRules(rules);
		setStartSymbol(startSymbol);
	}
	
	/** Construct a MyGrammar object with specified set of nonTerminals(Symbols),
	 * terminals(alphabet), rules, and the startSymbol */
	public Grammar(String[] nonTerminals, String[] terminals, Rule[] rules, String startSymbol){
		this();
		//check for null objects
		if (nonTerminals == null || terminals == null || rules == null || startSymbol == null)
			throw new NullPointerException("Error! Could not construct Grammar object. None of the specified objects can be null!");
		//set the non-terminals, terminals, rules, and start symbol
		setNonTerminals(nonTerminals);
		setTerminals(terminals);
		setRules(rules);
		setStartSymbol(startSymbol);
	}
	
	/** Copies a specified Grammar object */
	public Grammar(Grammar g){
		//check for null object
		if (g == null)
			throw new NullPointerException("Could not copy Grammar object. Specified object cannot be null!");
		//copy information
		this.nonTerminals = new MySet(g.nonTerminals);
		this.terminals = new MySet(g.terminals);
		this.rules = new MySet(g.rules);
		this.startSymbol = g.startSymbol;
		this.isNonContrac = g.isNonContrac;
		this.hasChain = g.hasChain;
		this.hasUslessSym = g.hasUslessSym;
		this.DLR = g.DLR;
		this.CNF = g.CNF;
		this.GNF = g.GNF;
	}
	
	/** getNonTerminals():
	 * 		Returns a copy of the nonTerminals */
	public String[] getNonTerminals(){
		Object[] o = this.nonTerminals.getElements();
		String[] temp = new String[o.length];
		for (int i = 0; i < o.length; i++){
			temp[i] = (String)o[i];
		}
		return temp;
	}
	
	/** getTerminals():
	 * 		Returns a copy of the terminals */
	public String[] getTerminals(){
		Object[] o = this.terminals.getElements();
		String[] temp = new String[o.length];
		for (int i = 0; i < o.length; i++){
			temp[i] = (String)o[i];
		}
		return temp;
	}
	
	/** getRules():
	 * 		Returns a copy of the rules */
	public Rule[] getRules(){
		Object[] o = this.rules.getElements();
		Rule[] temp = new Rule[o.length];
		for (int i = 0; i < o.length; i++){
			temp[i] = (Rule)o[i];
		}
		return temp;
	}
	
	/** getStartSymbol():
	 * 		Returns a copy of the start symbol */
	public String getStartSymbol(){
		return this.startSymbol;
	}
	
	/** isNonContrac():
	 * 		Returns true if lambda rules have been removed from this Grammar */
	public boolean isNonContrac(){
		return this.isNonContrac;
	}
	
	/** hasChain():
	 * 		Returns true if chain rules have been removed from this Grammar */
	public boolean hasChain(){
		return this.hasChain;
	}
	
	/** noUselessSym():
	 * 		Returns true if useless symbols have been removed from this Grammar */
	public boolean noUselessSym(){
		return this.hasUslessSym;
	}
	
	/** isCNF():
	 * 		Returns true if this Grammar is CNF */
	public boolean isCNF(){
		return this.CNF;
	}
	
	/** isDLR():
	 * 		Returns true if this Grammar had direct left recursion removed */
	public boolean isDLR(){
		return this.DLR;
	}
	
	/** isGNF():
	 * 		Returns true if this Grammar is GNF */
	public boolean isGNF(){
		return this.GNF;
	}
	
	/** setNonContrac():
	 * 		Can set whether the grammar given is already non-contracting or not, 
	 * 		initially set as false */
	public void setNonContrac(boolean b){
		this.isNonContrac = b;
	}
	
	/** setChain():
	 * 		Can set whether the grammar given already has no chain rules, initially set as false */
	public void setChain(boolean b){
		this.hasChain = b;
	}
	
	/** setUselessSym():
	 * 		Can set whether the grammar given already has no useless symbol, initially set as false */
	public void setUselessSym(boolean b){
		this.hasUslessSym = b;
	}
	
	/** setCNF():
	 * 		Can set whether the grammar given is already in CNF, initially set as false */
	public void setCNF(boolean b){
		this.CNF = b;
	}
	
	/** setDLR():
	 * 		Can set whether the grammar already has no direct left recursion, initially set as false */
	public void setDLR(boolean b){
		this.DLR = b;
	}
	
	/** setGNF():
	 * 		Can set whether the grammar given is already in GNF, initially set as false */
	public void setGNF(boolean b){
		this.GNF = b;
	}
	
	/** setNonTerminals(MySet nonTerminals):
	 * 		Sets the nonTerminals of this Grammar */
	public void setNonTerminals(MySet nonTerminals){
		//check for null object
		if (nonTerminals == null)
			throw new NullPointerException("Error in setNonTerminals(MySet nonTerminals)! Specified ojbect cannot be null.");
		
		//make sure the nonTerminals specified are valid 
		//and are also not members of the terminals if they are set
		try{
			for (int i = 0; i < nonTerminals.getSize(); i++){
				//make sure the first character is valid for every nonTerminal
				if ((String)nonTerminals.get(i) == null || (String)nonTerminals.get(i) == ""){
					System.out.println("Error! Grammar object could not be constructed. Every non-terminal must start with " +
							"'$', '_', or a-z and A-Z.");
					System.exit(0);
				}
				if (((String)nonTerminals.get(i)).charAt(0) != '$' && ((String)nonTerminals.get(i)).charAt(0) != '_' &&
						!(((String)nonTerminals.get(i)).charAt(0) >= 65 && ((String)nonTerminals.get(i)).charAt(0) <= 90) &&
						!(((String)nonTerminals.get(i)).charAt(0) >= 97 && ((String)nonTerminals.get(i)).charAt(0) <= 122)){
					System.out.println("Error! Grammar object could not be constructed. Every non-terminal must start with " +
							"'$', '_', or a-z and A-Z.");
					System.exit(0);
				}
			}
			if (MySet.intersection(this.terminals, nonTerminals).getSize() != 0){//throws null pointer exception if terminals aren't set
				System.out.println("Error! Grammar object could not be constructed. Non-terminals cannot also be a terminal.");
				System.exit(0);
			}
		}catch(NullPointerException nex){
		}
		//set the specified nonTerminals
		this.nonTerminals = new MySet(nonTerminals);
	}
	
	/** setNonTerminals(String[] nonTerminals):
	 * 		Sets the nonTerminals of this Grammar */
	public void setNonTerminals(String[] nonTerminals){
		//check for null object
		if (nonTerminals == null)
			throw new NullPointerException("Error in setNonTerminals(MySet nonTerminals)! Specified ojbect cannot be null.");
		
		//make sure the nonTerminals specified are valid 
		//and are also not members of the terminals if they are set
		try{
			for (int i = 0; i < nonTerminals.length; i++){
				//make sure the first character is valid for every nonTerminal
				if (nonTerminals[i] == null || nonTerminals[i] == ""){
					System.out.println("Error! Grammar object could not be constructed. Every non-terminal must start with " +
							"'$', '_', or a-z and A-Z.");
					System.exit(0);
				}
				/*if (nonTerminals[i].charAt(0) != '$' && nonTerminals[i].charAt(0) != '_' &&
						!(nonTerminals[i].charAt(0) >= 65 && nonTerminals[i].charAt(0) <= 90) &&
						!(nonTerminals[i].charAt(0) >= 97 && nonTerminals[i].charAt(0) <= 122)){
					System.out.println("Error! Grammar object could not be constructed. Every non-terminal must start with " +
							"'$', '_', or a-z and A-Z.");
					System.exit(0);
				}*/
			}
			for (int i = 0; i < nonTerminals.length; i++){
				if (this.terminals.isMember(nonTerminals[i])){//throws null pointer exception if terminals aren't set
					System.out.println("Error! Grammar object could not be constructed. Non-terminals cannot also be a terminal.");
					System.exit(0);
				}
			}
		}catch (NullPointerException nex){
		}
		//add the specified nonTerminals
		this.nonTerminals.add(nonTerminals);
	}
	
	/** setTerminals(MySet terminals):
	 * 		Sets the terminals of this Grammar */
	public void setTerminals(MySet terminals){
		//check for null object
		if (terminals == null)
			throw new NullPointerException("Error in setTerminals(MySet terminals)! Specified ojbect cannot be null.");
		
		//make sure none of the terminals are not non-terminals, if the non-terminals have been set
		//make sure none of the specified terminals are not null or an empty string
		try{
			for (int i = 0; i < terminals.getSize(); i++){
				if ((String)terminals.get(i) == null /*|| (String)terminals.get(i) == ""*/){
					System.out.println("Error! Grammar object could not be constructed. Terminals cannot be null." /*or empty string."*/);
					System.exit(0);
				}
				//make sure the content of the terminals don't contain elements from the non-terminals
				String temp = (String)terminals.get(i);
				for (int y = 0; y < temp.length(); y++){
					for (int k = 0; k < this.nonTerminals.getSize(); k++){
						if (hasString(temp, (String)this.nonTerminals.get(k), y) != 0){
							System.out.println("Error! Grammar object could not be constructed. Terminals contents cannot " +
									"contain non-terminals.");
							System.exit(0);
						}
					}
				}
			}
			if (MySet.intersection(this.nonTerminals, terminals).getSize() != 0){//throws null pointer exception if non-terminals aren't set
				System.out.println("Error! Grammar object could not be constructed. Terminals cannot also be a non-terminal.");
				System.exit(0);
			}
		}catch(NullPointerException nex){
		}
		//add the new terminals
		this.terminals = new MySet(terminals);
	}
	
	/** setTerminals(String[] terminals):
	 * 		Sets the terminals of this Grammar */
	public void setTerminals(String[] terminals){
		//check for null object
		if (terminals == null)
			throw new NullPointerException("Error in setTerminals(String[] terminals)! Specified ojbect cannot be null.");
		
		//make sure none of the terminals are not non-terminals, if the non-terminals have been set
		//make sure none of the specified terminals are not null or an empty string
		try{
			for (int i = 0; i < terminals.length; i++){
				if (terminals[i] == null/* || terminals[i] == ""*/){
					System.out.println("Error! Grammar object could not be constructed. Terminals cannot be null" /*or empty string."*/);
					System.exit(0);
				}
				//make sure the content of the terminals don't contain elements from the non-terminals
				for (int y = 0; y < terminals[i].length(); y++){
					for (int k = 0; k < this.nonTerminals.getSize(); k++){
						if (hasString(terminals[i], (String)this.nonTerminals.get(k), y) != 0){
							System.out.println("Error! Grammar object could not be constructed. Terminals contents cannot " +
									"contain non-terminals.");
							System.exit(0);
						}
					}
				}
			}
			for (int i = 0; i < terminals.length; i++){
				if (this.nonTerminals.isMember(terminals[i])){//throws null pointer exception if non-terminals aren't set
					System.out.println("Error! Grammar object could not be constructed. Terminals cannot also be a non-terminal.");
					System.exit(0);
				}
			}
		}catch (NullPointerException nex){
		}
		//add the new terminals
		this.terminals.add(terminals);
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
	
	/** setRules(Rules[] rules):
	 * 		Sets the rules of this Grammar */
	public void setRules(Rule[] rules){
		//check for null object
		if (rules == null)
			throw new NullPointerException("Error in setRules(Rules[] rules)! Specified ojbect cannot be null.");
		
		//make sure nonTerminals have been added first
		if (this.nonTerminals.getSize() == 0){
			System.out.println("Cannot set rules of the grammar until non-terminals have been set!");
			System.exit(0);
		}
		
		//make sure the symbols of each rule is a member of the nonTerminals and
		//make sure the rules are only made up of member from the terminals and non-terminals
		for (int i = 0; i < rules.length; i++){
			if (!this.nonTerminals.isMember(rules[i].getSymbol())){
				System.out.println("Error! Could not set the rules of this Grammar object. Every rule's symbol " +
						"must be a member of the set of nonTerminals.");
				System.exit(0);
			}
			//traverse this Rule objects rules and check if the rules only contain
			//elements from the set of non-terminals and terminals
			for (int y = 0; y < rules[i].getNumOfRules(); y++){
				String str = rules[i].ruleAt(y);
				int offset;
				//cycle through content of str
				for (int k = 0; k < str.length(); k += offset){
					offset = 0;
					boolean ok = false; //initially false for each character to be checked
					//cycle through non-terminals to determine if a non-terminal is at position k and get its offset
					for (int x = 0; x < this.nonTerminals.getSize(); x++){
						int newOffset = hasString(str, (String)this.nonTerminals.get(x), k);
						if (newOffset > offset) offset = newOffset;
					}
					if (offset != 0){ //determines if non-terminal was found
						ok = true;
						continue; //if terminal made ok true then continue to next character for str
					}
					//cycle through terminals to determine if a terminal is at position k and get its offset
					for (int x = 0; x < this.terminals.getSize(); x++){
						//if string is found from terminals exit the loop
						int newOffset = hasString(str, (String)this.terminals.get(x), k);
						if (newOffset > offset) offset = newOffset;
					}
					if (offset != 0){ //determines if terminal was found
						ok = true;
					}
					//if ok is not true then the rule is not made up of elements from the
					//terminals or the non-terminals
					if (!ok){
						System.out.println("Error! Could not set the rules of this Grammar object. Every rule " +
								"must be made up of elements from the non-terminals and/or terminals.");
						System.exit(0);
					}
				}
			}
		}
		//set rules
		this.rules.add(rules);
	}
	
	/** setStartSymbol(String startSymbol):
	 * 		Sets the start symbol of this Grammar */
	public void setStartSymbol(String startSymbol){
		//check for null object
		if (startSymbol == null)
			throw new NullPointerException("Error in setStartSymbol(String startSymbol)! Specified ojbect cannot be null.");
		
		//make sure nonTerminals have been added first
		if (this.nonTerminals.getSize() == 0 || this.rules.getSize() == 0){
			System.out.println("Cannot set start symbol of the grammar until non-terminals and/or rules have been set!");
			System.exit(0);
		}
		
		//make sure the startSymbol is a member of the nonTerminals
		if (!this.nonTerminals.isMember(startSymbol)){
			System.out.println("Error! Could not set the start symbol of this Grammar object. Start symbol must be " +
					"a member of the set of nonTerminals.");
			System.exit(0);
		}
		//make sure the startSymbol also has a rule associated with it
		boolean ok = false;
		for (int i = 0; i < this.rules.getSize(); i++){
			if (startSymbol == ((Rule)this.rules.get(i)).getSymbol()){
				//put the rule with the start symbol at the front of the rules
				if (i != 0) this.rules.switchElements(this.rules.get(i), 0, i);
				ok = true;
				break;
			}
		}
		if (!ok){
			System.out.println("Error! Could not set the start symbol of this Grammar object. Start symbol must have " +
					"a rule associated with it.");
			System.exit(0);
		}
		//set start symbol
		this.startSymbol = startSymbol;
	}
	
	/** removeLambaRules():
	 * 		Removes the lambda rules from this Grammar's rules */
	public void removeLambdaRules(){
		if (this.isNonContrac) return;
		//Get the set of rules with lambda rules
		MySet nullRules = new MySet();
		boolean ok = false;
		while(!ok){
			boolean added = false;
			//cycle through the set of Rule objects
			for (int i = 0; i < this.rules.getSize(); i++){
				//cycle through the actual rules
				for (int y = 0; y < ((Rule)this.rules.get(i)).getNumOfRules(); y++){
					String tempSy = ((Rule)this.rules.get(i)).getSymbol(); //the rule's symbol
					if (nullRules.isMember(tempSy)) break; //check if rule is already apart of the set of nullRules
					String tempR = ((Rule)this.rules.get(i)).ruleAt(y); //rule being evaluated
					//get the rules with direct lambda rules
					if (tempR == ""){
						nullRules.add(tempSy); //add the rules symbol to the set of null rules
						added = true; //added is true since a new rule was added to the set of nullRules
						break; //once the rule is added exit loop to go to the next Rule object to be evaluated
					}
					//get the indirect/chain lambda rules
					boolean indirect = false;
					for (int k = 0; k < nullRules.getSize(); k++){
						int offset = 0;
						//cycle through content of the rule
						for (int x = 0; x < tempR.length(); x++){
							offset = hasString(tempR, (String)nullRules.get(k), x); //determines if a rule was found
							//if a rule is found from the set of nullRules make sure there is not a rule from the 
							//set of non-terminals that actually is the rule that should have been used
							//such as A being the rule in the nullRules, but the actual rule should have been A'
							if (offset != 0){
								boolean ok1 = false;
								//cycle through the set of nonTerminals
								for (int z = 0; z < this.nonTerminals.getSize(); z++){
									//if a new rule has been found to be more fit than the rule from the nullRules set
									if (hasString(tempR, (String)this.nonTerminals.get(z), x) > offset){
										offset = 0;
										ok1 = true;
										break;
									}
								}
								if (ok1) break;
							}
						}
						if (offset != 0){ //determine if a rule was found
							nullRules.add(tempSy); //add the rules symbol to the set of null rules
							added = true; //added is true since a new rule was added to the set of nullRules
							indirect = true;
							break; //once the rule is added exit loop to go to the next Rule object to be evaluated
						}
					}
					if (indirect) break; //once the rule is added exit loop to go to the next Rule object to be evaluated
				}
			}
			if (!added) ok = true;
		}
		
		//remove lambda rules
		//cycle through Rule objects
		for (int i = 0; i < this.rules.getSize(); i++){
			//cycle through Rule objects rules
			for (int y = ((Rule)this.rules.get(i)).getNumOfRules()-1; y >= 0; y--){
				int numOfNRules = 0; //number of nullable rules for this rule
				int offset = 0;
				String tempR = ((Rule)this.rules.get(i)).ruleAt(y);
				//cycle through contents of the rule
				for (int k = 0; k < tempR.length(); k += offset){
					offset = 0;
					//cycle through the set of nullable rules to find which one is at position k and get its offset
					for (int x = 0; x < nullRules.getSize(); x++){
						int newOffset = hasString(tempR, (String)nullRules.get(x), k); //determine if a rule was found
						if (newOffset > offset) offset = newOffset;
					}
					//if a element from the set of nullable rules is found in the contents of the rule
					//then increment the numOfNRules
					if (offset != 0){
						numOfNRules++;
					}else{
						k++; //if nullable rule wasn't found move over one
					}
				}
				if (numOfNRules != 0){
					int[][] table = nullRules.tableOfValues(numOfNRules); //contains all combinations of rules to be added when removing lambda rules
					((Rule)this.rules.get(i)).remove(y); //remove the rule at index y from this rule object
					//traverse the table to get all combinations to be added
					for (int row = 0; row < table.length; row++){
						StringBuilder tempRR = new StringBuilder(tempR); //copy of the rule
						for (int column = 0, deletions = 0; column < table[0].length; column++){
							if (table[row][column] == 0){
								int findNVar = column + 1; //determines which nullable rule needs to be replaced with lambda in the rule
								int offset1 = 0;
								for (int k = 0, x = 0; k < tempR.length(); k += offset1){
									offset1 = 0;
									//cycle through the set of nullable rules to find which one is at position k and get its offset
									for (int z = 0; z < nullRules.getSize(); z++){
										int newOffset1 = hasString(tempR, (String)nullRules.get(z), k); //determine if the rule was found
										if (newOffset1 > offset1) offset1 = newOffset1;
									}
									if (offset1 != 0){//if nullable rule is found increment x to keep count of the nullable rules found
										x++;
									}else{
										k++; //move to next position to looked at
									}
									//check if x is nullable rule that needs to be removed
									if (x == findNVar){
										tempRR.delete(k+deletions, offset1+(k+deletions));
										deletions -= offset1; //keep track of delted rules
										break; //the nullable rule to be removed has been found so exit loop
									}
								}
							}
						}
						//add the new rule to the rule object after lambda rules have been applied
						if (tempRR.length() == 0) ((Rule)this.rules.get(i)).add("");
						((Rule)this.rules.get(i)).add(tempRR.toString());
					}
				}
			}
		}
		
		//remove lambda/empty string from all rules except the start symbol
		for (int i = 0; i < this.rules.getSize(); i++){
			for (int y = 0; y < ((Rule)this.rules.get(i)).getNumOfRules(); y++){
				if (((Rule)this.rules.get(i)).getSymbol() != this.startSymbol && ((Rule)this.rules.get(i)).ruleAt(y) == ""){
					((Rule)this.rules.get(i)).remove(y);
				}
			}
		}
		this.isNonContrac = true;
	}
	
	/** removeChainRules():
	 * 		Removes chain rules from this Grammar */
	public void removeChainRules(){
		if (this.hasChain) return;
		//must remove lambda rules first if they haven't been removed
		if (!this.isNonContrac) removeLambdaRules();
		MySet[] chains = new MySet[this.rules.getSize()];
		//initialize each index as a MySet object
		for (int i = 0; i < chains.length; i++){
			chains[i] = new MySet();
		}
		//cycle through and construct the direct chains for each rule
		for (int i = 0; i < chains.length; i++){
			for (int y = 0; y < ((Rule)this.rules.get(i)).getNumOfRules(); y++){
				String tempR = ((Rule)this.rules.get(i)).ruleAt(y);
				int offset;
				//can only have one single non-terminal as a rule for it to be a chain rule
				boolean ok = false; //determines if a single non-terminal has been read
				//cycle through content of the rule
				for (int k = 0; k < tempR.length(); k += offset){
					offset = 0;
					//if number of non-terminals is equal to one but has entered this loop again then that
					//means another terminal or non-terminal is after this rule so it is not a chain rule
					if (ok || k > 0) {
						ok = false;
						break;
					}
					//cycle through the rules to check each rule's symbol
					for (int x = 0; x < this.rules.getSize(); x++){
						int newOffset = hasString(tempR, ((Rule)this.rules.get(x)).getSymbol(), k); //determines if a symbol was found
						if (newOffset > offset) offset = newOffset;
					}
					//if the first thing found was a terminal when k is at the initial position
					if (k == 0 && offset != 0){
						ok = true; //non-terminal has been found
					}
					if (offset == 0) k++; //move to next position to be evaluated
				}
				//check if there was only one terminal in the rule
				if (ok){
					chains[i].add(tempR); //add the chain rule to this chain
				}
			}
		}
		
		
		//cycle through chains and get the indirect chain rules
		for (int i = 0; i < chains.length; i++){
			MySet prev = new MySet(); //holds elements that were previously in the initial set
			do{
				MySet tempNew = chains[i].difference(prev); //holds elements that are unique to this chain
				if (tempNew.getSize() == 0) break; //exit the loop if there were no unique elements found
				prev = new MySet(chains[i]); //make prev equal to the old elements of this chain
				//cycle through each rules, which corresponds to the position of the chains, to add new elements to this chain
				for (int k = 0; k < this.rules.getSize(); k++){
					if (chains[k].getSize() == 0) continue; //go to next rule to be evaluated if it has no chain rules
					//determine if there is a indirect chain rule
					if (tempNew.isMember(((Rule)this.rules.get(k)).getSymbol())){
						chains[i] = MySet.union(chains[i], chains[k]); //add all of the indirect chains to this chain
					}
				}
			}while(!chains[i].equals(prev));
		}
		
		//remove the chains from the rules
		//cycle through the chains, the chains position corresponds to it's rules position
		for (int i = 0; i < chains.length; i++){
			//cycle through the content of each chain to remove the chain rules
			for (int y = 0; y < chains[i].getSize(); y++){
				//cycle through the rules to figure which new rules need to added to the rule
				for (int k = 0; k < this.rules.getSize(); k++){
					//if the rule to be added to is the same rule whose rules are to be added go to the next rule
					if (((Rule)this.rules.get(i)).getSymbol() == ((Rule)this.rules.get(k)).getSymbol()){
						//remove the chain rule from the rules
						((Rule)this.rules.get(i)).remove(((Rule)this.rules.get(k)).getSymbol());
						continue;
					}
					if (chains[i].get(y).equals(((Rule)this.rules.get(k)).getSymbol())){
						//once the right rule is found add all of its rules to the rule at index i
						for (int x = 0; x < ((Rule)this.rules.get(k)).getNumOfRules(); x++){
							String tempR = ((Rule)this.rules.get(k)).ruleAt(x); //rule to be added
							//check if the rule to be added is a chain rule
							if (chains[i].isMember(tempR) || tempR == "") continue; //if the rule is a chain rule then skip to the next rule
							((Rule)this.rules.get(i)).add(tempR); //add the rule
						}
						//remove the chain rule from the rules
						((Rule)this.rules.get(i)).remove(((Rule)this.rules.get(k)).getSymbol());
					}
				}
			}
		}
		this.hasChain = true;
	}
	
	/** removeUselessSym():
	 * 		Removes the useless symbols from this Grammar */
	public void removeUselessSym(){
		if (this.hasUslessSym) return;
		//Construct a set of the initial rules that have rules that derive to just terminals
		MySet term = new MySet();
		//cycle through the number of rules to check for terminal rules
		for (int i = 0; i < this.rules.getSize(); i++){
			//cycle through the the rule's rules
			for (int y = 0; y < ((Rule)this.rules.get(i)).getNumOfRules(); y++){
				String tempR = ((Rule)this.rules.get(i)).ruleAt(y);
				int offset;
				boolean ok = false;
				//cycle through the contents of the rule being looked at
				for (int k = 0; k < tempR.length(); k += offset){
					offset = 0;
					ok = false; //have ok initially false for each position being looked at
					//cycle through the set of terminals, to check for terminal rules
					for (int x = 0; x < this.terminals.getSize(); x++){
						int newOffset = hasString(tempR, (String)this.terminals.get(x), k); //determine if a terminal was read
						if (newOffset > offset) offset = newOffset;
					}
					if (offset != 0){
						ok = true; //terminal was read for this position
					}
					if (!ok) break;//if a terminal was not read exit the loop and look at the next rule
				}
				if (ok) {
					term.add(((Rule)this.rules.get(i)).getSymbol()); //add the symbol of the rule that has a terminal rule
					break; //exit the loop once one terminal rule is found
				}
			}
		}
		
		//add rules that now have rules that are made up from any combination of elements from the set term
		//and the set of terminals
		for (int i = 0; i < this.rules.getSize(); i++){
			//if the rule being looked at is already a member of the term set
			//then go to the next rule to be evaluated
			if (term.isMember(((Rule)this.rules.get(i)).getSymbol())) continue;
			//traverse this Rule objects rules and check if the rules only contain
			//elements from the term set and terminals
			for (int y = 0; y < ((Rule)this.rules.get(i)).getNumOfRules(); y++){
				String str = ((Rule)this.rules.get(i)).ruleAt(y);
				int offset;
				boolean ok = false;
				//cycle through the content of the rule
				for (int k = 0; k < str.length(); k += offset){
					offset = 0;
					ok = false; //initially false for each character to be checked
					//cycle through the term set to find if a member of the term set is at position k and get is offset
					for (int x = 0; x < term.getSize(); x++){
						//if string is found from term set exit the loop
						int newOffset = hasString(str, (String)term.get(x), k);
						if (newOffset > offset) offset = newOffset;
					}
					if (offset != 0){ //non-terminal was found if offset is not 0
						ok = true;
						continue; //if element from term made ok true then continue to next character for str
					}
					//cycle through the terminals to find if a member of the terminals is at position k and get is offset
					for (int x = 0; x < this.terminals.getSize(); x++){
						//if string is found from terminals exit the loop
						int newOffset = hasString(str, (String)this.terminals.get(x), k);
						if (newOffset > offset) offset = newOffset;
					}
					if (offset != 0) ok = true; //terminal was found if offset is not 0
					if (!ok) break; //if a terminal or element from term set was not read exit the loop and look at the next rule
				}
				if (ok){
					term.add(((Rule)this.rules.get(i)).getSymbol()); //add the symbol of the rule that is made up of terminals and/or term set
					break; //exit the loop once one rule is found
				}
			}
		}
		
		//remove all the rules that are not apart of term set
		//cycle through the rules to remove every rule that is not apart of the term set 
		for (int i = 0; i < this.rules.getSize(); i++){
			//if the rules symbol is not a member of the term set delete the rule
			if (!term.isMember(((Rule)this.rules.get(i)).getSymbol())){
				this.rules = this.rules.difference(new MySet(new Object[]{this.rules.get(i)}));
				i--; //account for the deleted rule
				continue;
			}
			//remove all of the rules whose rules have elements that are not apart of the term set
			for (int y = 0; y < ((Rule)this.rules.get(i)).getNumOfRules(); y++){
				String tempR = ((Rule)this.rules.get(i)).ruleAt(y); //rule to be evaluated
				int offset;
				//cycle through the content of the rule, to check for elements not of the term set
				for (int k = 0; k < tempR.length(); k += offset){
					offset = 0;
					int indexR = 0;
					//cycle through set of nonTerminals to compare to the rule's content
					for (int x = 0; x < this.nonTerminals.getSize(); x++){
						//find which non-terminal is to be evaluated
						int newOffset = hasString(tempR, (String)this.nonTerminals.get(x), k);
						if (newOffset > offset) {
							offset = newOffset;
							indexR = x; //get the index of the nonTerminal to be evaluated
						}
					}
					//if the rule was found to contain a non-terminal and the non-terminal
					//is not a member of the term set then delete the rule
					if (offset != 0 && !term.isMember(this.nonTerminals.get(indexR))){
						((Rule)this.rules.get(i)).remove(y);
						y--; //account for the deleted rule
						break; //exit loop once rule is deleted to evaluate the next rule
					}else if (offset != 0 && term.isMember(this.nonTerminals.get(indexR))) continue; //go to next position
					if (offset == 0) k++; //move to the next position
				}
			}
		}
		
		//construct a set of all of the reachable variables from the start symbol
		MySet reachable = new MySet();
		reachable.add(this.startSymbol);
		MySet previous = new MySet();
		do {
			MySet newVar = reachable.difference(previous); //set of variables unique to reachable
			previous = new MySet(reachable); //the previous variable of reachable
			//cycle through the new variables
			for (int i = 0; i < newVar.getSize(); i++){
				Rule temp = new Rule(); //the Rule object whose rules are to be evaluated
				//get the rule to be evaluated
				for (int y = 0; y < this.rules.getSize(); y++){
					if (newVar.get(i).equals(((Rule)this.rules.get(y)).getSymbol())){
						temp = ((Rule)this.rules.get(y));
						break; //exit loop once rule is found
					}
				}
				//cycle through the rules of temp
				for (int y = 0; y < temp.getNumOfRules(); y++){
					String tempR = temp.ruleAt(y); //get the rule to be evaluated
					int offset;
					//cycle through the contents of the rule to find the variable in the rule
					for (int k = 0; k < tempR.length(); k += offset){
						offset = 0;
						int indexR = 0;
						//cycle through the number of rules to find which rule is to be added to the reachable set
						for (int x = 0; x < this.rules.getSize(); x++){
							int newOffset = hasString(tempR, ((Rule)this.rules.get(x)).getSymbol(), k); //determine if a rule was read
							if (newOffset > offset) {
								offset = newOffset;
								indexR = x; //get the index of the rule to be evaluated
							}
						}
						if (offset != 0){ //determine if a rules symbol was found
							reachable.add(((Rule)this.rules.get(indexR)).getSymbol());
						}
						if (offset == 0) k++; //if a rule was not read move to next position and evaluate
					}
				}
			}
		}while(!reachable.equals(previous));
		
		//remove all of the rules that are not a member of the reachable set
		for (int i = 0; i < this.rules.getSize(); i++){
			if (!reachable.isMember(((Rule)this.rules.get(i)).getSymbol())){
				this.rules = this.rules.difference(new MySet(new Object[]{this.rules.get(i)}));
				i--; //account for the symbol being removed
			}
		}
		//remove all of the non-terminals that are not a member of the reachable set
		for (int i = 0; i < this.nonTerminals.getSize(); i++){
			if (!reachable.isMember(this.nonTerminals.get(i))){
				this.nonTerminals = this.nonTerminals.difference(new MySet(new Object[]{this.nonTerminals.get(i)}));
				i--; //account for the symbol being removed
			}
		}
		//remove all terminals that are not used in any of the rules
		for (int x = 0; x < this.terminals.getSize(); x++){ //cycle through each terminal
			boolean found = false;
			for (int i = 0; i < this.rules.getSize(); i++){ //cycle through the number of rules
				for (int y = 0; y < ((Rule)this.rules.get(i)).getNumOfRules(); y++){ //cycle through the Rule objects rules
					String temp = ((Rule)this.rules.get(i)).ruleAt(y); //get the rule to be evaluated
					for (int k = 0; k < temp.length(); k++){ //cycle through the content of the rule
						if (hasString(temp, (String)this.terminals.get(x), k) > 0){//if a terminal 
							found = true;
							break;
						}
					}
					if (found) break;
				}
				if (found) break;
			}
			if (!found) this.terminals = this.terminals.difference(new MySet(new Object[]{this.terminals.get(x)}));
		}
		this.hasUslessSym = true;
	}
	
	/** toCNF():
	 * 		Converts this grammar to Chomsky normal form */
	public void toCNF(){
		if (this.CNF) return;
		if (!this.isNonContrac) removeLambdaRules();
		if (!this.hasChain) removeChainRules();
		if (!this.hasUslessSym) removeUselessSym();
		int size = this.rules.getSize(); //the initial rules size
		//for every rule that has two or more non-terminals and/or terminals find 
		//all of the terminals and replace them with new non-terminals
		for (int i = 0; i < size; i++){
			//cycle through every Rule objects rules to find the terminals
			for (int y = ((Rule)this.rules.get(i)).getNumOfRules()-1; y >= 0; y--){
				if (hasTwoOrMore(((Rule)this.rules.get(i)).ruleAt(y))){
					replaceTerminals(((Rule)this.rules.get(i)), y);
				}
			}
		}
		
		//after every terminal has been replaced, for every rule that has 
		//more than two non-terminals replace two of the non-terminals
		//with new non-terminals until the rule has only two non-terminals
		for (int i = 0; i < size; i++){
			//cycle through every Rule objects rules to find the non-terminals and replace them
			for (int y = ((Rule)this.rules.get(i)).getNumOfRules()-1; y >= 0; y--){
				int count = nonTCount(((Rule)this.rules.get(i)).ruleAt(y));
				if (count != 0 && count != 2) replaceNTerminals(((Rule)this.rules.get(i)), count, y);
			}
		}
		this.CNF = true;
	}
	
	/** hasTwoOrMore(String rule):
	 * 		Determines if a rule has two or more non-terminals and/or terminals */
	private boolean hasTwoOrMore(String rule){
		int count = 0;
		int offset;
		boolean ok = false;
		//cycle through the content of the rule
		for (int k = 0; k < rule.length(); k += offset){
			offset = 0;
			ok = false; //initially false for each character to be checked
			if (count == 1) return true;
			//cycle through the non-terminals to find which non-terminal is at position k and get is offset
			for (int x = 0; x < this.nonTerminals.getSize(); x++){
				int newOffset = hasString(rule, (String)this.nonTerminals.get(x), k);
				if (newOffset > offset) offset = newOffset;
			}
			if (offset != 0){//determine if a nonTerminal was found
				ok = true;
				count++;
			}
			//if element from nonTerminals made ok true then continue to next character for rule
			if (ok) continue;
			//cycle through the terminals to find which terminal is at position k and get is offset
			for (int x = 0; x < this.terminals.getSize(); x++){
				int newOffset = hasString(rule, (String)this.terminals.get(x), k);
				if (newOffset > offset) offset = newOffset;
			}
			if (offset != 0){//determine if a terminal was found
				ok = true;
				count++;
			}
		}
		return false;
	}
	
	/** nonTCount(String rule):
	 * 		Returns how many non-terminals the specified string has */
	private int nonTCount(String rule){
		if (rule.length() == 1) return 0;
		int count = 0;
		int offset = 0;
		//cycle through the content of the rule
		for (int k = 0; k < rule.length(); k += offset){
			offset = 0;
			//cycle through the non-terminals to find which non-terminal is at position k and get is offset
			for (int x = 0; x < this.nonTerminals.getSize(); x++){
				int newOffset = hasString(rule, (String)this.nonTerminals.get(x), k);
				if (newOffset > offset) offset = newOffset;
			}
			if (offset != 0){ //determine if a non-terminal was found
				count++; //count the number of non-terminals found
			}
		}
		return count;
	}
	
	/** replaceTerminals(Rule rule, int index):
	 * 		Replaces the terminals of this rule with new non-terminals and
	 * 		and new rule for the new non-terminal */
	private void replaceTerminals(Rule rule, int index){
		StringBuilder strB = new StringBuilder(rule.ruleAt(index));
		int count = 0, count2 = 1;
		int offset;
		boolean alpha = false;
		int charNum = 65, c;
		int nonTSize = this.nonTerminals.getSize();
		//cycle through the content of the rule
		for (int k = 0; k < strB.length(); k += (count-offset)+offset){
			offset = 0;
			//cycle through the non-terminals first to determine if a non-terminal is at position k
			for (int x = 0; x < nonTSize; x++){
				int newOffset = hasString(strB.toString(), (String)this.nonTerminals.get(x), k);
				if (newOffset > offset) offset = newOffset;
			}
			if (offset > 0) {
				k += offset;
				offset = 0;
				count = 0;
				continue; //if a non-terminal was found then go to next position to be evaluated
			}
			//cycle through the terminals to find which terminal is at position k and get is offset
			for (int x = 0; x < this.terminals.getSize(); x++){
				int newOffset = hasString(strB.toString(), (String)this.terminals.get(x), k);
				if (newOffset > offset) offset = newOffset;
			}
			//if terminal is found must remove it and create a new rule to takes its place
			if (offset != 0){
				String temp = strB.substring(k, k+offset); //create a copy of the terminal to be removed from this rule
				strB.delete(k, k+offset); //delete the terminal
				String newRule = new String();
				//create a name/symbol for the new rule to be added
				c = 1;
				do{
					if (alpha){ 
						if (c > 1) {
							newRule = String.valueOf((char)charNum++);
							for (int i = 0; i < c; i++){
								newRule += "'";
							}
						}else{
							if (charNum == 65) count2++;
							newRule = String.valueOf((char)charNum++) + "'";
						}
						if (charNum == 90){
							charNum = 65;
							c++;
							count2 = 1; count2 += c;
						}
					}else{
						newRule = String.valueOf((char)charNum++);
						if (charNum == 90){
							alpha = true;
							charNum = 65;
						}
					} 
				}while(this.nonTerminals.isMember(newRule) || this.terminals.isMember(newRule));
				count = count2;
				strB.insert(k, newRule); //insert the new rule in place of the deleted terminal
				//construct a new rule with its rule being the terminal that was removed
				Rule tempR = new Rule(newRule, new String[]{temp});
				this.nonTerminals.add(newRule); //add the new symbol to the set of non-terminals
				this.rules.add(tempR); //add the rule to the set of rules
			}
		}
		rule.remove(index); //remove the old rule at the specified index
		rule.add(strB.toString()); //add the modified rule back to the Rule object
	}
	
	/** replaceNTerminals(Rule rule, int numOfNT):
	 * 		Replace the non-terminals of this rule with new rules until 
	 * 		there are only two non-terminals left in the rule */
	public void replaceNTerminals(Rule rule, int numOfNT, int index){
		StringBuilder strB = new StringBuilder(rule.ruleAt(index));
		int count;
		int offset;
		int loops = numOfNT / 2;
		boolean alpha = false;
		int charNum = 65, c = 1;
		for (int i = 0; i < loops; i++){
			offset = 0;
			count = 0;
			int tempOffset = 0;
			//find the two non-terminals to be replaced
			while (count < 2){
				//cycle through the terminals to find which terminal is at position k and get its offset
				for (int x = 0; x < this.nonTerminals.getSize(); x++){
					int newOffset = hasString(strB.toString(), (String)this.nonTerminals.get(x), offset);
					if (newOffset > tempOffset) tempOffset = newOffset;
				}
				offset += tempOffset; //get the offset of both rules together
				count++;
				tempOffset = 0;
			}
			//when the two non-terminals are found remove them and create a new rule to take their place
			String temp = strB.substring(0, offset); //create a copy of the two non-terminals to be removed from this rule
			strB.delete(0, offset); //delete the two non-terminal
			String newRule = new String(); 
			//create a name/symbol for the new rule to be added
			c = 1;
			do{
				if (alpha){ 
					if (c > 1) {
						newRule = String.valueOf((char)charNum++);
						for (int y = 0; y < c; y++){
							newRule += "'";
						}
					}else{
						newRule = String.valueOf((char)charNum++) + "'";
					}
					if (charNum == 90){
						charNum = 65;
						c++;
					}
				}else{
					newRule = String.valueOf((char)charNum++);
					if (charNum == 90){
						alpha = true;
						charNum = 65;
					}
				} 
			}while(this.nonTerminals.isMember(newRule) || this.terminals.isMember(newRule));
			strB.insert(0, newRule); //insert the new rule in place of the deleted terminal
			//construct a new rule with its rule being the terminal that was removed
			Rule tempR = new Rule(newRule, new String[]{temp});
			this.nonTerminals.add(newRule); //add the new symbol to the set of non-terminals
			this.rules.add(tempR); //add the rule to the set of rules
		}
			rule.remove(index); //remove the old rule at the specified index
			rule.add(strB.toString()); //add the modified rule back to the Rule object
	}
	
	/** removeLeftRecursion():
	 * 		Removes direct left recursion from this Grammar */
	public void removeLeftRecursion(){
		if (this.DLR) return;
		int charNum = 65, c = 0;
		boolean alpha = false;
		//cycle through the rules of each Rule object
		int initSize = this.rules.getSize(); //gets the initial number of Rule objects
		for (int i = 0; i < initSize; i++){
			MySet dlr = setOfDLR((Rule)this.rules.get(i));
			if (dlr.getSize() == 0) continue; //there are no directly left recursive rules go to next Rule object
			//create a name/symbol for the new rule to be added
			String newRule = new String();
			c = 1;
			do{
				if (alpha){ 
					if (c > 1) {
						newRule = String.valueOf((char)charNum++);
						for (int y = 0; y < c; y++){
							newRule += "'";
						}
					}else{
						newRule = String.valueOf((char)charNum++) + "'";
					}
					if (charNum == 90){
						charNum = 65;
						c++;
					}
				}else{
					newRule = String.valueOf((char)charNum++);
					if (charNum == 90){
						alpha = true;
						charNum = 65;
					}
				} 
			}while(this.nonTerminals.isMember(newRule) || this.terminals.isMember(newRule));
			this.nonTerminals.add(newRule); //add the new rule/symbol to the set of non-terminals
			//cycle through the the rules left after the dlr rules were removed and add
			//the new rule/symbol to the end of the rule
			int initRules = ((Rule)this.rules.get(i)).getNumOfRules(); //the initial number of rules this Rule object has
			for (int y = 0; y < initRules; y++){
				String tempR = ((Rule)this.rules.get(i)).ruleAt(y);
				((Rule)this.rules.get(i)).add((tempR+newRule));
			}
			//construct the new rule to be added and set it's start symbol
			Rule tempR = new Rule();
			tempR.setSymbol(newRule);
			//add all of the elements in the dlr set to the new rule, along with copies
			//of the elements with the new Rule objects symbol appended to them
			for (int y = 0; y < dlr.getSize(); y++){
				String temp = (String)dlr.get(y);
				tempR.add(temp);
				tempR.add((temp+newRule));
			}
			this.rules.add(tempR); //add the new rule to the set of rules
		}
		this.DLR = true;
	}
	
	/** setOfDLR():
	 * 		Constructs a set of the direct left recursive rules for a Rule object */
	private MySet setOfDLR(Rule rule){
		MySet dlr = new MySet();
		//cycle through the rules of this Rule object to check for dlr rules
		for (int i = rule.getNumOfRules()-1; i >= 0; i--){
			String tempR = rule.ruleAt(i); //get the rule
			int offset = hasString(tempR, (String)rule.getSymbol(), 0);
			//make sure there isn't a member of the non-terminals that actually fits in the position
			if (offset != 0){
				for (int y = 0; y < this.nonTerminals.getSize(); y++){
					if (hasString(tempR, (String)this.nonTerminals.get(y), 0) > offset){
						offset = 0; //if a member of the non-terminals if found to fit better then the initial offset is not valid
						break; //exit the loop once one member of the non-terminals is found
					}
				}
			}
			//if offset is still greater than zero then add the rule to the set, 
			//remove the rule from the rule object, and remove the dlr rule
			if (offset != 0){
				StringBuilder temp = new StringBuilder(tempR);
				temp.delete(0, offset); //delete the dlr rule
				dlr.add(temp.toString()); //add the modified rule to the dlr set
				rule.remove(tempR); //remove the rule from the Rule object
			}
		}
		return dlr;
	}
	
	/** toGNF():
	 * 		Converts the Grammar to Greibach normal form */
	public void toGNF(){
		if (this.GNF) return;
		if (!this.isDLR()) removeLeftRecursion();
		if (!this.CNF) toCNF();
		MySet prev = new MySet(); //contains the older elements
		do{
			MySet newNT = this.nonTerminals.difference(prev); //contains the newer elements
			prev = new MySet(this.nonTerminals);
			//cycle through the set newNT
			//for (int i = 0; i < newNT.getSize(); i++){
				//cycle through the Rule objects to remove the smaller value rules at position 0
				for (int y = 0; y < this.rules.getSize(); y++){
					//determine if the Rule object being evaluated is a member of the newNT set
					if (newNT.isMember(((Rule)this.rules.get(y)).getSymbol())){
						removeSRules((Rule)this.rules.get(y), y);
					}
				}
			//}
			//cycle through the set newNT
			//for (int i = 0; i < newNT.getSize(); i++){
				//cycle through the Rule objects to remove the larger value rules at position 0 and
				//until all rules start with a terminal
				for (int y = this.rules.getSize()-1; y >= 0; y--){
					//determine if the Rule object being evaluated is a member of the newNT set
					if (newNT.isMember(((Rule)this.rules.get(y)).getSymbol())){
						removeLRules((Rule)this.rules.get(y));
					}
				}
			//}
		}while(!this.nonTerminals.equals(prev));
		this.GNF = true;
	}
	
	/** removeSRules(Rule rule):
	 * 		Evaluates the rules of the Rule object, whose first symbol has a rule that
	 * 		has a smaller value than the Rule object and replaces the first symbol with
	 * 		the smaller Rule objects rules */
	private void removeSRules(Rule rule, int value){
		boolean hasDLR = false, ok = true;
		do {
			ok = true;
			for (int i = rule.getNumOfRules()-1; i >= 0; i--){
				String tempR = rule.ruleAt(i); //get the rule
				int offset = 0;
				int valueIP = 0; //value of the rule at this rule's position 0
				//find the value of the rule that is at this rule's position 0
				for (int y = 0; y < this.rules.getSize(); y++){
					int newOffset = hasString(tempR, ((Rule)this.rules.get(y)).getSymbol(), 0);
					if (newOffset > offset){
						offset = newOffset; //if a member of the rules is found at the initial position of this rule
						valueIP = y; //get the value of the rule
					}
				}
				if (offset != 0){ //if a rule was read at position 0 of this rule
					if (valueIP == value){
						setDLR(false);
						hasDLR = true; //the two value are the same meaning there is direct left recursion
					}else if(valueIP < value){
						StringBuilder temp = new StringBuilder(tempR);
						temp.delete(0, offset); //delete the rule at position 0
						rule.remove(tempR); //remove the rule from the Rule object
						//for the smaller valued rule at position 0 add all of its rules in place of it and add to this Rule object
						for (int y = 0; y < ((Rule)this.rules.get(valueIP)).getNumOfRules(); y++){
							String tempSR = ((Rule)this.rules.get(valueIP)).ruleAt(y);
							rule.add((tempSR+temp.toString()));
						}
					}
				}
			}
			if (hasDLR) {
				removeLeftRecursion(); //remove left recursion if hasDLR was set to true
			}else { //if left recursion was not found initially check to see if left recursion was added by the addition of new rules
				for (int i = rule.getNumOfRules()-1; i >= 0; i--){
					String tempR = rule.ruleAt(i); //get the rule
					int offset = 0;
					int valueIP = 0; //value of the rule at this rule's position 0
					//find the value of the rule that is at this rule's position 0
					for (int y = 0; y < this.rules.getSize(); y++){
						int newOffset = hasString(tempR, ((Rule)this.rules.get(y)).getSymbol(), 0);
						if (newOffset > offset){
							offset = newOffset; //if a member of the rules is found at the initial position of this rule
							valueIP = y; //get the value of the rule
						}
					}
					if (offset != 0){
						if (value == valueIP){ //if values are the same left recursion was found
							setDLR(false);
							removeLeftRecursion();
							break; //exit the loop was left recursion is removed
						}
					}
				}	
			}
			//check if the Rule object still has smaller valued rules to be removed
			for (int i = rule.getNumOfRules()-1; i >= 0; i--){
				String tempR = rule.ruleAt(i); //get the rule
				int offset = 0;
				int valueIP = 0; //value of the rule at this rule's position 0
				//find the value of the rule that is at this rule's position 0
				for (int y = 0; y < this.rules.getSize(); y++){
					int newOffset = hasString(tempR, ((Rule)this.rules.get(y)).getSymbol(), 0);
					if (newOffset > offset){
						offset = newOffset; //if a member of the rules is found at the initial position of this rule
						valueIP = y; //get the value of the rule
					}
				}
				if (offset != 0){
					if (value > valueIP){ //if values is greater than valueIP then a smaller valued rule was found
						ok = false; //set ok to false to loop again and remove the smaller valued rules
						break; //exit for loop
					}
				}
			}	
		} while(!ok);
	}
	
	/** removeLRules():
	 * 		Evaluates the rules of the Rule object, whose first symbol has a rule that
	 * 		has a larger value than the Rule object and replaces the first symbol with
	 * 		the larger Rule objects rules */
	private void removeLRules(Rule rule){
		for (int i = rule.getNumOfRules()-1; i >= 0; i--){
			String tempR = rule.ruleAt(i); //get the rule
			int offset = 0;
			int valueIP = 0; //value of the rule at this rule's position 0
			//find the value of the rule that is at this rule's position 0
			for (int y = 0; y < this.rules.getSize(); y++){
				int newOffset = hasString(tempR, ((Rule)this.rules.get(y)).getSymbol(), 0);
				if (newOffset > offset){
					offset = newOffset; //if a member of the rules is found at the initial position of this rule
					valueIP = y; //get the value of the rule
				}
			}
			if (offset != 0){ //if a rule was read at position 0 of this rule
				StringBuilder temp = new StringBuilder(tempR);
				temp.delete(0, offset); //delete the rule at position 0
				rule.remove(tempR); //remove the rule from the Rule object
				//for the larger valued rule at position 0 add all of its rules in place of it and add to this Rule object
				for (int y = 0; y < ((Rule)this.rules.get(valueIP)).getNumOfRules(); y++){
					String tempSR = ((Rule)this.rules.get(valueIP)).ruleAt(y);
					rule.add((tempSR+temp.toString()));
				}
			}
		}
	}
	
	/** toString():
	 * 		String representation of this Grammar object */
	public String toString(){
		String output = new String();
		output = "Non-Terminals:\n";
		output += this.nonTerminals.toString();
		output += "\nTerminals:\n";
		output += this.terminals.toString();
		output += "\nRules:\n";
		Object[] r = this.rules.getElements();
		for (int i = 0; i < r.length; i++){
			output += ((Rule)r[i]).toString() + "\n";
		}
		output += "\nStart Symbol: " + this.startSymbol;
		return output;
	}
	
	//public static void main(String[] args){
		/*String[] nonT = {"AA", "B", "B", "CCC", "DD", "E", "F", "$", "_"};
		String[] t = {"a", "b", "c", "d", "x", "y", "z"};
		Rule[] r = new Rule[8];
		Rule r1 = new Rule("AA", new String[]{"aBcd", "", "B", "F", "$", "_"});
		Rule r2 = new Rule("B", new String[]{"a", "F"});
		Rule r3 = new Rule("CCC", new String[]{"DDE", "xCCCx", "z", "y"});
		Rule r4 = new Rule("DD", new String[]{"AABCCCDD"});
		Rule r5 = new Rule("E", new String[]{"aB", "a", "bCCC"});
		Rule r6 = new Rule("F", new String[]{"b", "c", "$"});
		Rule r7 = new Rule();
		r7.setSymbol("$");
		r7.setRules(new String[]{"x", "y", "z", "_"});
		Rule r8 = new Rule();
		r8.setSymbol("_");
		r8.setRules(new String[]{"a", "b", "aB", "bCCC", "$", "_"});
		r[0] = r1;
		r[1] = r2;
		r[2] = r3;
		r[3] = r4;
		r[4] = r5;
		r[5] = r6;
		r[6] = r7;
		r[7] = r8;
		String startS = "AA";
		Grammar g = new Grammar(nonT, t, r, startS);
		System.out.println(g.toString());
		Grammar g1 = new Grammar(g);
		System.out.println("\n" + g1.toString());
		String[] nT = g.getNonTerminals();
		String[] tT = g.getTerminals();
		Rule[] rT = g.getRules();
		String sST = g.getStartSymbol();
		for (int i = 0; i < nT.length; i++){
			System.out.print(nT[i] + " ");
		}
		System.out.println();
		for (int i = 0; i < tT.length; i++){
			System.out.print(tT[i] + " ");
		}
		System.out.println();
		for (int i = 0; i < rT.length; i++){
			System.out.println(rT[i].toString());
		}
		System.out.println(sST);
		//g.removeLambdaRules();
		//g.removeChainRules();
		//g.removeUselessSym();*/
		
		/*String[] nonT = {"S", "A", "B", "C"};
		String[] term = {"a", "b", "c"};
		Rule[] rules = new Rule[4];
		rules[0] = new Rule("S", new String[]{"ACA"});
		rules[1] = new Rule("A", new String[]{"aAa", "B", "C"});
		rules[2] = new Rule("B", new String[]{"bB", "b"});
		rules[3] = new Rule("C", new String[]{"cC", ""});
		String startSym = "S";
		Grammar g = new Grammar(nonT, term, rules, startSym);
		System.out.println(g.toString() + "\n");
		g.removeLambdaRules();
		System.out.println(g.toString() + "\n");
		g.removeChainRules();
		System.out.println(g.toString() + "\n");*/
		
		/*String[] nonT = {"S", "A", "B", "C", "D", "E", "F"};
		String[] term = {"a", "b", "c"};
		Rule[] rules = new Rule[7];
		rules[0] = new Rule("S", new String[]{"AC", "BS", "B"});
		rules[1] = new Rule("A", new String[]{"aA", "aF"});
		rules[2] = new Rule("B", new String[]{"CF", "b"});
		rules[3] = new Rule("C", new String[]{"cC", "D"});
		rules[4] = new Rule("D", new String[]{"aD", "BD", "C"});
		rules[5] = new Rule("E", new String[]{"aA", "BSA"});
		rules[6] = new Rule("F", new String[]{"bB", "b"});
		String startSym = "S";
		Grammar g = new Grammar(nonT, term, rules, startSym);
		System.out.println(g.toString() + "\n");
		g.removeUselessSym();
		System.out.println(g.toString() + "\n");*/
		
		/*String[] nonT = {"S", "A", "B", "C"};
		String[] term = {"a", "b", "c"};
		Rule[] rules = new Rule[4];
		rules[0] = new Rule("S", new String[]{"aABC", "a"});
		rules[1] = new Rule("A", new String[]{"aA", "a"});
		rules[2] = new Rule("B", new String[]{"bcB", "bc"});
		rules[3] = new Rule("C", new String[]{"cC", "c"});
		String startSym = "S";
		Grammar g = new Grammar(nonT, term, rules, startSym);
		System.out.println(g.toString() + "\n");
		g.toCNF();
		System.out.println(g.toString() + "\n");*/
		
		/*String[] nonT = {"S", "X"};
		String[] term = {"a", "b"};
		Rule[] rules = new Rule[2];
		rules[0] = new Rule("S", new String[]{"aXb", "ab"});
		rules[1] = new Rule("X", new String[]{"aXb", "ab"});
		String startSym = "S";
		Grammar g = new Grammar(nonT, term, rules, startSym);
		System.out.println(g.toString() + "\n");
		g.toCNF();
		System.out.println(g.toString() + "\n");*/
		
		/*String[] nonT = {"A", "B"};
		String[] term = {"a", "b", "c"};
		Rule[] rules = new Rule[2];
		rules[0] = new Rule("A", new String[]{"AB", "BA", "a"});
		rules[1] = new Rule("B", new String[]{"b", "c"});
		String startSym = "A";
		Grammar g = new Grammar(nonT, term, rules, startSym);
		g.removeLeftRecursion();
		System.out.println(g.toString() + "\n");*/
		
		/*String[] nonT = {"S", "A", "B", "C"};
		String[] term = {"a", "b", "c"};
		Rule[] rules = new Rule[4];
		rules[0] = new Rule("S", new String[]{"AB", ""});
		rules[1] = new Rule("A", new String[]{"AB", "CB", "a"});
		rules[2] = new Rule("B", new String[]{"AB", "b"});
		rules[3] = new Rule("C", new String[]{"AC", "c"});
		String startSym = "S";
		Grammar g = new Grammar(nonT, term, rules, startSym);
		System.out.println(g.toString() + "\n");
		g.toGNF();
		System.out.println(g.toString() + "\n");*/
		
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
		g.toGNF();
		System.out.println(g.toString() + "\n");*/
	//}
}
