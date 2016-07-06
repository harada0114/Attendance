package servlet;

import java.io.IOException;
import java.util.Arrays;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/DeleteUserConfirmServlet")
public class DeleteUserConfirmServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		request.setCharacterEncoding("UTF-8");
		
		// チェックボックスの値を複数取得
		String user[] = request.getParameterValues("user");

		System.out.println(Arrays.toString(user));
		
		if (user == null) {
			System.out.println(user);
			RequestDispatcher dispatcher = request.getRequestDispatcher("UserListServlet");
			dispatcher.forward(request, response);
			return;
		}
		
		// int配列に変換
		int[] delete_user = new int[user.length];
		
		for (int i = 0; i < user.length; i++) {
			delete_user[i] = Integer.parseInt(user[i]);
		}

		HttpSession session = request.getSession();
		session.setAttribute("delete_user",delete_user);
		
		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/deleteuserconfirm.jsp");
		dispatcher.forward(request, response);
	}
}
