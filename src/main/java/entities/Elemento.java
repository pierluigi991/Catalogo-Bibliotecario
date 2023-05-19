package entities;
import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.NamedQuery;
import javax.persistence.SequenceGenerator;


@Entity
@NamedQuery(name = "getByISBN", query = "select e from Elemento e where e.codIsbn = :isbn")
@NamedQuery(name = "getByAutore", query = "select e from Elemento e where e.autore = :autore")
@NamedQuery(name = "getByTitolo", query = "select e from Elemento e where e.titolo like :titolo")
@NamedQuery(name = "getByAnno", query = "select e from Elemento e where extract(year from e.pubblicazione)=:anno")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
public abstract class Elemento {
	
	@Id
	@SequenceGenerator(name = "elemento_seq", sequenceName = "elemento_seq", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.IDENTITY, generator = "elemento_seq")
	private Long id;
	
	private String titolo;
	private LocalDate pubblicazione;
	private int pagine;
	
	@Column(name = "cod_isbn")
	private String codIsbn;
	
	
	public Elemento() {

	}
	
	public Elemento(String codIsbn, String titolo, LocalDate pubblicazione, int pagine) {
		this.codIsbn = codIsbn;
		this.titolo = titolo;
		this.pubblicazione = pubblicazione;
		this.pagine = pagine;
	}
	
	//GETTERS e SETTERS 
	
		public String getCodIsbn() {
			return codIsbn;
		}

		public String getTitolo() {
			return titolo;
		}

		public LocalDate getPubblicazione() {
			return pubblicazione;
		}

		public int getPagine() {
			return pagine;
		}
}
