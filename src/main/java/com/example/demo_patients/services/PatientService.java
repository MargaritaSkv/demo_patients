/* Copyright © 2023 EIS Group and/or one of its affiliates. All rights reserved. Unpublished work under U.S. copyright laws.
CONFIDENTIAL AND TRADE SECRET INFORMATION. No portion of this work may be copied, distributed, modified, or incorporated into any other media without EIS Group prior written consent.*/
package com.example.demo_patients.services;

import com.example.demo_patients.model.Patient;
import com.example.demo_patients.model.exceptions.EntityNotFoundException;
import com.example.demo_patients.model.exceptions.PatientTemperatureException;
import com.example.demo_patients.repository.PatientRepository;
import com.example.demo_patients.services.validators.PatientValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * TODO description
 *
 * @author mskvortsova
 * @since 1.0.0
 */

@Service
public class PatientService {

    @Autowired
    private PatientRepository patientRepository;

    @Autowired
    private PatientValidator patientValidator;

    public Patient createPatient(Patient patient) {
        if (patientValidator.isTemperatureValid(patient)) {
            return patientRepository.save(patient);
        } else {
            throw new PatientTemperatureException("Temperature must be between 33.00 and 43.00 degrees.");
        }
    }

    public Patient getPatientById(Long patientId) {
        return patientRepository.findById(patientId).orElseThrow(() -> new EntityNotFoundException("Patient is not found"));
    }
}
