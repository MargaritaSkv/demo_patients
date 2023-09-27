/* Copyright © 2023 EIS Group and/or one of its affiliates. All rights reserved. Unpublished work under U.S. copyright laws.
CONFIDENTIAL AND TRADE SECRET INFORMATION. No portion of this work may be copied, distributed, modified, or incorporated into any other media without EIS Group prior written consent.*/
package com.example.demo_patients.services.exceptions;

import com.example.demo_patients.model.Error;
import com.example.demo_patients.model.exceptions.EntityNotFoundException;
import com.example.demo_patients.model.exceptions.PatientTemperatureException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

/**
 * TODO description
 *
 * @author mskvortsova
 * @since 1.0.0
 */

@ControllerAdvice
public class ServicesExceptionHandler {

    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<Error> notFoundException(EntityNotFoundException patientNotFoundException){
        Error errorToReturn = new Error();
        errorToReturn.setStatusCode("404");
        errorToReturn.setErrorMessage(patientNotFoundException.getMessage());

        return new ResponseEntity<Error>(errorToReturn, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(PatientTemperatureException.class)
    public ResponseEntity<Error> patientTemperatureException(PatientTemperatureException patientTemperatureException){
        Error errorToReturn = new Error();
        errorToReturn.setStatusCode("400");
        errorToReturn.setErrorMessage(patientTemperatureException.getMessage());

        return new ResponseEntity<Error>(errorToReturn, HttpStatus.BAD_REQUEST);
    }
}
