package Model;

import java.util.ArrayList;
/**
 * Model interface
 */
public interface IModel {
	ArrayList<CPracownikIT> dajPracownikowIT();
	Boolean modyfikujPracownikaIT(CPracownikIT PracownikDoModyfikacji);
	Boolean usunPracownika(CPracownikIT PracownikDoUsuniecia);
	Boolean dodajPracownika(CPracownikIT PracownikDoDodania);
}
	
