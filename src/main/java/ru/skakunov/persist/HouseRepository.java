package ru.skakunov.persist;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.skakunov.persist.model.House;

public interface HouseRepository extends JpaRepository<House, Long> {
}
