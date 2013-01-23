package Widok;

import java.util.ArrayList;
import java.util.Observer;
import Komunikator.ETypModulu;

public interface IWidok {
	void utworzIPokazGUI();
	void dodajObserwatoraMenu(Observer NewObserver);
	void wyswietlModul(ETypModulu TypModuluDoWyswieltenia, Observer ObserwatorModulu, ArrayList<Object> Argumenty);
}