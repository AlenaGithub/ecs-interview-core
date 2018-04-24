package com.ecs.interview;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.ecs.interview.model.Anagram;
import com.ecs.interview.service.AnagramCalculator;

@RestController
public class AnagramController {

	@Autowired
	AnagramCalculator calculater;

	@RequestMapping(method = RequestMethod.GET, value = "/word/{word}")
	@ResponseStatus(value = HttpStatus.OK)
	public Anagram getAnagrams(@PathVariable("word") String inputWord) {

		calculater = new AnagramCalculator();
		Anagram anagram = calculater.getAnagram(inputWord);

		return anagram;
	}
}