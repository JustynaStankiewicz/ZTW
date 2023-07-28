package pl.edu.pwr.ztw.books.Author;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Service
public class AuthorService implements IAuthorService {
    public static List<Author> authorsRepo = new ArrayList<>();

    static {
        authorsRepo.add(new Author(1, "Henryk", "Sienkiewicz"));
        authorsRepo.add(new Author(2, "Stanisław", "Reymont"));
        authorsRepo.add(new Author(3, "Adam", "Mickiewicz"));
    }

    @Override
    public Collection<Author> getAuthors() {
        return authorsRepo;
    }

    @Override
    public Author getAuthor(int id) {
        for (Author author : authorsRepo) {
            if (author.getId() == id) {
                return author;

            }
        }
        return null;
    }

    @Override
    public boolean deleteAuthor(int id) {
        for (Author author : authorsRepo) {
            if (author.getId() == id) {
                authorsRepo.remove(author);
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean updateAuthor(int id, Author newAuthor) {
        for (Author author : authorsRepo) {
            if (author.getId() == id) {
                authorsRepo.add(newAuthor);
                authorsRepo.remove(author);
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean addAuthor(Author newAuthor) {
        for (Author author : authorsRepo) {
            if (author.getId() == newAuthor.getId()) {
                return false;
            }
        }
        authorsRepo.add(newAuthor);
        return true;
    }
}
