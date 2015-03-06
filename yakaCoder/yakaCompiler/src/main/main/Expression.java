package main;

import java.util.Stack;
import main.Constantes.Type;
public class Expression {
	private Stack<Type> typeStack;
	private Stack<Character> opStack;
	
	public Expression(){
		opStack = new Stack<Character>();
		typeStack = new Stack<Type>();
	}
	
	public void addType(Type integer){
		typeStack.push(integer);
	}
	
	public void addOperator(char op){
		opStack.push(op);
	}
	
	public void valueLastOp(){
		
	}
}
