package Widok;

import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Iterator;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import Komunikator.CZdarzenie;
import Komunikator.ETypZdarzenia;
import Model.CPracownikIT;

public class CListaPracownikow extends CSzablonWidoku implements ListSelectionListener, ActionListener {

	private DefaultListModel listModel;
	private JList list;
	private ArrayList<CPracownikIT> listaPracownikow;
	private JButton usunButton;
	private JButton edytujButton;
	static final private String EDYTUJ_PRACOWNIKA = "edytuj";
	static final private String USUN_PRACOWNIKA = "usun";

	@SuppressWarnings("unchecked")
	@Override
	void ustawArgumenty(ArrayList<Object> ListaArgumentow) {
		if((ListaArgumentow != null) && (ListaArgumentow.get(0) instanceof ArrayList<?>))
		{
			listaPracownikow = ((ArrayList<CPracownikIT>)ListaArgumentow.get(0));
		}
		else
		{
			System.err.println("Błędny parametr w CListapracowników ustawArgumenty");
			System.exit(-1);
		}
	}

	@Override
	JComponent dajKomponent() {
		JLabel label = new JLabel("Lista pracownikow: ");
		
        listModel = new DefaultListModel();
		Iterator<CPracownikIT> iterator = listaPracownikow.iterator();
		while (iterator.hasNext())
		{
			CPracownikIT next = iterator.next();
			listModel.addElement(next);
		}

        //Create the list and put it in a scroll pane.
        list = new JList(listModel);
        list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        list.setSelectedIndex(0);
        list.addListSelectionListener(this);
        
		edytujButton = new JButton("Edytuj");
        edytujButton.setActionCommand(EDYTUJ_PRACOWNIKA);
        edytujButton.addActionListener(this);
        edytujButton.setEnabled(true);

        usunButton = new JButton("Usuń pracownika");
        usunButton.setActionCommand(USUN_PRACOWNIKA);
        usunButton.addActionListener(this);
        usunButton.setEnabled(true);
        
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
        label.setAlignmentX(Component.CENTER_ALIGNMENT);
        mainPanel.add(label);
        
        list.setAlignmentX(Component.CENTER_ALIGNMENT);
        mainPanel.add(list);
        mainPanel.add(Box.createVerticalStrut(10));
        
        edytujButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        mainPanel.add(edytujButton);
        
        mainPanel.add(Box.createVerticalStrut(10));
        
        usunButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        mainPanel.add(usunButton);
        
		return mainPanel;
	}

	@Override
	public void valueChanged(ListSelectionEvent arg0) {
		
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		String actionCommand = arg0.getActionCommand();
		if (actionCommand == EDYTUJ_PRACOWNIKA)
		{
			Integer index = list.getSelectedIndex();
			if(index != -1)
			{
				CPracownikIT elementAt = (CPracownikIT)list.getModel().getElementAt(index);
				CZdarzenie zdarzenie = new CZdarzenie();
				zdarzenie.ustawTyp(ETypZdarzenia.WYBRANO_ID_PRACOWNIKA_EDYCJA);
				ArrayList<Object> parametry = new ArrayList<Object>();
				parametry.add(elementAt.dajID());
				zdarzenie.ustawParametry(parametry);
				setChanged();
				notifyObservers(zdarzenie);
			}
		}
		else if (actionCommand == USUN_PRACOWNIKA)
		{
			Integer index = list.getSelectedIndex();
			if(index != -1)
			{
				CPracownikIT elementAt = (CPracownikIT)list.getModel().getElementAt(index);
				CZdarzenie zdarzenie = new CZdarzenie();
				zdarzenie.ustawTyp(ETypZdarzenia.WYBRANO_ID_PRACOWNIKA_USUN);
				ArrayList<Object> parametry = new ArrayList<Object>();
				parametry.add(elementAt.dajID());
				zdarzenie.ustawParametry(parametry);
				setChanged();
				notifyObservers(zdarzenie);
			}
		}
		
	}

}
