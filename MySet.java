package theoreticalFoundations;
/*Lamar A. Sims
 * November 19, 2013*/

public class MySet {
	//Declarations and initializations of variables
	private Object[] elements;
	private int iSize = 0;
	private String name;
	
	/** Constructs a empty set */
	public MySet(){
		this.elements = new Object[10];
	}
	
	/** Constructs a set with specified objects */
	public MySet(Object[] o){
		//check for null object
		if (o == null)
			throw new NullPointerException("Error! Could not construct MySet object. Specified object " +
					"cannot be null");
		elements = new Object[o.length];
		add(o);
	}
	
	/** Constructs a set with a name and specified objects */
	public MySet(String name, Object[] o){
		this(o);
		//check for null objects
		if (name == null)
			throw new NullPointerException("Error! Could not construct MySet object. Specified objects " +
					"cannot be null");
		this.name = name;
	}
	
	/** Construct a set with a specified size */
	public MySet(int size){
		this.elements = new Object[size];
	}
	
	/** Constructs a set from another set */
	public MySet(MySet s){
		//check for null object
		if (s == null)
			throw new NullPointerException("Error! Cannot copy from null object");
		
		//copy information
		this.elements = s.getElements();
		this.iSize = s.getSize();
		this.name = (s.getName() != null) ? s.getName() : null;
	}

	/** getElements():
	 * 		Returns the elements of this set */
	public Object[] getElements() {
		//temp object to store copy of elements
		Object[] temp = new Object[this.iSize];
		//copy elements to temp object
		for (int i = 0; i < this.iSize; i++){
			temp[i] = this.elements[i];
		}
		return temp;
	}

	/** setElements(Object[] elements):
	 * 		Sets specified elements to this set */
	public void setElements(Object[] elements) {
		//check for null object
		if (elements == null)
			throw new NullPointerException("Error setElements(Object[] elements)! Could not set elements. Specified elements " +
					"cannot be null");
		this.elements = new Object[elements.length];
		this.iSize = 0;
		add(elements);
	}

	/** getiSize():
	 * 		Returns the size of this set */
	public int getSize() {
		return this.iSize;
	}

	/** getName():
	 * 		Returns the name of this set */
	public String getName() {
		return this.name;
	}

	/**setName(String name): 
	 * 		Set a name for this set */
	public void setName(String name) {
		this.name = name;
	}
	
	/** get(int index):
	 * 		Returns the element at the specified index */
	public Object get(int index){
		return this.elements[index];
	}
	
	/** switchElements(Object o, int index):
	 * 		Switches a specified object with another object at a specified index */
	public void switchElements(Object o, int newIndex, int oldIndex){
		//check for null object
		if (o == null)
			throw new NullPointerException("set() operation faild! Cannot set a null object.");
		try{
			Object temp = this.elements[newIndex];
			this.elements[newIndex] = o;
			this.elements[oldIndex] = temp;
		}catch(ArrayIndexOutOfBoundsException aex){
			System.out.println("Error! Specified indices were out of bounds.");
			System.exit(0);
		}
	}
	
	/** add():
	 * 		Adds an object to this set */
	public void add(Object o) {
		if (this.isMember(o)) return; //Make sure there are no duplicate elements
		try{
			if (o == null) throw new NullPointerException("add() operation failed! Cannont add a null object.");
			this.elements[this.iSize] = o; //Add the object to this MySet
			this.iSize++;
		}catch(ArrayIndexOutOfBoundsException aix){
			//Create temp array, increase capacity, copy original objects to temp
			//Assign temp to elements and add new object to elements
			Object[] temp = new Object[this.elements.length * 2];
			for(int i = 0; i < this.elements.length; i++){
				temp[i] = this.elements[i];
			}
			this.elements = temp;
			this.elements[this.iSize] = o; //Add the object to this MySet
			this.iSize++;
		}catch(NullPointerException nex){
			System.out.println(nex.getMessage());
		}
	}
	
	/** add(Object[] o):
	 * 		Adds an array of objects to this set */
	public void add(Object[] o){
		//check for null object
		if (o == null)
			throw new NullPointerException("Error add(Object[] o)! Could not objects. Specified object " +
					"cannot be null");
		for (int i = 0; i < o.length; i++){
			if (o[i] == null) continue;
			this.add(o[i]);
		}
	}
	
	/** cardinality():
	 * 		Returns the cardinality of this set, |S| */
	public int card(){
		return this.iSize;
	}
	
	/** isMember(Object o):
	 * 		Returns if an element is a member of this set */
	public boolean isMember(Object o){
		//check for null object
		if (o == null)
			throw new NullPointerException("Error isMember(Object o)! Specified object cannot be null");
		
		for (int i = 0; i < this.iSize; i++){
			if (this.elements[i].equals(o)) return true;
		}
		return false;
	}
	
