package com.trcalendar.service;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import com.trcalendar.dao.CalendarDao; 
import com.trcalendar.dao.EventDao; 
import com.trcalendar.model.TrCalendar;
import com.trcalendar.model.TrEvent;

public class CalendarServiceImpl implements CalendarService{
	
	@Autowired
	private CalendarDao calendarDao;

	@Autowired
	private EventDao eventDao;
	
	@Override
	public List<TrEvent> getDateEvents(int calendarId, String mmddyyyy) {
		String getDy = mmddyyyy.substring(2,2);
		String getMo = mmddyyyy.substring(0,2);
		String getYr = mmddyyyy.substring(4,4);
		
		List<TrEvent> allEvnts = getEvents(calendarId);
		List<TrEvent> returnEvents = new ArrayList<TrEvent>();
		for (TrEvent evnt: allEvnts) {
			String dy = evnt.getDate().substring(2,2);
			String mo = evnt.getDate().substring(0,2);
			String yr = evnt.getDate().substring(4,4);
			if (yr.equals(getYr)) {
				if (mo.equals(getMo)) {
					if (dy.equals(getDy)) {
						returnEvents.add(evnt);
					}
				}
			}
		}
/*		
		StringBuilder retVal = new StringBuilder(getCalendarString(calendarId));
		retVal.append("\n\"Events\": {");
		for (TrEvent evnt: returnEvents) {
			retVal.append("\n\t\"Event\": [\n\t{");
			retVal.append("\n\t\"event id\" \" + " + evnt.getTrEventId());
			retVal.append("\n\t\"title\" \" + " + evnt.getTitle());
			retVal.append("\n\t\"date\" \" + " + evnt.getDate());
			retVal.append("\n\t\"time\" \" + " + evnt.getTime());
			retVal.append("\n\t\"time\" \" + " + evnt.getLocation());
			retVal.append("\n\t\"time\" \" + " + evnt.getAttendees());
			retVal.append("\n\t\"},\\n\\t]");
		}
		retVal.append("\n\t}\n}");
*/		
		return returnEvents;
	}
	
	@SuppressWarnings("deprecation")
	public List<TrEvent> getWeekEvents(int calendarId, String mmddyyyy) {
		
		List<TrEvent> allEvnts = getEvents(calendarId);
		List<TrEvent> returnEvents = new ArrayList<TrEvent>();
		
		Calendar c=Calendar.getInstance();
		c.setTime(new Date(mmddyyyy));
		int week1 = c.get(c.WEEK_OF_YEAR);
		Calendar c2 = Calendar.getInstance();
		
		for (TrEvent evnt: allEvnts) {
			c2.setTime(new Date(evnt.getDate()));
			int week2 = c2.get(c.WEEK_OF_YEAR);
			if(week1 == week2){
		       returnEvents.add(evnt);
		    }
		}
		return returnEvents;
	};
	

	public List<TrEvent> getMonthEvents(int calendarId, String mmyyyy) {
		String getMo = mmyyyy.substring(0,2);
		String getYr = mmyyyy.substring(2,4);
		
		List<TrEvent> allEvnts = getEvents(calendarId);
		List<TrEvent> returnEvents = new ArrayList<TrEvent>();
		for (TrEvent evnt: allEvnts) {
			String mo = evnt.getDate().substring(0,2);
			String yr = evnt.getDate().substring(4,4);
			if (yr.equals(getYr)) {
				if (mo.equals(getMo)) {
					returnEvents.add(evnt);
				}
			}
		}
		return returnEvents;
	};
	
	@SuppressWarnings("unchecked")
	@Override
	public List<TrEvent> getEvents(int calendarId) {
		List<TrEvent> objs = calendarDao.getEvents(calendarId);
		return objs;
	}
	
	@Override
	public String getCalendarString(int trCalendarId) {
		TrCalendar cal = calendarDao.getCalendarById(trCalendarId);
		StringBuilder retVal = new StringBuilder("{\n\"Calendar\": {\n\t\"User\" \":" + cal.getTrUser().getName() + ",\n\t\"Name\" \":"  + cal.getName() +  ",");
		return retVal.toString();
	}
	
	@Override
	public TrCalendar getCalendarById(int trCalendarId) {
		TrCalendar obj = calendarDao.getCalendarById(trCalendarId);
		return obj;
	}
	
	@Override
	public void addCalendar(TrCalendar calendar) {
		calendarDao.addCalendar(calendar);
	}
	
	@Override
	public void updateCalendar(TrCalendar trCalendar) {
		calendarDao.updateCalendar(trCalendar);
	}

	@Override
	public void deleteCalendar(int calendarId) {
		calendarDao.deleteCalendar(calendarId);
	}
	
	@Override
	public void addEvent(TrCalendar calendar, TrEvent evnt) {
		eventDao.addEvent(evnt);
	}
	
	@Override
	public void updateEvent(TrCalendar calendar, TrEvent evnt) {
		eventDao.addEvent(evnt);
	}
	
	public void removeEvent(TrCalendar calendar, int evntId) {
		eventDao.deleteEvent(evntId);
	}
	
	public TrEvent findEventById(int eventId) {
		return eventDao.findEventById(eventId);
	}
	
	public boolean eventExists(int eventId) {
		return eventDao.eventExists(eventId);
	}
}
