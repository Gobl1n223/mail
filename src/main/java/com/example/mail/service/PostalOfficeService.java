package com.example.mail.service;

import com.example.mail.entity.PostalOffice;
import com.example.mail.repository.PostalOfficeRepository;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class PostalOfficeService {
    private final PostalOfficeRepository postalOfficeRepository;

    public PostalOfficeService(PostalOfficeRepository postalOfficeRepository) {
        this.postalOfficeRepository = postalOfficeRepository;
    }

    public PostalOffice add(PostalOffice postalOffice){
        return postalOfficeRepository.save(postalOffice);
    }

    public void deleteById(Long id){
        postalOfficeRepository.deleteById(id);
    }

    public List<PostalOffice> findAll() {
        return postalOfficeRepository.findAllByOrderByIndexAsc();
    }
    public PostalOffice update(PostalOffice postalOffice){
        return postalOfficeRepository.save(postalOffice);
    }
    public PostalOffice findById(Long id){
        return postalOfficeRepository.findById(id).get();
    }
}
