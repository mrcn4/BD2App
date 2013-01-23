package Kontroler;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Observable;

import Widok.IWidok;
import Widok.MessageBoxHelper;

import Komunikator.CZdarzenie;
import Komunikator.ETypModulu;
import Model.CPracownikIT;
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
		
		ArrayList<Object> listaArgumentow = new ArrayList<Object>();
		
		//utworz nowego pracownika
		CPracownikIT pracownikIT = new CPracownikIT();
		pracownikIT.ustawImie("Mariusz");
		pracownikIT.ustawNazwisko("Korcz");
		pracownikIT.ustawDoswiadczenie(1);
		listaArgumentow.add(pracownikIT);
		
		//wez liste umiejetnosci
		HashMap<Integer, String> listaUmiejetosci = model.dajUslugi();
		listaArgumentow.add(listaUmiejetosci);
		
		return listaArgumentow;
	}

	@Override
	public void update(Observable o, Object arg) {
		if (arg instanceof CZdarzenie) {
			CZdarzenie zdarzenie = (CZdarzenie) arg;
			System.out.println(zdarzenie.dajTyp());
			switch(zdarzenie.dajTyp())
			{
				case ZAKONCZONO_EDYCJE_PRACOWNIKA:
					CPracownikIT pracownikDoDodania = (CPracownikIT) zdarzenie.dajParametry().get(0);
					System.out.println("pracownikDoDodania: " + pracownikDoDodania);
					model.modyfikujPracownikaIT(pracownikDoDodania);
					break;
				default:
					System.out.println("Niepoprawne dane otrzymane od widoku: CKontrolerDodawaniaPracownikow");
					System.exit(-1);
					break;
			}
			
		}
	}

}
