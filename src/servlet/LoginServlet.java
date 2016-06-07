package servlet;

import java.io.IOException;
import java.util.*;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.Login;
import model.LoginLogic;
import model.Staff;

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
		
		} // エラー。無駄な処理はしない。修正
		
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
				
				String random_word = UUID.randomUUID().toString();
				
				// インタフェース型を利用するほうが幅広く使える
				Map<String, String> map_mail = new HashMap<String, String>(); 
				map_mail.put(random_word, mail);
			    //              key        値
				String login_mail = map_mail.get(random_word);
				
				request.setAttribute("login_mail",login_mail);
				
				RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/attendance.jsp");
				dispatcher.forward(request, response);
						
				return;
			}		
		RequestDispatcher dispatcher = request.getRequestDispatcher("/index.jsp");
		dispatcher.forward(request, response);	
	}
}