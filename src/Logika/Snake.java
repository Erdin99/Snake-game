package Logika;

import java.awt.Point;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

import GUI.HighScore;


/**
 * @author Erdin
 */


/**
 * Implementacija logike za igru Snake
 */

public class Snake {
	public static final int prazno_polje = 0;
	public static final int tijelo_zmije = 1;
	public static final int obicna_hrana = 2;
	public static final int spec_hrana1 = 3;
	public static final int spec_hrana2 = 4;
	public static final int spec_hrana3 = 5;
	public static final int prepreka = 6;
	public static final int smjer_lijevo = 20;
	public static final int smjer_desno = 21;
	public static final int smjer_gore = 22;
	public static final int smjer_dolje = 23;
	public static int klik_desno = 30;
	public static int klik_lijevo = 31;
	public static int klik_desno_provjera = 30;
	public static int klik_lijevo_provjera = 31;

	public ArrayList<Point> tijelo = new ArrayList<>();
	
	int m; //red
	int n; //kolona
	int matrica_stanja[][];
	public static int score;
	public static int highscore;
	HighScore hs;
	int smjer_zmije;
	boolean kraj;
	FileZaScore file;
	
	/**
	 * Konstruktor koji ima dodijeljene vrijednost m1, n1 koje predstavljaju dimenzije polja
	 * Vrsi se dodjela dimenzija, skora, smjera zmije, matrice stanja te poziva funkcija za dodjelu tijela zmije, prepreke i hrane
	
	 * @param m1, n1
	 * 
	 */
	
	public Snake(int m1, int n1) {
		m = m1;
		n = n1;
		score = 0;
		smjer_zmije = smjer_desno;
		matrica_stanja = new int[m1][n1];
		kraj = false;
		for(int i = 0; i < m1; i++) {
			for(int j = 0; j < n1; j++) {
				matrica_stanja[i][j] = prazno_polje;
			}
		}
		dodajTijelo();
		dodajPrepreke();
		dodajHranu();
		/*
		for(int i = 0; i < m1; i++) {
			for(int j = 0; j < n1; j++) {
				System.out.print(matrica_stanja[i][j] + " ");
			}
			System.out.println();
		}
		*/
	}
	
	/**
	 * Funkcija koja dodaje 4 kockice na tijelo zmije na samom pocetku igre
	 */
	public void dodajTijelo() {
		for(int i = 3; i >= 0; i--) {
				tijelo.add(new Point(m/2, i));
				matrica_stanja[m/2][i] = tijelo_zmije;
		}
	}
	
	/**
	 * Funkcija koja dodaje prepreke po polju u raznim oblicima, kreirajuci ih kroz for petlju zadavanjem koordinata
	 */
	public void dodajPrepreke() {
		Point koord_prepreke1 = new Point(1, 1);
		Point koord_prepreke2 = new Point(10, 12);
		Point koord_prepreke3 = new Point(14, 4);
		
		for(int i = 0; i < 4; i++) {
			matrica_stanja[(int) koord_prepreke1.getX()][(int) koord_prepreke1.getY()] = prepreka;
			koord_prepreke1.setLocation((int)koord_prepreke1.getX() + 1, (int) koord_prepreke1.getY() + 1);

			matrica_stanja[(int) koord_prepreke2.getX()][(int) koord_prepreke2.getY()] = prepreka;
			if(i % 2 == 0) {
				koord_prepreke2.setLocation((int)koord_prepreke2.getX() + 1, (int) koord_prepreke2.getY());
			}
			else {
				koord_prepreke2.setLocation((int)koord_prepreke2.getX(), (int) koord_prepreke2.getY() + 1);
			}
			
			matrica_stanja[(int) koord_prepreke3.getX()][(int) koord_prepreke3.getY()] = prepreka;
			if(i == 0) {
				koord_prepreke3.setLocation((int)koord_prepreke3.getX() + 1, (int) koord_prepreke3.getY());
			}
			else if(i == 1) {
				koord_prepreke3.setLocation((int)koord_prepreke3.getX(), (int) koord_prepreke3.getY() + 1);
			}
			else if(i == 2) {
				koord_prepreke3.setLocation((int)koord_prepreke3.getX() - 1, (int) koord_prepreke3.getY());
			}
		}
	}
	
