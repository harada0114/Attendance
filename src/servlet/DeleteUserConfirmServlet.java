package servlet;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.SearchMailLogic;
import model.Staff;

@WebServlet("/DeleteUserConfirmServlet")
public class DeleteUserConfirmServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		String msg = "";
		String error_msg = "";
		List<Staff> staff_List = new ArrayList<Staff>();
		
		request.setCharacterEncoding("UTF-8");
		// チェックボックスのメールを複数取得
		String user[] = request.getParameterValues("user");
		
		// 検索一覧からきたか判別
		String text = request.getParameter("text");
		
		if (text == null) {
			text = "";
		}
		
		request.setAttribute("text",text);
		System.out.println("text = "+text);

		System.out.println("チェックされた値 = "+Arrays.toString(user));
		
		// 空なら
		if (user == null) {
			RequestDispatcher dispatcher = request.getRequestDispatcher("/UserListServlet");
			dispatcher.forward(request, response);
			return;
		}
		
		try {
			SearchMailLogic b = new SearchMailLogic();
			staff_List = b.execute(user);
			
			if (staff_List.isEmpty()) {
				msg = "該当データはありません";
			}
			
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
			msg = "システムエラーが発生しました。管理者にご連絡ください";
		}
		
		for (int i = 0; i < staff_List.size(); i++) {
			System.out.println("チェックした値の検索結果 = "+staff_List.get(i).toString()); 
		
		}
		
		request.setAttribute("error_msg",error_msg);
		request.setAttribute("msg",msg);
		
		request.setAttribute("staff_List",staff_List);
		
		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/deleteuserconfirm.jsp");
		dispatcher.forward(request, response);
	}
}