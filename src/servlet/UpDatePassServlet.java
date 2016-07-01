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

import model.CheckPass;
import model.CheckPass.ErrorMsgPass;
import model.Staff;
import model.UpDateAccountLogic;

@WebServlet("/UpDatePassServlet")
public class UpDatePassServlet extends HttpServlet {
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
		String new_pass = request.getParameter("new_pass");
		String re_new_pass = request.getParameter("re_new_pass");
		String before_pass = request.getParameter("before_pass");
		
		// カラム名をセット
		String column = "pass";
		
		String errorMsg ="";
		String errorMsg_new_pass = "";
		String errorMsg_re_new_pass = "";
		String errorMsg_before_pass = "";
		
		try {
			// パスチェック
			CheckPass p = new CheckPass();
			ErrorMsgPass check_new_pass = p.checkPass(new_pass);
			ErrorMsgPass check_re_new_pass = p.checkPass(re_new_pass);
			ErrorMsgPass check_before_pass = p.checkPass(before_pass);
						
			switch (check_new_pass) {
				case MAX_WORD:
					errorMsg_new_pass = "パスワードは半角英数6文字で入力してください";
					break;
				case BLANK:
					errorMsg_new_pass = "新しいパスワードが入力されていません";
					break;
				default:	
					switch (check_re_new_pass) {
						case MAX_WORD:
							errorMsg_re_new_pass = "パスワードは半角英数6文字で入力してください";
							break;
						case BLANK:
							errorMsg_re_new_pass = "新しいパスワードを再度入力してください";
							break;
						default:
							switch (check_before_pass) {
								case MAX_WORD:
									errorMsg_before_pass = "パスワードは半角英数6文字で入力してください";
									break;
								case BLANK:
									errorMsg_before_pass = "現在のパスワードが入力されていません";
									break;
								default:
									
									// 新しいパスワードと再入力したパスは等しいか
									if (!new_pass.equals(re_new_pass)) {
										errorMsg_re_new_pass = "新しいパスワードと再入力が一致しません";
									}
									
									String pass = p.SearchPass(bifoa_staff);
									
									if (!before_pass.equals(pass)) {
										errorMsg_before_pass = "パスワードが一致しません";
										
									} else {
										UpDateAccountLogic n = new UpDateAccountLogic();
										Staff after_staff = n.execute(bifoa_staff, column, new_pass);
			
										// DBより取得した値でstaffを上書き
										session.setAttribute("staff", after_staff);
									}
							}
					}
			}
					
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
			errorMsg = "システムエラーが発生しました。管理者にご連絡ください";
		}
					
		if (!errorMsg.equals("") || !errorMsg_new_pass.equals("") || !errorMsg_re_new_pass.equals("") || !errorMsg_before_pass.equals("")) {
						
			request.setAttribute("errorMsg", errorMsg);
			request.setAttribute("errorMsg_new_pass", errorMsg_new_pass);
			request.setAttribute("errorMsg_re_new_pass", errorMsg_re_new_pass);
			request.setAttribute("errorMsg_before_pass", errorMsg_before_pass);
			
			// エラーメッセージを編集画面に表示させる
			RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/UpDatePass.jsp");
			dispatcher.forward(request, response);
			return;
		}
					
		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/my_account.jsp");
		dispatcher.forward(request, response);			
	}

	}


