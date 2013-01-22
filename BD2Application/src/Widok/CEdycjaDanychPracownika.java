package Widok;

import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.SpringLayout;

import Komunikator.CZdarzenie;
import Komunikator.ETypZdarzenia;
import Model.CPracownikIT;


public class CEdycjaDanychPracownika extends CSzablonWidoku implements ActionListener, ItemListener {
	private static final int GAP = 10;
	private static final String ZAPISZ = "zapisz";
	private static final String ANULUJ = "anuluj";
	
	JTextField imiePole, nazwiskoPole, zatrudnionyOdPole, doswiadczeniePole;
	private CPracownikIT pracownik;
	private HashMap<Integer, String> listaUmiejetnosci;
	
	@Override
	void ustawArgumenty(ArrayList<Object> ListaArgumentow) {
		Object o0 = ListaArgumentow.get(0);
		Object o1 = ListaArgumentow.get(1);
		if(o0 instanceof CPracownikIT)
		{
			pracownik = (CPracownikIT) o0;
		}
		if(o1 instanceof HashMap<?, ?>)
		{
			listaUmiejetnosci = (HashMap<Integer, String>) o1;
		}
	}

	@Override
	JComponent dajKomponent() {
		JPanel textInputPanel = new JPanel(new SpringLayout());

        String[] labelStrings = {
            "Imię: ",
            "Nazwisko: ",
            "Zatrudniony od: ",
            "Doświadczenie: "
        };

        JLabel[] labels = new JLabel[labelStrings.length];
        JComponent[] fields = new JComponent[labelStrings.length];
        int fieldNum = 0;

        //Create the text field and set it up.
    	imiePole  = new JTextField();
    	imiePole.setColumns(30);
    	imiePole.setText(pracownik.dajImie());
        fields[fieldNum++] = imiePole;

        nazwiskoPole = new JTextField();
        nazwiskoPole.setColumns(30);
        nazwiskoPole.setText(pracownik.dajNazwisko());
        fields[fieldNum++] = nazwiskoPole;
        
        zatrudnionyOdPole  = new JTextField();
        zatrudnionyOdPole.setColumns(30);
        if(pracownik.dajZatrudnionyOd() != null)
        {
        	SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        	String zatrudnionyOdString = formatter.format(pracownik.dajZatrudnionyOd());
        	zatrudnionyOdPole.setText(zatrudnionyOdString);
        }
        fields[fieldNum++] = zatrudnionyOdPole;

        doswiadczeniePole = new JTextField();
        doswiadczeniePole.setColumns(30);
        if(pracownik.dajDoswiadczenie() != null)
        {
        	nazwiskoPole.setText(pracownik.dajDoswiadczenie().toString());
        }
        fields[fieldNum++] = doswiadczeniePole;
        
        //Associate label/field pairs, add everything,
        //and lay it out.
        for (int i = 0; i < labelStrings.length; i++) {
            labels[i] = new JLabel(labelStrings[i],
                                   JLabel.TRAILING);
            labels[i].setLabelFor(fields[i]);
            textInputPanel.add(labels[i]);
            textInputPanel.add(fields[i]);
        }
        SpringUtilities.makeCompactGrid(textInputPanel,
                                        labelStrings.length, 2,
                                        GAP, GAP, //init x,y
                                        GAP, GAP/2);//xpad, ypad

        //umiejetnosci
        JPanel umiejetnosciPanel = new JPanel();
        umiejetnosciPanel.setLayout(new BoxLayout(umiejetnosciPanel, BoxLayout.Y_AXIS));
        Iterator it = listaUmiejetnosci.entrySet().iterator();
        while (it.hasNext()) {
            Map.Entry pairs = (Map.Entry)it.next();
            System.out.println(pairs.getKey() + " = " + pairs.getValue());
            JPanel insidePane = new JPanel();
            insidePane.setLayout(new BoxLayout(insidePane, BoxLayout.X_AXIS));
            JCheckBox checkBox = new JCheckBox();
            checkBox.setName(pairs.getKey().toString());
            checkBox.addItemListener(this);
            insidePane.add(checkBox);
            insidePane.add(new JLabel((String)pairs.getValue()));
            insidePane.setAlignmentX(Component.LEFT_ALIGNMENT);
            umiejetnosciPanel.add(insidePane);
            it.remove(); // avoids a ConcurrentModificationException
        }
        umiejetnosciPanel.setAlignmentX(Component.CENTER_ALIGNMENT);
        
        JButton saveButton = new JButton("Zapisz");
        saveButton.setActionCommand(ZAPISZ);
        saveButton.addActionListener(this);
        
        JPanel buttonsPane = new JPanel();
        buttonsPane.setLayout(new BoxLayout(buttonsPane, BoxLayout.X_AXIS));
        buttonsPane.add(saveButton);
        
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
        mainPanel.add(textInputPanel);
        mainPanel.add(Box.createVerticalGlue());
        mainPanel.add(umiejetnosciPanel);
        mainPanel.add(Box.createVerticalGlue());
        mainPanel.add(buttonsPane);

        return mainPanel;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		String actionCommand = e.getActionCommand();
		if(ZAPISZ == actionCommand)
		{
			System.out.println("action perf EdycjaDanychPracownika");
			CZdarzenie zdarzenie = new CZdarzenie();
			zdarzenie.ustawTyp(ETypZdarzenia.ZAKONCZONO_EDYCJE_PRACOWNIKA);
			
			pracownik.ustawImie(imiePole.getText());
			pracownik.ustawNazwisko(nazwiskoPole.getText());
			
			SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
	        java.util.Date zatrudnionyOd;
			try {
				zatrudnionyOd = formatter.parse(zatrudnionyOdPole.getText());
			} catch (ParseException e1) {
				MessageBoxHelper.infoBox("Datę wpisz w formacie yyyy-MM-dd", "Edycja danych pracownika");
				return;
			}
			pracownik.ustawZatrudnionyOd(zatrudnionyOd);
			
			
			Integer doswiadczenie;
			try
			{
				doswiadczenie = Integer.parseInt(doswiadczeniePole.getText());
				if (doswiadczenie < 0)
					throw new NumberFormatException();
			}
			catch (NumberFormatException exception)
			{
				MessageBoxHelper.infoBox("Doświadczenie powinno być liczbą całkowitą > 0", "Edycja danych");
				return;
			}
			pracownik.ustawDoswiadczenie(doswiadczenie);
			
			ArrayList<Object> parametry = new ArrayList<Object>();
			parametry.add(pracownik);
			
			zdarzenie.ustawParametry(parametry);
			setChanged();
	    	notifyObservers(zdarzenie);
		}
	}

	@Override
	public void itemStateChanged(ItemEvent e) {
		JCheckBox jcb = (JCheckBox)e.getItem();
		try
		{
			Integer umiejetnosc = Integer.parseInt(jcb.getName());
			if(jcb.isSelected())
			{
				pracownik.dajUmiejetnosci().add(umiejetnosc);
			}
			else
			{
				pracownik.dajUmiejetnosci().remove(umiejetnosc);
			}
		}
		catch (NumberFormatException exception)
		{
			MessageBoxHelper.infoBox("Niepoprawna umiejetność", "Edycja danych");
			return;
		}
	}

}
