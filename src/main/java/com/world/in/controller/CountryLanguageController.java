package com.world.in.controller;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import com.world.in.serviceImpl.CountryLanguageServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.world.in.entity.CountryLanguage;
import com.world.in.service.CountryLanguageService;

import org.springframework.web.bind.annotation.RequestBody;

import javax.validation.Valid;

//Fetch all unique languages in world 
@RestController
@RequestMapping("/api/countrylang")
public class CountryLanguageController {
	private CountryLanguageService countryLanguageService;
    @Autowired
	public void setCountryLanguageService(CountryLanguageService countryLanguageService) {
		this.countryLanguageService = countryLanguageService;
	}
	@GetMapping
	public ResponseEntity<List<String>> getAllUniqueLanguages() {
		List<String> uniqueLanguages = countryLanguageService.getAllUniqueLanguages();
		return new ResponseEntity<>(uniqueLanguages,HttpStatus.OK);
	}
	@GetMapping("/{countrycode}")
	public ResponseEntity<List<CountryLanguage>> getLanguagesByCountryCode(@PathVariable("countrycode") String countryCode) {
		List<CountryLanguage> languages = countryLanguageService.getLanguagesByCountryCode(countryCode);
		return new ResponseEntity<>(languages,HttpStatus.OK);
	}
	@GetMapping("/allofficial")
    public ResponseEntity<List<CountryLanguage>> getAllOfficialLanguages() {
		List<CountryLanguage> allOfficialLang = countryLanguageService.getAllOfficialLanguages();
		return new ResponseEntity<>(allOfficialLang,HttpStatus.OK);
    }
	@GetMapping("/maxspokenlang")
	public ResponseEntity<Map<String, String>> getMaxSpokenLanguageByCountryCode() {
		Map<String, String> countryLanguages = countryLanguageService.getLanguageWithMaxPercentageByCountryCode();
		return new ResponseEntity<>(countryLanguages,HttpStatus.ACCEPTED);
	}
	@GetMapping("/unofficial/{countrycode}")
    public ResponseEntity<List<CountryLanguage>> getUnofficialLanguagesByCountryCode(@PathVariable String countrycode) {
		List<CountryLanguage> allUnOfficialLang= countryLanguageService.getUnofficialLanguagesByCountryCode(countrycode);
		return new ResponseEntity<>(allUnOfficialLang,HttpStatus.OK);
	}
	@GetMapping("/maxspokenlang/{countrycode}")
    public ResponseEntity<String> getMaxSpokenLanguage(@PathVariable("countrycode") String countryCode) {
        String result = countryLanguageService.getMaxPercentageLanguageByCountryCode(countryCode);
		return new ResponseEntity<String>(result,HttpStatus.OK);
    }
	@PatchMapping("/setofficial/{ctycode}/{lang}")
	public ResponseEntity<String> setOfficial(@PathVariable("ctycode") String countryCode, @PathVariable("lang") String language, @RequestParam char isOfficial) {
		String result = countryLanguageService.updateOfficialByCountryAndLanguage(countryCode, language, isOfficial);
		return new ResponseEntity<>(result,HttpStatus.OK);
	}

	@PatchMapping("/updatepercentage/{ctycode}/{lang}")
	public ResponseEntity<String> updatePercentage(@PathVariable("ctycode") String ctycode, @PathVariable("lang") String lang, @RequestParam BigDecimal percentage) {
	    String result = countryLanguageService.updatePercentageByCountryAndLanguage(ctycode,lang,percentage);
	    return new ResponseEntity<>(result, HttpStatus.OK);
	}
}
