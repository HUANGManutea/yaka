package main;

import main.Constantes.Type;

public class Declaration {
	private String currentIdent;
	private Type currentType;
	private int countVar;
	
	public void setCurrentIdent(String ident) {
		if (!Yaka.tabIdent.exists(ident)) {
			currentIdent = ident;
		} else {
			// Throw error
		}
	}
	
	public void setCurrentType(Type type) {
		currentType = type;
	}
	
	public void declareConstByValue(Type type, int value) {
		IdConst c = new IdConst(type, value);
		Yaka.tabIdent.add(currentIdent, c);
	}
	
	public void declareConstByIdent(String ident) {
		if (Yaka.tabIdent.exists(ident)) {
			Ident tmp = Yaka.tabIdent.find(ident);
			if (tmp instanceof IdConst) {
				IdConst c2 = (IdConst)tmp;
				IdConst c1 = new IdConst(c2.type, c2.value);
				Yaka.tabIdent.add(currentIdent, c1);
			} else {
				// Throw error
			}
		} else {
			// Throw error
		}
	}
	
	public void declareVar() {
		countVar++;
		IdVar v = new IdVar(currentType, countVar*-2);
		Yaka.tabIdent.add(currentIdent, v);
	}
}
