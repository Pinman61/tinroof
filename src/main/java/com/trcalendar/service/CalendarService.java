package com.trcalendar.service;

import java.util.List;

import com.trcalendar.model.TrCalendar;
import com.trcalendar.model.TrEvent;

public interface CalendarService {
	
	public List<TrEvent> getDateEvents(int calendarId, String targetDay);
	
	public List<TrEvent> getWeekEvents(int calendarId, String targetDay);
	
	public List<TrEvent> getMonthEvents(int calendarId, String targetMonth);
	
	public List<TrEvent> getEvents(int calendarId);
	
	public String getCalendarString(int trCalendarId);
	
	public TrCalendar getCalendarById(int trCalendarId);
	
	public void addCalendar(TrCalendar calendar);
	
	public void updateCalendar(TrCalendar trCalendar);
	
	public void deleteCalendar(int calendarId);
	
	public void addEvent(TrCalendar calendar, TrEvent evnt);
	
	public void updateEvent(TrCalendar calendar, TrEvent evnt);
	
	public void removeEvent(TrCalendar calendar, int evntId);
	
	public TrEvent findEventById(int eventId);
	
	public boolean eventExists(int eventId);
}