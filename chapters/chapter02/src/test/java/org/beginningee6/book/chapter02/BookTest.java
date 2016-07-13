package org.beginningee6.book.chapter02;

import org.beginningee6.book.chapter02.Book;
import static junit.framework.Assert.assertNotNull;
import static org.junit.Assert.assertEquals;

import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.Query;

import java.sql.SQLException;
import java.util.List;


public class BookTest {

    // ======================================
    // =             Attributes             =
    // ======================================
    private static EntityManagerFactory emf;
    private static EntityManager em;
    private static EntityTransaction tx;

    // ======================================
    // =          Lifecycle Methods         =
    // ======================================
    @BeforeClass
	// method lancée 1 seule fois AVANT pour tous les tests
    public static void initEntityManager() throws Exception {
		// see the entity manager definition in src\test\resources\META-INF\persistence.xml
        emf = Persistence.createEntityManagerFactory("chapter02PU");
        em = emf.createEntityManager();
    }

    @AfterClass
	// method lancée 1 seule fois APRES pour tous les tests
    public static void closeEntityManager() throws SQLException {
        if (em != null) em.close();
        if (emf != null) emf.close();
    }

    @Before
	// method lancée 1  fois AVANT chaque test
    public void initTransaction() {
		// obtenir la transaction
    }

    // ======================================
    // =              Unit tests            =
    // ======================================
    @SuppressWarnings("unchecked")
	@Test
    public void shouldCreateABook() throws Exception {
		// Retrieves all the books from the database
        List<Book> booksAvant = em.createNamedQuery("findAllBooks").getResultList();
		// memoriser le nombre de livres AVANT le test
		int nbLivresAVANT = booksAvant.size();
		
        // Creates an instance of book
        Book book = new Book();
		
        book.setDescription("Livre d'exemple");
        book.setIllustrations(true);
        book.setIsbn("1222.2");
        book.setNbOfPage(1000);
        book.setPrice((float) 10);
        book.setTitle("titre d'exemple");
        // Persists the book to the database
        EntityTransaction tx = em.getTransaction();
        tx.begin();
        em.persist(book);
        tx.commit();
		
        // Retrieves all the books from the database
        List<Book> booksApres = em.createNamedQuery("findAllBooks").getResultList();
		// memoriser le nombre de livres APRES le test
		int nbLivresAPRES = booksApres.size();
		
		// verifier que book.id n'est pas null
        assertNotNull(book.getId());
		
		// verfier que le nombre de livres APRS est egal au nombre de livres AVANT + 1 
		assertEquals(nbLivresAVANT+1,nbLivresAPRES);
    }
}