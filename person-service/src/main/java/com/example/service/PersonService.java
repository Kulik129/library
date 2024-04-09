package com.example.service;

import com.example.dto.Request;
import com.example.dto.Response;

import java.util.UUID;

public interface PersonService {

    Response create(Request request);

    Response getPersonByUuid(UUID uuid);
}
