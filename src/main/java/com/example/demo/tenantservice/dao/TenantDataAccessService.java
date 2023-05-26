package com.example.demo.tenantservice.dao;

import com.example.demo.tenantservice.model.Tenant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository("postgres")
public class TenantDataAccessService implements TenantDao {

    private final JdbcTemplate jdbcTemplate;
    private final String tenantTableName = "tenant";

    @Autowired
    public TenantDataAccessService(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public int insertTenant(UUID id, Tenant tenant) {
        final String query = String.format("INSERT INTO %s (id, name) VALUES (?, ?)", tenantTableName);
        jdbcTemplate.update(query, id, tenant.getName());

        return 1;
    }

    @Override
    public List<Tenant> selectAllTenants() {
        final String query = String.format("SELECT name, id FROM %s", tenantTableName);

        List<Tenant> tenants = jdbcTemplate.query(query, (resultSet, i) -> {
            UUID id = UUID.fromString(resultSet.getString("id"));
            String name = resultSet.getString("name");
            return new Tenant(id, name);
        });

        return tenants;
    }

    @Override
    public Optional<Tenant> selectTenantById(UUID id) {
        final String query = String.format("SELECT name, id FROM %s WHERE id = ?", tenantTableName);

        Tenant tenant = jdbcTemplate.queryForObject(query, (resultSet, i) -> {
            UUID tenantId = UUID.fromString(resultSet.getString("id"));
            String name = resultSet.getString("name");
            return new Tenant(tenantId, name);
        }, id);

        return Optional.ofNullable(tenant);
    }

    @Override
    public int deleteTenantByID(UUID id) {
        final String query = String.format("DELETE FROM %s WHERE id = ?", tenantTableName);
        jdbcTemplate.update(query, id);

        return 1;
    }

    @Override
    public int updateTenantById(UUID id, Tenant tenant) {
        final String query = String.format("UPDATE %s SET name = ? WHERE id = ?", tenantTableName);
        jdbcTemplate.update(query, tenant.getName(), id);

        return 1;
    }
}
