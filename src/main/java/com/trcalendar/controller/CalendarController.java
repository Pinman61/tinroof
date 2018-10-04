package com.trcalendar.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;
import com.trcalendar.model.TrCalendar;
import com.trcalendar.model.TrEvent;
import com.trcalendar.service.CalendarService;

@RestController
@RequestMapping("/calendar")
public class CalendarController {

	@Autowired
	private CalendarService calendarService;
	
	@GetMapping("{id}")
	public ResponseEntity<TrCalendar> getCalendar(@PathVariable Integer id) {
		TrCalendar calendar = calendarService.getCalendarById(id);
		List<TrEvent> evnts = calendarService.getEvents(id);
		calendar.setEvents(evnts);
		return new ResponseEntity<TrCalendar>(calendar, HttpStatus.OK);
	}
	
	@GetMapping("/day/{id,targetDay}")
	public ResponseEntity<TrCalendar> getDayCalendar(@PathVariable("id") Integer id, @PathVariable("day") String targetDay) {
		TrCalendar calendar = calendarService.getCalendarById(id);
		List<TrEvent> evnts = calendarService.getDateEvents(id, targetDay);
		calendar.setEvents(evnts);
		return new ResponseEntity<TrCalendar>(calendar, HttpStatus.OK);
	}	

	@GetMapping("/week/{id,targetWeek}")
	public ResponseEntity<TrCalendar> getWeekCalendar(@PathVariable("id") Integer id, @PathVariable("week") String targetWeek) {
		TrCalendar calendar = calendarService.getCalendarById(id);
		List<TrEvent> evnts = calendarService.getWeekEvents(id, targetWeek);
		calendar.setEvents(evnts);
		return new ResponseEntity<TrCalendar>(calendar, HttpStatus.OK);
	}	
	
	@GetMapping("/month/{id,targetMonth}")
	public ResponseEntity<TrCalendar> getMonthCalendar(@PathVariable("id") Integer id, @PathVariable("month") String targetMonth) {
		TrCalendar calendar = calendarService.getCalendarById(id);
		List<TrEvent> evnts = calendarService.getMonthEvents(id, targetMonth);
		calendar.setEvents(evnts);
		return new ResponseEntity<TrCalendar>(calendar, HttpStatus.OK);
	}	
	
	@PostMapping("/event/{id}")
	public ResponseEntity<Void> addEvent(@PathVariable int calendarId, @RequestBody TrEvent newEvent, UriComponentsBuilder ucBuilder) {
		TrCalendar calendar = calendarService.getCalendarById(calendarId);
		calendarService.addEvent(calendar, newEvent);
		HttpHeaders headers = new HttpHeaders();
        headers.setLocation(ucBuilder.path("/event/{id}").buildAndExpand(newEvent.getTrEventId()).toUri());
		return new ResponseEntity<Void>(headers, HttpStatus.CREATED);
	}
	
/*
 * 
   	<dependency>
  		<groupId>com.sap.cloud.s4hana.frameworks</groupId>
  		<artifactId>spring-web</artifactId>
  		<version>2.4.2</version>
  	</dependency>
 	
 */
	
	@PutMapping(value="/event/{id}", headers="Accept=application/json")
	public ResponseEntity<String> updateEvent(@PathVariable int calendarId, @RequestBody TrEvent thisEvent)  {
		TrCalendar calendar = calendarService.getCalendarById(calendarId);
        TrEvent evnt = calendarService.findEventById(thisEvent.getTrEventId());
	    if (evnt==null) {
            return new ResponseEntity<String>(HttpStatus.NOT_FOUND);
        }
        evnt.setTitle(thisEvent.getTitle());
        evnt.setDate(thisEvent.getDate());
        evnt.setTime(thisEvent.getTime());
        evnt.setLocation(thisEvent.getLocation());
        evnt.setAttendees(thisEvent.getAttendees());
        if (!thisEvent.getReminder().equals(evnt.getReminder()) ) {
            evnt.setReminder(thisEvent.getReminder());
            evnt.setReminderSent(false);
        }
        calendarService.updateEvent(calendar, evnt);
        return new ResponseEntity<String>(HttpStatus.OK);
    }

	@DeleteMapping("event/{id}")
	public ResponseEntity<TrEvent> deleteEvent(@PathVariable int calendarId, @RequestBody TrEvent evnt) {
		TrCalendar calendar = calendarService.getCalendarById(calendarId);
        if (calendarService.eventExists(evnt.getTrEventId())) {
            calendarService.removeEvent(calendar, evnt.getTrEventId());
            return new ResponseEntity<TrEvent>(HttpStatus.NO_CONTENT);		
        } else {
            return new ResponseEntity<TrEvent>(HttpStatus.NOT_FOUND);
        }
	}
}
