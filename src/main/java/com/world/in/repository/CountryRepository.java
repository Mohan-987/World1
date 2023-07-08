package com.world.in.repository;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.world.in.dto.CountryData;
import com.world.in.entity.Country;

@Repository
public interface CountryRepository extends JpaRepository<Country,String> {
	//Fetch a Country by name -1
	Optional<Country> findByName(String name);
	//
	Optional<Country> findByCode(String code);

	//Fetch the count of cities in given country  -2 
	int countByCode(String code);
	
	//Fetch the population and life expectancy for people in given country code -3
	Optional<Country> findFirstByOrderByLifeExpectancyDesc();
	
	//Fetch distinct government forms in the world - 4
	@Query("SELECT DISTINCT c.governmentForm FROM Country c")
    Collection<String> findDistinctGovernmentForms();
	
	//Fetch top 10 populated countries in the world - 5
	@Query("SELECT new com.world.in.dto.CountryData(c.name, c.population) FROM Country c ORDER BY c.population DESC")
    Collection<CountryData> findTop10ByOrderByPopulationDesc(PageRequest pageRequest);
	
	//Collection of CountryLanguages - 6
	List<Country> findByRegion(String region);


	//top ten gnp
	Optional<List<Country>> findTop10ByOrderByGnpDesc();

}

