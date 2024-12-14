package models;
import java.util.Date;

public class Book {
    private int id;
    private String title;
    private String genre;
    private Author author;
    private Boolean isAvailable;
    private Date createdAt;
    private Date updatedAt;

    public Book(int id, String title, String genre, Author author, Boolean isAvailable, Date createdAt, Date updatedAt) {
        this.id = id;
        this.title = title;
        this.genre = genre;
        this.author = author;
        this.isAvailable = isAvailable;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
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

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public String getGenre() {
        return this.genre;
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

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public Date getCreatedAt() {
        return this.createdAt;
    }

    public void setUpdatedAt(Date updatedAt) {
        this.updatedAt = updatedAt;
    }

    public Date getUpdatedAt() {
        return this.updatedAt;
    }

    @Override
    public String toString() {
        return "Book: " + this.title + " (ID: " + this.id + ")";
    }
}