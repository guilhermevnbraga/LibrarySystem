package models;
import java.text.SimpleDateFormat;
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
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        String borrowedAtString = (borrowedAt != null) ? dateFormat.format(borrowedAt) : "N/A";
        String returnedAtString = (returnedAt != null) ? dateFormat.format(returnedAt) : "N/A";
        return """
               Borrow Details:
               Book ID: %d
               Customer ID: %d
               Borrowed At: %s
               Returned At: %s
               """.formatted(bookId, customerId, borrowedAtString, returnedAtString);
    }
}
