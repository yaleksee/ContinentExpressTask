package com.example.demo.model;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

import java.io.Serializable;

@JsonDeserialize(using = DeserializerStatus.class)
public enum BookingStatus implements Serializable {

    Open("Open"),
    InProgress("InProgress"),
    Closed("Closed");

    private String text;

    BookingStatus(String text) {
        this.text = text;
    }

    public String getText() {
        return this.text;
    }

    public static BookingStatus fromText(String text) {
        for (BookingStatus r : BookingStatus.values()) {
            if (r.getText().equals(text)) {
                return r;
            }
        }
        throw new IllegalArgumentException();
    }
}
