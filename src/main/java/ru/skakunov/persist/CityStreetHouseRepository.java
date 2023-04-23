package ru.skakunov.persist;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.skakunov.persist.model.Street;

@Repository
public interface CityStreetHouseRepository extends JpaRepository<Street, Long> {

//    @Query("SELECT s.streetName, " +
//            " COUNT(h) AS total" +
//            " FROM Street s" +
//            " LEFT JOIN s.houses h" +
//            " JOIN City AS c ON s.id = c.id" +
//            " GROUP BY s.streetName")
//    List<IStreet> countTotalHouses();

}
