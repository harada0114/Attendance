package servlet;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.DeleteUserListLogic;

@WebServlet("/DeleteUserServlet")
public class DeleteUserServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		request.setCharacterEncoding("UTF-8");
		String delete[] = request.getParameterValues("delete[]");

		String error_msg = "";
		String msg = "";

		try {	
			// 削除できたか
			for (int i = 0; i < delete.length; i++) {
				DeleteUserListLogic getUserListLogic = new DeleteUserListLogic();
				
				boolean can_delete = getUserListLogic.execute(delete[i]);
				
				if (!can_delete) {
					error_msg = "システムエラーが発生しました。一部削除されていない可能性があります";
				} else {
					msg = "削除しました";
				}
			}
	
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
			error_msg = "システムエラーが発生しました。管理者にご連絡ください";
		}
	
		request.setAttribute("error_msg",error_msg);
		request.setAttribute("msg",msg);
		
		// 検索一覧から来るとフォワード先を変える
		String text = request.getParameter("text");
		
		if (!text.equals("")) {
			
			request.setAttribute("text", text);
			
			RequestDispatcher dispatcher = request.getRequestDispatcher("/SearchUserServlet");
			dispatcher.forward(request, response);
		
		} else {
		
			RequestDispatcher dispatcher = request.getRequestDispatcher("/UserListServlet");
			dispatcher.forward(request, response);
		}
	}
}