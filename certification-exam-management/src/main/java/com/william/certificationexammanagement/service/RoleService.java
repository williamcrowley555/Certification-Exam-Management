package com.william.certificationexammanagement.service;

import com.william.certificationexammanagement.model.Role;

import java.util.List;

public interface RoleService {
    List<Role> getAllRoles();
    Role getRoleById(Long id);
    Role getRoleByNormalizedName(String normalizedName);
    Role saveRole(Role role);
    void deleteRoleById(Long id);
}
