package Komunikator;

import java.util.ArrayList;

public class CZdarzenie {
	ETypZdarzenia typ;
	ArrayList<Object> parametry;
	public ETypZdarzenia dajTyp() {
		return typ;
	}
	public void ustawTyp(ETypZdarzenia typ) {
		this.typ = typ;
	}
	public ArrayList<Object> dajParametry() {
		return parametry;
	}
	public void ustawParametry(ArrayList<Object> parametry) {
		this.parametry = parametry;
	}
	
}
