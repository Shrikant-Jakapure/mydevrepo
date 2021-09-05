package pages;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.UserDao;
import pojo.User;
@WebServlet("/register")
public class RegistrationServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private UserDao dao;
	public void init(ServletConfig config) throws ServletException {
		try {
			super.init(config);
			this.dao = new UserDao( );
		}catch (Exception cause) {
			throw new ServletException( cause );
		}
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		User user = new User( );
		user.setFullName( request.getParameter("fullname") );
		user.setEmail( request.getParameter("email"));
		user.setPassword( request.getParameter("password"));
		user.setPhoneNumber( request.getParameter("phonenumber"));
		user.setCountry( request.getParameter("country") );
		try {
			dao.insert( user );
		} catch (Exception cause) {
			throw new ServletException(cause);
		}
		response.setContentType("text/html");
		try( PrintWriter out = response.getWriter()){
			out.println("<html>");
			out.println("<head>");
			out.println("<title>Registration Servlet</title>");
			out.println("</head>");
			out.println("<body>");
			out.println("<a href='Index.html'>Click here</a>to login.");	
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
