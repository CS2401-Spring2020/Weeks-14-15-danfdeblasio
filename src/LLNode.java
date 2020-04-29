
public class LLNode<T> {
	public T payload;
	public LLNode<T> next;
	
	LLNode(){}
	
	LLNode(T in){
		payload = in;
	}
	
	public void addToEnd(T d) {
		if(next == null) next = new LLNode<T>(d);
		next.addToEnd(d);
	}
	
	public LLNode<T> getEnd() {
		if(next == null) return this;
		return next.getEnd();
	}
	
	public int length() {
	  if(next == null) return 1;
	  return 1 + next.length();
	}
}