	/**
	 * Funkcija za dodavanje hrane po polju uz dodatak dodavanja specijalne hrane zadane odredjenim uslovima
	 */
	public void dodajHranu() {
		Random rn = new Random();
		int dodatna_hrana = -1;
		/**
		 * Ukoliko je skor djeljiv sa 10 dodaj specijalnu hranu u vidu dodavanja skora i tijela zmije za 3
		 */
		if(score % 10 == 0 && score != 0) {
			dodatna_hrana = spec_hrana1;
		} 
		/**
		 * Ukoliko je skor djeljiv sa 25 dodaj specijalnu hranu u vidu oduzimanja skora i tijela zmije za 3
		 */
		else if(score % 25 == 0 && score != 0) { 
			dodatna_hrana = spec_hrana2;
		}
		/**
		 * Ukoliko je skor djeljiv sa 35 dodaj specijalnu hranu u vidu promjene kontrole
		 */
		else if(score % 35 == 0 && score != 0) { 
			dodatna_hrana = spec_hrana3;
		}
		
		/**
		 * While petlja za provjeru koordinata, odnosno ukoliko se koordinate hrane poklapaju samo sa koordinatama praznih polja petlja se zavrsava
		 */
		while(true) {
			int x_koord = rn.nextInt(n);
			int y_koord = rn.nextInt(m);
			if(matrica_stanja[x_koord][y_koord] == prazno_polje) {
				matrica_stanja[x_koord][y_koord] = obicna_hrana;
				break;
			}
		}
		
		/**
		 * U ovom uslovu provjeravamo da li je ispunjen neki od uslova za prikaz specijalne hrane, te ukoliko jeste kroz while petlju se nalaze koordinate koje su jednake koordinatama nekog od praznih polja, te se potom petlja zavrsava 
		 */
		if(dodatna_hrana != -1) {
			while(true) {
				int x_koord1 = rn.nextInt(n);
				int y_koord1 = rn.nextInt(m);
				if(matrica_stanja[x_koord1][y_koord1] == prazno_polje) {
					matrica_stanja[x_koord1][y_koord1] = dodatna_hrana;
					break;
				}
			}
		}
	}
	
	/**
	 * Funkcija koja namjesta smjer u zavisnosti sa odredjenim uslovima
	 * Funkcija prima vrijednost klika, te u zavisnosti od te vrijednosti izvrsava se namjestanje smjera
	 * Takodjer ukoliko se pojede specijalna hrana za promjenu kontrola, vrsi se namjestanje suprotnog smjera
	 * @param klik
	 */
	public void namjestiSmjer(int klik) {
		if(klik == klik_desno_provjera) {
			if(smjer_zmije == smjer_gore) {
				smjer_zmije = smjer_desno;
			}
			else if(smjer_zmije == smjer_desno) {
				smjer_zmije = smjer_dolje;
			}
			else if(smjer_zmije == smjer_dolje) {
				smjer_zmije = smjer_lijevo;
			}
			else if(smjer_zmije == smjer_lijevo) {
				smjer_zmije = smjer_gore;
			}
		}
		else if(klik == klik_lijevo_provjera) {
			if(smjer_zmije == smjer_gore) {
				smjer_zmije = smjer_lijevo;
			}
			else if(smjer_zmije == smjer_desno) {
				smjer_zmije = smjer_gore;
			}
			else if(smjer_zmije == smjer_dolje) {
				smjer_zmije = smjer_desno;
			}
			else if(smjer_zmije == smjer_lijevo) {
				smjer_zmije = smjer_dolje;
			}
		}
	}
		
