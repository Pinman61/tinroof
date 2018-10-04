package com.trcalendar.model;

	
	import java.util.List;
	import javax.persistence.Column;
	import javax.persistence.Entity;
	import javax.persistence.GeneratedValue;
	import javax.persistence.GenerationType;
	import javax.persistence.Id;
	import javax.persistence.JoinColumn;
	import javax.persistence.ManyToOne;
	import javax.persistence.Table;

	@Entity
	@Table(name="trEvent")
	public class TrEvent {
		
		private int trEventId;
	    private String title;
		private String eDate; // formatted 'MMddyyyy' for to enable selection and comparison 05232018
		private String eTime; // formatted 'hh:mm' for to enable selection and comparison
		private String location;
		private List<String> attendees;
		private String reminder; // formatted 'hh:mm' for to enable selection and comparison
		private boolean reminderSent;
		
		@Id
		@GeneratedValue(strategy=GenerationType.AUTO)
	    @Column(name = "trEventId", unique = true, nullable = false)
		public int getTrEventId() {
			return trEventId;
		}
		
		@ManyToOne
		@JoinColumn(name="trCalendarId", nullable=false)
		private TrCalendar trCalendar;
		
/*
(calendar, title, event date and time, location, attendee list, reminder time, and whether the reminder has been sent).
 */

		public TrCalendar getCalendar() {
			return trCalendar;
		}
		public void setCalendar(TrCalendar trCalendar) {
			this.trCalendar = trCalendar;
		}
		
		public String getTitle() {
			return title;
		}
		public void setTitle(String title) {
			this.title = title;
		}

		public String getDate() {
			return eDate;
		}
		public void setDate(String eDate) {
			this.eDate = eDate;
		}
		
		public String getTime() {
			return eTime;
		}
		public void setTime(String eTime) {
			this.eTime = eTime;
		}
		
		public String getLocation() {
			return location;
		}
		public void setLocation(String location) {
			this.location = location;
		}

		public String getReminder() {
			return reminder;
		}
		public void setReminder(String reminder) {
			this.reminder = reminder;
		}

		public List<String> getAttendees() {
			return attendees;
		}
		public void setAttendees(List<String> attendees) {
			this.attendees = attendees;
		}

		public boolean getReminderSent() {
			return reminderSent;
		}
		public void setReminderSent(boolean reminderSent) {
			this.reminderSent = reminderSent;
		}
}
