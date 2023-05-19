package entities;

import java.time.LocalDate;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.SequenceGenerator;

@Entity

@NamedQuery(name = "getElementiPrestati", query = "SELECT pr FROM Prestito pr WHERE pr.utente.numeroTessera = :tessera AND pr.restituzioneEffettiva IS NULL")
@NamedQuery(name = "getPrestitiScaduti", query = "SELECT pr FROM Prestito pr WHERE pr.dataRestituzionePrevista < CURRENT_DATE AND pr.restituzioneEffettiva IS NULL")
public class Prestito {
	@Id
	@SequenceGenerator(name = "prestito_seq", sequenceName = "prestito_seq", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.IDENTITY, generator = "prestito_seq")
	private Long id;
	
	@ManyToOne
	private Utente utente;
	
	@ManyToOne
	private Elemento elementoPrestato;
	
	@Column(name="inizio_prestito")
	private LocalDate inizioPrestito = LocalDate.now();
	@Column(name="data_restituzione_prevista")
	private LocalDate dataRestituzionePrevista = this.inizioPrestito.plusMonths(1);
	@Column(name="restituzione_effettiva")
	private LocalDate restituzioneEffettiva;
	
	//CONSTRUCTORS
	public Prestito() {
	}

	public Prestito(Utente utente, Elemento elementoPrestato, LocalDate inizioPrestito, LocalDate restituzioneEffettiva) {
		super();
		this.utente = utente;
		this.elementoPrestato = elementoPrestato;
		this.inizioPrestito = inizioPrestito;
		this.dataRestituzionePrevista = this.inizioPrestito.plusMonths(1);
		this.restituzioneEffettiva = restituzioneEffettiva;
	}

	//GETTERS e SETTERS 
	public Utente getUtente() {
		return utente;
	}

	public void setUtente(Utente utente) {
		this.utente = utente;
	}

	public Elemento getElementoPrestato() {
		return elementoPrestato;
	}

	public void setElementoPrestato(Elemento elementoPrestato) {
		this.elementoPrestato = elementoPrestato;
	}

	public LocalDate getInizioPrestito() {
		return inizioPrestito;
	}

	public void setInizioPrestito(LocalDate inizioPrestito) {
		this.inizioPrestito = inizioPrestito;
	}

	public LocalDate getDataRestituzionePrevista() {
		return dataRestituzionePrevista;
	}

	public void setDataRestituzionePrevista(LocalDate dataRestituzionePrevista) {
		this.dataRestituzionePrevista = dataRestituzionePrevista;
	}

	public LocalDate getRestituzioneEffettiva() {
		return restituzioneEffettiva;
	}

	public void setRestituzioneEffettiva(LocalDate restituzioneEffettiva) {
		this.restituzioneEffettiva = restituzioneEffettiva;
	}

	@Override
	public String toString() {
		return "Prestito [id=" + id + ", utente=" + utente + ", elementoPrestato=" + elementoPrestato
				+ ", inizioPrestito=" + inizioPrestito + ", dataRestituzionePrevista=" + dataRestituzionePrevista
				+ ", restituzioneEffettiva=" + restituzioneEffettiva + "]";
	}	
	
	
}