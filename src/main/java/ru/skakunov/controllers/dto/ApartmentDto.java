package ru.skakunov.controllers.dto;

import lombok.*;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class ApartmentDto {

    private String apartmentNumber;

    private Double area;
}
