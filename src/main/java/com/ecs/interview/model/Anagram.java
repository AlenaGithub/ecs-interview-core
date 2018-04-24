package com.ecs.interview.model;

import java.util.TreeSet;

public class Anagram {

	private String word = null;
	private TreeSet<String> anagrams;
	
	public Anagram(String word) {
		this.word = word;
		anagrams = new TreeSet<>();
	}
	
	public String getWord() {
		return word;
	}

	public void setWord(String word) {
		this.word = word;
	}

	public TreeSet<String> getAnagrams() {
		return anagrams;
	}
	public void setAnagrams(TreeSet<String> anagrams) {
		this.anagrams = anagrams;
	}
}
