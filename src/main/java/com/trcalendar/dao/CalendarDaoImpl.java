package com.trcalendar.dao;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;
//import org.springframework.transaction.annotation.Transactional;
import com.trcalendar.model.TrEvent;
import com.trcalendar.model.TrCalendar;

//@Transactional
@Repository
public class CalendarDaoImpl implements CalendarDao {
	
	@PersistenceContext	
	private EntityManager entityManager;	
	

	@Override
	public TrCalendar getCalendarById(int trCalendarId) {
		return entityManager.find(TrCalendar.class, trCalendarId);
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<TrEvent> getEvents(int calendarId) {
		String hql = "FROM TrEvent as evnt WHERE evnt.trCalendarId = ? order by evnt.trEventId";
		return (List<TrEvent>) entityManager.createQuery(hql).setParameter(1, calendarId).getResultList();
	}
	
	@Override
	public void addCalendar(TrCalendar calendar) {
		entityManager.persist(calendar);
	}
	
	@Override
	public void updateCalendar(TrCalendar trCalendar) {
		
		TrCalendar clndr = getCalendarById(trCalendar.getTrCalendarId());
		
		clndr.setName(trCalendar.getName());
		clndr.setEvents(trCalendar.getEvents());
		
		entityManager.flush();
	}
	
	@Override
	public void deleteCalendar(int calendarId) {
		entityManager.remove(getCalendarById(calendarId));
	}
	
}
