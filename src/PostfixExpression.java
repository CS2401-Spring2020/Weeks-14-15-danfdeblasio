public class PostfixExpression {
	String expression;
	
	/*
	 * Constructors
	 */
	PostfixExpression() {}
	PostfixExpression(String e) { expression = e; }
	
	/*
	 * Getters
	 */
	public String getExpression() { return expression; }
	
	/*
	 * Setters
	 */
	public void setExpression(String newExpr) { expression = newExpr; }
	
	/*
	 * Other Methods
	 */
	
	// traverses the expression using IntStack and returns an integer: 
	//    the integer value of the expression
	public int Evaluate(){
		String[] clauses = expression.split(" ");
		IntStack s = new IntStack();
		for(int i=0; i<clauses.length; i++) {
			if(clauses[i].equals("+") || clauses[i].equals("-") || clauses[i].equals("*") || clauses[i].equals("/")) {
			  if(s.getSize() < 2) {
		      System.err.println("Given expression " + expression + " is not valid (empty stack)");
		      System.exit(1);
		    }
				int a = s.pop();
				int b = s.pop();
				if(clauses[i].equals("+")) {
  				s.push(a + b);
  			} else if(clauses[i].equals("-") ) {
  				s.push(a - b);
  			} else if(clauses[i].equals("*")) {
  				s.push(a * b);
  			} else if(clauses[i].equals("/")) {
  				s.push(a / b);
  			}
			} else {
				s.push(Integer.parseInt(clauses[i]));
			}
		}
		if(s.getSize() != 1) {
		  System.err.println("Given expression " + expression + " is not valid (non-empty end)");
		  System.exit(1);
		}
		return s.pop();
	}
	
	// prints out the expression in postfix notation
	public void Print(){
		System.out.print(expression);
	}

}
