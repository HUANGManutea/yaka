package main;

import main.Constantes.Type;

public class IdFunc extends Ident {	
	public TabIdent tabIdent;

	public IdFunc(Type type) {
		super(type);
		tabIdent = new TabIdent();
	}
}
