package com.example.atmmngtsystem.repositories;

import com.example.atmmngtsystem.models.Operations;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
@Repository
public interface OperationsRepository extends CrudRepository<Operations, Long> {
    @Override
    <S extends Operations> S save(S s);

    Operations findByDateTime(LocalDateTime dateTime);

}
