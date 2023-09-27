/* Copyright © 2023 EIS Group and/or one of its affiliates. All rights reserved. Unpublished work under U.S. copyright laws.
CONFIDENTIAL AND TRADE SECRET INFORMATION. No portion of this work may be copied, distributed, modified, or incorporated into any other media without EIS Group prior written consent.*/
package com.example.demo_patients.model;

import javax.persistence.*;
import java.util.List;

/**
 * TODO description
 *
 * @author mskvortsova
 * @since 1.0.0
 */

@Entity
public class Room {

    @Override
    public String toString() {
        return "Room{" +
                "id=" + id +
                ", roomNumber='" + roomNumber + '\'' +
                ", isBooked=" + isBooked +
                ", patientIds=" + patientIds +
                '}';
    }

    @GeneratedValue(strategy = GenerationType.AUTO)
    @Id
    private Long id;
    private String roomNumber;

    private boolean isBooked;

    @ElementCollection
    private List<Long> patientIds;

    public void addPatient(Long patientId) {
        patientIds.add(patientId);
    }

    public void removePatient(Long patientId) {
        patientIds.remove(patientId);
    }

    public List<Long> getPatientIds() {
        return patientIds;
    }

    public String getRoomNumber() {
        return roomNumber;
    }

    public void setRoomNumber(String roomNumber) {
        this.roomNumber = roomNumber;
    }

    public boolean getIsBooked() {
        return isBooked;
    }

    public void setIsBooked(boolean isBooked) {
        this.isBooked = isBooked;
    }

    public Long getId() {
        return id;
    }
}
