package servlet;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.AdminLoginLogic;
import model.CheckPass;
import model.CheckPass.ErrorMsgPass;

@WebServlet("/AdminLoginServlet")
public class AdminLoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/adminLogin.jsp");
		dispatcher.forward(request, response);
	
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		request.setCharacterEncoding("UTF-8");
		String pass = request.getParameter("pass");
		
		String msg = "";
		String forwardPath = "";
		
		// 管理者パスワードチェック
		CheckPass p = new CheckPass();
		ErrorMsgPass check_pass = p.checkPass(pass);
					
		switch (check_pass) {
			case OK:
				break;
			default:
				msg = "ログインに失敗しました";
				forwardPath = "/WEB-INF/jsp/adminLogin.jsp";
		}
		
		if (msg.equals("")) {
			// 管理者パスをDBでチェック
			try {
				AdminLoginLogic bo = new AdminLoginLogic();
				boolean ispass = bo.isexecute(pass);
			
				// パスが一致したなら
				if (ispass) {					
					forwardPath = "/UserListServlet";
				} else {
					msg = "パスワードが違います";
					forwardPath = "/WEB-INF/jsp/adminLogin.jsp";
				}
				
			} catch (ClassNotFoundException | SQLException e) {
				e.printStackTrace();
				msg = "システムエラーが発生しました。管理者にご連絡ください";
				forwardPath = "/WEB-INF/jsp/adminLogin.jsp";
			}
		}
		request.setAttribute("msg", msg);
			
		RequestDispatcher dispatcher = request.getRequestDispatcher(forwardPath);
		dispatcher.forward(request, response);
	}
}