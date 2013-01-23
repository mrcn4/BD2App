package Kontroler;

import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;

import Widok.CWidok;

import Komunikator.CZdarzenie;
import Komunikator.ETypModulu;
import Model.CDAO;

public class CKontrolerKontrolerow implements Observer{
	
	public CKontrolerKontrolerow() {
		widok = new CWidok();
		model = new CDAO();
		String url =
				"jdbc:oracle:thin:kkrosman/kkrosman@ikar.elka.pw.edu.pl:1521/elka.elka.pw.edu.pl";
		try {
			model.polacz(url, "kkrosman", "kkrosman");
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		final Observer thisObject = this;
		javax.swing.SwingUtilities.invokeLater(new Runnable() {
            public void run() {
            	widok.utworzIPokazGUI();
            	widok.dodajObserwatoraMenu(thisObject);
            }
        });
		
	}
	
	public static void main(String[] args) {
		System.out.println("main enter");
		CKontrolerKontrolerow kk= new CKontrolerKontrolerow();
		System.out.println("main leave");
	}

	private void updateHelper(final ETypModulu typ, final CKontroler kontroler)
	{
		biezacyKontroler = kontroler;
		javax.swing.SwingUtilities.invokeLater(new Runnable() {
            public void run() {
            	widok.wyswietlModul(typ, kontroler, kontroler.dajArgumenty(typ));
            }
        });
	}
	@Override
	public void update(Observable obserwowany, Object arg) {		
		if (arg instanceof CZdarzenie) {
			CZdarzenie zdarzenie = (CZdarzenie) arg;
			switch(zdarzenie.dajTyp())
			{
			case DODAJ_PRACOWNIKA_MENU:
				updateHelper(ETypModulu.EDYTUJ_PRACOWNIKA_FORMULARZ,new CKontrolerDodawaniaPracownikow(model,widok));
				break;
			case EDYTUJ_PRACOWNIKA_MENU:
				updateHelper(ETypModulu.LISTA_PRACOWNIKOW,new CKontrolerEdycjiPracownikow(model,widok));
				break;
			default:
				System.err.println("Nie zaimplementowana akcja menu!");
				System.exit(-1);
			}
		}
	}
	
	private CWidok widok;
	private CDAO model;
	private CKontroler biezacyKontroler;
}
