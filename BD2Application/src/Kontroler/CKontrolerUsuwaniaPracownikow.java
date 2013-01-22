package Kontroler;

import java.util.ArrayList;
import java.util.Observable;

import Widok.IWidok;

import Komunikator.ETypModulu;
import Model.IModel;

public class CKontrolerUsuwaniaPracownikow extends CKontroler {
	
	IModel model;
	IWidok widok;
	
	public CKontrolerUsuwaniaPracownikow(IModel mmodel, IWidok wwidok) {
		// TODO Auto-generated constructor stub
		model = mmodel;
		widok = wwidok;
	}
	
	@Override
	public void update(Observable o, Object arg) {
		// TODO Auto-generated method stub
		System.out.println("update CKontrolerUsuwaniaPracownikow");
	}

	@Override
	ArrayList<Object> dajArgumenty(ETypModulu typModulu) {
		// TODO Auto-generated method stub
		System.out.println("dajArgumenty CKontrolerUsuwaniaPracownikow");
		return null;
	}

}
