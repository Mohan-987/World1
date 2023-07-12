package com.cg.world.repository;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import com.cg.world.entity.Country;

@Repository
public interface CountryRepository extends JpaRepository<Country,String> {
	Optional<Country> findByName(String name);
	Optional<Country> findByCode(String code);
	Optional<Country> findFirstByOrderByLifeExpectancyDesc();
	@Query("SELECT c.name, c.population FROM Country c ORDER BY c.population DESC")
	Collection<Object[]> findTop10ByOrderByPopulationDesc(PageRequest pageRequest);
	List<Country> findByRegion(String region);
	Optional<List<Country>> findTop10ByOrderByGnpDesc();

}

