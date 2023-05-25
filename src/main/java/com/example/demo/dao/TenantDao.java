package com.example.demo.dao;

import com.example.demo.model.Tenant;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface TenantDao {
    int insertTenant(UUID id, Tenant tenant);

    default int insertTenant(Tenant tenant) {
        UUID id = UUID.randomUUID();
        return insertTenant(id, tenant);
    }

    List<Tenant> selectAllTenants();

    Optional<Tenant> selectTenantById(UUID id);

    int deleteTenantByID(UUID id);

    int updateTenantById(UUID id, Tenant tenant);
}
