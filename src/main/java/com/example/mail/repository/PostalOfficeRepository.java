package com.example.mail.repository;

import com.example.mail.entity.PostalOffice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PostalOfficeRepository extends JpaRepository<PostalOffice, Long> {

    List<PostalOffice> findAllByOrderByIndexAsc();

}
