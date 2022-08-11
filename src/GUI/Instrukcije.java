package GUI;


import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;


public class Instrukcije extends JFrame{
	JPanel prostor_naslova, prostor_za_instrukcije;
	JLabel naslov_instrukcije, instrukcije;
	
	/**
	 * Konstruktor koji kreira prozor na kojem se nalazi tekst sa instrukijama vezano za samu igricu
	 */
	public Instrukcije() {
		super("Instrukcije!"); //naslov prozora
		this.setSize(new Dimension(900, 800)); //dimenzije prozora
		this.setLocationRelativeTo(null); //postavljanje lokacije prozora na sredini monitora
		this.getContentPane().setBackground(new Color(25, 107, 23)); //postavljanje boje prozora na odredjenu
		this.setLayout(new GridBagLayout()); //metoda za postavljanje izgleda prozora
		Font font_za_naslov_instrukcije = new Font("Comic Sans MS", Font.PLAIN, 100); //postavljanje fonta za tekst vezan za naslov
		Font font_za_instrukcije = new Font("Comic Sans MS", Font.PLAIN, 25); //postavljanje fonta za tekst vezan za instukcije
		GridBagConstraints c = new GridBagConstraints(); //kreiramo constraint
        c.gridx = 0;
        c.gridy = 0;
        c.weightx = 0.3;
        c.weighty = 0.5;
		
        /**
         * U ovom bloku koda se kreira prostor za ispis naslova, njegove dimenzije i lokacija u prostoru
         */
		prostor_naslova = new JPanel();
        prostor_naslova.setBackground(new Color(25, 107, 23)); //postavljanje boje prostora za naslov
        c.gridx = 1;
        c.gridy = 0;
        c.weightx = 0.1;
        c.weighty = 0;
        c.anchor = GridBagConstraints.PAGE_START;
        c.fill = GridBagConstraints.BOTH;
        naslov_instrukcije = new JLabel("Instrukcije"); //postavljanje naslova u prozoru
        naslov_instrukcije.setForeground(Color.white); //boja teksta
        naslov_instrukcije.setFont(font_za_naslov_instrukcije); 
        prostor_naslova.add(naslov_instrukcije);
        this.add(prostor_naslova, c);
        
        /**
         * U ovom bloku koda se kreira prostor za ispis instrukcija vezano za igricu, njegove dimenzije i lokacija u prostoru
         */
        prostor_za_instrukcije = new JPanel();
        prostor_za_instrukcije.setBackground(new Color(25, 107, 23)); //postavljanje boje prostora za naslov
        c.gridx = 0;
        c.gridy = 1;
        c.gridwidth = 2;
        c.gridheight = 4;
        c.weightx = 1.0;
        c.weighty = 1.0;
        c.anchor = GridBagConstraints.LAST_LINE_START;
        c.fill = GridBagConstraints.BOTH;
        StringBuilder tekst_instrukcije = new StringBuilder(1000); 
        /**
         * Tekst koji se prikazuje na prozoru u vidu instrukcija igrice
         */
        tekst_instrukcije.append("<html><br>Snake je igra koja se igra na principu klika tipki na tastaturi<br>").
									append("tako da A predstavlja usmjerenje ka lijevo, tipka D usmjerenje ka<br>").
									append("desno. Igrica se završava kada se glava zmije sudari sa svojim<br>").
									append("tijelom ili kada udarite u prepreke koje su na polju oznaèene sivom<br>").
									append("bojom. Zmija može prolaziti kroz zidove. Hrana se random pojavljuje<br>").
									append("po polju. Crvena hrana oznaèava obiènu hranu i kada je zmija pojede<br>").
									append("score raste za 1. Plava hrana predstavlja specijalnu hranu koja score<br>").
									append("poveæava za 3 a samim tim i zmiju, bijela hrana smanjuje score i zmiju<br>").
									append("za 3 a crna hrana mijenja komande tako da desno postaje lijevo i obratno.<br>").
									append("Najbolji skor se može vidjeti na dugmetu 'High score'.<br>").
									append("Nadamo se da æete uživati u igri!<br>").
									append("Sretno :) !</html>");
        instrukcije = new JLabel(tekst_instrukcije.toString());
        instrukcije.setForeground(Color.white);
        instrukcije.setFont(font_za_instrukcije);
        prostor_za_instrukcije.add(instrukcije);
        this.add(prostor_za_instrukcije, c);     
	}
	
	/**
	 * Funkcija koja prilikom poziva prikazuje prozor za instrukcije
	 */
	public void prikaziOkvir() {
		this.setVisible(true);
	}
}
