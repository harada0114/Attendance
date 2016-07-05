package servlet;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.GetUserCountLogic;
import model.GetUserListLogic;
import model.Staff;

@WebServlet("/UserListServlet")
public class UserListServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			
		String msg = "";
		
		// 変換用
		int page = 0;
		
		// 押されたページ数をJSPから取得
		request.setCharacterEncoding("UTF-8");
		String next_page = request.getParameter("next_page");
		
		// 最初だけの処理
		if (next_page == null) {
			request.setAttribute("page", 0);
			
			try {				
				// 必要なページ数を取得
				GetUserCountLogic getUserCountLogic = new GetUserCountLogic();		
				int all_page = getUserCountLogic.execute();
 			
				// 必要ページ数をセッションで保存
				System.out.println("必要なpage = "+all_page);
				HttpSession session = request.getSession();
				session.setAttribute("all_page", all_page);
			
			} catch (ClassNotFoundException | SQLException e) {
				e.printStackTrace();
				msg = "システムエラーが発生しました。管理者にご連絡ください";
			}
			
		} else {
		
			// 取得したリクエストパラをintにし、保存
			page = Integer.parseInt(next_page);
			request.setAttribute("page", page);
		}
		try {				

			// ボタンを押されるたびOFSETを*10
			GetUserListLogic getUserListLogic = new GetUserListLogic();
			List<Staff> staff_List = getUserListLogic.execute(page * 10);
				
	        request.setAttribute("staff_List", staff_List);	
			
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
			msg = "システムエラーが発生しました。管理者にご連絡ください";
		}
		
		request.setAttribute("msg",msg);
	
		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/admin.jsp");
		dispatcher.forward(request, response);
	}
}