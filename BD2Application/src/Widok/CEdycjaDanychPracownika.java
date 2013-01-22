package Widok;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.SpringLayout;

import Komunikator.CZdarzenie;
import Komunikator.ETypZdarzenia;

import layout.SpringUtilities;

public class CEdycjaDanychPracownika extends CSzablonWidoku implements ActionListener {
	private static final int GAP = 10;
	private static final String ZAPISZ = "zapisz";
	private static final String ANULUJ = "anuluj";
	
	JTextField imiePole, nazwiskoPole, zatrudnionyOdPole, doswiadczeniePole;
	
	@Override
	void ustawArgumenty(ArrayList<Object> ListaArgumentow) {
		// TODO Auto-generated method stub
		
	}

	@Override
	JComponent dajKomponent() {
		JPanel panel = new JPanel(new SpringLayout());

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
        fields[fieldNum++] = imiePole;

        nazwiskoPole = new JTextField();
        nazwiskoPole.setColumns(30);
        fields[fieldNum++] = nazwiskoPole;
        
        zatrudnionyOdPole  = new JTextField();
        zatrudnionyOdPole.setColumns(30);
        fields[fieldNum++] = zatrudnionyOdPole;

        doswiadczeniePole = new JTextField();
        doswiadczeniePole.setColumns(30);
        fields[fieldNum++] = doswiadczeniePole;
        
        //Associate label/field pairs, add everything,
        //and lay it out.
        for (int i = 0; i < labelStrings.length; i++) {
            labels[i] = new JLabel(labelStrings[i],
                                   JLabel.TRAILING);
            labels[i].setLabelFor(fields[i]);
            panel.add(labels[i]);
            panel.add(fields[i]);

            //Add listeners to each field.
            //JTextField tf = (JTextField)fields[i];
            //tf.addActionListener(this);
            //tf.addFocusListener(this);
        }
        JButton saveButton = new JButton("Zapisz");
        saveButton.setActionCommand(ZAPISZ);
        saveButton.addActionListener(this);
        JButton cancelButton = new JButton("Anuluj");
        cancelButton.setActionCommand(ANULUJ);
        cancelButton.addActionListener(this);
        
        panel.add(saveButton);
        panel.add(cancelButton);
        
        SpringUtilities.makeCompactGrid(panel,
                                        labelStrings.length+1, 2,
                                        GAP, GAP, //init x,y
                                        GAP, GAP/2);//xpad, ypad
        
        return panel;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		System.out.println("action perf EdycjaDanychPracownika");
		CZdarzenie zdarzenie = new CZdarzenie();
		zdarzenie.ustawTyp(ETypZdarzenia.ZAKONCZONO_EDYCJE_PRACOWNIKA);
		
		ArrayList<Object> parametry = new ArrayList<Object>();
		
		zdarzenie.ustawParametry(parametry);
		setChanged();
    	notifyObservers(zdarzenie);
	}

}
