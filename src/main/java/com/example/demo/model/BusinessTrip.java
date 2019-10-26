package com.example.demo.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

@Entity
@Table(name = "business_trip")
public class BusinessTrip {

    @Id
    @GeneratedValue
    private long id;

    private String ltdName;
    private BookingStatus bookingStatus;
    private Date reservation;
    private Date departure;
    private Date timeChangeStatus;

    public BusinessTrip() {

    }

    public BusinessTrip(String ltdName, BookingStatus bookingStatus, Date reservation, Date departure, Date timeChangeStatus) {
        this.ltdName = ltdName;
        this.bookingStatus = bookingStatus;
        this.reservation = reservation;
        this.departure = departure;
        this.timeChangeStatus = timeChangeStatus;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getLtdName() {
        return ltdName;
    }

    public void setLtdName(String ltdName) {
        this.ltdName = ltdName;
    }

    public BookingStatus getBookingStatus() {
        return bookingStatus;
    }

    public void setBookingStatus(BookingStatus bookingStatus) {
        this.bookingStatus = bookingStatus;
    }

    public Date getReservation() {
        return reservation;
    }

    public void setReservation(Date reservation) {
        this.reservation = reservation;
    }

    public Date getDeparture() {
        return departure;
    }

    public void setDeparture(Date departure) {
        this.departure = departure;
    }

    public Date getTimeChangeStatus() {
        return timeChangeStatus;
    }

    public void setTimeChangeStatus(Date timeChangeStatus) {
        this.timeChangeStatus = timeChangeStatus;
    }

}
