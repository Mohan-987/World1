package com.world.in;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import com.world.in.serviceImpl.CountryLanguageServiceImpl;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import com.world.in.serviceImpl.CityServiceImpl;
import com.world.in.serviceImpl.CountryServiceImpl;

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
	public void CityTest() {
		assertNotNull(service.getAllCities(), "error");
	}
	@Test
	public void CityTopTen() {
		String r = "[a-z]";
		assertNotNull(service.getFirstTenCitiesStartingWithChar(r.charAt(0)),"Error");
	}
	@Test
	public void CityAllRegion() {
		assertNotNull(service.fetchCityNamesAndRegions(),"Error");
	}
	@Test
	public void CityDist() {
		assertNotNull(service.getDistinctDistricts());
	}
	@Test
	public void CityUpDist() {
		assertNotNull(service.updateCityDistrict("Amsterdam", "Kurnool"));
	}
	@Test
	public void CountryTest() {
		assertNotNull(countryService.getAllCountries(), "error");
	}
	@Test
	public void CountryGetOne() {
		assertNotNull(countryService.getOneCountry("Anguilla"));
	}
	@Test
	public void CountryPop() {
		assertNotNull(countryService.populationLifeExpectancy("ARE"));
	}
	@Test
	public void CountryGnp() {
		String countryName = "Anguilla";
		double gnpValue = 9317.00;
		BigDecimal gnp = BigDecimal.valueOf(gnpValue);
		assertNotNull(countryService.updateGNP(countryName, gnp));
	}
	@Test
	public void CountryLangAll() {
		assertNotNull(countryLanguageService.getAllUniqueLanguages());
	}
	@Test
	public void UniqueLang() {
		assertNotNull(countryLanguageService.getAllOfficialLanguages());
	}
	@Test
	public void PercentageLang() {
		assertNotNull(countryLanguageService.getMaxPercentageLanguageByCountryCode("ARM"));
	}
}
