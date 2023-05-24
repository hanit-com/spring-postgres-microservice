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

    @Autowired
    public PersonDataAccessService(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public int insertPerson(UUID id, Person person) {
        jdbcTemplate.update(
                "INSERT INTO person (id, name) VALUES (?, ?)",
                id, person.getName()
        );

        return 1;
    }

    @Override
    public List<Person> selectAllPeople() {
        final String sql = "SELECT name, id FROM person";

        List<Person> people = jdbcTemplate.query(sql, (resultSet, i) -> {
            UUID id = UUID.fromString(resultSet.getString("id"));
            String name = resultSet.getString("name");
            return new Person(id, name);
        });

        return people;
    }

    @Override
    public Optional<Person> selectPersonById(UUID id) {
        final String sql = "SELECT name, id FROM person WHERE id = ?";

        Person person = jdbcTemplate.queryForObject(sql, (resultSet, i) -> {
            UUID personId = UUID.fromString(resultSet.getString("id"));
            String name = resultSet.getString("name");
            return new Person(personId, name);
        }, id);

        return Optional.ofNullable(person);
    }

    @Override
    public int deletePersonByID(UUID id) {
        jdbcTemplate.update(
                "DELETE FROM person WHERE id = ?",
                id
        );

        return 1;
    }

    @Override
    public int updatePersonById(UUID id, Person person) {
        jdbcTemplate.update(
                "UPDATE person SET name = ? WHERE id = ?",
                person.getName(), id
        );

        return 1;
    }
}
