package models;
import java.util.Date;

public class Book {
    private int id;
    private String title;
    private int authorId;
    private Boolean isAvailable;
    private Date createdAt;
    private Date updatedAt;

    public Book(int id, String title, int authorId, Boolean isAvailable, Date createdAt, Date updatedAt) {
        this.id = id;
        this.title = title;
        this.authorId = authorId;
        this.isAvailable = isAvailable;
        this.createdAt = createdAt;
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

    public void setAuthor(int authorId) {
        this.authorId = authorId;
    }

    public int getAuthor() {
        return this.authorId;
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
}