package com.ww.servlet.listener.demo;

public class Event {

	private Person source;
	
	public Event() {
	}
	
	public Event(Person source) {
		this.source = source;
	}
	
	public Person getSource() {
		return source;
	}
	
	public void setSource(Person source) {
		this.source = source;
	}
}
