package servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Calendar;
import java.util.Date;
import java.sql.PreparedStatement;
import java.text.SimpleDateFormat;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.Time;


@WebServlet("/CalendarViewServlet")
public class CalendarViewServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected Connection conn = null;

    public void init() throws ServletException{
        String url = "jdbc:mysql://localhost/attendance";
        String user = "harada";
        String password = "dandt";

        try {
            Class.forName("com.mysql.jdbc.Driver").newInstance();
            conn = DriverManager.getConnection(url, user, password);
        }catch (ClassNotFoundException e){
            log("ClassNotFoundException:" + e.getMessage());
        }catch (SQLException e){
            log("SQLException:" + e.getMessage());
        }catch (Exception e){
            log("Exception:" + e.getMessage());
        }
    }

    public void destory(){
        try{
            if (conn != null){
                conn.close();
            }
        }catch (SQLException e){
            log("SQLException:" + e.getMessage());
        }
    }

    public void doGet(HttpServletRequest req, HttpServletResponse res)
        throws ServletException, IOException{
    	
    	/*
    	String random_word = request.getParameter("random_word");
		request.setCharacterEncoding("UTF-8");
		
		HttpSession session = request.getSession();
		Map<String, String> account = (Map<String, String>)session.getAttribute("account");
		String mail = account.get(random_word);
		*/

    	String mail = "harada@yahoo.co.jp";
		
    	Date today = new Date();    	
    	SimpleDateFormat tdf = new SimpleDateFormat("yyyy/MM/dd");
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd HH:mm");
			
		String day1 = tdf.format(today);
		String admission = sdf.format(today);
		String leaving = sdf.format(today);
		
		Time time = new Time (mail,day1,admission,leaving);
    	
    	
        res.setContentType("text/html;charset=UTF-8");
        PrintWriter out = res.getWriter();

        int[] calendarDay;
        int count;

        int year;
        int month;
        int day = 1;

        calendarDay = new int[42];  /* 最大で7日×6週 */
        count = 0;

        String param = req.getParameter("YEAR");
        if (param == null || param.length() == 0){
            year = -999;
        }else{
            try{
                year = Integer.parseInt(param);
            }catch (NumberFormatException e){
                year = -999;
            }
        }

        param = req.getParameter("MONTH");
        if (param == null || param.length() == 0){
            month = -999;
        }else{
            try{
                month = Integer.parseInt(param);
            }catch (NumberFormatException e){
                month = -999;
            }
        }

        /* パラメータが指定されていない場合は本日の日付を設定 */
        if (year == -999 || month == -999){
            Calendar calendar = Calendar.getInstance();
            year = calendar.get(Calendar.YEAR);
            month = calendar.get(Calendar.MONTH);
            day = calendar.get(Calendar.DATE);
        }else{
            if (month == 12){
                month = 0;
                year++;
            }

            if (month == -1){
                month = 11;
                year--;
            }
        }

        StringBuffer sb = new StringBuffer();

        sb.append("<!DOCTYPE html PUBLIC>");

        sb.append("<html lang=\"ja\">");
        sb.append("<head>");
        sb.append("<meta http-equiv=\"Content-Type\" Content=\"text/html;charset=UTF-8\">");

        sb.append("<title>スケジュール管理</title>");

        sb.append("<style>");
        sb.append("table{border:1px solid #a9a9a9;width:90%;padding:0px;margin:0px;border-collapse:collapse;}");
        sb.append("td{width:12%;border-top:1px solid #a9a9a9;border-left:1px solid #a9a9a9;vertical-align:top;margin:0px;padding:2px;}");
        sb.append("td.week{background-color:#f0f8ff;text-align:center;}");
        sb.append("td.day{background-color:#f5f5f5;text-align:right;font-size:0.75em;}");
        sb.append("td.otherday{background-color:#f5f5f5;color:#d3d3d3;text-align:right;font-size:0.75em;}");
        sb.append("td.sche{background-color:#fffffff;text-align:left;height:80px;}");
        sb.append("img{border:0px;}");
        sb.append("span.small{font-size:0.75em;}");
        sb.append("</style>");

        sb.append("</head>");
        sb.append("<body>");

        /* 日付データを配列に格納 */
        count = setDateArray(year, month, day, calendarDay, count);

        /* 年月のリンク作成 */
        sb.append(createMonthLink(year, month));

        sb.append("<table>");

        sb.append("<tr><td class=\"week\">日</td><td class=\"week\">月</td><td class=\"week\">火</td><td class=\"week\">水</td><td class=\"week\">木</td><td class=\"week\">金</td><td class=\"week\">土</td></tr>");

        int weekCount = count / 7;

        for (int i = 0 ; i < weekCount ; i++){
            /* スケジュールの日付画面を作成する */
            sb.append("<tr>");

            for (int j = i * 7 ; j < i * 7 + 7 ; j++){
                if (calendarDay[j] > 35){
                    sb.append("<td class=\"otherday\">");
                    sb.append(calendarDay[j] - 35);
                }else{
                    sb.append("<td class=\"day\">");
                    sb.append(calendarDay[j]);
                }
                sb.append("</td>");
            }

            sb.append("</tr>");

            /* カレンダーのスケジュール登録画面を作成する */
            sb.append(createScheduleStr(year, month, i * 7, calendarDay, time));
        }

        sb.append("</table>");

        sb.append("</body>");
        sb.append("</html>");

        out.println(new String(sb));
    }

    /* スケジュール登録へのリンクを設定する */
    protected String createScheduleStr(int year, int month, int startDayNo, int[] calendarDay, Time time){
        StringBuffer sb = new StringBuffer();

        sb.append("<tr>");

        for (int i = startDayNo ; i < startDayNo + 7 ; i++){
            if (calendarDay[i] > 35){
                /* 前月及び翌月の箇所にはアイコンは表示しない */
                sb.append("<td class=\"sche\"></td>");
            }else{
                sb.append("<td class=\"sche\">");
                sb.append("<a href=\"/schedule/NewSchedule");

                /* パラメータの作成 */
                sb.append("?YEAR=");
                sb.append(year);
                sb.append("&MONTH=");
                sb.append(month);
                sb.append("&DAY=");
                sb.append(calendarDay[i]);

                sb.append("\">");
                sb.append("<img src=\"./img/memo.png\" width=\"14\" height=\"16\">");
                sb.append("</a><br>");

                /* スケジュールの表示 */
                // データベースに接続
                
                sb.append("<span class=\"small\">");

                try {
                	//String sql = "SELECT * FROM schedule WHERE userid = ? and scheduledate = ? ORDER BY starttime";
                	String sql = "SELECT * FROM time WHERE mail=? AND day=?";
                    PreparedStatement pstmt = conn.prepareStatement(sql);

                    String startDateStr = year + "-" + (month + 1) + "-" + calendarDay[i];
                    pstmt.setString(1, time.getMail());
                    pstmt.setString(2, startDateStr);

                    ResultSet rs = pstmt.executeQuery();

                    while(rs.next()){
                    	String mail = rs.getString("mail");
        				String starttime = rs.getString("day");
        				String schedule1 = rs.getString("admission");
        				String schedule2 = rs.getString("leaving");
                    	//String starttime = rs.getString("starttime");
                        //String endtime = rs.getString("endtime");
                        //String schedule = rs.getString("schedule");

        				if (starttime == null){
                            sb.append("* " + schedule1 + schedule2 + "<br>");
                        }else{
                            String startTimeStr = starttime.substring(0, 5);
                            //String endTimeStr = endtime.substring(0, 5);
                            sb.append(schedule1 + "<br>" + schedule2 + "<br>");
                        }
        				
        				/*
                        if (starttime == null || endtime == null){
                            sb.append("* " + schedule + "<br>");
                        }else{
                            String startTimeStr = starttime.substring(0, 5);
                            String endTimeStr = endtime.substring(0, 5);
                            sb.append(startTimeStr + "-" + endTimeStr + " " + schedule + "<br>");
                        }
                        */
                    }

                    rs.close();
                    pstmt.close();

                }catch (SQLException e){
                    log("SQLException:" + e.getMessage());
                }

                sb.append("</span>");

                sb.append("</td>");
            }
            sb.append("</td>");
        }

        sb.append("</tr>");

        return (new String(sb));
    }

    protected int setDateArray(int year, int month, int day, int[] calendarDay, int count){
        Calendar calendar = Calendar.getInstance();

        /* 今月が何曜日から開始されているか確認する */
        calendar.set(year, month, 1);
        int startWeek = calendar.get(Calendar.DAY_OF_WEEK);
        System.out.println("今月の曜日は" + startWeek + "から");

        /* 先月が何日までだったかを確認する */
        calendar.set(year, month, 0);
        int beforeMonthlastDay = calendar.get(Calendar.DATE);
        System.out.println("先月は" + beforeMonthlastDay + "日まで");

        /* 今月が何日までかを確認する */
        calendar.set(year, month + 1, 0);
        int thisMonthlastDay = calendar.get(Calendar.DATE);
        System.out.println("今月は" + thisMonthlastDay + "日まで\r\n");

        /* 先月分の日付を格納する */
        for (int i = startWeek - 2 ; i >= 0 ; i--){
            calendarDay[count++] = beforeMonthlastDay - i + 35;
        }

        /* 今月分の日付を格納する */
        for (int i = 1 ; i <= thisMonthlastDay ; i++){
            calendarDay[count++] = i;
        }

        /* 翌月分の日付を格納する */
        int nextMonthDay = 1;
        while (count % 7 != 0){
            calendarDay[count++] = 35 + nextMonthDay++;
        }

        return count;
    }

    protected String createMonthLink(int year, int month){
        StringBuffer sb = new StringBuffer();

        sb.append("<p>");

        sb.append("<a href=\"/Attendance/CalendarViewServlet?YEAR=");
        sb.append(year);
        sb.append("&MONTH=");
        sb.append(month - 1);
        sb.append("\"><span class=\"small\">前月</span></a>  ");

        sb.append(year);
        sb.append("年");
        sb.append(month + 1);
        sb.append("月  ");

        sb.append("<a href=\"/Attendance/CalendarViewServlet?YEAR=");
        sb.append(year);
        sb.append("&MONTH=");
        sb.append(month + 1);
        sb.append("\"><span class=\"small\">翌月</span></a>");

        sb.append("</p>");

        return (new String(sb));
    }


protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
}
}
