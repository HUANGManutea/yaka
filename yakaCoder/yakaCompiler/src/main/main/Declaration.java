package main;

import java.util.ArrayList;
import java.util.List;

import main.Constantes.*;

public class Declaration {
	private String currentIdent;
	private Type currentType;
	private IdFunc currentFunction;
	private int countVar;
	private List<String> paramNames;
	private List<Type> paramTypes;
	
	public Declaration() {
		countVar = 0;
		paramNames = new ArrayList<String>();
		paramTypes = new ArrayList<Type>();
	}
	
	public void setCurrentIdent(String ident){
		if (!Yaka.tabIdent.exists(ident)) {
			currentIdent = ident;
		} else {
			System.out.println("2 ident de même nom:" +ident);
			Yaka.yvm.erreur();
		}
	}
	
	public void setCurrentType(Type type) {
		currentType = type;
	}
	
	public void declareConstByValue(Type type, int value) {
		IdConst c = new IdConst(type, value);
		Yaka.tabIdent.add(currentIdent, c);
	}
	
	public void declareConstByIdent(String ident){
		if (Yaka.tabIdent.exists(ident)) {
			Ident tmp = Yaka.tabIdent.find(ident);
			if (tmp instanceof IdConst) {
				IdConst c2 = (IdConst)tmp;
				IdConst c1 = new IdConst(c2.type, c2.value);
				Yaka.tabIdent.add(currentIdent, c1);
			} else {
				System.out.println("Ident "+ident+" non initialisé");
				Yaka.yvm.erreur();
			}
		} else {
			System.out.println("Ident "+ident+" non déclaré");
			Yaka.yvm.erreur();
		}
	}
	
	public void declareFunction(String ident) {
		setCurrentIdent(ident);
		currentFunction = new IdFunc(currentType);
		Yaka.tabIdent.add(currentIdent, currentFunction);
		Yaka.yvm.label(ident);
	}
	
	public void checkReturnType() {
		Type t = Yaka.expression.popType();
		if (currentFunction.type != t) {
			System.out.println("Erreur de type de retour, ligne " + Yaka.token.beginLine);
			Yaka.yvm.erreur();
		}
	}
	
	public void endFunction() {
		paramNames = new ArrayList<String>();
		paramTypes = new ArrayList<Type>();
		countVar = 0;
		Yaka.function.end();
	}
	
	public void addPara(String ident) {
		setCurrentIdent(ident);
		paramNames.add(currentIdent);
		paramTypes.add(currentType);
		Yaka.function.incParamCount();
	}
	
	public void declareParams() {
		for (int i=1; i<=paramNames.size(); i++) {
			int offset = paramNames.size()*2 + 4 - (i*2);
			IdVar para = new IdVar(paramTypes.get(i-1), offset);
			Yaka.tabIdent.add(paramNames.get(i-1), para);
		}
		
		currentFunction.paramTypes = paramTypes;
	}
	
	public void declareVar(String ident) {
		setCurrentIdent(ident);
		countVar++;
		IdVar v = new IdVar(currentType, countVar*-2);
		Yaka.tabIdent.add(currentIdent, v);
	}
	
	public void reserveMemory() {
		Yaka.yvm.ouvreBloc(countVar);
	}
}
