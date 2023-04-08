package ru.skakunov.persist;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.skakunov.persist.model.Apartment;

public interface ApartmentRepository extends JpaRepository<Apartment, Long> {
}
