package GUI;

import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagLayout;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import Logika.Snake;

public class Menu {
	Snake log;
	JFrame okvir; //varijabla pomocu koje cemo otvoriti prozor.
	Container con; //varijabla u kojoj cemo smjestiti panel menu_prostor(u koje smo smjestili sve ostale panele i buttone koje smo kreirali). 
	JPanel menu_prostor, prostor_za_naslov, prostor_za_dugme_start, prostor_za_dugme_instrukcije, prostor_za_dugme_score, prostor_za_dugme_quit; //kreiramo posebno panele, prvi menu_prostor, u kojoj smjestamo ostale panele. U ovom slucaju paneli sluze kao prostor u kojoj ce se smjestiti sadrzaj(naslov, dugmad, itd.).
	JLabel naslov_igrice; //varijabla pomocu koje smjestamo i prikazujemo naslov
	Font font_naslova_igrice = new Font("Comic Sans MS", Font.PLAIN, 130); //kreirali smo varijablu koju cemo koristiti za font naslova, te njegovu velicinu. Font.PLAIN predstavlja konstantu obicnog teksta.
	Font font_za_dugmad = new Font("Comic Sans MS", Font.PLAIN, 30); //kreirali smo varijablu koju cemo koristiti za font teksta unutar dugmadi, te njegovu velicinu.Font.PLAIN predstavlja konstantu obicnog teksta.
	JButton dugme_start, dugme_instrukcije, dugme_score, dugme_quit;
	/**
	 * procedura koja ce se pozvati nakon pritiska na dugme "Start".
	 */
	private void PoljeZaIgru() {
		SnakeGUI igraj = new SnakeGUI(20, 20);
	}
	
	/**
	 * procedura koja ce se pozvati nakon pritiska na dugme "Instrukcije".
	 */
	private void Instrukcije() {
		Instrukcije inst = new Instrukcije();
		inst.prikaziOkvir();
	}
	
	/**
	 * procedura koja ce se pozvati nakon pritiska na dugme "Score".
	 */
	private void HighScore() {
		HighScore hsc = new HighScore();
		hsc.prikaziOkvir();
	}
	
