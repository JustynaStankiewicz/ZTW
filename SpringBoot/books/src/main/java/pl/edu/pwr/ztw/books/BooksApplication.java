package pl.edu.pwr.ztw.books;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

//swagger http://localhost:8080/swagger-ui/index.html
@SpringBootApplication
public class BooksApplication {
    public static void main(String[] args) {
        SpringApplication.run(BooksApplication.class, args);
    }

}
