package ru.skakunov.persist;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.skakunov.persist.model.Street;

import java.util.List;

public interface StreetRepository extends JpaRepository<Street, Long> {

    @Override
    List<Street> findAll();
}
