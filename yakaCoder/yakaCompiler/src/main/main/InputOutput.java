package main;

import main.Constantes.*;

public class InputOutput {
	public void readInt(String ident) {
		if (Yaka.tabIdent.exists(ident)) {
			Yaka.yvm.lireEnt(((IdVar)Yaka.tabIdent.find(ident)).offset);
		} else {
			System.out.println("InputOutput erreur affectation variable");
			Yaka.yvm.erreur();
		}
	}
	
	public void writeExpression() {
		Type t = Yaka.expression.popType();
		if (t == Type.INTEGER) {
			Yaka.yvm.ecrireEnt();
		} else if (t == Type.BOOLEAN) {
			Yaka.yvm.ecrireBool();
		} else {
			System.out.println("InputOutput type inconnu");
			Yaka.yvm.erreur();
		}
	}
	
	public void writeString(String str) {
		Yaka.yvm.ecrireChaine(str);
	}
}
