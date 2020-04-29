
public class BTQueue {
	LLNode<ExpressionBT> front;
	LLNode<ExpressionBT> back;
	
	/*
	 * queue methods
	 */
	public void enqueue(ExpressionBT d) {
		if(front == null) {
			front = new LLNode<ExpressionBT>(d);
			back = front;
		}else {
			back.next = new LLNode<ExpressionBT>(d);
			back = back.next;
		}
	}
	
	public ExpressionBT dequeue() {
		if(isEmpty()) return null;
		ExpressionBT rtn = front.payload;
		front = front.next;
		if(front == null) back = null;
		return rtn;
	}
	
	public ExpressionBT peek() {
		if(isEmpty()) return null;
		return front.payload;
	}
	
	public boolean isEmpty() {
		return front == null;
	}
	
	 public int getSize() {
	    return front.length();
	  }
	
}
