package com.example.mail.controllers;

import com.example.mail.payload.StatSearch;
import com.example.mail.entity.Stat;
import com.example.mail.payload.MessageResponse;
import com.example.mail.repository.StatRepository;
import com.example.mail.service.DepartureService;
import com.example.mail.service.StatService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Контроллер Статистики
 */
@RestController
@RequestMapping("/stat")
public class StatController {

    private StatService statService;

    private StatRepository statRepository;
    private DepartureService departureService;

    public StatController(StatService statService, StatRepository statRepository, DepartureService departureService) {
        this.statService = statService;
        this.statRepository = statRepository;
        this.departureService = departureService;
    }
    //Обновление перемещения посылки по почтовым отделениям(изменением индекса почтового оделение в котором находиться посылка)
    @PutMapping("/add")
    public ResponseEntity add (@RequestBody Stat stat){
        System.out.println("StatController: add------------------------");
        //Выводит ошибку если индификатор посылки равен 0
        if(stat.getIdMail() == 0 && stat.getIdMail() == null){
            return new ResponseEntity("only stat can be correct", HttpStatus.NOT_ACCEPTABLE);
        }
        //Выводит ошибку если индекс почтового отделения равен 0
        if(stat.getIndexOffice() == 0 && stat.getIndexOffice() == null){
            return new ResponseEntity("missed param index office", HttpStatus.NOT_ACCEPTABLE);
        }
        //Выдает ошибку если при перемещении посылки на новое почтовое отделение ставиться status 0
        if(stat.getStatus() == 0 && stat.getStatus() == null){
            return new ResponseEntity("It is necessary for the new item to have status 1", HttpStatus.NOT_ACCEPTABLE);
        }
        //Проверка: если индекс нового почтового оделение равен индексу почтового отделения получателя то посылка пришла и получатель ее забрал
        if(stat.getIndexOffice() == departureService.findDepartureById(stat.getIdMail()).getRecipientIndex()) {
            Stat stat1 = stat;
            stat1.setStatus(0);
            statService.updateOffice(stat1);
            return ResponseEntity.ok(new MessageResponse("The recipient took the parcel"));
        }
        return ResponseEntity.ok(statService.updateOffice(stat));
    }
    //Уход посылки из почтового отделение (нужно указывать вместе с id и ставить status 0)
    @PutMapping("/delete")
    public ResponseEntity delete(@RequestBody Stat stat){
        System.out.println("StatController: delete------------------------");
        //Если status равен 0 то посылка ушла из промежуточного почтового отделения
        if(stat.getStatus() == 0 && stat.getStatus() == null){
            return ResponseEntity.ok(new MessageResponse("The parcel left the intermediate point"));
        }
        //Если статус не равен 0 то посылка не может быть удалена так как для удаления нужно поствить 0 статусу
        if(stat.getStatus() != 0 ){
            return new ResponseEntity("To delete, you need to change the status to 0", HttpStatus.NOT_ACCEPTABLE);
        }
        return ResponseEntity.ok(statService.updateOffice(stat));
    }
    //Показ всего пути посылки
    @PostMapping("/findAll")
    public ResponseEntity<List<Stat>> findAll(@RequestBody StatSearch statSearch){
        return ResponseEntity.ok(statService.findAllById(statSearch.getIdMail()));
    }
}
