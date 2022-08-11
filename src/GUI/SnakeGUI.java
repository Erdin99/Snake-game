package GUI;

import java.awt.Dimension;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Timer;
import java.util.TimerTask;

import javax.swing.JFrame;

import Logika.Snake;

import Logika.FileZaScore;

public class SnakeGUI extends JFrame{
	
	public static int visina = 800, sirina = 1000;
	Snake logika;
	Polje polje;
	Timer timer = new Timer(true);
	FileZaScore file;
	
	/**
	 * Konstruktor sa dimenzijama polja koja poziva logiku i funkciju za kreiranje polja
	 * @param m
	 * @param n
	 */
	public SnakeGUI(int m, int n) { 
		logika = new Snake(m, n);
		kreirajPolje();
	}
	
	/**
	 * konstruktor koji na poziv kreira prozor
	 */
	public void kreirajPolje() {
		this.setSize(new Dimension(sirina, visina)); //namjesta se dimenzija prozora (x_osa,y_osa)
		this.setTitle("Snake"); //namjestamo naslov prozora 
		this.setLocationRelativeTo(null); //centriranje prozora
		this.setLayout(null); //ova naredba znaci apsolutno pozicioniranje tj. neophodno je ispisati sve pozicije. Nema menadzera izgleda koji bi pomogao. 
		this.setResizable(false); ////zabranjuje mogucnost povecavanja ili smanjenja okvira. Okvir ce ostati u dimenziji u kojoj je prvobitno postavljen.
		this.addKeyListener(new Upravljanje());
		polje = new Polje(logika.vratiStanjeMatrice());
		polje.setBounds(0, 0, sirina, visina); //koordinate panela (x, y, sirina(panela), visina(panela))
		polje.setVisible(true);
		MojTimerTask mtt = new MojTimerTask();
		timer.scheduleAtFixedRate(mtt, 0, 200); //postavljanje timera na 200
		this.add(polje);
		this.requestFocus(); //postavlja zahtjev da se data komponenta postavi u fokusirano stanje. Ova metoda zahtijeva da komponenta bude vidljiva i fokusirana.
		this.setVisible(true);
	}
	
	
	/**
	 * Klasa upravljanje koja registruje klikove na tastaturi
	 *
	 */
	public class Upravljanje implements KeyListener{
		@Override
		public void keyTyped(KeyEvent e) {
			
		}
		
		/**
		 * keyPressed koji prihvata klik na tastaturi te kroz uslove provjerava klik i vrsi poziv funkcije za namjestanje smjera
		 */
		@Override
		public void keyPressed(KeyEvent e) {
			if(e.getKeyCode() == KeyEvent.VK_D) {
				logika.namjestiSmjer(logika.klik_desno);
			}
			else if(e.getKeyCode() == KeyEvent.VK_A) {
				logika.namjestiSmjer(logika.klik_lijevo);
			}
		}
		
		@Override
		public void keyReleased(KeyEvent e) {
			
		}
	}
	
	/**
	 * Klasa sa funkcijom run, koja se poziva svaki put dok god timer nije otkazan
	 *
	 */
	class MojTimerTask extends TimerTask {
		/**
		 * U ovoj funkciji se poziva kretanje tijela zmije, osvjezavanje matrice i polja te se vrsi prikaz u prozoru. Ukoliko je kraj igre timer se otkazuje, te prestaje rad funkcije run
		 */
		@Override
		public void run() {
			logika.kretanje();
			logika.osvjeziMatricu();
			polje.osvjezi_polje(logika.vratiStanjeMatrice());
			if(logika.daLiJeKraj() == true) {
				timer.cancel(); 
			}
		}
		
	}	
}
