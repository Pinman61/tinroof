package com.trcalendar.model;


import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="trCalendar")
public class TrCalendar {


	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
    @Column(name = "trCalendarId", unique = true, nullable = false)
    private int trCalendarId;  
	private String name;
	private TrUser trUser;
	
	@OneToMany(mappedBy="trCalendar")
    private List<TrEvent> trEvents;

	public int getTrCalendarId() {
		return trCalendarId;
	}

	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	public List<TrEvent> getEvents() {
		return trEvents;
	}
	public void setEvents(List<TrEvent> trEvents) {
		this.trEvents = trEvents;
	}
			
	public TrUser getTrUser() {
		return trUser;
	}
	public void setTrUser(TrUser trUser) {
		this.trUser = trUser;
	}
	
	public List<TrEvent> getTrEvents() {
		return trEvents;
	}
	public void setTrEvents(List<TrEvent> trEvents) {
		this.trEvents = trEvents;
	}
}
