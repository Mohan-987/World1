package com.world.in.service;


import java.util.Collection;
import java.util.List;

import com.world.in.entity.City;

public interface CityService {
    List<City> getAllCities();
    List<String> getFirstTenCitiesStartingWithChar(char ch);
    City getCityWithMaxPopulation();
    List<String> getTop10PopulatedCityNames();
   List<Object[]> getAllCityRegion();
    List<City> getCitiesAndDistrictsByCountryCode(String countryCode);
	Collection<com.world.in.dto.CityRegionDto> fetchCityNamesAndRegions();
    List<String> getDistinctDistricts();
    Double getAveragePopulationByDistrict(String districtName);
  
    public City updatePopulationByCityName(String cityName, Integer population);
    public City updateCityDistrict(String cityName, String newDistrict);
}
