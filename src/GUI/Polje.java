package GUI;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.RenderingHints;

import javax.swing.JPanel;

import Logika.Snake;

public class Polje extends JPanel {
	Point p;
	int matrica_stanja[][];
	public static int x_koord = 170, y_koord = 90;
	
	/**
	 * Konstruktor koji postavlja vrijednosti matrice stanja na vrijednosti matrice koja je dodijeljena u konstruktora
	 * @param matrica
	 */
	public Polje(int matrica[][]) {
		this.matrica_stanja = matrica;
	}
	
	/**
	 * paintComponent za iscrtavanje polja u prozoru, te pracenjem logike same igrice, iscrtavanje kretanja zmije, prepreka i hrane
	 */
	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D gr2d = (Graphics2D) g; //Ova klasa Graphics2D prosiruje klasu Graphics kako bi pruzila sofisticiraniju kontrolu nad transformacijama koordinata, upravljanjem bojama i rasporedom teksta. Ovo je osnovna klasa za prikazivanje 2-dimenzionalnih oblika, teksta i slika.
		gr2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_OFF); //Postavlja vrijednost jedne preferencije za algoritme za renderovanje.
		
		//postavljanje boje i oblika u kockicu za pozadinu
		gr2d.setColor(new Color(70, 224, 45));
		gr2d.fillRect(0, 0, 1000, 800);
		
		/**
		 * for petlja za ispis tijela zmije, prepreka, obicne i specijalne hrane po polju u datim koordinatama
		 */
		for(int i = 0; i < matrica_stanja.length; i++) {
			for(int j = 0; j < matrica_stanja[0].length; j++) {
				if(matrica_stanja[i][j] == Snake.tijelo_zmije) {
					gr2d.setColor(Color.yellow);
					gr2d.fillRect(j*32 + x_koord, i*32 + y_koord, 32, 32);	
				}
				else if(matrica_stanja[i][j] == Snake.prepreka) {
					gr2d.setColor(Color.gray);
					gr2d.fillRect(j*32 + x_koord, i*32 + y_koord, 32, 32);
				}
				else if(matrica_stanja[i][j] == Snake.obicna_hrana) {
					gr2d.setColor(Color.red);
					gr2d.fillRect(j*32 + x_koord, i*32 + y_koord, 32, 32);
				}
				else if(matrica_stanja[i][j] == Snake.spec_hrana1) {
					gr2d.setColor(Color.blue);
					gr2d.fillRect(j*32 + x_koord, i*32 + y_koord, 32, 32);
				}
				else if(matrica_stanja[i][j] == Snake.spec_hrana2) {
					gr2d.setColor(Color.white);
					gr2d.fillRect(j*32 + x_koord, i*32 + y_koord, 32, 32);
				}
				else if(matrica_stanja[i][j] == Snake.spec_hrana3) {
					gr2d.setColor(Color.black);
					gr2d.fillRect(j*32 + x_koord, i*32 + y_koord, 32, 32);
				}
			}
		}
		
		/**
		 * za grid odnosno mreze u polju za igru 
		 */
		gr2d.setColor(Color.white);
		for(int i = 0; i < matrica_stanja.length; i++) {
			for(int j = 0; j < matrica_stanja[0].length; j++) {
				gr2d.drawRect(j * 32 + x_koord, i * 32 + y_koord, 32, 32);
			}
		}
		
		//za granice(border)
		gr2d.setColor(Color.white);
		gr2d.drawRect(x_koord, y_koord, 512, 512);
		
		//za ispisivanje skora
		gr2d.setFont(new Font("Comic Sans MS", Font.BOLD, 50));
		gr2d.drawString("Score: " + Snake.score, 385, 60);
		
	}
	
	/**
	 * Funkcija koja osvjezava stanje na matrici te poziva repaint tj. prikaz stanja u prozoru za igru
	 * @param matrica
	 */
	public void osvjezi_polje(int matrica[][]) {
		this.matrica_stanja = matrica;
		//Poziv revalidate() i repaint() nam sluze da se ukloni stara slika na gui-u i prikaze nova prefarbana verzija nakon osvjezenja polja odnosno matrice polja
		this.revalidate();
		this.repaint();
	}
	
}
