package org.beginningee6.book.chapter10;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

//TODO import 


// TODO : en TP6 -couplage avec la persistance en Base
// anotation @Stateless : indique qu'il s'agit d1 EJB Sesion Stateless 
@Stateless
public class BookEJB {

    // ======================================
    // =             Attributes             =
    // ======================================
	
	private List<BookDTO> books = new ArrayList<BookDTO>();

	public BookEJB() {
		for (int i = 0; i < 3; i++) {
			BookDTO bookDTO = new BookDTO("TitreBookDTO"+i, (float) 10, "Description book "+i, "isbn "+i, 115, true); 
			this.books.add(bookDTO);
		}
	}
// TODO : en TP6 -couplage avec la persistance en Base
// decommenter  ce bloc

	@PersistenceContext(unitName = "chapter10PU")
    private EntityManager em;

    // ======================================
    // =           Public Methods           =
    // ======================================

    @SuppressWarnings("unchecked")
	public List<Book> findBooks() { 
        // TODO TP6 - appeler la Bases de données  	
    	// TODO Executer la requete nommée 'findAllBooks' et demander la ResultList
    	List<Book> booksList = em.createNamedQuery("findAllBooks").getResultList();
    	return booksList; 
    }

    public Book createBook(Book book) {
        em.persist(book);
        return book;
    }
    
    
    // Méthodes bouchon pour tester book 
    
    public BookDTO createBook(BookDTO book) {
        books.add(book);
        return book;
    }
    
	public List<BookDTO> findBooksDTO() { 
		// TODO créer une liste de Book comprenant des livres bidons.
		List<BookDTO> booksDTOList = new ArrayList<BookDTO>();
		// creer 10 livres, peupler leurs propriétés et les ajouter à la liste de Books
		for (int i = 0; i < 10; i++) {
			BookDTO bookDTO = new BookDTO("TitreBookDTO"+i, (float) 10, "Description book "+i, "isbn "+i, 115, true); 
			booksDTOList.add(bookDTO);
		}
    	return booksDTOList; 
    }
	
	
    
}