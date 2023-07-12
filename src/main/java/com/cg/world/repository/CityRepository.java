package com.cg.world.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.cg.world.entity.City;

@Repository
public interface CityRepository extends JpaRepository<City,Integer> {
    @Query("SELECT c.name FROM City c WHERE c.name LIKE ?1% ORDER BY c.name")
    Optional<List<String>> findFirstTenCitiesStartingWithChar(char ch);
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
