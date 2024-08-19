package com.example.auctioncapstone2.Controller;

import com.example.auctioncapstone2.Model.Rate;
import com.example.auctioncapstone2.Service.RateService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/rate")
public class RateController {
    private final RateService rateService;

    @GetMapping("/getall")
    public ResponseEntity getAllRate(){
        return ResponseEntity.status(200).body(rateService.getAllRates());
    }

    @PostMapping("/add")
    public ResponseEntity addRate(@Valid @RequestBody Rate rate, Errors errors){
        if(errors.hasErrors()){
            return ResponseEntity.status(400).body(errors.getFieldError().getDefaultMessage());
        }else {
            rateService.addNewRate(rate);
            return ResponseEntity.status(201).body("rate added successfully");
        }
    }

    @PutMapping("/update/{id}")
    public ResponseEntity updateRate( @PathVariable Integer id,@Valid @RequestBody Rate rate,Errors errors){
        if(errors.hasErrors()){
            return ResponseEntity.status(400).body(errors.getFieldError().getDefaultMessage());
        }else {
            rateService.updateRate(rate, id);
            return ResponseEntity.status(201).body("rate updated successfully");
        }
    }

    @DeleteMapping("/delete/{adminId}/{id}")
    public ResponseEntity deleteRate(@PathVariable Integer adminId,@PathVariable Integer id){
        rateService.deleteRate(adminId,id);
        return ResponseEntity.status(201).body("rate deleted successfully");
    }

    @GetMapping("/get/rate/by/{adminId}/{ownerId}")
    public ResponseEntity getRateByAdminId(@PathVariable Integer adminId,@PathVariable Integer ownerId){
        return ResponseEntity.status(200).body(rateService.getOwnerRatesForAdmin(ownerId,adminId));
    }
    @GetMapping("/get/owner/rate/for/user/{ownerId}")
    public ResponseEntity getRateByOwnerId(@PathVariable Integer ownerId){
        return ResponseEntity.status(200).body(rateService.getOwnerRatesForUser(ownerId));
    }

    @GetMapping("/get/best/owners")
    public ResponseEntity getBestOwners(){
        return ResponseEntity.status(200).body(rateService.getBestOwnerRate());
    }
}
