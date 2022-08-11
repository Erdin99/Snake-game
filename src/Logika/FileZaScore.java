package Logika;

import java.io.File;  
import java.io.IOException;
import java.io.FileWriter;


public class FileZaScore {
	/**
	 * Konstruktor koji izvrsava kreiranje fajla ukoliko ne postoji, te kao pocetnu vrijednost unosi 0
	 * Ukoliko fajl vec postoji ispisuje se odgovarajuca poruka
	 */
	
	public FileZaScore() {
		try {
			File fileZaSkor = new File("HighScore.txt");
		    if (fileZaSkor.createNewFile()) {
		    	System.out.println("Fajl je kreiran: " + fileZaSkor.getName());
		    	FileWriter upisi = new FileWriter("HighScore.txt");
			    upisi.write("0");
			    upisi.close();
		    } 
		    else {
		    	System.out.println("Fajl koji ste pokusali kreirati vec postoji.");
		    }
		}
		catch (IOException e) {
			System.out.println("Error je u pitanju.");
		    e.printStackTrace();
	    }
	}
	
}
