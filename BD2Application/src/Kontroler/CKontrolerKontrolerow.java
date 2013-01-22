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
		// TODO: do cdao
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
		System.out.println("catched menu action in kontrolerKontrolerów");
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
			case USUN_PRACOWNIKA_MENU:
				updateHelper(ETypModulu.LISTA_PRACOWNIKOW,new CKontrolerUsuwaniaPracownikow(model,widok));
				break;
			default:
				break;
			}
		}
	}
	
	private CWidok widok;
	private CDAO model;
	private CKontroler biezacyKontroler;
}