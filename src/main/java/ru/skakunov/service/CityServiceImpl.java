package ru.skakunov.service;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.skakunov.controllers.dto.*;
import ru.skakunov.persist.CityRepository;
import ru.skakunov.persist.model.City;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
@AllArgsConstructor
@Transactional(readOnly = true)
public class CityServiceImpl implements CityService {

    private final CityRepository cityRepository;
    private static ModelMapper modelMapper;
    private final static String DELIMITER = ",";

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

    public ResponseEntity<List<ISearch>> searchAddress(String address) {
        String[] values = address.split(DELIMITER);

        for (int i = 0; i < values.length; i++) {
            for (int j = i; j < values.length; j++) {
                for (int k = j; k < values.length; k++) {
                    for (int t = k; t < values.length; t++) {
                        List<ISearch> search = cityRepository.searchAddress(values[i], values[j], values[k], values[t]);
                        if (!search.isEmpty()) {
                            return ResponseEntity.ok(search);
                        }
                    }
                }
            }
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
    }

    @Override
    public List<ICity> cityCountHouses() {
        return cityRepository.cityCountHouses();
    }

    @Override
    public List<IHousesInCity> countHouseInStreetByCity(String cityName) {
        return cityRepository.countHouseInStreetByCity(cityName);
    }

    @Override
    public List<IHousesWithApartments> countApartByStreet( String streetName) {
        return cityRepository.countApartByStreet( streetName);
    }

    private static CityDto toCityDto (City city) {
        return modelMapper.map(city, CityDto.class);
    }

    private static City toCity(CityDto cityDto) {
        return modelMapper.map(cityDto, City.class);
    }
}
