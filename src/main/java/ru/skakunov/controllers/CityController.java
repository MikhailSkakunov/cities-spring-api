package ru.skakunov.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.skakunov.controllers.dto.*;
import ru.skakunov.service.CityService;

import java.util.List;

@RestController
@RequestMapping("/api/cities")
public class CityController {

    private final CityService cityService;

    @Autowired
    public CityController(CityService cityService) {
        this.cityService = cityService;
    }

    @GetMapping
    public List<CityDto> findAll() {
        return cityService.findAll();
    }

    @PostMapping("/new")
    public void create(@RequestBody CityDto cityDto) {
        cityService.save(cityDto);
    }

    @GetMapping("/search")
    public ResponseEntity<List<ISearch>> searchForData(@RequestParam("address") String address) {
        return cityService.searchAddress(address);
    }

    @GetMapping("/count")
    public List<ICity> countHouses() {
        return cityService.cityCountHouses();
    }

    @GetMapping("/count-house")
    public List<IHousesInCity> countHouseInStreetByCity( String cityName) {
        return cityService.countHouseInStreetByCity(cityName);
    }

    @GetMapping("/count-house-city")
    public List<IHousesWithApartments> countApartByStreet( String streetName) {
        return cityService.countApartByStreet( streetName);
    }
}
