package model;

import java.sql.SQLException;

import dao.TimeDAO;

public class CreateDayLogic {
	
	// 1日の表示設定
		public String OnDay(int year, int month, int startDayNo, int[] calendarDay, String mail) {
						
			System.out.println("iがstartDayNo="+startDayNo);
			System.out.println("mail="+mail);
			
			//StringBufferクラスは値が変更された場合、初期生成時のメモリ領域を使用するため(使いまわせる)効率がよくなる。
			StringBuffer sb = new StringBuffer();
			
			sb.append("<tr>");

			for (int i = startDayNo ; i < startDayNo + 7 ; i++) {
				
				// 前月及び翌月の箇所には勤怠表示しない
				if (calendarDay[i] > 35) {
	    		   sb.append("<td class='sche'></td>"); // 列を終わらす
	               // [0][1][2]は35超えている(先月)ので、ifで処理が終わる
	       	
				} else {
	       		// 配列[3]の中1からelse処理になり、iは[3]
	               sb.append("<td class='sche'>");
	              // sb.append("<br>");

	               // 勤怠表示部分
	               sb.append("<span class='small'>");
	               
	               System.out.println("データベース検索日時"+i); // [3](1)

	               // DB検索
	               TimeDAO attendance = new TimeDAO();
	               
	               try {
	            	   Time timeList = attendance.findAll(year, month, i, calendarDay, mail);
	            	   
	            	   // 表示用に値を加工
	            	   ChangeString a = new ChangeString();
	            	   String b = a.makeAdmission(timeList);
	            	   
	            	   sb.append(b);
	            	   
	               } catch (ClassNotFoundException | SQLException e) {
	            	   e.printStackTrace();
	            	   sb.append("システムエラーが発生しました。管理者にご連絡ください");
	               }
	               sb.append("</span>");
	               sb.append("</td>");
				}
				sb.append("</td>");
			}
			sb.append("</tr>");	
			return (new String(sb)); // 文字列を連結したのを渡す。
		}
	}