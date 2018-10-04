package com.trcalendar.dao;

import java.util.List;
import com.trcalendar.model.TrEvent;
import com.trcalendar.model.TrCalendar;

public interface CalendarDao {
	
	public TrCalendar getCalendarById(int trCalendarId);
	
	public List<TrEvent> getEvents(int calendarId);
	
	public void addCalendar(TrCalendar calendar);
	
	public void updateCalendar(TrCalendar trCalendar);
	
	public void deleteCalendar(int calendarId);
}