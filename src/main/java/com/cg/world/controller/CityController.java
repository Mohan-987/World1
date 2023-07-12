package com.cg.world.controller;

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

import com.cg.world.dto.CityRegionDto;
import com.cg.world.entity.City;
import com.cg.world.service.CityService;

@RestController
@RequestMapping(value="/api/cities")
public class CityController {

    private CityService cityService;
    @Autowired
    public void setCityService(CityService cityService) {
        this.cityService = cityService;
    }
    @GetMapping
    public ResponseEntity<List<City>> getAllCities() {
		return new ResponseEntity<>(cityService.getAllCities(),HttpStatus.OK);
    }
    @GetMapping( value="/firsttencities/{ch}")
    public ResponseEntity<List<String>> getFirstTenCitiesStartingWithChar(@PathVariable char ch) {
        return new ResponseEntity<>(cityService.getFirstTenCitiesStartingWithChar(ch),HttpStatus.OK);
    }
    @GetMapping("/maxpopulated")
    public ResponseEntity<City> getCityWithMaxPopulation() {
        return new ResponseEntity<>(cityService.getCityWithMaxPopulation(),HttpStatus.OK);
    }
    @GetMapping("/toptenpopulatedcities")
    public ResponseEntity<List<String>> getTop10PopulatedCities() {
        return new ResponseEntity<>(cityService.getTop10PopulatedCityNames(),HttpStatus.OK);
    }
    @GetMapping("/regions")
    public ResponseEntity<Collection<CityRegionDto>> fetchCityNamesAndRegions() {
        return new ResponseEntity<>(cityService.fetchCityNamesAndRegions(), HttpStatus.OK);
    }
    @GetMapping("/districts/{countrycode}")
    public ResponseEntity<List<City>> getCitiesAndDistrictsByCountryCode(@PathVariable String countrycode) {
        return new ResponseEntity<>(cityService.getCitiesAndDistrictsByCountryCode(countrycode),HttpStatus.OK);
    }

    @GetMapping("/districts")
    public ResponseEntity<List<String>> getDistinctDistricts() {
        return new ResponseEntity<>(cityService.getDistinctDistricts(),HttpStatus.OK);
    }
    @GetMapping("/avgpopulation/{districtName}")
    public ResponseEntity<String> getAveragePopulationByDistrict(@PathVariable String districtName) {
        Double averagePopulation = cityService.getAveragePopulationByDistrict(districtName);
        String districtAndPopulation = "District: " + districtName + ", Average Population: " + averagePopulation;
        return new ResponseEntity<>(districtAndPopulation,HttpStatus.OK);
    }
    @PatchMapping("/{nm}/updatedistrict")
    public ResponseEntity<City> updateCityDistrict(@PathVariable String nm, @RequestParam String newDistrict) {
        return new ResponseEntity<>(cityService.updateCityDistrict(nm, newDistrict),HttpStatus.OK);
    }
    @PatchMapping("/updatepopulation/{name}")
    public ResponseEntity<City> updateCityPopulation(@PathVariable("name") String cityName, @RequestParam("population") Integer population) {
        return new ResponseEntity<>(cityService.updatePopulationByCityName(cityName, population),HttpStatus.OK);
    }
}