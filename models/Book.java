package models;

public class Book {
    private int id;
    private String title;
    private Author author;
    private Boolean isAvailable;

    public Book(int id, String title, Author author, Boolean isAvailable) {
        this.id = id;
        this.title = title;
        this.author = author;
        this.isAvailable = isAvailable;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return this.id;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getTitle() {
        return this.title;
    }

    public void setAuthor(Author author) {
        this.author = author;
    }

    public Author getAuthor() {
        return this.author;
    }

    public void setIsAvailable(Boolean isAvailable) {
        this.isAvailable = isAvailable;
    }

    public Boolean getIsAvailable() {
        return this.isAvailable;
    }

    @Override
    public String toString() {
        return "Book: " + this.title + " (ID: " + this.id + ")";
    }
}