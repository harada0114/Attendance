package servlet;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Date;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.PostTimeLogic;

@WebServlet("/LeavingServlet")
public class LeavingServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String forwardPath = "";
		
		Date today = new Date();
	
		String mail = request.getParameter("mail");	
		request.setCharacterEncoding("UTF-8");
		
		try {
			PostTimeLogic postTimeLogic = new PostTimeLogic();
			int error = postTimeLogic.execute_leaving(mail,today);	
			
		
			switch (error) {
				case 1:
					request.setAttribute("errorMsg1","本日はすでに退社済みです");
					forwardPath = "/WEB-INF/jsp/attendance.jsp";
	
					break;
	
				case 2:
					request.setAttribute("errorMsg1","本日の出社の確認ができませんでした。出社後、退社ボタンを押してください");
					forwardPath = "/WEB-INF/jsp/attendance.jsp";
	
					break;

				case 3:
				// 退社OK
					forwardPath = "/WEB-INF/jsp/leavingOK.jsp";
			}
			
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
			request.setAttribute("errorMsg1","システムエラーが発生しました。管理者にご連絡ください");
			forwardPath = "/WEB-INF/jsp/attendance.jsp";
		}

		RequestDispatcher dispatcher = request.getRequestDispatcher(forwardPath);
		dispatcher.forward(request, response);	
	}
}
