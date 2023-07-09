package com.world.in.repository;

import java.util.List;
import java.util.Optional;

import com.world.entity.enums.IsOfficial;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.world.in.entity.Country;
import com.world.in.entity.CountryLanguage;

@Repository
public interface CountryLanguageRepository extends JpaRepository<CountryLanguage, String> {
	@Query("SELECT DISTINCT cl.language FROM CountryLanguage cl")
    Optional<List<String>> findDistinctLanguage();
	List<CountryLanguage> findByCountryCode(String countryCode);
	List<CountryLanguage> findAllByIsOfficial(IsOfficial isOfficial);
	List<CountryLanguage> findByCountryCodeAndIsOfficial(String countryCode, IsOfficial isOfficial);
	Optional<CountryLanguage> findByCountryCodeAndLanguage(String countryCode, String language);
}
