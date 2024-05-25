package br.com.daniel.restwithspringbootandjava.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.daniel.restwithspringbootandjava.model.Person;

@Repository
public interface PersonRepository extends JpaRepository<Person, Long> {

}
