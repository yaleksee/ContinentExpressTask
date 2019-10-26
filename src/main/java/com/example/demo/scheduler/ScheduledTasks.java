package com.example.demo.scheduler;

import com.example.demo.model.BookingStatus;
import com.example.demo.model.BusinessTrip;
import com.example.demo.repository.BusinessTripRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

@Component
public class ScheduledTasks {

    @Autowired
    BusinessTripRepository repository;

    @Scheduled(fixedRate = 2000)
    public void makeClosed() {

        BusinessTrip searchTrip = new BusinessTrip();
        searchTrip.setBookingStatus(BookingStatus.InProgress);
        List<BusinessTrip> allTripsInProgress = repository.findAll(Example.of(searchTrip));
        Date oneDayAgo = java.sql.Timestamp.valueOf(LocalDateTime.now().minusDays(1));

        for (BusinessTrip current : allTripsInProgress) {
            if (current.getTimeChangeStatus().before(oneDayAgo)) {
                current.setBookingStatus(BookingStatus.Closed);
                current.setTimeChangeStatus(new Date());
                repository.save(current);
            }
        }
    }
}
