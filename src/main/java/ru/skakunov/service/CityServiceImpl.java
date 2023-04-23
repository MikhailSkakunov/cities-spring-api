package ru.skakunov.service;

import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.skakunov.controllers.dto.*;
import ru.skakunov.persist.CityRepository;
import ru.skakunov.persist.CityStreetHouseRepository;
import ru.skakunov.persist.model.Apartment;
import ru.skakunov.persist.model.City;
import ru.skakunov.persist.model.House;
import ru.skakunov.persist.model.Street;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
@AllArgsConstructor
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class CityServiceImpl implements CityService {

    private final CityRepository cityRepository;
    private EntityManager entityManager;
    private final CityStreetHouseRepository cityHouseRepository;
    private static ModelMapper modelMapper;
    private final static String DELIMITER = ",";

    @Autowired
    public CityServiceImpl(CityRepository cityRepository, CityStreetHouseRepository cityHouseRepository, ModelMapper modelMapper) {
        this.cityRepository = cityRepository;
        this.cityHouseRepository = cityHouseRepository;
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
    public ResponseEntity<AddressDto> searchForData(String address) {
        String[] values = address.split(DELIMITER);

        for (int i = 0; i < values.length; i++) {
            for (int j = 0; j < values.length && j != i; j++) {
                for (int k = 0; k < values.length && k != j && k != i; k++) {
                    for (int t = 0; t < values.length && t != k && t != j && t != i; t++) {
                        Query query = entityManager.createNativeQuery(
                                "select city_name, street_name, house_number, apartment_number " +
                                        "from cities c " +
                                        "join streets s on c.id=s.city_id " +
                                        "join houses h on h.street_id=s.id " +
                                        "join apartments a on a.house_id=h.id" +
                                        "  where c.city_name=:city_param " +
                                        "and s.street_name=:street_param " +
                                        "and h.house_number=:house_param " +
                                        "and a.apartment_number=:apartment_param");
                        query.setParameter("city_param", values[i]);
                        query.setParameter("street_param", values[j]);
                        query.setParameter("house_param", values[k]);
                        query.setParameter("apartment_param", values[t]);

                        List<Object> result = query.getResultList();
                        if (!result.isEmpty()) {
                           ArrayList<City> first = (ArrayList<City>) result.get(0);
                            ArrayList<CityDto> cityDtos = new ArrayList<>();
                            for (City city : first) {
                                cityDtos.add(toCityDto(city));
                            }

                            ArrayList<Street> second = (ArrayList<Street>) result.get(1);
                            ArrayList<StreetDto> streetDtos = new ArrayList<>();
                            for (Street street : second) {
                                streetDtos.add(toStreetDto(street));
                            }

                            ArrayList<House> third = (ArrayList<House>) result.get(2);
                            ArrayList<HouseDto> houseDtos = new ArrayList<>();
                            for (House house : third) {
                                houseDtos.add(toHouseDto(house));
                            }

                            ArrayList<Apartment> fourth = (ArrayList<Apartment>) result.get(3);
                            ArrayList<ApartmentDto> apartmentDtos = new ArrayList<>();
                            for (Apartment apartment : fourth) {
                                apartmentDtos.add(toApartmentDto(apartment));
                            }

                            return ResponseEntity.ok(new AddressDto (
                                    cityDtos,
                                    streetDtos,
                                    houseDtos,
                                    apartmentDtos));
                        }
                    }
                }
            }
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
    }

    @Override
    public AddressDto searchForData(AddressDto addressDto) {

        return null;
    }

    @Override
    public List<IStreet> countTotalHouses() {
//        return cityHouseRepository.countTotalHouses();
//                .stream()
//                .map(() -> S)
//                .collect(Collectors.toList());

        Query query = entityManager.createQuery(
                "SELECT s.streetName, " +
                        " COUNT(h) AS total" +
                        " FROM Street s" +
                        " LEFT JOIN s.houses h" +
                        " GROUP BY s.streetName");
        List<IStreet> streetList = query.getResultList();
        return streetList;
    }

    private static CityDto toCityDto (City city) {
        return modelMapper.map(city, CityDto.class);
    }

    private static City toCity(CityDto cityDto) {
        return modelMapper.map(cityDto, City.class);
    }

    private static StreetDto toStreetDto (Street street) {
        return modelMapper.map(street, StreetDto.class);
    }

    private static HouseDto toHouseDto (House house) {
        return modelMapper.map(house, HouseDto.class);
    }

    private static ApartmentDto toApartmentDto (Apartment apartment) {
        return modelMapper.map(apartment, ApartmentDto.class);
    }

//    private static StreetCountDto toCityCountDto() {
//        return modelMapper.map( StreetCountDto.class);
//    }
}
