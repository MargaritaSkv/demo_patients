/* Copyright © 2023 EIS Group and/or one of its affiliates. All rights reserved. Unpublished work under U.S. copyright laws.
CONFIDENTIAL AND TRADE SECRET INFORMATION. No portion of this work may be copied, distributed, modified, or incorporated into any other media without EIS Group prior written consent.*/
package com.example.demo_patients.repository;

import com.example.demo_patients.model.Room;
import org.springframework.data.repository.CrudRepository;

/**
 * TODO description
 *
 * @author mskvortsova
 * @since 1.0.0
 */
public interface RoomRepository extends CrudRepository<Room, Long> {
}