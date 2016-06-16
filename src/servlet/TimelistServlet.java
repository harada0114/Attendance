package servlet;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.Time;
import model.GetListLogic;


@WebServlet("/TimelistServlet")
public class TimelistServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/attendance.jsp");
		dispatcher.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		String forwardPath = "";
		
		String random_word = request.getParameter("mail");
		request.setCharacterEncoding("UTF-8");
		
		HttpSession session = request.getSession();
		Map<String, String> account = (Map<String, String>)session.getAttribute("account");
		String mail = account.get(random_word);

		Date today = new Date();
		
		try {
			GetListLogic getListLogic = new GetListLogic();
			List<Time> timeList = getListLogic.execute(mail, today);
			
			request.setAttribute("timeList", timeList);
			
			forwardPath = "/WEB-INF/jsp/timelist.jsp";
			
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
			request.setAttribute("errorMsg_system","システムエラーが発生しました。管理者にご連絡ください");
			forwardPath = "/WEB-INF/jsp/attendance.jsp";
		}
		
		request.setAttribute("random_word",random_word);
		
		RequestDispatcher dispatcher = request.getRequestDispatcher(forwardPath);
		dispatcher.forward(request, response);
	}
}