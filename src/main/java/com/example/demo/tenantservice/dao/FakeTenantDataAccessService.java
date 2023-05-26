package com.example.demo.tenantservice.dao;

import com.example.demo.tenantservice.model.Tenant;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository("fakeDao")
public class FakeTenantDataAccessService implements TenantDao {

    private static List<Tenant> DB = new ArrayList<>();

    @Override
    public int insertTenant(UUID id, Tenant tenant) {
        DB.add(new Tenant(id, tenant.getName()));
        return 1;
    }

    @Override
    public List<Tenant> selectAllTenants() {
        return DB;
    }

    @Override
    public Optional<Tenant> selectTenantById(UUID id) {
        return DB.stream()
                .filter(tenant -> tenant.getId().equals(id))
                .findFirst();
    }

    @Override
    public int deleteTenantByID(UUID id) {
        Optional<Tenant> tenantMaybe = selectTenantById(id);
        if (tenantMaybe.isEmpty()) {
            return 0;
        }

        DB.remove(tenantMaybe.get());
        return 1;
    }

    @Override
    public int updateTenantById(UUID id, Tenant update) {
        return selectTenantById(id)
                .map(tenant -> {
                    int indexToUpdate = DB.indexOf(tenant);
                    if (indexToUpdate >= 0) {
                        DB.set(indexToUpdate, new Tenant(id, update.getName()));
                        return 1;
                    }
                    return 0;
                })
                .orElse(0);
    }
}
