// 新規登録サーブレット

package servlet;

import java.io.IOException;

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
		CheckMail m = new CheckMail();
		ErrorMsgMail checkmail = m.checkMail(mail);
		
		switch (checkmail) {
			// 半角英数かつ40文字以内
			case mail_limit:
				request.setAttribute("errorMsg1","メールアドレスは半角英数40文字以内で入力してください");
				break;
			// メールアドレス形式	
			case mail_pattern:
				request.setAttribute("errorMsg1","メールアドレスの形式が正しくありません");
				break;
			// 空白	
			case mail_blank:
				request.setAttribute("errorMsg1","メールアドレスが入力されていません");
				break;
			// 保存
			default:
				request.setAttribute("mail",mail);	
		}
		
		// パスワードチェック
		CheckPass p = new CheckPass();
		ErrorMsgPass checkpass = p.checkPass(pass);
		
		switch (checkpass) {
			// 半角英数6文字
			case pass_limit:
				request.setAttribute("errorMsg2","パスワードは半角英数6文字で入力してください");
				break;
			// 空白	
			case pass_blank:
				request.setAttribute("errorMsg2","パスワードが入力されていません");
				break;
			default:	
				request.setAttribute("pass",pass);	
		}
		
		// 名前チェック
		CheckName n = new CheckName();
		ErrorMsgName checkname = n.checkName(name);
				
		switch (checkname) {
			// 20文字以内
			case name_limit:
				request.setAttribute("errorMsg3","名前は20文字以内で入力してください");
				break;
			// スペース無効	
			case name_space:
				request.setAttribute("errorMsg3","スペースは無効です");
				break;
			// 空白	
			case name_blank:
				request.setAttribute("errorMsg3","名前が入力されていません");
				break;
			default:
				request.setAttribute("name",name);	
		}
		
				//Staff entryStaff = new Staff(mail,pass,name);
				//request.setAttribute("entryStaff", entryStaff);
				
				// 確認画面へ
				RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/entryConfirm.jsp");
				dispatcher.forward(request, response);
	}				
}
		
		// ①パラメータチェックできたらこのサーブレットで確認画面をだすまでいく。
		// ②その後、登録サーブレットで登録
		
		///////////////////////////////////////////////////////////////////////////////////////////
		
		
		//  ↓確認画面まで
	/*			
  	Staff entryStaff = new Staff(mail,pass,name);
					
			HttpSession session = request.getSession();
			session.setAttribute("entryStaff", entryStaff); // "属性名",インスタンス
			
			
			HttpSession session = request.getSession();
			Staff entryStaff = (Staff) session.getAttribute("entryStaff");
			
			StaffLogic logic = new StaffLogic();
			boolean b = logic.execute(entryStaff);
			
			request.setAttribute("okMsg1","登録が完了しました");

			forwardPath = "/index.jsp";
			
			RequestDispatcher dispatcher = request.getRequestDispatcher(forwardPath);
			dispatcher.forward(request, response);
			
			
}
	
		// 確認画面
		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/entryConfirm.jsp");
		dispatcher.forward(request, response);
	}
}*/


//登録処理
/*
String forwardPath = "";

String action = request.getParameter("action");

// MainServlet(index.jsp)から「新規登録」をリクエストされたときの処理
if (action == null) {
	forwardPath = "/WEB-INF/jsp/entry.jsp";
}

// 「登録確認画面」から「登録」をリクエストされた時の処理
else if (action.equals("done")) {
	
	HttpSession session = request.getSession();
	Staff entryStaff = (Staff) session.getAttribute("entryStaff");
	
	StaffLogic logic = new StaffLogic();
	boolean b = logic.execute(entryStaff); // →参照渡し
	
	if (!b) { // 被ったら
		
		// セッションスコープ内のインスタンスを削除。※必要→これ消すとエラーでない？
		//session.removeAttribute("entryStaff");
		
		request.setAttribute("errorMsg4","このメールアドレスはすでに使われています");
		
		forwardPath = "/WEB-INF/jsp/entryConfirm.jsp";
		
	} else {
	
		request.setAttribute("okMsg1","登録が完了しました");

		forwardPath = "/index.jsp";
	}
}

RequestDispatcher dispatcher = request.getRequestDispatcher(forwardPath);
dispatcher.forward(request, response);
}

*/
