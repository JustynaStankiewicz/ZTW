package pl.edu.pwr.ztw.books;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class BooksControler {
    @Autowired
    IBooksService booksService;

    @RequestMapping(value = "/get/books", method = RequestMethod.GET)
    public ResponseEntity<List<Book>> getBooks() {
        List<Book> books = booksService.getBooks().stream().toList();
        HttpHeaders headers = new HttpHeaders();
        headers.add("Access-Control-Allow-Origin", "*");
        headers.setContentType(MediaType.APPLICATION_JSON);
        return new ResponseEntity<>(books, headers, HttpStatus.OK);
    }

    @RequestMapping(value = "/get/book/{id}", method = RequestMethod.GET)
    public ResponseEntity<Object> getBook(@PathVariable("id") int id) {
        HttpHeaders headers = new HttpHeaders();
        headers.add("Access-Control-Allow-Origin", "*");
        return new ResponseEntity<>(booksService.getBook(id), headers, HttpStatus.OK);
    }

    @RequestMapping(value = "/get/booksWithAuthor", method = RequestMethod.GET)
    public ResponseEntity<Object> getBooksWithAuthor() {
        List<BookWithAuthor> books = booksService.getBooksWithAuthor().stream().toList();
        HttpHeaders headers = new HttpHeaders();
        headers.add("Access-Control-Allow-Origin", "*");
        headers.setContentType(MediaType.APPLICATION_JSON);
        return new ResponseEntity<>(books, headers, HttpStatus.OK);
    }

    @RequestMapping(value = "/get/bookWithAuthor/{id}", method = RequestMethod.GET)
    public ResponseEntity<Object> getBookWithAuthor(@PathVariable("id") int id) {
        HttpHeaders headers = new HttpHeaders();
        headers.add("Access-Control-Allow-Origin", "*");
        return new ResponseEntity<>(booksService.getBookWithAuthor(id), headers, HttpStatus.OK);
    }

    @RequestMapping(value = "/delete/book/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<Object> deleteBook(@PathVariable("id") int id) {
        HttpHeaders headers = new HttpHeaders();
        headers.add("Access-Control-Allow-Origin", "*");
        return new ResponseEntity<>(booksService.deleteBook(id), headers, HttpStatus.OK);
    }

    @RequestMapping(value = "/update/book/{id}", method = RequestMethod.POST)
    public ResponseEntity<Object> updateBook(@PathVariable("id") int id, Book book) {
        HttpHeaders headers = new HttpHeaders();
        headers.add("Access-Control-Allow-Origin", "*");
        return new ResponseEntity<>(booksService.updateBook(id, book), headers, HttpStatus.OK);
    }

    @RequestMapping(value = "/add/book", method = RequestMethod.POST)
    public ResponseEntity<Object> addBook(Book book) {
        HttpHeaders headers = new HttpHeaders();
        headers.add("Access-Control-Allow-Origin", "*");
        return new ResponseEntity<>(booksService.addBook(book), headers, HttpStatus.OK);
    }
}
