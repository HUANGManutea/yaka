package main;

import main.Constantes.Type;

public class Declaration {
	private String currentIdent;
	private Type currentType;
	private int countVar;
	
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
	
	public void declareVar(String ident){
		setCurrentIdent(ident);
		countVar++;
		IdVar v = new IdVar(currentType, countVar*-2);
		Yaka.tabIdent.add(currentIdent, v);
	}
	
	public void reserveMemory() {
		Yaka.yvm.ouvrePrinc(countVar);
	}
}
