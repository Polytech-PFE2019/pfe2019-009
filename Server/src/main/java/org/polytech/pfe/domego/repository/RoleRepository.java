package org.polytech.pfe.domego.repository;

import org.polytech.pfe.domego.models.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
public class RoleRepository {

    @Autowired
    public RoleRepository() {
    }

    public List<Role> getAllRoles(){
        return new ArrayList<>();
    }

    public Optional<Role> getSpecificRoleById(int id){
        return Optional.empty();
    }
}
