package servlet;

import java.io.IOException;

import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.CheckCalendar;
import model.CalendarDate;

@WebServlet("/CalendarServlet")
public class CalendarServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		String random_word = request.getParameter("random_word");
    	request.setCharacterEncoding("UTF-8");
	
    	HttpSession session = request.getSession();
    	Map<String, String> account = (Map<String, String>)session.getAttribute("account");

    	// random_wordをキーにmailアドレスを取得
    	String mail = account.get(random_word);
    	
    	int year = 0;     // 年
        int month = 0;    // 月
        int day = 1;      // 日 ついたち

        // カレンダーのように表示するための日付配列。最大7日(列)が6週(行)
        int calendarDay[] = new int[42];  /* 最大で7日×6週 */
        // 日付の枠がどれだけいるかカウント
        int count = 0;
        
        // リクエストパラメータから年月を取得
        String year_param = request.getParameter("YEAR");
        String month_param = request.getParameter("MONTH");
                
        //  取得した値をチェックし、年、月、日が入った配列をもらう
        int[] param = CheckCalendar.checkParam(year_param, month_param, day);
        
        // チェックし終えたパラメータを配列に格納。[0]year, [1]month, [2]day
        count = CalendarDate.setArray(param[0], param[1], param[2], calendarDay, count);

        System.out.println("random_word="+random_word);
        System.out.println("param="+param);
        System.out.println("param[0]="+param[0]);
        System.out.println("param[1]="+param[1]);
        System.out.println("param[2]="+param[2]);
        System.out.println("calendarDay="+calendarDay);
        System.out.println("count="+count);
      
        request.setAttribute("mail",mail);
     	request.setAttribute("year",param[0]);
		request.setAttribute("month",param[1]);
		request.setAttribute("count",count);
		request.setAttribute("calendarDay",calendarDay);
		
		request.setAttribute("random_word",random_word); 
		
		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/attendanceList.jsp");
		dispatcher.forward(request, response);
	}
}