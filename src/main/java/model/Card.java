package model;

import java.sql.Date;
import java.time.LocalDate;

public class Card {
    private int id;
    private Book book;
    private Student student;
    private int status;
    private Date borrowDate;
    private Date returnDate;

    public Card() {
    }

    public Card(int id, Book book, Student student, int status, Date borrowDate, Date returnDate) {
        this.id = id;
        this.book = book;
        this.student = student;
        this.status = status;
        this.borrowDate = borrowDate;
        this.returnDate = returnDate;
    }

    public Card(Book book, Student student, int status, Date borrowDate, Date returnDate) {
        this.book = book;
        this.student = student;
        this.status = status;
        this.borrowDate = borrowDate;
        this.returnDate = returnDate;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Book getBook() {
        return book;
    }

    public void setBook(Book book) {
        this.book = book;
    }

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    public int isStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public Date getBorrowDate() {
        return borrowDate;
    }

    public void setBorrowDate(Date borrowDate) {
        this.borrowDate = borrowDate;
    }

    public Date getReturnDate() {
        return returnDate;
    }

    public void setReturnDate(Date returnDate) {
        this.returnDate = returnDate;
    }

    @Override
    public String toString() {
        return "Card{" +
                "id=" + id +
                ", book=" + book +
                ", student=" + student +
                ", status=" + status +
                ", borrowDate=" + borrowDate +
                ", returnDate=" + returnDate +
                '}';
    }
}
