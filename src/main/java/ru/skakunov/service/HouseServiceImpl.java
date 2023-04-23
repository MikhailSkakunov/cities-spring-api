package ru.skakunov.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.skakunov.persist.model.House;

import java.util.List;

@Slf4j
@Service
@Transactional(readOnly = true)
public class HouseServiceImpl implements HouseService{


    @Override
    public List<House> findAllByStreet() {
        return null;
    }
}
