package com.example.demo.tenantservice.service;

import com.example.demo.tenantservice.dao.TenantDao;
import com.example.demo.tenantservice.model.Tenant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class TenantService {

    private final TenantDao tenantDao;

    @Autowired
    public TenantService(@Qualifier("postgres") TenantDao tenantDao) {
        this.tenantDao = tenantDao;
    }

    public int addTenant(Tenant tenant) {
        return tenantDao.insertTenant(tenant);
    }

    public List<Tenant> getAllTenants() {
        return tenantDao.selectAllTenants();
    }

    public Optional<Tenant> getTenantById(UUID id) {
        return tenantDao.selectTenantById(id);
    }

    public int deleteTenant(UUID id) {
        return tenantDao.deleteTenantByID(id);
    }

    public int updateTenant(UUID id, Tenant newTenant) {
        return tenantDao.updateTenantById(id, newTenant);
    }
}
