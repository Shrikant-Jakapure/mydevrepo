package pages;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
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
@WebServlet("/showcart")
public class ShowCartServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private BookDao dao;
    public ShowCartServlet() {
        super();
    }
    @Override
    public void init(ServletConfig config) throws ServletException {
    	try {
			super.init(config);
			this.dao = new BookDao();
		} catch (Exception cause) {
			throw new ServletException(cause);
		}
    }
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		@SuppressWarnings("unchecked")
		List<Integer> cart =  (List<Integer>) session.getAttribute("BookCart");
		List<Book> books = new ArrayList<Book>();
		try {
			for (Integer bookId : cart) {
				Book book = this.dao.getBook( bookId );
				books.add(book);
			}
		} catch (Exception cause) {
			throw new ServletException(cause);
		}
		
		response.setContentType("text/html");
		try( PrintWriter out = response.getWriter()){
			out.println("<html>");
			out.println("<head>");
			out.println("<title>Show Cart Servlet</title>");
			out.println("</head>");
			out.println("<body>");
			out.println("<form action='logout'>");
			for (Book book : books) {
				out.println("<b>"+book.toString()+"</b><br>");
			}
			out.println("<input type='submit' value='Logout' >");
			out.println("</form>");
			out.println("</body>");
			out.println("</html>");
		}
	}
	@Override
	public void destroy() {
		try {
			this.dao.close();
		} catch (IOException cause) {
			throw new RuntimeException(cause);
		}
	}
}
