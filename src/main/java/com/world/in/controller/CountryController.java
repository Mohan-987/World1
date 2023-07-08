package com.world.in.controller;

import java.math.BigDecimal;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import com.world.in.entity.City;
import com.world.in.exception.CountryNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.world.in.dto.CountryData;
import com.world.in.dto.CountryDto;
import com.world.in.entity.Country;
import com.world.in.entity.CountryLanguage;
import com.world.in.service.CountryService;

import javax.validation.Valid;
import javax.validation.constraints.Pattern;

@RestController
@RequestMapping(value="/api/countries")
public class CountryController {

	private CountryService cuService;

	@Autowired
	public void setCuService(CountryService cuService) {
		this.cuService = cuService;
	}

	@GetMapping
	public ResponseEntity<List<Country>> getAllCountries() {
		List<Country> allCountries=cuService.getAllCountries();
		ResponseEntity<List<Country>> re=new ResponseEntity<List<Country>>(allCountries,HttpStatus.OK);
		return re;
	}
	@GetMapping(value = "/{name}")
    public ResponseEntity<Country> getCountryByName(@PathVariable String name) {
		Country country=cuService.getOneCountry(name);
		return new ResponseEntity<>(country,HttpStatus.OK);
    }
	 @GetMapping("/highestlifeexpectancy")
	 public ResponseEntity<Country> getCountryWithHighestLifeExpectancy() {
		 Country country = cuService.getCountryWithHighestLifeExpectancy();
		 return new ResponseEntity<>(country, HttpStatus.OK);
	 }
	@GetMapping("/{name}/capital")
	public ResponseEntity<String> getCapitalCityByCountryName(@PathVariable("name") String countryName) {
		City capitalCity = cuService.getCapitalCityByCountryName(countryName);
		return new ResponseEntity<String>(capitalCity.getName(),HttpStatus.OK);
	}
	 @GetMapping("/uniquegovernmentforms")
	 public ResponseEntity<Collection<String>> getDistinctGovernmentForms() {
		Collection<String> governmentForms = cuService.getDistinctGovernmentForms();
		 return new ResponseEntity<>(governmentForms,HttpStatus.OK);
	    }
	 @GetMapping("/toptenpopulated")
	 public ResponseEntity<Collection<CountryData>> getTop10PopulatedCountries() {
		Collection<CountryData> top10PopulatedCountries = cuService.getTop10PopulatedCountries();
		 return new ResponseEntity<>(top10PopulatedCountries, HttpStatus.OK);
	 }
	 @GetMapping(value="/{region}/alllanguages")
     public ResponseEntity<List<CountryLanguage>> getAllLanguagesByRegion(@PathVariable String region) {
		List<CountryLanguage> CL= cuService.getAllLanguagesByRegion(region);
		return new ResponseEntity<List<CountryLanguage>>(CL, HttpStatus.OK);
     }
	 @PatchMapping("/updategnp/{name}")
	 public ResponseEntity<Country> updateGNP(@PathVariable String name, @RequestParam BigDecimal gnp) {
	     Country updatedCountry = cuService.updateGNP(name, gnp);
		 return new ResponseEntity<>(updatedCountry,HttpStatus.OK);
	 }
	 @GetMapping("/{name}/citycount")
     public ResponseEntity<String> getCityCountByCountryName(@PathVariable("name") String countryName) {
		String cityCount = cuService.getCityCountByCountryName(countryName);
		return new ResponseEntity<>(cityCount,HttpStatus.OK);
     }
	 @GetMapping("/{name}/cities")
     public ResponseEntity<List<String>> getCityNamesByCountry(@PathVariable("name") String countryName) {
		List<String> cityNames = cuService.getCityNamesByCountry(countryName);
		return new ResponseEntity<>(cityNames,HttpStatus.OK);
     }

	 @GetMapping("/toptengnp")
     public ResponseEntity<List<CountryDto>> getTopTenGNPCountries() {
		List<CountryDto> countries = cuService.getTopTenGNPCountries();
		return new ResponseEntity<>(countries, HttpStatus.OK);
     }

	 @PatchMapping("/updateheadofstate/{name}")
	 public ResponseEntity<Country> updateHeadOfState(@PathVariable("name") String name, @RequestParam String headOfState) {
		Country country = cuService.updateHeadOfState(name, headOfState);
		return new ResponseEntity<>(country,HttpStatus.OK);

	 }
	@PatchMapping("/updatepopulation/{name}")
     public ResponseEntity<Country> updatePopulation(@PathVariable("name") String name, @RequestParam Integer population) {
		Country country = cuService.updatePopulation(name,population );
		return new ResponseEntity<Country>(country, HttpStatus.OK);
     }
}
