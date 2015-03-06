package main;

import main.Constantes.Type;

public class IdVar extends Ident {
	public int offset;
	
	public IdVar(Type type, int offset) {
		super(type);
		this.offset = offset;
	}
}
