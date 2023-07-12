package com.cg.world.service;

import java.math.BigDecimal;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import com.cg.world.entity.enums.IsOfficial;
import com.cg.world.repository.CountryRepository;
import com.cg.world.exception.CountryLanguageNotFoundException;
import com.cg.world.exception.CountryNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cg.world.entity.CountryLanguage;
import com.cg.world.repository.CountryLanguageRepository;

@Service
@Transactional
public class CountryLanguageServiceImpl implements CountryLanguageService {
	private  CountryLanguageRepository countryLanguageRepository;
	private CountryRepository countryRepository;
	@Autowired
    public CountryLanguageServiceImpl(CountryLanguageRepository countryLanguageRepository) {
        this.countryLanguageRepository = countryLanguageRepository;
    }
	@Autowired
	public void setCountryRepository(CountryRepository countryRepository) {
		this.countryRepository = countryRepository;
	}
	@Override
	public List<String> getAllUniqueLanguages() {
		return  countryLanguageRepository.findDistinctLanguage().orElseThrow(() -> new CountryLanguageNotFoundException("There Is No Distinct Country Languages!"));
	}
	@Override
	public List<CountryLanguage> getLanguagesByCountryCode(String countryCode) {
		List<CountryLanguage> languages= countryLanguageRepository.findByCountryCode(countryCode);
		if(languages.isEmpty()) throw new CountryNotFoundException("Provide Correct Country Code");
		return languages;
	}
	@Override
	public List<CountryLanguage> getAllOfficialLanguages() {
		List<CountryLanguage> countryLanguages = countryLanguageRepository.findAllByIsOfficial(IsOfficial.T);
		if(countryLanguages.isEmpty()) throw new CountryLanguageNotFoundException("No Official Languages Found");
		return countryLanguages;
	}
	@Override
	public List<CountryLanguage> getUnofficialLanguagesByCountryCode(String countryCode) {
		if(countryRepository.findByCode(countryCode).isPresent()){
			return countryLanguageRepository.findByCountryCodeAndIsOfficial(countryCode, IsOfficial.F);
		}else{
			throw new CountryNotFoundException("Check The Country Code Again! Country Code Not Found");
		}


	}

	@Override
	public Map<String, String> getLanguageWithMaxPercentageByCountryCode() {
		List<CountryLanguage> countryLanguages = countryLanguageRepository.findAll();
		if(countryLanguages.isEmpty()) throw new CountryLanguageNotFoundException("No Languages Found");
		return countryLanguages.stream()
				.collect(Collectors.groupingBy(
						countryLanguage -> countryLanguage.getCountry().getCode(),
						Collectors.collectingAndThen(
								Collectors.maxBy(Comparator.comparing(CountryLanguage::getPercentage)),
								language -> language.map(CountryLanguage::getLanguage).orElse(null))
				));
	}
	@Override
	public String getMaxPercentageLanguageByCountryCode(String countryCode) {
		List<CountryLanguage> countryLanguages = countryLanguageRepository.findByCountryCode(countryCode);
		if(countryLanguages.isEmpty()) throw new CountryNotFoundException("Country Code Not Found");
		Optional<CountryLanguage> maxPercentageLanguage = countryLanguages.stream().max(Comparator.comparing(CountryLanguage::getPercentage));
		if (maxPercentageLanguage.isPresent()) {
			CountryLanguage language = maxPercentageLanguage.get();
			return "Language: " + language.getLanguage() + ", Percentage: " + language.getPercentage();
		} else return "No language found for the given country code";
	}
	@Override
	public String updateOfficialByCountryAndLanguage(String countryCode, String language, char isOfficial) {
		CountryLanguage countryLanguage = countryLanguageRepository.findByCountryCodeAndLanguage(countryCode, language).orElseThrow(()->new CountryLanguageNotFoundException("Please The Country Code  And Language Once"));
		if (countryLanguage != null) {
			countryLanguage.setIsOfficial(isOfficial=='T' ? IsOfficial.F : IsOfficial.T);
			countryLanguageRepository.save(countryLanguage);
			return "IsOfficial flag updated successfully : "+isOfficial;
		} else return "Country language not found.";
	}
	public String updatePercentageByCountryAndLanguage(String countryCode, String language, BigDecimal percentage) {
		CountryLanguage countryLanguage = countryLanguageRepository.findByCountryCodeAndLanguage(countryCode, language)
				.orElseThrow(() -> new CountryNotFoundException("Country Code NOt Found"+countryCode));
		countryLanguage.setPercentage(percentage);
		countryLanguageRepository.save(countryLanguage);
		return "Percentage updated successfully: " + percentage;
	}

}
