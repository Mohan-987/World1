package com.world.in.service;

import java.math.BigDecimal;
import java.util.Collection;
import java.util.List;

import com.world.in.dto.CountryData;
import com.world.in.dto.CountryDto;
import com.world.in.entity.City;
import com.world.in.entity.Country;
import com.world.in.entity.CountryLanguage;

public interface CountryService {
	
		public List<Country> getAllCountries();
		public Country getOneCountry(String name);
		public Country getCountryWithHighestLifeExpectancy(); 
		public String populationLifeExpectancy(String countryCode);
	    City getCapitalCityByCountryName(String countryName);
		public Collection<String> getDistinctGovernmentForms();
		public Collection<CountryData> getTop10PopulatedCountries();
		List<CountryLanguage> getAllLanguagesByRegion(String region);
		Country updateGNP(String name, BigDecimal gnp);
		public String getCityCountByCountryName(String countryName);
		public List<String> getCityNamesByCountry(String countryName);
		public List<CountryDto> getTopTenGNPCountries();
		public Country updateHeadOfState(String countryCode, String newHeadOfState);
		public Country updatePopulation(String countryCode, Integer newPopulation);
		
}
