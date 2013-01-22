package Widok;

import java.util.ArrayList;

import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class CListaPracownikow extends CSzablonWidoku {

	@Override
	void ustawArgumenty(ArrayList<Object> ListaArgumentow) {
		// TODO Auto-generated method stub
		
	}

	@Override
	JComponent dajKomponent() {
		// TODO Auto-generated method stub
		JLabel label = new JLabel("Lista pracownikooow");
		return label;
	}

}
