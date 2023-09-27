/* Copyright © 2023 EIS Group and/or one of its affiliates. All rights reserved. Unpublished work under U.S. copyright laws.
CONFIDENTIAL AND TRADE SECRET INFORMATION. No portion of this work may be copied, distributed, modified, or incorporated into any other media without EIS Group prior written consent.*/
package com.example.demo_patients.services.validators;

import com.example.demo_patients.model.Patient;
import org.springframework.stereotype.Service;

/**
 * TODO description
 *
 * @author mskvortsova
 * @since 1.0.0
 */

@Service
public class PatientValidator {
    public boolean isTemperatureValid(Patient patient) {
        if (patient.getTemperature() < 33.00 || patient.getTemperature() > 43.00) {
            return false;
        }
        return true;
    }
}
