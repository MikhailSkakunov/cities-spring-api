package ru.skakunov.persist;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import ru.skakunov.controllers.dto.ICity;
import ru.skakunov.controllers.dto.IHousesInCity;
import ru.skakunov.controllers.dto.IHousesWithApartments;
import ru.skakunov.controllers.dto.ISearch;
import ru.skakunov.persist.model.City;

import java.util.List;

@Repository
public interface CityRepository extends JpaRepository<City, Long> {

    @Query("select c.cityName as cityName, " +
            "count (h.id) as countHouse " +
            "from City c " +
            "join c.streets s " +
            "join s.houses h " +
            "group by c.cityName")
    List<ICity> cityCountHouses();

    @Query("select s.streetName as streetName, " +
            "count (h.id) as countHouse " +
            "from City c " +
            "join c.streets s " +
            "join s.houses h " +
            "where c.cityName = :cityName " +
            "group by s.streetName")
    List<IHousesInCity> countHouseInStreetByCity(String cityName);

    @Query("select " +
            "concat(c.cityName, ', ', s.streetName, ', ', h.houseNumber) as fullAddress, " +
            "count(a.id) as apartCount " +
            "from City c " +
            "join c.streets s " +
            "join s.houses h " +
            "join h.apartments a " +
            "where s.streetName = :streetName " +
            "group by c.cityName, s.streetName, h.houseNumber")
    List<IHousesWithApartments> countApartByStreet(String streetName);

    @Query("select c.id as cityId, s.id as streetId, h.id as houseId, a.id as apartId, " +
            "concat(c.cityName, ', ', s.streetName, ', ', h.houseNumber) as fullAddress " +
            "from City c " +
            "join c.streets s " +
            "join s.houses h " +
            "join h.apartments a " +
            "where c.cityName = :cityName " +
            "and s.streetName = :streetName " +
            "and h.houseNumber = :houseNumber " +
            "and a.apartmentNumber = :apartNumber")
    List<ISearch> searchAddress(String cityName, String streetName, String houseNumber, String apartNumber);

}
