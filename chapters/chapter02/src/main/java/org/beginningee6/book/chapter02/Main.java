package org.beginningee6.book.chapter02;



import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.Query;

import java.util.LinkedList;
import java.util.List;


public class Main {

    public static void main(String[] args) {

        // Creates an instance of book
        Book book = new Book();
		// TODO setter les propriétés title, price, description, isbn, nbOfPage, illustrations
        book.setDescription("Livre d'exemple");
        book.setIllustrations(true);
        book.setIsbn("1222.2");
        book.setNbOfPage(1000);
        book.setPrice((float) 10);
        book.setTitle("titre d'exemple");
        
        // Gets an entity manager
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("chapter02PU");
		// see the entity manager definition in src\main\resources\META-INF\persistence.xml   
                
        EntityManager em = emf.createEntityManager();
        
        EntityTransaction tx = em.getTransaction();
        
        Query q0 = em.createNamedQuery("findAllBooks");
        List<Book> resultat1 = q0.getResultList();
        System.out.println(resultat1.size());
        
        tx.begin();
        em.persist(book);
        
        book.setPrice((float) 25);
        em.merge(book);
        
        tx.commit();

        // Affichage de tous les Books en BDD
        /*
        Query q = em.createNamedQuery("findAllBooks");
        List<Book> resultat = q.getResultList();
        System.out.println(resultat.size());
        for (int i = 0; i < resultat.size(); i++) {
			System.out.println(resultat.get(i));
		}
        */
        
        // Suppression d'un objet en BDD en le récupérant par sa clé primaire
        /*
        Book bookASupprimer = em.find(Book.class, (long) 51);
        System.out.println(bookASupprimer);
        tx.begin();
        em.remove(bookASupprimer);
        tx.commit();
        */

		// fermer toutes les ressources JPA
        em.close();
        emf.close();
    }
}