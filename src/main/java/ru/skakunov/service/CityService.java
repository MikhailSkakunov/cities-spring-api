package ru.skakunov.service;

import org.springframework.http.ResponseEntity;
import ru.skakunov.controllers.dto.*;

import java.util.List;

public interface CityService {

    List<CityDto> findAll();

    void save(CityDto cityDto);

    ResponseEntity<List<ISearch>> searchAddress(String address);

    List<ICity> cityCountHouses();

    List<IHousesInCity> countHouseInStreetByCity(String cityName);

    List<IHousesWithApartments> countApartByStreet( String streetName);
}