	/**
	 * Funkcija u kojoj se izvodi kretanje zmije po polju za igru
	 */
	public void kretanje() {
		Point naredno_polje = new Point();
		Point glava = tijelo.get(0);
		/**
		 * Provjerava se smjer kretanja zmije, te u zavisnosti od toga dodaju se koordinate za naredno polje u koje glava zmije treba ici
		 */
		if(smjer_zmije == smjer_gore) {
			if(glava.getX() == 0) {
				naredno_polje = new Point(m-1, (int)glava.getY());
			} 
			else {
				naredno_polje = new Point((int) glava.getX() - 1, (int)glava.getY());
			}
		}
		else if(smjer_zmije == smjer_desno) {
			if(glava.getY() == n-1) {
				naredno_polje = new Point((int)glava.getX(), 0);
			}
			else {
				naredno_polje = new Point((int) glava.getX(), (int)glava.getY() + 1);
			}
		}
		else if(smjer_zmije == smjer_dolje) {
			if(glava.getX() == m-1) { 
				naredno_polje = new Point(0, (int)glava.getY()); 
			}
			else {
				naredno_polje = new Point((int) glava.getX() + 1, (int)glava.getY());
			}
		}
		else if(smjer_zmije == smjer_lijevo) {
			if(glava.getY() == 0) {
				naredno_polje = new Point((int)glava.getX(), n-1);
			}
			else {
				naredno_polje = new Point((int) glava.getX(), (int)glava.getY() - 1);
			}
		}
		
		/**
		 * Vrsi se provjera sta se nalazi u narednoj koordinati u koje glava zmije treba uci
		 * Ukoliko je u narednoj koordinati prepreka ili tijelo zmije, vrsi se prvobitno namjestanje starog high score-a, te se uporedjuje sa trenutnim skorom dobijenim u igrici
		 * Ukoliko je trenutni skor veci poziva se funkcija za unos tog skora u fajl
		 */
		
		if(matrica_stanja[(int)naredno_polje.getX()][(int)naredno_polje.getY()] == prepreka || matrica_stanja[(int)naredno_polje.getX()][(int)naredno_polje.getY()] == tijelo_zmije) {
			updateHighScore();
			if(score > highscore) {
				String highscore_string = Integer.toString(score);
				upisiSkorUFajl(highscore_string);
			}
			kraj = true;
			
		} 
		/**
		 * Ukoliko je u narednoj koordinati hrana vrsi se poziv funkcije koja provjerava koja je hrana pojedena, te u zavisnosti od toga vrsi odredjene radnje
		 */
		else if(matrica_stanja[(int)naredno_polje.getX()][(int)naredno_polje.getY()] != prazno_polje && matrica_stanja[(int)naredno_polje.getX()][(int)naredno_polje.getY()] != prepreka && matrica_stanja[(int)naredno_polje.getX()][(int)naredno_polje.getY()] != tijelo_zmije) {
			pojediHranu(naredno_polje);
			/**
			 * Uslov u kojem se dodaje nova hrana ukoliko je u pitanju bila obicna hrana 
			 */
			if(matrica_stanja[(int)naredno_polje.getX()][(int)naredno_polje.getY()] == obicna_hrana) {
				dodajHranu();
			}
		}
		/**
		 * dodavanje koordinate narednog polja
		 */
		tijelo.add(0, naredno_polje);
		/**
		 * oduzimanje repa iz tijela zmije
		 */
		tijelo.remove(tijelo.size() - 1);
	}
	
	/**
	 * Funkcija koja izvrsava upisivanje najveceg skora u file
	 * @param skor
	 */
	public void upisiSkorUFajl(String skor) {
		try {
			FileWriter upisi = new FileWriter("HighScore.txt");
		    upisi.write(skor);
		    upisi.close();
		    System.out.println("Uspješno ste unijeli skor u fajl.");
		} 
		catch (IOException e) {
			System.out.println("Error je u pitanju.");
		    e.printStackTrace();
		}
	}
	
