package servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.Login;
import model.LoginLogic;

@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		RequestDispatcher dispatcher = request.getRequestDispatcher("/index.jsp");
		dispatcher.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		request.setCharacterEncoding("UTF-8");
		String mail = request.getParameter("mail");
		String pass = request.getParameter("pass");
		
		// 入力パラメータチェック
		// メールアドレスが空白なら
		if (mail.equals("")) {
			
			request.setAttribute("errorMsg1","メールアドレスが入力されていません");
		}
			// パスワードが空白なら
		if (pass.equals("")) {
			
			request.setAttribute("errorMsg2","パスワードが入力されていません");
		
		} else { // エラー。無駄な処理はしない。修正
		
			// ログイン処理の実行
			Login login = new Login(mail,pass);
			LoginLogic bo = new LoginLogic();
			boolean result = bo.execute(login);
			
			// ログイン失敗時
			if (!result) { // 一致するユーザがない場合
				
				request.setAttribute("errorMsg3","一致するアカウントが存在しません");
			}
			
			// ログイン成功時
			else { // 一致するユーザがある場合
													
				// セッションスコープにメールアドレスを保存。他のところでも使うので。
				HttpSession session = request.getSession();
				session.setAttribute("mail",mail);
						
				RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/attendance.jsp");
				dispatcher.forward(request, response);
						
				return;
			}		
		}
	
		// ※リダイレクトだとリクエストとレスポンスが2往復してしまうため、
		// リダイレクトからフォワードに変更。
		RequestDispatcher dispatcher = request.getRequestDispatcher("/index.jsp");
		dispatcher.forward(request, response);	
	}
}