import static org.junit.jupiter.api.Assertions.*;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import org.junit.jupiter.api.Test;

class Week1415Tester {
  
  public static void main(String[] args) {
    System.out.println(new PostfixExpression("3 4 + 0 * 7 11 * 6 + +").Evaluate());
  }

	@Test
	void PostFixExpression1() {
		PostfixExpression e1 = new PostfixExpression("2 2 + 4 *");
		assertEquals(16,e1.Evaluate());
		ByteArrayOutputStream myOut = new ByteArrayOutputStream();
		PrintStream original = System.out; 
		System.setOut(new PrintStream(myOut));
		e1.Print();
		System.setOut(original);
		String standardOutput = myOut.toString();
		assertEquals("2 2 + 4 *",standardOutput);
	}
	
	@Test
	void PostFixExpression2() {
		PostfixExpression e1 = new PostfixExpression("2 2 + 2 2 - /");
		assertEquals(0,e1.Evaluate());
		ByteArrayOutputStream myOut = new ByteArrayOutputStream();
		PrintStream original = System.out; 
		System.setOut(new PrintStream(myOut));
		e1.Print();
		System.setOut(original);
		String standardOutput = myOut.toString();
		assertEquals("2 2 + 2 2 - /",standardOutput);
	}
	
	@Test
	void ExpressionBT1() {
		ExpressionBT e1 = new ExpressionBT(new String[]{"+","2","*", null, null, "+", "+",  null, null,  null, null, "1","2","3","4"});
		ByteArrayOutputStream myOut = new ByteArrayOutputStream();
		PrintStream original = System.out; 
		System.setOut(new PrintStream(myOut));
		e1.Print();
		System.setOut(original);
		String standardOutput = myOut.toString();
		assertEquals("( 2 + ( ( 1 + 2 ) * ( 3 + 4 ) ) )",standardOutput);
		myOut.reset();
		System.setOut(new PrintStream(myOut));
		int eval = e1.Evaluate();
		System.setOut(original);
		standardOutput = myOut.toString();
		assertEquals(23, eval);
		assertEquals("",standardOutput);
	}	
	
	@Test
	void ExpressionBT3() {
		ExpressionBT e1 = new ExpressionBT(new String[]{"+","2","*", null, null, "X", "3"});
		PrintStream original = System.out; 
		ByteArrayOutputStream myOut = new ByteArrayOutputStream();
		System.setOut(new PrintStream(myOut));
		e1.Print();
		System.setOut(original);
		String standardOutput = myOut.toString();
		assertEquals("( 2 + ( X * 3 ) )",standardOutput);
		myOut.reset();
		System.setOut(new PrintStream(myOut));
		int eval = e1.Evaluate();
		System.setOut(original);
		standardOutput = myOut.toString();
		assertEquals(0, eval);
		assertEquals("there are variables in this expression",standardOutput);
	}

}
