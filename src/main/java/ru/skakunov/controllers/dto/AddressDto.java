package ru.skakunov.controllers.dto;

import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class AddressDto {

    private List<CityDto> cityDtos = new ArrayList<>();

    private List<StreetDto> streetDtos = new ArrayList<>();

    private List<HouseDto> houseDtos = new ArrayList<>();

    private List<ApartmentDto> apartmentDtos = new ArrayList<>();

}
