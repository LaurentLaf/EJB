package org.beginningee6.book.chapter10;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

// TODO import ...

// TODO Annotations pour que cette classe soit un ManagedBean pour JSF et de scope Request
@ManagedBean @RequestScoped
public class BookController {

    // ======================================
    // =             Attributes             =
    // ======================================

	// TODO en TP6  notre EJB Session
	// ajouter une annotation pour realiser une inject CDI de type EJB et supprimer l'appel au constructeur
	@EJB
    private BookEJB bookEJB;
    //=  new BookEJB();
    
	// TP4 à commenter pout TP6
    private BookDTO bookDTO = new BookDTO();
    private List<BookDTO> bookDTOList = new ArrayList<BookDTO>();
	
	// TODO TP6
	// private Book book = new Book();
    // private List<Book> bookList = new ArrayList<Book>();

    // ======================================
    // =           Public Methods           =
    // ======================================

	// TODO en TP6
    /*
    public String doCreateBook() {
        book = bookEJB.createBook(book);
        bookList = bookEJB.findBooks();
		
		// TODO en return, donner la page listBooks.xhtml à afficher comme prochaine VUE
        return "...";
    }
    */
    
	// TODO en TP4
	public String doCreateBookDTO() {
        bookDTO = bookEJB.createBook(bookDTO);
        bookDTOList = bookEJB.findBooksDTO();
		
		// TODO en return, donner la page listBooks.xhtml à afficher comme prochaine VUE
        return "listBooks.xhtml";
    }
    // ======================================
    // =          Getters & Setters         =
    // ======================================
	/*
    public Book getBook() {
        return book;
    }

    public void setBook(Book book) {
        this.book = book;
    }

    public List<Book> getBookList() {
        return bookList;
    }

    public void setBookList(List<Book> bookList) {
        this.bookList = bookList;
    }
    */

	public BookDTO getBook() {
		return bookDTO;
	}

	public void setBook(BookDTO bookDTO) {
		this.bookDTO = bookDTO;
	}

	public List<BookDTO> getBookList() {
		return bookDTOList;
	}

	public void setBookList(List<BookDTO> bookDTOList) {
		this.bookDTOList = bookDTOList;
	}
    
	
    
}