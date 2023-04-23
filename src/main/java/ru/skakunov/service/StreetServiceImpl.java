package ru.skakunov.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.skakunov.controllers.dto.HouseDto;
import ru.skakunov.controllers.dto.IStreet;
import ru.skakunov.persist.HouseRepository;
import ru.skakunov.persist.model.House;

import java.util.List;

@Slf4j
@Service
@Transactional(readOnly = true)
public class StreetServiceImpl implements StreetService{

    private final HouseRepository houseRepository;

    public StreetServiceImpl(HouseRepository houseRepository) {
        this.houseRepository = houseRepository;
    }
}
