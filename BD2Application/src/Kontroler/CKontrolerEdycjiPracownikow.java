package Kontroler;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Observable;

import Komunikator.CZdarzenie;
import Komunikator.ETypModulu;
import Model.CPracownikIT;
import Model.IModel;
import Widok.IWidok;
import Widok.MessageBoxHelper;

public class CKontrolerEdycjiPracownikow extends CKontroler {
	
	IModel model;
	IWidok widok;
	Integer id = 0;
	ArrayList<CPracownikIT> pracownicy = new ArrayList<CPracownikIT>();
	
	public CKontrolerEdycjiPracownikow(IModel mmodel, IWidok wwidok) {
		model = mmodel;
		widok = wwidok;
	}
	
	@Override
	public void update(Observable o, Object arg) {
		if (arg instanceof CZdarzenie)
		{
			CZdarzenie zdarzenie = (CZdarzenie) arg;
			switch(zdarzenie.dajTyp())
			{
			case WYBRANO_ID_PRACOWNIKA_EDYCJA:
				id = (Integer)zdarzenie.dajParametry().get(0);
				widok.wyswietlModul(ETypModulu.EDYTUJ_PRACOWNIKA_FORMULARZ, this, dajArgumenty(ETypModulu.EDYTUJ_PRACOWNIKA_FORMULARZ));
				break;
			case WYBRANO_ID_PRACOWNIKA_USUN:
				id = (Integer)zdarzenie.dajParametry().get(0);
				Iterator<CPracownikIT> iterator = pracownicy.iterator();
				CPracownikIT next = null;
				while(iterator.hasNext())
				{
					next = iterator.next();
					if(next.dajID() == id)
					{						
						break;
					}
				}
				if(next != null)
				{
					if(model.usunPracownika(next))
					{
						MessageBoxHelper.infoBox("Modyfikacja zakończona powodzeniem!", "Edycja pracownika");
					}
					else
					{
						MessageBoxHelper.infoBox("Modyfikacja nie powiodła się!", "Edycja pracownika");
					}
				}
				widok.wyswietlModul(ETypModulu.LISTA_PRACOWNIKOW, this, dajArgumenty(ETypModulu.LISTA_PRACOWNIKOW));
				break;
			case ZAKONCZONO_EDYCJE_PRACOWNIKA:
				CPracownikIT pracownikDoDodania = (CPracownikIT) zdarzenie.dajParametry().get(0);
				System.out.println("pracownikDoDodania: " + pracownikDoDodania);
				if(model.modyfikujPracownikaIT(pracownikDoDodania))
				{
					MessageBoxHelper.infoBox("Modyfikacja zakończona powodzeniem!", "Edycja pracownika");
				}
				else
				{
					MessageBoxHelper.infoBox("Modyfikacja nie powiodła się!", "Edycja pracownika");
				}
				break;
			default:
				System.err.println("Niepoprawa akcja w CKontroler edycji");
				System.exit(-1);
				break;
			}
		}
	}

	@Override
	ArrayList<Object> dajArgumenty(ETypModulu typModulu) {
		ArrayList<Object> parametry = new ArrayList<Object>();
		switch(typModulu)
		{
		case LISTA_PRACOWNIKOW:
			pracownicy = model.dajPracownikowIT();
			parametry.add(pracownicy);
			break;
		case EDYTUJ_PRACOWNIKA_FORMULARZ:
			Iterator<CPracownikIT> iterator = pracownicy.iterator();
			while(iterator.hasNext())
			{
				CPracownikIT next = iterator.next();
				if(next.dajID() == id)
				{
					parametry.add(next);
					parametry.add(model.dajUslugi());
					break;
				}
			}
			break;
		}
		return parametry;
	}

}
