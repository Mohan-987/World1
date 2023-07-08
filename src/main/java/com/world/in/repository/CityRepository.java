package com.world.in.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.world.in.entity.City;

import javax.swing.text.html.Option;

@Repository
public interface CityRepository extends JpaRepository<City,Integer> {
    List<City> findAll();
    Optional<List<City>> findByNameStartingWith(char ch);
    @Query("SELECT c FROM City c WHERE c.population = (SELECT MAX(c.population) FROM City c)")
    Optional<City> findCityWithMaxPopulation();
    List<City> findTop10ByOrderByPopulationDesc();
    @Query("SELECT c.name, cn.region FROM City c INNER JOIN c.country cn")
    Optional<List<Object[]>> getAllCityRegion();
    Optional<List<City>> findByCountryCode(String countryCode);
    @Query("SELECT DISTINCT c.district FROM City c")
    Optional<List<String> >findDistinctDistricts();
    @Query("SELECT AVG(c.population) FROM City c WHERE c.district = :districtName")
    Optional<Double> findAveragePopulationByDistrict(@Param("districtName") String districtName);
    Optional<City> findByName(String name);
    }
