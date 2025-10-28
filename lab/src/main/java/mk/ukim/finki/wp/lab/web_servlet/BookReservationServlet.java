package mk.ukim.finki.wp.lab.web_servlet;

import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import mk.ukim.finki.wp.lab.repository.IBookReservationRepository;
import mk.ukim.finki.wp.lab.service.IBookReservationService;
import org.springframework.web.context.support.WebApplicationContextUtils;
import org.thymeleaf.context.WebContext;
import org.thymeleaf.spring6.SpringTemplateEngine;
import org.thymeleaf.web.IWebExchange;
import org.thymeleaf.web.servlet.JakartaServletWebApplication;

import java.io.IOException;

@WebServlet(name = "BookReservationServlet", urlPatterns = "/bookReservation")
public class BookReservationServlet extends HttpServlet {
private SpringTemplateEngine springTemplateEngine;
private IBookReservationService bookReservationService;

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        var ctx = WebApplicationContextUtils.getRequiredWebApplicationContext(getServletContext());
        this.springTemplateEngine = ctx.getBean(SpringTemplateEngine.class);
        this.bookReservationService = ctx.getBean(IBookReservationService.class);
    }


    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String bookTitle     = req.getParameter("bookTitle");
        String readerName    = req.getParameter("readerName");
        String readerAddress = req.getParameter("readerAddress");
        int numCopies        = Integer.parseInt(req.getParameter("numCopies"));

        // client IP (prefer header if behind proxy, else remote addr)
        String ip = req.getHeader("X-Forwarded-For");
        if (ip == null || ip.isBlank()) ip = req.getRemoteAddr();

        bookReservationService.placeReservation(bookTitle, readerName, readerAddress, numCopies);

        var app = JakartaServletWebApplication.buildApplication(getServletContext());
        IWebExchange exchange = app.buildExchange(req, resp);
        var ctx = new WebContext(exchange);
        ctx.setVariable("readerName", readerName);
        ctx.setVariable("clientIp", ip);
        ctx.setVariable("bookTitle", bookTitle);
        ctx.setVariable("numCopies", numCopies);

        resp.setContentType("text/html;charset=UTF-8");
        springTemplateEngine.process("reservationConfirmation", ctx, resp.getWriter());
    }
}

