package pages;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.UserDao;
import pojo.User;
@WebServlet("/validate")
public class ValidationServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private UserDao dao;
	public ValidationServlet() {
	}
	 @Override
	public void init(ServletConfig config) throws ServletException {
		try {
			super.init(config);
			this.dao = new UserDao();
		}catch (Exception cause) {
			throw new ServletException( cause );
		}
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String email  = request.getParameter("email");
		String password  = request.getParameter("password");
		
		User user = null;
		try {
			user = dao.validate(email, password);
		} catch (Exception cause) {
			throw new ServletException( cause );
		}
		response.setContentType("text/html");
		try( PrintWriter out = response.getWriter()){
			out.println("<html>");
			out.println("<head>");
			out.println("<title>Validation Servlet</title>");
			out.println("</head>");
			out.println("<body>");
			if( user != null ) {
				HttpSession session = request.getSession();
				List<Integer> cart = new ArrayList<Integer>();
				session.setAttribute("BookCart", cart);
				
				String location = response.encodeRedirectURL("subject");
				response.sendRedirect(location);
			}
			else {
				out.println("Invalid username  or password");
				out.println("<a href='Index.html'>Retry</a>");	
			}
			out.println("</body>");
			out.println("</html>");
		}
	}
	@Override
	public void destroy(){
		try {
			this.dao.close();
		} catch (IOException cause) {
			throw new RuntimeException(cause);
		}
	}
}
