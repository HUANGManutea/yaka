package main;

import main.Constantes.*;

public class Affectation {
	private String currentIdent;
	private boolean erreurAffect=false;
	
	public void setCurrentIdent(String ident){
		if (Yaka.tabIdent.exists(ident)) {
			currentIdent = ident;
			if(Yaka.tabIdent.find(currentIdent) instanceof IdConst){
				System.out.println("Erreur affectation dans une constante.");
				erreurAffect = true;
				Yaka.yvm.erreur();
			}
		} else {
			System.out.println("Ident "+ident+" non déclaré");
			Yaka.yvm.erreur();
		}
	}
	
	public void doAffect(){
		if(!erreurAffect){
			IdVar v = (IdVar) Yaka.tabIdent.find(currentIdent);
			Type t = Yaka.expression.popType();
			if (v.type != t) {
				System.out.println("Erreur de type dans l'affectation, ligne " + Yaka.token.beginLine);
				Yaka.yvm.erreur();	
			}
			Yaka.yvm.istore(v.offset);
		}
	}
}
