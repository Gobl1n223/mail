package com.example.mail.controllers;

import com.example.mail.entity.PostalOffice;
import com.example.mail.service.PostalOfficeService;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.NoSuchElementException;
/**
 * Контроллер почтовых отделений
 * Проверки аналогичны как и в StatController
 */

@RestController
@RequestMapping("/postal")
public class PostalOfficeController {

    private PostalOfficeService postalOfficeService;

    public PostalOfficeController(PostalOfficeService postalOfficeService) {
        this.postalOfficeService = postalOfficeService;
    }
    //Показ всех почтовых отделений
    @GetMapping("/all")
    public List<PostalOffice> findAll(){

        System.out.println("PostOfficeController: all------------------------");
        return postalOfficeService.findAll();
    }
    //Добавление почтового отделения
    @PostMapping("/add")
    public ResponseEntity add(@RequestBody PostalOffice postalOffice){
        System.out.println("PostalOfficeController: add------------------------");
        if(postalOffice.getId() != null && postalOffice.getId() != 0) {
            return new ResponseEntity("redundant param: id MUST be null", HttpStatus.NOT_ACCEPTABLE);
        }
        if (postalOffice.getIndex() == null || postalOffice.getIndex() == 0){
            return new ResponseEntity("missed param index", HttpStatus.NOT_ACCEPTABLE);
        }
        if (postalOffice.getName() == null || postalOffice.getName().trim().length() == 0){
            return new ResponseEntity("missed param name", HttpStatus.NOT_ACCEPTABLE);
        }
        if (postalOffice.getAdress() == null || postalOffice.getAdress().trim().length() == 0){
            return new ResponseEntity("missed param address", HttpStatus.NOT_ACCEPTABLE);
        }
        return ResponseEntity.ok(postalOfficeService.add(postalOffice));
    }

    @PutMapping("/update")
    public ResponseEntity update (@RequestBody PostalOffice postalOffice){
        System.out.println("PostalOffice: update------------------------");
        if(postalOffice.getId() == 0 && postalOffice.getId() == null) {
            return new ResponseEntity("you need to specify a value", HttpStatus.NOT_ACCEPTABLE);
        }
            if (postalOffice.getIndex() == null || postalOffice.getIndex() == 0){
                return new ResponseEntity("missed param index", HttpStatus.NOT_ACCEPTABLE);
            }
            if (postalOffice.getName() == null || postalOffice.getName().trim().length() == 0){
                return new ResponseEntity("missed param name", HttpStatus.NOT_ACCEPTABLE);
            }
            if (postalOffice.getAdress() == null || postalOffice.getAdress().trim().length() == 0){
                return new ResponseEntity("missed param address", HttpStatus.NOT_ACCEPTABLE);
            }
        return ResponseEntity.ok(postalOfficeService.update(postalOffice));
    }

    @GetMapping("/id/{id}")
    public ResponseEntity<PostalOffice> findById(@PathVariable Long id){
        System.out.println("PostalOffice: findById------------------------");
        PostalOffice postalOffice = null;
        try{
            postalOffice = postalOfficeService.findById(id);
        }
        catch (NoSuchElementException e){
            e.printStackTrace();
            return new ResponseEntity("id = " + id + " " + "not found", HttpStatus.NOT_ACCEPTABLE);
        }
        return ResponseEntity.ok(postalOffice);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<PostalOffice> deleteById(@PathVariable Long id){
        System.out.println("PostalOffice: delete------------------------");
        PostalOffice postalOffice = null;
        try{
            postalOfficeService.deleteById(id);
        }catch (EmptyResultDataAccessException e){
            e.printStackTrace();
            return new ResponseEntity("id = "+ id + " " + "not found", HttpStatus.NOT_ACCEPTABLE);
        }
        return new ResponseEntity(HttpStatus.OK);
    }

}
