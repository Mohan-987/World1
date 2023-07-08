package com.world.in.dto;

public class CountryData {
	private String name;
    public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getPopulation() {
		return population;
	}

	public void setPopulation(int population) {
		this.population = population;
	}

	private int population;

    public CountryData(String name, int population) {
        this.name = name;
        this.population = population;
    }

}
