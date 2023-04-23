package ru.skakunov.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.skakunov.controllers.dto.AddressDto;
import ru.skakunov.controllers.dto.CityDto;
import ru.skakunov.controllers.dto.IStreet;
import ru.skakunov.persist.model.Street;
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
    public ResponseEntity<AddressDto> searchForData(@RequestParam("address") String address) {
        return cityService.searchForData(address);
    }

    @GetMapping("/search/body")
    public AddressDto searchForData(@RequestBody AddressDto address) {
        return cityService.searchForData(address);
    }

    @GetMapping("/count")
    public List<IStreet> countHouses() {
        return cityService.countTotalHouses();
    }

}
