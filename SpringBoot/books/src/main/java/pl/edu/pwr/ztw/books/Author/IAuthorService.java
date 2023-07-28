package pl.edu.pwr.ztw.books.Author;

import java.util.Collection;

public interface IAuthorService {
    public abstract Collection<Author> getAuthors();
    public abstract Author getAuthor(int id);
    public abstract boolean deleteAuthor(int id);
    public abstract boolean updateAuthor(int id, Author newAuthor);
    public abstract boolean addAuthor(Author newAuthor);
}
