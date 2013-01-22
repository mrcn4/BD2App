package Kontroler;

import java.util.ArrayList;
import java.util.Observable;

import Komunikator.ETypModulu;
import Model.IModel;
import Widok.IWidok;

public class CKontrolerEdycjiPracownikow extends CKontroler {
	
	IModel model;
	IWidok widok;
	
	public CKontrolerEdycjiPracownikow(IModel mmodel, IWidok wwidok) {
		model = mmodel;
		widok = wwidok;
	}
	
	@Override
	public void update(Observable o, Object arg) {
		// TODO Auto-generated method stub
		System.out.println("update CKontrolerEdycjiPracownikow");
	}

	@Override
	ArrayList<Object> dajArgumenty(ETypModulu typModulu) {
		System.out.println("dajArgumenty CKontrolerEdycjiPracownikow");
		return null;
	}

}
