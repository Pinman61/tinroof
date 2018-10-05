package com.trcalendar.async;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import org.springframework.scheduling.annotation.Async;
import com.trcalendar.model.TrEvent;

public class AsyncNotificationService {
		 
	private com.trcalendar.dao.EventDao eventDao;
		
	@Async
	public void sendNotificaitoin() throws InterruptedException {
		
		List<TrEvent> uEvents;
		Date newDate;
    	String inputFormat = "MMddyyyy HH:mm";
		SimpleDateFormat sdf = new SimpleDateFormat(inputFormat);
			
        while (true) { 
				
        	uEvents = eventDao.getUnflaggedEvents();
        	newDate = new Date();

        	for (TrEvent e: uEvents) {
        		try {

        			String date2 = e.getDate();
        			String time2 = e.getTime();

        			Date dateObj2 = sdf.parse(date2 + " " + time2);
	        	 
       				// getTime() returns the number of milliseconds since January 1, 1970, 00:00:00 GMT represented by this Date object
       				long diff = dateObj2.getTime() - newDate.getTime();
	        	 
       				int diffmin = (int) (diff / (60 * 1000));
        				
       				if ((diffmin > 0) && (diffmin < 15000)) {
       					// .send notification
       					System.out.println(e.getCalendar().getTrUser().getName() + " has an event coming up in " + diffmin + " minutes");
        				e.setReminderSent(true);  	
        				eventDao.updateEvent(e); // update event reminder flag
        			}	
	        	 
	        	 
	   			} catch (Exception ex) {
	   				ex.printStackTrace();
	   			}
	       	}
	        	
	       	/* sleep for 10 minutes then check events again, this assumes reminder process 
               for all events will complete in less than 5 minutes */
	       	Thread.sleep(10000);
        }	       	
	}
}
