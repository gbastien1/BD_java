package PackageBD;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class BD_Servlet extends HttpServlet {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException{
		String username = request.getParameter("username");
		String password = request.getParameter("motdepasse");
		
		//Facade TODO
		FacadeLogin fLogin = new FacadeLogin();
		Utilisateur userObject = fLogin.authentification(username,password);
		
		//Verif TODO
		if(userObject == null) { //Error here. 
			PrintWriter out = response.getWriter();
			
			out.println("<title>Login</title>"  + "<body bgcolor=FFFFFF>");
			out.println("<h2>Authentification échouée</h2>");
		}
		else { //Authentification avec succes.
			response.setContentType("text/html");
			PrintWriter out = response.getWriter();
			
			out.println("<title>Login</title>" + "<body bgcolor=FFFFFF>");
			out.println("<h2>Authentification avec succès</h2>");
			out.close();
		}
	}
}
