package servlet;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Map;
import java.util.HashMap;
import java.util.UUID;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.CheckMail;
import model.CheckPass;
import model.Login;
import model.LoginLogic;
import model.Staff;
import model.CheckMail.ErrorMsgMail;
import model.CheckPass.ErrorMsgPass;

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
		
		String forwardPath = "";
		
		// メールチェック	
		CheckMail m = new CheckMail();
		ErrorMsgMail check_mail = m.checkMail(mail);
	
		switch (check_mail) {
			case OK:
				break;
			default:
				request.setAttribute("errorMsg1","ログインに失敗しました");
				RequestDispatcher dispatcher = request.getRequestDispatcher("/index.jsp");
				dispatcher.forward(request, response);
				return;
		}
		
		// パスワードチェック
		CheckPass p = new CheckPass();
		ErrorMsgPass check_pass = p.checkPass(pass);
			
		switch (check_pass) {
			case OK:
				break;
			default:	
				request.setAttribute("errorMsg1","ログインに失敗しました");
				RequestDispatcher dispatcher = request.getRequestDispatcher("/index.jsp");
				dispatcher.forward(request, response);	
				return;
		}							
			
		// ログイン処理の実行
		try {
			Login login = new Login(mail,pass);
			LoginLogic bo = new LoginLogic();
			Staff staff = bo.execute(login);
						
			if (staff == null) {   // 一致するユーザがない
				request.setAttribute("errorMsg1","ログインに失敗しました");
				forwardPath = "/index.jsp";
				
			} else {   // 一致するユーザがある	
				String random_word = UUID.randomUUID().toString();
				
				Map<String, String> account = new HashMap<String, String>();
				account.put(random_word, staff.getMail());
				
				request.setAttribute("random_word",random_word);
				
				// Map型accountインスタンスを別のサーブレットで使うためにセッションスコープで保存。
				HttpSession session = request.getSession();
				session.setAttribute("account", account);
				session.setAttribute("staff.getName()",staff.getName());
							
				forwardPath = "/WEB-INF/jsp/attendance.jsp";
			}
						
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
			request.setAttribute("errorMsg_system","システムエラーが発生しました。管理者にご連絡ください");
			forwardPath = "/index.jsp";
		}
		
		RequestDispatcher dispatcher = request.getRequestDispatcher(forwardPath);
		dispatcher.forward(request, response);
	}
}