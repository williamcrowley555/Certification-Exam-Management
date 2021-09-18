package com.william.certificationexammanagement.service.impl;

import com.william.certificationexammanagement.model.Role;
import com.william.certificationexammanagement.repository.RoleRepository;
import com.william.certificationexammanagement.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RoleServiceImpl implements RoleService {

    @Autowired
    private RoleRepository roleRepository;

    @Override
    public List<Role> getAllRoles() {
        return roleRepository.findAll();
    }

    @Override
    public Role getRoleById(Long id) {
        Role role = null;
        Optional<Role> optional = roleRepository.findById(id);
        if (optional.isPresent()) {
            role = optional.get();
        } else {
            throw new RuntimeException("Role ID: " + id + " does not exist");
        }
        return role;
    }

    @Override
    public Role getRoleByName(String name) {
        Role role = null;
        Optional<Role> optional = roleRepository.findByName(name);

        if (optional.isPresent()) {
            role = optional.get();
        } else {
            throw new RuntimeException("Role name : " + name + " does not exist");
        }

        return role;
    }

    @Override
    public Role getRoleByNormalizedName(String normalizedName) {
        Role role = null;
        Optional<Role> optional = roleRepository.findByNormalizedName(normalizedName);

        if (optional.isPresent()) {
            role = optional.get();
        } else {
            throw new RuntimeException("Role normalize name : " + normalizedName + " does not exist");
        }

        return role;
    }

    @Override
    public Role saveRole(Role role) {
        return roleRepository.save(role);
    }

    @Override
    public void deleteRoleById(Long id) {
        roleRepository.deleteById(id);
    }
}
