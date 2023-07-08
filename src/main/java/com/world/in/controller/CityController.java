package com.world.in.controller;

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

import com.world.in.dto.CityRegionDto;
import com.world.in.entity.City;
import com.world.in.service.CityService;

@RestController
@RequestMapping(value="/api/cities")
public class CityController {

    private final CityService cityService;

    @Autowired
    public CityController(CityService cityService) {
        this.cityService = cityService;
    }

    @GetMapping
    public ResponseEntity<List<City>> getAllCities() {
        List<City> cities  = cityService.getAllCities();
		return new ResponseEntity<List<City>>(cities,HttpStatus.OK);
    }

    @GetMapping( value="/firsttencities/{ch}")
    public ResponseEntity<List<String>> getFirstTenCitiesStartingWithChar(@PathVariable char ch) {
        List<String> cities = cityService.getFirstTenCitiesStartingWithChar(ch);
        return ResponseEntity.ok(cities);
    }
    @GetMapping("/maxpopulated")
    public ResponseEntity<City> getCityWithMaxPopulation() {
        City city = cityService.getCityWithMaxPopulation();
        return new ResponseEntity<>(city,HttpStatus.OK);
    }
    @GetMapping("/toptenpopulatedcities")
    public ResponseEntity<List<String>> getTop10PopulatedCities() {
        List<String> cities = cityService.getTop10PopulatedCityNames();
        return new ResponseEntity<>(cities,HttpStatus.OK);
    }
    @GetMapping("/regions")
    public ResponseEntity<Collection<CityRegionDto>> fetchCityNamesAndRegions() {
        Collection<CityRegionDto> cityRegionDtos = cityService.fetchCityNamesAndRegions();
        return new ResponseEntity<>(cityRegionDtos, HttpStatus.OK);
    }
    @GetMapping("/districts/{countrycode}")
    public ResponseEntity<List<City>> getCitiesAndDistrictsByCountryCode(@PathVariable String countrycode) {
        List<City> cityDtoList = cityService.getCitiesAndDistrictsByCountryCode(countrycode);
        return ResponseEntity.ok(cityDtoList);
    }

    @GetMapping("/districts")
    public List<String> getDistinctDistricts() {
        return cityService.getDistinctDistricts();
    }
    @GetMapping("/avgpopulation/{districtName}")
    public ResponseEntity<String> getAveragePopulationByDistrict(@PathVariable String districtName) {
        Double averagePopulation = cityService.getAveragePopulationByDistrict(districtName);
        String districtAndPopulation = "District: " + districtName + ", Average Population: " + averagePopulation;
        return new ResponseEntity<>(districtAndPopulation,HttpStatus.OK);
    }
    @PatchMapping("/{nm}/updatedistrict")
    public ResponseEntity<City> updateCityDistrict(@PathVariable String nm, @RequestParam String newDistrict) {
        City updatedCity = cityService.updateCityDistrict(nm, newDistrict);
        return new ResponseEntity<>(updatedCity,HttpStatus.OK);
    }
    
    @PatchMapping("/updatepopulation/{name}")
    public ResponseEntity<City> updateCityPopulation(@PathVariable("name") String cityName, @RequestParam("population") Integer population) {
        City updatedCity = cityService.updatePopulationByCityName(cityName, population);
        return ResponseEntity.ok(updatedCity);
    }
}