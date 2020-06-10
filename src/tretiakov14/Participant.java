package ua.khpi.oop.tretiakov14;

import java.io.Serializable;

public class Participant implements Serializable {
	private String name;
	private String surname;
	private int age;
	
	public Participant(String name, String surname, int age){
		this.name = name;
		this.surname = surname;
		this.age = age;
	}
	
	public Participant(){
	}
	
	public String getName() {
		return this.name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	public String getSurname() {
		return this.surname;
	}
	public void setSurname(String surname) {
		this.surname = surname;
	}
	
	public int getAge() {
		return this.age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	
	@Override
	public String toString() {
		StringBuffer buf = new StringBuffer();
		buf.append("Имя: " + this.name + "\n");
		buf.append("Фамилия: " + this.surname + "\n");
		buf.append("Возраст: " + this.age + "\n");
		return buf.toString();
	}
}
