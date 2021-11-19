package controller;

import model.Card;
import service.book.BookService;
import service.card.CardService;
import service.student.StudentService;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet(name = "BookServlet", value = "/books")
public class BookServlet extends HttpServlet {
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
            case "edit":
                showEditFrom(request,response);
                break;
            case "delete":
                delete(request,response);
                break;
            case "view":
                showBookView(request,response);
                break;
            case "sort":
                sortByName(request,response);
                break;
            default:
                listBooks(request, response);
                break;
        }
    }

    private void showCreateForm(HttpServletRequest request, HttpServletResponse response) {
    }

    private void showEditFrom(HttpServletRequest request, HttpServletResponse response) {
    }

    private void delete(HttpServletRequest request, HttpServletResponse response) {
    }

    private void showBookView(HttpServletRequest request, HttpServletResponse response) {
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
                createBook(request,response);
                break;
            case "edit":
                updateBook(request,response);
                break;
            default:
                listBooks(request, response);
                break;
        }
    }

    private void createBook(HttpServletRequest request, HttpServletResponse response) {
    }

    private void updateBook(HttpServletRequest request, HttpServletResponse response) {
    }

    private void listBooks(HttpServletRequest request, HttpServletResponse response) {
        RequestDispatcher dispatcher = request.getRequestDispatcher("book/list.jsp");
        try {
            request.setAttribute("bookList",bookService.findAll());
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
