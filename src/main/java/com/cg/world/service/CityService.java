package com.cg.world.service;


import java.util.Collection;
import java.util.List;

import com.cg.world.dto.CityRegionDto;
import com.cg.world.entity.City;

public interface CityService {
    List<City> getAllCities();
    List<String> getFirstTenCitiesStartingWithChar(char ch);
    City getCityWithMaxPopulation();
    List<String> getTop10PopulatedCityNames();
    List<City> getCitiesAndDistrictsByCountryCode(String countryCode);
	Collection<CityRegionDto> fetchCityNamesAndRegions();
    List<String> getDistinctDistricts();
    Double getAveragePopulationByDistrict(String districtName);
    public City updatePopulationByCityName(String cityName, Integer population);
    public City updateCityDistrict(String cityName, String newDistrict);
}
