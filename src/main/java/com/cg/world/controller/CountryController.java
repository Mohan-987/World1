package com.cg.world.controller;

import java.math.BigDecimal;
import java.util.Collection;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.cg.world.dto.CountryData;
import com.cg.world.dto.CountryDto;
import com.cg.world.entity.Country;
import com.cg.world.entity.CountryLanguage;
import com.cg.world.service.CountryService;

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
		return new ResponseEntity<>(cuService.getAllCountries(),HttpStatus.OK);
	}
	@GetMapping(value = "/{name}")
    public ResponseEntity<Country> getCountryByName(@PathVariable String name) {
		return new ResponseEntity<>(cuService.getOneCountry(name),HttpStatus.OK);
    }
	@GetMapping("/{countryCode}/population")
	public ResponseEntity<String> getPopulationAndLifeExpectancy(@PathVariable String countryCode) {
		return new ResponseEntity<>(cuService.populationLifeExpectancy(countryCode),HttpStatus.OK);
	}
	 @GetMapping("/highestlifeexpectancy")
	 public ResponseEntity<Country> getCountryWithHighestLifeExpectancy() {
		 return new ResponseEntity<>(cuService.getCountryWithHighestLifeExpectancy(), HttpStatus.OK);
	 }
	 @GetMapping("/{name}/capital")
	 public ResponseEntity<String> getCapitalCityByCountryName(@PathVariable("name") String countryName) {
		return new ResponseEntity<>(cuService.getCapitalCityByCountryName(countryName).getName(),HttpStatus.OK);
	}
	 @GetMapping("/uniquegovernmentforms")
	 public ResponseEntity<Collection<String>> getDistinctGovernmentForms() {
		return new ResponseEntity<>(cuService.getDistinctGovernmentForms(),HttpStatus.OK);
	}
	@GetMapping("/toptenpopulated")
	public ResponseEntity<Collection<CountryData>> getTopTenPopulatedCountries() {
	return new ResponseEntity<>(cuService.getTop10PopulatedCountries(),HttpStatus.OK);
	}
	@GetMapping(value="/{region}/alllanguages")
	public ResponseEntity<List<CountryLanguage>> getAllLanguagesByRegion(@PathVariable String region) {
		return new ResponseEntity<>(cuService.getAllLanguagesByRegion(region), HttpStatus.OK);
	}
	@PatchMapping("/updategnp/{name}")
	public ResponseEntity<Country> updateGNP(@PathVariable String name, @RequestParam BigDecimal gnp) {
		return new ResponseEntity<>(cuService.updateGNP(name, gnp),HttpStatus.OK);
	 }
	 @GetMapping("/{name}/citycount")
	 public ResponseEntity<String> getCityCountByCountryName(@PathVariable("name") String countryName) {
		return new ResponseEntity<>(cuService.getCityCountByCountryName(countryName),HttpStatus.OK);
	}
	 @GetMapping("/{name}/cities")
     public ResponseEntity<List<String>> getCityNamesByCountry(@PathVariable("name") String countryName) {
		return new ResponseEntity<>(cuService.getCityNamesByCountry(countryName),HttpStatus.OK);
     }
	 @GetMapping("/toptengnp")
     public ResponseEntity<List<CountryDto>> getTopTenGNPCountries() {
		return new ResponseEntity<>( cuService.getTopTenGNPCountries(), HttpStatus.OK);
     }
	 @PatchMapping("/updateheadofstate/{name}")
	 public ResponseEntity<Country> updateHeadOfState(@PathVariable("name") String name, @RequestParam String headOfState) {
		return new ResponseEntity<>(cuService.updateHeadOfState(name, headOfState),HttpStatus.OK);
	 }
	@PatchMapping("/updatepopulation/{name}")
     public ResponseEntity<Country> updatePopulation(@PathVariable("name") String name, @RequestParam Integer population) {
		return new ResponseEntity<>(cuService.updatePopulation(name,population ), HttpStatus.OK);
     }
}
