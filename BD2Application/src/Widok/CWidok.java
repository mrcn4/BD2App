package Widok;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.ButtonGroup;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JRadioButton;

import Komunikator.CZdarzenie;
import Komunikator.ETypModulu;
import Komunikator.ETypZdarzenia;

public class CWidok extends Observable implements IWidok, ActionListener {

    //constants for action commands
    protected final static String DODAJ_PRACOWNIKOW = "dodaj";
    protected final static String EDYTUJ_PRACOWNIKA = "edytuj";
    protected final static String USUN_PRACOWNIKA = "usun";
    private JFrame frame;
    CSzablonWidoku biezacyWidok;
    private final static int WINDOW_WIDTH = 800;
    private final static int WINDOW_HEIGHT = 600;
    
	@Override
	public void dodajObserwatoraMenu(Observer NewObserver) {
		// TODO Auto-generated method stub
		System.out.println("Dodaje obserwatora menu");
		deleteObservers();
		addObserver(NewObserver);
	}

	@Override
	public void wyswietlModul(ETypModulu TypModuluDoWyswieltenia,
			Observer ObserwatorModulu, ArrayList<Object> Argumenty) {
		// TODO Auto-generated method stub
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
		menuItem = new JMenuItem("Edytuj pracownika");
		menuItem.setActionCommand(EDYTUJ_PRACOWNIKA);
		menuItem.addActionListener(this);
		submenu.add(menuItem);
		menuItem = new JMenuItem("Usuń pracowników");
		menuItem.setActionCommand(USUN_PRACOWNIKA);
		menuItem.addActionListener(this);
		submenu.add(menuItem);
		menu.add(submenu);
		
		//build the second submenu in menu
		submenu = new JMenu("Pracownicy Handlowi");
		submenu.setMnemonic(KeyEvent.VK_S);
		menuItem = new JMenuItem("(not implemented)");
		submenu.add(menuItem);
		menu.add(submenu);
		
		//add the first menu
		menuBar.add(menu);

		menu = new JMenu("Another Menu");
		menu.setMnemonic(KeyEvent.VK_N);
		menu.getAccessibleContext().setAccessibleDescription(
		        "This menu does nothing");
		menuItem = new JMenuItem("Nothing here");
		menu.add(menuItem);
		menuBar.add(menu);
		
		frame.setJMenuBar(menuBar);
	}
	
	protected JComponent createOptionControls() {
        JLabel label1 = new JLabel("Decoration options for subsequently created frames:");
        ButtonGroup bg1 = new ButtonGroup();
 
        //Create the buttons
        JRadioButton rb1 = new JRadioButton();
        rb1.setText("Look and feel decorated");
        rb1.addActionListener(this);
        rb1.setSelected(true);
        bg1.add(rb1);
        //
        JRadioButton rb2 = new JRadioButton();
        rb2.setText("Window system decorated");
        rb2.addActionListener(this);
        bg1.add(rb2);
        //
        JRadioButton rb3 = new JRadioButton();
        rb3.setText("No decorations");
        rb3.addActionListener(this);
        bg1.add(rb3);
        //
        
        //Add everything to a container.
        Box box = Box.createVerticalBox();
        /*box.add(label1);
        box.add(Box.createVerticalStrut(5)); //spacer
        box.add(rb1);
        box.add(rb2);
        box.add(rb3);*/
        
        //Add some breathing room.
        box.setBorder(BorderFactory.createEmptyBorder(300,600,10,10));
 
        return box;
    }
	
	@Override
	public void utworzIPokazGUI() {
		// TODO Auto-generated method stub
		JFrame.setDefaultLookAndFeelDecorated(true);
 
		  //Instantiate the controlling class.
        frame = new JFrame("FrameDemo2");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
 
        //Add components to it.
        Container contentPane = frame.getContentPane();
        contentPane.add(this.createOptionControls(),
                        BorderLayout.CENTER);
//      contentPane.add(this.createButtonPane(),
//                        BorderLayout.PAGE_END);
        createAndAddMenu();
        frame.getRootPane().setDefaultButton(null);
 
        //Display the window
        frame.pack();
        frame.setSize(WINDOW_WIDTH, WINDOW_HEIGHT);
        frame.setLocationRelativeTo(null); //center it
        frame.setVisible(true);
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
        else if (USUN_PRACOWNIKA == command)
        {
        	zdarzenie.ustawTyp(ETypZdarzenia.USUN_PRACOWNIKA_MENU);
        	setChanged();
        	notifyObservers(zdarzenie);
        }
        else
        {
        	System.out.println("Nieznana komenda menu!");
        }
	}


}
