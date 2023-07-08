	package com.world.in.dto;

import com.world.in.entity.Country;

public class UpdateGNPResponse {
    private String message;
    private Country updatedCountry;
    
    public UpdateGNPResponse(String message, Country updatedCountry) {
        this.message = message;
        this.updatedCountry = updatedCountry;
    }

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public Country getUpdatedCountry() {
		return updatedCountry;
	}

	public void setUpdatedCountry(Country updatedCountry) {
		this.updatedCountry = updatedCountry;
	}
    
    // Getters and setters
}

