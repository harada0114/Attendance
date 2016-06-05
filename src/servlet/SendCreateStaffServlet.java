package servlet;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.CreateStaffLogic;
import model.Staff;


@WebServlet("/SendCreateStaffServlet")
public class SendCreateStaffServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		request.setCharacterEncoding("UTF-8");
		String mail = request.getParameter("mail");
		String pass = request.getParameter("pass");
		String name = request.getParameter("name");
		
		Staff entryStaff = new Staff(mail,pass,name);
		
		try {
			CreateStaffLogic logic = new CreateStaffLogic();
			boolean s = logic.execute(entryStaff);
			
			if (s) {
				request.setAttribute("okMsg1","登録が完了しました");
				
			} else {
				request.setAttribute("errorMsg1","システムエラーが発生しました。管理者にご連絡ください");			
			}
			
			RequestDispatcher dispatcher = request.getRequestDispatcher("/index.jsp");
			dispatcher.forward(request, response);

		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			request.setAttribute("errorMsg1","システムエラーが発生しました。管理者にご連絡ください");
			
		} catch (SQLException e) {
			e.printStackTrace();
			request.setAttribute("errorMsg1","システムエラーが発生しました。管理者にご連絡ください");
		}
	}
}
