package main;

import java.util.Stack;

import main.Constantes.*;

public class Iteration {
	private static int count = 0;
	private Stack<Integer> numStack;
	
	public Iteration() {
		numStack = new Stack<Integer>();
	}
	
	public void start() {
		int num = ++count;
		numStack.push(num);
		Yaka.yvm.label("FAIRE" + num);
	}
	
	public void testExpression() {
		int num = count;
		
		Type t = Yaka.expression.popType();
		if (t == Type.BOOLEAN) {
			Yaka.yvm.iffaux("FAIT" + num);
		} else {
			// type error
		}
	}
	
	public void repeat() {
		int num = numStack.pop();
		Yaka.yvm.goto_("FAIRE" + num);
		Yaka.yvm.label("FAIT" + num);
	}
}
