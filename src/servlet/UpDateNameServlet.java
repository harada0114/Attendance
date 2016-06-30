package servlet;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.CheckName;
import model.Staff;
import model.CheckName.ErrorMsgName;
import model.UpDateAccountLogic;

@WebServlet("/UpDateNameServlet")
public class UpDateNameServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		request.setCharacterEncoding("UTF-8");
		String random_word = request.getParameter("random_word");
		request.setAttribute("random_word",random_word);
		
		HttpSession session = request.getSession();
		// 現在の情報を持ったStaffインスタンスを取得
		Staff bifoa_staff = (Staff) session.getAttribute("staff");
		
		// 変更された値を取得
		String name = request.getParameter("name");
		
		// カラム名をセット
		String column = "name";
		
		String errorMsg = "";
		
		try {
			// 名前チェック
			CheckName c = new CheckName();
			ErrorMsgName check_name = c.checkName(name);
						
			switch (check_name) {
				case MAX_WORD:
					errorMsg = "名前は20文字以内で入力してください";
					break;
				case SPACE:
					errorMsg = "スペースは無効です";
					break;
				case BLANK:
					errorMsg = "名前が入力されていません";
					break;
				default:
					UpDateAccountLogic n = new UpDateAccountLogic();
					Staff after_staff = n.execute(bifoa_staff,column, name);
			
					// DBより取得した値でstaffを上書き
					session.setAttribute("staff", after_staff);
			}	
					
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
			errorMsg = "システムエラーが発生しました。管理者にご連絡ください";
		}
					
		if (!errorMsg.equals("")) {
						
			request.setAttribute("errorMsg", errorMsg);
			
			// エラーメッセージを編集画面に表示させる
			RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/UpDateName.jsp");
			dispatcher.forward(request, response);
			return;
		}
					
		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/my_account.jsp");
		dispatcher.forward(request, response);			
	}
}