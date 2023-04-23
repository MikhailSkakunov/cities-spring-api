package ru.skakunov.controllers.dto;

public interface IStreet {

    String getId();

    String getStreetName();

    String getTotal();

    IHouse getHouses();

    interface IHouse {
        String getId();
    }
}
