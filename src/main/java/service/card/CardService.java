package service.card;

import model.Book;
import model.Card;
import model.Student;
import service.book.BookService;
import service.student.StudentService;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CardService implements ICardService{
    private Connection connection = config.Connection.getConnection();
    private BookService bookService = new BookService();
    private StudentService studentService = new StudentService();

    @Override
    public boolean insert(Card card) throws SQLException {
        boolean rowInserted ;

        PreparedStatement statement = connection.prepareStatement("insert into card(book_id,student_id,status,borrowDate,returnDate)value (?,?,?,?,?)");
        statement.setInt(1,card.getBook().getId());
        statement.setInt(2,card.getStudent().getId());
        statement.setInt(3,card.isStatus());
        statement.setDate(4,card.getBorrowDate());
        statement.setDate(5,card.getReturnDate());
        rowInserted = statement.executeUpdate()>0;
        return rowInserted;
    }

    @Override
    public boolean update(Card card) throws SQLException {
        boolean rowUpdated;
        PreparedStatement statement = connection.prepareStatement("update card set book_id=?,student_id=?,status=?,borrowDate=?,returnDate=?where id=?");
        statement.setInt(1,card.getBook().getId());
        statement.setInt(2,card.getStudent().getId());
        statement.setInt(3,card.isStatus());
        statement.setDate(4,card.getBorrowDate());
        statement.setDate(5,card.getReturnDate());
        statement.setInt(6,card.getId());
        rowUpdated = statement.executeUpdate()>0;
        return rowUpdated;

    }

    @Override
    public boolean delete(int id) throws SQLException {
        boolean rowDeleted ;
        PreparedStatement statement = connection.prepareStatement("delete from card where id = ?;");
        statement.setInt(1,id);
        rowDeleted = statement.executeUpdate()>0;
        return  rowDeleted;
    }

    @Override
    public Card findById(int id) throws SQLException {
        Card card = null;
        PreparedStatement statement = connection.prepareStatement("select * from card where id = ?");
        statement.setInt(1,id);
        ResultSet rs = statement.executeQuery();
        while (rs.next()){
            int book_id = rs.getInt("book_id");
            Book book = bookService.findById(book_id);
            int student_id = rs.getInt("student_id");
            Student student = studentService.findById(student_id);
            int status =  rs.getInt("status");
            Date borrowDate = rs.getDate("borrowDate");
            Date returnDate = rs.getDate("returnDate");
            card = new Card(id,book,student,status,borrowDate,returnDate);
        }
        return card;
    }

    public static void main(String[] args) {
        CardService cardService = new CardService();
        List<Card> cardServices = new ArrayList<>();
        try {
            cardServices = cardService.findAll();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        for (Card c: cardServices
             ) {
            System.out.println(c);
        }
    }
public boolean updateStatus(Card card){
        boolean rowUpdated = false;
    PreparedStatement statement = null;
    try {
        statement = connection.prepareStatement("update card set status = ? where id =?");
        statement.setInt(1,card.isStatus());
        statement.setInt(2,card.getId());
        rowUpdated = statement.executeUpdate()>0;
    } catch (SQLException e) {
        e.printStackTrace();
    }
    return rowUpdated;
}
    @Override
    public List<Card> findAll() throws SQLException {
        List<Card>cards = new ArrayList<>();
        PreparedStatement statement = connection.prepareStatement("select * from card where status = 1;");
        ResultSet rs = statement.executeQuery();
        while (rs.next()){
            int id = rs.getInt("id");
            int book_id = rs.getInt("book_id");
            Book book = bookService.findById(book_id);
            int student_id = rs.getInt("student_id");
            Student student = studentService.findById(student_id);
            int status =  rs.getInt("status");
            Date borrowDate = rs.getDate("borrowDate");
            Date returnDate = rs.getDate("returnDate");
           cards.add( new Card(id,book,student,status,borrowDate,returnDate));
        }
        return cards;
    }
}
