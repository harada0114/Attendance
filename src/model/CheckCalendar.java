package model;

import java.util.Calendar;

public class CheckCalendar {
	
	// リクエストパラメータをチェックします
	public static int[] checkParam(String year_param, String month_param,int day) {
		
		int year = 0;
        int month = 0;
		
        // 空、もしくはnullなら
        if (year_param == null || year_param.length() == 0){
        	year_param = "-999";   
        }
        
        if (month_param == null || month_param.length() == 0){
        	month_param = "-999";
        }
        	
        try {
        	// 取得した値をint型に変換
            year = Integer.parseInt(year_param);
            month = Integer.parseInt(month_param);
            
        } catch (NumberFormatException e){
            year = -999;
            month = -999;
        }

        // パラメータが入っていない場合、本日の年日付を設定
        if (year == -999 || month == -999){
        	Calendar calendar = Calendar.getInstance(); // デフォルトで現在の年月日を取得
            year = calendar.get(Calendar.YEAR);     // 年
            month = calendar.get(Calendar.MONTH);   // 月
            day = calendar.get(Calendar.DATE);      // 日
        
        // パラメータが入っていた場合の去年、来年チェック
        } else {
        	if (month == 12) { // 来年。calenderクラスの月は0(1月)~11(12月)。12だと来年になる
                month = 0;
                year++;
            }

            if (month == -1) { // 去年
                month = 11;
                year--;
            }
        }
        
        // チェックした値を返すための配列
        int okparam[] = new int[3];
        okparam[0] = year;
        okparam[1] = month;
        okparam[2] = day;
   
        return okparam;
	}
}