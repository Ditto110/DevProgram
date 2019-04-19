package com.ditto.test.test;

public class Test2 {
	public static void main(String[] args) {
		StringBuilder sb = new StringBuilder();
		sb.append("1111111111");
		testFunc(sb);
		System.out.println(sb.toString());
	}

	public static StringBuilder testFunc(StringBuilder sb) {
		sb.append("3333333333");
		return sb;
	}
}

class Person{
	private String name;

	public Person(String name) {
		this.name = name;
	}
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	void printName() {
		System.out.println(this.name);
	}
	@Override
	public String toString() {
		return "Person [name=" + name + "]";
	}
	
}