package main;

import java.util.Stack;

public class Function {
	private Stack<String> calls;
	private int paramCount;
	
	public Function() {
		calls = new Stack<String>();
	}
	
	public void prepareCall(String functionName) {
		calls.push(functionName);
	}
	
	public void incParamCount() {
		paramCount++;
	}
	
	public void end() {
		Yaka.yvm.fermeBloc(paramCount);
		paramCount = 0;
	}
	
	public void call() {
		Yaka.yvm.call(calls.pop());
	}
	
	public void rturn() {
		Yaka.yvm.ireturn(4 + paramCount*2);
	}
}
