package servlet;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Date;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.PostAdmissionLogic;

@WebServlet("/AdmissionServlet")
public class AdmissionServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/attendance.jsp");
		dispatcher.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		String forwardPath = "";

		String random_word = request.getParameter("mail");
		request.setCharacterEncoding("UTF-8");
		
		HttpSession session = request.getSession();
		// キーからメールアドレスを取得
		Map<String, String> account = (Map<String, String>)session.getAttribute("account");
		String mail = account.get(random_word);
		
		// フォーマットはモデルにさせる
		Date today = new Date();
			
		try {
			PostAdmissionLogic postAdmissionLogic = new PostAdmissionLogic();
			boolean canAdmission = postAdmissionLogic.isExecute(mail,today);
			
			// 出社できれば画面遷移します
			if (canAdmission) {
				forwardPath = "/WEB-INF/jsp/admissionOK.jsp";
				request.setAttribute("random_word",random_word);
			
			// できなければエラーメッセージ出力
			} else {
				request.setAttribute("errorMsg1","本日はすでに出社済みです");
				forwardPath = "/WEB-INF/jsp/attendance.jsp";
			}	
		
		// 例外処理
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
			request.setAttribute("errorMsg1","システムエラーが発生しました。管理者にご連絡ください");
			forwardPath = "/WEB-INF/jsp/attendance.jsp";
		}
		
		// 二度目以降もmailに値が入るように保存
		request.setAttribute("random_word",random_word);
		
		RequestDispatcher dispatcher = request.getRequestDispatcher(forwardPath);
		dispatcher.forward(request, response);
	}
}