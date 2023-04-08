package ru.skakunov.service;

import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.skakunov.controllers.dto.CityDto;
import ru.skakunov.persist.CityRepository;
import ru.skakunov.persist.model.City;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
@Transactional(readOnly = true)
public class CityServiceImpl implements CityService {

    private final CityRepository cityRepository;
    private static ModelMapper modelMapper;


    @Autowired
    public CityServiceImpl(CityRepository cityRepository, ModelMapper modelMapper) {
        this.cityRepository = cityRepository;
        CityServiceImpl.modelMapper = modelMapper;
    }

    public List<CityDto> findAll() {
        return cityRepository.findAll().stream()
                .map(CityServiceImpl::toCityDto).collect(Collectors.toList());
    }

    @Override
    @Transactional
    public void save(CityDto cityDto) {
        City city = toCity(cityDto);
        if (city.getId() != null) {
            throw new RuntimeException("////");
        }
        else cityRepository.save(city);
    }

    private static CityDto toCityDto (City city) {
        return modelMapper.map(city, CityDto.class);
    }

    private static City toCity(CityDto cityDto) {
        return modelMapper.map(cityDto, City.class);
    }

}
