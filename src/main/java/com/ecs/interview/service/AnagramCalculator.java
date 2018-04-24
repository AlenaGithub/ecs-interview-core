package com.ecs.interview.service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.TreeSet;

import com.ecs.interview.model.Anagram;

public class AnagramCalculator {

	// @Value("${app.dictionary.url}")
	private String dictionaryUrl = "https://users.cs.duke.edu/~ola/ap/linuxwords";

	//this can be removed after local testing
	public static void main(String[] args) {
		
		AnagramCalculator tester = new AnagramCalculator();
		String word = "abc";
		TreeSet<String> t = tester.getAnagrams(word);

		for (String w : t) {
			System.out.println(w);
		}
	}

	public Anagram getAnagram(String word) {
		Anagram anagram = new Anagram(word);
		anagram.setAnagrams(calculateAnagrams(word));
		return anagram;
	}

	/**
	 * logic service implementation 
	 * @param word
	 * @return
	 */
	private TreeSet<String> calculateAnagrams(String word) {

		// logic go here
		URL url = null;
		try {
			url = new URL(dictionaryUrl);
		} catch (MalformedURLException e) {
			e.printStackTrace();
		}

		TreeSet<String> dict = new TreeSet<>();
		BufferedReader in;
		try {
			in = new BufferedReader(new InputStreamReader(url.openStream()));
			String inputLine;
			while ((inputLine = in.readLine()) != null) {
				// System.out.println(inputLine);
				dict.add(inputLine);
			}
			in.close();

		} catch (IOException e) {
			e.printStackTrace();
		}

		TreeSet<String> tWords = new TreeSet<>();

		// check if the word in dictionary
		boolean isWordInDic = dict.contains(word);

		// only generate anagrams when the word exist in dictionary
		if (isWordInDic) {
			tWords = getAnagrams(word);
		}
		return tWords;
	}

	/**
	 * generate all anagrams for the input word
	 * 
	 * @param word
	 * @return anagrams
	 */
	private List<String> getAnagrams2(String word) {
		List<String> lWords = new LinkedList<>();
		char[] nums = word.toCharArray();
		List<List<Character>> result = new ArrayList<>();
		LinkedList<Character> tempList = new LinkedList<>();
		boolean[] used = new boolean[nums.length];
		helper(result, tempList, nums, used);
		StringBuffer sb = null;
		for (List<Character> l : result) {
			sb = new StringBuffer();
			for (Character c : l) {
				sb.append(c);
			}
			if (!word.equals(sb.toString())) {
				lWords.add(sb.toString());
			}
		}
		return lWords;
	}
	
	private TreeSet<String> getAnagrams(String word) {
		TreeSet<String> tWords = new TreeSet<>();
		char[] nums = word.toCharArray();
		List<List<Character>> result = new ArrayList<>();
		LinkedList<Character> tempList = new LinkedList<>();
		boolean[] used = new boolean[nums.length];
		helper(result, tempList, nums, used);
		StringBuffer sb = null;
		for (List<Character> l : result) {
			sb = new StringBuffer();
			for (Character c : l) {
				sb.append(c);
			}
			if (!word.equals(sb.toString())) {
				tWords.add(sb.toString());
			}
		}
		return tWords;
	}

	/**
	 * backtracking help method
	 * 
	 * @param result
	 * @param tempList
	 * @param nums
	 * @param used
	 */
	private void helper(List<List<Character>> result, LinkedList<Character> tempList, char[] nums, boolean[] used) {
		if (tempList.size() == nums.length) {
			result.add(new LinkedList(tempList));
			return;
		}
		for (int i = 0; i < nums.length; i++) {
			if (!used[i]) {
				tempList.add(nums[i]);
				used[i] = true;
				helper(result, tempList, nums, used);
				tempList.removeLast();
				used[i] = false;
			}
		}
	}
}
