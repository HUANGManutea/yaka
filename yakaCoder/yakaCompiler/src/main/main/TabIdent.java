package main;

import java.util.HashMap;

public class TabIdent {
	private HashMap<String,Ident> table;
	
	public TabIdent() {
		table = new HashMap<String, Ident>();
	}
	
	public Ident find(String key) {
		return table.get(key);
	}
	
	public boolean exists(String key) {
		return table.containsKey(key);
	}
	
	public void add(String key, Ident value) {
		table.put(key, value);
	}
}
