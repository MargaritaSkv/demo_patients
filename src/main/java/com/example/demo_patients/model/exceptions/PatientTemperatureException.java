/* Copyright © 2023 EIS Group and/or one of its affiliates. All rights reserved. Unpublished work under U.S. copyright laws.
CONFIDENTIAL AND TRADE SECRET INFORMATION. No portion of this work may be copied, distributed, modified, or incorporated into any other media without EIS Group prior written consent.*/
package com.example.demo_patients.model.exceptions;

/**
 * TODO description
 *
 * @author mskvortsova
 * @since 1.0.0
 */
public class PatientTemperatureException extends RuntimeException{
    public PatientTemperatureException(String message) {
        super(message);
    }
}
