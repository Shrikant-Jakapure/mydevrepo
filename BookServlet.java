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

import dao.BookDao;
import pojo.Book;
@WebServlet("/book")
public class BookServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private BookDao dao;
    public BookServlet() {
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
		List<Book> books = null;
		try {
			String subject = request.getParameter("subject");
			books = this.dao.getBooks( subject );
		} catch (Exception cause) {
			throw new ServletException( cause);
		}
		
		response.setContentType("text/html");
		try( PrintWriter out = response.getWriter()){
			out.println("<html>");
			out.println("<head>");
			out.println("<title>Book Servlet</title>");
			out.println("</head>");
			out.println("<body>");
			out.println("<form action='cart'>");
			
			for (Book book : books) {
				out.println("<input type='checkbox' name='books' value='"+book.getBookId()+"'>"+book.toString()+"<br/>");
			}
			
			out.println("<input type='submit' value='Add To Cart' >");
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
