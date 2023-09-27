/* Copyright © 2023 EIS Group and/or one of its affiliates. All rights reserved. Unpublished work under U.S. copyright laws.
CONFIDENTIAL AND TRADE SECRET INFORMATION. No portion of this work may be copied, distributed, modified, or incorporated into any other media without EIS Group prior written consent.*/
package com.example.demo_patients.controller;

import com.example.demo_patients.model.Patient;
import com.example.demo_patients.model.Room;
import com.example.demo_patients.model.exceptions.EntityNotFoundException;
import com.example.demo_patients.repository.PatientRepository;
import com.example.demo_patients.repository.RoomRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


/**
 * TODO description
 *
 * @author mskvortsova
 * @since 1.0.0
 */
@RestController
@RequestMapping(value = "/room")
public class RoomController {

    @Autowired
    private RoomRepository roomRepository;

    @Autowired
    private PatientRepository patientRepository;

    @GetMapping(value = "/{roomId}")
    public ResponseEntity<Room> getRoom(@PathVariable Long roomId) {
        Room roomToReturn = roomRepository.findById(roomId).orElseThrow(() -> new EntityNotFoundException("Room is not found"));
        return new ResponseEntity<>(roomToReturn, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Room> saveRoom(@RequestBody Room roomToSave) {
        return new ResponseEntity<>(roomRepository.save(roomToSave), HttpStatus.CREATED);
    }

    @PostMapping(value = "/{roomId}/assign/{patientId}")
    public ResponseEntity<Room> assignRoom(@PathVariable Long roomId, @PathVariable Long patientId) {
        Room roomToReturn = roomRepository.findById(roomId).get();
        Patient patient = patientRepository.findById(patientId).get();
        roomToReturn.addPatient(patientId);
        roomToReturn.setIsBooked(true);

        patient.setRoomId(roomId);
        patientRepository.save(patient);

        return new ResponseEntity<>(roomRepository.save(roomToReturn), HttpStatus.OK);

    }

    @PostMapping(value = "/{roomId}/empty")
    public ResponseEntity<Room> emptyRoom(@PathVariable Long roomId) {
        Room roomToReturn = roomRepository.findById(roomId).get();

        for (Long patientId : roomToReturn.getPatientIds()) {
            Patient patientToReturn = patientRepository.findById(patientId).get();
            patientToReturn.setRoomId(null);
        }

        roomToReturn.getPatientIds().clear();
        roomToReturn.setIsBooked(false);

        return new ResponseEntity<>(roomRepository.save(roomToReturn), HttpStatus.OK);

    }
}
