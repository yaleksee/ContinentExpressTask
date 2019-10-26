package com.example.demo.service;

import com.example.demo.exeption.ResourceNotFoundException;
import com.example.demo.exeption.WrongStateException;
import com.example.demo.model.BookingStatus;
import com.example.demo.model.BusinessTrip;
import com.example.demo.repository.BusinessTripRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class BusinessTripService {
    @Autowired
    private BusinessTripRepository repository;

    public BusinessTrip changeState(
            BookingStatus newState,
            Long id
    ) {
        return repository.findById(id)
                .map(trip -> {
                    if (changeStateAllowed(trip.getBookingStatus(), newState)) {
                        trip.setBookingStatus(newState);
                        trip.setTimeChangeStatus(new Date());
                        return repository.save(trip);
                    } else throw new WrongStateException(
                            "Change from " + trip.getBookingStatus() + " to " + newState + " is not correct");
                })
                .orElseThrow(() -> new ResourceNotFoundException("BusinessTrip not found for this id :: " + id));
    }

    public BusinessTrip updateTrip(
            BusinessTrip newTrip,
            Long id
    ) {
        return repository.findById(id)
                .map(trip -> {
                    if (!updateAllowed(trip))
                        throw new WrongStateException("Open state only");
                    if (newTrip.getLtdName() != null) {
                        trip.setLtdName(newTrip.getLtdName());
                    }
                    if (newTrip.getReservation() != null) {
                        trip.setReservation(newTrip.getReservation());
                    }
                    if (newTrip.getDeparture() != null) {
                        trip.setReservation(newTrip.getDeparture());
                    }
                    if (newTrip.getBookingStatus() != null &
                            changeStateAllowed(trip.getBookingStatus(), newTrip.getBookingStatus())
                            ) {
                        trip.setBookingStatus(newTrip.getBookingStatus());
                    }
                    return repository.save(trip);
                })
                .orElseThrow(() -> new ResourceNotFoundException("BusinessTrip not found for this id :: " + id));
    }

    private boolean changeStateAllowed(
            BookingStatus current,
            BookingStatus changed
    ) {
        return changed.ordinal() >= current.ordinal();
    }

    private boolean updateAllowed(BusinessTrip trip) {
        return (trip.getBookingStatus().equals(BookingStatus.Open));

    }


}

