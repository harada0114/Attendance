package servlet;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Date;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.TimeDAO.MsgLeaving;
import model.PostLeavingLogic;

@WebServlet("/LeavingServlet")
public class LeavingServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String random_word = request.getParameter("random_word");
		request.setCharacterEncoding("UTF-8");
		
		HttpSession session = request.getSession();
		Map<String, String> account = (Map<String, String>)session.getAttribute("account");
		String mail = account.get(random_word);

		Date today = new Date();
		
		String forwardPath = "";
		String errorMsg = "";
		
		try {
			PostLeavingLogic postLeavingLogic = new PostLeavingLogic();
			MsgLeaving result_leaving = postLeavingLogic.execute(mail, today);

			switch (result_leaving) {
				case ALREADY:
					errorMsg = "本日はすでに退社済みです";
					forwardPath = "/WEB-INF/jsp/attendance.jsp";
					break;
	
				case NOT_ADMISSION:
					errorMsg = "本日の出社の確認ができませんでした。出社後、退社ボタンを押してください";
					forwardPath = "/WEB-INF/jsp/attendance.jsp";
					break;
					
				case SYSTEM_ERROR:
					errorMsg = "システムエラーが発生しました。管理者にご連絡ください";
					forwardPath = "/WEB-INF/jsp/attendance.jsp";
					break;
					
				default:
				// 退社OK。画面遷移します。
					forwardPath = "/WEB-INF/jsp/leavingOK.jsp";
			}
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
			errorMsg = "システムエラーが発生しました。管理者にご連絡ください";
			forwardPath = "/WEB-INF/jsp/attendance.jsp";
		}
		
		request.setAttribute("random_word",random_word);
		request.setAttribute("errorMsg",errorMsg);
		
		RequestDispatcher dispatcher = request.getRequestDispatcher(forwardPath);
		dispatcher.forward(request, response);	
	}
}