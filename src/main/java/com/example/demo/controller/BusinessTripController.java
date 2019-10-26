package com.example.demo.controller;

import com.example.demo.exeption.ResourceNotFoundException;
import com.example.demo.model.BookingStatus;
import com.example.demo.model.BusinessTrip;
import com.example.demo.repository.BusinessTripRepository;
import com.example.demo.service.BusinessTripService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api")
public class BusinessTripController {

    @Autowired
    private BusinessTripRepository repository;

    @Autowired
    private BusinessTripService businessTripService;

    @GetMapping("/allTrips")
    public List<BusinessTrip> allTrips() {
        return repository.findAll();
    }

    @GetMapping("/trip/{id}")
    public ResponseEntity<BusinessTrip> getTripById(@PathVariable(value = "id") Long Id) {
        BusinessTrip businessTrip = repository
                .findById(Id)
                .orElseThrow(() -> new ResourceNotFoundException("Employee not found for this id :: " + Id));
        return ResponseEntity.ok().body(businessTrip);
    }

    @PostMapping("/addTrip")
    public ResponseEntity<BusinessTrip> addTrip(@Valid @RequestBody BusinessTrip businessTrip) {
        repository.save(businessTrip);
        return ResponseEntity.ok(businessTrip);
    }

    @PostMapping("/updateTrip/{id}")
    public ResponseEntity<BusinessTrip> updateTrip(
            @PathVariable(value = "id") Long Id,
            @Valid @RequestBody BusinessTrip businessTrip
    ) {
        BusinessTrip trip = businessTripService.updateTrip(businessTrip, Id);
        return ResponseEntity.ok(trip);
    }

    @PostMapping("/changeStateTrip/{id}")
    public ResponseEntity<BusinessTrip> changeStateTrip(
            @RequestBody BookingStatus bookingStatus,
            @PathVariable Long id
    ) {
        BusinessTrip trip = businessTripService.changeState(bookingStatus, id);
        return ResponseEntity.ok(trip);
    }
}
