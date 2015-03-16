package main;

import java.util.List;

import main.Constantes.Type;

public class IdFunc extends Ident {	
	public TabIdent tabIdent;
	public List<Type> paramTypes;

	public IdFunc(Type type) {
		super(type);
		tabIdent = new TabIdent();
	}
}
