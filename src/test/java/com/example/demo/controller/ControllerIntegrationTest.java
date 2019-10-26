package com.example.demo.controller;

import com.example.demo.TripApplication;
import com.example.demo.model.BookingStatus;
import com.example.demo.model.BusinessTrip;
import javafx.application.Application;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.*;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.client.HttpClientErrorException;

import java.util.Date;

import static org.junit.Assert.assertNotNull;


@RunWith(SpringRunner.class)
@SpringBootTest(classes = TripApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class ControllerIntegrationTest {
    @Autowired
    private TestRestTemplate restTemplate;

    @LocalServerPort
    private int port;

    private String getRootUrl() {
        return "http://localhost:" + port;
    }

    @Test
    public void contextLoads() {

    }

    @Test
    public void testGetAllBusinessTrips() {
        HttpHeaders headers = new HttpHeaders();
        HttpEntity<String> entity = new HttpEntity<String>(null, headers);
        ResponseEntity<String> response = restTemplate.exchange(getRootUrl() + "/allTrips",
                HttpMethod.GET, entity, String.class);
        assertNotNull(response.getBody());
    }

    @Test
    public void testGetBusinessTripById() {
        BusinessTrip BusinessTrip = restTemplate.getForObject(getRootUrl() + "/trip/1", BusinessTrip.class);
        System.out.println(BusinessTrip.getLtdName());
        assertNotNull(BusinessTrip);
    }

    @Test
    public void testCreateBusinessTrip() {
        BusinessTrip BusinessTrip = new BusinessTrip();
        BusinessTrip.setLtdName("ORACLE");
        BusinessTrip.setBookingStatus(BookingStatus.Open);
        BusinessTrip.setReservation(new Date());
        BusinessTrip.setDeparture(null);
        BusinessTrip.setTimeChangeStatus(null);
        ResponseEntity<BusinessTrip> postResponse = restTemplate.postForEntity(
                getRootUrl() + "/addTrip",
                BusinessTrip,
                BusinessTrip.class
        );
        assertNotNull(postResponse);
        assertNotNull(postResponse.getBody());
    }

    @Test
    public void testUpdateBusinessTrip() {
        int id = 1;
        BusinessTrip BusinessTrip = restTemplate.getForObject(
                getRootUrl() + "/updateTrip/" + id,
                BusinessTrip.class
        );
        BusinessTrip.setLtdName("Apple");
        BusinessTrip.setBookingStatus(BookingStatus.Open);
        BusinessTrip.setReservation(null);
        BusinessTrip.setDeparture(new Date());
        BusinessTrip.setTimeChangeStatus(new Date());
        restTemplate.put(getRootUrl() + "/updateTrip/" + id, BusinessTrip);
        BusinessTrip updatedBusinessTrip = restTemplate.getForObject(
                getRootUrl() + "/updateTrip/" + id,
                BusinessTrip.class
        );
        assertNotNull(updatedBusinessTrip);
    }
}
