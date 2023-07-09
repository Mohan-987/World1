package com.world.in.repository;

import java.util.List;
import java.util.Optional;

import com.world.in.entity.Country;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.world.in.entity.City;

import javax.swing.text.html.Option;

@Repository
public interface CityRepository extends JpaRepository<City,Integer> {
    Optional<List<City>> findByNameStartingWith(char ch);
    @Query("SELECT c FROM City c WHERE c.population = (SELECT MAX(c.population) FROM City c)")
    Optional<City> findCityWithMaxPopulation();
    Optional<List<City>> findTop10ByOrderByPopulationDesc();
    Optional<List<City>> findByCountryCode(String countryCode);
    @Query("SELECT DISTINCT c.district FROM City c")
    Optional<List<String> >findDistinctDistricts();
    @Query("SELECT AVG(c.population) FROM City c WHERE c.district = :districtName")
    Optional<Double> findAveragePopulationByDistrict(@Param("districtName") String districtName);
    Optional<City> findByName(String name);
    }
