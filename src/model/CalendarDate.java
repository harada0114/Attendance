package model;

import java.util.Calendar;

public class CalendarDate {
	
	// カレンダーの設定
	// カウント数を返す
    public static int setArray(int year, int month, int day, int[] calendarDay, int count){
           	
    	// 現在の年月日を取得
    	Calendar calendar = Calendar.getInstance();
    	
        // 今月が何曜日から開始されているか確認する
        calendar.set(year, month, 1); // 年月日をフィールドにセット
        int startWeek = calendar.get(Calendar.DAY_OF_WEEK); // 今年の今月一日の曜日を取得
        System.out.println("今月の曜日は" + startWeek + "から");

        // 先月が何日までだったかを確認する
        calendar.set(year, month, 0); // 0にすると先月の最終日になる
        int beforeMonthlastDay = calendar.get(Calendar.DATE);
        System.out.println("先月は" + beforeMonthlastDay + "日まで");

        // 今月が何日までかを確認する
        calendar.set(year, month + 1, 0);
        int thisMonthlastDay = calendar.get(Calendar.DATE);
        System.out.println("今月は" + thisMonthlastDay + "日まで\r\n");
        
        // 先月分の日付を格納する
        // startWeekの全日〜6x7枠の最初の日まで。1少ない回数回して配列に入れたいので、-2
        for (int i = startWeek - 2 ; i >= 0 ; i--){
            System.out.println(i+"、startWeek="+startWeek);
            calendarDay[count] = beforeMonthlastDay - i + 35;
            count++;
            System.out.println("先月分の日付を格納します。calendarDay[0]="+calendarDay[0]+"、count="+count+"、beforeMonthlastDay="+beforeMonthlastDay+"、+i="+i);
        }
        
        System.out.println("先月分の日付を格納した結果。calendarDay[0]="+calendarDay[0]+calendarDay[1]+calendarDay[2]+calendarDay[3]+calendarDay[4]); // 64
        System.out.println("先月count="+count); // 3
        // calendarDay配列[0][1][2]に64,65,66が入る。countには3が入る,
        
        // 今月分の日付を格納する
        // thisMonthlastDayは30
        for (int i = 1 ; i <= thisMonthlastDay ; i++){
        	System.out.println("今月分の日付を格納します。thisMonthlastDay="+thisMonthlastDay+"calendarDay[3]="+calendarDay[3]);
        	System.out.println(count);
        	calendarDay[count] = i;
            count++;
        }
        System.out.println("今月分の日付を格納した結果。calendarDay[32]="+calendarDay[32]); // 30
        System.out.println("今月count="+count); // 33 先月分+3されてる
     // calendarDay配列[3]〜[32]に今月の日付(30日)が入る。countは33
        
        /* 翌月分の日付を格納する */
        int nextMonthDay = 1;
        while (count % 7 != 0) { // すでに日数で配列が埋まっているのは33日。翌月日は残り2日しか入らない。
            System.out.println("翌月分の日付を格納します。nextMonthDay="+nextMonthDay+"calendarDay[count]="+calendarDay[count]);
        	calendarDay[count] = 35 + nextMonthDay;
        	nextMonthDay++;
        	count++;
        }
        
        System.out.println("翌月分の日付を格納する結果。calendarDay[35]="+calendarDay[35]);
        System.out.println("翌月count="+count);
        // あまりは2。[33]に36、[34]に37が入る。countは35(プラス2)

        System.out.println("return count="+count);
        return count;
    }

}
