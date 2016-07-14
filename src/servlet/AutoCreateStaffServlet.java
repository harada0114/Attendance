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

import model.AutoCreateStaff;
import model.CreateStaffLogic;
import model.Staff;

@WebServlet("/AutoCreateStaffServlet")
public class AutoCreateStaffServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		String msg = "";
		String error_msg = "";
		
		// ランダムアカウント作成 件数渡す
		AutoCreateStaff a = new AutoCreateStaff();
		List<Staff> entry_Staff = a.autoCreateStaff(10);
		
		try {
			for (int i = 0; i < entry_Staff.size(); i++) {
				// 登録する
				CreateStaffLogic logic = new CreateStaffLogic();
				boolean can_entry = logic.execute(entry_Staff.get(i));
			
				if (can_entry) {
					msg = "登録が完了しました";
					
				} else {
					msg = "登録に失敗しました。管理者にご連絡ください";			
				}
			} // for終
		
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
			error_msg = "システムエラーが発生しました。管理者にご連絡ください";
		}
		
		request.setAttribute("error_msg",error_msg);
		request.setAttribute("msg",msg);
	
		RequestDispatcher dispatcher = request.getRequestDispatcher("/UserListServlet");
		dispatcher.forward(request, response);
		
	}
}
