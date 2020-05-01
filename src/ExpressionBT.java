class ExpressionBT{
	String type;
	char operator;
	int value;
	String variable;
	ExpressionBT left;
	ExpressionBT right;
	boolean visited;
	
	/*
	 * Constructors 
	 */
	ExpressionBT(){
	  visited = false;
	}
	
	private void printArr(String[] e) {
		for(int i=0; i<e.length; i++) {
			if(i != 0) System.out.print(", ");
			System.out.print(e[i]);
		}
		System.out.println();
	}
	
	private boolean isInt(String s) {
		for(int i=0; i<s.length(); i++) {
			if(s.charAt(i) < '0' || s.charAt(i) > '9') {
				if(i!=0 || s.charAt(i) != '-')
					return false;
			}
		}
		return true;
	}
	
	ExpressionBT(String[] e){
		//printArr(e);
		if((e.length+1)!=Math.pow(2, Math.round(Math.log((e.length+1))/Math.log(2)))) {
			System.err.println("The input array must be a power of 2 minus 1.");
			System.exit(1);
		}
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
			if(left_arr[0] != null) {
				left = new ExpressionBT(left_arr);
			}else {
				System.err.println("Somehow, there is a subtree with a null root value.");
				System.exit(1);
			}
			i = 0;
			String[] right_arr = new String[(e.length - 1)/2];
			for(int level=1; e.length > Math.pow(2, level); level++) {
				for(int j=(int)Math.pow(2, level)/2; j < Math.pow(2, level); j++) {
			//		System.out.println("left_arr[" + i + "++] = e[" + (int)Math.pow(2, level) + "+" + j + " - 1];");
					right_arr[i++] = e[(int)Math.pow(2, level)+ j - 1];
				}
			}
			if(right_arr[0] != null) {
				right = new ExpressionBT(right_arr);
			}else {
				System.err.println("Somehow, there is a subtree with a null root value.");
				System.exit(1);
			}
		}else {
			/*try {
				value = Integer.parseInt(e[0]);
				type = "value";
			}catch(NumberFormatException exc) {
				variable = e[0];
				type = "var";
			}*/
			if(isInt(e[0])) {
				value = Integer.parseInt(e[0]);
				type = "value";
			}else {
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
			return 0;
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
		
		if(type.equals("value")) {
			return value;
		}
		
		return 0;
		
	}
	
	public void printSingleNode() {
		if(type.equals("var")) {
			System.out.print(variable);
		}
		
		if(type.equals("operator")) {
			System.out.print(" " + operator + " ");
		}
		
		if(type.equals("value")) {
			System.out.print(value);
		}
	}
	
	// prints out the expression in infix notation with parentheses, using a BTStack
	public void Print(){
		BTStack s = new BTStack();
		
		/*
		 * Two stack version
		 */
		BTStack v = new BTStack();
		if(this.type.equals("operator")) System.out.print("( ");
		s.push(this);
    ExpressionBT cur = left;
    while(cur != null) {
      if(cur.type.equals("operator")) {
        System.out.print("( ");
      }
      s.push(cur);
      cur = cur.left;
    }
    while(!s.isEmpty()) {
      cur = s.pop();
      if(cur == v.peek()) {
        v.pop();
        System.out.print(" )");
      }else {
        cur.printSingleNode();
        if(cur.type.equals("operator")) {
          s.push(cur);
          v.push(cur);
          cur = cur.right;
          while(cur != null) {
           if(cur.type.equals("operator")) {
              System.out.print("( ");
            }
            cur.visited = false;
            s.push(cur);
            cur = cur.left;
          }
        }
      }
    }
    
		/*
		 * Extra variable version
		 * 
		 
		visited = false;
		s.push(this);
		ExpressionBT cur = left;
		while(cur != null) {
			if(cur.type.equals("operator")) {
				System.out.print("( ");
			}
			cur.visited = false;
			s.push(cur);
			cur = cur.left;
		}
		while(!s.isEmpty()) {
			cur = s.peek();
			if(cur.visited) {
			  System.out.print(" )");
			  s.pop();
			}else {
			  cur.visited = true;
			  cur.printSingleNode();
			  if(cur.type.equals("operator")) {
			    cur = cur.right;
  			  while(cur != null) {
			     if(cur.type.equals("operator")) {
			        System.out.print("( ");
			      }
			      cur.visited = false;
  			    s.push(cur);
  			    cur = cur.left;
  			  }
			  }else {
			    s.pop();
			  }
			}
		}*/
		
		/*
		 * Recursive Version (does not comply with the assignment)
		 * 

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
		
		if(type.equals("value")) {
			System.out.print(value);
		}*/
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
