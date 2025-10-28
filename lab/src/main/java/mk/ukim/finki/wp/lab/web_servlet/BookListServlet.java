package mk.ukim.finki.wp.lab.web_servlet;

import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import mk.ukim.finki.wp.lab.exceptions.EmptyListException;
import mk.ukim.finki.wp.lab.model.Book;
import mk.ukim.finki.wp.lab.service.IBookReservationService;
import mk.ukim.finki.wp.lab.service.IBookService;
import org.springframework.web.context.support.WebApplicationContextUtils;
import org.thymeleaf.context.WebContext;
import org.thymeleaf.spring6.SpringTemplateEngine;
import org.thymeleaf.web.IWebExchange;
import org.thymeleaf.web.servlet.JakartaServletWebApplication;

import java.io.IOException;
import java.security.interfaces.DSAPublicKey;
import java.util.List;


@WebServlet(name = "BookListServlet", urlPatterns = "/books")
public class BookListServlet extends HttpServlet {
    private SpringTemplateEngine springTemplateEngine;
    private IBookService bookService;
    private IBookReservationService bookReservationService;


    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        var ctx = WebApplicationContextUtils.getRequiredWebApplicationContext(getServletContext());
        this.springTemplateEngine = ctx.getBean(SpringTemplateEngine.class);
        this.bookService    = ctx.getBean(IBookService.class);
    }


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        var app = JakartaServletWebApplication.buildApplication(getServletContext());
        IWebExchange exchange = app.buildExchange(req, resp);
        var ctx = new WebContext(exchange);

            ctx.setVariable("books", bookService.listAll());


        resp.setContentType("text/html;charset=UTF-8");
        springTemplateEngine.process("index", ctx, resp.getWriter());
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String text = req.getParameter("title");
        Double avgRtng = Double.parseDouble(req.getParameter("avgRating"));
        var app = JakartaServletWebApplication.buildApplication(getServletContext());
        IWebExchange exchange = app.buildExchange(req, resp);
        var ctx = new WebContext(exchange);

        List<Book> books = bookService.searchBooks(text, avgRtng);

            ctx.setVariable("searchedBooks", books);
            ctx.setVariable("books", bookService.listAll());

        resp.setContentType("text/html;charset=UTF-8");
        springTemplateEngine.process("index", ctx, resp.getWriter());
    }
}
