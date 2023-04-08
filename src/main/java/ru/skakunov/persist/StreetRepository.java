package ru.skakunov.persist;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.skakunov.persist.model.Street;

public interface StreetRepository extends JpaRepository<Street, Long> {
}
