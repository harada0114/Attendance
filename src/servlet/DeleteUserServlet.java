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

import model.DeleteUserListLogic;
import model.Staff;

@WebServlet("/DeleteUserServlet")
public class DeleteUserServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		HttpSession session = request.getSession();
		int[] delete_user = (int[]) session.getAttribute("delete_user");
		List<Staff> staff_List = (List<Staff>) session.getAttribute("staff_List");
			
		String msg = "";
		
		try {	
			// 削除できたか
			for (int i = 0; i < delete_user.length; i++) {
				DeleteUserListLogic getUserListLogic = new DeleteUserListLogic();
				
				System.out.println(staff_List.get(delete_user[i]).getMail());
				
				boolean can_delete = getUserListLogic.execute(staff_List.get(delete_user[i]).getMail());
				
				if (!can_delete) {
					msg = "システムエラーが発生しました。一部削除されていない可能性があります";
				} else {
					msg = "削除しました";
				}
			}
	
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
			msg = "システムエラーが発生しました。管理者にご連絡ください";
		}
				
		request.setAttribute("msg",msg);
		
		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/admin.jsp");
		dispatcher.forward(request, response);
	}
}