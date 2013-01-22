package Widok;

import javax.swing.JOptionPane;

public final class MessageBoxHelper {
	public static void infoBox(String infoMessage, String location)
    {
        JOptionPane.showMessageDialog(null, infoMessage, "InfoBox: " + location, JOptionPane.INFORMATION_MESSAGE);
    }
}