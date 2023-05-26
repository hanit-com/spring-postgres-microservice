package com.example.demo.tenantservice.api;

import com.example.demo.tenantservice.model.Tenant;
import com.example.demo.tenantservice.service.TenantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.UUID;

@Profile("tenantservice")
@RequestMapping("/api/v1/tenant")
@RestController
public class TenantController {
    private final TenantService tenantService;

    @Autowired
    public TenantController(TenantService tenantService) {
        this.tenantService = tenantService;
    }

    @PostMapping
    public void addTenant(@Valid @NotNull @RequestBody Tenant tenant) {
        tenantService.addTenant(tenant);
    }

    @GetMapping
    public List<Tenant> getAllTenants() {
        return tenantService.getAllTenants();
    }

    @GetMapping(path = "{id}")
    public Tenant getTenantById(@PathVariable("id") UUID id) {
        return tenantService.getTenantById(id).orElse(null);
    }

    @DeleteMapping(path = "{id}")
    public void deleteTenantById(@PathVariable("id") UUID id) {
        tenantService.deleteTenant(id);
    }

    @PutMapping(path = "{id}")
    public void updateTenant(@PathVariable("id") UUID id, @Valid @NotNull @RequestBody Tenant tenant) {
        tenantService.updateTenant(id, tenant);
    }
}
