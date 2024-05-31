package br.com.daniel.restwithspringbootandjava.services;

import java.util.List;
import java.util.Optional;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.daniel.restwithspringbootandjava.exceptions.ResourceConflictException;
import br.com.daniel.restwithspringbootandjava.exceptions.ResourceNotFoundException;
import br.com.daniel.restwithspringbootandjava.model.Person;
import br.com.daniel.restwithspringbootandjava.repositories.PersonRepository;

@Service
public class PersonService {

    private static final String NO_RECORD_FOUND_FOR_THIS_ID = "No record found for this ID";

    @Autowired
    PersonRepository repository;
    
    private Logger logger = Logger.getLogger(PersonService.class.getName());

    public Person findById(Long id) {

        logger.info("Finding one person By ID");

        return repository.findById(id).orElseThrow(
            () -> new ResourceNotFoundException(NO_RECORD_FOUND_FOR_THIS_ID));
    }

    public List<Person> findAll() {

        logger.info("Finding all people");

        return repository.findAll();
    }

    public Person create(Person person) {
        logger.info("Creating one person!");

        Optional<Person> savedPerson = repository.findByEmail(person.getEmail());

        if(savedPerson.isPresent()) {
            throw new ResourceConflictException("Person already exist with given e-mail: " 
                + person.getEmail());
        }

        return repository.save(person);
    }

    public Person update(Person person) {
        logger.info("Updating one person!");

        var entity = repository.findById(person.getId()).orElseThrow(
            () -> new ResourceNotFoundException(NO_RECORD_FOUND_FOR_THIS_ID));

        entity.setFirstName(person.getFirstName());
        entity.setLastName(person.getLastName());
        entity.setAddress(person.getAddress());
        entity.setGender(person.getGender());
        entity.setEmail(person.getEmail());

        return repository.save(entity);
    }

    public void delete(Long id) {
        logger.info("Deleting one person!");
        var entity = repository.findById(id).orElseThrow(
            () -> new ResourceNotFoundException(NO_RECORD_FOUND_FOR_THIS_ID));
        repository.delete(entity);
    }

}
