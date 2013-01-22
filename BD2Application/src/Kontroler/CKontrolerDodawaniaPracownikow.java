package Kontroler;

import java.util.ArrayList;
import java.util.Observable;

import Widok.IWidok;

import Komunikator.ETypModulu;
import Model.IModel;

public class CKontrolerDodawaniaPracownikow extends CKontroler {

	IModel model;
	IWidok widok;
	
	public CKontrolerDodawaniaPracownikow(IModel mmodel, IWidok wwidok) {
		model = mmodel;
		widok = wwidok;
	}
	@Override
	ArrayList<Object> dajArgumenty(ETypModulu typModulu) {
		System.out.println("wydano argumenty CKontrolerDodawaniaPracownikow");
		return null;
	}

	@Override
	public void update(Observable o, Object arg) {
		
		System.out.println("Złapano jakiśtam ");
	}

}
