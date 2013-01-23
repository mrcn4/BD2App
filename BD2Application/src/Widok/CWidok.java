package Widok;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;

import Komunikator.CZdarzenie;
import Komunikator.ETypModulu;
import Komunikator.ETypZdarzenia;

public class CWidok extends Observable implements IWidok, ActionListener {

    //constants for action commands
    protected final static String DODAJ_PRACOWNIKOW = "dodaj";
    protected final static String EDYTUJ_PRACOWNIKA = "edytuj";
    protected final static String TYTUŁ_OKNA =  "Zarządzanie projektami informatycznymi";
    private final static int WINDOW_WIDTH = 800;
    private final static int WINDOW_HEIGHT = 400;
    private JFrame frame;
    private CSzablonWidoku biezacyWidok;
    
	@Override
	public void dodajObserwatoraMenu(Observer NewObserver) {
		System.out.println("Dodaje obserwatora menu");
		deleteObservers();
		addObserver(NewObserver);
	}

	@Override
	public void wyswietlModul(ETypModulu TypModuluDoWyswieltenia,
			Observer ObserwatorModulu, ArrayList<Object> Argumenty) {
		System.out.println("Wyświetlam moduł: " + TypModuluDoWyswieltenia);
		switch(TypModuluDoWyswieltenia)
		{
			case EDYTUJ_PRACOWNIKA_FORMULARZ:
				biezacyWidok = new CEdycjaDanychPracownika();
				break;
			case LISTA_PRACOWNIKOW:
				biezacyWidok = new CListaPracownikow();
				break;
			default:
				System.out.println("not implemented");
				System.exit(-1);
		}
		biezacyWidok.deleteObservers();
		biezacyWidok.addObserver(ObserwatorModulu);
		biezacyWidok.ustawArgumenty(Argumenty);
		
		//ustaw komponenty w oknie
        Container contentPane = frame.getContentPane();
        contentPane.removeAll();
        contentPane.add(biezacyWidok.dajKomponent());
        frame.pack();
        frame.setSize(WINDOW_WIDTH, WINDOW_HEIGHT);
	}

	protected void createAndAddMenu()
	{
		JMenuItem menuItem;
		
		JMenuBar menuBar = new JMenuBar();

		//Build the first menu.
		JMenu menu = new JMenu("Ewidencja pracowników");
		menu.setMnemonic(KeyEvent.VK_A);
		menu.getAccessibleContext().setAccessibleDescription(
		        "Ewidencja pracowników");

		//build the first submenu in menu
		JMenu submenu = new JMenu("Pracownicy IT");
		submenu.setMnemonic(KeyEvent.VK_S);
		menuItem = new JMenuItem("Dodaj pracowników");
		menuItem.addActionListener(this);
		menuItem.setActionCommand(DODAJ_PRACOWNIKOW);
		submenu.add(menuItem);
		menuItem = new JMenuItem("Edytuj pracowników");
		menuItem.setActionCommand(EDYTUJ_PRACOWNIKA);
		menuItem.addActionListener(this);
		submenu.add(menuItem);
		menu.add(submenu);
		
		//build the second submenu in menu
		submenu = new JMenu("Pracownicy Handlowi");
		submenu.setMnemonic(KeyEvent.VK_H);
		menuItem = new JMenuItem("Przeglądaj listę pracowników");
		submenu.add(menuItem);
		menuItem = new JMenuItem("Dodaj pracownika ");
		submenu.add(menuItem);
		menuItem = new JMenuItem("Usuń pracownika");
		submenu.add(menuItem);
		menuItem = new JMenuItem("Edytuj dane pracownika");
		submenu.add(menuItem);
		menuItem = new JMenuItem("Przeglądaj przypisanych klientów");
		submenu.add(menuItem);
		menu.add(submenu);
		menuBar.add(menu);
		
		menu = new JMenu("Katalog usług");
		menu.setMnemonic(KeyEvent.VK_H);
		menuItem = new JMenuItem("Przeglądaj listę usług");
		menu.add(menuItem);
		menuItem = new JMenuItem("Dodaj usługę");
		menu.add(menuItem);
		menuItem = new JMenuItem("Usuń usługę");
		menu.add(menuItem);
		menuItem = new JMenuItem("Edytuj dane usługi");
		menu.add(menuItem);
		menu.add(menu);
		
		//add the first menu
		menuBar.add(menu);

		frame.setJMenuBar(menuBar);
	}
	
	@Override
	public void utworzIPokazGUI() {
		JFrame.setDefaultLookAndFeelDecorated(true);
		
		  //Instantiate the controlling class.
        frame = new JFrame(TYTUŁ_OKNA);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
 
        createAndAddMenu();
        createAndShowMainScreen();
        //Display the window
        frame.pack();
        frame.setSize(WINDOW_WIDTH, WINDOW_HEIGHT);
        frame.setLocationRelativeTo(null); //center it
        frame.setVisible(true);
	}

	private void createAndShowMainScreen() {
		Container contentPane = frame.getContentPane();
		contentPane.removeAll();
		
		JPanel panelY = new JPanel();
		panelY.setLayout(new BoxLayout(panelY, BoxLayout.Y_AXIS));
		
		JPanel panel = new JPanel();
		panel.setLayout(new BoxLayout(panel, BoxLayout.X_AXIS));
		
		JLabel label = new JLabel("Sacha to ....");
		
		JTextField textField = new JTextField();
		textField.setColumns(30);

		panel.add(label);
		panel.add(textField);

		panelY.add(Box.createVerticalGlue());
		panelY.add(panel);
		panelY.add(Box.createVerticalGlue());
		
		contentPane.add(panelY);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
        String command = e.getActionCommand();
        System.out.println("Komenda to:" + command);
        CZdarzenie zdarzenie = new CZdarzenie();
        if(DODAJ_PRACOWNIKOW == command)
        {
        	zdarzenie.ustawTyp(ETypZdarzenia.DODAJ_PRACOWNIKA_MENU);
        	setChanged();
        	notifyObservers(zdarzenie);
        }
        else if (EDYTUJ_PRACOWNIKA == command)
        {
        	zdarzenie.ustawTyp(ETypZdarzenia.EDYTUJ_PRACOWNIKA_MENU);
        	setChanged();
        	notifyObservers(zdarzenie);
        }
        else
        {
        	System.out.println("Nieznana komenda menu!");
        }
	}


}
