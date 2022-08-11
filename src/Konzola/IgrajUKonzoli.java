package Konzola;

import java.util.Scanner;

import Logika.Snake;

/**
 * Klasa za intefejs konzola
 * 
 * @author Erdin Idrizovic
 */

public class IgrajUKonzoli {
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Snake logika = new Snake(20, 20); //inicijalizacija 
		
		System.out.println("Igrica Snake, potezi su:\n" +
				"\tA - lijevo\n" +
				"\tD - desno\n");
		System.out.println("Ukoliko ne zelite mijenjati pravac, nego nastaviti u istom smjeru samo pritisnite enter.");
		System.out.println("Instrukcije:");
		System.out.println("1 predstavlja tijelo zmije, 2 obicnu hranu, 3, 4 i 5 predstavljaju specijalnu hranu, od kojih su");
		System.out.println("3 za povecanje zmije za 3, 4 smanjenje zmije za 3 i 5 za promjenu kontrola sa desno na lijevo i obratno.");
		System.out.println("6 predstavlja prepreku, te zmija ne smije udariti u istu jer ce biti kraj igre.");
		
		/**
		 * While petlja koja se ponavlja dok se igra ne zavrsi tj. dok se zmija ne sudari sa preprekom ili svojim tijelom
		 */
		while(logika.daLiJeKraj() == false) {
			/**
			 * poziv funkcije koja ucitava potez korisnika
			 */
			String potez = ucitajPoteze(); 
			/**
			 * vrsi se provjera poteza korisnika, te u zavisnosti od klika izvrsava se namjestanje smjera
			 */
			if(potez.equals("A") || potez.equals("a")) {
				int klik_potez = logika.klik_lijevo;
				logika.namjestiSmjer(klik_potez); 
			}
			else if(potez.equals("D") || potez.equals("d")) {
				int klik_potez = logika.klik_desno;
				logika.namjestiSmjer(klik_potez);
			}
			logika.kretanje();
			logika.osvjeziMatricu();
			
			/**
			 * ispisivanje trenutnog skora i stanja matrice
			 */
			System.out.println("Trenutni score je: " + logika.score);
			String tabela_stanja = pripremiTabeluStanja(logika.vratiStanjeMatrice());
			System.out.println(tabela_stanja);
		}
		System.out.println("KRAJ IGRE U KONZOLI");
	}
	
	/**
	 * Vrsi se unos kontrola od strane korisnika, odnosno unos slova "A", "a", "D", "d"
	 * @return returnString
	 */
	private static String ucitajPoteze() {
		Scanner sc = new Scanner(System.in);
		String returnString = "";
		try {
			returnString = sc.nextLine();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return returnString;
	}
	
	
	/**
	 * U ovoj funkciji se izvrsava unos matrice u varijablu koju vracamo u while petlju za ispis stanja matrice
	 * @param vratiTrenutnoStanje
	 * @return stanje
	 */
	private static String pripremiTabeluStanja(int[][] vratiTrenutnoStanje) {
		String stanje = "";
		/**
		 * Kroz for petlje se provjeravaju stanja u matrici, te u zavisnosti od njih se ispisuju odgovarajuci brojevi kao predstavnici tih objekata
		 */
		for (int i = 0; i < vratiTrenutnoStanje.length; i++) {
			for (int j = 0; j < vratiTrenutnoStanje[i].length; j++) {
				if(vratiTrenutnoStanje[i][j] == Snake.prazno_polje) {
					stanje += Snake.prazno_polje + " ";
				}
				else if(vratiTrenutnoStanje[i][j] == Snake.tijelo_zmije) {
					stanje += Snake.tijelo_zmije + " ";
				}
				else if(vratiTrenutnoStanje[i][j] == Snake.obicna_hrana) {
					stanje += Snake.obicna_hrana + " ";
				}
				else if(vratiTrenutnoStanje[i][j] == Snake.spec_hrana1) {
					stanje += Snake.spec_hrana1;
				}
				else if(vratiTrenutnoStanje[i][j] == Snake.spec_hrana2) {
					stanje += Snake.spec_hrana2;
				}
				else if(vratiTrenutnoStanje[i][j] == Snake.spec_hrana3) {
					stanje += Snake.spec_hrana3;
				}
				else if(vratiTrenutnoStanje[i][j] == Snake.prepreka) {
					stanje += Snake.prepreka + " ";
				}
			}
			stanje += "\n";
		}
		return stanje;
	}
		
		
	
	

}
 