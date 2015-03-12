package main;

import java.util.Stack;

import main.Constantes.*;

public class Condition {
	private static int count = 0;
	private Stack<Integer> numStack;
	
	public Condition() {
		numStack = new Stack<Integer>();
	}
	
	public void testExpression() {
		int num = ++count;
		numStack.push(num);
		
		Type t = Yaka.expression.popType();
		if (t == Type.BOOLEAN) {
			Yaka.yvm.iffaux("SINON" + num);
		} else {
			// type error
		}
	}
	
	public void startElseBlock() {
		int num = numStack.pop();
		numStack.push(num);
		
		Yaka.yvm.goto_("FSI" + num);
		Yaka.yvm.label("SINON" + num);
	}
	
	public void endIfBlock() {
		int num = numStack.pop();
		Yaka.yvm.label("FSI" + num);
	}
}
