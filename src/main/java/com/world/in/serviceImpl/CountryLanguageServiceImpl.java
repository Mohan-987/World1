package com.world.in.serviceImpl;

import java.math.BigDecimal;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import com.world.entity.enums.IsOfficial;
import com.world.in.entity.Country;
import com.world.in.exception.CountryLanguageNotFoundException;
import com.world.in.exception.CountryNotFoundException;
import com.world.in.repository.CountryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.world.in.entity.CountryLanguage;
import com.world.in.repository.CountryLanguageRepository;
import com.world.in.service.CountryLanguageService;

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
		 return countryLanguageRepository.findDistinctLanguage();
	}
	@Override
	public List<CountryLanguage> getLanguagesByCountryCode(String countryCode) {
		List<CountryLanguage> languages= countryLanguageRepository.findByCountryCode(countryCode);
		if(languages.isEmpty())
			throw new CountryNotFoundException("Provide Correct Country Code");
		return languages;
	}
	@Override
	public List<CountryLanguage> getAllOfficialLanguages() {
		List<CountryLanguage> countryLanguages = countryLanguageRepository.findAllByIsOfficial(IsOfficial.T);
		if(countryLanguages.isEmpty())
			throw new CountryLanguageNotFoundException("No Official Languages Found");
		return countryLanguages;
	}
	@Override
	public List<CountryLanguage> getUnofficialLanguagesByCountryCode(String countryCode) {
		countryRepository.findByCode(countryCode).orElseThrow(()->new CountryNotFoundException("Check The Country Code Again! Country Code NOt Found"));
		return countryLanguageRepository.findByCountryCodeAndIsOfficial(countryCode, IsOfficial.F);
	}
	@Override
	public String findLanguageWithMaxPercentage(String countryCode) {
		Country code = countryRepository.findByCode(countryCode).orElseThrow(()->new CountryNotFoundException("Country Code Not Found Exception"));
		Object[] result = countryLanguageRepository.findLanguageWithMaxPercentage(countryCode);
	    if (result != null) {
	        String language = (String) result[0];
	        BigDecimal percentage = (BigDecimal) result[1];
	        return "Language: " + language + ", Percentage: " + percentage;
	    }
	    return "No language found for the given country code.";
	}

	@Override
	public Map<String, String> getLanguageWithMaxPercentageByCountryCode() {
		List<CountryLanguage> countryLanguages = countryLanguageRepository.findAll();
		if(countryLanguages.isEmpty())
			throw new CountryLanguageNotFoundException("No Languages Found");
		Map<String, String> languageMap = countryLanguages.stream()
				.collect(Collectors.groupingBy(
						countryLanguage -> countryLanguage.getCountry().getCode(),
						Collectors.collectingAndThen(
								Collectors.maxBy(Comparator.comparing(CountryLanguage::getPercentage)),
								language -> language.map(CountryLanguage::getLanguage).orElse(null))
				));
		return languageMap;
	}
	@Override
	public String getMaxPercentageLanguageByCountryCode(String countryCode) {
		List<CountryLanguage> countryLanguages = countryLanguageRepository.findByCountryCode(countryCode);
		if(countryLanguages.isEmpty())
			throw new CountryNotFoundException("Country Code Not Found");
		Optional<CountryLanguage> maxPercentageLanguage = countryLanguages.stream().max(Comparator.comparing(CountryLanguage::getPercentage));
		if (maxPercentageLanguage.isPresent()) {
			CountryLanguage language = maxPercentageLanguage.get();
			return "Language: " + language.getLanguage() + ", Percentage: " + language.getPercentage();
		} else return "No language found for the given country code";
	}
	@Override
	public String updateIsOfficialFlag(String countryCode, String language, String isOfficial) {
	    String s = "";
	    List<CountryLanguage> countryLanguages = countryLanguageRepository.findByLanguage(language);
	    for (CountryLanguage countryLanguage : countryLanguages) {
	        if (countryLanguage.getCountry().getCode().equals(countryCode)) {
	            countryLanguage.setIsOfficial(IsOfficial.valueOf(isOfficial.toUpperCase()));
	            countryLanguageRepository.save(countryLanguage);
	            s = "Updated IsOfficial";
	            break;
	        } else s = "Country language not found.";
	    }
	    return s;
	}

	public String updatePercentageByCountryAndLanguage(String countryCode, String language, BigDecimal percentage) {

		CountryLanguage optionalCountryLanguage = countryLanguageRepository.findByCountryCodeAndLanguage(countryCode, language);
		if (optionalCountryLanguage!=null) {
			System.out.println();
			optionalCountryLanguage.setPercentage(percentage);
			//countryLanguageRepository.save(optionalCountryLanguage);
			return "Percentage updated successfully.";
		} else
			return "Country language not found.";
	}
}
