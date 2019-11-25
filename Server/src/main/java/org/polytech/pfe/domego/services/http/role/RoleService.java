package org.polytech.pfe.domego.services.http.role;

import org.polytech.pfe.domego.models.Role;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface RoleService {

    ResponseEntity<List<Role>> getAllRoles();

    ResponseEntity<Role> getRoleById(int id);


}
