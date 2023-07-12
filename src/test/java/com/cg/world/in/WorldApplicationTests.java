package com.cg.world.in;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import com.cg.world.service.CountryLanguageServiceImpl;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import com.cg.world.service.CityServiceImpl;
import com.cg.world.service.CountryServiceImpl;

import java.math.BigDecimal;

@SpringBootTest
class WorldApplicationTests {
	@Autowired
	private CityServiceImpl service;
	public void setService(CityServiceImpl service) {
		this.service = service;
	}
	private CountryServiceImpl countryService;
	@Autowired
	public void setCountryService(CountryServiceImpl countryService) {
		this.countryService = countryService;
	}
	private CountryLanguageServiceImpl countryLanguageService;
	@Autowired
	public void setCountryLanguageService(CountryLanguageServiceImpl countryLanguageService) {
		this.countryLanguageService = countryLanguageService;
	}
	@Test
	 void CityTest() {
		assertNotNull(service.getAllCities(), "error");
	}
	@Test
	 void CityTopTen() {
		String r = "[a-z]";
		assertNotNull(service.getFirstTenCitiesStartingWithChar(r.charAt(0)),"Error");
	}
	@Test
	 void CityAllRegion() {
		assertNotNull(service.fetchCityNamesAndRegions(),"Error");
	}
	@Test
	 void CityDist() {
		assertNotNull(service.getDistinctDistricts());
	}
	@Test
	 void CityUpDist() {
		assertNotNull(service.updateCityDistrict("Amsterdam", "Kurnool"));
	}
	@Test
	 void CountryTest() {
		assertNotNull(countryService.getAllCountries(), "error");
	}
	@Test
	 void CountryGetOne() {
		assertNotNull(countryService.getOneCountry("Anguilla"));
	}
	@Test
	 void CountryPop() {
		assertNotNull(countryService.populationLifeExpectancy("ARE"));
	}
	@Test
	 void CountryGnp() {
		String countryName = "Anguilla";
		double gnpValue = 9317.00;
		BigDecimal gnp = BigDecimal.valueOf(gnpValue);
		assertNotNull(countryService.updateGNP(countryName, gnp));
	}
	@Test
	 void CountryLangAll() {
		assertNotNull(countryLanguageService.getAllUniqueLanguages());
	}
	@Test
	 void UniqueLang() {
		assertNotNull(countryLanguageService.getAllOfficialLanguages());
	}
	@Test
	 void PercentageLang() {
		assertNotNull(countryLanguageService.getMaxPercentageLanguageByCountryCode("ARM"));
	}
}
