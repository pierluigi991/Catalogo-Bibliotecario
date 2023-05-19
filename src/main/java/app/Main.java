package app;


import java.time.LocalDate;



import entities.Elemento;
import entities.Libro;
import entities.Periodicita;
import entities.Prestito;
import entities.Rivista;

import entities.Utente;
import dao.ElementoDAO;

public class Main {

	public static void main(String[] args) {
	
		Elemento e1 = new Libro("10085684", "Enzo, il sogno di un ragazzo", LocalDate.of(2023, 5, 16), 460, "Enrico Brizzi", "Biografico");
		Elemento e2 = new Libro("1210762", "Pinocchio", LocalDate.of(2020, 1, 17), 180, "Carlo Collodi", "Bambini");
		Elemento e3 = new Rivista("10525257", "Cavalli e segugi", LocalDate.of(2023, 2, 5), 20, Periodicita.SETTIMANALE);
		
		ElementoDAO.saveElement(e1);
		ElementoDAO.saveElement(e2);
		ElementoDAO.saveElement(e3);
		
//		ElementoDAO.deleteByISBN("1210762");
		ElementoDAO.getByISBN("10525257");
		ElementoDAO.getByAnno(2020);
		ElementoDAO.getByAutore("Enrico Brizzi");
		ElementoDAO.getByTitolo("Enzo");
		
		Utente u1 = new Utente("Pierluigi", "Marzo", LocalDate.of(1991, 9, 15), 10085684);
		Utente u2 = new Utente("Iron", "Man", LocalDate.of(1970, 5, 29), 123456789);
		ElementoDAO.saveElement(u1);
		ElementoDAO.saveElement(u2);
		
		Prestito p1 = new Prestito(u1, e1, LocalDate.now(), LocalDate.of(2023, 12, 31));
		Prestito p2 = new Prestito(u2, e2, LocalDate.now(), null);
		Prestito p3 = new Prestito(u1, e1, LocalDate.now(), LocalDate.of(2023, 12, 31));
		Prestito p4 = new Prestito(u2, e3, LocalDate.now(), null);
		ElementoDAO.saveElement(p1);
		ElementoDAO.saveElement(p2);
		ElementoDAO.saveElement(p3);
		ElementoDAO.saveElement(p4);
		ElementoDAO.getElementiPrestati(123456789);
		ElementoDAO.getPrestitiScaduti();
		
}
}