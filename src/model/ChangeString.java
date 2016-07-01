package model;

public class ChangeString {

	// 表示用に取得した出退時間を加工
	// 文字列を返す
	public String makeAdmission(Time time) {

		String sb = "";
		
		// 値がなかった
		if (time == null) {
			sb = "<br>";
		
		} else {
			// 退社がnull
			if (time.getLeaving() == null) {
				sb = "出社:" + "<br>　　" + time.getAdmission().substring(11, 16) + "<br>" + "退社:" + "<br>　　" + "未完了" + "<br>";
		   
			// 取得した日付と退社の日付部分(0~10文字目)が同じなら
			} else if (time.getDay().equals(time.getLeaving().substring(0, 10))) {
				sb = "出社:" + "<br>　　" + time.getAdmission().substring(11, 16) + "<br>" + "退社:" + "<br>　　" + time.getLeaving().substring(11,16) + "<br>";
	   
				// 日跨ぎ退社なら
			} else {
				sb = "出社:" + "<br>　　" + time.getAdmission().substring(11, 16) + "<br>" + "退社:" + "<br>　" + time.getLeaving().substring(0,10) + "<br>　　" + time.getLeaving().substring(11,16) + "<br>";
			}
		}
		return sb;
	}
}