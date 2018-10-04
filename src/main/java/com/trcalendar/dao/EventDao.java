package com.trcalendar.dao;

import java.util.List;
import com.trcalendar.model.TrEvent;

public interface EventDao {
	
	public TrEvent findEventById(int trEventId);
	
	public List<TrEvent> getCalendarEvents(int calendarId);
	
	public List<TrEvent> getUnflaggedEvents();
	
	public void addEvent(TrEvent event);
	
	public void updateEvent(TrEvent trEvent);
	
	public void deleteEvent(int eventId);
	
	public boolean eventExists(int eventId);

}