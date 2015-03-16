package main;

import java.util.Stack;
import main.Constantes.*;

public class Expression {
	private Stack<Type> typeStack;
	private Stack<String> opStack;
	private Stack<IdFunc> functions;
	private Stack<Integer> functionParamCounts;
	
	public Expression(){
		opStack = new Stack<String>();
		typeStack = new Stack<Type>();
		functions = new Stack<IdFunc>();
		functionParamCounts = new Stack<Integer>();
	}
	
	private void addType(Type t){
		typeStack.push(t);
	}
	
	public void addIdent(String ident) {
		Ident i = Yaka.tabIdent.find(ident);
		if (i instanceof IdConst) {
			Yaka.yvm.iconst(((IdConst)i).value);
		} else if (i instanceof IdVar) {
			Yaka.yvm.iload(((IdVar)i).offset);
		} else if (i instanceof IdFunc) {
			Yaka.function.prepareCall(ident);
			functions.push((IdFunc) i);
			functionParamCounts.push(0);
		}
		
		addType(i.type);
	}
	
	public void addInteger(int value) {
		Yaka.yvm.iconst(value);
		addType(Type.INTEGER);
	}
	
	public void addBoolean(int value){
		Yaka.yvm.iconst(value);
		addType(Type.BOOLEAN);
	}
	
	public void addOperator(String op){
		opStack.push(op);
	}
	
	private void doUnOp(String op){
		switch (op){
		case "-":
			Yaka.yvm.ineg();
			break;
		case "NON":
			Yaka.yvm.inot();
			break;
		default:
			break;
		}
	}
	private void doBinOp(String op){
		switch (op) {
		case "+":
			Yaka.yvm.iadd();
			break;
		case "-":
			Yaka.yvm.isub();
			break;
		case "*":
			Yaka.yvm.imul();
			break;
		case "/":
			Yaka.yvm.idiv();
			break;				
		case "<":
			Yaka.yvm.iinf();
			break;
		case ">":
			Yaka.yvm.isup();
			break;
		case "<=":
			Yaka.yvm.iinfegal();
			break;
		case ">=":
			Yaka.yvm.isupegal();
			break;				
		case "=":
			Yaka.yvm.iegal();
			break;
		case "<>":
			Yaka.yvm.idiff();
			break;
		case "ET":
			Yaka.yvm.iand();
			break;
		case "OU":
			Yaka.yvm.ior();
			break;
		default:
			break;
		}
	}
	
	public void incParamCount() {
		int n = functionParamCounts.pop();
		n++;
		functionParamCounts.push(n);
	}
	
	public void checkParams() {
		int n = functionParamCounts.pop();
		IdFunc f = functions.pop();
		
		if (f.paramTypes.size() != n) {
			System.out.println("Erreur : nombre de parametres incorrect, ligne " + Yaka.token.beginLine);
			Yaka.yvm.erreur();
		} else {
			for (int i=0; i<n; i++) {
				Type t = typeStack.pop();
				if (f.paramTypes.get(n-1-i) != t) {
					System.out.println("Erreur : Parametre de type incorrect, ligne " + Yaka.token.beginLine);
					Yaka.yvm.erreur();
				}
			}
		}
	}
	
	public void checkType() {
		String op = opStack.pop();
		Type b = typeStack.pop();
		Type a = typeStack.pop();
		if(b==Type.ERROR || a==Type.ERROR){
			typeStack.push(Type.ERROR);
		}
		else{
			doBinOp(op);
			switch (op) {
			case "+":
			case "-":
			case "*":
			case "/":
				if(a==Type.INTEGER && b==Type.INTEGER){
					typeStack.push(Type.INTEGER);
				}
				else{
					typeStack.push(Type.ERROR);
				}
				break;
/**********************************************************************/				
			case "<":
			case ">":
			case "<=":
			case ">=":
				if(a==Type.INTEGER && b==Type.INTEGER){
					typeStack.push(Type.BOOLEAN);
				}
				else{
					typeStack.push(Type.ERROR);
				}
				break;
/**********************************************************************/					
			case "=":
			case "<>":
				if(a==Type.INTEGER && b==Type.INTEGER){
					typeStack.push(Type.BOOLEAN);
				}
				else if(a==Type.BOOLEAN && b==Type.BOOLEAN){
					typeStack.push(Type.BOOLEAN);
				}
				else {
					typeStack.push(Type.ERROR);
				}
				break;
/**********************************************************************/
			case "ET":
			case "OU":
				if(a==Type.BOOLEAN && b==Type.BOOLEAN){
					typeStack.push(Type.BOOLEAN);
				}
				else {
					typeStack.push(Type.ERROR);
				}
				break;
			default:
				break;
			}
		}
	}
	
	public void checkNeg(){
		String op = opStack.pop();
		Type chosenOne = typeStack.pop();
		doUnOp(op);
		switch(op){
		case "-":
			if(chosenOne==Type.INTEGER){
				typeStack.push(Type.INTEGER);
			}
			else{
				typeStack.push(Type.ERROR);
			}
			break;
		case "NON":
			if(chosenOne==Type.BOOLEAN){
				typeStack.push(Type.BOOLEAN);
			}
			else{
				typeStack.push(Type.ERROR);
			}
			break;
			default:
				break;
		}
	}
	
	public Type popType() {
		return typeStack.pop();
	}
}
