package com.cg.world.dto;

import lombok.*;

import java.math.BigDecimal;
@AllArgsConstructor
@Setter
@Getter
@NoArgsConstructor
@ToString
public class CountryDto {
	private String name;
	private BigDecimal gnp;
}
