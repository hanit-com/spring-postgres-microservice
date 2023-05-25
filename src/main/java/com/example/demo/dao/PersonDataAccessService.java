package com.example.demo.dao;

import com.example.demo.model.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository("postgres")
public class PersonDataAccessService implements PersonDao {

    private final JdbcTemplate jdbcTemplate;
    private final String personTableName = "person";

    @Autowired
    public PersonDataAccessService(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public int insertPerson(UUID id, Person person) {
        final String query = String.format("INSERT INTO %s (id, name) VALUES (?, ?)", personTableName);
        jdbcTemplate.update(query, id, person.getName());

        return 1;
    }

    @Override
    public List<Person> selectAllPeople() {
        final String query = String.format("SELECT name, id FROM %s", personTableName);

        List<Person> people = jdbcTemplate.query(query, (resultSet, i) -> {
            UUID id = UUID.fromString(resultSet.getString("id"));
            String name = resultSet.getString("name");
            return new Person(id, name);
        });

        return people;
    }

    @Override
    public Optional<Person> selectPersonById(UUID id) {
        final String query = String.format("SELECT name, id FROM %s WHERE id = ?", personTableName);

        Person person = jdbcTemplate.queryForObject(query, (resultSet, i) -> {
            UUID personId = UUID.fromString(resultSet.getString("id"));
            String name = resultSet.getString("name");
            return new Person(personId, name);
        }, id);

        return Optional.ofNullable(person);
    }

    @Override
    public int deletePersonByID(UUID id) {
        final String query = String.format("DELETE FROM %s WHERE id = ?", personTableName);
        jdbcTemplate.update(query, id);

        return 1;
    }

    @Override
    public int updatePersonById(UUID id, Person person) {
        final String query = String.format("UPDATE %s SET name = ? WHERE id = ?", personTableName);
        jdbcTemplate.update(query, person.getName(), id);

        return 1;
    }
}
