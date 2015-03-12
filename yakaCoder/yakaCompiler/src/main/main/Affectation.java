package main;

import main.Constantes.*;

public class Affectation {
	private String currentIdent;
	
	public void setCurrentIdent(String ident) {
		if (Yaka.tabIdent.exists(ident)) {
			currentIdent = ident;
		} else {
			// Throw error
			System.out.println(ident + " doesn't exist !");
		}
	}
	
	public void doAffect() {
		IdVar v = (IdVar) Yaka.tabIdent.find(currentIdent);
		Type t = Yaka.expression.popType();
		if (v.type != t) {
			// type error
		}
		
		Yaka.yvm.istore(v.offset);
	}
}
