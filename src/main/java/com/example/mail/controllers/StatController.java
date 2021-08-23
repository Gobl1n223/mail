package com.example.mail.controllers;

import com.example.mail.StatSearch;
import com.example.mail.entity.Departure;
import com.example.mail.entity.Stat;
import com.example.mail.payload.MessageResponse;
import com.example.mail.repository.StatRepository;
import com.example.mail.service.StatService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/stat")
public class StatController {

    private StatService statService;

    private StatRepository statRepository;

    public StatController(StatService statService, StatRepository statRepository) {
        this.statService = statService;
        this.statRepository = statRepository;
    }

    @PutMapping("/add")
    public ResponseEntity add (@RequestBody Stat stat){
        System.out.println("StatController: add------------------------");
        Departure departure = new Departure();
        if(stat.getIdMail() == 0 && stat.getIdMail() == null){
            return new ResponseEntity("only stat can be correct", HttpStatus.NOT_ACCEPTABLE);
        }
        if(stat.getIndexOffice() == 0 && stat.getIndexOffice() == null){
            return new ResponseEntity("missed param index office", HttpStatus.NOT_ACCEPTABLE);
        }
        if(stat.getStatus() == 0 && stat.getStatus() == null){
            return new ResponseEntity("It is necessary for the new item to have status 1", HttpStatus.NOT_ACCEPTABLE);
        }
            Stat stat1 = stat;
            stat1.setStatus(0);
            statService.updateOffice(stat1);
            return ResponseEntity.ok(new MessageResponse("The recipient took the parcel"));

    }

    @PutMapping("/delete")
    public ResponseEntity delete(@RequestBody Stat stat){
        System.out.println("StatController: delete------------------------");
        if(stat.getStatus() == 0 && stat.getStatus() == null){
            return ResponseEntity.ok(new MessageResponse("The parcel left the intermediate point"));
        }
        if(stat.getStatus() != 0 ){
            return new ResponseEntity("To delete, you need to change the status to 0", HttpStatus.NOT_ACCEPTABLE);
        }
        return ResponseEntity.ok(statService.updateOffice(stat));
    }
    @PostMapping("/findAll")
    public ResponseEntity<List<Stat>> findAll(@RequestBody StatSearch statSearch){
        return ResponseEntity.ok(statRepository.findAllByIdMail(statSearch.getIdMail()));
    }
}
