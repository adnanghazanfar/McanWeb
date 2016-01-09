package com.eamtar.mccn.util;

public class MainTest {
	
	public static void main(String[] args) {
		String field  = "Master's Degree";
		field = field.replaceAll("'", "'\'");
		System.out.println(field);
	    String s = "Hello 'thanks' bye";
	    s = s.replace("'", "\\'");
	    System.out.println(s);
	}

}
