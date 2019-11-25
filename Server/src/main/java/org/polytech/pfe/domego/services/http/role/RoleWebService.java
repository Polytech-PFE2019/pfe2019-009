package org.polytech.pfe.domego.services.http.role;

import org.polytech.pfe.domego.exceptions.role.RoleNotFoundException;
import org.polytech.pfe.domego.models.Role;
import org.polytech.pfe.domego.database.accessor.RoleAccessor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
public class RoleWebService implements RoleService{

    private RoleAccessor accessor;

    @Autowired
    public RoleWebService(RoleAccessor roleAccessor) {
        this.accessor = roleAccessor;
    }

    @Override
    @RequestMapping(value = "/Roles", method = RequestMethod.GET)
    public ResponseEntity<List<Role>> getAllRoles() {
        return ResponseEntity.ok(this.accessor.getAllRoles());
    }

    @Override
    @RequestMapping(value = "/Role/{id}", method = RequestMethod.GET)
    public ResponseEntity<Role> getRoleById(@PathVariable int id) {
        Optional<Role> role = this.accessor.getSpecificRoleById(id);
        if(role.isPresent())
            return ResponseEntity.ok(role.get());

        throw new RoleNotFoundException(id);

    }


}
