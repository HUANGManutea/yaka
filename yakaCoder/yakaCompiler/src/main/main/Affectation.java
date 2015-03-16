package main;

import main.Constantes.*;

public class Affectation {
	private String currentIdent;
	
	public void setCurrentIdent(String ident){
		if (Yaka.tabIdent.exists(ident)) {
			currentIdent = ident;
		} else {
			System.out.println("Ident "+ident+" non déclaré");
			Yaka.yvm.erreur();
		}
	}
	
	public void doAffect(){
		IdVar v = (IdVar) Yaka.tabIdent.find(currentIdent);
		Type t = Yaka.expression.popType();
		if (v.type != t) {
			System.out.println("Erreur de type dans l'affectation, ligne " + Yaka.token.beginLine);
			Yaka.yvm.erreur();
		}
		
		Yaka.yvm.istore(v.offset);
	}
}
