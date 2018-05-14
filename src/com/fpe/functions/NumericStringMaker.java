package com.fpe.functions;

import java.util.HashMap;
import java.util.Map;

public class NumericStringMaker {
	private static final char[] ALPHA_NUMERIC_SET = {'0','1','2','3','4','5','6','7','8','9',
			'a','b','c','d','e','f','g','h','i','j','k','l','m','n','o','p','q','r','s','t','u','v','w','x','y','z',
			'A','B','C','D','E','F','G','H','I','J','K','L','M','N','O','P','Q','R','S','T','U','V','W','X','Y','Z'};
	private static final Map<Character, Integer> CHAR_TO_INT_MAP = new HashMap<>();
	
	static {
		for(int i = 0; i < ALPHA_NUMERIC_SET.length; i++)
			CHAR_TO_INT_MAP.put(ALPHA_NUMERIC_SET[i], i);
	}
	
	public static int get(char c) {
		return CHAR_TO_INT_MAP.get(c);
	}
}
