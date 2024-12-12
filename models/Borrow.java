package models;
import java.util.Date;

public class Borrow {
    private Book book;
    private Customer borrower;
    private Date borrowedAt;
    private Date returnedAt;

    public Borrow(Book book, Customer borrower, Date borrowedAt) {
        this.book = book;
        this.borrower = borrower;
        this.borrowedAt = borrowedAt;
    }

    public void setBook(Book book) {
        this.book = book;
    }

    public Book getBook() {
        return this.book;
    }

    public void setBorrower(Customer borrower) {
        this.borrower = borrower;
    }

    public Customer getBorrower() {
        return this.borrower;
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
