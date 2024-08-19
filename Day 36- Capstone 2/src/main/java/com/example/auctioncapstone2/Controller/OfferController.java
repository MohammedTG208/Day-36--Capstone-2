package com.example.auctioncapstone2.Controller;

import com.example.auctioncapstone2.Model.Offer;
import com.example.auctioncapstone2.Service.OfferService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/offer")
public class OfferController {
    private final OfferService offerService;

    @GetMapping("/getall")
    public ResponseEntity getAllOffers() {
        return ResponseEntity.ok(offerService.getAllOffers());
    }

    @PostMapping("/add")
    public ResponseEntity addOffer(@Valid @RequestBody Offer offer, Errors errors) {
        if (errors.hasErrors()) {
            return ResponseEntity.badRequest().body(errors.getFieldError().getDefaultMessage());
        }else {
            offerService.addNewOffer(offer);

            return ResponseEntity.status(200).body("offer added successfully");
        }
    }

    @PutMapping("/update/{id}")
    public ResponseEntity updateOffer(@PathVariable Integer id,@Valid @RequestBody Offer offer,Errors errors ) {
        if (errors.hasErrors()) {
            return ResponseEntity.status(400).body(errors.getFieldError().getDefaultMessage());
        }else {
            offerService.updateOffer(offer,id);
            return ResponseEntity.status(200).body("offer updated successfully");
        }
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity deleteOffer(@PathVariable Integer id) {
        offerService.deleteOffer(id);
        return ResponseEntity.status(200).body("offer deleted successfully");
    }
}
