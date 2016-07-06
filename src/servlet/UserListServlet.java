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
		
		HttpSession session = request.getSession();
		
		// 押されたページ数をJSPから取得
		request.setCharacterEncoding("UTF-8");
		String next_page = request.getParameter("next_page");
		
		// 最初だけの処理
		if (next_page == null) {
			session.setAttribute("page", 0);
			
			try {				
				// ユーザの件数を取得
				GetUserCountLogic getUserCountLogic = new GetUserCountLogic();		
				int count = getUserCountLogic.execute();
				
				// 取得した全件数を保存
				session.setAttribute("count", count);
				
				// ページ数
				// 1ページに10件表示
				int all_page = count / 10;
				
				// あまった分を次へに表示するため
				if (count % 10 != 0) {
					all_page = all_page + 1;
				}
				
				// 必要ページ数をセッションで保存
				System.out.println("必要なpage = "+all_page);
				session.setAttribute("all_page", all_page);
			
			} catch (ClassNotFoundException | SQLException e) {
				e.printStackTrace();
				msg = "システムエラーが発生しました。管理者にご連絡ください";
			}
			
		} else {
		
			// 取得したリクエストパラをintにし、保存
			page = Integer.parseInt(next_page);
			session.setAttribute("page", page);
		}
		try {				

			// ボタンを押されるたびOFSETを*10
			GetUserListLogic getUserListLogic = new GetUserListLogic();
			List<Staff> staff_List = getUserListLogic.execute(page * 10);
				
	        session.setAttribute("staff_List", staff_List);	
			
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
			msg = "システムエラーが発生しました。管理者にご連絡ください";
		}
		
		request.setAttribute("msg",msg);
	
		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/admin.jsp");
		dispatcher.forward(request, response);
	}
}