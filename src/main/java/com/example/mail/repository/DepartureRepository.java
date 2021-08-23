package com.example.mail.repository;

import com.example.mail.entity.Departure;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface DepartureRepository extends JpaRepository<Departure, Long> {

    Departure findDepartureById(Long id);
}
