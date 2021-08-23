package com.example.mail.service;

import com.example.mail.entity.Departure;
import com.example.mail.entity.Stat;
import com.example.mail.repository.DepartureRepository;
import com.example.mail.repository.StatRepository;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@Transactional
public class DepartureService {
    private final Integer status = 1;

    private final DepartureRepository departureRepository;

    private final StatRepository statRepository;

    public DepartureService(DepartureRepository departureRepository, StatRepository statRepository) {
        this.departureRepository = departureRepository;
        this.statRepository = statRepository;
    }

    public Departure add(Departure departure){
        Stat stat = new Stat();
        stat.setIdMail(departure.getId());
        stat.setIndexOffice(departure.getSenderIndex());
        stat.setStatus(status);
        statRepository.save(stat);

        return departureRepository.save(departure);
    }

    public void deleteById(Long id){
        departureRepository.deleteById(id);
    }

    public Departure findDepartureById(Long id){
        return departureRepository.findDepartureById(id);
    }
}
