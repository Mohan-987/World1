package com.world.in.entity;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;


@Table(name = "city")
@Data
@Entity
public class City {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private int id;

	@NotEmpty
    @Column(name = "Name", columnDefinition = "char")
    private String name;

    @Column(name = "District", columnDefinition = "char")
    private String district;

	@NotNull
    @Column(name = "Population",columnDefinition = "char")
    private int population;

    @JsonIgnore
    @ManyToOne//(fetch = FetchType.LAZY)
    @JoinColumn(name = "CountryCode", referencedColumnName = "Code", insertable = false, updatable = false,columnDefinition = "char")
    private Country country;

    public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	public String getDistrict() {
		return district;
	}
	public void setDistrict(String district) {
		this.district = district;
	}
	public int getPopulation() {
		return population;
	}
	public void setPopulation(int population) {
		this.population = population;
	}
	public Country getCountry() {
		return country;
	}
	public void setCountry(Country country) {
		this.country = country;
	}

	@Override
	public String toString() {
		return "City [id=" + id + ", name=" + name + ", countryCode=" + ", district=" + district
				+ ", population=" + population + ", country=" + country + "]";
	}

	// Constructors
    public City() {
    }

	public City(int id, String name, String district, int population, Country country) {
		super();
		this.id = id;
		this.name = name;
		this.district = district;
		this.population = population;
		this.country = country;
	}

}
