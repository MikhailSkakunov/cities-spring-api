package ru.skakunov.service;


import org.springframework.transaction.annotation.Transactional;
import ru.skakunov.controllers.dto.CityDto;
import ru.skakunov.persist.model.City;

import java.util.List;

public interface CityService {

    List<CityDto> findAll();

    void save(CityDto cityDto);
}
