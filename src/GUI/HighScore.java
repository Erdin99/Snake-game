package GUI;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import Logika.Snake;

public class HighScore extends JFrame{
	public int highScoreReplika;
	int high_score;
	public static ArrayList<Integer> niz_najvecih = new ArrayList<>();

	JPanel prostor_teksta, prostor_skora;
	JLabel tekst, skor;
	Snake logika;
	/**
	 * Konstruktor koji kreira prozor na kojem je ispisan najveci zabiljezen skor u igrici
	 */
	public HighScore() {
		super("High score!"); //naslov prozora 
		this.setSize(new Dimension(800, 800)); //dimenzije prozora
		this.setLocationRelativeTo(null); //postavljanje prozora na sredini monitora
		this.getContentPane().setBackground(new Color(25, 107, 23)); //pozadina prozora 
		this.setLayout(new GridBagLayout()); //metoda za postavljanje izgleda prozora
		Font font_za_tekst = new Font("Comic Sans MS", Font.PLAIN, 100); //namjestanje fonta i velicine slova
		GridBagConstraints c = new GridBagConstraints();
		
		/**
		 * U ovom bloku koda se namjesta prostor za ispis teksta High score:, njegove dimenzije i pozicija u prozoru         
		 */
        prostor_teksta = new JPanel();
        prostor_teksta.setBackground(new Color(25, 107, 23));
        c.gridx = 1;
        c.gridy = 0;
        c.weightx = 0.1;
        c.weighty = 0;
        c.fill = GridBagConstraints.BOTH;
        tekst = new JLabel("High score: ");
        tekst.setForeground(Color.white);
        tekst.setFont(font_za_tekst);
        prostor_teksta.add(tekst);
        this.add(prostor_teksta, c);
        
        /**
         * U ovom bloku koda se namjesta prostor za ispis najveceg skora, njegove dimenzije i pozicija u prozoru
         */
        prostor_skora = new JPanel();
        prostor_skora.setBackground(new Color(25, 107, 23));
        c.gridx = 2;
        c.gridy = 0;
        c.weightx = 0.1;
        c.weighty = 0;
        c.fill = GridBagConstraints.BOTH;
        
        highScoreReplika = vratiSkorIzFajla(); //poziv funkcije koji vraca najveci skor iz fajla i dodijeljuje ga varijabli highScoreReplika
        
        /**
         * U ovom bloku koda se ispisuje najveci skor u prozoru
         */
        String ispisi_skor = Integer.toString(highScoreReplika); 
        skor = new JLabel(ispisi_skor); 
        //skor = new JLabel("0");
        skor.setForeground(Color.white);
        skor.setFont(font_za_tekst);
        prostor_skora.add(skor);
        this.add(prostor_skora, c);
	}
	
	/**
	 * Funkcija koja otvara vec kreiran fajl i izvlaci skor te ga dodijeljuje vec postojecoj varijabli pod nazivom high_score, 
	 * mijenja vrijednost na vrijednost skora iz fajla i vraca tu istu vrijednost
	*/
	public int vratiSkorIzFajla() {
		try {
			File fileZaSkor = new File("HighScore.txt");
		    Scanner procitaj = new Scanner(fileZaSkor);
		    int int_skor;
		    if(procitaj.hasNextLine()) {
		    	System.out.println("Usao u if uslov");
		    	String skor = procitaj.nextLine();
		    	int_skor = Integer.parseInt(skor);
		    	high_score = int_skor;
		    }
		    procitaj.close();
		}
		catch (FileNotFoundException e) {
			System.out.println("Error je u pitanju.");
		    e.printStackTrace();
		}
		return high_score;
	}
	
	/**
	 * Funkcija za prikazivanje prozora prilikom poziva iste
	 */
	public void prikaziOkvir() {
		this.setVisible(true);
	}
}
