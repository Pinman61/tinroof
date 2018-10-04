package com.trcalendar.dao;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;
//import org.springframework.transaction.annotation.Transactional;
import com.trcalendar.model.TrEvent;
import com.trcalendar.model.TrCalendar;
import com.trcalendar.model.TrUser;

//@Transactional
@Repository
public class EventDaoImpl implements EventDao {
	
	@PersistenceContext	
	private EntityManager entityManager;	

	@Override
	public TrEvent findEventById(int trEventId) {
		return entityManager.find(TrEvent.class, trEventId);
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<TrEvent> getCalendarEvents(int calendarId) {
		String hql = "FROM TrEvent as evnt WHERE evnt.trCalendarId = ? order by evnt.trEventId";
		return (List<TrEvent>) entityManager.createQuery(hql).setParameter(1, calendarId).getResultList();
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<TrEvent>  getUnflaggedEvents() {
		String hql = "FROM TrEvent as evnt WHERE evnt.reminderSent = 0 order by evnt.trEventId";
		return (List<TrEvent>) entityManager.createQuery(hql).getResultList();
	}
	
	@Override
	public void addEvent(TrEvent event) {
		entityManager.persist(event);
	}
	
	@Override
	public void updateEvent(TrEvent trEvent) {
		
		TrEvent evnt = findEventById(trEvent.getTrEventId());
		
		evnt.setCalendar(trEvent.getCalendar());
		evnt.setTitle(trEvent.getTitle());
		evnt.setDate(trEvent.getDate());
		evnt.setTime(trEvent.getTime());
		evnt.setLocation(trEvent.getLocation());
		evnt.setAttendees(trEvent.getAttendees());
		evnt.setReminder(trEvent.getReminder());
		evnt.setReminderSent(trEvent.getReminderSent());
		
		entityManager.flush();
	}
	
	@Override
	public void deleteEvent(int eventId) {
		entityManager.remove(findEventById(eventId));
	}
	
	@Override
	public boolean eventExists(int eventId) {
		String hql = "FROM TrEvent as evnt WHERE evnt.trEventId = ?";
		int count = entityManager.createQuery(hql).getResultList().size();
		return count > 0 ? true : false;
	}
}
