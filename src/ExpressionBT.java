class ExpressionBT{
	String type;
	char operator;
	int value;
	String variable;
	ExpressionBT left;
	ExpressionBT right;
	
	/*
	 * Constructors 
	 */
	ExpressionBT(){
		
	}
	
	private void printArr(String[] e) {
		for(int i=0; i<e.length; i++) {
			if(i != 0) System.out.print(", ");
			System.out.print(e[i]);
		}
		System.out.println();
	}
	
	ExpressionBT(String[] e){
		//printArr(e);
		if(e[0].equals("+") || e[0].equals("-") || e[0].equals("*") || e[0].equals("/")) {
			type = "operator";
			operator = e[0].charAt(0);
			String[] left_arr = new String[(e.length - 1)/2];
			int i = 0;
			for(int level=1; e.length > Math.pow(2, level); level++) {
				for(int j=0; j < Math.pow(2, level)/2; j++) {
			//		System.out.println("left_arr[" + i + "++] = e[" + (int)Math.pow(2, level) + "+" + j + " - 1];");
					left_arr[i++] = e[(int)Math.pow(2, level) + j - 1];
				}
			}
			left = new ExpressionBT(left_arr);
			i = 0;
			String[] right_arr = new String[(e.length - 1)/2];
			for(int level=1; e.length > Math.pow(2, level); level++) {
				for(int j=(int)Math.pow(2, level)/2; j < Math.pow(2, level); j++) {
			//		System.out.println("left_arr[" + i + "++] = e[" + (int)Math.pow(2, level) + "+" + j + " - 1];");
					right_arr[i++] = e[(int)Math.pow(2, level)+ j - 1];
				}
			}
			right = new ExpressionBT(right_arr);
		}else {
			try {
				value = Integer.parseInt(e[0]);
				type = "val";
			}catch(NumberFormatException exc) {
				variable = e[0];
				type = "var";
			}
		}
	}
	
	/*
	 * Getters 
	 */
	public String getType(){ return type; }
	public int getValue(){ return value; }
	public String getVariable(){ return variable; }
	public ExpressionBT getLeft(){ return left;}
	public ExpressionBT getRight(){ return right; }
	
	/*
	 * Setters  
	 */
	public void setType(String t){ type = t; }
	public void setValue(int v){ value = v; }
	public void setVariable(String var){ variable = var; }
	public void setLeft(ExpressionBT b){ left = b; }
	public void setRight(ExpressionBT b){ right = b; }
	
	/* 
	 * Other Methods 
	 */
	
	// traverses the expression using recursion and returns an integer: 
	//    the integer value of the expression. 
	//  Note: only if there are no variables in the expression. 
	//  If there are variables, print out that you cannot evaluate and return 0
	public int Evaluate(){
		if(includesVariables()) {
			System.out.print("there are variables in this expression");
		}
		
		if(type.equals("operator")) {
			int left_val = left.Evaluate();
			int right_val = right.Evaluate();
			if(operator  == '+') {
				return left_val + right_val;
			} else if(operator  == '-') {
				return left_val - right_val;
			} else if(operator  == '*') {
				return left_val * right_val;
			} else if(operator  == '/') {
				return left_val / right_val;
			} 
		}
		
		if(type.equals("val")) {
			return value;
		}
		
		return 0;
		
	}
	
	// prints out the expression in infix notation with parentheses, using a BTStack
	public void Print(){
		
		if(type.equals("var")) {
			System.out.print(variable);
		}
		
		if(type.equals("operator")) {
			System.out.print("( ");
			left.Print();
			System.out.print(" " + operator + " ");
			right.Print();
			System.out.print(" )");
		}
		
		if(type.equals("val")) {
			System.out.print(value);
		}
	}
	
	// It prints out all variables in the tree, if any. 
	// If there is no variable, it prints out "no variable in this expression". This method should use a BTQueue.
	public void allVariables(){
		
		if(!includesVariables()) {
			System.out.print("no variable in this expression");
			return;
		}
		
		BTQueue q = new BTQueue();
		q.enqueue(this);
		
		while(!q.isEmpty()) {
			ExpressionBT cur = q.dequeue();
			if(cur.type.equals("var")) {
				System.out.println(cur.variable);
			}
			
			if(cur.type.equals("operator")) {
				q.enqueue(cur.left);
				q.enqueue(cur.right);
			}
		}
	}
	
	// returns true if the expression contains at least one variable, false otherwise
	public boolean includesVariables(){
		
		if(type.equals("var")) {
			return true;
		}
		
		if(type.equals("operator")) {
			return left.includesVariables() || right.includesVariables();
		}
			
		return false;
	}
}
