package com.ww.servlet.listener.demo;

import java.util.Objects;

public class Person {

	private PersonListener listener;
	
	public void eat() {
		if (Objects.nonNull(listener)) {
			listener.doeat(new Event(this));
		}
	}
	
	public void run() {
		if (Objects.nonNull(listener)) {
			listener.dorun(new Event(this));
		}
	}
	
	public void registerListener(PersonListener listener) {
		this.listener = listener;
	}
}
