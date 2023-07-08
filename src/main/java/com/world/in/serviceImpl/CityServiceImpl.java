package com.world.in.serviceImpl;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import com.world.in.exception.CountryNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.world.in.dto.CityRegionDto;
import com.world.in.entity.City;
import com.world.in.exception.CityNotFoundException;
import com.world.in.repository.CityRepository;
import com.world.in.repository.CountryRepository;
import com.world.in.service.CityService;

@Service
public class CityServiceImpl implements CityService {

    private CityRepository cityRepository;

    @Autowired
    public CityServiceImpl(CityRepository cityRepository) {
        this.cityRepository = cityRepository;
    }
    CountryRepository countryRepository;
    @Autowired
    public void setCountryRepository(CountryRepository countryRepository) {
		this.countryRepository = countryRepository;
	}
    @Override
    public List<City> getAllCities() {
        List<City> cities = cityRepository.findAll();
        if(cities.isEmpty())
            throw new CityNotFoundException("Cities Not Found! Check The Database");
        return cities;
    }
    @Override
    public List<String> getFirstTenCitiesStartingWithChar(char ch) {
        List<City> cities = cityRepository.findByNameStartingWith(ch).orElseThrow(()->new CityNotFoundException("Not a valid input. Input should be a letter"));
        List<String> cityNames = new ArrayList<>();
        int count = 0;
        for (City city : cities) {
            cityNames.add(city.getName());
            count++;
            if (count >= 10) break;
        }
        return cityNames;
    }
    @Override
    public City getCityWithMaxPopulation() {
        City city = cityRepository.findCityWithMaxPopulation().orElseThrow(()->new CityNotFoundException("City Not Found! Unable To Get Max Population"));
        return city;
    }
    @Override
    public List<String> getTop10PopulatedCityNames() {
        List<City> top10Cities = cityRepository.findTop10ByOrderByPopulationDesc();
        return top10Cities.stream().map(City::getName).collect(Collectors.toList());
    }
    @Override
    public List<Object[]> getAllCityRegion() {
        List<Object[]> allCities =cityRepository.getAllCityRegion().orElseThrow(()->new CityNotFoundException("No Cities Found"));
        return allCities;
    }
    @Override
    public Collection<CityRegionDto> fetchCityNamesAndRegions() {
        List<City> cities = cityRepository.findAll();
        List<CityRegionDto> cityRegionDtos = new ArrayList<>();
        for (City city : cities) {
            String region = "";
            if (city.getCountry() != null && city.getCountry().getRegion() != null) {
                region = city.getCountry().getRegion();
            }
            cityRegionDtos.add(new CityRegionDto(city.getName(), region));
        }

        return cityRegionDtos;
    }
    @Override
    public List<City> getCitiesAndDistrictsByCountryCode(String countryCode) {
        List<City> cities = cityRepository.findByCountryCode(countryCode).orElseThrow(()->new CountryNotFoundException("Country Code Not Found! Check Once"));
        List<City> citiesAndDistricts = new ArrayList<>();
        for (City city : cities) {
            City cityAndDistrict = new City();
            cityAndDistrict.setName(city.getName());
            cityAndDistrict.setDistrict(city.getDistrict());
            citiesAndDistricts.add(cityAndDistrict);
        }
        return citiesAndDistricts;
    }

    @Override
    public List<String> getDistinctDistricts() {
        List<String> districts = cityRepository.findDistinctDistricts().orElseThrow(()->new CityNotFoundException("No Districts Found"));
        return districts;
    }
    @Override
    public Double getAveragePopulationByDistrict(String districtName) {
        Double population = cityRepository.findAveragePopulationByDistrict(districtName).orElseThrow(()->new CityNotFoundException("District Name Not Found! Check Once Again"));
        return population;
    }
    @Override
    public City updateCityDistrict(String cityName, String newDistrict) {
        City city = cityRepository.findByName(cityName).orElseThrow(()->new CityNotFoundException("City Not Found "+cityName+" Check Once!"));
        city.setDistrict(newDistrict);
        cityRepository.save(city);
        return city;
    }

    @Override
    public City updatePopulationByCityName(String cityName, Integer population) {
        City city = cityRepository.findByName(cityName).orElseThrow(()->new CityNotFoundException("City Not Found "+cityName+" Check Once!"));
        city.setPopulation(population);
        return cityRepository.save(city);

    }
}
    

