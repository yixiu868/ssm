package com.ww.servlet.listener.demo;

public class PersonTest {

	public static void main(String[] args) {
		Person person = new Person();
		person.registerListener(new PersonListener() {
			
			@Override
			public void dorun(Event e) {
				Person source = e.getSource();
				System.out.println(source + "在吃东西");
			}
			
			@Override
			public void doeat(Event e) {
				Person source = e.getSource();
				System.out.println(source + "在跑步");
			}
		});
		
		person.eat();
		person.run();
	}
}
