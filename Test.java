package theoreticalFoundations;
/*Running Test will show a few examples of strings being parsed */
public class Test {
	public static void main(String[] args){
		String[] nonT = {"<F>", "<E>", "<T>", "<n>", "<v>"};
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
		System.out.println(a.toString() + "\n");
		System.out.println("Accept \"1+1\": " + a.accpeted("1+1"));
		System.out.println("Accept \"((2*2)+1)*8/2\": " + a.accpeted("((2*2)+1)*8/2"));
		System.out.println("Accept \"a/v\": " + a.accpeted("a/v"));
		System.out.println("Accept \"11\": " + a.accpeted("11"));
		System.out.println("Accept \"132/15\": " + a.accpeted("132/15"));
		System.out.println("Accept \"13v\": " + a.accpeted("13v"));
		System.out.println("Accept \"300x*400y\": " + a.accpeted("300x*400y"));
		System.out.println("Accept \"((900/300)*2+5/2)/(55*3x)\": " + a.accpeted("((900/300)*2+5/2)/(55*3x)"));
		System.out.println("Accept \"(xyz/abc)*lmnop\": " + a.accpeted("(xyz/abc)*lmnop"));
		System.out.println("Accept \"(x*y*z/a*b*c)*l*m*n*o*p\": " + a.accpeted("(x*y*z/a*b*c)*l*m*n*o*p"));
		System.out.println("Accept \"((1+1)\": " + a.accpeted("((1+1)"));
		System.out.println("Accept \"m*/Y+-k\": " + a.accpeted("m*/Y+-k"));
		System.out.println("Accept \"+\": " + a.accpeted("+"));
		System.out.println("Accept \"v/1)\": " + a.accpeted("v/1)"));
	}
}
