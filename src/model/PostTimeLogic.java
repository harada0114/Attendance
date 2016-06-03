package model;

import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;

import dao.TimeDAO;

public class PostTimeLogic {
	
	// 出社
	public boolean execute_admission(String mail,Date today) throws ClassNotFoundException,SQLException {
		
		SimpleDateFormat tdf = new SimpleDateFormat("yyyy/MM/dd");
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd HH:mm");
					
		String day = tdf.format(today);
		String admission = sdf.format(today);
		String leaving = sdf.format(today);
		
		Time time = new Time (mail,day,admission,leaving);
		
		TimeDAO dao = new TimeDAO();
		return dao.admission(time);
		

	}
	
	// 退社
	public int execute_leaving(String mail,Date today) throws ClassNotFoundException,SQLException {
		
		SimpleDateFormat tdf = new SimpleDateFormat("yyyy/MM/dd");
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd HH:mm");
					
		String day = tdf.format(today);
		String admission = sdf.format(today);
		String leaving = sdf.format(today);
		
		Time time = new Time (mail,day,admission,leaving);
		
		TimeDAO dao = new TimeDAO();
		int error = dao.leaving(time);
		return error;
	}
}