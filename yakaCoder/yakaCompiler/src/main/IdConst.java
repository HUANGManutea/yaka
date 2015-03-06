package main;

import main.Constantes.Type;

public class IdConst extends Ident {
	public int value;
	
	public IdConst(Type type, int value) {
		super(type);
		this.value = value;
	}
}
