package webmy;


import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


@WebServlet("/SPALoginServlet")
public class SPALoginServlet extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
	
    private static final String USERID = "userid";
    
    
    
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		/*System.out.println("LoginServlet --> dopost");*/
		/*request.setAttribute("asd", "sesioon");;*/
	  HttpSession session = request.getSession();
	 
	  if(request.getParameter("getcourseid") != null){
		  System.out.println("the session course id = "+session.getAttribute("courseid"));
		  response.getWriter().print(session.getAttribute("courseid"));
	  }else{
	  if(request.getParameter("courseid") != null){
		  System.out.println("the id course id = "+request.getParameter("courseid"));
		  session.setAttribute("courseid", request.getParameter("courseid"));
		  response.getWriter().print(session.getAttribute("courseid"));
		}else{
	  if(request.getParameter("logout") != null){
			System.out.println("session in out"+session.getAttribute("id"));
			 request.getSession().invalidate();
			 response.getWriter().print("session is invaled now");
		}else{
		if(session.getAttribute("id") != null){
			response.getWriter().print(session.getAttribute("id"));
		}else{
		String userid = request.getParameter(USERID);
	    session.setAttribute("id", userid);
	     System.out.println(session.getAttribute("id"));
		
		if(session.getAttribute("id") != null){
				response.getWriter().print(session.getAttribute("id"));
		
		} else {
			response.getWriter().print("failed");
		}
	}
	}
	}
	}}}
