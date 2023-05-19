package entities;

import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;


import org.apache.commons.io.FileUtils;

public class Archivio {
	static File file;
	static String divisor = "%n------------------------------%n%n";
	private static final Charset ENCODING = null;
//	Attributes
	private List<Elemento> listaElementi;

	public Archivio() {
		listaElementi = new ArrayList<Elemento>();
	}

//	Overload adding methods 
	public void aggiungiElemento(Elemento elemento) {
		listaElementi.add(elemento);
		System.out.println("Elemento aggiunto con successo!");
	}

	public void aggiungiElemento(ArrayList<Elemento> elementList) {
		elementList.forEach(e -> listaElementi.add(e));
	}
	
//	To string method
	String string = "";
	@Override
	public String toString() {
		this.listaElementi.forEach(e -> string += e.toString() + (System.lineSeparator().repeat(2)));
		return string;
	}

//	Get element list
	public List<Elemento> getListaElementi() {
		return listaElementi;
	}

//	Print archive
	public void printArchivio() {
		listaElementi.forEach(e -> System.out.println(e.toString()));
	}

//	Remove by ISBN
	public void rimuoviIsbn(String codIsbn) {
		try {
		listaElementi.removeIf(e -> e.getCodIsbn().equals(codIsbn));
		System.out.printf("%nEliminazione effettuata con successo!");
		} catch (Error e) {
			System.out.println("Nessun elemento corrispondente trovato");
		}
	}

//	Find by ISBN
	public void ricercaIsbn(String codIsbn) {
		System.out.printf(divisor);
		System.out.println("Ricerca per ISBN: " + codIsbn);
		listaElementi.stream().filter(e -> e.getCodIsbn().equals(codIsbn))
				.forEach(e -> System.out.println(e.toString()));
	}

//	Find by year
	public void ricercaAnno(int anno) {
		System.out.printf(divisor);
		System.out.println("Ricerca per ANNO: " + anno);
		listaElementi.stream().filter(e -> e.getPubblicazione().getYear() == anno)
				.forEach(e -> System.out.println(e.toString()));
	}

//	Find by author
	public void ricercaAutore(String autore) {
		System.out.printf(divisor);
		System.out.println("Ricerca per AUTORE: " + autore);
		listaElementi.stream().filter(e -> e instanceof Libro).filter(e -> ((Libro) e).getAutore().equals(autore))
				.forEach(e -> System.out.println(e.toString()));
	}

//	Find by type overload
	public void ricerca(Tipo tipo, String criterio) {
		switch (tipo) {
		case ISBN: {
			this.ricercaIsbn(criterio);
			break;
		}
		case AUTORE: {
			this.ricercaAutore(criterio);
			break;
		}
		case TITOLO: {
			System.out.printf(divisor);
			System.out.println("Ricerca per TITOLO: " + criterio);
			listaElementi.stream().filter(e -> e.getTitolo().equals(criterio))
			.forEach(e -> System.out.println(e.toString()));
			break;
		}
		default: {
			System.out.println("La ricerca non ha prodotto risultati!");
		}
		}
	}

	public void ricerca(Tipo tipo, int year) {
		switch (tipo) {
		case ANNO: {
			this.ricercaAnno(year);
			break;
		}
		default: {
			System.out.println("La ricerca non ha prodotto risultati!");
		}
		}
	}

	public void scriviFile(String fileName, Elemento elemento) {
		File file = new File(fileName);
		if (file.exists()) {
			try {
				FileUtils.writeStringToFile(file, elemento.toString(), ENCODING, true);
				System.out.println("Hai scritto correttamente sul file!");
			} catch (IOException e) {
				e.printStackTrace();
			}
		} else {
			System.out.printf("il file %s non esiste %n", file);
		}
	}
	
	public void scriviFile(String fileName, Archivio elemento) {
		file = new File(fileName);
		if (file.exists()) {
			try {
				FileUtils.writeStringToFile(file, elemento.toString(), ENCODING, true);
				System.out.printf(divisor);
				System.out.println("Hai scritto correttamente sul file!");
				System.out.printf(divisor);
			} catch (IOException e) {
				e.printStackTrace();
			}
		} else {
			System.out.printf("il file %s non esiste %n", file);
		}
	}

	public static void leggiFile(String fileName) throws IOException {
		file = new File(fileName);
		System.out.printf(divisor);
		System.out.println(FileUtils.readFileToString(file, ENCODING));
	}
}
