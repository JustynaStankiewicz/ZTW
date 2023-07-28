package pl.edu.pwr.ztw.books;

import java.util.Collection;

public interface IBooksService {
    public abstract Collection<Book> getBooks();
    public abstract Book getBook(int id);
    public abstract Collection<BookWithAuthor> getBooksWithAuthor();
    public abstract BookWithAuthor getBookWithAuthor(int id);
    public abstract boolean deleteBook(int id);
    public abstract boolean updateBook(int id, Book newBook);
    public abstract boolean addBook(Book newBook);

}
