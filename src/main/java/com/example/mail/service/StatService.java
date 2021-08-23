package com.example.mail.service;

import com.example.mail.entity.Stat;
import com.example.mail.payload.StatSearch;
import com.example.mail.repository.StatRepository;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class StatService {

    private final StatRepository statRepository;

    public StatService(StatRepository statRepository) {
        this.statRepository = statRepository;
    }

    public Stat updateOffice(Stat stat){
        return statRepository.save(stat);
    }

    public List<Stat> findAllById(Long id){
        return statRepository.findAllByIdMail(id);
    }
}
