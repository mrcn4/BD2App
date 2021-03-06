package Model;

import java.util.ArrayList;
import java.util.HashMap;
/**
 * Model interface
 */
public interface IModel {
	void polacz(String url,String user,String password)throws Exception ;
	void rozlacz();
	ArrayList<CPracownikIT> dajPracownikowIT();
	Boolean modyfikujPracownikaIT(CPracownikIT PracownikDoModyfikacji);
	Boolean usunPracownika(CPracownikIT PracownikDoUsuniecia);
	Boolean dodajPracownika(CPracownikIT PracownikDoDodania);
	HashMap<Integer, String> dajUslugi();
}
	
