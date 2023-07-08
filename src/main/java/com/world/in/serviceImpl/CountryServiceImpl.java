package com.world.in.serviceImpl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import com.world.in.exception.CountryLanguageNotFoundException;
import com.world.in.exception.CountryNotFoundException;
import com.world.in.repository.CityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.world.in.dto.CountryData;
import com.world.in.dto.CountryDto;
import com.world.in.entity.City;
import com.world.in.entity.Country;
import com.world.in.entity.CountryLanguage;
import com.world.in.repository.CountryLanguageRepository;
import com.world.in.repository.CountryRepository;
import com.world.in.service.CountryService;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

@Service
public class CountryServiceImpl implements CountryService{
	private CountryRepository countryRepository;
    private  CountryLanguageRepository countryLanguageRepository;
	private CityRepository cityRepository;
	@Autowired
	public void setCountryRepository(CountryRepository countryRepository) {
		this.countryRepository = countryRepository;
	}
	@Autowired
	public void setLanguageRepository(CountryLanguageRepository languageRepository) {
		this.countryLanguageRepository = languageRepository;
	}
	@Autowired
	public void setCityRepository(CityRepository cityRepository) {this.cityRepository = cityRepository;}
	@Override
	public List<Country> getAllCountries() {
		List<Country> allCountries = countryRepository.findAll();
		if(allCountries.isEmpty()) throw new CountryNotFoundException("No Countries Found! Check Once");
		return allCountries;
	}
	@Override
	public Country getOneCountry(String name) {
		 Country country = countryRepository.findByName(name).orElseThrow(()->new CountryNotFoundException("Country Name Not Found "+name+" Check Once!"));
		 return country;
		 
	}
	@Override
	public String populationLifeExpectency(String countryCode)
	{
		Country country = countryRepository.findByCode(countryCode).orElseThrow(()->new CountryNotFoundException("Country Code Not Found"));
		String s;
		String s1 = Integer.toString(country.getPopulation());
		String s2 = String.valueOf(country.getLifeExpectancy());
		s = "Country Code: "+countryCode+" "+"Population: "+s1+" Life Expectancy: "+ s2;
		return s;
	}
	@Override
	public City getCapitalCityByCountryName(String countryName) {
		Country country = countryRepository.findByName(countryName).orElseThrow(()->new CountryNotFoundException("Country Name Not Found "+countryName+" Check Once!"));
		return cityRepository.findById(country.getCapital()).get();
	}
	@Override
	public Country getCountryWithHighestLifeExpectancy() {
		 Country country =countryRepository.findFirstByOrderByLifeExpectancyDesc().orElseThrow(()->new CountryNotFoundException("Country Not Found! Unable To get Highest Expectancy"));
		 return country;
	}
	@Override
	public Collection<String> getDistinctGovernmentForms() {
		 List<Country> countries = countryRepository.findAll();
		if(countries.isEmpty()) throw new CountryNotFoundException("Unable To Get Distinct Governments Forms!");
	        Set<String> governmentForms = new HashSet<>();
	        for (Country country : countries) {
	            governmentForms.add(country.getGovernmentForm());
	        }
	        return governmentForms;
	    }
	@Override
	public Collection<CountryData> getTop10PopulatedCountries() {
	    PageRequest pageRequest = PageRequest.of(0, 10); // Create a PageRequest object with page number 0 and size 10
	    Collection<CountryData> countries = countryRepository.findTop10ByOrderByPopulationDesc(pageRequest);
		if(countries.isEmpty()) throw new CountryNotFoundException("No Populations Found");
	    List<CountryData> countryDataList = new ArrayList<>();
		for (CountryData country : countries) {
	        CountryData countryData = new CountryData(country.getName(), country.getPopulation());
	        countryDataList.add(countryData);
	    }
		return countryDataList;
	}
	@Override
	public List<CountryLanguage> getAllLanguagesByRegion(String region) {
	    List<Country> countries = countryRepository.findByRegion(region);
	    if(countries.isEmpty())
			throw new CountryNotFoundException("No Languages Found! Check Once Country Region ");
		List<CountryLanguage> allLanguages = new ArrayList<>();
	        for (Country country : countries) {
	            List<CountryLanguage> countryLanguages = country.getCountryLanguages();
	            if (countryLanguages != null && !countryLanguages.isEmpty()) {
	                allLanguages.addAll(countryLanguages);
	            }
	        }
	    return allLanguages;
	}
@Override
  public Country updateGNP(String name, BigDecimal gnp) {
			Country country = countryRepository.findByName(name).orElseThrow(()->new CountryNotFoundException("Country Name Not Found"));
			country.setGnp(gnp);
            return countryRepository.save(country);
    }
	@Override
    public String getCityCountByCountryName(String countryName) {
		Country country = countryRepository.findByName(countryName).orElseThrow(() -> new CountryNotFoundException("Country Name Not Found"));
		int cityCount = country.getCities().size();
		return "Total count of cities in " + countryName + ": " + cityCount;
	}
	@Override
    public List<String> getCityNamesByCountry(String countryName) {
        Country country = countryRepository.findByName(countryName).orElseThrow(()->new CountryNotFoundException("Country Name Not Found"));
		List<String> cityNames = country.getCities().stream()
                .map(City::getName)
                .collect(Collectors.toList());
		return cityNames;
    }
	@Override
    public List<CountryDto> getTopTenGNPCountries() {
        List<Country> countries = countryRepository.findTop10ByOrderByGnpDesc().orElseThrow(()->new CountryNotFoundException("Countries Not Found! Check Once"));
        return countries.stream()
                .map(country -> new CountryDto(country.getName(), country.getGnp()))
                .collect(Collectors.toList());
    }
	@Override
	public Country updateHeadOfState(String countryCode, String newHeadOfState) {
	    Country country = countryRepository.findByName(countryCode).orElseThrow(()->new CountryNotFoundException("Country Name Not Found"));
		country.setHeadOfState(newHeadOfState);
	        return countryRepository.save(country);
	}
	//update pop
	@Override
    public Country updatePopulation(String countryCode, Integer newPopulation) {

        Country country = countryRepository.findByName(countryCode).orElseThrow(()->new CountryNotFoundException("Country Name Not Found"));
        country.setPopulation(newPopulation);
        countryRepository.save(country);
        return country;
    }
	//update percentage
}