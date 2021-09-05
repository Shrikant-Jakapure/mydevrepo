package pages;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.Set;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.BookDao;
import pojo.Book;
@WebServlet("/cart")
public class CartServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
    public CartServlet() {
        super();
    }
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		List<Integer> cart =  (List<Integer>) session.getAttribute("BookCart");
		
		String[] selectedBookIds =  request.getParameterValues("books");
		for (String selectedBookId : selectedBookIds) {
			int bookId = Integer.parseInt(selectedBookId);
			cart.add(bookId);
		}
		
		String location = response.encodeRedirectURL("subject");
		response.sendRedirect(location);
	}
}
