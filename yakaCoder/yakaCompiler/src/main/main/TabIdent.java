package main;

import java.util.HashMap;

public class TabIdent {
	private IdFunc currentFunction;
	private HashMap<String,Ident> table;
	
	public TabIdent() {
		currentFunction = null;
		table = new HashMap<String, Ident>();
	}
	
	public void endFunction() {
		currentFunction = null;
	}
	
	public Ident find(String key) {
		if (currentFunction != null && currentFunction.tabIdent.exists(key)) return currentFunction.tabIdent.find(key);
		else return table.get(key); 
	}
	
	public boolean exists(String key) {
		return table.containsKey(key) || (currentFunction != null && currentFunction.tabIdent.exists(key));
	}
	
	public void add(String key, Ident value) {
		if (value instanceof IdFunc) {
			// Il faut ajouter la fonction au TabIdent global
			currentFunction = (IdFunc)value;
			table.put(key, currentFunction);
		} else if (currentFunction != null) {
			// On est dans le TabIdent global, il faut ajouter la var/const au TabIdent de la fonction en cours
			currentFunction.tabIdent.add(key, value);
		} else {
			// On est dans le TabIdent de la fonction, on peut ajouter la var/const
			table.put(key, value);
		}
	}
}
