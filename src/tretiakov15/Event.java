package ua.khpi.oop.tretiakov15;

import java.io.Serializable;


public class Event implements Comparable<Event>, Serializable {

	private String data; // Дата
	private String startTime; // Время начала
	private int duration; // Продолжительность
	private String venue; // Место проведения
	private String description; // Описание
	private Participant[] participants; // Участники
	
	public Event(String data, String startTime, int duration, 
			String venue, String description, Participant[] participants) {
	this.data = data;
	this.startTime = startTime;
	this.duration = duration;
	this.venue = venue;
	this.description = description;
	this.participants = participants;
	}

	public Event() {
	}
	
	public String getData() {
		return this.data;
	}
	public void setData(String data) {
		this.data = data;
	}
	
	public String getStartTime() {
		return this.startTime;
	}
	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}
	
	public int getDuration() {
		return this.duration;
	}
	public void setDuration(int duration) {
		this.duration = duration;
	}
	
	public String getVenue() {
		return this.venue;
	}
	public void setVenue(String venue) {
		this.venue = venue;
	}
	
	public String getDescription() {
		return this.description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	
	public Participant[] getParticipants() {
		return this.participants;
	}
	public void setParticipants(Participant[] participants) {
		this.participants = participants;
	}
	
	@Override
	public String toString() {
		StringBuffer buf = new StringBuffer();
		buf.append("Дата: " + this.data + "\n");
		buf.append("Время начала: " + this.startTime + "\n");
		buf.append("Продолжительность: " + this.duration + "\n");
		buf.append("Место проведения: " + this.venue + "\n");
		buf.append("Описание: " + this.description + "\n");
		buf.append("Участники: ");
		for(int i = 0; i < this.participants.length; i++) {
			buf.append(this.participants[i].toString());
		}
		return buf.toString();
	}
	
	@Override
    public int compareTo(Event o) {
        Event entry = (Event) o;

        int tmp = o.getData().compareTo(entry.getData());
        // this.payment - ((SecondCreate)o).payment;
        return tmp;
    }
}
