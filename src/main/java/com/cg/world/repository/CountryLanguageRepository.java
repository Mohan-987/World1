package com.cg.world.repository;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

import com.cg.world.entity.enums.IsOfficial;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.cg.world.entity.CountryLanguage;


@Repository
public interface CountryLanguageRepository extends JpaRepository<CountryLanguage, String> {
	@Query("SELECT DISTINCT cl.language FROM CountryLanguage cl")
    Optional<List<String>> findDistinctLanguage();
	List<CountryLanguage> findByCountryCode(String countryCode);
	List<CountryLanguage> findAllByIsOfficial(IsOfficial isOfficial);
	List<CountryLanguage> findByCountryCodeAndIsOfficial(String countryCode, IsOfficial isOfficial);
	Optional<CountryLanguage> findByCountryCodeAndLanguage(String countryCode, String language);
	@Modifying
	@Query("UPDATE CountryLanguage cl SET cl.percentage = :percentage WHERE cl.country.code = :countryCode AND cl.language = :language")
	void updatePercentageByCountryCodeAndLanguage(String countryCode, String language,BigDecimal percentage);
}
