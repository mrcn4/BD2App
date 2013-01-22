package Widok;

import java.util.ArrayList;
import java.util.Observable;

import javax.swing.JComponent;

public abstract class CSzablonWidoku extends Observable {
	abstract void ustawArgumenty(ArrayList<Object> ListaArgumentow);
	abstract JComponent dajKomponent();
}
