package servlet;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.BackTrackLogic;
import model.CheckWord;
import model.SearchUserLogic;
import model.Staff;

@WebServlet("/SearchUserServlet")
public class SearchUserServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		request.setCharacterEncoding("UTF-8");
		String text = request.getParameter("text");
		request.setAttribute("text",text);
		
		String error_msg = "";
		String msg = "";
		
		List<Staff> staff_List = new ArrayList<Staff>();
		// 新しく詰めるようのリスト
		List<Staff> staff_List2 = new ArrayList<Staff>();
		
		// 変換用
		int page = 0;
		
		// 件数
		int count = 0;
		
		int all_page = 0;
		
		
		// textチェック
		CheckWord a = new CheckWord();
		String[] word = a.execute(text);
		
		System.out.println("検索ワードチェック後の配列の中身 = "+Arrays.toString(word));
		
		// 空文字なら
		if (word == null) {
			RequestDispatcher dispatcher = request.getRequestDispatcher("/UserListServlet");
			dispatcher.forward(request, response);
			return;

		} else {
			try {
				
				// 入力されたtextの全パターンを配列化
				
				// BackTrackLogicクラスで使うための配列
				boolean[] flag = new boolean[word.length+1];
				String[] new_word = new String[word.length];
				List new_word_list = new ArrayList<String>();
				
				BackTrackLogic d = new BackTrackLogic();
				List word_List = d.make_perm(0, word, flag, new_word, new_word_list);
				
				// DBに接続
				SearchUserLogic b = new SearchUserLogic();
				staff_List = b.execute(word_List);
			
				if (staff_List.isEmpty()) {
					msg = "該当データはありません";
				}
							
			} catch (ClassNotFoundException | SQLException e) {
				e.printStackTrace();
				error_msg = "システムエラーが発生しました。管理者にご連絡ください";
			}
			
			if (!msg.equals("") || !error_msg.equals("")) {
				
				request.setAttribute("error_msg",error_msg);
				request.setAttribute("msg",msg);
				request.setAttribute("page", page);
				request.setAttribute("count", count);
				request.setAttribute("all_page", all_page);
				request.setAttribute("staff_List2",staff_List2);
				
				RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/searchresult.jsp");
				dispatcher.forward(request, response);
				return;
			}
			
			
			
			// 検索用一覧ページ
			// 押されたページ数をJSPから取得
			request.setCharacterEncoding("UTF-8");
			String next_page = request.getParameter("next_page");
			
			if (next_page == null) {
				next_page = "0";
			}
			
			page = Integer.parseInt(next_page);
				
			// 全件数
			count = staff_List.size();
					
			// 1ページに表示したい件数
			int hope_count = 10;
			
			// ページ数
			// 1ページに10件表示
			all_page = count / hope_count;
					
			// あまった分を「次へ」に表示するため
			if (count % hope_count != 0) {
				all_page = all_page + 1;
			}
			System.out.println("必要なpage = "+all_page);
			
			// 初期化
			int i = 0;
			
			// ページ作成
			// ページ数が1ページで足りる場合
			if (page == 0) {
				for (i = 0; i < hope_count; i++) {
					if (i >= staff_List.size()) {
						break;
					}
					staff_List2.add(staff_List.get(i));
				}
				
				System.out.println(staff_List2.toString());
			
			} else {
				for (i = page*hope_count; i < page*hope_count+hope_count; i++) {
					if (i >= staff_List.size()) {
						break;
					}
					staff_List2.add(staff_List.get(i));
				}
				System.out.println(staff_List2.toString());
			}
		}
		
		request.setAttribute("error_msg",error_msg);
		request.setAttribute("msg",msg);
		request.setAttribute("page", page);
		request.setAttribute("count", count);
		request.setAttribute("all_page", all_page);
		request.setAttribute("staff_List2",staff_List2);
		
		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/searchresult.jsp");
		dispatcher.forward(request, response);
	}
}