	/** difference(MySet s): 
	 * 		Adds all of the elements unique to this set, compared to another set, to a
	 * 		new set. X-Y = {z | z E X and z !E Y} */
	public MySet difference(MySet s){
		//check for null object
		if (s == null)
			throw new NullPointerException("Error difference(MySet s)! Specified object cannot be null");
		
		MySet diff = new MySet();
		for (int i = 0; i < this.iSize; i++){
			if (this.isMember(this.elements[i]) && !s.isMember(this.elements[i])){
				diff.add(this.elements[i]);
			}
		}
		return diff;
	}
	
	/** union(MySet s, MySet s1):
	 * 		Adds all of the elements from two specified sets to a new set.
	 * 		x U Y = {z | z E X or z E Y} */
	public static MySet union(MySet s, MySet s1){
		//check for null objects
		if (s == null || s1 == null)
			throw new NullPointerException("Error union(MySet s, MySet s1)! Specified objects cannot be null");
		
		MySet union = new MySet();
		union.add(s.getElements());
		union.add(s1.getElements());
		return union;
	}
	
	/** intersection(MySet s, MySet s1):
	 * 		Adds all of the elements, that are both members of the two specified sets, to 
	 * 		a new set. x intsec Y = {z | z E X and z E Y} */
	public static MySet intersection(MySet s, MySet s1){
		//check for null objects
		if (s == null || s1 == null)
			throw new NullPointerException("Error intersection(MySet s, MySet s1)! Specified objects cannot be null");
		
		MySet intsec = new MySet();
		int card = (s.card() <= s1.card()) ? s.card() : s1.card();
		for (int i = 0; i < card; i++){
			if (card == s.card()){
				if (s1.isMember(s.get(i))) intsec.add(s.get(i));
			}else{
				if (s.isMember(s1.get(i))) intsec.add(s1.get(i));
			}
		}
		return intsec;
	}
	
	/** complement(MySet universal):
	 * 		Returns the set of all the elements that are not a part of this set with
	 * 		respect to the specified universal set */
	public MySet complement(MySet universal){
		//check for null object
		if (universal == null)
			throw new NullPointerException("Error complement(MySet universal)! Specified object cannot be null");
		return universal.difference(this);
	}
	
	/** equals(MySet s):
	 * 		Determines if this set is equivalent to a specified set */
	public boolean equals(MySet s){
		//check for null object
		if (s == null)
			throw new NullPointerException("Error equals(MySet s)! Specified object cannot be null");
		return isProperSubset(s);
	}
	
	/** isSubset(MySet s):
	 * 		Determines if this set is a subset of the specified set. */
	public boolean isSubset(MySet s){
		//check for null object
		if (s == null)
			throw new NullPointerException("Error isSubset(MySet s)! Specified object cannot be null");
		
		for (int i = 0; i < this.iSize; i++){
			if (!s.isMember(this.elements[i])){
				return false;
			}
		}
		return true;
	}
	
	/** isProperSubset(MySet s):
	 * 		Determines if this set is a proper subset of the specified set. */
	public boolean isProperSubset(MySet s){
		//check for null object
		if (s == null)
			throw new NullPointerException("Error isProperSubset(MySet s)! Specified object cannot be null");
		
		if ((this.card() != s.card()) || (!this.isSubset(s))) return false;
		return true;
	}
	
	/** powerSet():
	 * 		Returns a set with all of the subsets of this set */
	public MySet powerSet(){
		MySet temp = new MySet();
		int[][] table = tableOfValues(this.card());
		for (int row = 0; row < table.length; row++){
			MySet temp1 = new MySet();
			for (int column = 0; column < table[0].length; column++){
				if (table[row][column] == 1){
					temp1.add(this.elements[column]);
				}
			}
			temp.add(temp1);
		}
		return temp;
	}
	
	/** printPowerSet():
	 * 		Prints the power set of this set */
	public void printPowerSet(){
		int[][] table = tableOfValues(this.card());
		int column = 0, row = 0, count = 0;
		String s = new String();
		boolean b = false;
		System.out.print("{");
		for (row = 0; row < table.length; row++){
			count++;
			if (count % 10 == 0) System.out.println();
			System.out.print("{");
			for (column = 0; column < table[0].length; column++){
				if (table[row][column] == 1){
					s = (b) ? "," : "";
					System.out.print(s + (this.elements[column]).toString());
					b = true;
				}
			}
			s = (count == (int)Math.pow(2, this.card())) ? "}" : "},";
			System.out.print(s);
			b = false;
		}
		System.out.println("}");
	}
	
