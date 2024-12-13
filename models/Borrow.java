package models;
import java.util.Date;

public class Borrow {
    private int bookId;
    private int customerId;
    private Date borrowedAt;
    private Date returnedAt;

    public Borrow(int bookId, int customerId, Date borrowedAt, Date returnedAt) {
        this.bookId = bookId;
        this.customerId = customerId;
        this.borrowedAt = borrowedAt;
        this.returnedAt = returnedAt;
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

    @Override
    public String toString() {
        return "Borrow: Book ID: " + this.bookId + ", Customer ID: " + this.customerId + ", Borrowed At: " + this.borrowedAt + ", Returned At: " + this.returnedAt;
    }
}
