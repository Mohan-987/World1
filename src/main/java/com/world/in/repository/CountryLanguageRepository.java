package com.world.in.repository;

import java.util.List;

import com.world.entity.enums.IsOfficial;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.world.in.entity.Country;
import com.world.in.entity.CountryLanguage;

@Repository
public interface CountryLanguageRepository extends JpaRepository<CountryLanguage, String> {
	@Query("SELECT DISTINCT cl.language FROM CountryLanguage cl")
    List<String> findDistinctLanguage();
	
	List<CountryLanguage> findByLanguage(String language);
	//maxspoken
	List<CountryLanguage> findByCountryCode(String countryCode);
	
	List<CountryLanguage> findAllByIsOfficial(IsOfficial isOfficial);
	
	List<CountryLanguage> findByCountryCodeAndIsOfficial(String countryCode, IsOfficial isOfficial);
//	 @Query(value = "SELECT cl.language, cl.percentage FROM CountryLanguage cl " +
//	            "WHERE cl.country.code = :countryCode " +
//	            "AND cl.percentage = (SELECT MAX(cl2.percentage) FROM CountryLanguage cl2 WHERE cl2.country.code = :countryCode)")
//	    Object[] findLanguageWithMaxPercentage(String countryCode);
@Query(value = "SELECT cl.language, cl.percentage FROM CountryLanguage cl " +
		"WHERE cl.country.code = :countryCode " +
		"AND cl.percentage = (SELECT MAX(cl2.percentage) FROM CountryLanguage cl2 WHERE cl2.country.code = :countryCode)")
Object[] findLanguageWithMaxPercentage(String countryCode);
	 
	CountryLanguage findByCountryCodeAndLanguage(String countryCode, String language);
	
    List<CountryLanguage> findByCountryIn(List<Country> countries);
   
//    @Query("SELECT DISTINCT cl.language FROM CountryLanguage cl JOIN cl.country c WHERE c.region = ?1")
//    List<String> findDistinctLanguagesByRegion(String region);

    
  //update percentage
    List<CountryLanguage> findDistinctByCountryCodeOrderByPercentageDesc(String countryCode);

   

    

}
