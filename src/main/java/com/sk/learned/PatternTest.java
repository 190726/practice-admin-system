package com.sk.learned;

import java.util.Arrays;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Stream;

public class PatternTest {
	
	public static void main(String[] args) {
		String sampleStr = "test테스트";
		String sampleStr2 = "테스트";
		//1. 한번만 할경우
		boolean match = Pattern.matches("^[가-힣]*$", sampleStr);
		System.out.println(match);
		
		//2. 하나의 패턴에 대해 여러번 사용해야 할 경우는 정규표현식을 컴파일 하는 것이 좋음
		Pattern pattern = Pattern.compile("^[가-힣]*$");
		Matcher matchSampleStr = pattern.matcher(sampleStr);
		Matcher matchSampleStr2 = pattern.matcher(sampleStr2);
		System.out.println(matchSampleStr.matches());
		System.out.println(matchSampleStr2.matches());
		
		//3. 스트림 활용
		Stream<String> strings = Arrays.asList(sampleStr, sampleStr2).stream();
		strings.filter(pattern.asPredicate()).forEach(System.out::println);;
	}
}
