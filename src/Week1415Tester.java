import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class Week1415Tester {

	@Test
	void PostFixExpression1() {
		PostfixExpression e1 = new PostfixExpression("2 2 + 4 *");
		assertEquals(16,e1.Evaluate());
	}
	
	@Test
	void PostFixExpression2() {
		PostfixExpression e1 = new PostfixExpression("2 2 + 2 2 - /");
		assertEquals(0,e1.Evaluate());
	}
	
	@Test
	void ExpressionBT1() {
		ExpressionBT e1 = new ExpressionBT(new String[]{"+","2","*", null, null, "+", "+",  null, null,  null, null, "1","2","3","4"});
		e1.Print();
		assertEquals(23, e1.Evaluate());
	}

}
