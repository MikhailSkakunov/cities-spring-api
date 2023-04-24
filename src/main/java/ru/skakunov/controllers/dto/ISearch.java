package ru.skakunov.controllers.dto;

public interface ISearch {

        Long getCityId();

        Long getStreetId();

        Long getHouseId();

        Long getApartId();

        String getFullAddress();
}
