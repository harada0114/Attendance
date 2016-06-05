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
		
		// メールチェック		
		try {
			
			CheckMail m = new CheckMail();
			ErrorMsgMail checkmail = m.checkMail(mail);
			
			switch (checkmail) {
				case limit_mail:
					request.setAttribute("errorMsg1","メールアドレスは半角英数40文字以内で入力してください");
					break;	
				case pattern_mail:
					request.setAttribute("errorMsg1","メールアドレスの形式が正しくありません");
					break;
				case blank_mail:
					request.setAttribute("errorMsg1","メールアドレスが入力されていません");
					break;
				case double_mail:
					request.setAttribute("errorMsg1","このメールアドレスはすでに使用されています");
					break;	
				default:
			}
			
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			request.setAttribute("errorMsg1","システムエラーが発生しました。管理者にご連絡ください");
		
		} catch (SQLException e) {
			e.printStackTrace();
			request.setAttribute("errorMsg1","システムエラーが発生しました。管理者にご連絡ください");
		}
		
		
		// パスワードチェック
		CheckPass p = new CheckPass();
		ErrorMsgPass checkpass = p.checkPass(pass);
		
		switch (checkpass) {
			case limit_pass:
				request.setAttribute("errorMsg2","パスワードは半角英数6文字で入力してください");
				break;
			case blank_pass:
				request.setAttribute("errorMsg2","パスワードが入力されていません");
				break;
			default:	
		}
		
		// 名前チェック
		CheckName n = new CheckName();
		ErrorMsgName checkname = n.checkName(name);
				
		switch (checkname) {
			case limit_name:
				request.setAttribute("errorMsg3","名前は20文字以内で入力してください");
				break;
			case space_name:
				request.setAttribute("errorMsg3","スペースは無効です");
				break;
			case blank_name:
				request.setAttribute("errorMsg3","名前が入力されていません");
				break;
			default:
		}
		
		Staff entryStaff = new Staff(mail,pass,name);
		request.setAttribute("entryStaff", entryStaff);
				
		// 確認画面へ
		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/entryConfirm.jsp");
		dispatcher.forward(request, response);
	}				
}