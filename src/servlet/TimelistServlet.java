package servlet;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.Time;
import model.GetListLogic;


@WebServlet("/TimelistServlet")
public class TimelistServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		String forwardPath = "";
		
		Date today = new Date();
		
		String mail = request.getParameter("mail");	
		request.setCharacterEncoding("UTF-8");
		
		try {
			
			GetListLogic getListLogic = new GetListLogic();
			List<Time> timeList = getListLogic.execute(mail,today);
			
			request.setAttribute("timeList", timeList);
			
			forwardPath = "/WEB-INF/jsp/timelist.jsp";
			
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
			request.setAttribute("errorMsg1","システムエラーが発生しました。原田にご連絡ください");
			forwardPath = "/WEB-INF/jsp/attendance.jsp";
		}

		RequestDispatcher dispatcher = request.getRequestDispatcher(forwardPath);
		dispatcher.forward(request, response);
	}
}
