package ru.skakunov.service;

import ru.skakunov.persist.model.House;

import java.util.List;

public interface HouseService {

    List<House> findAllByStreet();
}