	/**
	 * konstruktor koji prilikom pozivanja prikazuje menu sadrzaj korisniku.
	 */
	public Menu() {	
		/**
		 * u ovom bloku koda konstruisemo JFrame, tj. prilikom pokretanja programa otvorit ce se prozor dimenzija koje smo mu mi dodijelili.
		 */
		//Content pane je zapravo JFrame prozor sa svim potrebnim elementima.
		okvir = new JFrame();
		okvir.setSize(1000, 850); //velicina prozora (x-osa, y-osa).
		okvir.setTitle("Snake game!"); //postavili smo naslov okvira
		okvir.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //kada zatvorimo okvir, program prestaje sa radom.
		okvir.getContentPane().setBackground(new Color(25, 107, 23)); //Posto content pane predstavlja zapravo JFrame pomocu ove naredbe smo dodijelili boju pozadine prozora pomocu RGB sifre.  
		okvir.setLayout(new GridBagLayout()); //GridBagLayout se postavlja preko setLayout. GridBagLayout postavlja komponente u celije, a zatim koristi preferirane osobine komponenti da odredi koliko velike celije trebaju biti.
		okvir.setResizable(false); //zabranjuje mogucnost povecavanja ili smanjenja okvira. Okvir ce ostati u dimenziji u kojoj je prvobitno postavljen.
		
		con = okvir.getContentPane(); //con smo dodijelili content pane odnosno sav sadzaj koji smo kreirali ce biti "strpan" unutar varijable con.
		
		/**
		 * Ovaj blok koda kreira novi panel dimenzija i boje koje mi zelimo. Ovaj panel ce predstavljati podlogu za sve objekte koje budemo kreirali u nastavku.
		 */
		menu_prostor = new JPanel();
		menu_prostor.setPreferredSize(new Dimension(900, 800));
		menu_prostor.setBackground(new Color(25, 107, 23));
		
		menu_prostor.setLayout(null); //ovdje dajemo mogucnost da u glavnom panelu smjestamo ostale panele po koordinatama.
		
		prostor_za_naslov = new JPanel();
		prostor_za_naslov.setBounds(150, 50, 600, 200); //koordinate panela (x, y, sirina(panela), visina(panela))
		prostor_za_naslov.setBackground(new Color(25, 107, 23)); //postavljamo boju panela
		
		/**
		 * U ovom bloku koda stilizujemo tekst koji se unosi u panelu vezan za naslov
		 */
		naslov_igrice = new JLabel("Snake"); 
		naslov_igrice.setForeground(Color.white); //postavljamo boju teksta
		naslov_igrice.setFont(font_naslova_igrice); //postavljamo font teksta
		prostor_za_naslov.add(naslov_igrice); //dodajemo na panel naslov
		
		menu_prostor.add(prostor_za_naslov); //panel naslova dodajemo na menu panel
		
		/**
		 * U ovom bloku koda smo kreirali novi panel u kojem smjestamo dugme koje je u ovom slucaju dugme za start igrice. 
		 */
		prostor_za_dugme_start = new JPanel();
		prostor_za_dugme_start.setBounds(350, 300, 200, 80); //koordinate panela (x, y, sirina(panela), visina(panela))
		prostor_za_dugme_start.setBackground(new Color(25, 107, 23)); //postavljamo boju panela
		
		/**
		 * U ovom bloku koda kreiramo dugme start i stilizujemo ga.
		 */
		dugme_start = new JButton("Start");
		dugme_start.setBackground(Color.white); //postavljamo boju dugmeta
		dugme_start.setForeground(Color.black); //postavljamo boju teksta unutar dugmeta
		dugme_start.setFont(font_za_dugmad); //postavljamo font za tekst unutar dugmeta
		dugme_start.setFocusable(false); //da skloni linije oko teksta unutar button-a
		prostor_za_dugme_start.add(dugme_start); //dodajemo dugme unutar panela 
		
		menu_prostor.add(prostor_za_dugme_start); //panel dugmeta start dodajemo na menu panel
		
		/**
		 * U ovom bloku koda smo kreirali novi panel u kojem smjestamo dugme koje je u ovom slucaju dugme za instrukcije igrice. 
		 */
		prostor_za_dugme_instrukcije = new JPanel();
		prostor_za_dugme_instrukcije.setBounds(350, 400, 200, 80); //koordinate panela (x, y, sirina(panela), visina(panela))
		prostor_za_dugme_instrukcije.setBackground(new Color(25, 107, 23)); //postavljamo boju panela
		
		/**
		 * U ovom bloku koda kreiramo dugme instrukcije i stilizujemo ga.
		 */
		dugme_instrukcije = new JButton("Instrukcije");
		dugme_instrukcije.setBackground(Color.white); 
		dugme_instrukcije.setForeground(Color.black); 
		dugme_instrukcije.setFont(font_za_dugmad); 
		dugme_instrukcije.setFocusable(false); //da skloni linije oko teksta unutar button-a
		prostor_za_dugme_instrukcije.add(dugme_instrukcije);
		
		menu_prostor.add(prostor_za_dugme_instrukcije);
		
		/**
		 * U ovom bloku koda smo kreirali novi panel u kojem smjestamo dugme koje je u ovom slucaju dugme za score igrice.
		 */
		prostor_za_dugme_score = new JPanel();
		prostor_za_dugme_score.setBounds(350, 500, 200, 80);
		prostor_za_dugme_score.setBackground(new Color(25, 107, 23));
		
		/**
		 * U ovom bloku koda kreiramo dugme za score i stilizujemo ga.
		 */
		dugme_score = new JButton("High score");
		dugme_score.setBackground(Color.white);
		dugme_score.setForeground(Color.black);
		dugme_score.setFont(font_za_dugmad);
		dugme_score.setFocusable(false); //da skloni linije oko teksta unutar button-a
		prostor_za_dugme_score.add(dugme_score);
		
		menu_prostor.add(prostor_za_dugme_score);
		
		/**
		 * U ovom bloku koda smo kreirali novi panel u kojem smjestamo dugme koje je u ovom slucaju dugme za izlazak iz igrice.
		 */
		prostor_za_dugme_quit = new JPanel();
		prostor_za_dugme_quit.setBounds(350, 600, 200, 80);
		prostor_za_dugme_quit.setBackground(new Color(25, 107, 23));
		
		/**
		 * U ovom bloku koda kreiramo dugme za izlazak iz igrice (quit) i stilizujemo ga.
		 */
		dugme_quit = new JButton("Quit");
		dugme_quit.setBackground(Color.white);
		dugme_quit.setForeground(Color.black);
		dugme_quit.setFont(font_za_dugmad);
		dugme_quit.setFocusable(false); //da skloni linije oko teksta unutar button-a
		prostor_za_dugme_quit.add(dugme_quit);
		
		menu_prostor.add(prostor_za_dugme_quit);
		
		con.add(menu_prostor); //glavni panel menu_prostor smjestamo u kontejner con i on predstavlja ono sto mi vidimo prilikom pokretanja ovog programa.
		 
		okvir.setLocationRelativeTo(null); //postavljamo okvir na sredinu ekrana
		
		/**
		 * Definisemo akciju da prilikom klika na dugme "Start", otvori se prozor sa poljem za igru.
		 */
		ActionListener akcija_start = new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				PoljeZaIgru();
			}
		};
		dugme_start.addActionListener(akcija_start); //dodajemo akciju na dugme.
		
		/**
		 * Definisemo akciju da prilikom klika na dugme "Instrukcije", otvori se prozor koji prikazuje instrukcije i pravila igre.
		 */
		ActionListener akcija_instrukcije = new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				Instrukcije();
			}
		};
		dugme_instrukcije.addActionListener(akcija_instrukcije); //dodajemo akciju na dugme.
		
		/**
		 * Definisemo akciju da prilikom klika na dugme "Score" prikaze se najveci zabiljezeni score.
		 */
		ActionListener akcija_high_score = new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				HighScore();
			}
		};
		dugme_score.addActionListener(akcija_high_score); //dodajemo akciju na dugme.
		
		/**
		 * Definisemo akciju da prilikom klika na dugme "Quit" zatvori prozor.
		 */
		ActionListener akcija_quit = new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				System.exit(0); //naredba koja zatvara okvir odnosno prozor prilikom klika na dugme.
			}
		};
		dugme_quit.addActionListener(akcija_quit); //dodajemo akciju na dugme.
	}
	
	/**
	 * procedura koja prilikom poziva prikazuje kreirani okvir.
	 */
	public void prikaziOkvir() {
		okvir.setVisible(true); //naredba za prikazivanje kreiranog okvira.
	}

}
