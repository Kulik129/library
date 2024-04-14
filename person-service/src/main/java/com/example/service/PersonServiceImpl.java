package com.example.service;

import com.example.dto.CreateIssueBookRequest;
import com.example.dto.Request;
import com.example.dto.Response;
import com.example.entity.Person;
import com.example.repository.PersonRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class PersonServiceImpl implements PersonService{
    private final PersonRepository personRepository;
    private final ModelMapper modelMapper;
    @Override
    public Response create(Request request) {
        Person person = Person.builder()
                .uuid(UUID.randomUUID())
                .personName(request.getPersonName())
                .dateOfBirth(request.getDateOfBirth())
                .build();
        return modelMapper.map(personRepository.save(person), Response.class);
    }

    @Override
    public Response getPersonByUuid(UUID uuid) {
        Person person = personRepository.findByUuid(uuid);

        return Optional.of(person)
                .map(person1 -> modelMapper.map(person1, Response.class))
                .orElseThrow(()-> new RuntimeException("Пользователь не найден"));
    }

    public Response addIssueBook(CreateIssueBookRequest request) {
        Person person = personRepository.findByUuid(request.getReaderUuid());
        person.setBookId(request.getBookUuid());
        personRepository.save(person);
        return modelMapper.map(person, Response.class);
    }
}