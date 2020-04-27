
public class BTStack {
	LLNode<ExpressionBT> top;
	
	/*
	 * stack methods
	 */
	public void push(ExpressionBT d) {
		LLNode<ExpressionBT> temp = new LLNode<ExpressionBT>(d);
		temp.next = top;
		top = temp;
	}
	
	public ExpressionBT pop() {
		if(isEmpty()) return null;
		ExpressionBT rtn = top.payload;
		top = top.next;
		return rtn;
	}
	
	public ExpressionBT peek() {
		if(isEmpty()) return null;
		return top.payload;
	}
	
	public boolean isEmpty() {
		return top == null;
	}
}
