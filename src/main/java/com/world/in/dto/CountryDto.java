package com.world.in.dto;

import java.math.BigDecimal;

public class CountryDto {

	private String name;
	private BigDecimal gnp;
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public BigDecimal getGnp() {
		return gnp;
	}
	public void setGnp(BigDecimal gnp) {
		this.gnp = gnp;
	}
	public CountryDto(String name, BigDecimal gnp) {
		super();
		this.name = name;
		this.gnp = gnp;
	}
	public CountryDto() {
		super();
	}
	
	
	
	
}
