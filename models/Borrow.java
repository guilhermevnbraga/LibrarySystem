package models;
import java.util.Date;

public class Borrow {
    private int bookId;
    private int customerId;
    private Date borrowedAt;
    private Date returnedAt;

    public Borrow(int bookId, int customerId, Date borrowedAt) {
        this.bookId = bookId;
        this.customerId = customerId;
        this.borrowedAt = borrowedAt;
    }

    public void setBookId(int bookId) {
        this.bookId = bookId;
    }

    public int getBookId() {
        return this.bookId;
    }

    public void setCstomerId(int customerId) {
        this.customerId = customerId;
    }

    public int getCustomerId() {
        return this.customerId;
    }

    public void setBorrowedAt(Date borrowedAt) {
        this.borrowedAt = borrowedAt;
    }

    public Date getBorrowedAt() {
        return this.borrowedAt;
    }

    public void setReturnedAt(Date returnedAt) {
        this.returnedAt = returnedAt;
    }

    public Date getReturnedAt() {
        return this.returnedAt;
    }
}
