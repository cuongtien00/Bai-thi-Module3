package controller;

import model.Book;
import model.Card;
import model.Student;
import service.book.BookService;
import service.card.CardService;
import service.student.StudentService;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Calendar;

@WebServlet(name = "CardServlet", value = "/cards")
public class CardServlet extends HttpServlet {
    private final BookService bookService = new BookService();
    private final StudentService studentService = new StudentService();
    private final CardService cardService = new CardService();
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        if (action == null) {
            action = "";
        }
        switch (action) {
            case "create":
                showCreateForm(request,response);
                break;
            case "return":
                returnBookConfirm(request,response);
                break;
            case "view":
                showCardView(request,response);
                break;
            case "sort":
                sortByName(request,response);
                break;
            default:
                listCards(request, response);
                break;
        }
    }

    private void returnBookConfirm(HttpServletRequest request, HttpServletResponse response) {
        RequestDispatcher dispatcher = request.getRequestDispatcher("card/returnConfirm.jsp");
        int id = Integer.parseInt(request.getParameter("id"));
        Card card = null;
        Book book = null;
        try {
            card = cardService.findById(id);
           book = bookService.findById(card.getBook().getId());
        } catch (SQLException e) {
            e.printStackTrace();
        }
        request.setAttribute("book",book);
        try {
            dispatcher.forward(request,response);
        } catch (ServletException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void showCardView(HttpServletRequest request, HttpServletResponse response) {
    }

    private void showCreateForm(HttpServletRequest request, HttpServletResponse response) {
        int bookId = Integer.parseInt(request.getParameter("id"));
        Book book = null;
        try {
          book = bookService.findById(bookId);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        request.setAttribute("book",book);
        RequestDispatcher dispatcher = request.getRequestDispatcher("card/create.jsp");
        try {
            request.setAttribute("studentList",studentService.findAll());
            dispatcher.forward(request,response);
        } catch (ServletException | SQLException | IOException e) {
            e.printStackTrace();
        }
    }

    private void showEditFrom(HttpServletRequest request, HttpServletResponse response) {
    }

    private void delete(HttpServletRequest request, HttpServletResponse response) {
    }



    private void sortByName(HttpServletRequest request, HttpServletResponse response) {
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        if (action == null) {
            action = "";
        }
        switch (action) {
            case "create":
                createCard(request,response);
                break;
            case "edit":
                updateCard(request,response);
                break;
            case "return":
                returnBook(request,response);
                break;
            default:
                listCards(request, response);
                break;
        }
    }

    private void returnBook(HttpServletRequest request, HttpServletResponse response) {
        RequestDispatcher dispatcher = request.getRequestDispatcher("card/list.jsp");
        int id = Integer.parseInt(request.getParameter("id"));
        try {
            Card card = cardService.findById(id);
            Book book = bookService.findById(card.getBook().getId());
            bookService.updateQuantityWhileReturned(book);
            card.setStatus(2);
            cardService.updateStatus(card);
            request.setAttribute("cardList",cardService.findAll());
            dispatcher.forward(request,response);
        } catch (SQLException | ServletException | IOException e) {
            e.printStackTrace();
        }
    }

    private void createCard(HttpServletRequest request, HttpServletResponse response) {
        RequestDispatcher dispatcher = request.getRequestDispatcher("card/list.jsp");
        int bookId = Integer.parseInt(request.getParameter("id"));
        Book book = null;
        Student student = null;
        try {
            book = bookService.findById(bookId);
            bookService.updateQuantityWhileBorrowed(book);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        int studentId = Integer.parseInt(request.getParameter("student"));
        try {
             student = studentService.findById(studentId);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        Date borrowDate = Date.valueOf(request.getParameter("borrowDate"));
        Date returnDate = Date.valueOf(request.getParameter("returnDate"));
        Card card = new Card(book,student,1,borrowDate,returnDate);
        try {
            cardService.insert(card);
//            request.setAttribute("book",book);
//            request.setAttribute("studentList",studentService.findAll());
            request.setAttribute("cardList",cardService.findAll());
            dispatcher.forward(request,response);
        } catch (SQLException | ServletException | IOException e) {
            e.printStackTrace();
        }
    }
    private void updateCard(HttpServletRequest request, HttpServletResponse response) {
    }

    private void listCards(HttpServletRequest request, HttpServletResponse response) {
        RequestDispatcher dispatcher = request.getRequestDispatcher("card/list.jsp");
        try {
            request.setAttribute("cardList",cardService.findAll());
        } catch (SQLException e) {
            e.printStackTrace();
        }
        try {
            dispatcher.forward(request,response);
        } catch (ServletException | IOException e) {
            e.printStackTrace();
        }
    }
}
