/**
 * Napraviti klasu SpisakFilmova. Pojedinačne filmove pamtiti u jednos-
 * truko povezanoj listi. Za svaki film zapamtiti naslov (string). Dod-
 * avati tako da su filmovi u listi jedinstveni po imenu.
 * 
 * Proširiti klasu koja predstavlja film tako da ima listu glumaca vez-
 * anih za taj film. Svaki glumac je predstavljen u filmu jednim strin-
 * gom tipa "Ime Prezime". Napraviti metode koji dodaju glumce u filmo-
 * ve po nazivu. Glumac se može samo jednom pojaviti u jednom filmu. A-
 * ko film ne postoji, odustati od dodavanja. Odštampati sve filmove, i
 * glumce u njima uredno na ekran (za ovo se može koristiti toString m-
 * etod ako je adekvatan, ali i odvojeni metod koji će nekako jasno oz-
 * načiti koji glumac je u kom filmu).
 * 
 * Dodati metod u klasu SpisakFilmova takav da briše sve filmove u koj-
 * ima je glumio zadati glumac.
 */

class N04Z01P10 {
	
	public static void main(String[] args) {
		
		SpisakFilmova spisak = new SpisakFilmova();
		
		spisak.dodajFilm("Dune");
			spisak.dodajGlumca("Kyle McLachlan", "Dune");
			spisak.dodajGlumca("Francesca Annis", "Dune");
			spisak.dodajGlumca("Max von Sydow", "Dune");
		spisak.dodajFilm("Blade Runner");
			spisak.dodajGlumca("Harrison Ford", "Blade Runner");
		spisak.dodajFilm("Dune");
		spisak.dodajFilm("Twin Peaks");
			spisak.dodajGlumca("Kyle McLachlan", "Twin Peaks");
			
		System.out.println(spisak);
		
		spisak.obrisiSveSaGlumcem("Kyle McLachlan");
		
		System.out.println(spisak);
	}
}

class SpisakFilmova {
	
	class Glumac {
		
		String ime;
		Glumac veza;
		
		
		public Glumac(String ime) {
			this.ime = ime;
			this. veza = null;
		}
		
		
		public String toString() {
			return ime;
		}
	}
	
	
	public boolean dodajGlumca(String ime, String naslov) {
		
		Film cilj = nadjiFilm(naslov);
		
		if (cilj == null || nadjiGlumca(ime, naslov) != null)
			return false;
			
		Glumac novi = new Glumac(ime);
		
		novi.veza = cilj.prviGlumac;
		cilj.prviGlumac = novi;
		
		return true;
	}
	
	
	private Glumac nadjiGlumca(String ime, String naslov) {
		
		Film cilj = nadjiFilm(naslov);
		
		if (cilj == null || cilj.prviGlumac == null)
			return null;
			
		Glumac tek = cilj.prviGlumac;
		
		while (tek != null) {
			
			if (tek.ime.equals(ime))
				return tek;
				
			tek = tek.veza;
		}
		
		return null;
	}
	
	
	class Film {
		
		String naslov;
		Glumac prviGlumac;
		Film veza;
		
		
		public Film(String naslov) {
			this.naslov = naslov;
			this.prviGlumac = null;
			this.veza = null;
		}
		
		
		public String toString() {
			
			String output = "[ '" + naslov + "' : ";
			
			Glumac tek = prviGlumac;
			
			while (tek != null) {
				
				output += tek + " ";
				tek = tek.veza;
			}
			
			return output + "]";
		}
	}
	
	
	Film prviFilm;
	
	
	public boolean dodajFilm(String naslov) {
		
		if (nadjiFilm(naslov) != null)
			return false;
		
		
		Film novi = new Film(naslov);
		
		novi.veza = prviFilm;
		prviFilm = novi;
		
		return true;
	}
	
	
	private Film nadjiFilm(String naslov) {
		
		if (prviFilm == null)
			return null;
			
		Film tek = prviFilm;
		
		while (tek != null) {
			
			if (tek.naslov.equals(naslov))
				return tek;
				
			tek = tek.veza;
		}
		
		return null;
	}
	
	
	public void obrisiSveSaGlumcem(String ime) {
		
		if (prviFilm == null)
			return;
			
		while (prviFilm != null && nadjiGlumca(ime, prviFilm.naslov) != null)
			prviFilm = prviFilm.veza;
		
		if (prviFilm == null)
			return;
			
		Film pret = prviFilm;
		
		while (pret.veza != null) {
			
			if (nadjiGlumca(ime, pret.veza.naslov) != null)
				pret.veza = pret.veza.veza;
			else
				pret = pret.veza;
		}
	}
	
	
	public String toString() {
		
		String output = "SPISAK FILMOVA : ";
		
		Film tek = prviFilm;
		
		while (tek != null) {
			
			output += tek + " ";
			tek = tek.veza;
		}
		
		return output + "\n";
	}
}
