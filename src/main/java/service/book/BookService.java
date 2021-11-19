package service.book;

import model.Book;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class BookService implements IBookService{
private Connection connection = config.Connection.getConnection();
    @Override
    public boolean insert(Book book) throws SQLException {
        return false;
    }

    @Override
    public boolean update(Book book) throws SQLException {
        return false;
    }

    @Override
    public boolean delete(int id) throws SQLException {
        return false;
    }

    @Override
    public Book findById(int id) throws SQLException {
        Book book = null;
        PreparedStatement statement = connection.prepareStatement("select * from book where id = ?;");
        statement.setInt(1,id);
        ResultSet rs = statement.executeQuery();
        while (rs.next()){
            String name = rs.getString("name");
            String author = rs.getString("author");
            String des = rs.getString("des");
            int quantity = rs.getInt("quantity");
            book = new Book(id,name,author,des,quantity);
        }
        return book;
    }

    public static void main(String[] args) {
        BookService bookService = new BookService();
        List<Book> books = new ArrayList<>();
        try {
          books = bookService.findAll();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        for (Book a:books
             ) {
            System.out.println(a);
        }
    }
public boolean updateQuantityWhileBorrowed(Book book) throws SQLException{
        boolean rowUpdated;
    PreparedStatement statement = null;
    int newQuantity = book.getQuantity()-1;
    try {
        statement = connection.prepareStatement("update book set quantity = ? where id = ?;");
    } catch (SQLException e) {
        e.printStackTrace();
    }
    statement.setInt(1,newQuantity);
    statement.setInt(2,book.getId());
    rowUpdated = statement.executeUpdate()>0;
    return rowUpdated;
}
public boolean updateQuantityWhileReturned(Book book) throws SQLException{
        boolean rowUpdated;
    PreparedStatement statement = null;
    int newQuantity = book.getQuantity()+1;
    try {
        statement = connection.prepareStatement("update book set quantity = ? where id = ?;");
    } catch (SQLException e) {
        e.printStackTrace();
    }
    statement.setInt(1,newQuantity);
    statement.setInt(2,book.getId());
    rowUpdated = statement.executeUpdate()>0;
    return rowUpdated;
}
    @Override
    public List<Book> findAll() throws SQLException {
        List<Book> books = new ArrayList<>();
        PreparedStatement statement = connection.prepareStatement("select * from book;");
        ResultSet rs = statement.executeQuery();
        while (rs.next()){
            int id = rs.getInt("id");
            String name = rs.getString("name");
            String author = rs.getString("author");
            String des = rs.getString("des");
            int quantity = rs.getInt("quantity");
            books.add(new Book(id,name,author,des,quantity));
        }
        return books;
    }
}
