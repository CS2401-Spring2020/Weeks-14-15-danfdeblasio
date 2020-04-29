public class IntStack {
	LLNode<Integer> top;

	/*
	 * stack methods
	 */
	public void push(int d) {
		LLNode<Integer> temp = new LLNode<Integer>(d);
		temp.next = top;
		top = temp;
	}
	
	public int pop() {
		if(isEmpty()) return Integer.MIN_VALUE;
		int rtn = top.payload;
		top = top.next;
		return rtn;
	}
	
	public int peek() {
		if(isEmpty()) return Integer.MIN_VALUE;
		return top.payload;
	}
	
	public boolean isEmpty() {
		return top == null;
	}
	
	public int getSize() {
	  return top.length();
	}
	
}
