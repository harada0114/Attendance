// 新規登録サーブレット

package servlet;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.CheckMail;
import model.CheckMail.ErrorMsgMail;
import model.CheckName;
import model.CheckName.ErrorMsgName;
import model.CheckPass;
import model.CheckPass.ErrorMsgPass;
import model.Staff;


@WebServlet("/CreateStaffServlet")
public class CreateStaffServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/entry.jsp");
		dispatcher.forward(request, response);
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		request.setCharacterEncoding("UTF-8");
		String mail = request.getParameter("mail");
		String pass = request.getParameter("pass");
		String name = request.getParameter("name");
		
		String errorMsg_mail = "";
		String errorMsg_pass = "";
		String errorMsg_name = "";
		
		// メールチェック		
		CheckMail m = new CheckMail();
		ErrorMsgMail check_mail = m.checkMail(mail);
		
		switch (check_mail) {
			case MAX_WORD:
				errorMsg_mail = "メールアドレスは半角英数40文字以内で入力してください";
				break;	
			case NG_PATTERN:
				errorMsg_mail = "メールアドレスの形式が正しくありません";
				break;
			case BLANK:
				errorMsg_mail = "メールアドレスが入力されていません";
				break;
			default:
		}
			
		// 重複チェック
		try {
			boolean check_duplicate_mail = m.duplicate(mail) ;

			if (check_duplicate_mail) {
				errorMsg_mail = "このメールアドレスはすでに使用されています";
			}
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
			errorMsg_mail = "システムエラーが発生しました。管理者にご連絡ください";
		}

		// パスワードチェック
		CheckPass p = new CheckPass();
		ErrorMsgPass check_pass = p.checkPass(pass);
		
		switch (check_pass) {
			case MAX_WORD:
				errorMsg_pass = "パスワードは半角英数6文字で入力してください";
				break;
			case BLANK:
				errorMsg_pass = "パスワードが入力されていません";
				break;
			default:	
		}
		
		// 名前チェック
		CheckName n = new CheckName();
		ErrorMsgName check_name = n.checkName(name);
				
		switch (check_name) {
			case MAX_WORD:
				errorMsg_name = "名前は20文字以内で入力してください";
				break;
			case SPACE:
				errorMsg_name = "スペースは無効です";
				break;
			case BLANK:
				errorMsg_name = "名前が入力されていません";
				break;
			default:
		}
		
		Staff entryStaff = new Staff(mail,pass,name);
		request.setAttribute("entryStaff", entryStaff);
		
		request.setAttribute("errorMsg_mail", errorMsg_mail);
		request.setAttribute("errorMsg_pass", errorMsg_pass);
		request.setAttribute("errorMsg_name", errorMsg_name);
				
		// 確認画面へ
		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/entryConfirm.jsp");
		dispatcher.forward(request, response);
	}				
}