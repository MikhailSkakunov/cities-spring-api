package ru.skakunov.persist;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.skakunov.persist.model.City;

import java.util.List;

public interface CityRepository extends JpaRepository<City, Long> {

    List<City> findAll();



}
