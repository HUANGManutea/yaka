package test;

import static org.junit.Assert.*;
import main.Constantes.Type;
import main.Expression;

import org.junit.Before;
import org.junit.Test;

public class ExpressionTest {

	private Expression exp;
	
	@Before
	public void setUp (){
		exp = new Expression();
	}

	@Test
	public void testAddInteger() {
		exp.addInteger(5);
		Type t1 = exp.popType();
		assertEquals(t1, Type.INTEGER);
	}
	
	@Test
	public void testAddBoolean() {
		exp.addBoolean(0);
		Type t1 = exp.popType();
		assertEquals(t1, Type.BOOLEAN);
	}
	
	@Test
	public void testAddOperator() {
		exp.addOperator("+");
		String t1 = exp.popOp();
		assertEquals(t1, "+");
	}
	
	@Test
	public void testCheckTypeArithBoolInteger(){
		exp.addBoolean(0);
		exp.addInteger(1);
		exp.addOperator("+");
		exp.checkType();
		assertEquals(exp.popType(),Type.ERROR);
	}
	
	@Test
	public void testCheckTypeArithBoolBool(){
		exp.addBoolean(0);
		exp.addBoolean(1);
		exp.addOperator("+");
		exp.checkType();
		assertEquals(exp.popType(),Type.ERROR);
	}
	
	@Test
	public void testCheckTypeLogiBoolInteger(){
		exp.addBoolean(0);
		exp.addInteger(1);
		exp.addOperator("=");
		exp.checkType();
		assertEquals(exp.popType(),Type.ERROR);
	}
	
	@Test
	public void testCheckTypeLogiIntegerInteger(){
		exp.addBoolean(0);
		exp.addInteger(1);
		exp.addOperator("<>");
		exp.checkType();
		assertEquals(exp.popType(),Type.ERROR);
	}


	@Test
	public void testCheckTypeCompBoolBool(){
		exp.addBoolean(0);
		exp.addBoolean(1);
		exp.addOperator("<");
		exp.checkType();
		assertEquals(exp.popType(),Type.ERROR);
	}

	@Test
	public void testCheckTypeCompBoolInteger(){
		exp.addBoolean(0);
		exp.addInteger(1);
		exp.addOperator(">");
		exp.checkType();
		assertEquals(exp.popType(),Type.ERROR);
	}
	
	@Test
	public void testCheckNegMinus(){
		exp.addBoolean(0);
		exp.addOperator("-");
		exp.checkNeg();
		assertEquals(exp.popType(),Type.ERROR);
	}
	
	@Test
	public void testCheckNegNegation(){
		exp.addInteger(0);
		exp.addOperator("NON");
		exp.checkNeg();
		assertEquals(exp.popType(),Type.ERROR);
	}

}
