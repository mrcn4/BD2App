package Widok;

import java.util.ArrayList;
import java.util.Iterator;

import javax.swing.DefaultListModel;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.ListSelectionModel;

import Model.CPracownikIT;

public class CListaPracownikow extends CSzablonWidoku {

	private DefaultListModel listModel;
	private JList list;
	private ArrayList<CPracownikIT> listaPracownikow;

	@Override
	void ustawArgumenty(ArrayList<Object> ListaArgumentow) {
		if((ListaArgumentow != null) && (ListaArgumentow.get(0) instanceof ArrayList<?>))
		{
			listaPracownikow = ((ArrayList<CPracownikIT>)ListaArgumentow.get(0));
			System.err.println("ustawArgumenty Lista argumentów: ok");
			Iterator<CPracownikIT> iterator = listaPracownikow.iterator();
			while (iterator.hasNext())
			{
				CPracownikIT next = iterator.next();
				System.err.println("Mam pracownika o id: " + next.dajID());
			}
		}
		else
		{
			System.err.println("Błędny parametr w CListapracowników ustawArgumenty");
			System.exit(-1);
		}
	}

	@Override
	JComponent dajKomponent() {
		// TODO Auto-generated method stub
		JLabel label = new JLabel("Lista pracownikow");
		
        listModel = new DefaultListModel();
        listModel.addElement("Koczo");
        listModel.addElement("Korcz");
        listModel.addElement("Mariusz");

        //Create the list and put it in a scroll pane.
        list = new JList(listModel);
        list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        list.setSelectedIndex(0);
		return list;
	}

}
