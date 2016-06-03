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

@WebServlet("/AdmissionServlet")
public class AdmissionServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		String forwardPath = "";
		
		// ボタンを押してから出勤日時を生成、取得。
		Date today = new Date();
		
		String mail = request.getParameter("mail");	
		request.setCharacterEncoding("UTF-8");
	
		try {
			
			PostTimeLogic postTimeLogic = new PostTimeLogic();
			boolean admission_ok = postTimeLogic.execute_admission(mail,today);
			
			if (admission_ok) {
				forwardPath = "/WEB-INF/jsp/admissionOK.jsp";
			} else {
				request.setAttribute("errorMsg1","本日はすでに出社済みです");						
				forwardPath = "/WEB-INF/jsp/attendance.jsp";
			}	
			
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			request.setAttribute("errorMsg1","システムエラーが発生しました。原田にご連絡ください");

			forwardPath = "/WEB-INF/jsp/attendance.jsp";

		} catch (SQLException e) {
			e.printStackTrace();
			request.setAttribute("errorMsg1","システムエラーが発生しました。原田にご連絡ください");

			forwardPath = "/WEB-INF/jsp/attendance.jsp";
		}
		
		RequestDispatcher dispatcher = request.getRequestDispatcher(forwardPath);
		dispatcher.forward(request, response);
	}
}