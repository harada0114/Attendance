package model;

import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import dao.TimeDAO;
import model.Time;

public class GetListLogic {
	
	public List<Time> execute(String mail,Date today) throws ClassNotFoundException,SQLException {
		
		// ここでformatして渡す
		SimpleDateFormat tdf = new SimpleDateFormat("yyyy/MM/dd");
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd HH:mm");
			
		String day = tdf.format(today);
		String admission = sdf.format(today);
		String leaving = sdf.format(today);
		
		Time time = new Time (mail,day,admission,leaving);
		
		TimeDAO dao = new TimeDAO();
		List<Time> timeList = dao.findAll(time);
		return timeList;
	}
}