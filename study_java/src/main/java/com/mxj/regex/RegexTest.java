package com.mxj.regex;

public class RegexTest {

	
	public static void main(String[] args) {
		
		String target="boo:and:foo";
		String[] split = target.split("o",-1);
		for (String item : split) {
			
			System.out.println(String.format("{%s}", item));;
			
		}
	}
}