	/** tableOfValues(int numOfElements):
	 * 		Warning: max is 23 elements
	 * 		Returns a 2D array of zeros and ones for a set of values. 0 means value
	 * 		is not in part of a set. 1 means the values is a part of a set.*/
	public int[][] tableOfValues(int numOfElements){
		if (numOfElements > 23){
			System.out.println("Error: could not perform tableOfValues in MySet. " +
					"The max number of elements allowed is 23 you entered " + numOfElements);
			System.exit(0);
		}
		int[][] table = new int[(int)Math.pow(2, numOfElements)][numOfElements];
		for (int column = 0; column < table[0].length; column++){
			long repeat = (long)Math.pow(2, column); //how many time to repeat 0 and 1 in the column
			int zeros = 0;
			int ones = 0;
			for (int rows = 0; rows < table.length; rows++){
				if (zeros < repeat){
					table[rows][column] = 0;
					zeros++;
				}
				else if (ones < repeat){
					table[rows][column] = 1;
					ones++;
				}
				if (zeros == repeat && ones == repeat){
					zeros = 0;
					ones = 0;
				}
			}/*for*/
		}/*for*/
		
		return table;
	}
	
	/** printTableOfValues(int numOfElements):
	 * 		Prints the table of values for this set. O means value is not in a part
	 * 		of a set. 1 means the values is a part of a set.*/
	public void printTableOfValues(int numOfElements){
		StringBuilder strB = new StringBuilder();
		for (int column = 0; column < numOfElements; column++){
			long repeat = (long)Math.pow(2, column); //how many time to repeat 0 and 1 in the column
			int zeros = 0;
			int ones = 0;
			for (int rows = 0; rows < (int)Math.pow(2, numOfElements); rows++){
				if (zeros < repeat){
					strB.append("0");
					zeros++;
				}
				else if (ones < repeat){
					strB.append("1");
					ones++;
				}
				if (zeros == repeat && ones == repeat){
					zeros = 0;
					ones = 0;
				}
			}/*for*/
		}/*for*/
		StringBuilder s = sort(strB);
		int incre = this.card();
		for (int i = 0, y = 0; i < ((int)Math.pow(2, this.card())); i++, y += incre){
			System.out.println(s.substring(y, y+incre));
		}
	}
	
	private StringBuilder sort(StringBuilder s){
		int incre = (int)Math.pow(2, this.card());
		int i = 0, j = 0, y = incre, count = 0, index = 0;
		StringBuilder strB = new StringBuilder();
		
		String s1 = s.substring(j, j+incre);
		String s2 = s.substring(y, y+incre);
		for (int k = 0; k < ((int)Math.pow(2, this.card())); k++){
			strB.insert(index, String.valueOf(s1.charAt(k)) + s2.charAt(k));
			index += 2;
		}
		
		count += 3;
		index = count - 1;
		j += 2*incre;
		int count1 = count;
		for(i = 2; i < this.card(); i++, j += incre){
			s1 = s.substring(j, j+incre);
			for (int k = 0; k < ((int)Math.pow(2, this.card())); k++){
				strB.insert(index, String.valueOf(s1.charAt(k)));
				count1 += count;
				index = count1 - 1;
			}
			count++;
			count1 = count;
			index = count1 - 1;
		}
		
		return strB;
	}
	
	/** cartesianProduct(MySet s):
	 * 		Returns the cartesian product of this set with the specified set
	 * 		X x Y = {(a,b) | a E X and b E Y} */
	public MySet cartesianProduct(MySet s){
		//check for null object
		if (s == null)
			throw new NullPointerException("Error cartesianProduct(MySet s)! Specified object cannot be null");
		
		MySet temp = new MySet();
		for (int i = 0; i < this.card(); i++){
			for (int y = 0; y < s.card(); y++){
				MySet temp1 = new MySet();
				temp1.add((this.elements[i] instanceof MySet) ? ((MySet)this.elements[i]).getElements() : new Object[] {this.elements[i]});
				temp1.add((s.elements[y] instanceof MySet) ? ((MySet)this.elements[y]).getElements() : new Object[] {s.elements[y]});
				temp.add(temp1);
			}
		}
		return temp;
	}

	
	/** toString():
	 * 		Returns the toString for this MySet */
	public String toString(){
		String str = new String();
		str = "{";
		for (int i = 0; i < this.card(); i++){
			if ((i != 0) && (i % 10 == 0)) str += "\n";
			str += ((this.card() - 1) == i) ? ((this.elements[i])).toString() : (this.elements[i]).toString() + ",";
		}
		str += "}";
		return str;
	}
}
