/* Copyright © 2023 EIS Group and/or one of its affiliates. All rights reserved. Unpublished work under U.S. copyright laws.
CONFIDENTIAL AND TRADE SECRET INFORMATION. No portion of this work may be copied, distributed, modified, or incorporated into any other media without EIS Group prior written consent.*/
package com.example.demo_patients.controller;

import com.example.demo_patients.model.Patient;
import com.example.demo_patients.model.Room;
import com.example.demo_patients.repository.PatientRepository;
import com.example.demo_patients.repository.RoomRepository;
import com.example.demo_patients.services.PatientService;
import com.example.demo_patients.services.validators.RoomValidator;
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
@RequestMapping(value = "/patient")
public class PatientController {
    @Autowired
    private PatientService patientService;
    @Autowired
    private PatientRepository patientRepository;

    @Autowired
    private RoomRepository roomRepository;

    @Autowired
    private RoomValidator roomValidator;

    @GetMapping(value = "/{patientId}")
    public ResponseEntity<Patient> getPatient(@PathVariable Long patientId) {

        return new ResponseEntity<>(patientService.getPatientById(patientId), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Object> savePatient(@RequestBody Patient patientToSave) {
            Patient savedPatient = patientService.createPatient(patientToSave);
            return new ResponseEntity<>(savedPatient, HttpStatus.CREATED);
    }

    @PostMapping(value = "/releasePatient/{patientId}")
    public ResponseEntity<Patient> releasePatient(@PathVariable Long patientId){
        Patient patientToRelease = patientRepository.findById(patientId).get();
        Long roomId = patientToRelease.getRoomId();
        Room roomToModify = roomRepository.findById(roomId).get();
        roomToModify.removePatient(patientId);
        patientToRelease.setRoomId(null);

        if (roomValidator.isRoomEmpty(roomToModify)){
            roomToModify.setIsBooked(false);
        }

        return new ResponseEntity<>(patientRepository.save(patientToRelease), HttpStatus.OK);
    }
}
