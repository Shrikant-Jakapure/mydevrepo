package pages;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Set;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.BookDao;
@WebServlet("/subject")
public class SubjectServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private BookDao dao;
    public SubjectServlet() {
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
		Set<String> subjects = null; 
		try {
			subjects  = this.dao.getDistinctSubjects();
		} catch (Exception cause) {
			throw new ServletException(cause);
		}
		response.setContentType("text/html");
		try( PrintWriter out = response.getWriter()){
			out.println("<html>");
			out.println("<head>");
			out.println("<title>Subject Servlet</title>");
			out.println("</head>");
			out.println("<body>");
			out.println("<form action='book'>");
			for (String subject : subjects) {
				out.println("<input type='radio' name='subject' value='"+subject+"'>"+subject+"<br/>");	
			}
			out.println("<input type='submit' value='Show Cart' formaction='showcart'>");
			out.println("<input type='submit' value='Show Books' >");
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
