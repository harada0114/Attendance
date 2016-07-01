package servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet("/AccountServlet")
public class AccountServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String forwardPath = "";
		
		request.setCharacterEncoding("UTF-8");
		String name = request.getParameter("name");
		String mail = request.getParameter("mail");
		String pass = request.getParameter("pass");
		
		String random_word = request.getParameter("random_word");
		request.setAttribute("random_word",random_word); 
		
		if (!(name == null)) {
			
			System.out.println("名前編集が押されました");
			forwardPath = "/WEB-INF/jsp/UpDateName.jsp";
		} else
		
		if (!(mail == null)) {
			
			System.out.println("メール編集が押されました");
			forwardPath = "/WEB-INF/jsp/UpDateMail.jsp";
		} else
		
		if (!(pass == null)) {
			
			System.out.println("パスワード編集が押されました");
			forwardPath = "/WEB-INF/jsp/UpDatePass.jsp";
		} else {
			
			forwardPath = "/WEB-INF/jsp/my_account.jsp";
			
		}
		
		RequestDispatcher dispatcher = request.getRequestDispatcher(forwardPath);
		dispatcher.forward(request, response);
	
	}

}