	/**
	 * Funkcija koja cita najveci skor iz fajla, te osvjezava vrijednost varijable highscore
	 */
	public void updateHighScore() {
		try {
			File fileZaSkor = new File("HighScore.txt");
		    Scanner procitaj = new Scanner(fileZaSkor);
		    int int_skor;
		    if(procitaj.hasNextLine()) {
		    	String skor = procitaj.nextLine();
		    	int_skor = Integer.parseInt(skor);
		    	highscore = int_skor;
		    }
		    procitaj.close();
		}
		catch (FileNotFoundException e) {
			System.out.println("Error je u pitanju.");
		    e.printStackTrace();
		}
		
	}
	
	/**
	 * Funkcija vrsi provjeru koja je hrana pojedena u zavisnosti od koordinata koje su joj dodijeljene
	 * @param naredno_polje
	 */
	public void pojediHranu(Point naredno_polje) {
		/**
		 * Ukoliko je u pitanju obicna hrana, dodaje se skor za 1 i tijelo zmije za 1
		 */
		if(matrica_stanja[(int)naredno_polje.getX()][(int)naredno_polje.getY()] == obicna_hrana) {
			score += 1;
			tijelo.add(naredno_polje);
		}
		/**
		 * Ukoliko je u pitanju specijalna hrana(koja dodaje tijelo i skor za 3), dodaje se skor i tijelo zmije za 3
		 */
		else if(matrica_stanja[(int)naredno_polje.getX()][(int)naredno_polje.getY()] == spec_hrana1) {
			score += 3;
			for(int i = 0; i < 3; i++) {
				tijelo.add(new Point(tijelo.get(tijelo.size()-1).x, tijelo.get(tijelo.size()-1).y));
			}
		}
		/**
		 * Ukoliko je u pitanju specijalna hrana(koja oduzima tijelo i skor za 3), oduzima se skor i tijelo zmije za 3
		 */
		else if(matrica_stanja[(int)naredno_polje.getX()][(int)naredno_polje.getY()] == spec_hrana2) {
			score -= 3;
			for(int i = 0; i < 3; i++) {
				tijelo.remove(tijelo.size() - 1);
			}
		}
		/**
		 * Ukoliko je u pitanju specijalna hrana(promjena kontrole), mijenja se kontrola smjera zmije po polju i dodaje se skor za 1
		 */
		else if(matrica_stanja[(int)naredno_polje.getX()][(int)naredno_polje.getY()] == spec_hrana3) {
			score += 1;
			
			/**
			 * Promjena kontrole smjera zmije
			 */
			int temp = klik_desno_provjera;
	        klik_desno_provjera = klik_lijevo_provjera;
	        klik_lijevo_provjera = temp;     
		}
	}
	
	/**
	 * Funkcija koja osvjezava stanje matrice u zavisnosti od poteza korisnika
	 */
	public void osvjeziMatricu() {
		/**
		 * Ova for petlja pretvara tijelo zmije u prazna polja, prepreke i hrana ostaju na svom mjestu
		 */
		for(int i = 0; i < m; i++) {
			for(int j = 0; j < n; j++) {
				if(matrica_stanja[i][j] == tijelo_zmije) {
					matrica_stanja[i][j] = 0;
				}
			}
		}
		/**
		 * Ova for petlja postavlja tijelo zmije po novim koordinatama koje su dobijene u zavisnosti od poteza korisnika
		 */
		for(int i = 0; i < tijelo.size(); i++) {
			matrica_stanja[(int)tijelo.get(i).getX()][(int)tijelo.get(i).getY()] = tijelo_zmije;
		}
		
	}
	
	/**
	 * Funkcija koja vraca vrijednost da li je kraj igre ili ne
	 * @return kraj
	 */
	public boolean daLiJeKraj() {
		return kraj;
	}
	
	/**
	 * Funkcija koja vraca trenutno stanje matrice
	 * @return nova_matrica
	 */
	public int[][] vratiStanjeMatrice() {
		int nova_matrica[][] = new int[m][n];
		
		for(int i = 0; i < m; i++) {
			for(int j = 0; j < n; j++) {
				nova_matrica[i][j] = matrica_stanja[i][j];
			}
		}
		
		return nova_matrica;
	}
	
	/**
	 * Funkcija koja vraca vrijednost skora
	 * @return score
	 */
	static public int getScore() {
		return score;
	}
}
