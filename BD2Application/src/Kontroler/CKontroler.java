package Kontroler;

import java.util.ArrayList;
import java.util.Observer;

import Komunikator.ETypModulu;

public abstract class CKontroler implements Observer{
	abstract ArrayList<Object> dajArgumenty(ETypModulu typModulu);
}
