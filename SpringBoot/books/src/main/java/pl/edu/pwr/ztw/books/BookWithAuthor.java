package pl.edu.pwr.ztw.books;

import pl.edu.pwr.ztw.books.Author.Author;

public class BookWithAuthor {
    private int id;
    private String title;
    private Author author;
    int pages;

    public BookWithAuthor(int id, String title, Author authorId, int pages) {
        this.id = id;
        this.title = title;
        this.author = authorId;
        this.pages = pages;
    }

    public BookWithAuthor(Book book, Author author) {
        this.id = book.getId();
        this.title = book.getTitle();
        this.author = author;
        this.pages = book.getPages();
    }

    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Author getAuthor() {
        return author;
    }

    public void setAuthor(Author author) {
        this.author = author;
    }

    public int getPages() {
        return pages;
    }

    public void setPages(int pages) {
        this.pages = pages;
    }
}
