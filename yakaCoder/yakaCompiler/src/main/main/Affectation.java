package main;

import main.Constantes.*;

public class Affectation {
	private String currentIdent;
	private boolean erreurAffect=false;
	
	public void setCurrentIdent(String ident){
		erreurAffect = false;
		
		if (Yaka.tabIdent.exists(ident)) {
			currentIdent = ident;
			if(Yaka.tabIdent.find(currentIdent) instanceof IdConst){
				System.out.println("Erreur affectation dans une constante, ligne " + Yaka.token.beginLine);
				erreurAffect = true;
				Yaka.yvm.erreur();
			} else if(Yaka.tabIdent.find(currentIdent) instanceof IdFunc){
				System.out.println("Erreur affectation a une fonction, ligne " + Yaka.token.beginLine);
				erreurAffect = true;
				Yaka.yvm.erreur();
			}
		} else {
			System.out.println("Ident "+ident+" non déclaré");
			Yaka.yvm.erreur();
		}
	}
	
	public void doAffect(){
		Type t = Yaka.expression.popType();
		
		if(!erreurAffect){
			IdVar v = (IdVar) Yaka.tabIdent.find(currentIdent);
			if (v.type != t) {
				System.out.println("Erreur de type dans l'affectation, ligne " + Yaka.token.beginLine);
				Yaka.yvm.erreur();	
			}
			Yaka.yvm.istore(v.offset);
		}
	}
}
