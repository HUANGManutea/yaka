package main;

import main.Constantes.*;

public class InputOutput {
	public void readInt(String ident) {
		if (Yaka.tabIdent.exists(ident)) {
			Yaka.yvm.lireEnt(((IdVar)Yaka.tabIdent.find(ident)).offset);
		} else {
			// Throw error
		}
	}
	
	public void writeExpression() {
		Type t = Yaka.expression.popType();
		if (t == Type.INTEGER) {
			Yaka.yvm.ecrireEnt();
		} else if (t == Type.BOOLEAN) {
			Yaka.yvm.ecrireBool();
		} else {
			// type error
		}
	}
	
	public void writeString(String str) {
		Yaka.yvm.ecrireChaine(str);
	}
}
