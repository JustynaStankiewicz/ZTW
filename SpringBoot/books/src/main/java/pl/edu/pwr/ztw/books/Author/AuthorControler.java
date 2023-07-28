package pl.edu.pwr.ztw.books.Author;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AuthorControler {
    @Autowired
    IAuthorService authorService;

    @RequestMapping(value = "/get/authors", method = RequestMethod.GET)
    public ResponseEntity<Object> getAuthors() {
        return new ResponseEntity<>(authorService.getAuthors(), HttpStatus.OK);
    }

    @RequestMapping(value = "/get/author/{id}", method = RequestMethod.GET)
    public ResponseEntity<Object> getAuthor(@PathVariable("id") int id) {
        return new ResponseEntity<>(authorService.getAuthor(id), HttpStatus.OK);
    }

    @RequestMapping(value = "/delete/author/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<Object> deleteAuthor(@PathVariable("id") int id) {
        return new ResponseEntity<>(authorService.deleteAuthor(id), HttpStatus.OK);
    }

    @RequestMapping(value = "/update/author/{id}", method = RequestMethod.POST)
    public ResponseEntity<Object> updateAuthor(@PathVariable("id") int id, Author author) {
        return new ResponseEntity<>(authorService.updateAuthor(id, author), HttpStatus.OK);
    }

    @RequestMapping(value = "/add/author", method = RequestMethod.POST)
    public ResponseEntity<Object> addAuthor(Author author) {
        return new ResponseEntity<>(authorService.addAuthor(author), HttpStatus.OK);
    }
}
