package ru.skakunov.service;

import org.springframework.http.ResponseEntity;
import ru.skakunov.controllers.dto.AddressDto;
import ru.skakunov.controllers.dto.CityDto;
import ru.skakunov.controllers.dto.IStreet;
import ru.skakunov.persist.model.Street;

import java.util.List;

public interface CityService {

    List<CityDto> findAll();

    void save(CityDto cityDto);

    ResponseEntity<AddressDto> searchForData(String address);

    AddressDto searchForData(AddressDto addressDto);

    List<IStreet> countTotalHouses();
}
