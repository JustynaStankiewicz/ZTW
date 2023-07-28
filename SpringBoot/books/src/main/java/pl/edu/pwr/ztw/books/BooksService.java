package pl.edu.pwr.ztw.books;

import org.springframework.stereotype.Service;
import pl.edu.pwr.ztw.books.Author.Author;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import static pl.edu.pwr.ztw.books.Author.AuthorService.authorsRepo;

@Service
public class BooksService implements IBooksService {
    private static List<Book> booksRepo = new ArrayList<>();

    static {
        booksRepo.add(new Book(1, "Potop", 0, 123));
        booksRepo.add(new Book(2, "Wesele", 1, 150));
        booksRepo.add(new Book(3, "Dziady", 2, 292));
    }

    @Override
    public Collection<Book> getBooks() {
        return booksRepo;
    }

    @Override
    public Book getBook(int id) {
        for (Book book : booksRepo) {
            if (book.getId() == id) {
                return book;
            }
        }
        return null;
    }

    public Collection<BookWithAuthor> getBooksWithAuthor() {
        List<BookWithAuthor> booksWithAuthor = new ArrayList<>();
        for (Book book : booksRepo) {
            for (Author author : authorsRepo) {
                if (author.getId() == book.getAuthor()) {
                    booksWithAuthor.add(new BookWithAuthor(book, author));
                }
            }
            booksWithAuthor.add(new BookWithAuthor(book, null));
        }
        return booksWithAuthor;
    }

    public BookWithAuthor getBookWithAuthor(int id) {
        for (Book book : booksRepo) {
            if (book.getId() == id) {
                for (Author author : authorsRepo) {
                    if (author.getId() == book.getAuthor()) {
                        return new BookWithAuthor(book, author);
                    }
                }
                return new BookWithAuthor(book, null);
            }
        }
        return null;
    }

    @Override
    public boolean deleteBook(int id) {
        for (Book book : booksRepo) {
            if (book.getId() == id) {
                booksRepo.remove(book);
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean updateBook(int id, Book newBook) {
        for (Book book : booksRepo) {
            if (book.getId() == id) {
                booksRepo.add(newBook);
                booksRepo.remove(book);
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean addBook(Book newBook) {
        for (Book book : booksRepo) {
            if (book.getId() == newBook.getId()) {
                return false;
            }
        }

        booksRepo.add(newBook);
        return true;
    }
}
